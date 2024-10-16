<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <div class="container">
        <h2>Student List</h2>
        <table>
            <tr>
                <th>ID</th>
                <th>Full Name</th>
                <th>Email</th>
                <th>Class</th>
                <th>Gender</th>
                <th>Age</th>
                <th>Date of Birth</th>
                <th>Photo</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="student" items="${listStudent}">
                <tr>
                    <td><c:out value="${student.id}" /></td>
                    <td><c:out value="${student.fullName}" /></td>
                    <td><c:out value="${student.email}" /></td>
                    <td><c:out value="${student.className}" /></td>
                    <td><c:out value="${student.gender}" /></td>
                    <td><c:out value="${student.age}" /></td>
                    <td><fmt:formatDate value="${student.dob}" pattern="yyyy-MM-dd" /></td>
                    <td>
                        <img src="${student.photoPath}" alt="Student Photo" width="100">
                    </td>
                    <td>
                        <a href="edit?id=<c:out value='${student.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?id=<c:out value='${student.id}' />" onclick="return confirm('Are you sure you want to delete this student?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>