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

        <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
        <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>

        <!-- Multi-select Dropdown -->
        <script src="js/custom.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>

        <script type="text/javascript">
            function divide() {
                var grading = document.getElementById('grading').value;
                var gradingTxt = grading.split("\n").join('</br>');
                document.getElementById('gradingTxt').value = gradingTxt;

                var objectives = document.getElementById('objectives').value;
                var objectivesTxt = objectives.split("\n").join('</br>');
                document.getElementById('objectivesTxt').value = objectivesTxt;
            }
        </script>
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
        <c:url var="ViewTopicDescriptionsApprovedLink" value="MainController">
            <c:param name="action" value="ViewTopicDescriptionsApprovedController"></c:param>
        </c:url>
        <a href="${ViewTopicDescriptionsApprovedLink}">&#8592; Back to Topic Descriptions</a>
        <div class="container">
            <form action="MainController" method="POST">
                <div>
                    <c:if test="${requestScope.LIST_PROFESSION != null}">
                        <c:if test="${not empty requestScope.LIST_PROFESSION}">
                            <select class="js-example-placeholder-single form-control" style="width: 50%;" name="profession">
                                <option hidden disabled selected value>Profession</option>
                                <c:forEach var="profession" varStatus="counter" items="${requestScope.LIST_PROFESSION}">
                                    <option value="${profession}">${profession}</option>
                                </c:forEach>
                            </select>
                        </c:if>
                    </c:if>
                </div>
                <div class="row">
                    <h3>Grading Breakdown <span style="color: red">*</span></h3>
                    <textarea placeholder="Grading Breakdown" id="grading" rows="8"></textarea>
                    <input type="hidden" id="gradingTxt" name="grading" value="" />
                </div>
                <div class="row">
                    <h3>Course Objectives & Project Components <span style="color: red">*</span></h3>
                    <textarea placeholder="Course Objectives & Project Components" id="objectives" rows="8"></textarea>
                    <input type="hidden" id="objectivesTxt" name="objectives" value="" />
                </div>

                <input onclick="divide()" type="submit" name="action" value="Add new Selection Criteria" style="background-color: #1773BC; color: white; font-weight: bolder;"/><br/>
                <input type="reset" value="Reset"/><br/>
            </form>
        </div>
    </body>
</html>
