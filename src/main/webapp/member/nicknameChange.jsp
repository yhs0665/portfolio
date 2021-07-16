<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>닉네임 변경하기</title>

<!-- Custom fonts for this template-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<!-- Favicon-->
<script src="https://kit.fontawesome.com/2d323a629b.js"
	crossorigin="anonymous"></script>
<style>
#nicknameDiv {
	color: red;
	font-size: 8pt;
	font-weight: bold;
	margin-top: 3pt;
}
</style>

</head>

<body>
	<input type="hidden" id="email" value="${memId }">
	<div class="container">

		<div class="card-body" style="padding: 0px; margin-top: 10px;">
			<div class="card-title fs-5">닉네임 변경</div>
		</div>
		<div class="col-8">
			<div class="col-xs-5 border-top" style="width: 270px"></div>
		</div>
		<div class="card-body"
			style="padding-left: 0px; padding-right: 0px; padding-bottom: 0px;">
			<div class="row">
				<div style="width: 300px; margin-right: 80px;">
					<div class="input-group">
						<span class="input-group-text">닉네임</span> <input id="nickname"
							name="nickname" type="text" class="form-control"
							value="${memberDTO.nickname }" aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-default">
					</div>
					<div id="nicknameDiv"></div>
					<button type="button" style="width: 275px;" id="nicknameChangeBtn"
						class="btn btn-warning btn-sm font-monospace mt-3">변경하기</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-3.6.0.min.js"></script>

	<script src="js/sb-admin-2.min.js"></script>
	<script>
	$('#nicknameChangeBtn').click(function(){
		$('#nicknameDiv').empty();
		
		if (!checkNickname($('#nickname').val())) {
			return false;
		} 		
		$.ajax({
			url:'/simriTest/member/useNicknameItem',
			data:{'nickname' : $('#nickname').val(),
				  'email' : $('#email').val()},
			type:'post',
			success: function(data){
				alert("닉네임이 변경되었습니다");
				window.close();
				window.opener.location.reload();
				//window.opener.location.href="/simriTest/index.jsp";
			},
			error: function(err){
				console.log(err);
			}
		
		});
		// 공백확인 함수
		function checkExistData(value) {
			if (value == "") {
				return false;
			}
			return true;
		}
		//이름검사------------------------------------------------
		function checkNickname(nickname) {
			if (!checkExistData(nickname)) {
				$('#nicknameDiv').text("닉네임을 입력해주세요!");
				$('#nickname').focus();
				return false;
			}

			var nicknameRegExp = /^[가-힣A-Za-z]{2,8}$/;
			if (!nicknameRegExp.test(nickname)) {
				$('#nicknameDiv').text("2~8자의 한글  또는 영문 대소문자");
				$('#nickname').focus();
				return false;
			}
			
			if(nickname == '관리자' || nickname=='admin'){
				alert("사용 불가능한 닉네임입니다");
				$('#writeForm #nickname').focus();
				return false;
			}
			return true; //확인이 완료되었을 때
		}
		
		
	});
	</script>



</body>

</html>