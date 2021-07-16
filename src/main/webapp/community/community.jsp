<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Page content-->
<form id="communityMain" method="post" action="/simriTest/community/community_View">
<input type="hidden" id="seq" name="seq">
<input type="hidden" id="category" name="category">
<input type="hidden" id="pg" name="pg" value="1">
<input type="hidden" id="com" name="com" value="0">
<input type="hidden" id="memId" name="memId" value="${memId }">
		<!-- Blog entries-->
		<div class="col-lg-12">
			<!-- 게시판 미리보기 부분 -->
			<div class="container">
				<div class="row">
					
					<div class="col-lg-12 mb-3">
						<div class="card shadow">
							<div class="card-body">
								<h4 class="card-title" style="text-align:center;">[공지사항]</h4>
								<table class="table table-hover" id='notice_boardList'>
									<thead>
										<tr>
											<td class="text-center w-25">글번호</td>
											<td>제목</td>
											<td class="text-center w-25 d-xl-table-cell">작성날짜</td>
										</tr>
									</thead>
									<tbody >
									
									</tbody>
								</table>
								
							</div>
						</div>
					</div>
				
					<div class="col-lg-6 mb-3">
						<div class="card shadow">
							<div class="card-body">
								<h4 class="card-title">[통합 게시글]</h4>
								<table class="table table-hover" id='total_boardList'>
									<thead>
										<tr>
											<td class="text-center w-25">글번호</td>
											<td>제목</td>
											<td class="text-center w-25 d-xl-table-cell">작성날짜</td>
										</tr>
									</thead>
									<tbody >
									
									</tbody>
								</table>
								<button type="button" class="btn btn-outline-primary"
									style="float: right;" onclick="location.href='/simriTest/community/community_List?com=0&category=0'">더보기</button>
							</div>
						</div>
					</div>
					
					<div class="col-lg-6">
						<div class="card shadow">
							<div class="card-body">
								<h4 class="card-title">유머 게시판</h4>
								<table class="table table-hover" id='humor_boardList'>
									<thead>
										<tr>
											<td class="text-center w-25">글번호</td>
											<td>제목</td>
											<td class="text-center w-25  d-xl-table-cell">작성날짜</td>
										</tr>
									</thead>
									<tbody>
							
									</tbody>
								</table>
								<button type="button" class="btn btn-outline-primary"
									style="float: right;" onclick="location.href='/simriTest/community/community_List?com=0&category=6'">더보기</button>
							</div>
						</div>
					</div>
					
					<div class="col-lg-6" style="margin-top: 20px">
						<div class="card shadow">
							<div class="card-body">
								<h4 class="card-title">연애 게시판</h4>
								<table class="table table-hover" id='love_boardList'>
									<thead>
										<tr>
											<td class="text-center w-25">글번호</td>
											<td>제목</td>
											<td class="text-center w-25  d-xl-table-cell">작성날짜</td>
										</tr>
									</thead>
							 		<tbody>
			
									</tbody> 
								</table>
								<button type="button" class="btn btn-outline-primary"
									style="float: right;" onclick="location.href='/simriTest/community/community_List?com=0&category=7'">더보기</button>
							</div>
						</div>
					</div>
					
					<div class="col-lg-6 mb-3" style="margin-top: 20px">
						<div class="card shadow">
							<div class="card-body">
								<h4 class="card-title">고민&상담 게시판</h4>
								<table class="table table-hover" id='QnA_boardList'>
									<thead>
										<tr>
											<td class="text-center w-25">글번호</td>
											<td>제목</td>
											<td class="text-center w-25  d-xl-table-cell">작성날짜</td>
										</tr>
									</thead>
								 	<tbody>
								
									</tbody>
								</table>
								<button type="button" class="btn btn-outline-primary"
									style="float: right;" onclick="location.href='/simriTest/community/community_List?com=0&category=8'">더보기</button>
							</div>
						</div>
					</div>
					
				</div>
			</div>
		</div>
</form>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
//------------------------------------------------------------------------getCommunity_Notice(공지사항)
$(document).ready(function() {
	$.ajax({
		type : 'post',
		url : '/simriTest/community/getCommunity_Notice',
		dataType : 'json', 
		success : function(data) {
			
			$.each(data.list,function(index,items){
				 $('<tr/>').append($('<td/>',{
	            	   align:'center',
	                   text:items.seq,
	              })).append($('<td/>',{
	                  
		             }).append($('<a/>',{
			           href: '#',
			           text: items.subject,
			           id: 'subjectH',
			           class: 'subject_9'+items.seq,
			           style: ' text-overflow:ellipsis; overflow:hidden; display: -webkit-box; -webkit-line-clamp: 1;  -webkit-box-orient: vertical; word-wrap:break-word;'
				        }))
				        
			     ).append($('<td/>',{
	                   align:'center',
	                   text:items.comLogtime
	             })).appendTo($('#notice_boardList'));
				 
			 $('.subject_9'+items.seq).click(function(){
				 if(data.id != null){
				 $.cookie('totalCommunityCookie', 'babu', {expires:1, path: '/' });
				 $('#seq').val(items.seq);
				 $('#category').val(9);
				 $('#communityMain').submit();
				 }else {
					 alert("로그인이 필요한 서비스 입니다")
				 }
             });
				 
			});
			

		},
		error : function(err) {
			console.log(err);
		}
	});
});

//-------------------------------------------------------------------------getCommunity_Main(통합 게시글)
$(document).ready(function() {
	$.ajax({
		type : 'post',
		url : '/simriTest/community/getCommunity_Main',
		dataType : 'json',
		success : function(data) {
			$.each(data.list,function(index,items){
				 $('<tr/>').append($('<td/>',{
	            	   align:'center',
	                   text:items.seq,
	              })).append($('<td/>',{
	                  
		             }).append($('<a/>',{
			           href: '#',
			           text: items.subject,
			           id: 'subjectH',
			           class: 'subject_0'+items.seq,
			           style: ' text-overflow:ellipsis; overflow:hidden; display: -webkit-box; -webkit-line-clamp: 1;  -webkit-box-orient: vertical; word-wrap:break-word;'
				        }))
				        
			     ).append($('<td/>',{
	                   align:'center',
	                   text:items.comLogtime
	             })).appendTo($('#total_boardList'));
				 

			 $('.subject_0'+items.seq).click(function(){
				 var red = '[팔레트]빨강';
               	 var blue = '[팔레트]파랑';
               	 var green = '[팔레트]초록';
               	 var yellow = '[팔레트]노랑';
               	
				 if(data.id == null){
					 alert("로그인이 필요한 서비스 입니다");
					 return;
				 }else if(data.memPalette=="red"){
       					if(items.category == yellow || items.category == blue || items.category == green){
       						alert("소속 팔레트 게시글만 볼 수 있습니다");
       						return;
       					}else{
       						$.cookie('totalCommunityCookie', 'babu', {expires:1, path: '/' });
       						$('#seq').val(items.seq);
       						$('#category').val(0);
       						$('#communityMain').submit();
       					}
       				}else if(data.memPalette=="blue"){
       					if(items.category == yellow || items.category == red || items.category == green){
       						alert("소속 팔레트 게시글만 볼 수 있습니다");
       						return;
       					}else{
       						$.cookie('totalCommunityCookie', 'babu', {expires:1, path: '/' });
       						$('#seq').val(items.seq);
       						$('#category').val(0);
       						$('#communityMain').submit();
       					}
       				}else if(data.memPalette=="green"){
       					if(items.category == yellow || items.category == blue || items.category == red){
       						alert("소속 팔레트 게시글만 볼 수 있습니다");
       						return;
       					}else{
       						$.cookie('totalCommunityCookie', 'babu', {expires:1, path: '/' });
       						$('#seq').val(items.seq);
       						$('#category').val(0);
       						$('#communityMain').submit();
       					}
       				}else if(data.memPalette=="yellow"){
       					if(items.category == red || items.category == blue || items.category == green){
       						alert("소속 팔레트 게시글만 볼 수 있습니다");
       						return;
       					}else{
       						$.cookie('totalCommunityCookie', 'babu', {expires:1, path: '/' });
       						$('#seq').val(items.seq);
       						$('#category').val(0);
       						$('#communityMain').submit();
       					}
       				}else if(data.memPalette=="white"){
       					if(items.category == yellow || items.category == blue || items.category == green || items.category == red){
       						alert("소속 팔레트가 없어서 볼 수 없습니다");
       						return;
       					}else{
       						$.cookie('totalCommunityCookie', 'babu', {expires:1, path: '/' });
       						$('#seq').val(items.seq);
       						$('#category').val(0);
       						$('#communityMain').submit();
       					}
       				}else{
       					$.cookie('totalCommunityCookie', 'babu', {expires:1, path: '/' });
    					$('#seq').val(items.seq);
    					$('#category').val(0);
    					$('#communityMain').submit();
       				}
             });
				 
			});
		},
		error : function(err) {
			console.log(err);
		}
	});
});


//-------------------------------------------------------------------------getCommunity_Humor(유머 게시판)
$(document).ready(function() {
	$.ajax({
		type : 'post',
		url : '/simriTest/community/getCommunity_Humor',
		dataType : 'json',
		success : function(data) {
			
			$.each(data.list,function(index,items){
				 $('<tr/>').append($('<td/>',{
	            	   align:'center',
	                   text:items.seq,
	              })).append($('<td/>',{
	                  
		             }).append($('<a/>',{
			           href: '#',
			           text: items.subject,
			           id: 'subjectH',
			           class: 'subject_6'+items.seq,
			           style:'text-overflow:ellipsis; overflow:hidden; display: -webkit-box; -webkit-line-clamp: 1;  -webkit-box-orient: vertical; word-wrap:break-word;'
				        }))
				        
			     ).append($('<td/>',{
	                   align:'center',
	                   text:items.comLogtime
	             })).appendTo($('#humor_boardList'));
				 

			 $('.subject_6'+items.seq).click(function(){
				 if(data.id == null){
					 alert("로그인이 필요한 서비스 입니다");
					 return;
				 }else {
					$.cookie('totalCommunityCookie', 'babu', {expires:1, path: '/' });
					$('#seq').val(items.seq);
					$('#category').val(6);
					$('#communityMain').submit();
				 }
          
             });
				 
			});
			

		},
		error : function(err) {
			console.log(err);
		}
	});
});

//-------------------------------------------------------------------------getCommunity_Love(연애 게시글)
$(document).ready(function() {
	$.ajax({
		type : 'post',
		url : '/simriTest/community/getCommunity_Love',
		dataType : 'json',
		success : function(data) {
			
			$.each(data.list,function(index,items){
				 $('<tr/>').append($('<td/>',{
	            	   align:'center',
	                   text:items.seq,
	              })).append($('<td/>',{
	                  
		             }).append($('<a/>',{
			           href: '#',
			           text: items.subject,
			           id: 'subjectH',
			           class: 'subject_7'+items.seq,
			           style: ' text-overflow:ellipsis; overflow:hidden; display: -webkit-box; -webkit-line-clamp: 1;  -webkit-box-orient: vertical; word-wrap:break-word;'
				        }))
				        
			     ).append($('<td/>',{
	                   align:'center',
	                   text:items.comLogtime
	             })).appendTo($('#love_boardList'));
				 

			 $('.subject_7'+items.seq).click(function(){
				 if(data.id == null){
					 alert("로그인이 필요한 서비스 입니다");
					 return;
				 }else {
					$.cookie('totalCommunityCookie', 'babu', {expires:1, path: '/' });
					$('#seq').val(items.seq);
					$('#category').val(7);
					$('#communityMain').submit();

				 }
             });
				 
			});
			

		},
		error : function(err) {
			console.log(err);
		}
	});
});
//-------------------------------------------------------------------------getCommunity_QnA(고민&상담 게시글)
$(document).ready(function() {
	$.ajax({
		type : 'post',
		url : '/simriTest/community/getCommunity_QnA',
		dataType : 'json',
		success : function(data) {
			
			$.each(data.list,function(index,items){
				 $('<tr/>').append($('<td/>',{
	            	   align:'center',
	                   text:items.seq,
	              })).append($('<td/>',{
	                  
		             }).append($('<a/>',{
			           href: '#',
			           text: items.subject,
			           id: 'subjectH',
			           class: 'subject_8'+items.seq,
			           style: ' text-overflow:ellipsis; overflow:hidden; display: -webkit-box; -webkit-line-clamp: 1;  -webkit-box-orient: vertical; word-wrap:break-word;'
				        }))
				        
			     ).append($('<td/>',{
	                   align:'center',
	                   text:items.comLogtime
	             })).appendTo($('#QnA_boardList'));
				 

			 $('.subject_8'+items.seq).click(function(){
				 if(data.id == null){
					 alert("로그인이 필요한 서비스 입니다");
					 return;
				 }else {
					$.cookie('totalCommunityCookie', 'babu', {expires:1, path: '/' });
					$('#seq').val(items.seq);
					$('#category').val(8);
					$('#communityMain').submit();
				 }
             });
				 
			});
			

		},
		error : function(err) {
			console.log(err);
		}
	});
});
</script>