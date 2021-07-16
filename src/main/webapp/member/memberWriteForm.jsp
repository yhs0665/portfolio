<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>회원가입</title>

<!-- Custom fonts for this template-->
<link rel="stylesheet" href="/simriTest/css/login.css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<!-- Favicon-->
<script src="https://kit.fontawesome.com/2d323a629b.js"
	crossorigin="anonymous"></script>

</head>
</head>

<body class="bg-gradient-primary">


	<div class="container">

		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<div class="p-5">
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-4">회원가입</h1>
							</div>
							<form class="user" id="writeForm" name="writeForm">
								<div class="form-group row" >
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="text" class="form-control form-control-user"
											id="name" name="name" placeholder="이름">
											<div id="nameDiv"></div>
									</div>
									<div class="col-sm-6">
										<input type="text" class="form-control form-control-user"
											id="nickname" name="nickname" placeholder="닉네임">
											<div id="nicknameDiv"></div>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-sm-9 mb-3 mb-sm-0" style="padding-right: 0px;">
										<input type="email" class="form-control form-control-user"
											id="email" name="email" placeholder="이메일">
									</div>
									<div class="col-sm-3 mb-sm-0">
										<input type="button" class="btn btn-primary mt-1 "
											style="width: 80px; height:48px; border-radius:30px; " id="sendConfirmBtn" value="인증">
									</div>
								</div>
								<div id="emailDiv"></div>
								<div class="form-group row" id="confirmRow"
									style="display: none;">
									<div class="col-sm-12 mb-3 mb-sm-0">
										<input type="text" class="form-control form-control-user"
											id="emailConfirm" name="emailConfirm" placeholder="인증번호 입력">
										<input type="hidden" id="hiddenEmailConfirm"
											name="hiddenEmailConfirm">
									</div>
								</div>
								<div id="emailConfirmDiv"></div>
								<input type="hidden" id="hiddenCheckId">
								<div class="form-group row mt-3">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="password" class="form-control form-control-user"
											id="pwd" name="pwd" placeholder="비밀번호">
											<div id="pwdDiv"></div>
									</div>
									<div class="col-sm-6">
										<input type="password" class="form-control form-control-user"
											id="repwd" name="repwd" placeholder="재확인 비밀번호">
											<div id="repwdDiv"></div>
										<input type="hidden" value="/simriTest/storage/profile.jpg" name="profile">	
										
									</div>
									
									
	<!-- ==================================성향 라디오 버튼=========================================== -->
                           <div class="mt-3 mb-3">
                              <p>
                                 <strong>나의 성향은?</strong>
                                 <span id="paletteDiv"></span>
                              </p>
                              <div class="ml-6 form-check form-check-inline">
                                 <input class="form-check-input" type="radio"
                                    name="palette" id="inlineRadio1" value="red">
                                 <label class="form-check-label" for="inlineRadio1">열정</label>
                              </div>
                              &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;
                              <div class="form-check form-check-inline">
                                 <input class="form-check-input" type="radio"
                                    name="palette" id="inlineRadio2" value="green">
                                 <label class="form-check-label" for="inlineRadio2">신중</label>
                              </div>
                              &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;
                              <div class="form-check form-check-inline">
                                 <input class="form-check-input" type="radio"
                                    name="palette" id="inlineRadio3" value="yellow">
                                 <label class="form-check-label" for="inlineRadio3">다정</label>
                              </div>
                              &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;
                              <div class="form-check form-check-inline">
                                 <input class="form-check-input" type="radio"
                                    name="palette" id="inlineRadio4" value="blue">
                                 <label class="form-check-label" for="inlineRadio4">냉정</label>
                              </div>
                              </div>
                           
                           
	<!-- ========================================================================================== -->
									
									
									
								</div>
								<input type=button id="writeFormBtn" class="btn btn-primary btn-user btn-block" value="회원가입">
								<hr>
			                        <a href="https://accounts.google.com/o/oauth2/v2/auth?client_id=743752575152-svdejceorb61u42i3g7cvrf5q23ejtms.apps.googleusercontent.com&redirect_uri=http://localhost:8080/simriTest/member/google&response_type=code&scope=email%20profile%20openid&access_type=offline"
			                           class="btn btn-google btn-user btn-block">
			                           <i class="fab fa-google fa-fw"></i> 구글 로그인
			                        </a>
			                    <a href="https://kauth.kakao.com/oauth/authorize?
													client_id=5a9b475eb2415e82114c765c3f09c849&
													redirect_uri=http://localhost:8080/simriTest/member/kakao&
													response_type=code"
											class="btn btn-kakaoTalk btn-user btn-block"><i class="fas fa-comment"></i> 카카오톡 로그인
										</a>
							</form>
							<hr>
							<div class="text-center">
								<a class="small" href="/simriTest/member/findPassword">비밀번호 찾기</a>
							</div>
							<div class="text-center">
								<a class="small" href="login.html">로그인</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	
	<!-- 회원가입 유효성검사 -->
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
	 <script src="/simriTest/js/writeForm.js"></script>

</body>

</html>