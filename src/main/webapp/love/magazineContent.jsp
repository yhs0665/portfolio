<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<form name="magazineViewForm" id="magazineViewForm" method="post" action="/simriTest/love/magazineContent">
<input type="hidden" name="seq" id="seq" value="${seq }">
<input type="hidden" name="category" id="category" value="연애 심리글">
<input type="hidden" name="pg" id="pg" value="${pg }">
<input type="hidden" name="hit" id="hit" value= "${hit }">
<input type="hidden" name="memId" id="memId" value="${memId }">
<input type="hidden" name="onoff" id="onoff" value="${onoff }">
<input type="hidden" name="similarSeq" id="similarSeq" value=${similarSeq }>
<input type="hidden" id="memberPoint" name="memberPoint" value=${memberPoint }>
<input type="hidden" id="boardPoint" name="boardPoint">
<!-- modal 신고 -->
<input type="hidden" id="checkReplySeq" value="">
<input type="hidden" id="checkEmail" value="">

<!-- Blog entries-->
<div class="col-lg-12">

	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="/simriTest/index.jsp">Home</a></li>
			<li class="breadcrumb-item"><a href="/simriTest/love/magazineList">연애 심리글</a></li>
			<li class="breadcrumb-item active" aria-current="page"><span id="hashtag1Span"></span></li>
		</ol>
	</nav>
	<!-- Featured blog post-->
	<div class="card mb-8 bg-transparent border-lightgray">
		<!-- <div class="card-body"> -->
		<div class="input-group">
			<div class="center-block" style="padding:15px;">
				<div class="small text-muted mb-1 mt-2" style="text-align-last: right;">작성일 : <span id="comLogtimeSpan"></span></div>
				<div class="small text-muted mb-1 mt-2" style="text-align-last: right; margin-right: 5px;">좋아요 : <span id="likeSpan"></span>&emsp;조회수 : <span id="hitSpan"></span></div>
				<div class="small text-muted mb-1 mt-2"><span id="hashtag1Span1"></span></div>
				<div class="card-title fs-2 mt-2 mb-2"><span id="subjectSpan"></span></div>
				<div class="text-center">
					<img class="img-fluid rounded" id="image" width="900" height="400"
						alt="뷰사진" />
				</div>
				
				<div class="card-body">
					<pre style="white-space:pre-wrap;"><span id="contentSpan"></span></pre>
				</div>
			</div>
		</div>
	</div>
	


	<!-- 저장하기 / 맨위로 -->
	<div class="card mb-4 border-light">
		<div class="col mb-4 mt-4 me-2" style="text-align-last: right;">
			<div class="gap-2 col-1.5 mx-auto">
					<div id="magazineContentDiv2">
						<button class="btn btn-outline-info" type="button" onclick="js:kakaoShare1()"><i class="fas fa-external-link-alt"></i></button>
							<c:if test="${onoff==0 || onoff==undifined }">
								<button class="btn btn-outline-info" type="button"
									 onclick="likesingo(1)">
										<i class="far fa-bookmark"></i> 
								</button>
							</c:if>
							<c:if test="${onoff==1 }">
								<button class="btn btn-info" type="button"
										 onclick="likesingo(1)">
										<i class="far fa-bookmark"></i> 
								</button>
							</c:if>
					
					<button class="btn btn-outline-info" onclick="location.href='#'" type="button">맨위로</button>
				</div>
			</div>
		</div>
	</div>
</div>
	<br>
	<br>

	<!-- 끝 -->

	<!-- 관련글 넣을곳 -->
	<figure>
		<blockquote class="blockquote">
			<p>관련글</p>
		</blockquote>
	</figure>
	<div class="row">
			<div class="col-lg-12">

			<div class="row row-cols-1 row-cols-md-3 g-4 similar">
		
			</div>
		</div>
	</div>
	<br>
	<br>

</form>
	<!-- 관련글끝 -->
	
	
	<!-- comment 여기부터 -->

	<div class="row">
		<div class="col-lg-12">
		<!-- Comments section-->
				<section class="mb-5">
					<div class="card bg-light">
						<div class="card-body">
						<div id="replyCommentForm">
							<!-- Comment form-->
							<form class="mb-4">
								<textarea class="form-control" style="resize: none;" rows="3"
									placeholder="Join the discussion and leave a comment!"
									id="replyComment"></textarea>
							</form>
							<p>
								<button type="button" class="btn btn-success btn-sm"
									style="float: right;"
									id="replyWriteBtn">댓글 등록</button>
							</p>
							<br>
							<br>
							<!-- Comment with nested comments-->
						</div>
							<ul class="commentList" style="list-style:none; padding-left:0px; overflow:auto; max-height: 600px;">
							</ul>
						</div>
					</div>
							
				</section>
		</div>
	</div>


	<!-- comment 여기까지  -->



		<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
		<script src="/simriTest/js/magazine_View.js"></script>
		<script src="/simriTest/js/magazine_Like.js"></script>
		<script src="/simriTest/js/magazine_Reply.js"></script>
		<!-- 카카오톡 공유 -->
		<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
        <script>
            Kakao.init('a2c3a339ed7bbd83cf434b61d9593f21');
            Kakao.isInitialized();
        </script>
        <script src="/simriTest/js/share.js" charset="utf-8"></script>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">신고</h5>
      </div>
      
      <div class="modal-body">
                  신고 사유 : <input type="text" list="singoReason" name="singoReason" placeholder="직접입력" >
            <datalist id="singoReason">    
                <option value="비속어 사용">
                <option value="음란물 등록">
                <option value="싸움조장">   
            </datalist>
            
            <br>            
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="singoInsertBtn" name="singoInsertBtn">신고</button>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>