<%-- 
    Document   : showFormAddResource
    Created on : Sep 10, 2020, 2:51:07 PM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

        <form action="createResource" method="post">
            
            <div class="form-group" style="width:15%;padding-left:2%;">
            <label>Имя ресурса: </label>
            <input type="text" name="name" class="form-control"><br>
            <label>Адрес ресурса: </label>
            <input type="text" name="url" class="form-control"><br>
            <label>Логин ресурса: </label>
            <input type="text" name="login" class="form-control"><br>
            <label>Пароль ресурса: </label>
            <input type="text" name="password" class="form-control"><br>
            <input type="submit" value="создать" class="btn btn-primary">
            </div>
        </form>

