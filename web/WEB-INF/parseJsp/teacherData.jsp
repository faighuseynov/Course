<%@ page import="az.course.model.Student" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: fuadp
  Date: 2/20/16
  Time: 12:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function() {
        $('#teachTable').DataTable();
    });

</script>

<%  List<Student> studentList = (List<Student>) request.getAttribute("teacherList");  %>

<table id="teachTable" class="display" cellspacing="0" width="100%" >
    <thead>
    <tr>

        <th>No1</th>
        <th>Name1</th>
        <th>Surname1</th>
        <th>Address1</th>
        <th>Date of Birth1</th>
    </tr>
    </thead>
    <tbody>

    <% for (Student student:studentList) { %>

    <tr>

        <td><%= student.getId()%></td>
        <td><%= student.getName()%></td>
        <td><%= student.getSurname()%></td>
        <td><%= student.getAddress()%></td>
        <td><%= student.getDob()%></td>
    </tr>

    <% } %>

    </tbody>
</table>
