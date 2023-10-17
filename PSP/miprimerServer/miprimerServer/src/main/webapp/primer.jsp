<%@ page import="java.time.LocalDateTime" %><%--
  Created by IntelliJ IDEA.
  User: oscar
  Date: 12/10/2022
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib uri="jakarta.tags.core" prefix="c" %>--%>

<html>
<head>
    <title>Title</title>
</head>
<body>

    <c:forEach items="${list}" var="item">
        <h1><c:out value="${item}"/></h1>

    </c:forEach>

</body>
</html>


