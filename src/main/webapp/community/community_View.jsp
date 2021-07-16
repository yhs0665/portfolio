<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form name="communityViewForm" id="communityViewForm" method="post" action="/simriTest/community/community_View">
<input type="hidden" name="seq" id="seq" value="${seq }">
<input type="hidden" name="category" id="category" value="${category }">
<input type="hidden" name="com" id="com" value="${com }">
<input type="hidden" name="pg" id="pg" value="${pg }">
<input type="hidden" name="hit" id="hit" value= "${hit }">
<input type="hidden" name="memId" id="memId" value="${memId }">
<input type="hidden" name="onoff" id="onoff" value="${onoff }">
<input type="hidden" name="singoOnoff" id="singoOnoff" value="${singoOnoff }">
<input type="hidden" name="replySingoOnoff" id="replySingoOnoff" value="${replySingoOnoff }">
<!-- modal 신고 -->
<input type="hidden" id="checkReplySeq" value="">
<input type="hidden" id="checkEmail" value="">

			<div class="col-lg-12">
				<!-- Post content-->
				<article>
					<!-- Post header-->
					<header class="mb-4">
						<!-- Post title-->
					
						<h1 class="fw-bolder mb-1"><span id="subjectSpan"></span></h1>
						<!-- Post meta content-->
						<div class="text-muted fst-italic mb-2"><span id="nickNameSpan"></span>&emsp;<span id="paletteSpan"></span></div>
						<!-- Post categories-->
						<span class="text-muted fst-italic mb-2"><span id="categorySpan"></span>&emsp;<span id="comLogtimeSpan"></span>&emsp;조회수:<span id="hitSpan"></span></span>
						<span class="text-muted fst-italic mb-2" style="float: right;">&emsp;좋아요 <span id="likeSpan"></span>
						</span>

					</header>
					<!-- Preview image figure-->
					<figure class="mb-4">
						<img class="img-fluid rounded" id="image" width="700px" height="300px"
							alt="뷰사진" />
					</figure>
					<!-- Post content-->
					<section class="mb-5">
						<pre style="white-space:pre-wrap;"><span id="contentSpan"></span></pre>
					</section>
				</article>

				<br>

					<div class="form-group mb-3"
						style="padding-left: 20px; padding-right: 20px">
						<div class="text-right">
							<div id="communityViewDiv1">
							<c:if test="${singoOnoff==0 || singoOnoff==undifined }">
								<button class="btn btn-outline-danger mb-3 singoBtn1" type="button"
									style="float: right; margin-right: 20px;" onclick="likesingo(2)">
									<i class="fas fa-ban"></i> 신고
								</button>
							</c:if>
								<c:if test="${singoOnoff== 1 }">
								<button class="btn btn-danger mb-3 singoBtn1" type="button"
									style="float: right; margin-right: 20px;" onclick="likesingo(2)">
									<i class="fas fa-ban"></i> 신고
								</button>
							</c:if>
							
								<c:if test="${onoff==0 || onoff==undifined }">
									<button class="btn btn-outline-primary mb-3" type="button"
										style="float: right; margin-right: 20px;" onclick="likesingo(1)">
										<i class="far fa-thumbs-up"></i> 좋아요
									</button>
								</c:if>
								<c:if test="${onoff==1 }">
									<button class="btn btn-primary mb-3" type="button"
										style="float: right; margin-right: 20px;" onclick="likesingo(1)">
										<i class="far fa-thumbs-up"></i> 좋아요
									</button>
								</c:if>
							</div>
							
							<c:if test="${myPage == 1 }">
								<button class="btn btn-outline-primary mb-3"
								type="button" id="myBoardListBtn"
								style="float: left; margin-right: 20px;" onclick="history.back(-1)">
								목록보기</button>
							
							</c:if>
							
							<c:if test="${myPage != 1 }">
								<button class="btn btn-outline-primary mb-3"
								type="button"
								style="float: left; margin-right: 20px;" onclick="location.href='community_List?pg=${pg}&com=0&category=${category }'">목록보기</button>
							</c:if>
							
							
							
							<div id="communityViewDiv2">
								<button class="btn btn-outline-info mb-3"
									style="float: left; margin-right: 20px;" onclick="mode(1)">수정하기</button>
								<button class="btn btn-outline-danger mb-3"
									style="float: left; margin-right: 20px;" onclick="mode(2)">삭제하기</button>
							</div>
						</div>
					</div>
				</div>
</form>				
				
				
				<br>
				<br>
				<br>
				<br>


					
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
			
			
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/simriTest/js/community_View.js"></script>
<script src="/simriTest/js/community_Like.js"></script>
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

