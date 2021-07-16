//아뒤기억하기
$(document).ready(function(){
    // 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.
    var userInputId = getCookie("userInputId"); //저장된 쿠키값 가져오기
    $("input[name='email']").val(userInputId); 
     
    if($("input[name='email']").val() != ""){ // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
        $("#idSaveCheck").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
    }
     
    $("#idSaveCheck").change(function(){ // 체크박스에 변화가 있다면,
        if($("#idSaveCheck").is(":checked")){ // ID 저장하기 체크했을 때,
            var userInputId = $("input[name='email']").val();
            setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
        }else{ // ID 저장하기 체크 해제 시,
            deleteCookie("userInputId");
        }
    });
     
    // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
    $("input[name='email']").keyup(function(){ // ID 입력 칸에 ID를 입력할 때,
        if($("#idSaveCheck").is(":checked")){ // ID 저장하기를 체크한 상태라면,
            var userInputId = $("input[name='email']").val();
            setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관 - 7일 이후 만료되는 userInputId라는 이름의 쿠키에  userInputId라는 값을 저장한다.
        }
    });
});
 
//쿠키값 set
function setCookie(cookieName, value, exdays){
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + exdays); //설정 일수만큼 현재시간에 만료값으로 지정
    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
    document.cookie = cookieName + "=" + cookieValue;
}
 
//쿠키값 delete
function deleteCookie(cookieName){
    var expireDate = new Date();
    expireDate.setDate(expireDate.getDate() - 1);
    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
}
 
//쿠키값 가져오기
function getCookie(cookieName) {
    cookieName = cookieName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cookieName);
    var cookieValue = '';
    if(start != -1){
        start += cookieName.length;
        var end = cookieData.indexOf(';', start);
        if(end == -1)end = cookieData.length;
        cookieValue = cookieData.substring(start, end);
    }
    return unescape(cookieValue); //unescape로 디코딩 후 리턴
}





//로그인 버튼을 눌렀을 때
$('#loginBtn').click(function() {
	$('#emailDiv').empty();
	$('#pwdDiv').empty();

	if ($('#email').val() == '') {
		$('#emailDiv').text("아이디를 입력하세요");
		$('#emailDiv').css('color', 'red');
		$('#emailDiv').css('font-size', '10pt');
		$('#emailDiv').css('font-weight', 'bold');
		$('#email').focus();
		return;
	} else if ($('#pwd').val() == '') {
		$('#pwdDiv').text("비밀번호를 입력하세요");
		$('#pwdDiv').css('color', 'red');
		$('#pwdDiv').css('font-size', '10pt');
		$('#pwdDiv').css('font-weight', 'bold');
		$('pwd').focus();
		return;
	}
	
	$.ajax({
		type : 'post',
		url : '/simriTest/member/login',
		data : {
			'email' : $('#email').val(),
			'pwd' : $('#pwd').val()
		},
		dataType : 'json',
		success : function(data) {
		
			if (data.result == 'success') {
				window.close();
				window.opener.location.href="/simriTest/index.jsp";
			} else if (data.result == 'fail'){
				$('#pwdDiv').text("가입하지 않은 아이디이거나, 잘못된 비밀번호입니다");
				$('#pwdDiv').css('color', 'red');
				$('#pwdDiv').css('font-size', '10pt');
				$('#pwdDiv').css('font-weight', 'bold');
				$('pwd').focus();
			} else {
				alert("해당 계정은 '" + data.memberCondition.stopReason + "' 와(과) 같은 사유로 " + data.memberCondition.stopPeriod + "일간 정지되었습니다");
				
			}
		},
		error : function(err) {
			console.log(err);
			alert('로그인 실패');
		}
	});// ajax
});// click




