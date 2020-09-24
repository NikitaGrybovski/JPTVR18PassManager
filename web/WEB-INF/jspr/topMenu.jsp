
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${loginOn ne null && loginOn eq true}">
    <p> <a href="showFormAddResource">Добавить новый ресуорс</a>  /
        <a href="listResources">Список ресурсов</a>  /
        <a href="showFormAddUsers">Добавить пользователя</a>  /
        <a href="logout">Выйти</a>
    </p>
</c:if>
    
    <c:if test="${loginOn eq null or loginOn eq false}">
    <p> <a href="showFormLogin">Войти в систему</a><br>
    </p>
</c:if>
    

    

