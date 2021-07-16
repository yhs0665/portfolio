<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- 너비를 장치너비로 설정 -->
<!-- 보여지는 화면의 줌 정도를 1배율  //애플 사파리 11버전에선 viewport 크기가 보여줘야할 내용보다 작으면 보여줘야 할 내용을 줄여서 보여준다고 한다.//모바일 접속시 모바일 크기에 맞춘 화면을 렌더링에 설정 -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no, user-scalable=yes">

<!-- 엣지 호환성 설정 -->
<meta http-equiv="X-UA-Compatible" content="id=edge">

<!--웹 사이트 제작자-->
<meta name="author" content="비트캠프 199기">

<!-- 웹 사이트 설명-->
<meta name="description" content="simriTest site">

<!-- swiper -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>

<!-- 아이콘 그림 -->
<link rel="shortcut icon"
	href="https://hobbyful.co.kr/static/img/favicon.png" />
<link rel="apple-touch-icon"
	href="https://hobbyful.co.kr/static/img/m_favicon.png" />

<!-- Favicon-->
<script src="https://kit.fontawesome.com/2d323a629b.js"
	crossorigin="anonymous"></script>

<!-- bootStrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">

<link href="/simriTest/css/style.css" rel="stylesheet" />


<style>
.css3-tab {
	list-style: none;
	margin: 0 auto 40px;
	padding: 38px 0 0 0;
	position: relative;
	width: 90%;
}

.css3-tab input[type='radio'] {
	display: none;
}

.css3-tab .css3-tab-nav {
	display: table;
	table-layout: fixed;
	width: 100%;
}

.css3-tab .css3-tab-nav label {
	display: table-cell;
	background-color: #666666;
	color: #FFFFFF;
	padding: 15px;
	text-align: center;
	transition: all .3s ease 0s;
}

.css3-tab .css3-tab-nav label:hover {
	cursor: pointer;
	background: white;
	color: #666666;
	transition: all .3s ease 0s;
}

@media ( max-width : 692px) {
	.css3-tab .css3-tab-nav {
		display: block;
		margin: 0 0 20px;
	}
	.css3-tab .css3-tab-nav label {
		display: block;
		box-sizing: border-box;
		width: 100%;
		padding: 20px;
	}
}

.css3-tab .css3-tab-content {
	overflow: hidden;
	padding: 25px;
	display: none;
	background: #FFF;
	clear: left;
	box-sizing: border-box;
}

.css3-tab input[id='tabOne']:checked ~ .css3-tab-nav label[for='tabOne']
	{
	background: red;
	color: white;
	cursor: default;
}

.css3-tab input[id='tabOne']:checked ~ div.tab-one {
	display: block;
	border-top: solid 3px red;
}

.css3-tab input[id='tabTwo']:checked ~ .css3-tab-nav label[for='tabTwo']
	{
	background: red;
	color: white;
	cursor: default;
}

.css3-tab input[id='tabTwo']:checked ~ div.tab-two {
	display: block;
	border-top: solid 3px red;
}

.css3-tab input[id='tabThree']:checked ~ .css3-tab-nav label[for='tabThree']
	{
	background: red;
	color: white;
	cursor: default;
}

.css3-tab input[id='tabThree']:checked ~ div.tab-three {
	display: block;
	border-top: solid 3px red;
}

body {
	background: white;
}

#nameDiv, #nicknameDiv, #emailDiv, #pwdDiv, #repwdDiv, #paletteDiv {
	color: red;
	font-size: 8pt;
	font-weight: bold;
	margin-top: 3pt;
}
/* 페이징 css  */
.pagination>.active>a, .pagination>.active>span, .pagination>.active>a:hover,
	.pagination>.active>span:hover, .pagination>.active>a:focus,
	.pagination>.active>span:focus {
	background: #dc3545;
	border-color: #dc3545;
}

.page-item.active .page-link {
	background-color: #dc3545;
	border-color: #dc3545;
}

.page-item.active .page-link {
	background-color: #dc3545;
	border-color: #dc3545;
}

.page-link {
	color: gray;
}

.page-link:hover {
	color: #dc3545;
	text-decoration: none;
}

#pagingNum:hover {
	cursor: pointer;
}

#file {
	display: none;
}
</style>
</head>
<body>
	<input type="hidden" id="email" value="${memberDTO.email }">
	<input type="hidden" id="myPagePg" name="pg" value="${pg }">
	<jsp:include page="/main/nav.jsp" />
	<!-- Page content-->
	<div class="container">
		<!-- 닉네임/ 팔레트/ 포인트-->

		<div class="col-lg-12">
			<div class="card">
				<div class="card-body mb-2">
					<div class="d-flex mb-2">
						<!-- Parent comment-->
						<div class="col-md-2">

							<form id="profile_upload" name="profile_upload" method="post"
								enctype="muLtipart/form-data" action="profile_upload">
								<input type="file" name="img" id="file"
									onchange="changeValue(this)" class="form-control" /> <input
									type="hidden" id="email" name="email"
									value="${memberDTO.email }">
								<!-- 동그라미 프로필!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1 -->
								<img class="rounded-circle ms-3 me-4" id="profileImg"
									style="width: 200px; height: 200px;" alt="..."
									src="${memberDTO.profile }" /> 
									<input type="hidden" name="profile_url">
						</div>



						<div class="col-md-2 ms-4 my-auto fs-2 lh-lg">
							<strong>${memberDTO.nickname }</strong><br>

							<!-- 버튼!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1 -->
							<button class="btn btn-outline-secondary btn-sm" type="button"
								id="profileBtn">프로필 사진 변경</button>



						</div>
						</form>


						<div class="col-md-2 my-auto text-center fs-4 lh-lg">
							<strong> 닉네임 변경권</strong> <br> <span class="fs-4">${memberDTO.changeNick }</span>
						</div>
						<div class="col-md-1 my-auto text-center">
							<img src="/simriTest/img/구분자.JPG">
						</div>
						<div class="col-md-2 my-auto text-center fs-3 lh-lg">
							<strong> <span class="text-danger">팔</span> <span
								class="text-primary">레</span> <span class="text-warning">트</span>
							</strong> <br><span class="fs-4">${memberDTO.palette }</span> 
							<input type="hidden" value="${memberDTO.palette }" id="hiddenPalette">
						</div>
						<div class="col-md-1 my-auto text-center">
							<img src="/simriTest/img/구분자.JPG">
						</div>
						<div class="col-md-2 my-auto text-center fs-3 lh-lg"
							style="padding-right: 60px;">
							<strong>포인트</strong> <br> <span class="fs-4"> <a
								href="/simriTest/member/pointList"
								style="text-decoration: none; color: black;">${memberDTO.point }
									P</a> <input type="hidden" value="${memberDTO.point }" id="mypoint">
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>


		<br> <br>
		<!-- Navbar -->
		<nav class="navbar navbar-expand-lg navbar-light bg-dark">
			<div class="container-fluid">
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link text-light me-4"
							id="m" href="/simriTest/member/userModify">내정보 관리</a></li>
						<li class="nav-item" aria-selected="true"><a
							class="nav-link text-light me-4 checked"
							href="/simriTest/member/myPage">나의 활동 내역</a></li>
						<li class="nav-item"><a class="nav-link text-light me-4"
							href="/simriTest/member/pointList">포인트 내역</a></li>
						<li class="nav-item"><a class="nav-link text-light me-4"
							href="/simriTest/member/pointStore">포인트 상점</a></li>
					</ul>
				</div>
			</div>
		</nav>
		<br> <br>

		<div class="card border-light">

			<c:if test="${check == null}">
				<div class="card">
					<div class='css3-tab'>
						<input type='radio' name='a' id='tabOne' tabindex="1" checked>
						<input type='radio' name='a' id='tabTwo' tabindex="2"> <input
							type='radio' name='a' id='tabThree' tabindex="3">


						<div class="css3-tab-nav">
							<label for='tabOne' id='tabOne' style="width: 10px;">내가 쓴
								글</label> <label for='tabTwo' id='tabTwo' style="width: 10px;">내가
								쓴 댓글</label> <label for='tabThree' id='tabThree' style="width: 10px;">찜
								목록</label>
						</div>

						<!--내가쓴글   ------------------------------------------------------------------------------------ -->

						<div class='css3-tab-content tab-one'>
							<!-- start slipsum code -->
							<div class="card">
								<form id="myPageBoardDeleteForm" method="POST"
									action="/simriTest/member/myPageBoardDelete">
									<div class="card-body mx-auto ms-5 me-5">
										<table class="table table-hover myPageBoardTable"
											id="myPageBoardTable">

											<tbody id="writeBody">
											</tbody>
										</table>
									</div>

									<br>
									<div>
										<input id="allCheck" type="checkbox"
											style="margin-left: 78px;"> 전체선택
										<button type="button" id="choiceDeleteBtn"
											class="btn btn-outline-danger float-right"
											style="margin-left: 780px;">선택삭제</button>
									</div>
								</form>


								<form name="myPageListForm" id="myPageListForm"
									method="post" action="/simriTest/community/community_View">
									<input type="hidden" id="seq" name="seq"> 
									<input type="hidden" id="com" name="com" value="0"> 
									<input type="hidden" id="category" name="category"> 
									<input type="hidden" id="myPage" name="myPage" value="1">

								</form>

								<nav aria-label="Page navigation example">
									<ul class="pagination justify-content-center my-4"
										id="MypageBoardPagingDiv">
									</ul>
								</nav>

								<br> <br>


							</div>

						</div>

						<!--댓글   ------------------------------------------------------------------------------------ -->
						<div class='css3-tab-content tab-two'>
							<!-- start slipsum code -->
							<div class="card">
								<div class="card-body mx-auto ms-5 me-5">
									<form id="myPageReplyDeleteForm" method="POST"
										action="/simriTest/member/myPageReplyDelete">
										<table class="table table-hover myPageReplyTable"
											id="myPageReplyTable">
											<tbody id="replyBody">
											</tbody>
										</table>
								</div>

								<br>
								<div>
									<input id="allReplyCheck" type="checkbox"
										style="margin-left: 78px;"> 전체선택
									<button type="button" id="replyChoiceDeleteBtn"
										class="btn btn-outline-danger float-right"
										style="margin-left: 780px;">선택삭제</button>

								</div>
								</form>
								<nav aria-label="Page navigation example">
									<ul class="pagination justify-content-center my-4"
										id="MypageReplyPagingDiv">
									</ul>
								</nav>

								<br> <br>


							</div>

						</div>


						<!--찜   ------------------------------------------------------------------------------------ -->
						<div class='css3-tab-content tab-three'>
							<!-- start slipsum code -->
							<div class="row row-cols-1 row-cols-md-4 g-4 likeBody">
								<table class="table table-hover myPageBoardTable"
									id="myPageLikeTable">
								
								</table>
							</div>
							<br> <br>
							<nav aria-label="Page navigation example">
								<ul class="pagination justify-content-center my-4"
									id="MypageLikePagingDiv">
								</ul>
							</nav>
						</div>
					</div>

				</div>

			</c:if>

			<jsp:include page="${display }" />

		</div>





	</div>


	<br>
	<br>
	<br>
	<br>

	<jsp:include page="/main/footer.jsp" />
	
	
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
	<script type="text/javascript" src="../js/myPage.js"></script>
	<script type="text/javascript" src="../js/pointHistory.js"></script>




</body>
</html>