$(document).ready(function() {
	
	$.ajax({
		url:'/simriTest/simriTest/getSimriTest_List',
		type: 'post',
		dataType: 'json',
		success:function(data){
			$.each(data.list,function(index,items){
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
				//리스트 불러오기
				if(index%2==0){					
					$('#main').append($('<div/>',{
						class:'row simri',
						id:'mainRow'+items.seq						
					}).append($('<div/>',{
					class:'col-lg-6'
					}).append($('<div/>',{
						class:'card mb-4 url'+items.seq,
						style:'cursor:pointer;'
							
					}).append($('<img/>',{
						class:'card-img-top',
						src:'/simriTest/storage/'+items.image,
						style:'height:250px; object-fit:cover;',
						alt: '...'
					})).append($('<div/>',{
							class:'card-body'
						}).append($('<div/>',{
							class:'small text-muted mb-1',
							text:"#"+items.hashTag1+' '+items.hashTag2+' '+items.hashTag3
						})).append($('<h2/>',{
							class:'card-title h4',
							text:items.subject,
							style:'text-overflow:ellipsis; overflow:hidden; display: -webkit-box; -webkit-line-clamp: 1;  -webkit-box-orient: vertical; word-wrap:break-word;',
						}))))))
				}else{
					$('#mainRow'+data.list[index-1].seq).append($('<div/>',{
						class:'col-lg-6'
						}).append($('<div/>',{
							class:'card mb-4 url'+items.seq,
							style:'cursor:pointer;'
						}).append($('<img/>',{
							class:'card-img-top',
							src:'/simriTest/storage/'+items.image,
							style:'height:250px; object-fit:cover;',
							alt: '...'
						})).append($('<div/>',{
								class:'card-body'
							}).append($('<div/>',{
								class:'small text-muted mb-1',
								text:"#"+items.hashTag1+' '+items.hashTag2+' '+items.hashTag3
							})).append($('<h2/>',{
								class:'card-title h4',
								text:items.subject,
								style:'text-overflow:ellipsis; overflow:hidden; display: -webkit-box; -webkit-line-clamp: 1;  -webkit-box-orient: vertical; word-wrap:break-word;',
							})))))
				}	
				
				//카드 클릭시 해당글로
				$(document).on('click', '.url'+items.seq, function(){
					
						$('#seq').val(items.seq);
						$('#simriTestForm').submit();					
					
					
				});
			});			
			
			//더보기
			$('.simri').hide();			
			$('.simri').slice(0,3).show();	
			if($('.simri:hidden').length==0){
				$('#load').hide();
			}
			$('#load').click(function(){				
				//functino(e) e.preventDefault();이유모르겠음 없어도 되는듯
				$('.simri:hidden').slice(0,3).show();
				if($('.simri:hidden').length==0){
					$('#load').hide();
				}
			});

		},
		error: function(err){
			alert("실패");
		}		
	});//ajax
	console.log($('.simri').prop('tagName'));
	$('.simri').hide();
	
});
  