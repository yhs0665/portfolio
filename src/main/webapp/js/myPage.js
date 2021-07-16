
//회원정보수정
$('#modifyBtn').click(function(){
	$('#nameDiv').empty(); // 횐정수정에선.. 로그인했으니까.. 한사람으 데이터니까 데이터가 충돌날일없어 그래서 걍
							// 일케써도된대
	$('#pwdDiv').empty();
	$('#repwdDiv').empty();
	
	if(checkAll()){
		$('#modifyForm').submit(); // 서브밋하면 액션을 찾아.. 모디파이폼.jsp의 액션을 찾음
		alert("회원정보가 수정되었습니다");
	}
	
	
});
	 
function checkAll() {
	if (!checkName($('#modifyForm #name').val())) {
		return false;
	} else if (!checkNickname($('#modifyForm #nickname').val())) {
		return false;
	} else if (!checkMail($('#modifyForm #email').val())) {
		return false;
	} else if (!checkPassword($('#modifyForm #pwd').val(), $('#modifyForm #repwd').val())) {
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
// 이름검사------------------------------------------------
function checkName(name) {
	if (!checkExistData(name)) {
		$('#modifyForm #nameDiv').text("이름을 입력해주세요!");
		$('#modifyForm #name').focus();
		return false;
	}

	var nameRegExp = /^[가-힣]{2,10}$/;
	if (!nameRegExp.test(name)) {
		$('#modifyForm #nameDiv').text("2자이상 10자 이내 한글만 가능");
		$('#modifyForm #name').focus();
		return false;
	}
	// $('#writeForm #nameDiv').empty();
	return true; // 확인이 완료되었을 때
}

// 닉네임검사--------------------------------------------------
function checkNickname(nickname) {
	if (!checkExistData(nickname)) {
		$('#modifyForm #nicknameDiv').text("닉네임을 입력해주세요!");
		$('#modifyForm #nickname').focus();
		return false;
	}

	var nicknameRegExp = /^[가-힣A-Za-z]{2,8}$/;
	if (!nicknameRegExp.test(nickname)) {
		$('#modifyForm #nicknameDiv').text("4~12자의 한글  또는 영문 대소문자");
		$('#modifyForm #nicknameDiv').focus();
		return false;
	}
	
	if(nickname == '관리자' || nickname=='admin'){
		alert("사용 불가능한 닉네임입니다");
		$('#writeForm #nickname').focus();
		return false;
	}
	
	return true; // 확인이 완료되었을 때
}

// 이메일-----------------------------------------------------
function checkMail(email) {
	// mail이 입력되었는지 확인하기
	if (!checkExistData(email)) {
		$('#writeForm #emailDiv').text("이메일을 입력해주세요");
		$('#writeForm #email').focus();

		return false;
	}

	var emailRegExp = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
	if (!emailRegExp.test(email)) {
		$('#writeForm #emailDiv').text("이메일 형식이 올바르지 않습니다　예)id@domain.com");
		$('#writeForm #emailDiv').focus();
		$('#writeForm #email').val() == "";

		return false;
		
	}
	// $('#writeForm #emailDiv').empty();
	return true; // 확인이 완료되었을 때
}

// 비번검사-----------------------------------------------------
function checkPassword(pwd, repwd) {
	// 비밀번호가 입력되었는지 확인하기
	if (!checkExistData(pwd)) {
		$('#modifyForm #pwdDiv').text("비밀번호를 입력해주세요");
		$('#modifyForm #pwd').focus();
		return false;
	}
	// 비밀번호 확인이 입력되었는지 확인하기
	if (!checkExistData(repwd)) {
		$('#modifyForm #repwdDiv').text("비밀번호 확인을 입력해주세요");
		$('#modifyForm #repwd').focus();
		return false;
	}

	var pwdRegExp = /^[a-zA-z0-9]{4,12}$/; // 비밀번호 유효성 검사
	if (!pwdRegExp.test(pwd)) {
		$('#modifyForm #pwdDiv').text("비밀번호는 영문 대소문자와 숫자 4~12자리로 입력해야 합니다");
		$('#modifyForm #pwdDiv').val() == "";
		$('#modifyForm #pwdDiv').focus();
		return false;
	}
	
	// 비밀번호와 비밀번호 확인이 맞지 않다면.
	if (pwd != repwd) {
		$('#modifyForm #repwdDiv').text("비밀번호가 일치하지 않습니다");
		$('#modifyForm #pwdDiv').val() == "";
		$('#modifyForm #repwdDiv').val() == "";
		$('#modifyForm #repwdDiv').focus();
		return false;
	}
	return true; // 확인이 완료되었을 때
}



// 닉네임 변경권-----------------------------------------
$('#nicknameChangeBtn').click(function(){
	if($('#check').val() > 0) {
		window.open("/simriTest/member/nicknameChange", "닉네임변경", "width=550 height=230 right=500 top=100 resizable=0");
	} else if($('#check').val() == 0){
		alert("닉네임이 변경권이 없습니다. 상점에서 구매 후 변경 가능합니다")
	}
});


// -------------내가쓴글목록-------------------------------------------------
$(function(){
	writeDataLoad(1);
	
	 if($("#tabTwo").is(":checked")){
		      replyDataLoad(1);
		   } else if($("#tabThree").is(":checked")){
		      likeDataLoad(1);
		   } 
});
$('#tabOne').click(function(){
	writeDataLoad(1);
	
});

// --- 내가쓴글목록
function writeDataLoad(pg){

	$('#writeBody').empty();
	
	$.ajax({
		type:'post',
		url:'/simriTest/member/getMypageBoardList', // 내가쓴글목록
		data: {
			'email' : $('#email').val(),
			'pg': pg
		},
		dataType:'json',
		success: function(data){
			
			if(JSON.stringify(data.list) == '[]'){
				$('<tr/>').append($('<td/>',{
					style: 'width: 50px; margin-top: 50px;',
					text:  '작성하신 게시글이 없습니다',
					align: 'center',
					class:'align-middle',
				})).appendTo($('#myPageBoardTable'));
			}
			
			$.each(data.list, function(index,items){
				$('<tr/>').append($('<td/>',{
					style: 'width: 50px;',
					align: 'center',	
					
				}).append($('<input/>',{
					type:'checkbox',
	          		name:'check',
	          		style: 'margin-top: 50px;',
	          		value: items.seq,
	          		onclick: 'checkSelectAll(this)',
	          		
				}))).append($('<td/>',{
					align: 'center',
					style: 'width: 75px; padding: 0px;',
					
				}).append($('<img/>',{
					src:'/simriTest/storage/'+items.image,
	                style: 'width: 150px; height: 100px; margin-top: 10px; cursor: pointer;',
					class:'align-middle',			
					
				}))).append($('<td/>',{
					style: 'width: 700px;',
					
				}).append($('<div/>',{
					class:'card-body bg-light',
					style:'padding-left: 40px; margin-left: 50px; margin-top: 0px; padding: 40px 10px;',
					
				}).append($('<div/>',{
					class:'row',
					
				}).append($('<div/>',{
					class:'col-md-7',
					style:'margin-right: 30px; ',
				}).append($('<strong/>')
						.append($('<a/>',{
							 href:'#',
							 class:'text-dark myWriteView'+items.seq,
							 style:'text-decoration-line: none; margin-left: 30px; text-overflow:ellipsis; overflow:hidden; display: -webkit-box; -webkit-line-clamp: 1;  -webkit-box-orient: vertical; word-wrap:break-word;',
							 text:items.subject,
							 
							 
				})))).append($('<div/>',{
					class:'col-md-2',
					style:'padding-right: 0',
					text:items.comLogtime,
					
				})).append($('<div/>',{
					class:'col-md-2',
					text:' | ',
				}).append($('<input/>',{
					type:'button',
					value:'삭제',
					class:'btn btn-danger btn-sm font-monospace myPageBoardDeleteBtn',
					style:'margin-left: 10px;',
				})))))).appendTo($('#myPageBoardTable'));
				
				
				$('.myWriteView'+items.seq).click(function(){
					
					if(items.category == '[팔레트]빨강'){
						items.category = 2;
					}else if(items.category == '[팔레트]파랑'){
						items.category = 3;
					}else if(items.category == '[팔레트]초록'){
						items.category = 4;
					}else if(items.category == '[팔레트]노랑'){
						items.category = 5;
					}else if(items.category == '유머 게시판'){
						items.category = 6;
					}else if(items.category == '연애 게시판'){
						items.category = 7;
					}
					$.cookie('totalSearchCookie', 'bo', {expires:1, path: '/' });					
					$('#com').val(0);
					$('#category').val(items.category); 
					$('#seq').val(items.seq); 
					$('#myPageListForm').submit();
					return;
				})
				
				
			});	// each
			
			// 개별 삭제
			$('.myPageBoardDeleteBtn').click(function(){
				if(!(confirm('정말 삭제하시겠습니까?'))){
					return;
				}
				
				const seq = $(this).parent().parent().parent().parent().prev().prev().children().val();
				$.ajax({
					type : 'POST',
					url : '/simriTest/member/myPageBoardDeleteOne',
					data : 'check=' + seq,
					success : function(data) {
						location.href="/simriTest/member/myPage";
					},
					error : function(err) {
						console.log(err);
						alert('로그인 실패');
					}
				});// ajax
			});
			
			// 페이징처리
			$('#MypageBoardPagingDiv').html(data.MypageBoardPaging.pagingHTML);
			
		},
		error:function(err){
			 alert(err);
		}
	});
}




// ---마이페이지보드페이징
function mypagePaging(pg){
	$('#myPagePg').val(pg);
	writeDataLoad(pg);
}


// -------------내가쓴댓글목록-------------------------------------------------
$('#tabTwo').click(function(){
	replyDataLoad(1);
});

// ---마이페이지댓글목록
function replyDataLoad(pg){
	$('#replyBody').empty();
		
		$.ajax({
			type:'post',
			url:'/simriTest/member/getMypageReplyList', // 내가쓴댓글목록
			data: {
				'email' : $('#email').val(),
				'pg': pg
			},
			dataType:'json',
			success: function(data){
				
				if(JSON.stringify(data.list) == '[]'){
					$('<tr/>').append($('<td/>',{
						style: 'width: 50px; margin-top: 50px;',
						text:  '작성하신 댓글이 없습니다',
						align: 'center',
						class:'align-middle',
					})).appendTo($('#myPageReplyTable'));
				}
				
				
				$.each(data.list, function(index,items){
					
					$('<tr/>').append($('<td/>',{
						// style: 'width: 50px;',
						align: 'center',	
					}).append($('<input/>',{
						type:'checkbox',
		          		name:'check',
		          		style: 'margin-top: 50px;',
		          		value: items.replySeq,
		          		onclick: 'checkReplySelectAll(this)',
					}))).append($('<td/>',{
						// align: 'center',
						style: 'padding: 0px;',
						
					}).append($('<div/>',{
						class:'card-body bg-light',
						style:'padding-left: 40px; margin-left: 50px; margin-top: 0px; padding: 40px 10px;',
						
					}).append($('<div/>',{
						class:'row',
						
					}).append($('<div/>',{
						class:'col-md-7',
						style:'margin-right: 30px; ',
					}).append($('<strong/>')
							.append($('<a/>',{
								 href:'#',
								 class:'text-dark myReplyView'+items.seq,
								 style:'text-decoration-line: none; margin-left: 30px; text-overflow:ellipsis; overflow:hidden; display: -webkit-box; -webkit-line-clamp: 1;  -webkit-box-orient: vertical; word-wrap:break-word;',
								 text:items.replyComment,
						
					})))).append($('<div/>',{
						class:'col-md-2',
						style:'padding-right: 0',
						text:items.replyLogtime,
						
					})).append($('<div/>',{
						class:'col-md-2',
						text:' | ',
					}).append($('<input/>',{
						type:'button',
						value:'삭제',
						class:'btn btn-danger btn-sm font-monospace myPageReplyDeleteBtn',
						style:'margin-left: 10px;',
					})))))).appendTo($('#myPageReplyTable'));
					
					
					$('.myReplyView'+items.seq).click(function(){
						
						if(items.category == '[팔레트]빨강'){
							items.category = 2;
						}else if(items.category == '[팔레트]파랑'){
							items.category = 3;
						}else if(items.category == '[팔레트]초록'){
							items.category = 4;
						}else if(items.category == '[팔레트]노랑'){
							items.category = 5;
						}else if(items.category == '유머 게시판'){
							items.category = 6;
						}else if(items.category == '연애 게시판'){
							items.category = 7;
						}
						
						$('#com').val(0);
						$('#category').val(items.category); 
						$('#seq').val(items.seq); 
						$.cookie('totalSearchCookie', 'bo', {expires:1, path: '/' });
						$('#myPageListForm').submit();
						return;
					})
					
					
					
				});	// each
				
				// 개별 삭제
				$('.myPageReplyDeleteBtn').click(function(){
					if(!(confirm('정말 삭제하시겠습니까?'))){
						return;
					}
					
					const seq = $(this).parent().parent().parent().parent().prev().children().val();
					
					$.ajax({
						type : 'post',
						url : '/simriTest/member/myPageReplyDelete',
						data : 'check=' + seq,
						success : function(data) {
							replyDataLoad(1);
							// location.href="/simriTest/member/myPage";
						},
						error : function(err) {
							console.log(err);
							alert('로그인 실패');
						}
					});// ajax
				});
				
				// 페이징처리
				$('#MypageReplyPagingDiv').html(data.MypageReplyPaging.pagingHTML);
				
			},
			error:function(err){
				alert("댓글로드실패");
			}
		});
	}

// ---마이페이지댓글페이징
function myPageReplyPaging(pg){
	$('#myPagePg').val(pg);
	replyDataLoad(pg);
}


// 마이페이지 찜 목록 ------------------
$('#tabThree').click(function(){
	likeDataLoad(1);
});

// ---마이페이지찜 페이징
function likeDataLoad(pg){
	$('#myPageLikeTable').empty();
	
	$.ajax({
		type:'post',
		url:'/simriTest/member/getMypageLikeList', // 내가찜한목록
		data: {
			'email' : $('#email').val(),
			'pg': pg
		},
		dataType:'json',
		success: function(data){
			
			if(JSON.stringify(data.list) == '[]'){
				$('<tr/>').append($('<td/>',{
					style: 'width: 50px; margin-top: 50px;',
					text:  '찜한 목록이 없습니다',
					align: 'center',
					class:'align-middle',
				})).appendTo($('#myPageLikeTable'));
				return
			}// if
			
			$('.likeBody').empty();
			
			//show each
			$.each(data.list, function(index,items){
				
				$('<div/>',{
					class: 'col',
				}).append($('<div/>',{
					class: 'card  h-100',
				}).append($('<img/>',{
					src:'/simriTest/storage/'+items.image,
					style: 'height:150px; width:auto; cursor: pointer;',
					class:'card-img-topmy myLikeView'+items.seq,
					href : '#'
				})).append($('<div/>',{
					class:'card-body',
				}).append($('<h5/>',{
					class:'card-title',
					text: items.subject,
					style:' text-overflow:ellipsis; overflow:hidden; display: -webkit-box; -webkit-line-clamp: 1;  -webkit-box-orient: vertical; word-wrap:break-word;'
				})))).appendTo($('.likeBody'));
							
				$('.myLikeView'+items.seq).click(function(){
					if(items.category == '[팔레트]빨강'){
						items.category = 2;
					}else if(items.category == '[팔레트]파랑'){
						items.category = 3;
					}else if(items.category == '[팔레트]초록'){
						items.category = 4;
					}else if(items.category == '[팔레트]노랑'){
						items.category = 5;
					}else if(items.category == '유머 게시판'){
						items.category = 6;
					}else if(items.category == '연애 게시판'){
						items.category = 7;
					}else if(items.category == '고민&상담 게시판'){
						items.category = 8;
					}else if(items.category == '공지사항'){
						items.category = 9;
					}else if(items.category == '심리 테스트'){
						items.category = 11;
					}else if(items.category == '연애 심리글'){
						items.category = 12;
					}
					$('#com').val(0);
					$('#category').val(items.category);
					$('#seq').val(items.seq); 
					$.cookie('totalSearchCookie', 'babu', {expires:1, path: '/' });
					if(items.category == 11){
						$('#myPageListForm').attr('action','/simriTest/simriTest/simriTestRead').submit();
					}else if(items.category ==12){
						$('#myPageListForm').attr('action','/simriTest/love/magazineContent').submit();
					}
					$('#myPageListForm').submit();
					return;
				})
			});//show each
		
			
			// 페이징처리
			$('#MypageLikePagingDiv').html(data.MypageLikePaging.pagingHTML);
			
		},// success
		error:function(err){
			console.log(err);
		}// error
	});// ajax
}

// ---마이페이지찜 페이징
function myPagelikePaging(pg){
	$('#myPagePg').val(pg);
	likeDataLoad(pg);
}


// 전체선택 or 전체해제--------------------------------
$('#allCheck').click(function(){
	
	if($('#allCheck').prop('checked')){
		$('input[name=check]').prop('checked',true);
	}else{
		$('input[name=check]').prop('checked',false);
	}
});


// 글선택삭제
$('#choiceDeleteBtn').click(function (){
	var count = $('input[name=check]:checked').length;
	
	if(count == 0){
		alert("삭제할 항목을 선택하세요");
	}else{
		if(confirm("정말로 삭제하시겠습니까?"))
			$('#myPageBoardDeleteForm').submit();
	}
	
});

// 댓글선택삭제
$('#replyChoiceDeleteBtn').click(function (){
	var count = $('input[name=check]:checked').length;

	if(count == 0){
		alert("삭제할 항목을 선택하세요");
	}else{
		if(confirm("정말로 삭제하시겠습니까?")){
			$.ajax({
				type : 'post',
				url : '/simriTest/member/myPageReplyDelete',
				data : $('#myPageReplyDeleteForm').serialize(),
				success : function(data) {
					$('#allReplyCheck').prop('checked',false)
					replyDataLoad(1);
				},
				error : function(err) {
					console.log(err);
					alert('로그인 실패');
				}
			});// ajax
			
		}
	}
	
});

// 하나 체크가 풀리면 전체체크 풀리게
function checkSelectAll(checkbox)  {
    const selectall = document.querySelector('input[id="allCheck"]');
    
    if(checkbox.checked === false)  {
      selectall.checked = false;
    }
  }





// 전체선택 or 전체해제 (댓글)
$('#allReplyCheck').click(function(){
	
	if($('#allReplyCheck').prop('checked')){
		$('input[name=check]').prop('checked',true);
	}else{
		$('input[name=check]').prop('checked',false);
	}
});


// 하나 체크가 풀리면 전체체크 풀리게 (댓글)
function checkReplySelectAll(checkbox)  {
    const selectall = document.querySelector('input[id="allReplyCheck"]');
    
    if(checkbox.checked === false)  {
      selectall.checked = false;
    }
  }



// 프사 변경-------------------------------
$('#profileBtn').click(function(e){
	e.preventDefault();
	$('#file').click();
	
});


// 이미지 클릭시 변경
$('#profileImg').click(function(e){ 
    document.profile_upload.profile_url.value = document.getElementById( 'profileImg' ).src;
    e.preventDefault();
    $('#file').click();
    
}); 
function changeValue(obj){
	   document.profile_upload.submit();

	}


// 포인트스토어 구입창-----------------------------------
$('.buyBtn').click(function(){
	if($(this).attr('id') == $('#hiddenPalette').val()){
		alert("같은 색상은 구입할 수 없습니다");
		return;
	}
	
	if(parseInt($(this).next().val()) > parseInt($('#mypoint').val())){
		alert("보유 포인트가 부족합니다");
		return;
	}
	

	if('rainbow' == $('#hiddenPalette').val() && ($(this).attr('id') == 'red'   ||
												  $(this).attr('id') == 'green' ||
											      $(this).attr('id') == 'blue'  ||
											      $(this).attr('id') == 'yellow')){
		if(!confirm("색상변경시 레인보우 기능을 잃게됩니다. 정말 구입하시겠습니까?")){
			return;
		}
	}else if(!confirm("정말 구입하시겠습니까?")){

		return;
	}

	
	$.ajax({
		type:'post',
		url:'/simriTest/member/pointstore',
		data: {
			'email' : $('#email').val(),
			'item' : $(this).attr('id'),
			'cost' : $(this).next().val(),
			'pointHistory' :$(this).next().attr('class'),
		},
		dataType:'text',
		success: function(data){
			
			alert("결제가 완료되었습니다")
			location.href='/simriTest/member/pointStore';
		},
		error: function(err) {
			alert(err)
		}
		
	});
	
});
// 회원탈퇴----
$('#exitBtn').click(function(){
	location.href="/simriTest/member/exitMember"
});


$('.pointChargeBtn').click(function(){

	// getter
    var IMP = window.IMP;
    IMP.init('imp73251238');
    var money = $(this).next().val();

    IMP.request_pay({
    	  pg: "kakaopay",
    	    pay_method: "kakaopay",
    	    merchant_uid: 'merchant_' + new Date().getTime(),
    	    name: "포인트 충전 "+money+"원" ,
    	    amount: money
    }, function (rsp) {
          console.log(rsp);
        if (rsp.success) {
            var msg = '결제가 완료되었습니다.';
            msg += '고유ID : ' + rsp.imp_uid;
            msg += '상점 거래ID : ' + rsp.merchant_uid;
            msg += '결제 금액 : ' + rsp.paid_amount;
            msg += '카드 승인번호 : ' + rsp.apply_num;
            
            $.ajax({
                type: "GET", 
                url: "/simriTest/member/pointCharge", // 충전 금액값을 보낼 url 설정
                data: {
                	"email" : $('#email').val(),
                    "point" : money,
                    "pointHistory" : "포인트 결제"
                },
                success: function(){
        			alert("결제가 완료되었습니다")
        			location.href='/simriTest/member/pointStore';
                }
            });
            
        } else {
            var msg = '결제에 실패하였습니다';
            msg += '에러내용 : ' + rsp.error_msg;
        }
        alert(msg);
    });
});



// 회원탈퇴-----

$('#exitPwdBtn').click(function(){
	$('#exitDiv').empty();
	
	
	if ((radioChecked('palette')) == "") {
		$('#exitDiv').text("탈퇴 사유를 체크해주세요");
		$('#exitDiv').css('color', 'red');
		$('#exitDiv').css('font-size', '10pt');
		$('#exitDiv').css('font-weight', 'bold');
		
		return false;
	
	}else{
		
		var reason = null;
		if($('#inlineRadio1').is(":checked")){
			reason = '이용빈도 낮음';
		}else if($('#inlineRadio2').is(":checked")){
			reason = '재미없음';
		}else if($('#inlineRadio3').is(":checked")){
			reason = '컨텐츠 부족';
		}else if($('#inlineRadio4').is(":checked")){
			reason = $('#exit-reason').val();
		}
	
		$.ajax({
			type:'post',
			url:'/simriTest/member/realExitMember',
			data: {'email' : $('#email').val(),
				'reason' : reason},
			dataType:'text',
			success: function(data){
				alert("회원탈퇴가 완료되었습니다");
				
				    window.location="/simriTest/index.jsp"; // loginForm 이동
				
			},
			error : function(err){
				console.log(err);
			}
		});
	
	
		}
	
	
	
	
});

function radioChecked(name) {
	var _flag = false;

	$('input:radio[name="' + name + '"]').each(function() {
		if ($(this).is(":checked")) {
			_flag = true;
		}
	});

	return _flag;
}


$('#myBoardListBtn').change(function(){
	$('#tabTwo').trigger('click');
	window.reload();
});




