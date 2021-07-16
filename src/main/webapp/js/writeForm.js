$('#writeFormBtn').click(function() {
   $('#writeForm #nameDiv').empty();
   $('#writeForm #nicknameDiv').empty();
   $('#writeForm #pwdDiv').empty();
   $('#writeForm #repwdDiv').empty();
   $('#writeForm #paletteDiv').empty();
   $('#writeForm #emailDiv').empty();
   $('#writeForm #emailConfirmDiv').empty();   
   
   if(checkAll()){
      $.ajax({
         url : '/simriTest/member/write',
         type : 'post',
         data : $('#writeForm').serialize(),
         dataType : 'text',
         success : function(data) {
            alert("회원가입이 완료되었습니다. 로그인 해주세요");
            window.open('about:blank', '_self').close();
         },
         error : function(err) {
            alert("실패");
            console.log(err);
         }
      });//ajax
   }
});

function checkAll() {

	if (!checkName($('#writeForm #name').val())) {
		return false;
	} else if (!checkNickname($('#writeForm #nickname').val())) {
		return false;
	} else if (!checkMail($('#writeForm #email').val())) {
		return false;
	} else if (!checkPassword($('#writeForm #pwd').val(),
							  $('#writeForm #repwd').val())) {
		return false;
	} else if ((radioChecked('palette')) == "") {
		$('#writeForm #paletteDiv').text("성향을 체크해주세요");
		return false;
	} else if ($('#writeForm #hiddenCheckId').val() != $('#writeForm #email').val()){
		$('#writeForm #emailDiv').text("이메일을 다시 입력하세요");
		return false;
	} else if ($('#emailConfirm').val() != $('#hiddenEmailConfirm').val()) {
		$('#writeForm #emailConfirmDiv').text("이메일 인증 문자를 다시 확인해주세요");
		return false;
	} 
	return true;

}

// 공백확인 함수
function checkExistData(value) {
   if (value == "") {
      return false;
   }
   return true;
}

//이름검사------------------------------------------------
function checkName(name) {
   if (!checkExistData(name)) {
      $('#writeForm #nameDiv').text("이름을 입력해주세요");
      $('#writeForm #name').focus();
      return false;
   }

   var nameRegExp = /^[가-힣]{2,10}$/;
   if (!nameRegExp.test(name)) {
      $('#writeForm #nameDiv').text("2자이상 10자 이내 한글만 가능");
      $('#writeForm #name').focus();
      return false;
   }
   //$('#writeForm #nameDiv').empty();
   return true; //확인이 완료되었을 때
}

 

//닉네임검사--------------------------------------------------
function checkNickname(nickname) {
	if (!checkExistData(nickname)) {
		$('#writeForm #nicknameDiv').text("닉네임을 입력해주세요");
		$('#writeForm #nickname').focus();
		return false;
	}

	var nicknameRegExp = /^[가-힣A-Za-z]{2,8}$/;
	if (!nicknameRegExp.test(nickname)) {
		$('#writeForm #nicknameDiv').text("2~8자의 한글  또는 영문 대소문자");
		$('#writeForm #nickname').focus();
		return false;
	}
	
	if(nickname == '관리자' || nickname=='admin'){
		alert("사용 불가능한 닉네임입니다");
		$('#writeForm #nickname').focus();
		return false;
	}
	
	
	return true; //확인이 완료되었을 때
}

//이메일-----------------------------------------------------
function checkMail(email) {
	//mail이 입력되었는지 확인하기
	if (!checkExistData(email)) {
		$('#writeForm #emailDiv').text("이메일을 입력해주세요");
		$('#writeForm #email').focus();
		return false;
	}

	var emailRegExp = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
	if (!emailRegExp.test(email)) {
		$('#writeForm #emailDiv').text("이메일 형식이 올바르지 않습니다　예)id@domain.com");
		$('#writeForm #email').focus();
		$('#writeForm #email').val() == "";

		return false;
	}
	//	$('#writeForm #emailDiv').empty();
	return true; //확인이 완료되었을 때
}

//이메일 2-----------------------------------------------------
function focusoutCheckMail(email) {
	//mail이 입력되었는지 확인하기
	if (!checkExistData(email)) {
		$('#writeForm #emailDiv').text("이메일을 입력해주세요");
		return false;
	}

	var emailRegExp = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
	if (!emailRegExp.test(email)) {
		$('#writeForm #emailDiv').text("이메일 형식이 올바르지 않습니다　예)id@domain.com");

		return false;
	}
	//	$('#writeForm #emailDiv').empty();
	return true; //확인이 완료되었을 때
}

//비번검사-----------------------------------------------------
function checkPassword(pwd, repwd) {
   //비밀번호가 입력되었는지 확인하기
   if (!checkExistData(pwd)) {
      $('#writeForm #pwdDiv').text("비밀번호를 입력해주세요");
      $('#writeForm #pwd').focus();
      return false;
   }
   //비밀번호 확인이 입력되었는지 확인하기
   if (!checkExistData(repwd)) {
      $('#writeForm #repwdDiv').text("비밀번호 확인을 입력해주세요");
      $('#writeForm #repwd').focus();
      return false;
   }

   var pwdRegExp = /^[a-zA-z0-9]{4,12}$/; //비밀번호 유효성 검사
   if (!pwdRegExp.test(pwd)) {
      $('#writeForm #pwdDiv').text("비밀번호는 영문 대소문자와 숫자 4~12자리로 입력해야합니다");
      $('#writeForm #pwdDiv').val() == "";
      $('#writeForm #pwdDiv').focus();
      return false;
   }
   //비밀번호와 비밀번호 확인이 맞지 않다면..
   if (pwd != repwd) {
      $('#writeForm #repwdDiv').text("비밀번호가 일치하지 않습니다");
      $('#writeForm #pwdDiv').val() == "";
      $('#writeForm #repwdDiv').val() == "";
      $('#writeForm #repwdDiv').focus();
      return false;
   }
   return true; //확인이 완료되었을 때
}

function radioChecked(name) {
   var _flag = false;

   $('input:radio[name="' + name + '"]').each(function() {

      if ($(this).is(":checked")) {
         _flag = true;
      }

   });

   return _flag;
}

$('#sendConfirmBtn').click(function(){
	checkMail($('#writeForm #email').val());

	$('#writeForm #emailDiv').empty();
	if (!focusoutCheckMail($('#writeForm #email').val())) {
		return false;
	}

	$.ajax({
		type : 'post',
		url : '/simriTest/member/checkId',
		data : 'email=' + $('#writeForm #email').val(),
		dataType : 'text',
		success : function(data) {
			if(data == 'fail' || data == 'kakao' || data == 'google'){
				$('#writeForm #emailDiv').text("이미 사용중이거나 사용 불가능한 아이디입니다");
				$('#writeForm #email').focus();
				return;
			}
			
			alert("이메일이 발송되었습니다")
			$('#confirmRow').show();
			$('#emailConfirm').focus();
			
			$.ajax({
				type : 'post',
				url : '/simriTest/member/emailCheckMail',
				data : 'email=' + $('#writeForm #email').val(),
				dataType : 'json',
				success : function(data) {
					$('#hiddenCheckId').val($('#writeForm #email').val());
					$('#hiddenEmailConfirm').val(data.result);
				
				},
				error : function(err) {
					console.log(err);
				}
			})

			
			
		},
		error : function(err) {
			console.log(err);
			$('#writeForm #emailDiv').text('중복체크 실패');

			// return false;
		}// error
	});// ajax
});// click
