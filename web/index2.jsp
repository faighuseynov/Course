<%@ page import="az.course.model.Student" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: fuadp
  Date: 2/17/16
  Time: 8:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Course</title>
      <script type="text/javascript" src="js/jquery/jquery-1.10.2.js"></script>
      <script type="text/javascript" src="js/jquery/jquery-ui.js"></script>
      <script type="text/javascript" src="js/jquery/jquery.dataTables.min.js"></script>
      <script type="text/javascript" src="js/main.js"></script>
      <script type="text/javascript" src="js/combo.js"></script>
      <link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
      <link rel="stylesheet" type="text/css" href="css/main.css">
      <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css">
  </head>
  <body>
   <script type="text/javascript">

     $(function() {

         $( "#accordion" ).accordion({
             collapsible: true
         });
         getSectorComboListAdv();
         getStudentComboList(0);

         $('#sectorComboId').change(function() {
             getStudentComboList($('#sectorComboId').val());
         });

         $( "#beginDate" ).datepicker({
             changeMonth: true,
             changeYear: true
         });

         $( "#endDate" ).datepicker({
             changeMonth: true,
             changeYear: true
         });


         $('#advSearchButId').click(function() {
            advSearchStudent();
         });




         $("#newStudentDialog").dialog({
             resizable: false,
             height:400,
             width: 500,
             title: 'Yeni Telebe',
             autoOpen: false,
             modal: true,
             buttons: {
                 "Yadda saxla": function() {
                     addStudent();

             //        $( this ).dialog( "close" );
                 },
                 "Ləğv et": function() {
                     $( this ).dialog( "close" );
                 }
             }
         });

         $("#editStudentDialog").dialog({
             resizable: false,
             height:400,
             width: 500,
             title: 'Yeni Telebe',
             autoOpen: false,
             modal: true,
             buttons: {
                 "Redakte et": function() {
                   updateStudent();
                  alert("Melumat ugurla redakte olundu!");
                     getStudentList();
                     $( this ).dialog( "close" );
                 },
                 "Ləğv et": function() {
                     $( this ).dialog( "close" );
                 }
             }
         });



         $(".buttonDesign").click(function() {
             var currLink = $(this).attr("id");
             //  alert(currLink);
             globButtonId = currLink;

        //     alert(globButtonId);

         });


           $('#searchId').keyup(function() {
               var keyword = $('#searchId').val();
               if (globButtonId == 'studentId')   {
                   $.ajax({
                       url:'cs?action=searchStudent',
                       data: 'keyword='+keyword,
                       dataType:'html',
                       success: function(data) {
                           $('#content').html(data);
                       }
                   });
               }   else if (globButtonId == 'teacherId') {
                   $.ajax({
                       url:'cs?action=searchTeacher',
                       data: 'keyword='+keyword,
                       dataType:'html',
                       success: function(data) {
                           $('#content').html(data);
                       }
                   });
               }


           });



         $('#teachTable').DataTable();

         $('#studentId').click(function() {
         //    $('#studentTable').show();
         //    $('#teacherTable').hide();
             $('#newStudentDiv').hide();
             getStudentList();


           //  alert("Student Button");
         });

         $('#teacherId').click(function() {
        //     $('#studentTable').hide();
         //    $('#teacherTable').show();
             $('#newStudentDiv').hide();
             getTeacherList();
         });




         $('#newId').click(function() {
             switch (globButtonId) {
                 case "studentId":
                     $('#newStudentDialog').load('views/newStudent.jsp',function() {
                         $('#newStudentDialog').dialog('open');
                         getSectorComboList();

                     });
                     break;
                 case "teacherId":
                     alert("Teacher Button");
                     break;

                 case "groupId":

                     break;



             }
         });


         $('#sendId').click(function() {
             addStudent();
         })

     });



   </script>

  <%  Student user = (Student) session.getAttribute("user"); %>
  <div id="container" style="width: 100%; height: 83%">
      <div id="header" style="background-color: #696969;height:90px; border: 1px solid; text-align: center">
         <span id="spId"></span>
          <h1 style="margin-bottom:0;">Course</h1>
          <h3 style="margin-top: 4px;">Xos gelmissiniz  <%=user.getName() + " "+user.getSurname()%></h3>
          <a href=logout.jsp style="color:#fff; padding: 0 20px 0 0px; float: right"> <img width="40" height="40" src="images/logout.png">
          </a>
      </div>
      <div id="menu"  style="background-color: #696969; height:100%;width:180px;float:left; border: 1px solid">
     <input type="button" class="buttonDesign" value="Student" id="studentId" />    <br>
     <input type="button" class="buttonDesign" value="Teacher" id="teacherId" />   <br>
          <input type="button" class="buttonDesign" value="Group" id="groupId" />


      </div>
      <div id="content1" style="background-color: #a9a9a9;height:100%;width:81%;float:left; border: 1px solid">


       <div id="buttonDiv" style="border: 1px solid">
          <input type="button" value="New" id="newId" class="extraBut" />   &nbsp;
           <input type="button" value="Update" id="updateId" class="extraBut" />   &nbsp;
           <input type="button" value="Delete" id="deleteId" class="extraBut" />  &nbsp;

           <input type="text" id="searchId" placeholder="Search..." /> <input type="button" value="Axtar" id="searchButId" />

       </div>

          <div id="accordion">
              <h3>Ətraflı axtarış</h3>
              <div>
                 <select id="sectorComboId"></select>   &nbsp;
                  <select id="studentComboId"></select>   <br>
                  <input type="text" id="beginDate" placeholder="Başlanğıc tarix"/> &nbsp;
                  <input type="text" id="endDate" placeholder="Son tarix" />
                  <input type="button" value="Axtarış" id="advSearchButId"  />
              </div>

          </div>

       <div id="content">
        <div id="newStudentDialog">


        </div>
        <div id="editStudentDialog">

        </div>

       </div>

       <%
           System.out.println(user.getRole());
           if (user.getRole().equals("user")) { %>
          <script type="text/javascript">
             $(function() {
                 $('#newId').show();
                 $('#studentId').show();

             });


          </script>


         <% } else if (user.getRole().equals("admin")){ %>
          <script type="text/javascript">
              $('#newId').show();
              $('#updateId').show();
              $('#deleteId').show();
              $('#studentId').show();
              $('#teacherId').show();
              $('#groupId').show();

          </script>

        <% } %>




      </div>
      <div id="footer" style="background-color: #696969 ;border: 1px solid; clear:both;text-align:center">
          Copyright © Fuad Pashabeyli
      </div> </div>


  </body>
</html>