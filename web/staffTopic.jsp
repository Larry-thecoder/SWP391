<%-- 
    Document   : staffTopic
    Created on : Mar 4, 2022, 7:55:41 AM
    Author     : Mr.Khuong
--%>

<%@page import="topic.TopicDTO"%>
<%@page import="account.AccountDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Topic Page</title>
    </head>
    <body>
        <%
            AccountDTO loginAc = (AccountDTO) session.getAttribute("LOGIN_AC");
            if (loginAc == null || !"Staff".equals(loginAc.getRole())) {
                response.sendRedirect("login.jsp");
                return;
            }
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        <h1>Hello Staff! This is Topic Page</h1>
        <form action="MainController">
            <input type="submit" name="action" value="Logout"/>
        </form>
        <form action="MainController">
            Search<input type="text" name="search" value="<%= search%>" />
            <input type="submit" name="action" value="ViewTopicController"/>
        </form>
        <%
            List<TopicDTO> listT = (List<TopicDTO>) request.getAttribute("LIST_TOPIC_STAFF");
            if (listT != null) {
                if (!listT.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>NO</th>
                    <th>Subject ID</th>
                    <th>Subject Name</th>
                    <th>Description ID</th>
                    <th>Lecture ID</th>
                    <th>Delete</th>
                    <th>Edit</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (TopicDTO T : listT) {
                %> 
            <form action="MainController">
                <tr>
                    <td><%= count++%></td>
                    <td>
                        <%= T.getTopicID() %>
                    </td>
                    <td>
                        <input type="text" name="subjectName" value="<%= T.getTopicName()%>"/>
                    </td>
                    <td>
                        <input type="text" name="descriptionID" value="<%= T.getDescriptionID()%>"/>
                    </td>
                    <td>
                        <input type="text" name="lectureID" value="<%= T.getLecturerID()%>"/>
                    </td>
                    <td>
                        <a href="MainController?action=DeleteTopicController&subjectID=<%= T.getTopicID()%>&search=<%= search%>" onclick="return confirm('Are you sure?')" >Delete</a>
                    </td>
                    <td>
                        <input type="submit" name="action" value="UpdateTopicController"/>
                        <input type="hidden" name="subjectID" value="<%= T.getTopicID()%>" required=""/>
                        <input type="hidden" name="search" value="<%= search%>" required=""/>
                    </td>
                </tr>
            </form>
            <%
                }
            %>    
        </tbody>
    </table>
        
    <%
            }
        }
    %>
    <%
        String error = (String) request.getAttribute("ERROR");
        if (error == null) {
            error = "";
        }
    %>
    <%=error%>
    <a href="MainController?action=CreateTopicController">Create new Topic(Must have a Topic Description ID first!!!) </a></br>
    <a href="staffTP.jsp">Topic Description Page</a></br>
    <a href="staff.jsp">Staff Product Page</a></br>
    </body>
</html>
