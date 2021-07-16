	
// 글 좋아요, 글 신고 
function likesingo(num){
	if(num==1){//글 좋아요
		$.ajax({
			type: 'post',
			url: '/simriTest/community/like',
			data: {
				'seq':$('#seq').val(),
				'category' : $('#category').val()
			},
			dataType:'json',
			success: function(data){
				
				$('#onoff').val(data.onoff);
				$('#communityViewForm').submit();
			},
			error : function(err){
				alert("실패")
			}
			
		});
		
	}else if(num==2){//글 신고
		
		
		if($('#singoOnoff').val()==0){
			$('#myModal').modal('show');		

			$('#singoInsertBtn').click(function() {
				if($('input[name=singoReason]').val()==null || $('input[name=singoReason]').val()==''){
	    			alert('신고 사유를 입력해주세요')
    			}else{
					$.ajax({
						type : 'post',
						url : '/simriTest/community/singo',
						data : {
							'seq' : $('#seq').val(),
							'category' : $('#category').val(),
							'singoReason' : $('input[name=singoReason]').val()
						},
						dataType : 'json',
						success : function(data) {
							$('#myModal').modal('hide');
							alert('신고 되었습니다');
							$('#singoOnoff').val(data.singoOnoff);
							$('#communityViewForm').submit();
							// window.location.reload();
						},
						error : function(err) {
							console.log(err);
						}
					});
    			}
			});
		}else{
			$.ajax({
				type : 'post',
				url : '/simriTest/community/singo',
				data : {
					'seq' : $('#seq').val(),
					'category' : $('#category').val(),
					'singoReason' : $('input[name=singoReason]').val()
				},
				dataType : 'json',
				success : function(data) {
					alert('신고를 취소하였습니다');
					$('#singoOnoff').val(data.singoOnoff);
					$('#communityViewForm').submit();
					// window.location.reload();
				},
				error : function(err) {
					console.log(err);
				}
			});
		}
	}	
	
}

