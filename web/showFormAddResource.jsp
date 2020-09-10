<%-- 
    Document   : showFormAddResource
    Created on : Sep 10, 2020, 2:51:07 PM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить ресурс</title>
    </head>
    <body>
        <form action="createResource" method="post">
            <label>Имя ресурса: </label>
            <input type="text" name="name"><br>
            <label>Адрес ресурса: </label>
            <input type="text" name="url"><br>
            <label>Логин ресурса: </label>
            <input type="text" name="login"><br>
            <label>Пароль ресурса: </label>
            <input type="text" name="password"><br>
            <input type="submit" value="создать">
        </form>
    </body>
</html>
