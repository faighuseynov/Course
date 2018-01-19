<%@ page import="az.course.model.Student" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: fuadp
  Date: 2/27/16
  Time: 12:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%--<% Student student = (Student) request.getAttribute("student"); %>--%>

<div style="width: 200px;display: inline-block">Ad:</div>   <input type="text" id="nameId1" value="${student.name}" />
<div style="width: 200px;display: inline-block">Soyad:</div>   <input type="text" id="surnameId1" value="${student.surname}" />
<div style="width: 200px;display: inline-block">Address:</div>   <input type="text" id="addressId1" value="${student.address}" />
<div style="width: 200px;display: inline-block">Doğum tarixi:</div>   <input type="text" id="dobId1" value="${student.dob}" />
<div style="width: 200px;display: inline-block">Sektor:</div>   <select id="sectorId1" style="width: 183px"></select>

<script type="text/javascript">
    $(function() {
        $( "#dobId1" ).datepicker({
            changeMonth: true,
            changeYear: true
        });

        editSectorComboList(${student.sectorId});
    });
</script>