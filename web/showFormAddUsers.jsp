<%-- 
    Document   : showFormAddUsers
    Created on : 14.09.2020, 21:03:14
    Author     : ban31
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить пользователя</title>
    </head>
    <body>
        <form action="createUsers" method="post">
            <label>Логин: </label>
            <input type="text" name="userlogin"><br>
            <label>Пароль: </label>
            <input type="text" name="userpassword"><br>
            <input type="submit" value="создать">
        </form>
    </body>
</html>
