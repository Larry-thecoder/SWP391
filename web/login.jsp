<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="css/loginpage.css">
    </head>
    <body>
        <div class="center">
            <h1>Login</h1>
            <form action="MainController" method="POST">
                <div class="txt_field">
                    <input type="text" name="accountID" placeholder="User ID"/>
                    <span></span>
                </div>
                <div class="txt_field">
                    <input type="password" name="password" placeholder="Password"/>
                    <span></span>
                </div>
                <!--                <div class="pass">Forgot Password?</div>-->
                <input type="submit" name="action" value="Login"/>
                <!--                <input type="reset" value="Reset"/>-->
                <p style="color: red;">${requestScope.ERROR}</p>
                <div class="signup_link">
                    Not a member? <a href="#">Sign Up</a>
                </div>
            </form>
        </div>
    </body>
</html>
