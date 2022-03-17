<%-- 
    Document   : update
    Created on : Mar 16, 2022, 1:41:17 PM
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

        <script src="../js/scripts.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-dark bg-primary">
            <a class="navbar-brand" href="#">
                <img src="../img/SRS2.png" width="30" height="30" class="d-inline-block align-top" alt="">
                Updating Product
            </a>
            <a href="search" class="btn btn-success my-2 my-sm-0" data-toggle="modal"><i class="material-icons arrow_back">&#xe5c4;</i> <span>Return</span></a>
        </nav>

        <!-- copy of input fields group -->
        <div class="container" style="padding-top: 20px">
            <div class="row">
                <form style="max-width:300px;margin:auto;"action="insert" method="POST"> 

                    <div class="form-group fieldGroup" style="padding-top:50px">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputGroupSelect01">Company</label>
                            </div>
                            <input type="hidden" name="vid" value="${requestScope.vid}"/>
                            <select class="custom-select" id="inputGroupSelect01" name="company" required>
                                <option selected>Choose...</option>
                                <c:forEach items="${requestScope.company}" var="c">
                                    <option
                                        ${(c.companyid==requestScope.cid)?"selected=\"selected\"":""}
                                        value="${c.companyid}">${c.company}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputGroupSelect01">Product</label>
                            </div>
                            <select class="custom-select" id="inputGroupSelect01" name="product" required>

                                <option selected>Choose...</option>
                                <c:forEach items="${requestScope.product}" var="product">
                                    <option
                                        ${(product.productid==requestScope.pid)?"selected=\"selected\"":""}
                                        value="${product.productid}">${product.productname}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputGroupSelect01">Amount</label>
                            </div>
                            <label for="amount" class='sr-only'>Amount</label>
                            <input type="number" class='form-control' placeholder='Amount' name="Amount" value="${requestScope.a}" require autofocus>
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputGroupSelect02">Cost</label>
                            </div>
                            <label for="cost" class='sr-only'>Cost</label>
                            <input type="number" class='form-control' placeholder='Cost' name="Cost" value="${requestScope.c}"require autofocus>
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputGroupSelect03">Date</label>
                            </div>
                            <label for="date" class='sr-only'>Date</label>
                            <input type="date" class='form-control' placeholder='Date' name="Date" value="${requestScope.idate}" require autofocus>
                        </div>
                        <div class='mt-1'>
                            <input class='btn-lg btn-primary btn-block' type="submit" value="Update" />
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>

