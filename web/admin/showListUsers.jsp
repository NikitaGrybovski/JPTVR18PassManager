<%-- 
    Document   : showListUsers
    Created on : Oct 2, 2020, 10:28:17 AM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<form action="" method="POST" id="showResource">
    <div class="jumbotron bg-white">
        <h4 class="w-100 text-center">Список пользователей</h4>
        <div class="form-group w-50 mx-auto">
        <c:forEach var="entry" items="${usersMap}" varStatus="status">
           
            <p>${status.index + 1}. ${entry.key.userlogin} роль: ${entry.value} <a href="editUserRoles?usersId=${entry.key.id}"</p>
            <a href="editUserRoles?userId=${entry.key.id}" >Редактировать </a>
        </c:forEach>
        </div>
        <div class="form-group w-50 mx-auto">
            
        </div>
        
    </div>
</form>
