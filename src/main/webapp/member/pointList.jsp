<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<input type="hidden" id="pointPg" value=${pointPg }>
<div class="col-lg-12">
	<div class="card border-5">
		<div class="card-body mb-2">
			<div class="d-flex mb-2">
				<!-- Parent comment-->
				<div class="col-md-3 my-auto text-center fs-4 lh-lg">
					<strong>현재 포인트</strong> <br> <span class="fs-4">${memberDTO.point} p</span>
				</div>
				<div class="col-md-3 my-auto text-center fs-4 lh-lg">
					<strong>총 적립 포인트</strong> <br> <span class="fs-4">${totalPointHistoryDTO.totalPlusPoint} p</span>
				</div>
				<div class="col-md-3 my-auto text-center fs-4 lh-lg">
					<strong>사용한 포인트</strong> <br> <span class="fs-4">${totalPointHistoryDTO.totalMinusPoint} p</span>
				</div>
				<div class="col-md-3 my-auto text-center fs-4 lh-lg">
					<br>
				</div>
				<div class="col-md-1 my-auto text-center"></div>
				<div class="col-md-2 my-auto text-center fs-3 lh-lg">
					<strong></strong> <br> <span class="fs-4"></span>
				</div>
			</div>
		</div>
	</div>
</div>
<br>
<br>


<div class="card-body mx-auto ms-5 me-5">
	<table class="table table-hover text-center caption-top" id="pointListTable">
		<caption>포인트 내역</caption>

		
		<thead>
			<tr>
				<th scope="col" style="width: 20%;">적립날짜</th>
				<th scope="col" style="width: 40%;">내역</th>
				<th scope="col" style="width: 40%;">포인트</th>
			</tr>

		</thead>
		<tbody id="tbody">
			
		</tbody>
	</table>

</div>
		<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center my-4" id="pointListPaging">
				</ul>
		</nav>	
