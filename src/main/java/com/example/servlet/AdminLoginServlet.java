package com.example.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/admin-login")
public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Implement your authentication logic here
        if (isValidAdmin(email, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("isLoggedIn", true);
            response.sendRedirect("admin-dashboard.jsp"); // Redirect to admin dashboard
        } else {
            response.sendRedirect("admin-login?error=1"); // Redirect with error
        }
    }

    private boolean isValidAdmin(String email, String password) {
        // Add your admin validation logic here
        return true; // Placeholder
    }
}

