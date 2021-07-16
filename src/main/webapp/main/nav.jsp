<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- style 파일 임폴트 -->
<link rel="stylesheet" href="/simriTest/css/style.css">

<!-- Responsive navbar-->
<nav class="navbar navbar-expand-lg navbar-dark bg-danger fixed-top">
	<div class="container">
	<form id=totalSearchForm method="post" action="/simriTest/simriTest/totalSearch">
	<input type="hidden" name="searchPg" id="searchPg">
		<div class="form-group">
			<div class="row no-gutters">
				<div class="col">
					<input
						class="form-control border-secondary border-right-0 rounded-0"
						type="search" placeholder="검색할 내용" id="totalKeyword" name="totalKeyword">
				</div>
				<div class="col-auto">
					<button
						class="btn btn-outline-light border-left-0 rounded-0 rounded-right"
						type="button"
						id="totalSearchBtn"
						>
						<i class="fa fa-search"></i>
					</button>
				</div>
			</div>
		</div>
	</form>

		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
				<c:if test="${sessionScope.memId == null }">
					<li class="nav-item"><a id="member_login" class="nav-link">로그인</a></li>
					<li class="nav-item"><a id="member_writeForm" class="nav-link">회원가입</a></li>
				</c:if>
				<c:if test="${sessionScope.memId != null }">
					<li class="nav-item"><a href="/simriTest/member/myPage"
						id="member_myPage" class="nav-link">${sessionScope.memNickname }님
					</a></li>
					<li class="nav-item"><a href="/simriTest/member/logout"
						id="member_logout" class="nav-link">로그아웃</a></li>
					<li class="nav-item"><a href="/simriTest/member/myPage"
						id="member_myPage" class="nav-link">마이페이지</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>
<!-- Page header with logo and tagline-->
<header class="masthead py-10 bg-light border-bottom mb-2">
	<div class="container">
		<div class="text-center my-7 mb-3">
			<br> <br> <br> <br>

			<span id="typing-txt" class="typing-txt"></span>


			<ul id="nav2" class="nav justify-content-center bg-transparent mt-4">
				<li class="nav-item"><a class="nav-link active"
					href="/simriTest/index.jsp">Home</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/simriTest/simriTest/simriTest_List">심리테스트</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/simriTest/community/community?com=0">커뮤니티</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/simriTest/love/magazineList">연애 심리글</a></li>
				<li class="nav-item"><a class="nav-link"
				 	href="/simriTest/simriIntroduce/simriIntroduce">홈페이지 소개글</a></li>
			</ul>
		</div>
	</div>
</header>


<br>

<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://www.cssscript.com/demo/simple-typewriter-effect-pure-javascript-typewriterjs/typewriter.js"></script>

<script>
$('#totalSearchBtn').click(function() {

   if ($('#totalKeyword').val() == '') {
      alert('검색어를 먼저 입력해주세요');
   }else {
	   
      $('#totalSearchForm').submit();
   }
});


var typing = document.getElementById('typing-txt');

var typewriter = new Typewriter(typing, {
    loop: true
});

typewriter.typeString('뭐하고 심리?')
    .pauseFor(2500)
    .deleteAll()
    .typeString('심리 테스트의 모든것')
    .pauseFor(2500)
    .deleteChars(11)
    .typeString('심테의 세상속으로!')
    .start();
</script>