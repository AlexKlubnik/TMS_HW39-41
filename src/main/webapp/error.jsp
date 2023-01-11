<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Request Error</title>
</head>
<body>
<c:set var="message" scope="session" value="${excMessage}"/>
<c:set var="URI" scope="session" value="${excURI}"/>
<c:set var="URL" scope="session" value="${excURL}"/>

<h1>You have error: <c:out value="${message}"/>. At request: <c:out value="${URL}"/></h1>
</body>
</html>
