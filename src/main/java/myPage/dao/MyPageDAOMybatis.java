package myPage.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import community.bean.CommunityDTO;
import community.bean.LikeSingoDTO;
import community.bean.ReplyDTO;
import member.bean.MemberDTO;
import myPage.bean.LikeListDTO;
import myPage.bean.PointHistoryDTO;
import myPage.bean.TotalPointHistoryDTO;

@Transactional
@Repository
public class MyPageDAOMybatis implements MyPageDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void modify(MemberDTO memberDTO) {
		sqlSession.update("mypageSQL.modify",memberDTO);
	}

	@Override
	public void useNicknameItem(Map<String, String> map) {
		sqlSession.update("mypageSQL.useNicknameItem",map);
	}

	@Override
	public void myPageBoardDelete(Map<String, String[]> map) {
		sqlSession.delete("mypageSQL.myPageBoardDelete",map);
	}
	
	@Override
	public List<CommunityDTO> getMypageBoardList(Map<String, Object> map) {
		return sqlSession.selectList("mypageSQL.getMypageBoardList",map);

	}

	@Override
	public int getMypageboardTotalA(String email) {
		 return sqlSession.selectOne("mypageSQL.getMypageboardTotalA", email);
		 
	}

	@Override
	public List<ReplyDTO> getMypageReplyList(Map<String, Object> map) {
		return sqlSession.selectList("mypageSQL.getMypageReplyList",map);
		
	}

	@Override //내 댓글 총글수
	public int getMypageReplyTotalA(String email) {
		 return sqlSession.selectOne("mypageSQL.getMypageReplyTotalA", email);
	}

	@Override
	public void myPageReplyDelete(Map<String, String[]> map) {
		sqlSession.delete("mypageSQL.myPageReplyDelete",map);
		
	}

	@Override
	public void profile_upload(MemberDTO memberDTO) {
		sqlSession.update("mypageSQL.profile_upload",memberDTO);
		
	}

	@Override
	public void nickChange(Map<String, Object> map) {
		sqlSession.update("mypageSQL.nickChange", map);
		
	}

	@Override
	public void colorChange(Map<String, Object> map) {
		sqlSession.update("mypageSQL.colorChange", map);
		
	}

	@Override
	public void pointCharge(Map<String, Object> map) {
		sqlSession.update("mypageSQL.pointCharge",map);
	}
	
	@Override
	public void realExitMember(Map<String, Object> map) {
		sqlSession.insert("mypageSQL.realExitMember",map);
		
	}

	@Override
	public List<LikeListDTO> getMypageLikeList(Map<String, Object> map) {
		return sqlSession.selectList("mypageSQL.getMypageLikeList",map);
	}

	@Override
	public int getMypageLikeTotalA(String email) {
		 return sqlSession.selectOne("mypageSQL.getMypageLikeTotalA", email);
	}

	@Override
	public void addPointUseHistory(Map<String, Object> map) {
		sqlSession.insert("mypageSQL.addPointUseHistory",map);
	}

	@Override
	public TotalPointHistoryDTO getTotalPointHistory(String id) {
		return sqlSession.selectOne("mypageSQL.getTotalPointHistory",id);
	}

	@Override
	public void addPointChargeHistory(Map<String, Object> map) {
		sqlSession.insert("mypageSQL.addPointChargeHistory",map);
		
	}

	@Override
	public List<PointHistoryDTO> pointHistoryList(Map<String, Object> map) {
		return sqlSession.selectList("mypageSQL.pointHistoryList",map);
	}

	@Override
	public int getPointHistoryTotalA(String email) {
		 return sqlSession.selectOne("mypageSQL.getPointHistoryTotalA", email);
	}

	@Override
	public List<ReplyDTO> getCommunitySeq(Map<String, String[]> map) {
		return sqlSession.selectList("mypageSQL.getCommunitySeq",map);
	}

	@Override
	public void updateReplyCount(List<ReplyDTO> list) {
		sqlSession.update("mypageSQL.updateReplyCount",list);
		
	}


}
