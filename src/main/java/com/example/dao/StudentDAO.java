package com.example.dao;

import com.example.model.Student;
import com.example.util.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private String jdbcDriver;

    public StudentDAO() {
        try {
            this.jdbcURL = DatabaseConfig.getUrl();
            this.jdbcUsername = DatabaseConfig.getUsername();
            this.jdbcPassword = DatabaseConfig.getPassword();
            this.jdbcDriver = DatabaseConfig.getDriverClassName();
        } catch (RuntimeException e) {
            System.err.println("Error initializing StudentDAO: " + e.getMessage());
            throw e;
        }
    }

    protected Connection getConnection() throws SQLException {
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found: " + jdbcDriver, e);
        }
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }


    public void insertStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (full_name, email, photo_path, class, gender, age, dob) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, student.getFullName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getPhotoPath());
            preparedStatement.setString(4, student.getClassName());
            preparedStatement.setString(5, student.getGender());
            preparedStatement.setInt(6, student.getAge());
            preparedStatement.setDate(7, new java.sql.Date(student.getDob().getTime()));
            preparedStatement.executeUpdate();
        }
    }

    public List<Student> selectAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setFullName(rs.getString("full_name"));
                student.setEmail(rs.getString("email"));
                student.setPhotoPath(rs.getString("photo_path"));
                student.setClassName(rs.getString("class"));
                student.setGender(rs.getString("gender"));
                student.setAge(rs.getInt("age"));
                student.setDob(rs.getDate("dob"));
                students.add(student);
            }
        }
        return students;
    }

    public Student selectStudent(int id) throws SQLException {
        Student student = null;
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setFullName(rs.getString("full_name"));
                    student.setEmail(rs.getString("email"));
                    student.setPhotoPath(rs.getString("photo_path"));
                    student.setClassName(rs.getString("class"));
                    student.setGender(rs.getString("gender"));
                    student.setAge(rs.getInt("age"));
                    student.setDob(rs.getDate("dob"));
                }
            }
        }
        return student;
    }

    public boolean updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET full_name = ?, email = ?, photo_path = ?, class = ?, gender = ?, age = ?, dob = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, student.getFullName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getPhotoPath());
            preparedStatement.setString(4, student.getClassName());
            preparedStatement.setString(5, student.getGender());
            preparedStatement.setInt(6, student.getAge());
            preparedStatement.setDate(7, new java.sql.Date(student.getDob().getTime()));
            preparedStatement.setInt(8, student.getId());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    public boolean deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }
}