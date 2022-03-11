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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="Style/stylefortable.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.15.4/css/fontawesome.min.css" integrity="sha384-jLKHWM3JRmfMU0A5x5AkjWkw/EYfGUAGagvnfryNV3F9VqM98XiIH7VBGVoxVSc7" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
        <script src="https://kit.fontawesome.com/2ffd866335.js" crossorigin="anonymous"></script>
        <script>
            $(document).ready(function () {
                $('#icon').click(function () {
                    $('ul').toggleClass('show');
                });
            });
        </script>
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

        <nav>
            <label class="logo">FPT University</label>
            <ul>
                <li><a href="staff.jsp">Project</a></li>
                <li><a href="staffTopic.jsp">Topic</a></li>
                <li><a class="active" href="staffTP.jsp">Topic Description</a></li>
                <li>
                    <form action="MainController">
                        <input type="submit" name="action" value="Logout"/>
                    </form>
                </li>
            </ul>
            <label id="icon">
                <i class="fas fa-bars"></i>
            </label>
        </nav>

        <h1 class="welcome">Hello Staff! This is Topic Description Page</h1>
        <form class="smallboxform" action="MainController">
            <input class="smallbox" type="text" name="search" value="<%= search%>" />
            <button class="smallboxsearch" type="submit" name="action" value="ViewTopicDescriptionController">Search</button>
        </form>


        <%
            List<TopicDescriptionDTO> listTD = (List<TopicDescriptionDTO>) request.getAttribute("LIST_TD_STAFF");
            if (listTD != null) {
                if (!listTD.isEmpty()) {
        %>
        <div class="header_fixed">
            <table>
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
                            <%= TD.getTopicDescrID()%>
                        </td>
                        <td>
                            <input type="text" name="approverID" value="<%= TD.getApproverID()%>"/>
                        </td>
                        <td>
                            <input type="text" name="details" value="<%= TD.getDetails()%>"/>
                        </td>
                        <td>
                            <input type="text" name="descriptionStatus" value="<%= TD.getTopicDescrStatus()%>"/>
                        </td>
                        <td>
    <!--                        <a href="MainController?action=DeleteTopicDescriptionController&descriptionID=<%= TD.getTopicDescrID()%>&search=<%= search%>" onclick="return confirm('Are you sure?')" >Delete</a>-->
                            <button type="submit" name="action" value="DeleteTopicDescriptionController" onclick="return confirm('Are you sure?')">Delete</button>
                            <input type="hidden" name="descriptionID" value="<%= TD.getTopicDescrID()%>" required=""/>
                            <input type="hidden" name="search" value="<%= search%>" required=""/>
                        </td>
                        <td>
                            <button type="submit" name="action" value="UpdateTopicDescriptionController">Update</button>
                            <!--                        <input type="submit" name="action" value="UpdateTopicDescriptionController"/>-->
                            <input type="hidden" name="descriptionID" value="<%= TD.getTopicDescrID()%>" required=""/>
                            <input type="hidden" name="search" value="<%= search%>" required=""/>
                        </td>
                    </tr>
                </form>
                <%
                    }
                %>    
                </tbody>
            </table>
        </div>
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

        <a class="lastone" href="MainController?action=CreateTopicDescriptionController">Create new Topic Description</a>


        <footer class="footer-distributed">
            <div class="footer-left">
                <h3>FPT<span>University</span></h3>

                <p class="footer-links">
                    <a href="https://fpt.edu.vn/">FPT Edu |</a>

                    <a href="https://cms.fpt.edu.vn/"> CMS |</a>

                    <a href="http://libol.fpt.edu.vn/"> Library |</a>

                    <a href="https://library.books24x7.com/login.asp?ic=0"> Books24x7</a>
                </p>

                <p class="footer-company-name">Copyright © 2022 <strong>FPT University</strong> All rights reserved</p>
            </div>
            <div class="footer-center">
                <div>
                    <i class="fa fa-map-marker"></i>
                    <p>D1 Street, Saigon Hi-tech Park,</br> Long Thanh My Ward, Thu Duc City, HCM City</p>
                </div>
                <div>
                    <i class="fa fa-phone"></i>
                    <p>(028) 7300 5588</p>
                </div>
                <div>
                    <i class="fa fa-envelope"></i>
                    <p><a href="#">daihoc.hcm@fpt.edu.vn</a></p>
                </div>
            </div>
            <div class="footer-right">
                <p class="footer-company-about">
                    <span>About the FPT</span>
                    <strong>FPT University </strong> established in 2006, FPT University set its mission to providing the global competitiveness for learners, expanding the nation's intellectual horizon.
                </p>
                <div class="footer-icons">
                    <a href="https://www.facebook.com/FPTU.HCM/"><i class="fa fa-facebook"></i> </a>
                    <a href="https://www.instagram.com/fptuniversityhcm/"> <i class="fa fa-instagram"></i> </a>

                    <a href="https://twitter.com/fptu_hcm"><i class="fa fa-twitter"></i></a>
                    <a href="https://www.youtube.com/channel/UCfNrlxNgcTZDJ3jZeSSSJxg"><i class="fa fa-youtube"></i></a>
                </div>
            </div>



        </footer>

    </body>
</html>
