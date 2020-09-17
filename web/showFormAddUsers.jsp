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
        <form action="createUsers" method="post" style="width:15%;padding-left:2%;">
            <div class="form-group">
            <label>Логин: </label>
            <input type="text" name="userlogin" class="form-control"><br>
            <label>Пароль: </label>
            <input type="text" name="userpassword" class="form-control"><br>
            <input type="submit" value="создать" class="btn btn-primary">
            </div>
        </form>
    </body>
</html>
