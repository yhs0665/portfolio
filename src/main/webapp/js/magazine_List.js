$(document).ready(function() {
   $.ajax({
      url:'/simriTest/love/getMagazine_List',
      data: {
            'pg' : $('#pg').val(),
            'memberPoint' : $('#memberPoint').val()
      },
      type: 'post',
      dataType: 'json',
      success:function(data){
         console.log(data);
         
         $.each(data.list,function(index,items){
        	 
            $('#main').append($('<div/>',{
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
                     class: 'subjectB_'+items.seq,
                     style:'font-weight:bold; text-overflow:ellipsis; overflow:hidden; display: -webkit-box; -webkit-line-clamp: 1;  -webkit-box-orient: vertical; word-wrap:break-word;',
                     text: items.subject
                  }))).append($('<p/>',{
                     class: 'card-text',
                     style:'text-overflow:ellipsis; overflow:hidden; display: -webkit-box; -webkit-line-clamp: 2;  -webkit-box-orient: vertical; word-wrap:break-word;',
                     text: items.content
                  }))
               )
            )))))

               // 로그인 여부와 포인트 여부
               $('.subjectB_'+items.seq).click(function(){
                      if(data.memNickname == null) {
                      alert('로그인이 필요한 서비스 입니다');
                   }else{// 아이디 존재
                                            
                      //그 사람의 포인트 쓴 유무 확인
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
                            	  $('#seq').val(data1.seq);
                            	  $('#magazineListForm').submit();
                            	  return;
                    		  }else{
                    			  if(items.point == 0){
                    				  $('#seq').val(items.seq);
                    				  $('#boardPoint').val(items.point);
                    				  $('#magazineListForm').submit();
                    			  }else if(data.memberPoint >= items.point){
                    				  if(confirm("결제 하시겠습니까?")==true){
                    					  alert("결제 되었습니다")
                    					  $('#seq').val(items.seq);
                    					  $('#boardPoint').val(items.point);
                    					  $('#magazineListForm').submit();
                    				  }else{
                    					  return;
                    				  }
                            	  }else if(data.memberPoint < items.point){
                            		  alert("포인트 충전 후 사용해 주세요")
                            	  }
                              }
                    	  },
                    	  error: function(err){
                    		  alert("실패요");
                    	  }
                      });
                      
                   }
                   });
            
         });// each
         // 페이징처리
            $('#simriTestPagingDiv').html(data.simriTestPaging.pagingHTML);
         
      },
      error: function(err){
         alert("실패양");
      }
      
      
   });
});
  