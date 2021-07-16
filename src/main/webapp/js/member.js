//로그인
$('#member_login').click(function(){
//	window.open("/simriTest/member/login", "로그인", "width=500 height=680 left=600 top=200 resizable=0");
	showPopup('login');
});

//회원가입
$('#member_writeForm').click(function(){
//	window.open("/simriTest/member/memberWriteForm", "회원가입", "width=600 height=730 left=600 top=200 resizable=0");
	showPopup('write');
});

function showPopup(page){
	   var nWidth;
	   var nHeight;           
	   var pageURL;
	   
	   if(page == 'login'){
		   pageURL = "/simriTest/member/login";
		   nWidth = "550";
		   nHeight = "680";
	   }else if(page == 'write'){
		   pageURL = "/simriTest/member/memberWriteForm";
		   nWidth = "600";
		   nHeight = "730";
	   }

	   var xPos = (document.body.clientWidth / 2) - (nWidth / 2); 
	   xPos += window.screenLeft;    //듀얼 모니터
	   var yPos = (screen.availHeight / 2) - (nHeight / 2);
	   
	   window.open(pageURL, page,"width="+nWidth+",height="+nHeight+", left="+xPos+", top="+yPos+", toolbars=no, resizable=no, scrollbars=no");
	   
	   // 새로고침 방지 ( Ctrl+R, Ctrl+N, F5 )
	    if ( event.ctrlKey == true && ( event.keyCode == 78 || event.keyCode == 82 ) || event.keyCode == 116) {
	         event.keyCode = 0;
	         event.cancelBubble = true;
	         event.returnValue = false;
	    }

	}