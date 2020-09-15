<%-- 
    Document   : showFormLogin
    Created on : Sep 15, 2020, 1:20:50 PM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Вход в систему</title>
        <h3>Введите логин и пароль: </h3>
    </head>
    <body>
        <form action="login" method="post">
            <label>Логин: </label>
            <input type="text" name="login"><br>
            <label>Пароль: </label>
            <input type="text" name="password"><br>
            <input type="submit" value="войти"><br>
            <br>Нет логина? <a href="showFormAddUsers">Зарегистрироваться</a>
        </form>
    </body>
</html>
