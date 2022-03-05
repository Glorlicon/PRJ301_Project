
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
        <title>JSP Page</title>
    </head>
    <body>
        <style>
            input[type="username"]{
                border-bottom-left-radius: 0px;;
                border-bottom-right-radius: 0px;;
            }
            
            input[type="password"]{
                border-top-left-radius: 0px;;
                border-top-right-radius: 0px;;
                border-top:0px;;
            }
        </style>
        <div class="text-center mt-5">
        <form style="max-width:300px;margin:auto;"action="login" method="POST">
            <img class="mt-4 mb-4" src=""
            <h1 class="h3 mb-3 front-weight-normal"> Login </h1>
            <label for="username" class='sr-only'>Username</label>
            <input type="text" class='form-control' placeholder='Username' name="username" require autofocus>
            <label for="password" class='sr-only'>Password</label>
            <input type="password" class='form-control' placeholder='Password' name="password" require autofocus>
            <br>
            <div class='mt-1'>
                <input class='btn-lg btn-primary btn-block' type="submit" value="Login" />
            </div>
        </form>
        </div>
    </body>
</html>
