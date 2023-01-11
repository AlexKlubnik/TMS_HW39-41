<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Students</title>
</head>
<body>

<h1>List of students</h1>
<c:forEach var="student" items="${students}">
    <div><c:out value="${student.name}"/> <a href="/student?method=getById&id=${student.id}">View student info</a></div>
</c:forEach>
<div><a href="/studentform?method=create">Add new student</a></div>
</body>
</html>
