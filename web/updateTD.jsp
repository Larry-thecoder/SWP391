<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="topicDescription.TopicDescriptionError"%>
<%@page import="account.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Topic Description Page</title>

        <link href="css/form.css" rel="stylesheet" type="text/css"/>

        <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
        <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
        <script>
            $(function () {
                $("#datepicker").datepicker();
            });
            $(function () {
                $("#datepicker2").datepicker();
            });
            $(function () {
                $("#datepicker3").datepicker();
            });
            
            $(document).ready(function() {
                $('#special').val(${requestScope.SPECIALS}).trigger('change');
                $('#profession').val("${requestScope.PROFESSION}").trigger('change');
                $('#supervisors').val(${requestScope.SUPERVISORS}).trigger('change');
                $('#students').val(${requestScope.STUDENTS}).trigger('change');
            });
            
        </script>
        
        <!-- Multi-select Dropdown -->
        <script src="js/custom.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
    </head>
    <body>
        <%
            AccountDTO loginAc = (AccountDTO) session.getAttribute("LOGIN_AC");
            if (loginAc == null || !"Lecturer".equals(loginAc.getRole())) {
                response.sendRedirect("login.jsp");
                return;
            }
            TopicDescriptionError error = (TopicDescriptionError) request.getAttribute("ERROR_TOPICDES");
            if (error == null) {
                error = new TopicDescriptionError();
            }
        %>
        <c:url var="viewDocumentsReturnedLink" value="MainController">
            <c:param name="action" value="ViewDocumentsReturnedController"></c:param>
        </c:url>
        <a href="${viewDocumentsReturnedLink}">&#8592; Back to Documents returned for revision</a>
        <div class="container">
            <form action="MainController" method="POST">
                <div class="row">
                    <input type="hidden" name="lecturerID" value="${sessionScope.LOGIN_AC.userName}" />
                </div>
                <div class="row">
                    <h3>Details <span style="color: red">*</span></h3>
                    <div class="col-third">
                        <input type="text" placeholder="Class" name="className" value="${requestScope.CLASS}"/>
                    </div>
                    <div>
                        <c:if test="${requestScope.LIST_PROFESSION != null}">
                            <c:if test="${not empty requestScope.LIST_PROFESSION}">
                                <select id="profession" class="js-example-placeholder-single form-control" style="width: 50%;" name="profession">
                                    <option hidden disabled selected value>Profession</option>
                                    <c:forEach var="profession" varStatus="counter" items="${requestScope.LIST_PROFESSION}">
                                        <option value="${profession}">${profession}</option>
                                    </c:forEach>
                                </select>
                            </c:if>
                        </c:if>
                    </div>
                </div>
                <div class="row">
                    <h4>Special: </h4>
                    <c:if test="${requestScope.LIST_SPECIAL != null}">
                        <c:if test="${not empty requestScope.LIST_SPECIAL}">
                            <select id="special" class="js-example-basic-multiple" name="special[]" multiple="multiple" style="width: 100%;">
                                <c:forEach var="special" varStatus="counter" items="${requestScope.LIST_SPECIAL}">
                                    <option value="${special}">${special}</option>
                                </c:forEach>
                            </select>
                        </c:if>
                    </c:if>
                </div>
                <div class="row">
                    <h4>Duration Time: </h4>
                    <div class="input-group">
                        <div class="col-third">
                            <input type="text" id="datepicker" placeholder="From" name="durationTimeFrom" value="${requestScope.DURATION_FROM}">
                        </div>
                        <div class="col-third">
                            <input type="text" id="datepicker2" placeholder="To" name="durationTimeTo" value="${requestScope.DURATION_TO}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <h4>Kind of Person Make Registers</h4>
                    <div class="input-group">
                        <input id="payment-method-card" type="radio" name="kindOfPersonMakeRegisters" value="Lecturer" <c:if test="${requestScope.PERSON_MAKE_REGISTERS == 'Lecturer'}">checked</c:if>/>
                        <label for="payment-method-card"><span>Lecturer</span></label>
                        <input id="payment-method-paypal" type="radio" name="kindOfPersonMakeRegisters" value="Students" <c:if test="${requestScope.PERSON_MAKE_REGISTERS == 'Students'}">checked</c:if>/>
                        <label for="payment-method-paypal"> <span>Students</span></label>
                    </div>
                    <div class="row">
                        <h4>Supervisors (if any)</h4>
                        <c:if test="${requestScope.LIST_SUPERVISOR != null}">
                            <c:if test="${not empty requestScope.LIST_SUPERVISOR}">
                                <select id="supervisors" class="js-example-basic-multiple" name="supervisors[]" multiple="multiple" style="width: 100%;">
                                    <c:forEach var="supervisor" varStatus="counter" items="${requestScope.LIST_SUPERVISOR}">
                                        <option value="${supervisor.email}">${supervisor.fullName} (${supervisor.email})</option>
                                    </c:forEach>
                                </select>
                            </c:if>
                        </c:if>
                    </div>
                    <div class="row">
                        <h4>Students (if any)</h4>
                        <c:if test="${requestScope.LIST_STUDENT != null}">
                            <c:if test="${not empty requestScope.LIST_STUDENT}">
                                <select id="students" class="js-example-basic-multiple" name="students[]" multiple="multiple" style="width: 100%;">
                                    <c:forEach var="student" varStatus="counter" items="${requestScope.LIST_STUDENT}">
                                        <option value="${student.email}">${student.fullName} (${student.email})</option>
                                    </c:forEach>
                                </select>
                            </c:if>
                        </c:if>
                    </div>
                    <div class="row">
                        <h3>Content of Graduation Thesis: </h3>
                    </div>
                    <div class="row">
                        <h4>Graduation Thesis name: </h4>
                    </div>
                    <div class="row">
                        <input type="text" placeholder="English" name="thesisNameEnglish" value="${requestScope.THESIS_NAME_ENGLISH}">
                        <input type="text" placeholder="Vietnamese" name="thesisNameVN" value="${requestScope.THESIS_NAME_VN}">
                        <input type="text" placeholder="Abbreviation" name="thesisNameAbbr" value="${requestScope.THESIS_NAME_ABBR}">
                    </div>
                    <div class="row">
                        <h3>Main proposal content (including result & product):</h3>
                    </div>
                    <div class="row">
                        <textarea placeholder="Theory" name="mainContent_Theory" rows="8">${requestScope.MAIN_CONTENT_THEORY}</textarea>
                        <textarea placeholder="Practice" name="mainContent_Practice" rows="8">${requestScope.MAIN_CONTENT_PRACTICE}</textarea>
                        <textarea placeholder="Other comments (if any)" name="otherComment" rows="8">${requestScope.OTHER_COMMENT}</textarea>
                    </div>
                    <div class="row">
                        <input type="text" placeholder="Place of Signing" name="signingPlace" value="${requestScope.SIGNING_PLACE}">
                        <input type="text" id="datepicker3" placeholder="Date of Signing" name="signedDate" value="${requestScope.SIGNED_DATE}">
                    </div>
                    ${requestScope.ERROR_TOPICDES.details}<br/>

                    <input type="hidden" name="topicDesID" value="${requestScope.TOPIC_DES_ID}" />
                    <input type="submit" name="action" value="Update Topic Description"/><br/>
                    <input type="reset" value="Reset"/></br>
            </form>
        </div>
    </body>
</html>
