package myPage.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

public interface MyPageService {
	
	public void modify(MemberDTO memberDTO);

	public void nicknameChange(Map<String, String> map);

	public void myPageBoardDelete(String[] check); //내글삭제 (하나)

	public List<CommunityDTO> getMypageBoardList(String pg, String email);

	public MyPagePaging MypageBoardPaging(String pg, String email);

	public List<ReplyDTO> getMypageReplyList(String pg, String email);

	public MyPageReplyPaging MypageReplyPaging(String pg, String email);

	public void myPageReplyDelete(Map<String, String[]> map); // 내 댓글 삭제 (하나)

	public void profile_upload(MemberDTO memberDTO);

	public void pointstore(Map<String, Object> map);

	public void pointCharge(Map<String, Object> map);

	public void realExitMember(String email, String reason, HttpSession session);

	public List<LikeListDTO> getMypageLikeList(String pg, String email);

	public MyPageLikePaging mypageLikePaging(String pg, String email);

	public TotalPointHistoryDTO getTotalPointHistory(String id);

	public List<PointHistoryDTO> pointHistoryList(String pg, String email);

	public PointHistoryPaging pointHistoryPaging(String pg, String id);

	public List<ReplyDTO> getCommunitySeq(Map<String, String[]> map);

	public void updateReplyCount(List<ReplyDTO> list);
} 
