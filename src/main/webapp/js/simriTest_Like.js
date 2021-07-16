// 글 좋아요
function likesingo(num){
	if(num==1){//글 좋아요
		if($('#memId').val()==''){
			alert('로그인이 필요한 서비스 입니다');
			return;
		}else{
			$.ajax({
				type: 'post',
				url: '/simriTest/simriTest/testLike',
				data: {
					'seq':$('#seq').val(),
					'category' : $('#category').val()
				},
				dataType:'json',
				success: function(data){
					
					$('#onoff').val(data.onoff);
					$('#simriTestReadForm').submit();
				},
				error : function(err){
					alert("실패")
				}
				
			});
		}
	}
}