<%--
  Created by IntelliJ IDEA.
  User: _seven
  Date: 2022-10-30
  Time: 1:26 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    session.invalidate();
    response.sendRedirect("index.jsp");
%>
