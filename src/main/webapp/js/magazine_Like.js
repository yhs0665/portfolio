// 글 좋아요
function likesingo(num){
	if(num==1){//글 좋아요
		$.ajax({
			type: 'post',
			url: '/simriTest/love/like',
			data: {
				'seq':$('#seq').val(),
				'category' : $('#category').val()
			},
			dataType:'json',
			success: function(data){
				
				$('#onoff').val(data.onoff);
				$('#magazineViewForm').submit();
			},
			error : function(err){
				alert("실패")
			}
			
		});
		
	}
}