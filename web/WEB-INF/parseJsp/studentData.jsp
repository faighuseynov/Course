<%@ page import="az.course.model.Student" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: fuadp
  Date: 2/20/16
  Time: 12:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    $(function() {
        $('#studTable').DataTable({
            "paging": true,
            "lengthChange": false,
            "searching": false,
            "ordering": true,
            "info": true,
            "autoWidth": false
        });






    });

</script>

<%--<%  List<Student> studentList = (List<Student>) request.getAttribute("studentList");  %>--%>

<div class="modal fade" id="editModal" tabindex="-1" role="dialog" >
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Edit Student</h4>
            </div>
            <div class="modal-body" id="editModalDataId">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" id="updateStudentId"   class="btn btn-primary">Save changes</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<table id="studTable" class="table table-bordered table-hover" >
    <thead>
    <tr>

        <th>No</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Address</th>
        <th>Sektor</th>
        <th>Surname</th>
        <th>Address</th>
        <th>Sektor</th>
        <th>Date of Birth</th>
        <th>Edit Student</th>
    </tr>
    </thead>
    <tbody>


    <c:forEach items="${studentList}" var="sl">

    <tr>

        <td>${sl.id}</td>
        <td>${sl.name}</td>
        <td>${sl.surname}</td>
        <td>${sl.address}</td>
        <td>${sl.sector}</td>
        <td>${sl.surname}</td>
        <td>${sl.address}</td>
        <td>${sl.sector}</td>
        <td>${sl.dob}</td>
        <td><a href="javascript: editStudent(${sl.id});">Edit Student</a></td>
    </tr>
    </c:forEach>

    </tbody>
</table>
