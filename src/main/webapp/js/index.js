//인덱스 로딩시 인기심리테스트 뿌려주기
$(function(){
	$.ajax({
		type:'post',
		url:'/simriTest/getIndexData', // 홈화면 인기순 최신순 출력
		dataType:'json',
		success: function(data){
			
			// 인기심리테스트
			$.each(data.favoriteSimriTestlist, function(index,items){
				if(items.hashTag2 == null){
					items.hashTag2 = '';
				}else{
					items.hashTag2 = "#"+items.hashTag2; 
				}
				if(items.hashTag3 == null){
					items.hashTag3 = '';
				}else{
					items.hashTag3 = "#"+items.hashTag3; 
				}
				
				if(items.point == 0){
					items.point = '무료';
				}
				
				$('<div/>',{
					class:"col-lg-6",
					
				}).append($('<div/>',{
					class:"card mb-4 url"+items.seq,
					style:'cursor:pointer;'
						
				}).append($('<img/>',{
					 class:"card-img-top",
					 src:"/simriTest/storage/" + items.image,
					 style:"height: 200px;"
						 
				})).append($('<div/>',{
					class:"card-body",
					style:"height: 120px; margin-bottom: 15px;"
						
				}).append($('<div/>',{
					class:"col before2",
					style: "text-align-last: right; margin-bottom : 8px;",
				})).append($('<div/>',{
					class:"small text-muted mb-2",
					style:"margin-bottom : 8px;",
					text :"#"+items.hashTag1+ " "+items.hashTag2+ " "+items.hashTag3,
					
				})).append($('<div/>',{
					class: "card-title h4",
					text: items.subject,
					style:'text-overflow:ellipsis; overflow:hidden; display: -webkit-box; -webkit-line-clamp: 2;  -webkit-box-orient: vertical; word-wrap:break-word;'
					
				})))).appendTo($('.hotSimriBody'));
				
				// 카드 클릭시 다음페이지로 넘어가기
				$(document).on('click', '.url'+items.seq, function(){
						$('#favoriteSimriTestSeq').val(items.seq);
						$('#indexForm').submit();					
					
				});
				
			});// each
			
			
			// 인기연애심리글
			$.each(data.favoriteMagazinelist, function(index,items){
				if(items.hashTag2 == null){
					items.hashTag2 = '';
				}else{
					items.hashTag2 = "#"+items.hashTag2; 
				}
				if(items.hashTag3 == null){
					items.hashTag3 = '';
				}else{
					items.hashTag3 = "#"+items.hashTag3; 
				}
				if(items.point == 0){
					items.point = '무료';
				}else{
					items.point = items.point+"p";
				}
				
				$('<div/>',{
					class:"col-lg-6",
					
				}).append($('<div/>',{
					class:"card mb-4 url"+items.seq,
					style:'cursor:pointer;'
						
				}).append($('<img/>',{
					 class:"card-img-top",
					 src:"/simriTest/storage/" + items.image,
					 style:"height: 200px;"
				})).append($('<div/>',{
					class:"card-body",
					style:"height: 150px; margin-bottom: 15px;"
				}).append($('<div/>',{
					class:"col before2",
					style: "text-align-last: right; margin-bottom : 8px;",
					
				}).append($('<span/>',{
					 class: "badge rounded-pill bg-warning mb-1",
					 text: items.point,
					 
				}))).append($('<div/>',{
					class:"small text-muted mb-2",
					text : "#"+items.hashTag1+ " "+items.hashTag2+ " "+items.hashTag3,
				})).append($('<div/>',{
					class: "card-title h4 subject_"+items.seq,
					text: items.subject,
					style:'text-overflow:ellipsis; overflow:hidden; display: -webkit-box; -webkit-line-clamp: 2;  -webkit-box-orient: vertical; word-wrap:break-word;'
					
				})))).appendTo($('.hotLoveBody'));
				
				
				
	               // 로그인 여부와 포인트 여부
	               $('.url'+items.seq).click(function(){
                      if(data.memId == null) {
	                      alert('로그인이 필요한 서비스 입니다');
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
	                    			  $.cookie('totalSearchCookie', 'babuu', {expires:1, path: '/' });
	                            	  $('#favoriteMagazinelistSeq').val(data1.seq);
	                            	  $('#indexLoveForm').submit();
	                    		  }else{
	                    			  if(items.point == 0){
	                    				  $.cookie('totalSearchCookie', 'babuu', {expires:1, path: '/' });
	                    				  $('#favoriteMagazinelistSeq').val(items.seq);
	                    				  $('#boardPoint').val(items.point);
	                    				  $('#indexLoveForm').submit(); 
	                    			  }else if(data.memberDTO.point >= items.point){
	                    				  if(confirm("결제 하시겠습니까?")==true){
	                    					  $.cookie('totalSearchCookie', 'babuu', {expires:1, path: '/' });
	                    					  alert("결제 되었습니다")
	                    					  $('#favoriteMagazinelistSeq').val(items.seq);
	                    					  $('#boardPoint').val(items.point);
	                    					  $('#indexLoveForm').submit();
	                    				  }else{
	                    					  return;
	                    				  }
	                            	  }else if(data.memberDTO.point < items.point){
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
			
			// 최신순
			$.each(data.recentlySimriTestlist, function(index,items){
				if(items.hashTag2 == null){
					items.hashTag2 = '';
				}else{
					items.hashTag2 = "#"+items.hashTag2; 
				}
				if(items.hashTag3 == null){
					items.hashTag3 = '';
				}else{
					items.hashTag3 = "#"+items.hashTag3; 
				}
					
					if(items.point == 0){
						items.point = '무료';
					}
					
					$('<div/>',{
						class:"col-lg-6",
						
					}).append($('<div/>',{
						class:"card mb-4 url"+items.seq,
						style:'cursor:pointer;'
							
					}).append($('<img/>',{
						 class:"card-img-top",
						 src:"/simriTest/storage/" + items.image,
						 style:"height: 200px;"
					})).append($('<div/>',{
						class:"card-body",
						style:"height: 120px; margin-bottom: 15px;"

					}).append($('<div/>',{
						class:"col before2",
						style: "text-align-last: right; margin-bottom : 8px;",
						
					
						 
					})).append($('<div/>',{
						class:"small text-muted mb-2",
						style:"margin-bottom : 8px;",
						text :"#"+items.hashTag1+ " "+items.hashTag2+ " "+items.hashTag3,
						
					})).append($('<div/>',{
						class: "card-title h4",
						text: items.subject,
						style:'text-overflow:ellipsis; overflow:hidden; display: -webkit-box; -webkit-line-clamp: 2;  -webkit-box-orient: vertical; word-wrap:break-word;'
					})))).appendTo($('.recentlySimriBody'));
					
					
					// 카드 클릭시 다음페이지로 넘어가기
					$(document).on('click', '.url'+items.seq, function(){
							$('#recentlySimriTestlistSeq').val(items.seq);
							$('#indexRecentForm').submit();					
						
					});
					
				});// each


		},// success
		error:function(err){
			console.log(err);
		}// error
	});// ajax

});