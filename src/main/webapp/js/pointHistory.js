$(function(){
	historyDataLoad(1)
});

function historyDataLoad(pg){
	$('#tbody').empty();
	
	$.ajax({
		type:'post',
		url:'/simriTest/member/pointHistoryList',
		data: {
			'email' : $('#email').val(),
			'pg': pg
		},
		dataType:'json',
		success: function(data){
			
			if(JSON.stringify(data.list) == '[]'){
				$('<tr/>').append($('<td/>',{
					colspan:3,
					style: 'width: 50px; margin-top: 50px;',
					text:  '구매내역이 없습니다',
					align: 'center',
					class:'align-middle',
				})).appendTo($('#pointListTable'));
			}
			
			$.each(data.list, function(index,items){
				var point = "+ "+items.plusPoint;
				if(items.plusPoint == 0){
					point = "- "+items.minusPoint;
				}
				
				$('<tr/>').append($('<td/>',{
					text:items.pointLogtime,
				})).append($('<td/>',{
					text:items.pointContent,
				})).append($('<td/>',{
					text:point +"p",
				})).appendTo($('#pointListTable'));
			});
			
			// 페이징처리
			$('#pointListPaging').html(data.PointHistoryPaging.pagingHTML);
		},
		error : function(err){
			console.log(err);
		}
	});
}

function pointHistoryPaging(pg){
	historyDataLoad(pg);
}