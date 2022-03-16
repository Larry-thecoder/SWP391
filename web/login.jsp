<%-- 
    Document   : login
    Created on : Jan 20, 2022, 2:32:49 PM
    Author     : Mr.Khuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link href="../myStyle.css" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
              crossorigin="anonymous">
        <link rel="shortcut icon" href="../favicon.ico"/>
    </head>
    <body>
        <!--        <form action="MainController" method="POST">
                    User ID<input type="text" name="accountID"/></br>
                    Password<input type="password" name="password"/></br>
                    <input type="submit" name="action" value="Login"/>
                    <input type="reset" value="Reset"/>
                </form>-->

        <div class="container">
            <div class="container-login">
                <h1>Login</h1>
                <form action="MainController" method="POST">
                    <div class="form-group">
                        <label>User ID</label>
                        <input type="text" class="form-control" name="accountID" placeholder="Enter user ID"/>
                    </div>
                    
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" class="form-control" name="password" placeholder="Enter password"/>
                    </div>
                    <!--                <div class="pass">Forgot Password?</div>-->
                    <input type="submit" name="action" value="Login"/>
                    <!--                <input type="reset" value="Reset"/>-->
                    <div class="signup_link">
                        Not a member? <a href="#">Sign Up</a>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
