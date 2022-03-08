<%-- 
    Document   : staff
    Created on : Jan 20, 2022, 2:33:33 PM
    Author     : Mr.Khuong
--%>

<%@page import="java.util.List"%>
<%@page import="project.ProjectDTO"%>
<%@page import="account.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Page</title>
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
        <h1>Hello Staff! This is the Project page for Staff</h1>
        <form action="MainController">
            <input type="submit" name="action" value="Logout"/>
        </form>
        <form action="MainController">
            Search<input type="text" name="search" value="<%= search%>" />
            <input type="submit" name="action" value="ViewSubByStaffController"/>
        </form>
        <%
            List<ProjectDTO> listProject = (List<ProjectDTO>) request.getAttribute("LIST_PROJECT_STAFF");
            if (listProject != null) {
                if (!listProject.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>NO</th>
                    <th>Project ID</th>
                    <th>Project Name</th>
                    <th>Lecture ID</th>
                    <th>Approver ID</th>
                    <th>Subject ID</th>
                    <th>Status</th>
                    <th>Submit Time</th>
                    <th>Edit</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (ProjectDTO project : listProject) {
                %> 
            <form action="MainController">
                <tr>
                    <td><%= count++%></td>
                    <td><%= project.getProjectID()%></td>
                    <td><%= project.getProjectName() %></td>
                    <td><%= project.getLectureID() %></td>
                    <td>
                        <input type="text" name="approverID" value="<%= project.getApproverID() %>"/>
                    </td>
                    <td><%= project.getSubjectID() %></td>
                    <td>
                        <input type="text" name="status" value="<%= project.getStatus() %>"/>
                    </td>
                    <td><%= project.getSubmitTime()%></td>
                    <td>
                        <input type="submit" name="action" value="UpdateSubByStaffController"/>
                        <input type="hidden" name="projectID" value="<%= project.getProjectID()%>" required=""/>
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
    <a href="staffTopic.jsp">Topic Page</a></br>
    <a href="staffTP.jsp">Topic Description Page</a></br>
    </body>
</html>
