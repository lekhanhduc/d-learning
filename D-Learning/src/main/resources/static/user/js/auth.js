$(document).ready(function(){

    $('#register-button').on('click', function(event){
        event.preventDefault();
        let username = $('#username').val();
        let email = $('#email').val();
        let password = $('#password').val();

        $.ajax({
            url: '/api/v1/register',
            method: 'post',
            contentType: 'application/json',
            data: JSON.stringify({
                username: username,
                email: email,
                password: password
            }),
            success: function (response){
                window.location.href='/';
            },
            error: function(xhr, status, error) {
                let errorMessage = "Đã xảy ra lỗi. Vui lòng thử lại sau.";
                if (xhr.status === 409) {
                    errorMessage = "Tên người dùng hoặc email đã tồn tại.";
                }
                $('#error-message').text(errorMessage);
            }
        });
    });
});

