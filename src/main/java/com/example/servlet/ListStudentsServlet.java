//package com.example.servlet;
//
//import com.example.dao.StudentDAO;
//import com.example.model.Student;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.WebServlet;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
////@WebServlet("/list")
//public class ListStudentsServlet extends HttpServlet {
//    private StudentDAO studentDAO;
//
//    public void init() {
//        try {
//            studentDAO = new StudentDAO();
//        } catch (RuntimeException e) {
//            System.err.println("Error initializing StudentDAO in ListStudentsServlet: " + e.getMessage());
//        }
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        // Check if user is logged in
//        HttpSession session = request.getSession(false); // Don't create a new session if it doesn't exist
//        if (session == null || session.getAttribute("isLoggedIn") == null) {
//            // Redirect to admin login page
//            response.sendRedirect("admin-login");
//            return;
//        }
//
//        try {
//            if (studentDAO == null) {
//                throw new ServletException("StudentDAO is not initialized");
//            }
//            List<Student> listStudent = studentDAO.selectAllStudents();
//            request.setAttribute("listStudent", listStudent);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("admin-dashboard.jsp");
//            dispatcher.forward(request, response);
//        } catch (SQLException | ServletException e) {
//            System.err.println("Error in ListStudentsServlet: " + e.getMessage());
//            throw new ServletException(e);
//        }
//    }
//}


package com.example.servlet;

import com.example.dao.StudentDAO;
import com.example.model.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/list")
public class ListStudentsServlet extends HttpServlet {
    private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Student> listStudent = studentDAO.selectAllStudents();
            request.setAttribute("listStudent", listStudent);
            RequestDispatcher dispatcher = request.getRequestDispatcher("list-students.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
