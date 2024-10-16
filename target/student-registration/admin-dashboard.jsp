<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - Student Registration System</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <style>
        /* Container Styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }
        .header {
            background-color: #343a40;
            color: white;
            padding: 1rem;
            text-align: center;
        }
        .container {
            padding: 2rem;
        }
        /* Navigation Bar */
        .navbar {
            display: flex;
            justify-content: space-between;
            background-color: #495057;
            padding: 0.5rem 1rem;
        }
        .navbar a {
            color: white;
            text-decoration: none;
            margin-right: 1rem;
            font-weight: bold;
        }
        .navbar a:hover {
            text-decoration: underline;
        }
        /* Messages */
        .message {
            padding: 0.75rem;
            margin-bottom: 1rem;
            border-radius: 4px;
        }
        .success {
            background-color: #d4edda;
            color: #155724;
        }
        .error {
            background-color: #f8d7da;
            color: #721c24;
        }
        /* Student Table */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 1rem;
        }
        table, th, td {
            border: 1px solid #dee2e6;
        }
        th, td {
            padding: 0.75rem;
            text-align: left;
        }
        th {
            background-color: #e9ecef;
        }
        tr:nth-child(even) {
            background-color: #f1f3f5;
        }
        /* Action Links */
        .actions a {
            margin-right: 0.5rem;
            color: #dc3545;
            text-decoration: none;
        }
        .actions a.edit {
            color: #007bff;
        }
        .actions a:hover {
            text-decoration: underline;
        }
        /* Add Student Button */
        .add-button {
            display: inline-block;
            padding: 0.5rem 1rem;
            background-color: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            margin-bottom: 1rem;
        }
        .add-button:hover {
            background-color: #218838;
        }
        /* Responsive Image */
        .student-photo {
            width: 50px;
            height: 50px;
            object-fit: cover;
            border-radius: 50%;
        }
    </style>
</head>
<body>
    <!-- Header -->
    <div class="header">
        <h1>Admin Dashboard</h1>
        <p>Welcome to the Student Registration System</p>
    </div>

    <!-- Navigation Bar -->
    <div class="navbar">
        <div>
            <a href="admin-dashboard">Dashboard</a>
            <a href="register.jsp">Add New Student</a>
        </div>
        <div>
            <a href="logout">Logout</a>
        </div>
    </div>

    <!-- Main Container -->
    <div class="container">
        <!-- Success Message -->
        <c:if test="${not empty successMessage}">
            <div class="message success">
                <c:out value="${successMessage}" />
            </div>
        </c:if>

        <!-- Error Message -->
        <c:if test="${not empty errorMessage}">
            <div class="message error">
                <c:out value="${errorMessage}" />
            </div>
        </c:if>

        <!-- Students Table -->
        <c:choose>
            <c:when test="${not empty listStudent}">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Photo</th>
                            <th>Full Name</th>
                            <th>Email</th>
                            <th>Class</th>
                            <th>Gender</th>
                            <th>Age</th>
                            <th>Date of Birth</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="student" items="${listStudent}">
                            <tr>
                                <td><c:out value="${student.id}" /></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty student.photoPath}">
                                            <img src="${pageContext.request.contextPath}/images/${fn:substringAfter(student.photoPath, 'uploads/')}" 
                                                 alt="Photo of ${student.fullName}" 
                                                 class="student-photo" />
                                        </c:when>
                                        <c:otherwise>
                                            <img src="${pageContext.request.contextPath}/images/default-photo.png" 
                                                 alt="Default Photo" 
                                                 class="student-photo" />
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><c:out value="${student.fullName}" /></td>
                                <td><c:out value="${student.email}" /></td>
                                <td><c:out value="${student.className}" /></td>
                                <td><c:out value="${student.gender}" /></td>
                                <td><c:out value="${student.age}" /></td>
                                <td><c:out value="${student.dob}" /></td>
                                <td class="actions">
                                    <a href="edit?id=${student.id}" class="edit">Edit</a>
                                    <a href="delete?id=${student.id}" onclick="return confirm('Are you sure you want to delete this student?');">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>No students registered yet.</p>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Optional JavaScript -->
    <script src="js/script.js"></script>
</body>
</html>
