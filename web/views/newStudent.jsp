<%--
  Created by IntelliJ IDEA.
  User: fuadp
  Date: 2/24/16
  Time: 7:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    $(function() {

        $( "#tabs" ).tabs();
        $( "#dobId" ).datepicker({
            changeMonth: true,
            changeYear: true
        });

        $('#checkFalseId').click(function() {
            check($('#usernameId').val());


    }) ;

    });
</script>

<div id="tabs">
    <ul>
        <li><a href="#tabs-1">Şəxsi məlumatlar</a></li>
        <li><a href="#tabs-2">İstifadəçi məlumatları</a></li>
    </ul>
    <div id="tabs-1">
        <div style="width: 200px;display: inline-block">Ad:</div>   <input type="text" id="nameId" />
        <div style="width: 200px;display: inline-block">Soyad:</div>   <input type="text" id="surnameId" />
        <div style="width: 200px;display: inline-block">Address:</div>   <input type="text" id="addressId" />
        <div style="width: 200px;display: inline-block">Doğum tarixi:</div>   <input type="text" id="dobId" />
        <div style="width: 200px;display: inline-block">Sektor:</div>   <select id="sectorId" style="width: 183px"></select>
    </div>
    <div id="tabs-2">
        <div style="width: 150px;display: inline-block">İstifadəçi adı:</div>   <input type="text" id="usernameId" />  <input type="button" id="checkFalseId" value="check">
        <div style="width: 150px;display: inline-block">Şifrə:</div>   <input type="password" id="pwdId" />
        <div style="width: 150px;display: inline-block">İcazə:</div>   <select id="roleId" style="width: 183px">
        <option value="0">Secin</option>
        <option value="user">User</option>
        <option value="admin">Admin</option>
        </select>
    </div>

</div>

