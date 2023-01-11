
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Visits</title>
</head>
<body>
<c:set var="visits" scope="session" value="${visitCounter}"></c:set>
<h2> Page with students was visited <c:out value="${visits}" default="none"/> times</h2>
</body>
</html>
