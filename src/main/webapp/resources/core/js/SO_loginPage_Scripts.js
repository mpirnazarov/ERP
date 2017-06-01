/**
 * Created by Sarvar on 26.05.2017.
 */

$('#loginButton').hover(function () {
    $('#loginPageLogo').toggleClass("buttonRotate");
});

$('#mail_reset_input').keypress(function(event){
    if(event.keyCode == 13){
        $('#mail_reset_submit_button').click();
    }
});

/*$(document).ajaxSend(function() {
    $('#login_forgetPassword_dialogue').css('background-color','red');
});*/

function forgetDialogue(action) {
    $('span.loginSpanPopOver').remove();
    $('#mail_reset_submit_button').prop('disabled', false);

    var targetForm = $('#login_main_form');
    var targetDialogue = $('#login_forgetPassword_dialogue');
    var targetInput = $('#mail_reset_input');
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

    $('span.loginSpanPopOver').remove();
    var inputMail = $('#mail_reset_input');
    var sendEmailButton = $('#mail_reset_submit_button');
    var msg = "";

    $.ajax({
        type: "POST",
        async: true,
        url: '/restorePassword',
        data: "email=" + inputMail.val(),
        beforeSend: function () {
            inputMail.after('<span class="loginSpanPopOver">' + '<img class="loginSpanPopOverImage" src="resources/images/ajax-loader2.gif" alt="Sending mail">' + 'Sending email, please wait' + '</span>');
            sendEmailButton.prop('disabled', false);
        },
        success: function (data) {
            $('span.loginSpanPopOver').remove();
            sendEmailButton.prop('disabled', true);
            var $code = data.code;
            msg = data.message;
            inputMail.after('<span class="loginSpanPopOver">' + msg + '</span>');
            var $span = $('span.loginSpanPopOver');

            if($code == 1){
                $span.css({'color':'#ff0000', 'border-color':'#ff0000'});
            }else if ($code == 2){
                $span.css({'color':'#E29A3B', 'border-color':'#E29A3B'});
            }else if ($code == 3){
                $span.css({'color':'#787878', 'border-color':'#787878'});
            }else if ($code == 200){
                $span.css({'color':'#33E123', 'border-color':'#33E123'});
            }
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });

}


