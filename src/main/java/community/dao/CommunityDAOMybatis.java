package community.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import community.bean.CommunityDTO;
import community.bean.LikeSingoDTO;
import community.bean.ReplyDTO;

@Transactional //여기다가 줘도 되고 밑에 함수마다 줘도된다
@Repository
public class CommunityDAOMybatis implements CommunityDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void community_Write(CommunityDTO communityDTO) {
		sqlSession.insert("communitySQL.community_Write" , communityDTO);
	}

	@Override
	public List<CommunityDTO> getTotalCommunity_List(Map<String, Integer> map) {
		   return sqlSession.selectList("communitySQL.getTotalCommunity_List" , map);
	}

	@Override
	public List<CommunityDTO> getTotalPaletteCommunity_List(Map<String, Integer> map) {
			return sqlSession.selectList("communitySQL.getTotalPaletteCommunity_List" , map);
	}
	
	@Override
	public List<CommunityDTO> getElseCommunity_List(Map<String, String> map) {
		return sqlSession.selectList("communitySQL.getElseCommunity_List" , map);
	}
	
	@Override
	public int getTotalA() {
		  return sqlSession.selectOne("communitySQL.getTotalA");
	}
	
	@Override
	public int getElseTotalA(String sideCategory) {
		return sqlSession.selectOne("communitySQL.getElseTotalA", sideCategory);
	}
	
	@Override
	public int getPaletteTotalA() {
		return sqlSession.selectOne("communitySQL.getPaletteTotalA");
	}

	@Override
	public List<CommunityDTO> community_totSearch(Map<String, String> map) {
		  return sqlSession.selectList("communitySQL.community_totSearch" , map);
	}

	@Override
	public List<CommunityDTO> community_paletteSearch(Map<String, String> map) {
		return sqlSession.selectList("communitySQL.community_paletteSearch" , map);
	}
	
	
	@Override
	public List<CommunityDTO> community_elseSearch(Map<String, String> map) {
		return sqlSession.selectList("communitySQL.community_elseSearch" , map);
	}
	@Override
	public int getSearchTotalA(Map<String, String> map) {
		  return sqlSession.selectOne("communitySQL.getSearchTotalA" , map);
	}

	@Override
	public void hitUpdate(String seq) {
		sqlSession.update("communitySQL.hitUpdate" ,Integer.parseInt(seq));
	}

	@Override
	public CommunityDTO getCommunity(String seq) {
		return sqlSession.selectOne("communitySQL.getCommunity" ,Integer.parseInt(seq));
	}

	@Override
	public void community_Modify(CommunityDTO communityDTO) {
		sqlSession.update("communitySQL.community_Modify" , communityDTO);
	}

	@Override
	public void community_Delete(String seq) {
		sqlSession.delete("communitySQL.community_Delete" ,Integer.parseInt(seq));
	}


	@Override
	public void replyWrite(ReplyDTO replyDTO) {
		sqlSession.insert("communitySQL.replyWrite" , replyDTO);
		
	}

	@Override
	public List<ReplyDTO> getReplyList(String seq) {
		
		return sqlSession.selectList("communitySQL.getReplyList" , Integer.parseInt(seq));
	}

	@Override
	public ReplyDTO getReply(int replySeq) {
		
		return sqlSession.selectOne("communitySQL.getReply", replySeq);
	}

	@Override
	public void rereplyWrite(ReplyDTO replyDTO) {
		sqlSession.insert("communitySQL.rereplyWrite" , replyDTO);
		
	}

	@Override
	public void reply_Delete(String replySeq) {
		sqlSession.delete("communitySQL.reply_Delete" ,Integer.parseInt(replySeq));
		
	}

	@Override
	public void reply_Modify(ReplyDTO replyDTO) {
		sqlSession.update("communitySQL.reply_Modify", replyDTO);
		
	}

	@Override
	public int getSeq() {
		
		return sqlSession.selectOne("communitySQL.getSeq");
	}

	@Override
	public List<CommunityDTO> getCommunity_Notice(Map<String, Integer> map) {
		return sqlSession.selectList("communitySQL.getCommunity_Notice" , map);
	}
	
	@Override
	public List<CommunityDTO> getCommunity_Main(Map<String, Integer> map) {
		return sqlSession.selectList("communitySQL.getCommunity_Main" , map);
	}

	@Override
	public List<CommunityDTO> getCommunity_Humor(Map<String, Integer> map) {
		return sqlSession.selectList("communitySQL.getCommunity_Humor" , map);
	}

	@Override
	public List<CommunityDTO> getCommunity_Love(Map<String, Integer> map) {
		return sqlSession.selectList("communitySQL.getCommunity_Love" , map);
	}

	@Override
	public List<CommunityDTO> getCommunity_QnA(Map<String, Integer> map) {
		return sqlSession.selectList("communitySQL.getCommunity_QnA" , map);
	}

	@Override
	public void like(LikeSingoDTO likeSingoDTO) {
		sqlSession.update("communitySQL.like", likeSingoDTO);
		
	}

	@Override
	public int likeCheck(LikeSingoDTO likeSingoDTO) {
		Integer a = sqlSession.selectOne("communitySQL.likeCheck", likeSingoDTO);
		if(a == null) {
			a = 2;
		}
		return a;
	}

	@Override
	public void likeCancel(LikeSingoDTO likeSingoDTO) {
		sqlSession.update("communitySQL.likeCancel",likeSingoDTO);
		
	}

	@Override
	public void likeInsert(LikeSingoDTO likeSingoDTO) {
		sqlSession.insert("communitySQL.likeInsert", likeSingoDTO);
		
	}

	@Override
	public int singoCheck(LikeSingoDTO likeSingoDTO) {
		Integer a = 0;
		if(likeSingoDTO.getReplySeq()==0) {
			a = sqlSession.selectOne("communitySQL.singoCheck", likeSingoDTO);
		}else {
			a = sqlSession.selectOne("communitySQL.replySingoCheck", likeSingoDTO);
		}
		if(a == null) {
			a = 2;
		}
		return a;
	}

	@Override
	public void singo(Map<Object, Object> map) {
		sqlSession.update("communitySQL.singo", map);
		
		
	}

	@Override
	public void singoCancel(Map<Object, Object> map) {
		sqlSession.update("communitySQL.singoCancel", map);
		
	}

	@Override
	public void singoInsert(LikeSingoDTO likeSingoDTO) {
		
		if(likeSingoDTO.getReplySeq()==0) {
			sqlSession.insert("communitySQL.singoInsert", likeSingoDTO);			
		}else {
			sqlSession.insert("communitySQL.replySingoInsert", likeSingoDTO);
		}		
		
	}

	@Override
	public void replySingo(Map<String, String> map) {
		sqlSession.update("communitySQL.replySingo", map);
		
	}

//	@Override
//	public List<Integer> getReplyCount(int seq) {
//		return sqlSession.selectList("communitySQL.getReplyCount" , seq);
//	}
//
//	@Override
//	public void updateCommunityReplyCount(Map<String, Object> map) {
//		sqlSession.update("communitySQL.updateCommunityReplyCount", map);
//		
//	}

	
}
