$(document).ready(function(){
	
	   $.ajax({
	      url: '/simriTest/love/getMagazine',
	      type: 'post',
	      data: 'seq='+$('#seq').val(),
	      dataType: 'json',
	      success: function(data){
	    	 //$.removeCookie('loveSideCookie', { path: '/' });
	         $('#hashtag1Span').text(data.communityDTO.hashtag1);
	         $('#image').attr('src','../storage/'+data.communityDTO.image);
	         $('#subjectSpan').text(data.communityDTO.subject);
	         $('#comLogtimeSpan').text(data.communityDTO.comLogtime);
	         $('#hitSpan').text(data.communityDTO.hit);
	         $('#hashtag1Span1').text(data.communityDTO.hashtag1);
	         $('#contentSpan').text(data.communityDTO.content);
	         $('#likeSpan').text(data.communityDTO.comLike);
	         
	      },
	      error: function(err){
	    	  alert("실패")
	         console.log(err);
	      }
	   });
});

