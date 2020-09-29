<%-- 
    Document   : listResources
    Created on : Sep 15, 2020, 12:31:27 PM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

    
        


<form action="showResource" method="POST" id="showResource">
    <div class="jumbotron bg-white">
        <h4 class="w-100 text-center">Список ресурсов</h4>
        <div class="form-group w-50 mx-auto">
            <select class="custom-select" name="idResource" id="idResource">
                <option selected="">Откройте меню</option>
                <c:forEach var="resource" items="${listResources}">
                    <option value="${resource.id}">${resource.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group w-50 mx-auto">
            <button type="submit" class="btn btn-primary">Показать</button>
        </div>
        
    </div>
</form>
        
        

