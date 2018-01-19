<%--
  Created by IntelliJ IDEA.
  User: fuadp
  Date: 3/12/16
  Time: 12:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<option value="0">Secin:</option>

<c:forEach items="${studentComboList}" var="scl">
<option value="${scl.id}">${scl.name} ${scl.surname}</option>
</c:forEach>
