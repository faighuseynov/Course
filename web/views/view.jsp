<%--
  Created by IntelliJ IDEA.
  User: fuadp
  Date: 4/12/16
  Time: 9:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String message = (String) request.getAttribute("message");  %>
<% String imagePath = (String) request.getAttribute("imagePath");  %>
<%= message %>
<form action="ds" method="post">
    <input type="text" value="<%=imagePath%>" id="downloadId" name="downloadName"  style="width: 600px" />
    <input type="submit" value="Download" />
</form>