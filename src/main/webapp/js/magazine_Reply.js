$(function(){
	$.ajax({
		url:'/simriTest/simriTest/getSimilar',
		type:'post',
		data:'seq='+$('#seq').val(),
		dataType:'json',
		success:function(data){
			$.each(data.list, function(index, items){
				$('.similar').append($('<div/>',{
					class:'col'
				}).append($('<div/>',{
					class:'card h-100  similarBtn'+items.seq,
					style:'cursor:pointer;'
				}).append($('<div/>',{
					style:'cursor:pointer;height:150px; overflow: hidden; margin:auto;'
				}).append($('<img/>',{
					src:'/simriTest/storage/'+items.image,
					class:'card-img-top',
					style:'width: 100%; height:100%; object-fit:cover;',
					alt:'...'
				}))).append($('<div/>',{
					class:'card-body'
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
					text:items.subject,
					style:'font-weight:bold; text-overflow:ellipsis; overflow:hidden; display: -webkit-box; -webkit-line-clamp: 1;  -webkit-box-orient: vertical; word-wrap:break-word;'
				})).append($('<p/>',{
					class:'card-text',
				    style:'text-overflow:ellipsis; overflow:hidden; display: -webkit-box; -webkit-line-clamp: 2;  -webkit-box-orient: vertical; word-wrap:break-word;',
					text:items.content
				})))))
				
				$('.similarBtn'+items.seq).click(function(){
					$('#similarSeq').val(items.seq);
					$.ajax({
                  	  type: 'post',
                  	  url: '/simriTest/love/checkPoint',
                  	  data: {
                  		  'pg' : $('#pg').val(),
                  		  'seq' : items.seq
                  	  },
                  	  dataType: 'json',
                  	  success:function(data1){
                  		  console.log(data1);
                  		  if(data1.checkPoint==1){
                          	$('#seq').val(data1.seq);
                          	$.cookie('similarCookie', 'babu', {expires:1, path: '/' });
        					$('#magazineViewForm').submit();
                          	  return;
                  		  }else{
                  			  if(items.point == 0){
                  				  $('#seq').val(items.seq);
                  				  $('#boardPoint').val(items.point);
                  				  $.cookie('similarCookie', 'babu', {expires:1, path: '/' });
            					  $('#magazineViewForm').submit();
                  			  }else if($('#memberPoint').val() >= items.point){
                  				  if(confirm("결제 하시겠습니까?")==true){
                  					  alert("결제 되었습니다")
                  					  $('#seq').val(items.seq);
                  					  $('#boardPoint').val(items.point);
                  					  $.cookie('similarCookie', 'babu', {expires:1, path: '/' });
                					  $('#magazineViewForm').submit();
                  				  }else{
                  					  return;
                  				  }
                          	  }else if($('#memberPoint').val() < items.point){
                          		  alert("포인트 충전 후 사용해 주세요")
                          	  }
                            }
                  	  },
                  	  error: function(err){
                  		  alert("실패요");
                  	  }
                    });
					
				});
				
			});
		 $.ajax({
		      url: '/simriTest/community/getReplyList',
		      type: 'post',
		      data: 'seq='+$('#seq').val(),
		      dataType: 'json',
		      success: function(data){	         
		    		var reNickName=null;
					var i=1;						
		    	 $.each(data.list, function(index, items){
		    		
		    		//대댓 닉네임
			    	  if(index>0 && items.lev>0){
			    		  
			    			                                                                                                                                                                                                                                                                                                                                                                            
									
			    			 if(items.lev > data.list[index-1].lev){
									if(!data.list[index+1]){
		 								reNickName = items.nickName;		 									
									}else if(items.lev!=data.list[index+1].lev){
		 								reNickName = items.nickName;
									}else if(reNickName==null && items.lev==data.list[index+1].lev){
										reNickName = data.list[index-i].nickName;
									}
										
								}
		    			   if(items.lev < data.list[index-1].lev){
								reNickName = data.list[index-i].nickName;
								i=1;
							} 
		    			   i++;
		    		 } 
		    		 
			    	 $('.commentList').append($('<div/>',{
			    		 class:'form'+items.replySeq,
			    		 style:'margin-right:15px;'
			    	 }).append($('<li/>',{
			    		 id:items.replySeq,
			    		 style:'display:inline-block;',
			    		 class:'commentItem'+items.replySeq
			    	 }).append($('<div/>',{
			    		 class:'d-flex mb-4'
			    	 }).append($('<div/>',{
						class:'flex-shrink-0' 
			    	 	}).append($('<img/>',{
			    			class:'rounded-circle',
			    			src: items.profile,
			    			width: '50px',
			    			height: '50px'
			    	 	}))//div img
			    	 ).append($('<div/>',{
			    		 class:'ms-3'
			    	 }).append($('<span/>',{
			    		 class:'fw-bold',
			    		 text:items.nickName
			    	 })).append($('<br/>')).append($('<pre/>').append($('<div/>').append($('<span/>',{
			    		 class:'reNickName'+items.replySeq,
			    		 text:items.replyComment
			    		 })))).append($('<span/>', {
						class:'replyLogtime'+items.replySeq,
			    		 html:items.replyLogtime+'&emsp;'
			    	 })
			    	 ).append($('<span/>',{
			    		 class:'replyBtn'+items.replySeq,
							id:'replyBtn'+items.replySeq,
							style: 'cursor:pointer;',
							html: '답글쓰기'}))
			    	 )
			    	 )//div total				    	 
			    	 ).append($('<div/>',{
						class:'dropdown dropdown'+items.replySeq,
						style:'float:right;'
					})//dropdown
					)
			    	 )
			    	 
			    	 
			    	 
			    	 
			    	 var levA=1;
			    	 //댓글 밀어주기
			    	 if(levA<=items.lev){				    		
			    		 $('.commentItem'+items.replySeq).before('&emsp;&emsp;&emsp;&emsp;');
			    	 }
			    	 
			    	 var levA=2;
			    	 if(levA<=items.lev){
			    		 $('.reNickName'+items.replySeq).prepend($('<strong/>',{
			    			 html : reNickName+'&emsp;'
			    			 }));
			    	 }
			    	 if(index>0 && items.lev>0){ 		  		    			 
							
		    			
					   if(items.lev < data.list[index-1].lev){
							reNickName = items.nickName;
							
						} 
					  
				 } 
			    	
			    	
			    	//같은사람이면 dropdown 버튼 생성
			    	 if($('#memId').val()==items.email){
			    		 $('.dropdown'+items.replySeq).append($('<button/>',{
							class:'btn btn-outline-primary dropdown-toggle btn-sm dropdownMenuButton1'+items.replySeq,
							type:'button',
							id:'dropdownMenuButton1',								
							text:'☰'
						})).append($('<ul/>',{
							class:'dropdown-menu',								
						}).append($('<li/>').append($('<span/>',{
								class:'dropdown-item replyModify'+items.replySeq,
								style: 'cursor:pointer;',
								text:'댓글수정'
							}))							
						).append($('<li/>').append($('<a/>',{
								class:'dropdown-item',
								href:'/simriTest/love/magazineReply_Delete?replySeq='+items.replySeq+'&seq='+$('#seq').val(),
								style: 'cursor:pointer;',
								text:'댓글삭제'
							}))							
						))	
					}else{
						$('.dropdown'+items.replySeq).append($('<button/>',{
							class:'btn btn-outline-primary dropdown-toggle btn-sm dropdownMenuButton1'+items.replySeq,
							type:'button',
							id:'dropdownMenuButton1',								
							text:'☰'
						})).append($('<ul/>',{
							class:'dropdown-menu',								
						}).append($('<li/>').append($('<span/>',{
								class:'dropdown-item replySingo'+items.replySeq,//진행해야될 부분
								style: 'cursor:pointer;',
								text:'신고'
							}))							
						))
					}//if					
					$('.dropdownMenuButton1'+items.replySeq).attr('data-bs-toggle','dropdown');
			    	$('.dropdownMenuButton1'+items.replySeq).attr('aria-expanded','false');
			    	$('.dropdown-menu').attr('aria-labelledby', 'dropdownMenuButton1');				    	
			    	 
			    	 
			    	//신고
			    	$('.replySingo'+items.replySeq).click(function(){
			    		$('#checkReplySeq').val(items.replySeq);
			    		$('#checkEmail').val(items.email);
			    		$.ajax({
			    			type:'post',
			    			url:'/simriTest/community/checkReplySingo',
			    			data:{
			    				'seq' : $('#seq').val(),
			    				'replySeq' : $('#checkReplySeq').val(),
			    				'category' : $('#category').val(),
		    					'email': $('#checkEmail').val()
			    			},
			    			dataType:'json',
			    			success: function(data){
			    				if(data.replySingoOnoff!=1){
						    		$('#myModal').modal('show');
						    		
						    		$(document).on('click', "button[name='singoInsertBtn']", function(){
						    			if($('input[name=singoReason]').val()==null || $('input[name=singoReason]').val()==''){
							    			alert('신고 사유를 입력해주세요')
						    			}else{
							    			if($('#checkReplySeq').val()!=""){
								    			$.ajax({
								    				type: 'post',
								    				url: '/simriTest/community/replySingo',
								    				data: { 
								    					'seq' : $('#seq').val(),
								    					'replySeq' : $('#checkReplySeq').val(),
								    					'category' : $('#category').val(),
								    					'email': $('#checkEmail').val(),
								    					'singoReason': $('input[name=singoReason]').val()
								    				},
								    				success: function(){
								    					alert('신고 되었습니다');
								    					$('#myModal').modal('hide');
								    					
								    					window.location.reload();
								    					return;
								    				},
								    				error: function(err){
								    					console.log(err);
								    				}				    			
								    			});
								    		 }
						    			}
							    	});
					    		
					    		}else{
							    	alert('이미 신고 하신 댓글 입니다')
							    }   	

			    			},
			    			error:function(err){
			    				
			    			}
			    		});
			    	});

			    	
			    	 
			    	 //대댓 form
			    	 $('#replyBtn'+items.replySeq).click(function(event, str){
			    		$('[class*="rereply"]').hide();
			    		 
			    		$('.form'+items.replySeq).append($('<form/>',{
			    			class:'mb-4 rereply'+items.replySeq
			    		}).append($('<textarea/>',{
			    			class:'form-control replyComment'+items.replySeq,
			    			style:'resize:none;',
			    			rows:'3',
			    			placeholder:'Join the discussion and leave a comment!'								
			    		}))).append($('<p/>',{
			    			class:'rereply2'+items.replySeq
			    		}).append($('<div/>',{
			    			class:'resetBtn'
			    		}).append($('<button/>',{
			    			type:'button',
			    			class:'btn btn-success btn-sm resetBtn'+items.replySeq,
			    			style:'float:right;',
			    			id:'replyResetBtn',
			    			text:'취소'
			    		}))))
			    		
			    		
			    		
			    		//답글 버튼 trigger
			    		if(str!='modify'){
			    			$('.rereply2'+items.replySeq).append($('<button/>',{
				    			type:'button',
				    			class:'btn btn-success btn-sm rereplyBtn'+items.replySeq,
				    			style:'float:right; margin-right:5px;',
				    			id:'replyWriteBtn',
				    			text:'답글 등록'
				    		})).append($('<br/>')).append($('<br/>'))
			    		}else{
			    			$('.commentItem'+items.replySeq).hide();
			    			$('.rereply2'+items.replySeq).append($('<button/>',{
				    			type:'button',
				    			class:'btn btn-success btn-sm replyModifyBtn'+items.replySeq,
				    			style:'float:right; margin-right:5px;',
				    			id:'replyModifyBtn',
				    			text:'댓글 수정'
			    			})).append($('<br/>')).append($('<br/>'))
			    		}//if
			    	 });
			    	 
			    	 //modify trigger 댓글 수정
			    	 $('.replyModify'+items.replySeq).click(function(){
				    	 $('#replyCommentForm').hide();
			    		 $('#replyBtn'+items.replySeq).trigger('click', 'modify');
				    	 
				    	 
				    	 $.ajax({
				    		url:'/simriTest/community/getReply',
				    		type:'post',
				    		data:{
				    			'replySeq':items.replySeq
				    			},
				    		dataType: 'json',
				    		success: function(data){					    			
				    			$('.replyComment'+items.replySeq).val(data.replyDTO.replyComment);
				    			//삭제된 댓글 readonly
				    	 		if($('.replyComment'+items.replySeq).val()=='[삭제된 댓글 입니다]'){
				    		
				    		 		$('.replyComment'+items.replySeq).prop('readonly', true);
				    	 		}
				    		},
				    		error: function(err){
				    			console.log(err);
				    		}
				    	 });
				    	 
				    	
			    	 });
			    	 
			    	//취소버튼
			    	$(document).on('click','.resetBtn'+items.replySeq, function(){
			    		$('.commentItem'+items.replySeq).show();
						$('.rereply'+items.replySeq).hide();
						$('.rereply2'+items.replySeq).hide();
						$('#replyCommentForm').show();
					});
			    	//dropdown button
			    	$('.dropdownMenuButton1'+items.replySeq).click(function(){
			    		$('[class*="commentItem"]').show();
			    		$('[class*="rereply"]').hide();
			    		$('#replyCommentForm').show();
			    	});
			    	
			    	 //대댓등록
			    	$(document).on('click','.rereplyBtn'+items.replySeq, function(){
			    		
						
						if($('.replyComment'+items.replySeq).val()==''){
							$('.replyComment'+items.replySeq).attr('placeholder','내용을 입력하세요');			
						}else{
							$.ajax({
								type:'post',
								url:'/simriTest/community/rereplyWrite',
								data:{
									//차후 원글번호 필요?? 세션값으로 닉네임이나 이메일 끌고 가야함				
									'replyComment':$('.replyComment'+items.replySeq).val(),
									'replySeq':items.replySeq,
									'seq':$('#seq').val()
									
								},
								success: function(){
									$('#magazineViewForm').submit();
									
								},
								error: function(err){
									console.log(err);
								}
							});
						}
					});
			    	 
			    	//댓글 수정
			    	$(document).on('click','.replyModifyBtn'+items.replySeq, function(){
			    		if($('.replyComment'+items.replySeq).val()==''){
			    			$('.replyComment'+items.replySeq).attr('placeholder','수정할 내용을 입력하세요');			
			    		}else{
			    			$.ajax({
			    				type:'post',
			    				url:'/simriTest/community/reply_Modify',
			    				data:{
			    					//차후 원글번호 필요?? 세션값으로 닉네임이나 이메일 끌고 가야함				
			    					'replyComment':$('.replyComment'+items.replySeq).val(),
			    					'replySeq':items.replySeq,
			    					'seq':$('#seq').val()
			    					
			    				},
			    				success: function(){	
			    					$('#magazineViewForm').submit();
			    					
			    				},
			    				error: function(err){
			    					console.log(err);
			    				}
			    			});
			    		}
			    	});
			    	 
		    	 })//each
		      },
		      error: function(err){
		         console.log(err);
		      }
		   });
		 
		},
		error: function(err){
			alert("실패");
		}
	});
});


//댓글 등록

$('#replyWriteBtn').click(function(){
	if($('#replyComment').val()==''){
		$('#replyComment').attr('placeholder','내용을 입력하세요');			
	}else{
		$.ajax({
			type:'post',
			url:'/simriTest/community/replyWrite',
			data:{
				//차후 원글번호 필요?? 세션값으로 닉네임이나 이메일 끌고 가야함				
				'replyComment':$('#replyComment').val(),
				'seq':$('#seq').val()				
			},
			success: function(){
				
				$('#magazineViewForm').submit();
				
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});

