<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="topicDescription.TopicDescriptionError"%>
<%@page import="account.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Topic Description Details Page</title>

        <link href="css/form.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/w3.css">

        <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
        <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
        <script>
            function enterReason() {
              let reason = prompt("Please enter a reason for rejecting document.", "");
              if (reason !== null) {
                document.getElementById("reason").value = reason;
              }
            }
        </script>
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
            <div class="row" style="font-family: Courier; font-size: xx-large; font-weight: bolder; color: #ff6600; text-transform: uppercase; text-align: center;">FPT University</div>
            <div class="row" style="font-weight: bolder; font-size: large; color: #000000; text-transform: uppercase; text-align: center;">Graduation Thesis Register</div>
            <c:if test="${requestScope.LINES != null}">
                <c:if test="${not empty requestScope.LINES}">
                    <c:forEach var="line" varStatus="counter" items="${requestScope.LINES}">
                        <div class="row" style="color: #000000;">${line}</div>
                    </c:forEach>
                </c:if>
            </c:if>
            <div class="row" style="color: #000000;">1. Register information for supervisors (if any)</div>
            <c:if test="${requestScope.SUPERVISORS != null}">
                <c:if test="${not empty requestScope.SUPERVISORS}">
                    <table border="1" class="table" style="color: #000000;">
                        <thead class="thead-primary">
                            <tr>
                                <th></th>
                                <th>Full Name</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>Title</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="supervisor" varStatus="counter" items="${requestScope.SUPERVISORS}">
                                <tr>
                                    <td>Supervisor ${counter.count}</td>
                                    <td>${supervisor.fullName}</td>
                                    <td>${supervisor.phone}</td>
                                    <td>${supervisor.email}</td>
                                    <td>${supervisor.title}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </c:if>
            <div class="row" style="color: #000000;">2. Register information for students (if any)</div>
            <c:if test="${requestScope.SUPERVISORS != null}">
                <c:if test="${not empty requestScope.SUPERVISORS}">
                    <table border="1" class="table" style="color: #000000;">
                        <thead class="thead-primary">
                            <tr>
                                <th></th>
                                <th>Full Name</th>
                                <th>Student Code</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>Role in Group</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="student" varStatus="counter" items="${requestScope.STUDENTS}">
                                <tr>
                                    <td>Student ${counter.count}</td>
                                    <td>${student.fullName}</td>
                                    <td>${student.studentCode}</td>
                                    <td>${student.phone}</td>
                                    <td>${student.email}</td>
                                    <td>${student.roleInGroup}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </c:if>
            <c:if test="${requestScope.LINES2 != null}">
                <c:if test="${not empty requestScope.LINES2}">
                    <c:forEach var="line" varStatus="counter" items="${requestScope.LINES2}">
                        <div class="row" style="color: #000000;">${line}</div>
                    </c:forEach>
                </c:if>
            </c:if>
            <br/><br/><br/><br/>
            <c:if test="${requestScope.SUPERVISORS != null}">
                <c:if test="${not empty requestScope.SUPERVISORS}">
                    <c:forEach var="supervisor" varStatus="counter" items="${requestScope.SUPERVISORS}">
                        <div class="row" style="color: #000000;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${supervisor.fullName}</div>
                    </c:forEach>
                </c:if>
            </c:if>
            <br/><br/><br/><br/>
            <form action="MainController" method="POST">
                <div class="row col-half">
                    <c:url var="approveLink" value="MainController">
                        <c:param name="action" value="Approve"></c:param>
                        <c:param name="topicDesID" value="${param.id}"></c:param>
                        <c:param name="userID" value="${sessionScope.LOGIN_AC.accountID}"></c:param>
                    </c:url>
                    <a href="${approveLink}" class="w3-button w3-block w3-section w3-green w3-ripple w3-padding">Approve</a>
                </div>
                <div class="row col-half">
                    <input onclick="enterReason()" type="submit" name="action" value="Reject" class="w3-button w3-block w3-section w3-red w3-ripple w3-padding"/>
                    <input type="hidden" id="reason" name="reason" value="" />
                    <input type="hidden" name="topicDesID" value="${param.id}" />
                    <input type="hidden" name="userID" value="${sessionScope.LOGIN_AC.accountID}" />
                </div>
            </form>
        </div>
    </body>
</html>
