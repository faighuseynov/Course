<%@ page import="az.course.model.Student" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: fuadp
  Date: 2/27/16
  Time: 11:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Student> sectorList = (List<Student>) request.getAttribute("sectorList"); %>
<option value="0">Secin:</option>

<% for (Student sector: sectorList) { %>
<option value="<%= sector.getSectorId()%>"><%= sector.getSector()%></option>

<% } %>