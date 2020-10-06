<%-- 
    Document   : editUsersForm
    Created on : Oct 6, 2020, 9:37:57 AM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="container">
 <div class="row">
<div class="col-md-3"></div>
 <div class="col-md-offset-3 col-md-6">
    <form class="form-horizontal" action="updateUserRoles" method="post">
        <span class="heading">Изменить данные пользователя</span>
        <p>${info}</p>
        <div class="form-group">
        <input value="${editUsers.id}" type="hidden" name="userId">
        <input value="${editUsers.userlogin}" type="text" class="form-control" id="inputLogin" name="userlogin" placeholder="Логин: ">
        <i class="fa fa-user"></i>
        </div>
        
        <div class="form-group help">
        <input value="" type="password" class="form-control" id="inputPassword" name="userpassword" placeholder="Password">
        <i class="fa fa-lock"></i>
        </div>
        
        <div class="form-group">
        <input value="${topRoleEditUser}" type="text" class="form-control" id="currentRole" name="currentRole" placeholder="текущая роль">
        <i class="fa fa-lock"></i>
        </div>
        
        <div class="form-group">
        <label for="newRole">Список ролей</label>
        <c:forEach var="role" items="${listRoles}">
            <select id="inputState" class="form-control" name="newRole" id="newRole">
                <c:if test="${role.name eq topRoleEditUser}">
                 <option value="${role.id}" selected>${role.name}</option>   
                </c:if>
                 <c:if test="${role.name ne topRoleEditUser}">
                 <option value="${role.id}">${role.name}</option>   
                </c:if>
            </c:forEach>
            </select>
         
        </div>
        
        
        <div class="form-group">
        <button type="submit" class="btn btn-default">Изменить</button>
        </div>
    </form>
 </div>

 </div><!-- /.row -->
</div><!-- /.cont