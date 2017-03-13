/**
 * Created by Sarvar on 10.03.2017.
 */

/*Empty subject validation*/
if ($("#subject").val().trim() == "") {
    flag = false;
    msg += "⛔ Subject cannot be empty" + "<br/>";
    $('#subject').css("border","2px solid red");
    $('#subject').next('span').addClass('glyphicon-info-sign');
}else {
    $('#subject').css("border", "1px solid #999999");
    $('#subject').next('span').removeClass('glyphicon-info-sign')
}



/* file size limitation */
if ($("#file").val().trim() != "") {
    var size = 0;
    input = document.getElementById('file');
    for (var i = 0; i < input.files.length; i++) {
        size += input.files[0].size;
    }
    if (size > 104857600) {
        flag = false
        msg += "⛔ Attached files cannot be more than 100MB" + "<br/>";
        $('#file').css("border","2px solid red");
        $('#file').next('span').addClass('glyphicon-info-sign');
    }else {
        $('#file').css("border", "1px solid #999999");
        $('#file').next('span').removeClass('glyphicon-info-sign')
    }
}

var dStart = new Date($("#dateStart").val());
var dEnd = new Date($("#dateEnd").val());


/* Start date cannot be more than end date*/
if (dStart > dEnd) {
    flag = false;
    msg += "⛔ Start date cannot be later than end date" + "<br/>";
    $('#dateStart').css("border","2px solid red");
    $('#dateEnd').css("border","2px solid red");
    $('#dateEnd').next('span').addClass('glyphicon-info-sign');
} else {
    $('#dateStart').css("border", "1px solid #999999");
    $('#dateEnd').css("border", "1px solid #999999");
    $('#dateEnd').next('span').removeClass('glyphicon-info-sign');
}

/* Date start cannot be empty */
if ($("#dateStart").val().trim() == "") {
    flag = false;
    msg += "⛔ Start date cannot be empty" + "<br/>";
    $("#dateStart").css("border","2px solid red");
    $('#dateEnd').next('span').addClass('glyphicon-info-sign');
}else {
    $('#dateStart').css("border", "1px solid #999999");
    $('#dateEnd').next('span').removeClass('glyphicon-info-sign');
}

/* Date end cannot be empty */
if ($("#dateEnd").val().trim() == "") {
    flag = false;
    msg += "⛔ End date cannot be empty" + "<br/>";
    $('#dateEnd').css("border","2px solid red");
    $('#dateEnd').next('span').addClass('glyphicon-info-sign');
}else {
    $('#dateEnd').css("border", "1px solid #999999");
    $('#dateEnd').next('span').removeClass('glyphicon-info-sign');
}


