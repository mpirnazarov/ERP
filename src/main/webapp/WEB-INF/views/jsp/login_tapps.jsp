<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>LG CNS TAPPS</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <spring:url value="/resources/core/css/Login.css" var="loginCss" />
    <spring:url value="/resources/core/css/bootstrap.css" var="bootstrapCss" />
    <spring:url value="/resources/core/css/Site.css" var="siteCss" />
    <spring:url value="/resources/core/js/jquery-1.12.4.min.js" var="jquery" />
    <spring:url value="/resources/core/js/notify.min.js" var="notifyMinJs" />
    <spring:url value="/resources/core/js/bootstrap.js" var="bootstrapJs" />
    <spring:url value="/resources/core/js/respond.js" var="respondJs" />
	<script src="${jquery}"></script>
    <script src="${notifyMinJs}"></script>
    <script src="${bootstrapJs}"></script>
    <script src="${respondJs}"></script>
    <link href="${loginCss}" rel="stylesheet" />
	<link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${siteCss}" rel="stylesheet" />
	<link href="//db.onlinewebfonts.com/c/91a4f54d33593964b72846b3d0a97cba?family=HalvorsenW01-Extrabold" rel="stylesheet" type="text/css" />

	<script>
		$(document).ready(function () {
			$('#progressBar').hide();



			//CAPS LOCK VALIDATION
			$('#Password').keypress(function (e) {
				var s = String.fromCharCode(e.which);
				if (s.toUpperCase() === s && s.toLowerCase() !== s && !e.shiftKey) {
					$(".Absolute-Center").notify("Caps lock is on!", {class:"warn", position: "top center" } )
				}
			});
		});

		function checkLogin() {

			var objURL = {};

			var returnUrl = window.location.search.replace(
					new RegExp("([^?=&]+)(=([^&]*))?", "g"),
					function ($0, $1, $2, $3) {
						objURL[$1] = $3;
					}
			);

			var requestData = {
				Username: $('#UserName').val(),
				Password: $('#Password').val(),
				RememberMe: ($("#RememberMe").is(":checked")) ? "true" : "false",
				ReturnUrl: objURL["ReturnUrl"]
			};

			$('#progressBar').attr("class", "progress active progress-striped").show();
			$('#btnSubmit').hide();
			$.ajax({
				url: '/User/LoginAjax',
				type: 'POST',
				data: JSON.stringify(requestData),
				dataType: 'json',
				contentType: 'application/json; charset=utf-8',
				error: function (xhr) {
					alert('Error: ' + xhr.statusText);
				},
				success: function (response) {
					if (response.hasOwnProperty('Url')) {
						//window.location.pathname = response.Url;
						history.pushState("", document.title, response.Url);
						window.location.pathname = response.Url;
					}
					else {
						$('#myModal').modal('show');
						$("#Password").val("");


						$('#progressBar').hide();
						$('#btnSubmit').show();
						return false;
					}
				},
				async: true,
				processData: false
			});
		}

	</script>
	<script type="text/javascript">
		window.onload = function () {
			var button = document.getElementById('input_button_bg_change');
			var body = document.getElementsByTagName('body')[0];
			var colors = ['rgb(102,51,204)', 'rgb(153,204,51)', 'black', 'white', 'rgb(0,204,153)', 'rgb(204,51,153)', 'rgb(255,204,51)', '#FFAACC', 'rgb(122,111,110)'];
			button.onclick = function () {
				body.style.backgroundColor = colors[Math.floor(Math.random() * colors.length)];
			};
		};
	</script>

</head>
<body>

<div class="container body-content">

	<!-------------------------------------------------------------------------------------->

	<div class="form-horizontal">

		<div class="container ">
			<div class="row">

				<div class="Absolute-Center is-Responsive">

					<div class="col-sm-12 col-md-8 col-md-offset-1">

						<div>

							<p style="font-family: HalvorsenW01-Extrabold; font-size:24px">&nbsp;LG CNS UZBEKISTAN</p>

						</div>


						<div class="form-group input-group control-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
							<input class="form-control" placeholder="username" type="text" data-val="true" data-val-required="Field Username is required." id="UserName" name="UserName" value="" onkeydown="if (event.keyCode == 13) document.getElementById('btnSubmit').click()" />
							<span class="field-validation-valid" data-valmsg-for="UserName" data-valmsg-replace="true"></span>
						</div>
						<div class="form-group input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
							<input class="form-control password" placeholder="password" data-val=" true" data-val-required="Field Password is required." id="Password" name="Password" type="password" value="" onkeydown="if (event.keyCode == 13) document.getElementById('btnSubmit').click()" />
							<span class="field-validation-valid" data-valmsg-for="Password" data-valmsg-replace="true"></span>
						</div>


						<div class="form-group">
							<div class="progress active progress-striped hidden" style="height:3em" tabindex="-1" id="progressBar">
								<div class="progress-bar progress-bar-success" role="progressbar"
									 aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:100%;">
									<h5>Login</h5>
								</div>
							</div>
							<input type="button" value="Login" id="btnSubmit" class="btn btn-block" onclick="checkLogin();" />
						</div>
						<div class="form-group input-group" align="center">
							<input type="checkbox" data-val="true" onkeydown="if (event.keyCode == 13) document.getElementById('btnSubmit').click()" data-val-required="Field RememberMe is required." id="RememberMe" name="RememberMe" type="checkbox" value="true" />
							<label for="RememberMe"> <span>&nbsp; Remember me</span></label>
						</div>

						<div>
							<img src="/resources/images/ERPnew.png" alt="logo" style="width:100%; height:100%; margin-left:10px">
						</div>

						<p>Copyright &copy; <%=(new SimpleDateFormat("yyyy").format(new java.util.Date())).toString()%> All rights reserved</p>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>


<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">Login failed</h4>
			</div>
			<div class="modal-body">
				Invalid Username and/or Password. Please, try again
			</div>
			<div class="modal-footer">
				TAPPS
			</div>
		</div>
	</div>
</div>






</body>
</html>