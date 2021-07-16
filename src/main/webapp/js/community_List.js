//검색
$('#communitySearchBtn').click(function() {

   if ($('#keyword').val() == '') {
      alert('검색어를 먼저 입력해주세요');
   }else {
      $.ajax({
         type : 'post',
         url : '/simriTest/community/community_Search',
         data: $('#communitySearchForm').serialize(),
         dataType : 'json',
         success : function(data) {
        	if(data.list == ''){
        		alert('찾는 결과물이 없습니다'); 
        		return;
        	} else {
               $('#communityListTable tr:gt(0)').remove();
               
               $.each(data.list,function(index,items){
                   $('<tr/>').append($('<td/>')).append($('<td/>',{
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
	                	   href:'#',
	                	   text:items.subject,
	                	   id:'subjectH',
	                	   class: 'subject_'+items.seq,
	                	   style:'line-height: 3.5em; text-overflow:ellipsis; overflow:hidden; display: -webkit-box; -webkit-line-clamp: 1;  -webkit-box-orient: vertical; word-wrap:break-word;'
	                   }))
                   ).append($('<td/>',{
                      align:'center',
                      text:items.nickName, 
                      style: 'line-height: 3.5em;'
                      
                   })).append($('<td/>',{
                       align:'center',
                       text:items.comLogtime,
                       style: 'line-height: 3.5em;'
                   })).append($('<td/>',{
                      align:'center',
                      text:items.hit,
                      style: 'line-height: 3.5em;'
                   })).append($('<td/>',{
                      align:'center',
                      text:items.comLike, 
                      style: 'line-height: 3.5em;'
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
        				}
                    });
       			
	       		//댓글수
	                if(items.reply!=0){
	                   
	                   $('.subject_'+items.seq).html(items.subject+'('+items.reply+')');
	                }
                   
                        
               });// each
            }//else
            
            //페이징 처리                                                         //getPagingHTML()하고 같은거
            $('#communityPagingDiv').html(data.communityPaging.pagingHTML);
            
         },
         error : function(err) {
            alert("실패")
            console.log(err);
         }
      });
   }
});