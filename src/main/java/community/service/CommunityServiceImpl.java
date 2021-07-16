package community.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale.Category;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import community.bean.CommunityDTO;
import community.bean.CommunityPaging;
import community.bean.LikeSingoDTO;
import community.bean.ReplyDTO;
import community.dao.CommunityDAO;
import member.bean.MemberDTO;
import member.dao.MemberDAO;

@Service
public class CommunityServiceImpl implements CommunityService {
	@Autowired
	private HttpSession session;
	@Autowired
	private CommunityDAO communityDAO;
	@Autowired
	private CommunityPaging communityPaging;
	@Autowired
	private MemberDAO memberDAO;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");


	@Override
	public void community_Write(CommunityDTO communityDTO) {
		MemberDTO memberDTO = memberDAO.getMemberDTO(communityDTO.getEmail());
		String Todaydate = sdf.format(Calendar.getInstance().getTime());
		String writeDate = sdf.format(memberDTO.getWriteDate());
		
		if(!(Todaydate.equals(writeDate))) {
			memberDAO.todayWriteCountReset(communityDTO.getEmail());
		}
		
		communityDAO.community_Write(communityDTO);
	}


	@Override
	public List<CommunityDTO> getTotalCommunity_List(String pg) {
		//1페이지당 10개씩
	      int endNum = Integer.parseInt(pg)*10; 
	      int startNum = endNum-9; 
	      
	      Map<String , Integer> map = new HashMap<String, Integer>();
	      map.put("startNum", startNum);
	      map.put("endNum", endNum);
	      return communityDAO.getTotalCommunity_List(map);
	}


	@Override
	public List<CommunityDTO> getTotalPaletteCommunity_List(String pg) {
		//1페이지당 10개씩
	      int endNum = Integer.parseInt(pg)*10; 
	      int startNum = endNum-9; 
	      
	      Map<String , Integer> map = new HashMap<String, Integer>();
	      map.put("startNum", startNum);
	      map.put("endNum", endNum);
	      return communityDAO.getTotalPaletteCommunity_List(map);
	}
	
	
	
	@Override
	public List<CommunityDTO> getElseCommunity_List(String pg, String category) {
		//1페이지당 10개씩
	      int endNum = Integer.parseInt(pg)*10; 
	      int startNum = endNum-9; 
	      
	      String sideCategory = null;
	      if(category.equals("2")) {
	    	  sideCategory = "[팔레트]빨강";
	      }else if(category.equals("3")) {
	    	  sideCategory = "[팔레트]파랑";
	      }else if(category.equals("4")) {
	    	  sideCategory = "[팔레트]초록";
	      }else if(category.equals("5")) {
	    	  sideCategory = "[팔레트]노랑";
	      }else if(category.equals("6")) {
	    	  sideCategory = "유머 게시판";
	      }else if(category.equals("7")) {
	    	  sideCategory = "연애 게시판";
	      }else if(category.equals("8")) {
	    	  sideCategory = "고민&상담 게시판";
	      }else if(category.equals("9")) {
	    	  sideCategory = "[공지사항]";
	      }
	      
	      Map<String , String> map = new HashMap<String, String>();
	      map.put("startNum", startNum+"");
	      map.put("endNum", endNum+"");
	      map.put("sideCategory", sideCategory);
	      
	      return communityDAO.getElseCommunity_List(map);
	}

	@Override
	public CommunityPaging communityPaging(String pg, String category){
		int totalA = 0;
		String sideCategory = null;
	      if(category.equals("2")) {
	    	  sideCategory = "[팔레트]빨강";
	    	  totalA = communityDAO.getElseTotalA(sideCategory);
	      }else if(category.equals("3")) {
	    	  sideCategory = "[팔레트]파랑";
	    	  totalA = communityDAO.getElseTotalA(sideCategory);
	      }else if(category.equals("4")) {
	    	  sideCategory = "[팔레트]초록";
	    	  totalA = communityDAO.getElseTotalA(sideCategory);
	      }else if(category.equals("5")) {
	    	  sideCategory = "[팔레트]노랑";
	    	  totalA = communityDAO.getElseTotalA(sideCategory);
	      }else if(category.equals("6")) {
	    	  sideCategory = "유머 게시판";
	    	  totalA = communityDAO.getElseTotalA(sideCategory);
	      }else if(category.equals("7")) {
	    	  sideCategory = "연애 게시판";
	    	  totalA = communityDAO.getElseTotalA(sideCategory);
	      }else if(category.equals("8")) {
	    	  sideCategory = "고민&상담 게시판";
	    	  totalA = communityDAO.getElseTotalA(sideCategory);
	      }else if(category.equals("9")) {
	    	  sideCategory = "[공지사항]";
	    	  totalA = communityDAO.getElseTotalA(sideCategory);
	      }else if(category.equals("1")) {
	    	  totalA = communityDAO.getPaletteTotalA();
	      }else {
	    	  // 총글수
	    	  totalA = communityDAO.getTotalA();
	      }
		      
		 communityPaging.setCurrentPage(Integer.parseInt(pg)); //현재 페이지
		 communityPaging.setPageBlock(3); //3
		 communityPaging.setPageSize(10);  //5
		 communityPaging.setTotalA(totalA);
		 communityPaging.makePagingHTML();
	      
	      return communityPaging;
	}


	@Override
	public List<CommunityDTO> community_totSearch(Map<String, String> map) {
		
		
		 //1페이지당 5개씩
	      int endNum = Integer.parseInt(map.get("pg"))*10;
	      int startNum = endNum-9;
	      
	      //pg, searchOption, keyword, startNum, endNum
	      map.put("startNum", startNum+"");
	      map.put("endNum", endNum+"");
	      
	      
	      return communityDAO.community_totSearch(map);
	}
	

	@Override
	public List<CommunityDTO> community_paletteSearch(Map<String, String> map) {
		 //1페이지당 5개씩
	      int endNum = Integer.parseInt(map.get("pg"))*10;
	      int startNum = endNum-9;
	      
	      //pg, searchOption, keyword, startNum, endNum
	      map.put("startNum", startNum+"");
	      map.put("endNum", endNum+"");
	      
	      return communityDAO.community_paletteSearch(map);
	}

	
	
	

	@Override
	public List<CommunityDTO> community_elseSearch(Map<String, String> map) {
		String category = map.get("category");
		  String sideCategory = null;
		  
	      if(category.equals("2")) {
	    	  sideCategory = "[팔레트]빨강";
	      }else if(category.equals("3")) {
	    	  sideCategory = "[팔레트]파랑";
	      }else if(category.equals("4")) {
	    	  sideCategory = "[팔레트]초록";
	      }else if(category.equals("5")) {
	    	  sideCategory = "[팔레트]노랑";
	      }else if(category.equals("6")) {
	    	  sideCategory = "유머 게시판";
	      }else if(category.equals("7")) {
	    	  sideCategory = "연애 게시판";
	      }else if(category.equals("8")) {
	    	  sideCategory = "고민&상담 게시판";
	      }else if(category.equals("9")) {
	    	  sideCategory = "[공지사항]";
	      }
		
		 //1페이지당 5개씩
	      int endNum = Integer.parseInt(map.get("pg"))*10;
	      int startNum = endNum-9;
	      
	      //pg, searchOption, keyword, startNum, endNum
	      map.put("startNum", startNum+"");
	      map.put("endNum", endNum+"");
	      map.put("sideCategory", sideCategory+"");
	      
	      return communityDAO.community_elseSearch(map);
	}

	@Override
	public CommunityPaging communityPaging(Map<String, String> map) {
		int totalA = communityDAO.getSearchTotalA(map); //검색한 총글수
	      
		communityPaging.setCurrentPage(Integer.parseInt(map.get("pg"))); //현재 페이지
		communityPaging.setPageBlock(3);
		communityPaging.setPageSize(10);
		communityPaging.setTotalA(totalA);
		communityPaging.makePagingHTML();
	      
	      return communityPaging;
	}


	@Override
	public void hitUpdate(String seq) {
		communityDAO.hitUpdate(seq);
	}


	@Override
	public CommunityDTO getCommunity(String seq) {
		return communityDAO.getCommunity(seq);
	}


	@Override
	public void community_Modify(CommunityDTO communityDTO) {
		communityDAO.community_Modify(communityDTO);
	}


	@Override
	public void community_Delete(String seq) {
		communityDAO.community_Delete(seq);
	}


	@Override
	public void replyWrite(ReplyDTO replyDTO) {
		communityDAO.replyWrite(replyDTO);		
	}


	@Override
	public List<ReplyDTO> getReplyList(String seq) {
		return communityDAO.getReplyList(seq);
	}


	@Override
	public void rereplyWrite(ReplyDTO replyDTO) {
		//원댓
		ReplyDTO rDTO = communityDAO.getReply(replyDTO.getReplySeq());
		
		replyDTO.setRef(rDTO.getRef());
		replyDTO.setLev(rDTO.getLev());
		replyDTO.setStep(rDTO.getStep());
		
		communityDAO.rereplyWrite(replyDTO);
	}


	@Override
	public void reply_Delete(String replySeq) {
		communityDAO.reply_Delete(replySeq);
		
	}


	@Override
	public ReplyDTO getReply(String replySeq) {
		
		return communityDAO.getReply(Integer.parseInt(replySeq));
	}


	@Override
	public void reply_Modify(ReplyDTO replyDTO) {
		communityDAO.reply_Modify(replyDTO);
		
	}


	@Override
	public int getSeq() {
		
		return communityDAO.getSeq();
	}

	@Override
	public List<CommunityDTO> getCommunity_Notice() {
		//1페이지당 10개씩
	      int endNum = 2;
	      int startNum = endNum-1; 
	      
	      Map<String , Integer> map = new HashMap<String, Integer>();
	      map.put("startNum", startNum);
	      map.put("endNum", endNum);
	      return communityDAO.getCommunity_Notice(map);	
	}
	
	
	public List<CommunityDTO> getCommunity_Main() {
		//1페이지당 10개씩
	      int endNum = 5;
	      int startNum = endNum-4; 
	      
	      Map<String , Integer> map = new HashMap<String, Integer>();
	      map.put("startNum", startNum);
	      map.put("endNum", endNum);
	      return communityDAO.getCommunity_Main(map);	
	      
	}


	@Override
	public List<CommunityDTO> getCommunity_Humor() {
		//1페이지당 10개씩
	      int endNum = 5;
	      int startNum = endNum-4; 
	      
	      Map<String , Integer> map = new HashMap<String, Integer>();
	      map.put("startNum", startNum);
	      map.put("endNum", endNum);
	      return communityDAO.getCommunity_Humor(map);	
	      
	}


	@Override
	public List<CommunityDTO> getCommunity_Love() {
		//1페이지당 10개씩
	      int endNum = 5;
	      int startNum = endNum-4; 
	      
	      Map<String , Integer> map = new HashMap<String, Integer>();
	      map.put("startNum", startNum);
	      map.put("endNum", endNum);
	      return communityDAO.getCommunity_Love(map);	
	}


	@Override
	public List<CommunityDTO> getCommunity_QnA() {
		//1페이지당 10개씩
	      int endNum = 5;
	      int startNum = endNum-4; 
	      
	      Map<String , Integer> map = new HashMap<String, Integer>();
	      map.put("startNum", startNum);
	      map.put("endNum", endNum);
	      return communityDAO.getCommunity_QnA(map);
	}


	@Override
	public void like(LikeSingoDTO likeSingoDTO) {		 	      
	     communityDAO.like(likeSingoDTO);
		
	}


	@Override
	public int likeCheck(LikeSingoDTO likeSingoDTO) {
		return communityDAO.likeCheck(likeSingoDTO);
	}


	@Override
	public void likeCancel(LikeSingoDTO likeSingoDTO) {
		communityDAO.likeCancel(likeSingoDTO);
		
	}


	@Override
	public void likeInsert(LikeSingoDTO likeSingoDTO) {
		  if(likeSingoDTO.getCategory().equals("2")) {
	    	  likeSingoDTO.setCategory("[팔레트]빨강");
	      }else if(likeSingoDTO.getCategory().equals("3")) {
	    	  likeSingoDTO.setCategory("[팔레트]파랑");
	      }else if(likeSingoDTO.getCategory().equals("4")) {
	    	  likeSingoDTO.setCategory("[팔레트]초록");
	      }else if(likeSingoDTO.getCategory().equals("5")) {
	    	  likeSingoDTO.setCategory("[팔레트]노랑");
	      }else if(likeSingoDTO.getCategory().equals("6")) {
	    	  likeSingoDTO.setCategory("유머 게시판");
	      }else if(likeSingoDTO.getCategory().equals("7")) {
	    	  likeSingoDTO.setCategory("연애 게시판");
	      }else if(likeSingoDTO.getCategory().equals("8")) {
	    	  likeSingoDTO.setCategory("고민&상담 게시판");
	      }else if(likeSingoDTO.getCategory().equals("9")) {
	    	  likeSingoDTO.setCategory("[공지사항]");
	      }
		communityDAO.likeInsert(likeSingoDTO);
		
	}


	@Override
	public int singoCheck(LikeSingoDTO likeSingoDTO) {
		return communityDAO.singoCheck(likeSingoDTO);
	}


	@Override
	public void singo(Map<Object, Object> map) {
		 communityDAO.singo(map);
		
	}


	@Override
	public void singoCancel(Map<Object, Object> map) {
		communityDAO.singoCancel(map);
		
	}


	@Override
	public void singoInsert(LikeSingoDTO likeSingoDTO) {
		  if(likeSingoDTO.getCategory().equals("2")) {
	    	  likeSingoDTO.setCategory("[팔레트]빨강");
	      }else if(likeSingoDTO.getCategory().equals("3")) {
	    	  likeSingoDTO.setCategory("[팔레트]파랑");
	      }else if(likeSingoDTO.getCategory().equals("4")) {
	    	  likeSingoDTO.setCategory("[팔레트]초록");
	      }else if(likeSingoDTO.getCategory().equals("5")) {
	    	  likeSingoDTO.setCategory("[팔레트]노랑");
	      }else if(likeSingoDTO.getCategory().equals("6")) {
	    	  likeSingoDTO.setCategory("유머 게시판");
	      }else if(likeSingoDTO.getCategory().equals("7")) {
	    	  likeSingoDTO.setCategory("연애 게시판");
	      }else if(likeSingoDTO.getCategory().equals("8")) {
	    	  likeSingoDTO.setCategory("고민&상담 게시판");
	      }else if(likeSingoDTO.getCategory().equals("9")) {
	    	  likeSingoDTO.setCategory("[공지사항]");
	      }
		communityDAO.singoInsert(likeSingoDTO);
		
	}


	@Override
	public void replySingo(Map<String, String> map) {
		communityDAO.replySingo(map);
		
	}


	 
}
