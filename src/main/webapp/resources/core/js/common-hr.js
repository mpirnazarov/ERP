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

function mainCommonAjax(type, url, data, async, callbackFunc) {
    var currentResponse;
    var currentType = type;
    var currentUrl = url;
    var currentData = data;
    var async = async;
    var callBack = callbackFunc;


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
        },
        complete: function (response) {
            if(callbackFunc != ""){

            }
        }
    });

    return currentResponse;
}

/*-----------------*/
/*Tab Notifications*/
/*-----------------*/
function getNotificationsRequest(data) {
    var requests = data;
    var requestTab = $('#head-nav-counter-request');
    requestTab.css('background-color','red');
    requestTab.text(data);

};

function getNotificationsToDo(data) {
    var todos = data;
    var todosTab = $('#head-nav-counter-todo');

    todosTab.css('background-color','red');
    todosTab.text(data);
};

function getNotificationsFromAjax() {

    var request_notifications , toDo_notifications;
    var todosTab = $('#head-nav-counter-todo');
    var requestTab = $('#head-nav-counter-request');


    $.get("/Workflow/MyForms/Request/Notification",function (data,status) {
        request_notifications = data;
    });

    $.get("/Workflow/MyForms/todo/notification",function (data,status) {
        toDo_notifications = data;

        if (request_notifications == 0){
            requestTab.hide();
        }else {
            requestTab.show();
            requestTab.html(request_notifications);
        }

        if(toDo_notifications == 0){
            todosTab.hide();
        }else {
            todosTab.show();
            todosTab.html(toDo_notifications);
        }

        /*alert("requet: " + request_notifications + "\nTo-Do: " + toDo_notifications )*/
    });








};
