<%-- 
    Document   : staffTP
    Created on : Mar 3, 2022, 11:52:04 PM
    Author     : Mr.Khuong
--%>

<%@page import="topicDescription.TopicDescriptionDTO"%>
<%@page import="java.util.List"%>
<%@page import="account.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Topic Description Page</title>
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
        <h1>Hello Staff! This is Topic Description Page</h1>
        <form action="MainController">
            <input type="submit" name="action" value="Logout"/>
        </form>
        <form action="MainController">
            Search<input type="text" name="search" value="<%= search%>" />
            <input type="submit" name="action" value="ViewTopicDescriptionController"/>
        </form>
        <%
            List<TopicDescriptionDTO> listTD = (List<TopicDescriptionDTO>) request.getAttribute("LIST_TD_STAFF");
            if (listTD != null) {
                if (!listTD.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>NO</th>
                    <th>Description ID</th>
                    <th>Approver ID</th>
                    <th>Details</th>
                    <th>Description Status</th>
                    <th>Delete</th>
                    <th>Edit</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (TopicDescriptionDTO TD : listTD) {
                %> 
            <form action="MainController">
                <tr>
                    <td><%= count++%></td>
                    <td>
                        <%= TD.getTopicDescrID() %>
                    </td>
                    <td>
                        <input type="text" name="approverID" value="<%= TD.getApproverID() %>"/>
                    </td>
                    <td>
                        <input type="text" name="details" value="<%= TD.getDetails() %>"/>
                    </td>
                    <td>
                        <input type="text" name="descriptionStatus" value="<%= TD.getTopicDescrStatus() %>"/>
                    </td>
                    <td>
                        <a href="MainController?action=DeleteTopicDescriptionController&descriptionID=<%= TD.getTopicDescrID()%>&search=<%= search%>" onclick="return confirm('Are you sure?')" >Delete</a>
                    </td>
                    <td>
                        <input type="submit" name="action" value="UpdateTopicDescriptionController"/>
                        <input type="hidden" name="descriptionID" value="<%= TD.getTopicDescrID() %>" required=""/>
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
    <a href="MainController?action=CreateTopicDescriptionController">Create new Topic Description</a></br>
    <a href="staffTopic.jsp">Topic Page</a></br>
    <a href="staff.jsp">Staff Product Page</a></br>
    </body>
</html>
