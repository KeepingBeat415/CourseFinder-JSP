<%--
  Created by IntelliJ IDEA.
  User: _seven
  Date: 2022-11-23
  Time: 10:55 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Delete</title>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

  <link href="${pageContext.request.contextPath}/view/css/delete.css" rel="stylesheet" />

</head>
<body>

<div>

  <c:if test="${requestScope.error_msg != null}">
    <c:out value="${requestScope.error_msg}" escapeXml="false"/>
  </c:if>

  <div class="modal-dialog modal-confirm">
    <div class="modal-content">
      <div class="modal-header flex-column">
        <div class="icon-box">
          <i class="material-icons">&#xE5CD;</i>
        </div>
        <h4 class="modal-title w-100">Are you sure?</h4>
      </div>
      <div class="modal-body">
        <p>Do you really want to delete account?</p>
      </div>
      <div class="modal-footer justify-content-center">
        <form action="<%= request.getContextPath() %>/DeletePersonServlet" method="post" >
          <a href="${pageContext.request.contextPath}/view/admin/admin_home.jsp" class="btn btn-secondary" style="padding-top: 10px;">Cancel</a>
          <button type="submit" class="btn btn-danger">Delete</button>
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>
