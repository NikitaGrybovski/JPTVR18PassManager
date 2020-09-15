<%-- 
    Document   : listResources
    Created on : Sep 15, 2020, 12:31:27 PM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        
        <ul>
            <c:forEach var="resource" items="${resources}">
                <li><c:out value="${resource}" /></li>
            </c:forEach>
                <a href="index.jsp">Назад</a>
        </ul>
        
        
    </body>
</html>
