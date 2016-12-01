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