<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="account.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Approver Page</title>
        <link rel="stylesheet" href="images/Envision.css" type="text/css" />
    </head>
    <body>
        <div id="wrap">
            <div id="header">
                <h1 id="logo-text">Topic Descriptions</h1>
            </div>
            <div  id="menu">
                <ul>
                    <c:url var="LogoutLink" value="MainController">
                        <c:param name="action" value="Logout"></c:param>
                    </c:url>
                    <li><a href="${LogoutLink}">Log out</a></li>
                </ul>
            </div>
            <div id="content-wrap">
                <div id="sidebar">
                    <h1>Sidebar Menu</h1>
                    <ul class="sidemenu">
                        <c:url var="ViewTopicDescriptionsReviewLink" value="MainController">
                            <c:param name="action" value="ViewTopicDescriptionsReviewController"></c:param>
                        </c:url>
                        <li><a href="${ViewTopicDescriptionsReviewLink}">View Topic Descriptions</a></li>
                    </ul>
                </div>
                <div id="main"> <a name="TemplateInfo"></a>
                    <h1>Topic Descriptions</h1>
                    <c:if test="${requestScope.FILES != null}">
                        <c:if test="${not empty requestScope.FILES}">
                            <table border="1" class="table">
                                <thead>
                                    <tr>
                                        <th>Topic</th>
                                        <th>Selection Criteria</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="row" varStatus="counter" items="${requestScope.FILES}">
                                        <c:url var="TopicDesDetailsLink" value="MainController">
                                            <c:param name="action" value="ViewTopicDesDetailsController"></c:param>
                                            <c:param name="id" value="${row.topicDescrID}"></c:param>
                                        </c:url>
                                        <c:url var="SelectionCriteriaLink" value="MainController">
                                            <c:param name="action" value="ViewSelectionCriteriaController"></c:param>
                                            <c:param name="profession" value="${row.profession}"></c:param>
                                        </c:url>
                                        <tr>
                                            <td><a href="${TopicDesDetailsLink}">${row.fileName}</a></td>
                                            <td><a href="${SelectionCriteriaLink}">${counter.count}</a></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </c:if>
                </div>
            </div>
            <div id="footer">
                <p> &copy; 2006 <strong>Your Company</strong> | Design by: <a href="http://www.styleshout.com/">styleshout</a></p>
            </div>
        </div>
    </body>
</html>
