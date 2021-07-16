<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
	<form id="totalSearchListForm" method="post">
		<input type="hidden" id="pg" name="pg" value="${pg }">
		<input type="hidden" id="seq" name="seq">
		<input type="hidden" id="category" name="category">
		<input type="hidden" id="memberPoint" name="memberPoint" value="${memberPoint }">
		<input type="hidden" id="boardPoint" name="boardPoint">
		<input type="hidden" id="totalKeyword" name="totalKeyword" value="${totalKeyword }">
		<input type="hidden" id="searchMemberPoint" name="searchMemberPoint" value="${memberPoint }">
			<!-- Blog entries-->
			<div class="col-lg-12">
				<div class="card mb-8 bg-transparent border-light"> <!-- card: card레이아웃, border-light: border 색  -->
					<div class="card-header h-500 fs-2 bg-transparent" >"${totalKeyword }"의  검색 결과</div> <!-- fs-2 : fontsize-2  -->
                    <div class="totSearchList"></div>
                                                      
				</div>


				<!-- 맨위로 버튼 -->
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
				
			</div>
		</nav>
			
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/simriTest/js/totalSearch.js"></script>
<script type="text/javascript">
function simriTestPaging(pg){
	 var totalKeyword = document.getElementById('totalKeyword').value;
	   if(totalKeyword==''){
		   location.href = '/simriTest/index.jsp';
	   }else{
	      $('#searchPg').val(pg);
	      $('#totalSearchBtn').trigger('click');//강제 이벤트를 발생         
	      //검색 버튼을 눌렀을 때 1페이지 부터 다시 검색할수 있도록 pg를 바꿔주어야 한다.
	      $('#searchPg').val(1);
	   }
}
</script>
