<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 사이드!!!! -->
<!-- 연애심리 랭킹-->

<div class="col-lg-4">
	<!-- Search widget-->
	<form name="loveSideListForm" id="loveSideListForm" method="post"
		action="/simriTest/love/magazineContent">
		<input type="hidden" class="seq" name="seq"> 
		<input type="hidden" class="pg" name="pg" value="${pg }"> 
		<input type="hidden" class="memberPoint" name="memberPoint" value="${memberPoint }"> 
		<input type="hidden" class="boardPoint" name="boardPoint">

		<div class="card mb-4">
			<div class="card-header">연애심리 TOP5</div>
			<div class="card-body">
				<div class="input-group" id="main0"></div>
			</div>
		</div>
	</form>



	<!-- 심리테스트 인기랭킹-->
	<div class="card mb-4">
		<div class="card-header">심리테스트 TOP3</div>
		<div class="card-body">
			<div class="input-group" id="main1">

				<form name="simriSideListForm" id="simriSideListForm" method="post"
					action="/simriTest/simriTest/simriTestRead">
					<input type="hidden" id="seq" name="seq">
					 <input type="hidden" id="pg" name="pg" value="${pg }">

				</form>

			</div>
		</div>
	</div>



	<!-- Side widget-->
	<div class="card mb-4">
		<img class="card-img-top" src="/simriTest/img/ganggo1.jpg"
			alt="Card image cap" style="cursor: pointer;" onclick="location.href='https://www.bitcamp.co.kr/'">
	</div>
</div>

<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script>
$(document).ready(function() {
	   $.ajax({
	      url:'/simriTest/getLoveSide',
	      type: 'post',
	      dataType: 'json',
	      success:function(data){

	         $.each(data.list,function(index,items){
	        	 
	            $('#main0').append($('<div/>',{
	               class: 'card mb-2'
	            }).append($('<div/>',{
	               class: 'row g-0'
	               }).append($('<div/>',{
	                  class: 'col-md-4',
	                  style:'height:100px; overflow: hidden; margin:auto;'
	               }).append($('<img/>',{
	                  src:"/simriTest/storage/"+items.image,
	                  class: 'img-thumbnail',
	                  style:'width: 100%; height:100%; object-fit:cover;',
	                  alt : '5번이미지'
	               }))).append($('<div/>',{
	               class: 'col-md-8'
	               }).append($('<div/>',{
	                  class: 'card-body'
	                  }).append($('<div/>',{
	                     class: 'col mb-2 before'+items.seq,
	                     style: 'text-align-last: right;'
	                     }).append(
	                     ( items.point > 0 ?  
	                     $('<span/>',{
	                        class: 'badge rounded-pill bg-warning point',
	                        text: items.point+'p'
	                     })
	                     : 
	                     $('<span/>',{
	                        class: 'badge rounded-pill bg-warning point',
	                        text: '무료'
	                     })
	                     )

	                     )).append($('<h5/>',{
	                     class: 'card-title'
	                  }).append($('<a/>',{
	                     href: '#',
	                     id: 'subjectH',
	                       class: 'subjectA_'+items.seq,
	                       style:'text-overflow:ellipsis; overflow:hidden; display: -webkit-box; -webkit-line-clamp: 2;  -webkit-box-orient: vertical; word-wrap:break-word;',
	                     text: items.subject
	                  })))
	               )
	            )))

	               // 로그인 여부와 포인트 여부
	               $('.subjectA_'+items.seq).click(function(){
	                      if(data.memNickname == null) {
	                      alert('로그인이 필요한 서비스 입니다');
	                   }else{// 아이디 존재
	                      
	                	   
	                      //그 사람의 포인트 쓴 유무 확인
	                      $.ajax({
	                    	  type: 'post',
	                    	  url: '/simriTest/love/checkPoint',
	                    	  data: {
	                    		  'pg' : $('.pg').val(),
	                    		  'seq' : items.seq
	                    	  },
	                    	  dataType: 'json',
	                    	  success:function(data1){          
	                              $.cookie('loveSideCookie', 'babu', {expires:1, path: '/' });
	                    		  //결제 한번 한 후
	                    		  if(data1.checkPoint==1){
	                            	  $('.seq').val(data1.seq);
	                            	  $('#loveSideListForm').submit();
	                            	  return;
	                    		  }else{
	                    			  //무료 일때
	                    			  if(items.point == 0){
	                    				  $('.seq').val(items.seq);
	                    				  $('.boardPoint').val(items.point);
	                    				  $('#loveSideListForm').submit();
	                    			  }else if(data.memberPoint >= items.point){
	                    				  //처음 결제
	                    				  if(confirm("결제 하시겠습니까?")==true){
	                    					  alert("결제 되었습니다")
	                    					  $('.seq').val(items.seq);
		                            		  $('.boardPoint').val(items.point);
		                            		  $('#loveSideListForm').submit();
	                    				  }else{
	                    					  return;
	                    				  }
	                            	  }else if(data.memberPoint < items.point){
	                            		  alert("포인트 충전 후 사용해 주세요")
	                            	  }
	                              }
	                    	  },
	                    	  error: function(err){
	                    		  alert("실패");
	                    	  }
	                      });
	                      
	                   }
	                   });
	            
	         });// each
	       
	         
	      },
	      error: function(err){
	         alert("실패");
	      }
	      
	      
	   });
	});
	
//---------------------------------------------심리테스트 side--------------------------------------------------------

$(document).ready(function() {
	   $.ajax({
	      url:'/simriTest/getSimriSide',
	      type: 'post',
	      dataType: 'json',
	      success:function(data){
	         console.log(data);

	         $.each(data.list,function(index,items){
	        	 
	            $('#main1').append($('<div/>',{
	               class: 'card mb-2'
	            }).append($('<div/>',{
	               class: 'row g-0'
	               }).append($('<div/>',{
	                  class: 'col-md-4',
	                  style:'height:100px; overflow: hidden; margin:auto'
	               		}).append($('<img/>',{
	                  		src:"/simriTest/storage/"+items.image,
	                  		class: 'img-thumbnail',
	                  		 style:'width: 100%; height:100%; object-fit:cover;',
	                  		alt : '5번이미지'
	               }))).append($('<div/>',{
	               class: 'col-md-8'
	               }).append($('<div/>',{
	                  class: 'card-body'
	                  }).append($('<h5/>',{
	                     class: 'card-title'
		                  }).append($('<a/>',{
		                     href: '#',
		                     id: 'subjectH',
		                       class: 'subject_'+items.seq,
		                       style:'text-overflow:ellipsis; overflow:hidden; display: -webkit-box; -webkit-line-clamp: 2;  -webkit-box-orient: vertical; word-wrap:break-word;',
		                       text: items.subject
	                  })))
	               )
	            )))
	            
	            $('.subject_'+items.seq).click(function(){
	            	$.cookie('simriSideCookie', 'babuya', {expires:1, path: '/' });
	            	$('input[name=seq]').val(items.seq);
	            	$('form[name=simriSideListForm]').submit();
		                   
	            });

	            
	         });// each
	         
	      },
	      error: function(err){
	         alert("실패");
	      }
	      
	      
	   });
	});
	  

</script>
