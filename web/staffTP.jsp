<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="topicDescription.TopicDescriptionDTO"%>
<%@page import="java.util.List"%>
<%@page import="account.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Envision</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" href="images/Envision.css" type="text/css" />
</head>
<body>
<div id="wrap">
  <div id="header">
      <h1 id="logo-text">Topic Descriptions</h1>
  </div>
  <div  id="menu">
  </div>
  <div id="content-wrap">
    <div id="sidebar">
      <h1>Search Box</h1>
      <form action="#" class="searchform">
        <p>
          <input name="search_query" class="textbox" type="text" />
          <input name="search" class="button" value="Search" type="submit" />
        </p>
      </form>
      <h1>Sidebar Menu</h1>
      <ul class="sidemenu">
          <c:url var="ViewTopicDescriptionsApprovedLink" value="MainController">
              <c:param name="action" value="ViewTopicDescriptionsApprovedController"></c:param>
          </c:url>
          <c:url var="createTopicDescriptionLink" value="MainController">
              <c:param name="action" value="CreateTopicDescriptionController"></c:param>
          </c:url>
          <li><a href="${ViewTopicDescriptionsApprovedLink}">View Topic Descriptions Approved</a></li>
          <li><a href="${createTopicDescriptionLink}">Create new Topic Description</a></li>
          <li><a href="staffTopic.jsp">Topics</a></li>
          <li><a href="staff.jsp">Projects</a></li>
      </ul>
    </div>
    <div id="main"> <a name="TemplateInfo"></a>
        <h1>Topic Descriptions Approved</h1>
        <c:if test="${requestScope.DOWNLOAD_FILES != null}">
            <c:if test="${not empty requestScope.DOWNLOAD_FILES}">
                <c:forEach var="entry" varStatus="counter" items="${requestScope.DOWNLOAD_FILES}">
                    <c:url var="DownloadLink" value="MainController">
                        <c:param name="action" value="DownloadFileController"></c:param>
                        <c:param name="file" value="${entry.key}"></c:param>
                    </c:url>
                    <p><a href="${DownloadLink}"">${entry.value}</a></p><br/>
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
