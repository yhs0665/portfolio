<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>로그인</title>

<!-- Custom fonts for this template-->
<link rel="stylesheet" href="/simriTest/css/login.css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<!-- Favicon-->
<script src="https://kit.fontawesome.com/2d323a629b.js"
	crossorigin="anonymous"></script>
</head>

<body style="background: linear-gradient(#e66465, #9198e5); ">

	<div class="container">

		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-8 col-lg-9 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row">
							<div class="col-lg-12">
								<div class="p-5">
									<div class="text-center">
										<h1 class="h4 text-gray-900 mb-4">로그인</h1>
									</div>
									
								
									
									
									<form class="user"> 
										<!-- 아이디 -->
										<div class="form-group" style="margin-bottom: 0;">
											<input type="email" class="form-control form-control-user"
												id="email" name="email" aria-describedby="emailHelp"
												placeholder="이메일" >
										</div>
										<div id="emailDiv" style="margin-left:8px;"></div>
										
										
										<!-- 비번 -->
										<div class="form-group" style="margin-bottom:0;">
											<input type="password" class="form-control form-control-user"
												id="pwd" placeholder="비밀번호">
										</div>
										<div id="pwdDiv" style="margin-left:8px;"></div>
										
										
										<!-- 아뒤기억하기 -->
										<div class="form-group">
											<div class="custom-control custom-checkbox small">
												<input type="checkbox" class="custom-control-input" id="idSaveCheck" name="idSaveCheck" >   <%-- ${checked} --%>
													<label class="custom-control-label" for="idSaveCheck">Remember Me</label>
											</div>
										</div>
										
										
										<a href="#"
											id="loginBtn" class="btn btn-primary btn-user btn-block"> 로그인 </a>
										<hr>
										<a href="https://accounts.google.com/o/oauth2/v2/auth?client_id=743752575152-svdejceorb61u42i3g7cvrf5q23ejtms.apps.googleusercontent.com&redirect_uri=http://localhost:8080/simriTest/member/google&response_type=code&scope=email%20profile%20openid&access_type=offline"
			                           class="btn btn-google btn-user btn-block">
			                           <i class="fab fa-google fa-fw"></i> 구글 로그인
			                        </a><a href="https://kauth.kakao.com/oauth/authorize?
													client_id=5a9b475eb2415e82114c765c3f09c849&
													redirect_uri=http://localhost:8080/simriTest/member/kakao&
													response_type=code"
											class="btn btn-kakaoTalk btn-user btn-block"><i class="fas fa-comment"></i> 카카오톡 로그인
										</a>
									</form>
									<hr>
									<div class="text-center">
										<a class="small" href="/simriTest/member/findPassword" id="find_pw_btn" >비밀번호 찾기</a>
									</div>
									<div class="text-center">
										<a class="small" href="/simriTest/member/memberWriteForm">회원가입!</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

		</div>

	</div>

	<!-- Bootstrap core JavaScript-->
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>


	<!-- Custom scripts for all pages-->
	<script src="/simriTest/js/login.js"></script>
	

 
</body>

</html>