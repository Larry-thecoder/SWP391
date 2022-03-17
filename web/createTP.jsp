<%@page import="topicDescription.TopicDescriptionError"%>
<%@page import="account.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Topic Description Page</title>
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
            TopicDescriptionError error = (TopicDescriptionError) request.getAttribute("ERROR_TOPICDES");
            if (error == null) {
                error = new TopicDescriptionError();
            }
        %>
        <div class="container">
            <div class="container-form">
                <h1>Create Topic Description</h1>
                <hr>
                <form action="MainController" method="POST">

                    <div class="form-group">
                        <label>Description ID(*)</label>
                        <input type="text" class="form-control" name="descriptionID" required=""/>
                        <%= error.getTopicDescrID()%></br>
                    </div>

                    <div class="form-group">
                        <label>Approver ID(*)</label>
                        <input type="text" class="form-control" name="approverID" required=""/>
                        <%= error.getApproverID()%></br>                    
                    </div>

                    <div class="form-group">
                        <label>Details(*)</label>
                        <input type="text" class="form-control" name="details" required=""/>
                        <%= error.getDetails()%></br>                    
                    </div>


                    <div>
                        <label>Description Status(*)</label>
                        <input type="text" class="form-control" name="descriptionStatus" required=""/>
                        <%= error.getTopicDescrStatus()%></br>
                    </div>
                    <input type="submit" name="action" value="CreateTopicDescriptionController"/></br>
                    <input type="reset" value="Reset"/></br>
                </form>
            </div>
        </div>
    </body>
</html>