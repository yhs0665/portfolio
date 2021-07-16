<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="modifyForm" method="post" action="/simriTest/member/modify">
   <div class="card pt-2 ps-5 pb-5">
      <div class="card-body">
      	<div class="card-title fs-4">회원정보 수정</div>
      </div>
      <div class="col-9"><hr></div>
      <div class="card-body">
         <div class="row">
            <div style="width: 500px;">
               <div class="input-group">
                  <span class="input-group-text">이름</span>
                 <input id="name" name="name" type="text" class="form-control" value="${memberDTO.name }" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
               </div>
                  <div id="nameDiv"></div>
            </div>
         </div>
      </div>
      <div class="card-body">
         <div class="row">
            <div style="width: 500px;">
               <div class="input-group ">
                  <span class="input-group-text" id="inputGroup-sizing-default" >닉네임</span>
                  <input id="nickname"  name="nickname" type="text" class="form-control" value="${memberDTO.nickname }"  readonly aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                  <button type="button" id="nicknameChangeBtn" class="btn btn-warning btn-sm font-monospace;" style=" border-top-right-radius:5px;  border-bottom-right-radius:5px; ">닉네임 변경권 사용</button>
                  		<input type="hidden" id="check" value="${memberDTO.changeNick }">
               </div>
                  <div id="nicknameDiv"></div>
            </div>
         </div>
      </div>
      <div class="card-body">
         <div class="row">
            <div style="width: 500px;">
               <div class="input-group ">
                  <span class="input-group-text" id="inputGroup-sizing-default">이메일</span>
                  <input id="email"  name="email" type="text" class="form-control" value="${memberDTO.email }"  aria-label="Sizing example input" readonly aria-describedby="inputGroup-sizing-default">
               </div>
                <div id="emailDiv"></div>
            </div>
         </div>
      </div>
      <div class="card-body">
         <div class="row">
            <div style="width: 370px;">
               <div class="input-group ">
                  <c:if test="${memberDTO.joinType == 'normal'}">
                  	 <span class="input-group-text" id="inputGroup-sizing-default">비밀번호</span>
                  	 <input type="password" name="pwd" id="pwd" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                  </c:if>
               </div>
                <div id="pwdDiv"></div>
            </div>
         </div>
      </div>
      <div class="card-body">
         <div class="row">
            <div style="width: 370px;">
               <div class="input-group ">
   	          	  <c:if test="${memberDTO.joinType == 'normal' }">
   	          	  	 <span class="input-group-text" id="inputGroup-sizing-default">비밀번호 확인</span>
                  	 <input type="password" id="repwd" name="repwd" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                  </c:if>
               </div>
                <div id="repwdDiv"></div>
            </div>
         </div>
      </div>
      <div style="width: 500px;">
         <div class="card mt-3 border-light mtdfas-4">
            <div class="col mb-2" style="text-align: center;">
         <div class="gap-2 col-1.5 mx-auto">
            <button id="modifyBtn" class="btn btn-outline-info" type="button">수정하기</button>
            <button class="btn btn-outline-info" type="button" id="exitBtn">탈퇴하기</button>
         </div>
            </div>
         </div>
      </div>
   </div>
   </form>

	