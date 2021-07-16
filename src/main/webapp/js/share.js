function kakaoShare1(){
    Kakao.Link.sendDefault({
        objectType: 'feed',
        content: {
        title: '뭐하고 심리? 심리테스트 플랫폼',
        description: '심테, 심리테스트, 성격유형',
        imageUrl:
        	'https://lh3.googleusercontent.com/proxy/xKSySoJy28pYDjnfRyDEyMmrc53yimcUDY302Ir1uG663rYaM6xw_gE-8k4SHmncB38EkfK8snby_TjKbco5isjTbO9iuEk9ZWIK4rFPL0wJByBhd0w',
        link: {
            mobileWebUrl: 'https://developers.kakao.com',
            androidExecParams: 'test',
        },
        },
        social: {
        likeCount: 87,
        commentCount: 35,
        sharedCount: 49,
        },
        buttons: [
        {
            title: '웹으로 이동',
            link: {
            mobileWebUrl: 'https://developers.kakao.com',
            },
        },
        {
            title: '앱으로 이동',
            link: {
            mobileWebUrl: 'https://developers.kakao.com',
            },
        },
        ]
    });
		if($('#memId').val()==''){
			alert('공유 포인트 적립을 원하시면 사이트 로그인을 해주세요');
			return;
		}else{
			    $.ajax({
					type: 'post',
					url: '/simriTest/love/loveShare',
					data: {
						'seq':$('#seq').val(),
					},
					success: function(data){
						$('#simriTestViewForm').submit();
					},
					error : function(err){
						alert("실패")
					}
					
				});
			}
		}    
    



function kakaoShare2(){
    Kakao.Link.sendDefault({
        objectType: 'feed',
        content: {
        title: '뭐하고 심리? 심리테스트 플랫폼',
        description: '심테, 심리테스트, 성격유형',
        imageUrl:
            'https://lh3.googleusercontent.com/proxy/xKSySoJy28pYDjnfRyDEyMmrc53yimcUDY302Ir1uG663rYaM6xw_gE-8k4SHmncB38EkfK8snby_TjKbco5isjTbO9iuEk9ZWIK4rFPL0wJByBhd0w',
        link: {
            mobileWebUrl: 'https://developers.kakao.com',
            androidExecParams: 'test',
        },
        },
        social: {
        likeCount: 101,
        commentCount: 48,
        sharedCount: 87,
        },
        buttons: [
        {
            title: '웹으로 이동',
            link: {
            mobileWebUrl: 'https://developers.kakao.com',
            },
        },
        {
            title: '앱으로 이동',
            link: {
            mobileWebUrl: 'https://developers.kakao.com',
            },
        },
        ]
    });
    
    if($('#memId').val()==''){
		alert('공유 포인트 적립을 원하시면 사이트 로그인을 해주세요');
		return;
	}else{
	    $.ajax({
			type: 'post',
			url: '/simriTest/simriTest/simriShare',
			data: {
				'seq':$('#seq').val(),
			},
			success: function(data){
				$('#simriTestReadForm').submit();
			},
			error : function(err){
				alert("실패")
			}
			
		});
	}
    
    
}