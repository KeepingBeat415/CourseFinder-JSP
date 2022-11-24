<%@ page import="com.example.soen387.model.Person" %><%--
  Created by IntelliJ IDEA.
  User: _seven
  Date: 2022-11-23
  Time: 4:13 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@include file="admin_header.jsp"%>

<body>
<div class="container py-5 h-100">
    <div class="row justify-content-center align-items-center h-100">
        <div class="col-12 col-lg-9 col-xl-7">
            <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                <div class="card-body p-4 p-md-5">

                    <form action="<%= request.getContextPath() %>/UpdatePersonServlet" method="post">
                        <p class="text-center h2 fw-bold mb-5 mx-1 mx-md-4 mt-4">Profile</p>

                        <c:if test="${requestScope.success_msg != null}">
                            <c:out value="${requestScope.success_msg}" escapeXml="false"/>
                        </c:if>

                        <c:if test="${requestScope.error_msg != null}">
                            <c:out value="${requestScope.error_msg}" escapeXml="false"/>
                        </c:if>

                        <c:if test="${requestScope.same_msg != null}">
                            <c:out value="${requestScope.same_msg}" escapeXml="false"/>
                        </c:if>

                        <jsp:useBean id="person_info" scope="request" class= "com.example.soen387.model.Person" />

                        <div style="padding: 20px 0 0 0;" class="form-row">
                            <div class="form-group col-md-4">
                                <label>First Name</label>
                                <input name="first_name" type="text" class="form-control"  value="<%= person_info.getFirst_name() %>" pattern="[A-Za-z]{1,32}" required title="Please enter valid information to proceed." />
                            </div>
                            <div class="form-group col-md-4">
                                <label>Last Name</label>
                                <input name="last_name" type="text" class="form-control"  value="<%= person_info.getLast_name() %>" pattern="[A-Za-z]{1,32}" required title="Please enter valid information to proceed." />
                            </div>

                            <div class="form-group col-md-4">
                                <label>Email</label>
                                <input name="email" type="email" class="form-control"  value="<%= person_info.getEmail() %>" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" required title="Please enter valid information to proceed." />
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label>Mobile Phone</label>
                                <input name="phone_number" type="text" class="form-control"  value="<%= person_info.getPhone_number() %>" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" required title="Please enter valid information to proceed (EXAMPLE: 000-000-0000)." />
                            </div>

                            <div class="form-group col-md-4">
                                <label>Account Type</label>
                                <input name="user_type" value="admin" type="text" class="form-control" readonly/>
                            </div>
                            <div class="form-group col-md-4">
                                <label for="DOB">Date of Birth</label> <br />
                                <input type="date" value="<%= person_info.getDOB() %>" name="DOB" id="DOB" required />
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-12">
                                <label>Address</label>
                                <input type="text" class="form-control" value="<%= person_info.getAddress() %>" name="address" required title="Please enter valid information to proceed." />
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <button id="submit" type="submit" class="btn btn-primary">SUBMIT</button>
                                <button type="reset" class="btn btn-default">RESET</button>

                            </div>
                            <div class="form-group col-md-4"></div>
                            <div class="form-group col-md-4">
                                <a href="${pageContext.request.contextPath}/view/admin/admin_delete.jsp" class="btn btn-danger" style="float:right;">DELETE</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<%@include file="admin_footer.jsp"%>