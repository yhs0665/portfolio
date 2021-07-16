package myPage.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class MyPageLikePaging {
	private int currentPage;
	private int pageBlock;
	private int pageSize;
	private int totalA;
	private StringBuffer pagingHTML;
	
	public void makePagingHTML() {
		pagingHTML = new StringBuffer();
		
		int totalP = (totalA+pageSize-1)/pageSize;
		
		int startPage = (currentPage-1) / pageBlock * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if(endPage > totalP) endPage = totalP;
		
		if(startPage > pageBlock) 
			pagingHTML.append("<li id='pagingNum' class='page-item'><a id='likePaging' class='page-link' onclick='myPagelikePaging("+(startPage-1)+")'>이전</a></li>");

		for(int i =startPage; i<=endPage; i++) {
			if(i==currentPage) {
				pagingHTML.append("<li id='pagingNum' class='page-item active' aria-current='page'><a id='likePaging' class='page-link' onclick='myPagelikePaging("+i+")'>"+i+"</a></li> ");
			}else {
				pagingHTML.append("<li id='pagingNum' class='page-item' ><a id='likePaging' class='page-link' onclick='myPagelikePaging("+i+")'>"+i+"</a></li>");
			}//else
		}//for
	
		if(endPage < totalP) 
			pagingHTML.append("<li id='pagingNum' class='page-item'><a id='likePaging' class='page-link' onclick='myPagelikePaging("+(endPage+1)+")'>다음</a></li>");
	}

}
