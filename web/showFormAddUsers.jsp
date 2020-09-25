<%-- 
    Document   : showFormAddUsers
    Created on : 14.09.2020, 21:03:14
    Author     : ban31
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<div class="container">
 <div class="row">
<div class="col-md-3"></div>
 <div class="col-md-offset-3 col-md-6">
    <form class="form-horizontal" action="createUsers" method="post">
        <span class="heading">Регистрация</span>
        <p>${info}</p>
        <div class="form-group">
        <input type="text" class="form-control" id="inputLogin" name="userlogin" placeholder="Логин: ">
        <i class="fa fa-user"></i>
        </div>
        <div class="form-group help">
        <input type="password" class="form-control" id="inputPassword" name="userpassword" placeholder="Password">
        <i class="fa fa-lock"></i>
        <a href="#" class="fa fa-question-circle"></a>
        </div>
        <div class="form-group">
        
        <button type="submit" class="btn btn-default">Зарегистрироваться</button>
        </div>
    </form>
 </div>

 </div><!-- /.row -->
</div><!-- /.container -->