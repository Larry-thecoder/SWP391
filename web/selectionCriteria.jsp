<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="topicDescription.TopicDescriptionError"%>
<%@page import="account.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Topic Description Page</title>

        <link href="css/form.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/w3.css">

        <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
        <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
    </head>
    <body>
        <%
            AccountDTO loginAc = (AccountDTO) session.getAttribute("LOGIN_AC");
            if (loginAc == null || !"Approver".equals(loginAc.getRole())) {
                response.sendRedirect("login.jsp");
                return;
            }
            TopicDescriptionError error = (TopicDescriptionError) request.getAttribute("ERROR_TOPICDES");
            if (error == null) {
                error = new TopicDescriptionError();
            }
        %>
        <c:url var="ViewTopicDescriptionsReviewLink" value="MainController">
            <c:param name="action" value="ViewTopicDescriptionsReviewController"></c:param>
        </c:url>
        <a href="${ViewTopicDescriptionsReviewLink}">&#8592; Back to Topic Descriptions</a>
        <div class="container">
            <div class="row" style="font-weight: bolder; font-size: large; color: #000000; text-transform: uppercase; text-align: center;">Grading Breakdown</div>
            <div class="row" style="color: #000000;">${requestScope.GRADING}</div>
            <div class="row" style="font-weight: bolder; font-size: large; color: #000000; text-transform: uppercase; text-align: center;">Course Objectives and Project Components</div>
            <div class="row" style="color: #000000;">${requestScope.OBJECTIVES}</div>
        </div>
    </body>
</html>
