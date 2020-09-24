<%-- 
    Document   : showFormAddResource
    Created on : Sep 10, 2020, 2:51:07 PM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="container">
    <div class="row">
        <div class="col-md-12">
        <form action="createResource" method="post" class="form">
            
            <div class="form__group">
            <label>Имя ресурса: </label>
            <input type="text" name="name" class="form__input"><br>
            <label>Адрес ресурса: </label>
            <input type="text" name="url" class="form__input"><br>
            <label>Логин ресурса: </label>
            <input type="text" name="login" class="form__input"><br>
            <label>Пароль ресурса: </label>
            <input type="text" name="password" class="form__input"><br>
            <input type="submit" value="создать" class="btn">
            </div>
        </form>
        </div>
    </div>
</div>
