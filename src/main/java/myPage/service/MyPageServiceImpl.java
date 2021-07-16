package myPage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import community.bean.CommunityDTO;
import community.bean.LikeSingoDTO;
import community.bean.ReplyDTO;
import member.bean.MemberDTO;
import myPage.bean.LikeListDTO;
import myPage.bean.MyPageLikePaging;
import myPage.bean.MyPagePaging;
import myPage.bean.MyPageReplyPaging;
import myPage.bean.PointHistoryDTO;
import myPage.bean.PointHistoryPaging;
import myPage.bean.TotalPointHistoryDTO;
import myPage.dao.MyPageDAO;

@Service
public class MyPageServiceImpl implements MyPageService {
	@Autowired
	private MyPagePaging mypagePaging;
	@Autowired
	private MyPageReplyPaging myPageReplyPaging;
	@Autowired
	private MyPageLikePaging myPageLikePaging;
	@Autowired
	private PointHistoryPaging pointHistoryPaging;
	@Autowired
	private MyPageDAO myPageDAO;
	@Autowired
	private HttpSession session;
	
	
	
     //회원정보수정---------------------------------------------------------
	@Override
	public void modify(MemberDTO memberDTO) {
		myPageDAO.modify(memberDTO);
	}

	 //닉넴변경권---------------------------------------------------------
	@Override
	public void nicknameChange(Map<String, String> map) {
		session.setAttribute("memNickname", map.get("nickname"));
		myPageDAO.useNicknameItem(map);
	}
	
	 //내가쓴글삭제---------------------------------------------------------
	@Override
	public void myPageBoardDelete(String[] check) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("array", check);

		myPageDAO.myPageBoardDelete(map);
	}

	 //내가쓴글목록---------------------------------------------------------
	@Override
	public List<CommunityDTO> getMypageBoardList(String pg, String email) {
		// 1페이지당 5개씩
		int endNum = Integer.parseInt(pg) * 5;
		int startNum = endNum - 4;

		Map<String, Object> map = new HashedMap<String, Object>();  //dao갈땐 하나만 갖고갈 수 있으니까
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		map.put("email", email);

		return myPageDAO.getMypageBoardList(map); 
	}

	 //내가쓴글페이징---------------------------------------------------------
	@Override
	public MyPagePaging MypageBoardPaging(String pg, String email) {
		int totalA = myPageDAO.getMypageboardTotalA(email); // 내가 쓴 총 글수
		mypagePaging.setCurrentPage(Integer.parseInt((pg))); // 현재 페이지
		mypagePaging.setPageBlock(3);
		mypagePaging.setPageSize(5);
		mypagePaging.setTotalA(totalA);
		mypagePaging.makePagingHTML();

		return mypagePaging;
	}

	 //댓글---------------------------------------------------------
	@Override
	public List<ReplyDTO> getMypageReplyList(String pg, String email) {
		// 1페이지당 5개씩
		int endNum = Integer.parseInt(pg) * 5;
		int startNum = endNum - 4;

		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		map.put("email", email);

		return myPageDAO.getMypageReplyList(map);
	}

	
	//댓글 페이징-------------------------------------------------------
	@Override
	public MyPageReplyPaging MypageReplyPaging(String pg, String email) {
		int totalReplyA = myPageDAO.getMypageReplyTotalA(email); // 내가 쓴 총 글수
		
		myPageReplyPaging.setCurrentPage(Integer.parseInt((pg))); // 현재 페이지
		myPageReplyPaging.setPageBlock(3);
		myPageReplyPaging.setPageSize(5);
		myPageReplyPaging.setTotalA(totalReplyA);
		myPageReplyPaging.makePagingHTML();

		return myPageReplyPaging;
	}

	
	//댓글삭제----------------------------------------
	@Override
	public void myPageReplyDelete(Map<String, String[]> map) {
		myPageDAO.myPageReplyDelete(map);
		
	}

	@Override
	public void profile_upload(MemberDTO memberDTO) {
		myPageDAO.profile_upload(memberDTO);
		
	}

	@Override
	public void pointstore(Map<String, Object> map) {
		String item = (String) map.get("item");

		if(item.equals("nickChange")) {
			myPageDAO.nickChange(map);
		}else {
			session.setAttribute("memPalette", item);
			myPageDAO.colorChange(map);
		}
		
		//포인트 사용내역 추가
		myPageDAO.addPointUseHistory(map);
	}

	@Override
	public void pointCharge(Map<String, Object> map) {
		myPageDAO.pointCharge(map);
		
		//포인트 충전내역 추가
		myPageDAO.addPointChargeHistory(map);
	}

	@Override
	public void realExitMember(String email, String reason, HttpSession session) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("email", email);
		map.put("reason", reason);
		
		
		myPageDAO.realExitMember(map);
		
	}

	@Override
	public List<LikeListDTO> getMypageLikeList(String pg, String email) {
		// 1페이지당 5개씩
		int endNum = Integer.parseInt(pg) * 8;
		int startNum = endNum - 7;

		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		map.put("email", email);

		return myPageDAO.getMypageLikeList(map);
	}

	@Override
	public MyPageLikePaging mypageLikePaging(String pg, String email) {
		int totalLikeA = myPageDAO.getMypageLikeTotalA(email); // 내가 쓴 총 글수
		
		myPageLikePaging.setCurrentPage(Integer.parseInt((pg))); // 현재 페이지
		myPageLikePaging.setPageBlock(3);
		myPageLikePaging.setPageSize(8);
		myPageLikePaging.setTotalA(totalLikeA);
		myPageLikePaging.makePagingHTML();

		return myPageLikePaging;
	}

	@Override
	public TotalPointHistoryDTO getTotalPointHistory(String id) {
		return myPageDAO.getTotalPointHistory(id);
	}

	@Override
	public List<PointHistoryDTO> pointHistoryList(String pg, String id) {
		String email = id;
		// 1페이지당 5개씩
		int endNum = Integer.parseInt(pg) * 5;
		int startNum = endNum - 4;

		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		map.put("email", email);

		return myPageDAO.pointHistoryList(map);
	}

	@Override
	public PointHistoryPaging pointHistoryPaging(String pg, String id) {
		String email = id;
		int totalA = myPageDAO.getPointHistoryTotalA(email); // 내가 쓴 총 글수
		pointHistoryPaging.setCurrentPage(Integer.parseInt((pg))); // 현재 페이지
		pointHistoryPaging.setPageBlock(3);
		pointHistoryPaging.setPageSize(5);
		pointHistoryPaging.setTotalA(totalA);
		pointHistoryPaging.makePagingHTML();

		return pointHistoryPaging;
	}

	@Override
	public List<ReplyDTO> getCommunitySeq(Map<String, String[]> map) {
		return myPageDAO.getCommunitySeq(map);
	}

	@Override
	public void updateReplyCount(List<ReplyDTO> list) {
		myPageDAO.updateReplyCount(list);
		
	}


}
