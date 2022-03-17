<%-- 
    Document   : loginprocess
    Created on : Mar 17, 2022, 1:18:55 PM
    Author     : BK
--%>

<%@page import="Entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
        <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link href="../css/styles.css" rel="stylesheet" />


        <script src="../js/scripts.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-dark bg-primary">
            <a class="navbar-brand" href="#">
                <img src="img/SRS2.png" width="30" height="30" class="d-inline-block align-top" alt="">
                Weeb Cooperation
            </a>
        </nav>
        <div class="container" style="padding-top: 20px">
            <div class="row">             
                <c:if test="${not empty account}">
                    <div class="col" style="text-align: center">Login Successfully!</div>
                </div>
                    <div class="row"> 
                        <div class="col" style="text-align: center">Redirecting shortly</div>
                    </div>
                    <% response.setHeader("Refresh", "5;url=home.jsp");%>
                </c:if>
                <c:if test="${empty account}">
                    <div class="col" style="text-align: center">Login Failed!</div>
                    </div>
                    <% response.setHeader("Refresh", "5;url=login.jsp");%>
                    <div class="row"> 
                    <div class="col" style="text-align: center">Redirecting shortly</div>
                </div>
                </c:if>
                
            </div>
        </div>

    </body>
</html>