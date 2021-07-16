$(function(){
	$.ajax({
        type : 'post',
        url : '/simriTest/simriTest/getTotalSearch',
        data: $('#totalSearchListForm').serialize(),
        dataType : 'json',
        success : function(data) {
       	   $('#totalKeyword').val(data.totalKeyword);
	       	 if(data.list == ''){
	    		 alert('찾는 결과물이 없습니다'); return;
	    	} else {
              $('#communityListTable tr:gt(0)').remove();
              
              $.each(data.list,function(index,items){
                  $('.totSearchList').append($('<div/>',{
     					class: 'card-body'
     				}).append($('<div/>',{
     					class: 'input-group'
     				}).append($('<div/>',{
     					class: 'card mb-2'
     				}).append($('<div/>',{
     					class: 'row g-0'
     					}).append($('<div/>',{
     						class: 'col-md-4',
     						style:'height:160px; overflow: hidden; margin:auto'
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
     							(items.point =='' ? $('<span/>'):
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
     							))

     							)).append($('<h5/>',{
     							class: 'card-title'
     						}).append($('<a/>',{
     							href: '#',
     							id: 'subjectH',
     					        class: 'subject_'+items.seq,
     					        style:'font-weight:bold; text-overflow:ellipsis; overflow:hidden; display: -webkit-box; -webkit-line-clamp: 1;  -webkit-box-orient: vertical; word-wrap:break-word;',
     							text: items.subject
     						}))).append($('<p/>',{
     							class: 'card-text',
     							style:'text-overflow:ellipsis; overflow:hidden; display: -webkit-box; -webkit-line-clamp: 2;  -webkit-box-orient: vertical; word-wrap:break-word;',
     							text: items.content
     						}))
     					)
     				)))));
                  
                                    
                  //로그인 여부 
                  $('.subject_'+items.seq).click(function(){
                  
                  	var red = '[팔레트]빨강';
                  	var blue = '[팔레트]파랑';
                  	var green = '[팔레트]초록';
                  	var yellow = '[팔레트]노랑';
                  	
                  	if(items.category == '심리 테스트'){
                  		$.cookie('totalSearchCookie', 'babu', {expires:1, path: '/' });
       					$('#seq').val(items.seq);
       					$('#category').val(items.category);
       					$('#totalSearchListForm').attr('action','/simriTest/simriTest/simriTestRead').submit();
       				}else if(data.memNickname == null) {
       					alert('로그인이 필요한 서비스 입니다');
       				}else if(items.category == '연애 심리글'){
       					$.ajax({
                      	  type: 'post',
                      	  url: '/simriTest/love/checkPoint',
                      	  data: {
                      		  'pg' : $('#pg').val(),
                      		  'seq' : items.seq
                      	  },
                      	  dataType: 'json',
                      	  success:function(data1){
                      		  if(data1.checkPoint==1){
                      			$.cookie('totalSearchCookie', 'babu', {expires:1, path: '/' });
                      			$('#seq').val(items.seq);
    	 						$('#boardPoint').val(items.point);
    	 						$('#category').val(items.category);
    	 						$('#totalSearchListForm').attr('action','/simriTest/love/magazineContent').submit();	 
                              	  return;
                      		  }else{
                      			  if(items.point == 0){
                      				$.cookie('totalSearchCookie', 'babu', {expires:1, path: '/' });
                      				$('#seq').val(items.seq);
        	 						$('#boardPoint').val(items.point);
        	 						$('#category').val(items.category);
        	 						$('#totalSearchListForm').attr('action','/simriTest/love/magazineContent').submit();	 
                      			  }else if(data.memberPoint >= items.point){
                      				  if(confirm("결제 하시겠습니까?")==true){
                      					  alert("결제 되었습니다")
                      					  $.cookie('totalSearchCookie', 'babu', {expires:1, path: '/' });
                      					$('#seq').val(items.seq);
              	 						$('#boardPoint').val(items.point);
              	 						$('#category').val(items.category);
              	 						$('#totalSearchListForm').attr('action','/simriTest/love/magazineContent').submit();	 
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
       					
       				}else if(data.memPalette=="red"){
       					if(items.category == yellow || items.category == blue || items.category == green){
       						alert("소속 팔레트 게시글만 볼 수 있습니다");
       						return;
       					}else{
       						$.cookie('totalSearchCookie', 'babu', {expires:1, path: '/' });
       						$('#seq').val(items.seq);
       						$('#category').val(items.category);
       						$('#totalSearchListForm').attr('action','/simriTest/community/community_View').submit();
       					}
       				}else if(data.memPalette=="blue"){
       					if(items.category == yellow || items.category == red || items.category == green){
       						alert("소속 팔레트 게시글만 볼 수 있습니다");
       						return;
       					}else{
       						$.cookie('totalSearchCookie', 'babu', {expires:1, path: '/' });
       						$('#seq').val(items.seq);
       						$('#category').val(items.category);
       						$('#totalSearchListForm').attr('action','/simriTest/community/community_View').submit();
       					}
       				}else if(data.memPalette=="green"){
       					if(items.category == yellow || items.category == blue || items.category == red){
       						alert("소속 팔레트 게시글만 볼 수 있습니다");
       						return;
       					}else{
       						$.cookie('totalSearchCookie', 'babu', {expires:1, path: '/' });
       						$('#seq').val(items.seq);
       						$('#category').val(items.category);
       						$('#totalSearchListForm').attr('action','/simriTest/community/community_View').submit();
       					}
       				}else if(data.memPalette=="yellow"){
       					if(items.category == red || items.category == blue || items.category == green){
       						alert("소속 팔레트 게시글만 볼 수 있습니다");
       						return;
       					}else{
       						$.cookie('totalSearchCookie', 'babu', {expires:1, path: '/' });
       						$('#seq').val(items.seq);
       						$('#category').val(items.category);
       						$('#totalSearchListForm').attr('action','/simriTest/community/community_View').submit();
       					}
       				}else if(data.memPalette=="white"){
       					if(items.category == yellow || items.category == blue || items.category == green || items.category == red){
       						alert("소속 팔레트가 없어서 볼 수 없습니다");
       						return;
       					}else{
       						$.cookie('totalSearchCookie', 'babu', {expires:1, path: '/' });
       						$('#seq').val(items.seq);
       						$('#category').val(items.category);
       						$('#totalSearchListForm').attr('action','/simriTest/community/community_View').submit();
       					}
       				}else{
       					$.cookie('totalSearchCookie', 'babu', {expires:1, path: '/' });
       					$('#seq').val(items.seq);
       					$('#category').val(items.category);
       					$('#totalSearchListForm').attr('action','/simriTest/community/community_View').submit();
       				}
                   });
      				       		                  
                       
              });// each
           }//else
           
           //페이징 처리                                                         //getPagingHTML()하고 같은거
           $('#simriTestPagingDiv').html(data.simriTestPaging.pagingHTML);
           
        },
        error : function(err) {
           alert("실패")
           console.log(err);
        }
     });
});