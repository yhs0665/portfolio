<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<form name="simriTestForm" id="simriTestForm" method="post" action="/simriTest/simriTest/simriTestRead">
	<input type="hidden" name="seq" id="seq" value="${seq }">
	
		<!-- Blog entries-->
		<div class="col-lg-12">
			<div class="card mb-8 bg-transparent border-light">
				<!-- card: card레이아웃, border-light: border 색  -->
				<div class="card-header h-500 fs-2 bg-transparent mb-4">심리테스트</div>
				<!-- fs-2 : fontsize-2  -->

				<div class="card-body" id="main">
					<!-- Featured blog post-->
<!-- 1	-----------------------------------------------------	 -->
					
				</div>

			</div>

			<!-- 더보기 / 맨위로 버튼 -->
			<div class="card mb-4 border-0">
				<div class="col mb-2" style="text-align-last: right;">
					<div class="gap-2 col-1.5 mx-auto">
						<button class="btn btn-outline-info" id="load" type="button">더보기</button>
						<button class="btn btn-outline-info" onclick="location.href='#'" type="button">맨위로</button>
					</div>
				</div>
			</div>
		</div>
	</form>
		<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script src="/simriTest/js/simriTest_List.js"></script>
		
