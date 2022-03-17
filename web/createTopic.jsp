<%-- 
    Document   : createTopic
    Created on : Mar 4, 2022, 11:02:35 AM
    Author     : Mr.Khuong
--%>

<%@page import="topic.TopicError"%>
<%@page import="account.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Topic Page</title>
    </head>
    <body>
        <%
            AccountDTO loginAc = (AccountDTO) session.getAttribute("LOGIN_AC");
            if (loginAc == null || !"Staff".equals(loginAc.getRole())) {
                response.sendRedirect("login.jsp");
                return;
            }
            TopicError error = (TopicError) request.getAttribute("ERROR_TOPIC");
            if (error == null) {
                error = new TopicError();
            }
        %>
        <form action="MainController" method="POST">
            Topic ID(*)<input type="text" name="subjectID" required=""/>
            <%= error.getTopicID()%></br>
            Topic Name(*)<input type="text" name="subjectName" required=""/>
            <%= error.getTopicName()%></br>
            Lecture ID(*)<input type="text" name="lectureID" required=""/>
            <%= error.getLecturerID()%></br>
            <input type="submit" name="action" value="CreateTopicController"/></br>
            <input type="reset" value="Reset"/></br>
    </body>
</html>
