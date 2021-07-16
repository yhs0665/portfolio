<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- Blog entries-->
<div class="col-lg-12">
	<div class="card mb-4">
		<div class="card-header ">글수정</div>

		<form id="communityModifyForm" name="communityModifyForm"  method="post"  enctype="muLtipart/form-data" action="community_Modify">
		
		<input type="hidden" name="seq" id="seq" value="${seq }">
		<input type="hidden" name="pg" id="pg" value="${pg }">
		<input type="hidden" name="palette" id="palette" value="${memPalette }">
		<input type="hidden" name="pageCategory" id="pageCategory" value="${category }">
		
		
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
				<label for="board_subject">제목</label> 
				<input type="text" id="subject" name="subject" class="form-control" />
				<div id="subjectDiv"></div>
			</div>
			
			<div class="form-group mb-3"
				style="padding-left: 20px; padding-right: 20px">
				<label for="board_content">내용</label>
				<textarea id="content" name="content" class="form-control" rows="10" style="resize: none; height: 325px;"></textarea>
				<div id="contentDiv"></div>
			</div>
			
			<div class="form-group mb-3" style="padding-left: 20px; padding-right: 20px">
				<label for="board_file">첨부 이미지</label> 
				<input type="file" id="file" name="img" title="첨부파일" accept="image/gif, image/jpeg, image/png" class="form-control" />
				<input id="fileInput" name="image" readonly style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;">
			</div>
			
			<div class="form-group mb-3" style="padding-left: 20px; padding-right: 20px">
				<div class="text-right">
					<button type="reset" class="btn btn-outline-primary mb-3" style="float: right; margin-right: 20px;">다시 쓰기</button>
					<input type="button" class="btn btn-outline-primary mb-3" style="float: right; margin-right: 20px;" id="communityModifyBtn" value="수정하기">
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
 	<script>
 	$(function(){
 		
 		$.ajax({
 			type: 'post',    /* 한사람의 글을 잡아오는거  */
 			url: '/simriTest/community/getCommunity',
 			data: 'seq='+$('input[name=seq]').val(),
 			dataType: 'json',
 			success: function(data){
 				$('#category').val(data.communityDTO.category);
 				$('#subject').val(data.communityDTO.subject);
 				$('#content').val(data.communityDTO.content);		
 				$('#fileInput').val(data.communityDTO.image);		
 				$('#fileInput').html(data.communityDTO.image);		
 			},
 			error: function(err){
 				console.log(err);
 			}
 		});
 	});
 	
 	
 	$('#communityModifyBtn').click(function(){
 		var red = '[팔레트]빨강';
 		var blue = '[팔레트]파랑';
 		var green = '[팔레트]초록';
 		var yellow = '[팔레트]노랑';
 		
 		$('#subjectDiv').empty();
 		$('#contentDiv').empty();
 		
 		
 	    if($("#category").val() == 'none') {
 			alert("카테고리를 정해주세요")
 			return false;
 			
 		}else if($("#palette").val() == 'yellow'){
 			if($("#category option:selected").val() == red || $("#category option:selected").val() == blue || $("#category option:selected").val() == green){
 				alert("소속 팔레트 게시판만 이용 하실 수 있습니다");
 				return;
 			}
 		}else if($("#palette").val() == 'blue'){
 			if($("#category option:selected").val() == red || $("#category option:selected").val() == yellow || $("#category option:selected").val() == green){
 				alert("소속 팔레트 게시판만 이용 하실 수 있습니다");
 				return;
 			}
 		}else if($("#palette").val() == 'red'){
 			if($("#category option:selected").val() == yellow || $("#category option:selected").val() == blue || $("#category option:selected").val() == green){
 				alert("소속 팔레트 게시판만 이용 하실 수 있습니다");
 				return;
 			}
 		}else if($("#palette").val() == 'green'){
 			if($("#category option:selected").val() == red || $("#category option:selected").val() == blue || $("#category option:selected").val() == yellow){
 				alert("소속 팔레트 게시판만 이용 하실 수 있습니다");
 				return;
 			}
 		}else if($("#palette").val() == 'white'){
 			if($("#category option:selected").val() == red || $("#category option:selected").val() == blue || $("#category option:selected").val() == green || $("#category option:selected").val() == yellow){
 				alert("소속이 없습니다. 상점에서 구매해주세요");
 				return;
 			}
 		}
 	    
 		if($('#subject').val() == '') {
 			$('#subjectDiv').html("제목 입력");
 			$('#subjectDiv').css('color', 'red');
 			$('#subjectDiv').css('font-size', '10pt');
 			$('#subjectDiv').css("font-weight", 'bold');
 		}else if($('#content').val() == '') {
 			$('#contentDiv').html("내용 입력");
 			$('#contentDiv').css('color', 'red');
 			$('#contentDiv').css('font-size', '10pt');
 			$('#contentDiv').css("font-weight", 'bold');
 		}else{
 			
 			 $('#communityModifyForm').submit();
 		}
 		
 	});
 	
 	</script>
