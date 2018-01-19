/**
 * Created with IntelliJ IDEA.
 * User: fuadp
 * Date: 2/17/16
 * Time: 9:08 PM
 * To change this template use File | Settings | File Templates.
 */

var globStudId = 0;

function getStudentList() {
//    var name1 = 'Namig';

    $.ajax({
        url: 'cs?action=getStudentList',
        type:'GET',
        dataType:'html',
        success: function(data) {
          $('.content').html(data);
        }

    });

}


function getTeacherList() {
    $.ajax({
        url: 'cs?action=getTeacherList',
        type:'GET',
        dataType:'html',
        success: function(data) {
            $('#content').html(data);
        }

    });
}


function check(username) {
    $.ajax({
        url: 'cs?action=checkUsername',
        type:'GET',
        data: 'username='+username,
        dataType:'html',
        success: function(data) {
           $('#spId').html(data);
        //    $('#checkHiddenId').val(data)
        //    alert($('#checkHiddenId').val(data));
   }
    });
}

function addStudent() {
     var name = $('#nameId').val();
     var surname = $('#surnameId').val();
     var address = $('#addressId').val();
     var dob = $('#dobId').val();
     var sector = $('#sectorId').val();
     var username = $('#usernameId').val();
    var pwd = $('#pwdId').val();
    var role = $('#roleId').val();

    if (username == '' || pwd == '' || role == 0) {

        alert("Zehmet olmasa elave edin");
    }  else {
        if ($('#checkEndId').val() == 'true')     {
            alert("Artiq movcuddur");
        }   else {
  $.ajax({
        url: 'cs?action=addStudent',
        type:'POST',
        data: 'name='+name+'&surname='+surname+'&address='+address+'&dob='+dob+'&sector='+sector+'&username='+username
            +'&pwd='+pwd+'&role='+role,
        dataType:'text',
        success: function() {
            alert("Melumat ugurla elave olundu!");
            $("#myModal").modal("hide");
            getStudentList();
        }
    });
    }
    }
}


function editStudent(studentId) {
    globStudId = studentId;
 //   alert(globStudId);
   $.ajax({
      url: 'cs?action=editStudent',
      type: 'GET',
      dataType: 'html',
      data: 'studentId='+studentId,
       success: function(data) {
          $("#editModalDataId").html(data);
        //   alert("ttt");
           $("#editModal").modal();
          // getSectorComboList();
       }

    });

}

function updateStudent() {
    var name = $('#nameId1').val();
    var surname = $('#surnameId1').val();
    var address = $('#addressId1').val();
    var dob = $('#dobId1').val();
    var sector = $('#sectorId1').val();

    $.ajax({
        url: 'cs?action=updateStudent',
        type:'POST',
        data: 'name='+name+'&surname='+surname+'&address='+address+'&dob='+dob+'&sector='+sector+'&studentId='+globStudId,
        dataType:'text',
        success: function() {
            //   alert("success!");
        }
    });
}


function advSearchStudent() {
  //  alert("test1");
    var sectorComboId = $('#sectorComboId').val();
    var beginDate = $('#beginDate').val();
    var endDate = $('#endDate').val();

    /*if (beginDate == '' || endDate == '') {
        alert("Please ,fill date!")
    }   else {*/

    $.ajax({
       url:'cs?action=advancedSearchStudent',
       type:'get',
       data:'sectorComboId='+sectorComboId+'&beginDate='+beginDate+'&endDate='+endDate,
       dataType: 'html',
       success:function(data) {
           $('#content').html(data);
       }
    });
   /* }*/

}
