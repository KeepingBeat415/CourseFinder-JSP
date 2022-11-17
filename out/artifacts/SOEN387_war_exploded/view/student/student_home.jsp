<%--
  Created by IntelliJ IDEA.
  User: Thanh Ta
  Date: 2022-11-09
  Time: 11:13 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="student_header.jsp" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container theme-showcase" role="main">
    <!-- Main jumbotron for a primary marketing message or call to action -->

    <div class="jumbotron" style="padding: 50px 50px 250px 50px;">
        <h3>Student Home Page</h3>

        <c:if test="${requestScope.add_success != null}">
            <c:out value="${requestScope.add_success}" escapeXml="false"/>
        </c:if>

        <c:if test="${requestScope.add_error != null}">
            <c:out value="${requestScope.add_error}" escapeXml="false"/>
        </c:if>

        <c:if test="${requestScope.delete_success != null}">
            <c:out value="${requestScope.delete_success}" escapeXml="false"/>
        </c:if>

        <c:if test="${requestScope.delete_error != null}">
            <c:out value="${requestScope.delete_error}" escapeXml="false"/>
        </c:if>

        <div class="form-row">

            <table class="table table-striped">
                <thead>
                <tr>
                    <th class="text-center">Code</th>
                    <th class="text-center">Title</th>
                    <th class="text-center">Semester</th>
                </tr>
                </thead>
                <tbody>
                <jsp:useBean id="registered_course" scope="request" type="java.util.List"/>
                <c:forEach items="${registered_course}" var="course">
                    <tr>
                        <td class="text-center">${course.code}</td>
                        <td class="text-center">${course.title}</td>
                        <td class="text-center">${course.semester}</td>
                        <td class="text-center">
                            <form action=${pageContext.request.contextPath}/delete_course method="post">
                                <input type="hidden" name="course_id" value=${course.id}>
                                <input type="hidden" name="course_end_date" value=${course.end_date}>

                                <button
                                        class="btn btn-danger btn-sm">Delete
                                </button>

                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>


            <div class="form-group col-md-4">
                <form action=${pageContext.request.contextPath}/available_course>
                    <button class="btn btn-primary" type="submit" name="add_course" id="add_course">Add Course</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
