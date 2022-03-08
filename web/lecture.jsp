<%-- 
    Document   : lecture
    Created on : Jan 20, 2022, 2:33:09 PM
    Author     : Mr.Khuong
--%>

<%@page import="account.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lecture Page</title>
    </head>
    <body>
        <%
            AccountDTO loginAc = (AccountDTO) session.getAttribute("LOGIN_AC");
            if (loginAc == null || !"Lecture".equals(loginAc.getRole())) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h1>Hello Lecture!</h1>
        <form action="MainController">
            <input type="submit" name="action" value="Logout"/>
        </form>
        
    </body>
</html>
