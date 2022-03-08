<%-- 
    Document   : createTP
    Created on : Mar 3, 2022, 9:10:46 PM
    Author     : Mr.Khuong
--%>

<%@page import="topicDescription.TopicDescriptionError"%>
<%@page import="account.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Topic Description Page</title>
    </head>
    <body>
        <%
            AccountDTO loginAc = (AccountDTO) session.getAttribute("LOGIN_AC");
            if (loginAc == null || !"Staff".equals(loginAc.getRole())) {
                response.sendRedirect("login.jsp");
                return;
            }
            TopicDescriptionError error = (TopicDescriptionError) request.getAttribute("ERROR_TOPICDES");
            if (error == null) {
                error = new TopicDescriptionError();
            }
        %>
        <form action="MainController" method="POST">
            Description ID(*)<input type="text" name="descriptionID" required=""/>
            <%= error.getTopicDescrID()%></br>
            Approver ID(*)<input type="text" name="approverID" required=""/>
            <%= error.getApproverID()%></br>
            Details(*)<input type="text" name="details" required=""/>
            <%= error.getDetails()%></br>
            Description Status(*)<input type="text" name="descriptionStatus" required=""/>
            <%= error.getTopicDescrStatus()%></br>
            <input type="submit" name="action" value="CreateTopicDescriptionController"/></br>
            <input type="reset" value="Reset"/></br>
    </body>
</html>
