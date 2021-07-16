<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>

.cssCategory span{
float: left;
margin-left: 20px;
margin-top: 20px;
font-size: 20px;
}
</style>

<!-- Blog entries-->
<div class="col-lg-12">
	<div class="card mb-4">

		<form name="communityListForm" id="communityListForm" method="post" action="/simriTest/community/community_View">
			<input type="hidden" id="pg" name="pg" value="${pg }">
			<input type="hidden" id="seq" name="seq">
			<input type="hidden" id="com" name="com" value="0">
			<input type="hidden" id="category" name="category" value="${category }">
			
			<span class="cssCategory">
			<c:if test="${category == 0 }">
			<span>[통합 게시글]</span>
			</c:if>
			<c:if test="${category == 1 }">
			<span>[전체 게시글] 팔레트</span>
			</c:if>
			<c:if test="${category == 2 }">
			<span>[팔레트] 빨강</span>
			</c:if>
			<c:if test="${category == 3 }">
			<span>[팔레트] 파랑</span>
			</c:if>
			<c:if test="${category == 4 }">
			<span>[팔레트] 초록</span>
			</c:if>
			<c:if test="${category == 5 }">
			<span>[팔레트] 노랑</span>
			</c:if>
			<c:if test="${category == 6 }">
			<span>유머 게시판</span>
			</c:if>
			<c:if test="${category == 7 }">
			<span>연애 게시판</span>
			</c:if>
			<c:if test="${category == 8 }">
			<span>고민&상담 게시판</span>
			</c:if>
			<c:if test="${category == 9 }">
			<span>공지사항</span>
			</c:if>
			</span>
			
			<c:if test="${sessionScope.memId != null && category !=9 }">
			<button type="button" class="btn btn-outline-primary"
				style="float: right; margin-right: 20px; margin-top: 15px;"
				onclick="location.href='/simriTest/community/community_WriteForm?com=0&category=${category }'">글쓰기</button>
			</c:if>
			<br>
			<br>
			<br>

			<table id="communityListTable" border="1" bordercolor="lightgray" cellspacing="0"
				cellpadding="5" frame="hsides" rules="rows" class="table table-hover table-sm table-responsive">

				<tr>
					<th align="center" width="20"></th>
					<c:choose>
					<c:when test="${category==0||category==1}">
					<th align="center" width="130" class="text-center my-auto">카테고리</th>
					</c:when>
					<c:otherwise>
					<th align="center" width="100" class="text-center">글번호</th>
					</c:otherwise>

					</c:choose>
					<th align="center" width="200" class="text-center">이미지</th>
					<th align="center" width="300" class="text-center">제목</th>
					<th align="center" width="150" class="text-center">작성자</th>
					<th align="center" width="150" class="text-center">작성일</th>
					<th align="center" width="100" class="text-center">조회수</th>
					<th align="center" width="80" class="text-center">좋아요</th>
					<th align="center" width="20"></th>
				</tr>
			</table>
			<br>
		</form>
	


		<!-- Pagination-->
		<nav aria-label="Pagination">
			<div class="text-center">
				<!-- Topbar Search -->
				<ul class="pagination justify-content-center my-4" id="communityPagingDiv"></ul>
				<form class="d-none d-sm-inline-block form-inline navbar-search mb-5" id="communitySearchForm">
					
					<input type="hidden" id="seq" name="seq">
					<input type="hidden" id="category" name="category" value="${category }">
					<input type="hidden" id="com" name="com" value="0">
					<input type="hidden" name="pg" value="1"> 
					
					
					<div class="input-group">
						<select name="searchOption" id="searchOption"
							class="bg-light border-0 small">
							<option value="subject">제목</option>
							<option value="nickName">작성자</option>
						</select> <input type="search" class="form-control bg-light border-0 small"
							placeholder="Search for..." aria-label="Search"
							aria-describedby="basic-addon2" value="${keyword }" id="keyword" name="keyword">
						<div class="input-group-append">
							<button class="btn btn-primary" type="button" id="communitySearchBtn">
								<i class="fas fa-search fa-sm"></i>
							</button>
						</div>
					</div>
				</form>
			</div>
		</nav>
	</div>

	</div>


<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/simriTest/js/community_List.js"></script>
<script>
$(document).ready(function() {
   $.ajax({
      type : 'post',
      url : '/simriTest/community/getCommunity_List',
      data: {
    	  'pg' : $('#pg').val(),
    	  'category' : $('#category').val()
      }, //컨트롤러로부터 넘어온 값,   
      dataType : 'json',
      success : function(data) {
         
         $.each(data.list,function(index,items){
             $('<tr/>').append($('<td/>',{
             
             })).append($('<td/>',{
            	  class:'seq'+items.seq,
            	  align:'center',
            	  style: 'line-height: 3.5em;'
             	
             })).append($('<td/>',{
                 align:'center',
                }).append($('<img/>',{
                   src:'/simriTest/storage/'+items.image,
                   style:'width:100px; height:70px; cursor:pointer;',
                }))
              ).append($('<td/>',{
                 
	             }).append($('<a/>',{
		           href: '#',
		           text: items.subject,
		           id: 'subjectH',
		           class: 'subject_'+items.seq,
		           style: 'line-height: 4em; text-overflow:ellipsis; overflow:hidden; display: -webkit-box; -webkit-line-clamp: 1;  -webkit-box-orient: vertical; word-wrap:break-word;'
			        }))
			        
		     ).append($('<td/>',{
                align:'center',
                text:items.nickName, //원래는 작성자
                style: 'line-height: 4em;'
             })).append($('<td/>',{
                 align:'center',
                 text:items.comLogtime,
                 style: 'line-height: 4em;'
             })).append($('<td/>',{
                align:'center',
                text:items.hit,
                style: 'line-height: 4em;'
             })).append($('<td/>',{
                align:'center',
                text:items.comLike, //원래는 like
                style: 'line-height: 4em;'
             })).append($('<td/>',{
                
             })).appendTo($('#communityListTable'));
				
             if($('#category').val()==0||$('#category').val()==1){
            	 $('.seq'+items.seq).append($('<td/>',{
            		 text:items.category,
            		 style: 'height: 2em; font-size:13px; overflow: hidden; white-space: nowrap;'
            		 
            	 }))
             }else{
            	 $('.seq'+items.seq).append($('<td/>',{
            		 text:items.seq
            	 }))
             }
             //로그인 여부
             $('.subject_'+items.seq).click(function(){
            	var red = '[팔레트]빨강';
            	var blue = '[팔레트]파랑';
            	var green = '[팔레트]초록';
            	var yellow = '[팔레트]노랑';
            		
            	 if(data.memNickname == null) {
 					alert('로그인이 필요한 서비스 입니다');
 				}else if(data.memPalette=="red"){
 					if(items.category == yellow || items.category == blue || items.category == green){
 						alert("소속 팔레트 게시글만 볼 수 있습니다");
 						return;
 					}else{
 						$('#seq').val(items.seq);
 	 					$('#communityListForm').submit();
 					}
 				}else if(data.memPalette=="blue"){
 					if(items.category == yellow || items.category == red || items.category == green){
 						alert("소속 팔레트 게시글만 볼 수 있습니다");
 						return;
 					}else{
 						$('#seq').val(items.seq);
 	 					$('#communityListForm').submit();
 					}
 				}else if(data.memPalette=="green"){
 					if(items.category == yellow || items.category == blue || items.category == red){
 						alert("소속 팔레트 게시글만 볼 수 있습니다");
 						return;
 					}else{
 						$('#seq').val(items.seq);
 	 					$('#communityListForm').submit();
 					}
 				}else if(data.memPalette=="yellow"){
 					if(items.category == red || items.category == blue || items.category == green){
 						alert("소속 팔레트 게시글만 볼 수 있습니다");
 						return;
 					}else{
 						$('#seq').val(items.seq);
 	 					$('#communityListForm').submit();
 					}
 				}else if(data.memPalette=="white"){
 					if(items.category == yellow || items.category == blue || items.category == green || items.category == red){
 						alert("소속 팔레트가 없어서 볼 수 없습니다");
 						return;
 					}else{
 						$('#seq').val(items.seq);
 	 					$('#communityListForm').submit();
 					}
 				}else {
 					$('#seq').val(items.seq);
 					$('#communityListForm').submit();
 				   //location.href = '/simriTest/community/community_View?seq='+items.seq+'&pg='+$('#pg').val()+'&com=0';
 				}
             });
             
           //댓글수
             if(items.reply!=0){
                $('.subject_'+items.seq).html(items.subject+'('+items.reply+')');
             }
             
          });//each
               //페이징처리
               $('#communityPagingDiv').html(data.communityPaging.pagingHTML);
      },
      error : function(err) {
    	 alert("실패")
         console.log(err);
      }
   });
});
</script>


<script type="text/javascript">
function communityPaging(pg){
	 var keyword = document.getElementById('keyword').value;
	   if(keyword==''){
	      location.href = 'community_List?pg='+pg+'&com=0&category='+${category};
	   }else{
	      
	      $('input[name=pg]').val(pg); //form 안에 pg의 값이 1로 고정되어 있기 때문
	         $('#communitySearchBtn').trigger('click');//강제 이벤트를 발생         
	         
	         //검색 버튼을 눌렀을 때 1페이지 부터 다시 검색할수 있도록 pg를 바꿔주어야 한다.
	         $('input[name=pg]').val(1);
	   }
}
</script>

