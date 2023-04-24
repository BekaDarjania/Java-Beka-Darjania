package com.exam.assignment;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class BlogPost extends HttpServlet {
    String url = "jdbc:mysql://localhost:3306/btu?autoReconnect=true&useSSL=false";
    String username = "root";
    String password = "password";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String title = request.getParameter("title");
        String body = request.getParameter("body");
        String imageLink = request.getParameter("image_link");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO posts (first_name, last_name, title, body, image_link) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, title);
            pstmt.setString(4, body);
            pstmt.setString(5, imageLink);

            pstmt.executeUpdate();
            pstmt.close();

            response.sendRedirect(request.getContextPath() + "/blogPost");

        } catch (SQLException e) {
            Writer writer = response.getWriter();
            writer.write(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<String> posts = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM posts";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                String body = rs.getString("body");
                String author = rs.getString("first_name") + " " + rs.getString("last_name");
                String post = title + " by " + author + ": " + body;
                posts.add(post);
            }

            rs.close();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Error selecting posts");
            e.printStackTrace();
        }

        PrintWriter out = resp.getWriter();
        out.println("<html><body><h1>Posts</h1><ul>");
        for (String post : posts) {
            out.println("<li>" + post + "</li>");
        }
        out.println("</ul></body></html>");
    }
}
