<%-- 
    Document   : showFormLogin
    Created on : Sep 15, 2020, 1:20:50 PM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    
    
   

<div class="container">
 <div class="row">
<div class="col-md-3"></div>
 <div class="col-md-offset-3 col-md-6">
    <form class="form-horizontal" action="login" method="post">
        <span class="heading">Авторизация</span>
        <p>${info}</p>
        <div class="form-group">
        <input type="text" class="form-control" id="inputLogin" name="login" placeholder="Логин: ">
        <i class="fa fa-user"></i>
        </div>
        <div class="form-group help">
        <input type="password" class="form-control" id="inputPassword" name="password" placeholder="Password">
        <i class="fa fa-lock"></i>
        <a href="#" class="fa fa-question-circle"></a>
        </div>
        <div class="form-group">
        <span class="text">Нет логина? <a href="showFormAddUsers">Зарегистрироваться</a></span>
        <button type="submit" class="btn btn-default">Войти</button>
        </div>
    </form>
 </div>

 </div><!-- /.row -->
</div><!-- /.container -->