package myPage.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import community.bean.CommunityDTO;
import community.bean.LikeSingoDTO;
import community.bean.ReplyDTO;
import member.bean.MemberDTO;
import myPage.bean.LikeListDTO;
import myPage.bean.PointHistoryDTO;
import myPage.bean.TotalPointHistoryDTO;

public interface MyPageDAO {
	
	public void modify(MemberDTO memberDTO);

	public void useNicknameItem(Map<String, String> map);

	public void myPageBoardDelete(Map<String, String[]> map); //글하나삭제
	
	public List<CommunityDTO> getMypageBoardList(Map<String, Object> map); //글뿌리기

	public int getMypageboardTotalA(String email); //총글수

	public List<ReplyDTO> getMypageReplyList(Map<String, Object> map); //댓글뿌리기

	public int getMypageReplyTotalA(String email); //총댓글수

	public void myPageReplyDelete(Map<String, String[]> map); //댓글하나삭제

	public void profile_upload(MemberDTO memberDTO);

	public void nickChange(Map<String, Object> map);

	public void colorChange(Map<String, Object> map);

	public void pointCharge(Map<String, Object> map);

	public void realExitMember(Map<String, Object> map);

	public List<LikeListDTO> getMypageLikeList(Map<String, Object> map);

	public int getMypageLikeTotalA(String email);

	public void addPointUseHistory(Map<String, Object> map);

	public TotalPointHistoryDTO getTotalPointHistory(String id);

	public void addPointChargeHistory(Map<String, Object> map);

	public List<PointHistoryDTO> pointHistoryList(Map<String, Object> map);

	public int getPointHistoryTotalA(String email);

	public List<ReplyDTO> getCommunitySeq(Map<String, String[]> map);

	public void updateReplyCount(List<ReplyDTO> list);


}
