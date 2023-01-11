<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add student</title>
</head>
<body>
<br>
<form action="studentform" method="post">
    <div>Student name: <input type="text" name="name"/>
        <c:set var="nameerror" scope="application" value="${nameError}"></c:set>
        <c:if test="${nameerror!=null}">
            <div style="color: red"><c:out value="${nameerror}"/></div>
        </c:if>
    </div>
    <div>Student address: <input type="text" name="address">
        <c:set var="addresserror" scope="application" value="${addressError}"></c:set>
        <c:if test="${addressError!=null}">
            <div style="color: red"><c:out value="${addressError}"/></div>
        </c:if>
    </div>
    <br/>
    <input type="submit" value="Add">
</form>
</body>
</html>
