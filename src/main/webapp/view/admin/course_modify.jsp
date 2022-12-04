<%--
  Created by IntelliJ IDEA.
  User: Thanh Ta
  Date: 2022-12-03
  Time: 5:54 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="admin_header.jsp"%>

<html>
<body>
    <div class="container-fluid">
        <h2>Modify Course</h2>

        <c:if test="${requestScope.success_msg != null}">
            <c:out value="${requestScope.success_msg}" escapeXml="false"/>
        </c:if>

        <c:if test="${requestScope.error_msg != null}">
            <c:out value="${requestScope.error_msg}" escapeXml="false"/>
        </c:if>

        <form action="<%= request.getContextPath()%>/UpdateCourseServlet" name="courseForm" onsubmit="return validateForm()"
              method="POST">
            <div class="form-group">
                <label>Course Code</label>
                <input type="text" name="code" class="form-control" required readonly value="${requestScope.code}">
            </div>
            <div class="form-group">
                <label>Title</label>
                <input type="text" name="title" class="form-control" required value="${requestScope.title}">
            </div>
            <div class="form-group">
                <label>Semester</label>
                <input type="text" name="semester" class="form-control" required value="${requestScope.semester}">
                <small class="form-text text-muted">Should be in the form Fall 2021, Winter 2022, etc.</small>
            </div>
            <div class="form-group">
                <label>Days</label>
                <input type="text" name="days" class="form-control" required value="${requestScope.days}">
                <small class="form-text text-muted" id="days-check">Should be in the form MonWedFri, MonFri, TueThu, Thu,
                    etc.</small>
            </div>
            <div class="form-group">
                <label>Time</label>
                <input type="text" name="time" class="form-control" required value="${requestScope.time}">
                <small class="form-text text-muted">Should be in the form 8:45-10:00, 13:15-14:30, etc.</small>
            </div>
            <div class="form-group">
                <label>Instructor</label>
                <input type="text" name="instructor" class="form-control" required value="${requestScope.instructor}">
            </div>
            <div class="form-group">
                <label>Room</label>
                <input type="text" name="room" class="form-control" required value="${requestScope.room}">
            </div>
            <br/>
            <button type="submit" class="btn btn-primary mb-2">Modify Course</button>
        </form>
    </div>

    <script>
    function validateForm() {
        let form = document.forms["courseForm"];
        const codeRegex = /^([A-Z]{3,4})-(\d{3,4})$/;
        const semesterRegex = /^((Fall)|(Winter))\s20[2-9][0-9]$/;
        const daysRegex = /^((Mon)|(Tue)|(Wed)|(Thu)|(Fri)){1,5}$/;
        const timeRegex = /^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]-([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/;
        // VALIDATE HERE
        if (!codeRegex.test(form["code"].value)) {
            alert("Please check course code value again.");
            return false;
        }
        if (!semesterRegex.test(form["semester"].value)) {
            alert("Please check semester value again.");
            return false;
        }
        if (!daysRegex.test(form["days"].value)) {
            alert("Please select at least 1 day of the week");
            return false;
        }
        if (!timeRegex.test(form["time"].value)) {
            alert("Please check time value again.");
            return false;
        }
        // SUBMIT
        submit();
    }

    function submit() {
        document.forms["courseForm"].submit();
    }
    </script>
</body>
</html>
