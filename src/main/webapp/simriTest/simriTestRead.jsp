<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Blog entries-->
<div class="col-lg-12">
<form id="simriTestReadForm" method="post" action="/simriTest/simriTest/simriTestRead">	
<input type="hidden" name="seq" id="seq" value="${seq }">
<input type="hidden"  name="similarSeq" id="similarSeq" value="${similarSeq }">
<input type="hidden" name="category" id="category" value="심리 테스트">
<input type="hidden" name="hit" id="hit" value= "${hit }">
<input type="hidden" name="memId" id="memId" value="${memId }">
<input type="hidden" name="onoff" id="onoff" value="${onoff }">
<input type="hidden"  name="testURL" id="testURL" value="${simriDTO.testURL }">
<!-- modal 신고 -->
<input type="hidden" id="checkReplySeq" value="">
<input type="hidden" id="checkEmail" value="">

	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="/simriTest/index.jsp">Home</a></li>
			<li class="breadcrumb-item"><a href="/simriTest/simriTest/simriTest_List">심리테스트</a></li>
			<li class="breadcrumb-item active" aria-current="page" id="nav_hashTag1">${simriDTO.hashTag1 }</li>
		</ol>
	</nav>
	<!-- Featured blog post-->
	<div class="card shadow bg-transparent border-lightgray mb-5">
		<div class="card ps-3  pe-3 bg-transparent">

			<!-- 추가 -->
			<div class="row">
				<div class="col-lg-12">

					<div class="carousel-item active">
						<img class="d-block w-100 mt-3" id="image" src="/simriTest/storage/${simriDTO.image }" alt="심리테스트 이미지">
					</div>

					</div>
				</div>

				<br>

				<div class="row ">
					<div class="col-lg-12">
						<div class="card mb-4">
							<button type="button" class="btn btn-primary btn-lg btn-block"
								id="testStart">테스트시작</button>
						</div>
					</div>
				</div>

				<div class="row ">
					<div class="col-lg-12">
						<div class="col-12 h2 mb-2" id="subject">${simriDTO.subject }</div>
						<div class="col-12 h4 mb-2 description_col muted">
							<p id="content">${simriDTO.content }</p>
						</div>

						<div class="col-12 hashtag_col muted color-red">
							<span id="hashTag1">#${simriDTO.hashTag1 }</span>
							<c:if test="${simriDTO.hashTag2 !=null }"> 
								<span id="hashTag2">#${simriDTO.hashTag2 }</span>
							</c:if>
							<c:if test="${simriDTO.hashTag2 !=null }"> 
								<span id="hashTag3">#${simriDTO.hashTag3}</span>
							</c:if>
							
						</div>
					</div>
					<!-- 저장하기 / 맨위로 -->
				</div>
				<!-- 저장하기 / 맨위로 -->
				<div class="card mb-4 border-0">
					<div class="col mb-4 mt-4 me-2" style="text-align-last: right;">
						<div class="gap-2 col-1.5 mx-auto">
							<button class="btn btn-outline-info" type="button"
								onclick="js:kakaoShare2()">
								<i class="fas fa-external-link-alt"></i>
							</button>
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
							<!-- 검정 북맠크 -->
							<!-- 			<button class="btn btn-outline-info" type="button"><i class="fas fa-bookmark"></i></button> -->
						</div>
					</div>
				</div>
			</div>


		</div>
		<br> <br>


		<!-- 끝 -->

		<!-- 관련글 넣을곳 -->


		<figure>
			<blockquote class="blockquote">
				<p>관련글</p>
			</blockquote>
			<!-- <figcaption class="blockquote-footer">
					    Someone famous in <cite title="Source Title">Source Title</cite>
				 </figcaption> -->
		</figure>

		<div class="row">
			<div class="col-lg-12">

				<div class="row row-cols-1 row-cols-md-3 g-4 similar">
					<!-- -------------------------------------------- -->

				</div>


			</div>
		</div>
		<br> <br>
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
								style="float: right;" id="replyWriteBtn">댓글 등록</button>
						</p>
						<br> <br>
						<!-- Comment with nested comments-->
					</div>
					<ul class="commentList"
						style="list-style: none; padding-left: 0px; overflow: auto; max-height: 600px;">
					</ul>
				</div>
			</div>

		</section>
	</div>
</div>


</div>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/simriTest/js/simriTestRead.js"></script>
<script src="/simriTest/js/simriTest_Like.js"></script>
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