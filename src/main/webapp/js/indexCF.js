//인덱스 로딩시 광고 뿌려주기
$(function(){
	$.ajax({
		type:'post',
		url:'/simriTest/getCFList', // 홈화면 인기순 최신순 출력
		dataType:'json',
		success: function(data){
			if(data.memberDTO != null){
				$('.memberPoint').val(data.memberDTO.point);
			}
			$.each(data.cfList, function(index,items){
				$('<div/>',{
					id:"indexCFDiv"+items.seq,
					class:'carousel-item',
				}).append($('<img/>',{
					id:"indexCFImg"+items.seq,
					src:'/simriTest/storage/'+items.image,
					class:"d-block img-fluid mx-auto",
					style: 'height:550px; object-fit:cover; cursor:pointer;',
				})).append($('<div/>',{
					class:"carousel-caption d-none d-md-block"
				})).appendTo($('#dlfma'));
				
				if(index == 0){
					$('#indexCFDiv'+items.seq).attr('class','carousel-item active')
				}	
	               // 로그인 여부와 포인트 여부
	               $('#indexCFImg'+items.seq).click(function(){
	            	   
	            	   if(items.category == '심리 테스트'){
	            		   $('#CFForm').attr('action','/simriTest/simriTest/simriTestRead')
	            		   $.cookie('totalSearchCookie', 'babu', {expires:1, path: '/' });
	            		   
	            		   $('#CFSeq').val(items.seq);
	            		   $('#CFForm').submit();					
	            	   }else if(items.category == '연애 심리글'){
	            		   $('#CFForm').attr('action','/simriTest/love/magazineContent')
	                       if(data.memId == null) {
	 	                      alert('로그인이 필요한 서비스 입니다');
	 	                      return;
	 	                   }else{// 아이디 존재
	 	                      // 그 사람의 포인트 쓴 유무 확인
	 	                      $.ajax({
	 	                    	  type: 'post',
	 	                    	  url: '/simriTest/love/checkPoint',
	 	                    	  data: {
	 	                    		  'seq' : items.seq
	 	                    	  },
	 	                    	  dataType: 'json',
	 	                    	  success:function(data1){
	 	                    		  if(data1.checkPoint==1){
	 	                    			 $.cookie('totalSearchCookie', 'babu', {expires:1, path: '/' });
	 	                            	  $('#CFSeq').val(data1.seq);
	 	                            	  $('#CFForm').submit();
	 	                    		  }else{
	 	                    			  if(items.point == 0){
	 	                    				 $.cookie('totalSearchCookie', 'babu', {expires:1, path: '/' });
	 	                    				  $('#CFSeq').val(items.seq);
	 	                    				  $('#CFboardPoint').val(items.point);
	 	                    				  $('#CFForm').submit(); 
	 	                    			  }else if(data.memberDTO.point >= items.point){
	 	                    				  if(confirm(items.point+"원이 차감됩니다. 결제 하시겠습니까?")==true){
	 	                    					  alert("결제 되었습니다")
	 	                    					  $.cookie('totalSearchCookie', 'babu', {expires:1, path: '/' });
	 	                    					  $('#CFSeq').val(items.seq);
	 	                    					  $('#CFboardPoint').val(items.point);
	 	                    					  $('#CFForm').submit();
	 	                    				  }else{
	 	                    					  return;
	 	                    				  }
	 	                            	  }else if(data.memberDTO.point < items.point){
	 	                            		  alert("포인트를 충전 후 사용해 주세요")
	 	                            		  return;
	 	                            	  }
	 	                              }
	 	                    	  },
	 	                    	  error: function(err){
	 	                    		  alert("실패");
	 	                    	  }
	 	                      });//ajax
	 	                      
	 	                   }//else
	                       return;
	            	   }//if(data.cfList[index].category == '연애 심리글'){
	            	   return;
                   });//click
	               
	               

			});//each
			
		},
		error: function(err){
			
		
		}//error
	});//ajax
});//function