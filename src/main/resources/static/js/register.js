$("#registerform").submit(function (event) {
    event.preventDefault();

    var userDTO = {
        email: $("#email").val(),
        name: $("#name").val(),
        password: $("#password").val()
    }

    console.log(userDTO);
    $.ajax({
        url: "register2",
        method: 'POST',
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        data: JSON.stringify(userDTO),
        success: function (res) {
            console.log(res);
            if (res) {
                alert(userDTO.email + " registered successfully");
                auth(userDTO);
                clearForm();
            }
            else {
                alert("User with email" + userDTO.email + " exists");
            }
        }
    });
});


function auth(userDTO) {
    console.log("auth");
    console.log(userDTO);
    $.ajax({
        url: "authregister",
        method: 'POST',
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        data: JSON.stringify(userDTO),
        success: function (res2) {
            window.location = "/contacts";
        }

    });

    function clearForm() {
        $('#registerform')[0].reset();
        $('#loginForm')[0].reset();
    }

}