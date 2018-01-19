<%--
  Created by IntelliJ IDEA.
  User: fuadp
  Date: 4/2/16
  Time: 12:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.getSession(true).invalidate();
    request.getSession(true).removeAttribute("user");
   response.sendRedirect("login.jsp");
%>