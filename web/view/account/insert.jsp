<%-- 
    Document   : insert
    Created on : Mar 12, 2022, 3:55:13 PM
    Author     : BK
--%>

<%@page import="Entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Weeb's Corp Invoice Management System</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
        <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <script src="..js/scripts.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-dark bg-primary">
            <a class="navbar-brand" href="#">
                <img src="../img/SRS2.png" width="30" height="30" class="d-inline-block align-top" alt="">
                Adding Company
            </a>
            <a href="../search" class="btn btn-success my-2 my-sm-0" data-toggle="modal"> <span>Return</span></a>
        </nav>

        <!-- copy of input fields group -->
        <div class="container" style="padding-top: 20px">
            <div class="row">
                <form style="max-width:300px;margin:auto;"action="insert" method="POST"> 
                    <div class='mt-1'>
                        <input class='btn-lg btn-primary btn-block' type="submit" value="Add" />
                    </div>
                    <div class="form-group fieldGroup" style="padding-top:50px">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputGroupSelect01">Username</label>
                            </div>
                            <label for="companyid" class='sr-only'>Company ID</label>
                            <input type="text" class='form-control' placeholder='Company ID' name="companyid" require autofocus required>
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputGroupSelect02">Password</label>
                            </div>
                            <label for="companyname" class='sr-only'>Company Name</label>
                            <input type="text" class='form-control' placeholder='Company Name' name="companyname" require autofocus required>
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputGroupSelect01">Permission</label>
                            </div>
                            <select class="custom-select" id="inputGroupSelect01" name="product" required>

                                <option selected>Choose...</option>
                                <option value="admin">Admin</option>
                                <option value="moderator">Moderator</option>
                                <option value="user">User</option>
                            </select>
                        </div>
                        <div class="input-group-addon"> 
                            <a href="javascript:void(0)" class="btn btn-success addMore"><span class="glyphicon glyphicon glyphicon-plus" aria-hidden="true"></span> Add</a>
                        </div>

                    </div>
                </form>
            </div>
        </div>

        <form style="max-width:300px;margin:auto;"action="test" method="POST">
            <div class="form-group fieldGroupCopy" style="display: none;">
                <div class="input-group mb-3">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="inputGroupSelect01">Username</label>
                        </div>
                        <label for="companyid" class='sr-only'>Company ID</label>
                        <input type="text" class='form-control' placeholder='Company ID' name="companyid" require autofocus required>
                    </div>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="inputGroupSelect02">Password</label>
                        </div>
                        <label for="companyname" class='sr-only'>Company Name</label>
                        <input type="text" class='form-control' placeholder='Company Name' name="companyname" require autofocus required>
                    </div>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="inputGroupSelect01">Permission</label>
                        </div>
                        <select class="custom-select" id="inputGroupSelect01" name="product" required>

                            <option selected>Choose...</option>
                            <option value="admin">Admin</option>
                            <option value="moderator">Moderator</option>
                            <option value="user">User</option>
                        </select>
                    </div>
                    <div class="input-group-addon"> 
                        <a href="javascript:void(0)" class="btn btn-danger remove"><span class="glyphicon glyphicon glyphicon-remove" aria-hidden="true"></span>Remove</a>
                    </div>
                </div>
        </form>
        <script>

            $(document).ready(function () {
                //group add limit
                var maxGroup = 10;

                //add more fields group
                $(".addMore").click(function () {
                    if ($('body').find('.fieldGroup').length < maxGroup) {
                        var fieldHTML = '<div class="form-group fieldGroup">' + $(".fieldGroupCopy").html() + '</div>';
                        $('body').find('.fieldGroup:last').after(fieldHTML);
                    } else {
                        alert('Maximum ' + maxGroup + ' groups are allowed.');
                    }
                });

                //remove fields group
                $("body").on("click", ".remove", function () {
                    $(this).parents(".fieldGroup").remove();
                });
            });
        </script>
    </body>
</html>


