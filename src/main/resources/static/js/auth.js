var isUsernameDuplicate = true;
var isPasswordEqual = false;

function checkUsernameDuplicate() {
    var username = $("#username").val();

    $.ajax({
        type: "POST",
        url: "/api/auth/checkUsernameDuplicate",
        data: username,
        contentType: "text/plain",
        success: function (data) {
            if (data.data === true) {
                $("#resultMessage").text("이미 사용중인 아이디입니다.");
                $("#resultMessage").css("color", "red");
                isUsernameDuplicate = true;
            } else {
                $("#resultMessage").text("사용가능한 아이디입니다.");
                $("#resultMessage").css("color", "green");
                isUsernameDuplicate = false;
            }
        },
        error: function () {
            $("#resultMessage").text("서버 오류가 발생하였습니다.");
        }
    });
}

$("#password2").on("input", function () {
    var password1 = $("#password").val();
    var password2 = $(this).val();

    if (password1 === password2) {
        $("#passwordConfirmMessage").text("비밀번호가 일치합니다.");
        $("#passwordConfirmMessage").css("color", "green");
        isPasswordEqual = true;
    } else {
        $("#passwordConfirmMessage").text("비밀번호가 일치하지 않습니다.");
        $("#passwordConfirmMessage").css("color", "red");
        isPasswordEqual = false;
    }
});

$('#btn-save').click(function (e) {
    if (isUsernameDuplicate) {
        alert('아이디 중복 확인을 먼저 진행해주세요.');
        e.preventDefault();
    } else if (!isPasswordEqual) {
        alert('비밀번호가 일치하지 않습니다.');
        e.preventDefault();
    } else {
        $("#joinForm").submit();
    }
});