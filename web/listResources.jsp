<%-- 
    Document   : listResources
    Created on : Sep 15, 2020, 12:31:27 PM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

    <h1>Список ресурсов: </h1>
        
    <table class="table">
            <c:forEach var="resource" items="${listResources}">
                <tr>
                    <td>
                        <li>Имя: ${resource.name}</li>
                        <li>URL: ${resource.url}</li>
                        <li>Login: ${resource.login}</li>
                        <li>Password: ${resource.password}</li>
                    </td>
                    <td>
                        <a href="deleteResource?id=${resource.id}">Удалить</a>
                    </td>
                </tr>
            </c:forEach>
                <a href="index.jsp">Назад</a>
    </table>
        
        
        

