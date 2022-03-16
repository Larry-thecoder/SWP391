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
        <link href="../myStyle.css" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
              crossorigin="anonymous">
        <link rel="shortcut icon" href="../favicon.ico"/>
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
        <div class="container">
            <div class="container-form">
                <h1>Create Topic</h1>
                <hr>
                <form action="MainController" method="POST">
                    <div class="form-group">
                        <label>Topic ID(*)</label>
                        <input type="text" class="form-control" name="subjectID" required=""/>
                        <%= error.getTopicID()%></br>
                    </div>
                    <div class="form-group">
                        <label>Topic Name(*)</label>
                        <input type="text" class="form-control" name="subjectName" required=""/>
                        <%= error.getTopicName()%></br>
                    </div>
                    <div class="form-group">
                        <label>Description ID(*)</label>
                        <input type="text" class="form-control" name="descriptionID" required=""/>
                        <%= error.getDescriptionID()%></br>
                    </div>
                    <div class="form-group">
                        <label>Lecture ID(*)</label>
                        <input type="text" class="form-control" name="lectureID" required=""/>
                        <%= error.getLecturerID()%></br>
                    </div>
                    <input type="submit" name="action" value="CreateTopic"/></br>
                    <input type="reset" value="Reset"/></br>
                </form>
            </div>
        </div>
    </body>
</html>
