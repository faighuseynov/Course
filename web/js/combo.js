/**
 * Created with IntelliJ IDEA.
 * User: fuadp
 * Date: 2/27/16
 * Time: 11:25 AM
 * To change this template use File | Settings | File Templates.
 */


function getSectorComboList() {
    $.ajax({
        url: 'cs?action=getSectorComboList',
        type: 'GET',
        dataType: 'html',
        success: function(data) {
           $('#sectorId').html(data);
        }
    }) ;
}

function editSectorComboList(sektorId) {
    $.ajax({
        url: 'cs?action=getSectorComboList',
        type: 'GET',
        dataType: 'html',
        success: function(data) {
            $('#sectorId1').html(data);
            $('#sectorId1').val(sektorId);
        }
    }) ;

}


function getSectorComboListAdv() {
    $.ajax({
        url: 'cs?action=getSectorComboList',
        type: 'GET',
        dataType: 'html',
        success: function(data) {
            $('#sectorComboId').html(data);
        }
    }) ;
 }

function getStudentComboList(sectorId) {



    $.ajax({
        url: 'cs?action=getStudentComboList',
        type: 'GET',
        dataType: 'html',
        data:'sectorId='+sectorId,
        success: function(data) {
            $('#studentComboId').html(data);
        }
    }) ;
}
