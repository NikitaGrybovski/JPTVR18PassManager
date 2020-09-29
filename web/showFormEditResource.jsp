<%-- 
    Document   : showFormAddResource
    Created on : Sep 10, 2020, 2:51:07 PM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>




<div class="container">
 <div class="row">
     <div class="col-md-3"></div>
    <div class="col-md-offset-3 col-md-6">
        <form class="form-horizontal" action="showEditResource" method="post">
            <span class="heading">Изменить ресурс</span>
            <div class="form-group">
            <input type="text" class="form-control" id="inputName" name="name" placeholder="Имя ресурса:" >
            <i class="fab fa-adn"></i>
            
            </div>
            <div class="form-group help">
            <input type="text" class="form-control" id="inputUrl" name="url" placeholder="Адрес ресурса:">
            <i class="fa fa-id-badge"></i>
            
            </div>
            <div class="form-group help">
            <input type="text" class="form-control" id="inputLogin" name="login" placeholder="Логин ресурса:">
            <i class="fa fa-user"></i>
            
            </div>
            <div class="form-group help">
            <input type="text" class="form-control" id="inputPassword" name="password" placeholder="Пароль ресурса:">
            <i class="fa fa-lock"></i>
            
            </div>
            <div class="form-group">
          
            <button type="submit" class="btn btn-default">Создать</button>
            </div>
        </form>
    </div>

 </div><!-- /.row -->
</div><!-- /.container -->