package com.example.servlet;

import com.example.dao.StudentDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete")
public class DeleteStudentServlet extends HttpServlet {
    private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            studentDAO.deleteStudent(id);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        response.sendRedirect("list");
    }
}