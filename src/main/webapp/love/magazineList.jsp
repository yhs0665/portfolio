<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   
<form name="magazineListForm" id="magazineListForm" method="post" action="/simriTest/love/magazineContent">
			<input type="hidden" id="pg" name="pg" value="${pg }">
			<input type="hidden" id="seq" name="seq">
			<input type="hidden" id="memberPoint" name="memberPoint" value="${memberPoint }">
			<input type="hidden" id="boardPoint" name="boardPoint">
			
			
			<!-- Blog entries-->
			<div class="col-lg-12">
				<div class="card mb-8 bg-transparent border-light"> <!-- card: card레이아웃, border-light: border 색  -->
					<div class="card-header h-500 fs-2 bg-transparent">연애 칼럼</div> <!-- fs-2 : fontsize-2  -->
					
					<div id="main">
                    </div>
				</div>

				<!-- 더보기 / 맨위로 버튼 -->
                <div class="card mb-4 border-0">
                    <div class="col mb-2" style="text-align-last: right;">
						<div class="gap-2 col-1.5 mx-auto">
							<button class="btn btn-outline-info" onclick="location.href='#'" type="button">맨위로</button>
						</div>
                    </div>
                </div>
			</div>
</form>
			<!-- Pagination-->
		<nav aria-label="Pagination">
			<div class="text-center">
				<!-- Topbar Search -->
				<ul class="pagination justify-content-center my-4" id="simriTestPagingDiv"></ul>
				<form class="d-none d-sm-inline-block form-inline navbar-search mb-5" id="magazineSearchForm" method="post" action="/simriTest/love/magazineContent">
					
					<input type="hidden" class="seq"  name="seq">
					<input type="hidden" class="category"  name="category" value="${category }">
					<input type="hidden" class="SearchmemberPoint"  name="SearchmemberPoint" value="${memberPoint }">
					<input type="hidden" class="pg" name="pg" value="1">
					<input type="hidden" class="boardPoint" name="boardPoint"> 
					
					
					<div class="input-group">
						<select name="searchOption" id="searchOption"
							class="bg-light border-0 small">
							<option value="subject">제목</option>
							<option value="content">내용</option>
						</select> <input type="search" class="form-control bg-light border-0 small"
							placeholder="Search for..." aria-label="Search"
							aria-describedby="basic-addon2" value="${keyword }" id="keyword" name="keyword">
						<div class="input-group-append">
							<button class="btn btn-primary" type="button" id="magazineSearchBtn">
								<i class="fas fa-search fa-sm"></i>
							</button>
						</div>
					</div>
				</form>
			</div>
		</nav>
			
		<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script src="/simriTest/js/magazine_List.js"></script>
		<script src="/simriTest/js/magazine_ListSearch.js"></script>

<script type="text/javascript">
function simriTestPaging(pg){
	 var keyword = document.getElementById('keyword').value;
	   if(keyword==''){
		   location.href = 'magazineList?pg='+pg;
	   }else{
	      
	      $('input[name=pg]').val(pg); //form 안에 pg의 값이 1로 고정되어 있기 때문
	         $('#magazineSearchBtn').trigger('click');//강제 이벤트를 발생         
	         
	         //검색 버튼을 눌렀을 때 1페이지 부터 다시 검색할수 있도록 pg를 바꿔주어야 한다.
	         $('input[name=pg]').val(1);
	   }
}
</script>