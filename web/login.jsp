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
        <link rel="stylesheet" href="Style/loginpage.css">
    </head>
    <body>
        <!--        <form action="MainController" method="POST">
                    User ID<input type="text" name="accountID"/></br>
                    Password<input type="password" name="password"/></br>
                    <input type="submit" name="action" value="Login"/>
                    <input type="reset" value="Reset"/>
                </form>-->

        <div class="center">
            <h1>Login</h1>
            <form action="MainController" method="POST">
                <div class="txt_field">
                    <input type="text" name="accountID" placeholder="User ID Here"/>
                    <span></span>
                    <label>User ID</label>
                </div>
                <div class="txt_field">
                    <input type="password" name="password" placeholder="Password Here"/>
                    <span></span>
                    <label>Password</label>
                </div>
                <!--                <div class="pass">Forgot Password?</div>-->
                <input type="submit" name="action" value="Login"/>
                <!--                <input type="reset" value="Reset"/>-->
                <div class="signup_link">
                    Not a member? <a href="#">Sign Up</a>
                </div>
            </form>
        </div>


    </body>
</html>
