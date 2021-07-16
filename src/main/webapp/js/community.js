//게시판 글쓰기
$('#communityWriteBtn').click(function(){
	var red = '[팔레트]빨강';
	var blue = '[팔레트]파랑';
	var green = '[팔레트]초록';
	var yellow = '[팔레트]노랑';
	
	$('#subjectDiv').empty();
	$('#contentDiv').empty();
	
	
    if($("#category").val() == 'none') {
		alert("카테고리를 정해주세요")
		return false;
		
	}else if($("#palette").val() == 'yellow'){
		if($("#category option:selected").val() == red || $("#category option:selected").val() == blue || $("#category option:selected").val() == green){
			alert("소속 팔레트 게시판만 이용 하실 수 있습니다");
			return;
		}
	}else if($("#palette").val() == 'blue'){
		if($("#category option:selected").val() == red || $("#category option:selected").val() == yellow || $("#category option:selected").val() == green){
			alert("소속 팔레트 게시판만 이용 하실 수 있습니다");
			return;
		}
	}else if($("#palette").val() == 'red'){
		if($("#category option:selected").val() == yellow || $("#category option:selected").val() == blue || $("#category option:selected").val() == green){
			alert("소속 팔레트 게시판만 이용 하실 수 있습니다");
			return;
		}
	}else if($("#palette").val() == 'green'){
		if($("#category option:selected").val() == red || $("#category option:selected").val() == blue || $("#category option:selected").val() == yellow){
			alert("소속 팔레트 게시판만 이용 하실 수 있습니다");
			return;
		}
	}else if($("#palette").val() == 'white'){
		if($("#category option:selected").val() == red || $("#category option:selected").val() == blue || $("#category option:selected").val() == green || $("#category option:selected").val() == yellow){
			alert("소속이 없습니다. 상점에서 구매해주세요");
			return;
		}
	}
    
	if($('#subject').val() == '') {
		$('#subjectDiv').html("제목 입력");
		$('#subjectDiv').css('color', 'red');
		$('#subjectDiv').css('font-size', '10pt');
		$('#subjectDiv').css("font-weight", 'bold');
	}else if($('#content').val() == '') {
		$('#contentDiv').html("내용 입력");
		$('#contentDiv').css('color', 'red');
		$('#contentDiv').css('font-size', '10pt');
		$('#contentDiv').css("font-weight", 'bold');
	}else{
		
		 $('#communityWriteForm').submit();
	}
});

