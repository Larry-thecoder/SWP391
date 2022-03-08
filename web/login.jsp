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
    </head>
    <body>
        <form action="MainController" method="POST">
            User ID<input type="text" name="accountID"/></br>
            Password<input type="password" name="password"/></br>
            <input type="submit" name="action" value="Login"/>
            <input type="reset" value="Reset"/>
        </form>
    </body>
</html>
