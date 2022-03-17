<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="topicDescription.TopicDescriptionDTO"%>
<%@page import="java.util.List"%>
<%@page import="account.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <title>Topic Descriptions</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" href="images/Envision.css" type="text/css" />
    </head>
    <body>
        <%
            AccountDTO loginAc = (AccountDTO) session.getAttribute("LOGIN_AC");
            if (loginAc == null || !"Lecturer".equals(loginAc.getRole())) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
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
            <div id="content-wrap" style="height: 500px;">
                <div id="sidebar">
                    <h1>Sidebar Menu</h1>
                    <ul class="sidemenu">
                        <c:url var="createTopicDescriptionLink" value="MainController">
                            <c:param name="action" value="LoadFormCreateTopicDesController"></c:param>
                        </c:url>
                        <c:url var="viewDocumentsSubmittedLink" value="MainController">
                            <c:param name="action" value="ViewTopicDescriptionController"></c:param>
                        </c:url>
                        <c:url var="viewDocumentsReturnedLink" value="MainController">
                            <c:param name="action" value="ViewDocumentsReturnedController"></c:param>
                        </c:url>
                        <li><a href="${createTopicDescriptionLink}">Create new Topic Description</a></li>
                        <li><a href="${viewDocumentsSubmittedLink}">View documents submitted for approval</a></li>
                        <li><a href="${viewDocumentsReturnedLink}">View documents returned for revision</a></li>
                    </ul>
                </div>
                <div id="main"> <a name="TemplateInfo"></a>
                    <c:if test="${requestScope.PENDING_FILES != null}">
                        <h1>Topic Descriptions submitted for approval</h1>
                        <c:if test="${not empty requestScope.PENDING_FILES}">
                            <c:forEach var="entry" varStatus="counter" items="${requestScope.PENDING_FILES}">
                                <c:url var="TopicDesDetailsLink" value="MainController">
                                    <c:param name="action" value="ViewReturnedDetailsController"></c:param>
                                    <c:param name="id" value="${entry.key}"></c:param>
                                </c:url>
                                <p><a href="${TopicDesDetailsLink}">${entry.value}</a></p>
                                </c:forEach>
                            </c:if>
                        </c:if>
                        <c:if test="${requestScope.FILES != null}">
                        <h1>Topic Descriptions Returned for Revision</h1>
                        <c:if test="${not empty requestScope.FILES}">
                            <c:forEach var="row" varStatus="counter" items="${requestScope.FILES}">
                                <c:url var="TopicDesDetailsLink" value="MainController">
                                    <c:param name="action" value="ViewReturnedDetailsController"></c:param>
                                    <c:param name="id" value="${row.topicDescrID}"></c:param>
                                </c:url>
                                <c:url var="viewCommentLink" value="MainController">
                                    <c:param name="action" value="ViewCommentsController"></c:param>
                                    <c:param name="id" value="${row.topicDescrID}"></c:param>
                                </c:url>
                                <p><a href="${TopicDesDetailsLink}">${row.fileName}</a></p>
                                <p class="post-footer"><a href="${viewCommentLink}" class="comments">Comments</a></p>
                                <c:if test="${not empty requestScope.COMMENTS}">
                                    <c:if test="${requestScope.COMMENTS != null}">
                                        <ul>
                                            <c:forEach var="comment" varStatus="counter" items="${requestScope.COMMENTS}">
                                                <li>${comment}</li>
                                                </c:forEach>
                                        </ul>
                                    </c:if>
                                </c:if>
                            </c:forEach>
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
