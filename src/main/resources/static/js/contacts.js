$(document).ready(getContacts());
$('#finnalyedit').hide();

$("#divlogout").click(function () {
    window.location = "/logout";
})
function cancelEdit() {
    $('#finnalyedit').hide();
}


function getContacts() {
    var all = '     <thead>        <tr>        <th ng-click="nameSorter()">Name</th>        <th>Email</th>        <th>Phone</th>    <th>Group</th>     <th></th>        </tr>        </thead>';
    console.log("ready!");
    $.ajax({
        url: "getContacts",
        method: 'get',
        success: function (res) {
            
            console.log(res);
            $.each(res, function (i) {
                // all += '<tr ng-repeat="obj in nameList | filter:filters:exactMatch | filter:filterFavs track by $index" class="{{obj.favorite}} animated fadeIn">';
                all += '  <td class="text-capitalize"><i class="glyphicon glyphicon-user"></i> ' + res[i].name +
                    '</td>  <td class="text-lowercase"><i class="glyphicon glyphicon-envelope"></i> ' + res[i].email +
                    ' </td><td><i class="glyphicon glyphicon-earphone"></i>' + res[i].phone + '' +
                    ' </td><td><i class="glyphicon glyphicon-briefcase"></i>' + res[i].group + '' +
                    '  </td> <td class="text-right">  <a href="#" onclick="openEdit(' + res[i].id + ')"><i class="glyphicon glyphicon-pencil"></i></a>' +
                    '   <a href="#" onclick="removeContact(' + res[i].id + ')"><i class="glyphicon glyphicon-trash"></i></a> </td> ';
                all += '</tr>';
            });
            all += '<tbody></tbody>';
            $("#contacts-table").html(all);
        }
    });
}
function removeContact(id) {
    $.ajax({
        url: "deletecontact?id=" + id,
        method: 'DELETE',
        success: function () {
            getContacts();
        }
    });
}

function deleteAllContacts() {
    $.ajax({
        url: "deleteUsersContact",
        method: 'DELETE',
        success: function () {
            getContacts();
        }
    });
}



function openEdit(id) {
    $('#finnalyedit').show();
    var contactDto = {
        id: id
    }
    console.log(contactDto);
    $.ajax({
        url: "getcontact?id=" + id,
        method: 'GET',
        /*    contentType: 'application/json; charset=UTF-8',
         dataType: 'json',
         data: JSON.stringify(contactDto),
         */   success: function (res) {

            $("#aid").val(res.id);
            $("#aname").val(res.name);
            $("#aemail").val(res.email);
            $("#aphone").val(res.phone);
            $("#agroup").val(res.group);
        }

    });
}

$("#finnalyedit").submit(function (event) {
    event.preventDefault();
    console.log(5465465465);
    var contactDto = {
        id: $("#aid").val(),
        email: $("#aemail").val(),
        name: $("#aname").val(),
        phone: $("#aphone").val(),
        group: $("#agroup").val()
    }
    console.log(contactDto);
    $.ajax({
        url: "editcontact",
        method: 'POST',
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        data: JSON.stringify(contactDto),
        success: function (res) {
            /*            alert(res.name + 'EDITED')*/
            console.log("AddedContact");
            getContacts();
            clearForm();
            $('#finnalyedit').hide();
        }
    });
});


$("#addContact").submit(function (event) {
    console.log(5);
    event.preventDefault();
    var contactDto = {
        email: $("#email").val(),
        name: $("#name").val(),
        phone: $("#phone").val(),
        group: $("#groups").val()
    }
    console.log(contactDto);
    $.ajax({
        url: "addContact",
        method: 'POST',
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        data: JSON.stringify(contactDto),
        success: function (res) {
            console.log("AddedContact");
            getContacts();
            clearForm();
        }
    });
});

$("#groups").keyup(function () {
    var nameDTO = {
        name: $("#groups").val()
    }
    console.log($("#groups").val());
    var all = '';
    $.ajax({
        url: "getGroupsByName",
        method: 'POST',
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        data: JSON.stringify(nameDTO),
        success: function (res) {
            $("#hint").hide();
            $.each(res, function (i) {
                all += '<a href="#" onclick = "submit(' + '\'' + res[i].name + '\'' + ')"> ' + res[i].name + '</a><br/> ';
                $("#hint").show();
                $("#hint").html(all);
            });
        }
    });
});
function submit(groupName) {
    $("#groups").val(groupName);
}
function clearForm() {
    $('#addContact')[0].reset();
    $("#hint").hide();
    $('#finnalyedit')[0].reset();
}