<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- Blog entries-->
<div class="col-lg-12">
	<div class="card mb-4">
		<div class="card-header ">글쓰기</div>

		<form id="communityWriteForm" method="post" enctype="muLtipart/form-data" action="community_Write">
		
		<input type="hidden" id="palette" name="palette" value="${palette }"/>
		<input type="hidden" id="pgCategory" name="pgCategory" value="${category }"/>
		
			<div class="form-group mb-3 mt-3"
				style="padding-left: 20px; padding-right: 20px">
				<select name="category" id="category"
					class="bg-light border-1 "
					style="width: 100%; border-color: lightgray">
					<option value="none" selected>=== 선택 ===</option>
					<option value="[팔레트]빨강" id="red">[팔레트]빨강</option>
					<option value="[팔레트]파랑">[팔레트]파랑</option>
					<option value="[팔레트]초록">[팔레트]초록</option>
					<option value="[팔레트]노랑" id="yellow">[팔레트]노랑</option>
					<option value="유머 게시판">유머 게시판</option>
					<option value="연애 게시판">연애 게시판</option>
					<option value="고민&상담 게시판">고민&상담 게시판</option>
				</select>
			</div>

			<div class="form-group mb-3"
				style="padding-left: 20px; padding-right: 20px">
				<label for="board_subject">제목</label> <input type="text"
					id="subject" name="subject" class="form-control" />
				<div id="subjectDiv"></div>
			</div>
			
			<div class="form-group mb-3"
				style="padding-left: 20px; padding-right: 20px">
				<label for="board_content">내용</label>
				<textarea id="content" name="content"
					class="form-control" rows="10" style="resize: none; height: 325px;"></textarea>
				<div id="contentDiv"></div>
			</div>
			
			<div class="form-group mb-3"
				style="padding-left: 20px; padding-right: 20px">
				<label for="board_file">첨부 이미지</label> <input type="file"
					name="img" class="form-control"
					/>
			</div>
			
			<div class="form-group mb-3"
				style="padding-left: 20px; padding-right: 20px">
				<div class="text-right">
					<button type="reset" class="btn btn-outline-primary mb-3"
						style="float: right; margin-right: 20px;">다시 쓰기</button>
					<input type="button" class="btn btn-outline-primary mb-3"
						style="float: right; margin-right: 20px;" id="communityWriteBtn" value="등록하기">
				</div>
			</div>
		</form>

	</div>

	<div class="card mb-4">
		<img class="card-img-top" src="/simriTest/img/swiper-1.jpg"
			alt="Card image cap">
	</div>

</div>

   <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
 	<script src="/simriTest/js/community.js"></script>
