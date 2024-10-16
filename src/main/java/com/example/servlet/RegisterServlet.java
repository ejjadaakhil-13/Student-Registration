package com.example.servlet;

import com.example.dao.StudentDAO;
import com.example.model.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/register")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
                 maxFileSize = 1024 * 1024 * 5, 
                 maxRequestSize = 1024 * 1024 * 5 * 5)
public class RegisterServlet extends HttpServlet {
    private StudentDAO studentDAO;

    public void init() {
        
        studentDAO = new StudentDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String className = request.getParameter("class");
        String gender = request.getParameter("gender");
        int age = Integer.parseInt(request.getParameter("age"));
        Date dob = null;
        try {
            dob = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dob"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Handle file upload
        Part filePart = request.getPart("photo");
        String fileName = getSubmittedFileName(filePart);
        String uploadDir = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdir();
        }
        String filePath = uploadDir + File.separator + fileName;
        filePart.write(filePath);

        Student newStudent = new Student(fullName, email, filePath, className, gender, age, dob);

        try {
            studentDAO.insertStudent(newStudent);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("register.jsp");
    }

    private String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }
}