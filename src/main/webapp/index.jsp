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

<!--웹 사이트 제작자 & 설명-->
<meta name="author" content="비트캠프 199기">
<meta name="description" content="simriTest site">
<meta name="keywords" content="심리테스트  플랫폼">

<!-- swiper -->
<link rel="stylesheet"
   href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>

<!-- style 파일 임폴트 -->
<link rel="stylesheet" href="/simriTest/css/style.css">

<!-- 아이콘 그림 -->
<link rel="shortcut icon"
   href="https://hobbyful.co.kr/static/img/favicon.png" />
<link rel="apple-touch-icon"
   href="https://hobbyful.co.kr/static/img/m_favicon.png" />
<link
   href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
   rel="stylesheet" type="text/css" />
<!-- Favicon-->
<script src="https://kit.fontawesome.com/2d323a629b.js"
   crossorigin="anonymous"></script>

<!--mappint error <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />-->
<!-- bootStrap -->
<link
   href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
   rel="stylesheet"
   integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
   crossorigin="anonymous">

<title>심리테스트</title>
</head>
<body>

   <jsp:include page="/main/nav.jsp" />
   <!-- Page content-->
   <div class="container">
      <div class="row">
         <!-- Blog entries-->
         <c:if test="${com != 0 && com != 2 }">
            <div class="col-lg-8">
         </c:if>
         <c:if test="${com == 0 }">
            <div class="col-lg-9">
         </c:if>

         <c:if test="${empty display }">
            <!-- Featured blog post-->
            <div class="card mb-4">
               <!-- 슬라이드 -->
               <div id="carouselExampleIndicators"   class="carousel carousel-dark slide" data-bs-ride="carousel">
                  <div class="carousel-indicators">
                     <button type="button" data-bs-target="#carouselExampleIndicators"
                        data-bs-slide-to="0" class="active" aria-current="true"
                        aria-label="Slide 1"></button>
                     <button type="button" data-bs-target="#carouselExampleIndicators"
                        data-bs-slide-to="1" aria-label="Slide 2"></button>
                     <button type="button" data-bs-target="#carouselExampleIndicators"
                        data-bs-slide-to="2" aria-label="Slide 3"></button>
                  </div>
                  
                  <form name="CFForm" id="CFForm" method="post" action="/simriTest/simriTest/simriTestRead" >
	                  <input type="hidden" name="seq" id="CFSeq">
	         		  <input type="hidden" id="CFboardPoint" name="boardPoint">
	         		  <input type="hidden" class="memberPoint" name="memberPoint">
	                  <div class="carousel-inner" id="dlfma">
							
	                  </div>
                  </form>
                  <button class="carousel-control-prev" type="button"
                     data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                     <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                     <span class="visually-hidden">Previous</span>
                  </button>
                  <button class="carousel-control-next" type="button"
                     data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                     <span class="carousel-control-next-icon" aria-hidden="true"></span>
                     <span class="visually-hidden">Next</span>
                  </button>
               </div>
            </div>

<input type="hidden" name="seq" id="seq" value="${seq }">
<!-- ------------------------------------- 글 뿌리기------------------------------------- -->
            <form name="indexForm" id="indexForm" method="post" action="/simriTest/simriTest/simriTestRead">
            <input type="hidden" name="seq" id="favoriteSimriTestSeq">
               <figure>
                  <blockquote class="blockquote">
                     <p>인기 컨텐츠</p>
                  </blockquote>
                  <figcaption class="blockquote-footer">인기 심리테스트</figcaption>
               </figure>
               <div class="row hotSimriBody"></div>
            </form>


            <form name="indexLoveForm" id="indexLoveForm" method="post" action="/simriTest/love/magazineContent">
            <input type="hidden" name="seq" id="favoriteMagazinelistSeq">
            <input type="hidden" id="memberPoint" class="memberPoint" name="memberPoint" value="${memberPoint }">
            <input type="hidden" id="boardPoint" name="boardPoint">
               <figure>
                  <blockquote class="blockquote">
                     <p>인기 컨텐츠</p>
                  </blockquote>
                  <figcaption class="blockquote-footer">인기 연애 심리글</figcaption>
                  
               </figure>
               <div class="row hotLoveBody"></div>
            </form>


            <form name="" id="indexRecentForm" method="post" action="/simriTest/simriTest/simriTestRead">
            <input type="hidden" name="seq" id="recentlySimriTestlistSeq">
               <figure>
                  <blockquote class="blockquote">
                     <p>신규 컨텐츠</p>
                  </blockquote>
                  <figcaption class="blockquote-footer">신규 심리테스트</figcaption>
               </figure>
               <div class="row recentlySimriBody"></div>
            </form>

         </c:if>

         <c:if test="${not empty display }">
            <jsp:include page="${display }" />
         </c:if>

      </div>

      <c:if test="${com != 0 && com != 2}">
         <jsp:include page="/main/side.jsp" />
      </c:if>
      <c:if test="${com == 0 }">
         <jsp:include page="/main/side_community.jsp" />
      </c:if>



   </div>
   </div>
   <!-- 위에 col c:if 준 div 지우면 안돼!-->
   <!-- 위에 큰 div2개   container , row 꺼 -->

   <jsp:include page="/main/footer.jsp" />


   <!-- Bootstrap core JS-->
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
   <script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
   <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
   <script type="text/javascript" src="/simriTest/js/member.js"></script>
   <script type="text/javascript" src="/simriTest/js/index.js"></script>  
   <script type="text/javascript" src="/simriTest/js/indexCF.js"></script>  
   
   
</body>
</html>