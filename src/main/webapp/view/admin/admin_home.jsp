<%--
  Created by IntelliJ IDEA.
  User: _seven
  Date: 2022-10-29
  Time: 11:06 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@include file="admin_header.jsp"%>

<div class="container theme-showcase" role="main">
    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron" style="padding: 50px 50px 250px 50px;">

        <form action="<%= request.getContextPath() %>/SearchCourseServlet" method="post">
            <h3>Course Enrolled Students:</h3>
            <div class="form-row">
                <div class="form-group col-md-8">
                    <input type="search" class="form-control" name="course_code" placeholder="Search by Course Code..." id="search_course" onkeyup="validate_search_course()" required>
                </div>
                <div class="form-group col-md-4">
                    <button class="btn btn-primary" type="submit" name="search_course" id="search_course_submit">Submit</button>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-8">
                    <span id="invalidate_course_alert"></span>
                </div>
            </div>
        </form>

        <c:if test="${requestScope.course_not_existed != null}">
            <c:out value="${requestScope.course_not_existed}" escapeXml="false"/>
        </c:if>

        <c:if test="${requestScope.student_not_existed != null}">
            <c:out value="${requestScope.student_not_existed}" escapeXml="false"/>
        </c:if>

        <c:if test="${requestScope.search_course != null}">
            <c:out value="${requestScope.search_course}" escapeXml="false"/>
        </c:if>

    </div>


<%--    <div class="jumbotron" style="padding: 50px 50px 250px 50px;">--%>
<%--        <form action="" method="post">--%>
<%--            <h3>Student Information:</h3>--%>
<%--            <div class="form-row">--%>
<%--                <div class="form-group col-md-8">--%>
<%--                    <input type="search" class="form-control" name="student_id" placeholder="Search by Student ID..." id="search_student" onkeyup="validate_search_student()">--%>
<%--                </div>--%>
<%--                <div class="form-group col-md-4">--%>
<%--                    <button class="btn btn-primary" type="submit" name="search_student" id="search_student_submit">Submit</button>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="form-row">--%>
<%--                <div class="form-group col-md-12">--%>
<%--                    <span id="invalidate_id_alert"></span>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </form>--%>
<%--        <?php include 'search_student.php' ?>--%>
<%--    </div>--%>
</div>

<%@include file="admin_footer.jsp"%>
