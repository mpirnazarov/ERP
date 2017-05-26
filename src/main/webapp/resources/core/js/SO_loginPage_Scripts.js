/**
 * Created by Sarvar on 26.05.2017.
 */
$('#loginButton').hover(function () {
    $('#loginPageLogo').toggleClass("buttonRotate");
});
function forgetDialogue(action) {
    var targetForm = $('#login_main_form');
    var targetDialogue = $('#login_forgetPassword_dialogue');
    var targetInput = $('#mail_reset_button');
    var curWidth = targetForm.width();
    var curHeight = targetForm.height();
    var curPosition = targetForm.offset();
    var action = action;

    targetDialogue.css({
        width: curWidth,
        height: curHeight
    });

    if (action == "show"){
        targetDialogue.slideDown(500);
    }else if (action == "hide"){
        targetDialogue.slideUp(500);
    }else {
        targetDialogue.slideToggle(500);
    }

    targetInput.val('');
}

function passwordRestartMailSend() {

    var inputMail = $('#mail_reset_button').val();

    $.ajax({
        type: "POST",
        async: false,
        url: '/restorePassword',
        data: "email=" + inputMail,
        success: function (data) {
            forgetDialogue("hide");

            alert(data);
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
}


