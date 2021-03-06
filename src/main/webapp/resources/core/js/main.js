/**
 * Created by JAS SHAYKHOV on 11/30/2016.
 */

$(document).ready(function(){
    $('.__scrollBar').scrollbar();
});
/*menu handler*/
$(document).ready(function(){
$(function(){
    function stripTrailingSlash(str) {
        if(str.substr(-1) == '/') {
            return str.substr(0, str.length - 1);
        }
        return str;
    }

    var url = window.location.pathname;
    var activePage = stripTrailingSlash(url);

    $('.nav li a').each(function(){
        var currentPage = stripTrailingSlash($(this).attr('href'));

        if (activePage == currentPage) {
            $(this).parent().addClass('active');
        }
    });
});
});
$(document).ready(function () {
    $(".filingbutton").click(function (e) {
        e.preventDefault();
        var obj = $("#"+$(this).attr("data-target"));
        var element = $(this);
        obj.click();
        obj.change(function () {
            element.text($(this).val().replace(/^.*[\\\/]/, ''));
        });
    })

});
/**
 * Created by Sarvar on 09.06.2017.
 */
/*-----------*/
/*Common Ajax*/
/*-----------*/

var commonAjax = new function () {
    this.url = "";
    this.param = "";
    this.async = false,
//  this.async = true,
//  if(this.formId == "commonForm"){
//    $("#commonForm")[0].reset();
//    $("#commonForm").empty();
//  }

        this.setUrl = function setUrl(url){
            this.url = url;
        };

    this.setDataType = function setDataType(dataType) {
        this.dataType = dataType;
    }
    this.setCallback = function setCallback(callBack){
        gfv_ajaxCallback = callBack;
    };

    this.setAsyncl = function setAsyncl(async){
        this.async = async;
    };

    this.clearParam = function clearParam() {
        this.param = "";
    }

    this.addParam = function addParam(key,value){
        this.param = this.param + "&" + key + "=" + value;
    }

    this.setJsonParam = function setJsonParam(param) {
        this.param = this.param + "&" + "jsonData=" + JSON.stringify(param);
    }

    this.setSerializeParam = function setSerializeParam(param){
        this.param = param;
    }

    this.ajax = function ajax(){
//    if(this.formId != "commonForm"){
//      this.param += "&" + $("#" + this.formId).serialize();
//    }

        if ( gfn_isNull(this.dataType) == true) {
            this.dataType = "json";
        }
        if(this.async) {
        }

        $.ajax({
            url : this.url,
            type : "POST",
            data : this.param,
            async : this.async,
            dataType : this.dataType,
            success : function(data, status) {
                if(typeof(gfv_ajaxCallback) == "function"){
                    gfv_ajaxCallback(data);
                }
                else {
                    eval(gfv_ajaxCallback + "(data);");
                }
            }
            ,error: OnError,
            complete: function (response) {
                if(this.async) {
                }
            }
        });
    };
}

/*-----------*/
/*Main   Ajax*/
/*-----------*/

function mainCommonAjax(type, url, data, async) {
    var currentResponse;
    var currentType = type;
    var currentUrl = url;
    var currentData = data;
    var async = async;


    $.ajax({
        type: currentType,
        async: async,
        url: currentUrl,
        data: currentData,
        success: function (response) {
            currentResponse = response;
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });

    return currentResponse;
}

/*-----------------*/
/*Tab Notifications*/
/*-----------------*/

function getNotificationsFromAjax() {

    var request_notifications = 0;
    var toDo_notifications = 0;

    $.get("/Workflow/MyForms/Request/Notification",function (data,status) {
        request_notifications = data;
    });

    $.get("/Workflow/MyForms/todo/notification",function (data,status) {
        toDo_notifications = data;
        alert("Requests: " + request_notifications + " To-Do: " + toDo_notifications);
    });



};
