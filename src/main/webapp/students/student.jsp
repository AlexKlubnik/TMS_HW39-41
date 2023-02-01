<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student info</title>
</head>
<body>

<c:set var="name" scope="session" value="${name}"></c:set>
<c:set var="id" scope="session" value="${id}"></c:set>
<c:set var="address" scope="session" value="${address}"></c:set>

<h1>Student: <c:out value="${name}"/></h1>
<div>Student ID is: <c:out value="${id}"/></div>
<div>Student address is: <c:out value="${address}"/></div>
</body>
</html>
