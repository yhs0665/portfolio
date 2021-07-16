package simri.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import community.bean.CommunityDTO;
import community.bean.LikeSingoDTO;
import member.bean.MemberDTO;
import simri.bean.AdvertiseDTO;
import simri.bean.SimriDTO;

@Repository
public class SimriDAOMybatis implements SimriDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<SimriDTO> getSimriTest_List() {
		return sqlSession.selectList("simriSQL.getSimriTest_List");
	}

	@Override
	public SimriDTO getCommunity(String seq) {
		return sqlSession.selectOne("simriSQL.getCommunity" ,Integer.parseInt(seq));
	}

	@Override
	public List<SimriDTO> getSimriList(Map<String, String> map) {
		
		return sqlSession.selectList("simriSQL.getSimriList", map);
	}

	@Override
	public void singoInsert(LikeSingoDTO likeSingoDTO) {
		
		sqlSession.insert("simriSQL.singoInsert", likeSingoDTO);		
	}

	@Override
	public int singoCheck(LikeSingoDTO likeSingoDTO) {
		
		Integer a = sqlSession.selectOne("simriSQL.replySingoCheck", likeSingoDTO);
		
		if(a == null) {
			a = 2;
		}
		return a;
	}

	@Override
	public void replySingo(Map<String, String> map) {
		sqlSession.update("simriSQL.replySingo", map);
		
	}
	
	@Override
	public List<SimriDTO> getMagazine_List(Map<String , Integer> map) {
		return sqlSession.selectList("simriSQL.getMagazine_List" , map);
	}

	@Override
	public int getLoveTotalA() {
		return sqlSession.selectOne("simriSQL.getLoveTotalA");
	}

	@Override
	public void hitUpdate(String seq) {
		sqlSession.update("simriSQL.hitUpdate" ,Integer.parseInt(seq));
	}

	@Override
	public CommunityDTO getMagazine(String seq) {
		return sqlSession.selectOne("simriSQL.getMagazine" ,Integer.parseInt(seq));
	}

	@Override
	public List<CommunityDTO> magazine_Search(Map<String, String> map) {
		return sqlSession.selectList("simriSQL.magazine_Search" , map);
	}

	@Override
	public int getloveSearchTotalA(Map<String, String> map) {
		return sqlSession.selectOne("simriSQL.getloveSearchTotalA" , map);
	}
	
	@Override
	public List<CommunityDTO> getTotalSearch(Map<String, String> map) {
		
		return sqlSession.selectList("simriSQL.getTotalSearch" , map);
	}
	
	@Override
	public int getSearchTotalA(Map<String, String> map) {
		return sqlSession.selectOne("simriSQL.getSearchTotalA" , map);
	}

	@Override
	public int likeCheck(LikeSingoDTO likeSingoDTO) {
		Integer a = sqlSession.selectOne("simriSQL.likeCheck", likeSingoDTO);
		if(a == null) {
			a = 2;
		}
		return a;
	}

	@Override
	public void likeInsert(LikeSingoDTO likeSingoDTO) {
		sqlSession.insert("simriSQL.likeInsert", likeSingoDTO);		
	}

	@Override
	public void like(LikeSingoDTO likeSingoDTO) {
		sqlSession.update("simriSQL.like", likeSingoDTO);		
	}

	@Override
	public void likeCancel(LikeSingoDTO likeSingoDTO) {
		sqlSession.update("simriSQL.likeCancel",likeSingoDTO);
		
	}

	@Override
	public void loveShare(Map<Object, Object> map) {
		sqlSession.update("simriSQL.loveShare", map);

	}

	@Override
	public int testLikeCheck(LikeSingoDTO likeSingoDTO) {
		Integer a = sqlSession.selectOne("simriSQL.testLikeCheck", likeSingoDTO);
		if(a == null) {
			a = 2;
		}
		return a;
	}

	@Override
	public void testLikeInsert(LikeSingoDTO likeSingoDTO) {
		sqlSession.insert("simriSQL.testLikeInsert", likeSingoDTO);
		
	}

	@Override
	public void testLike(LikeSingoDTO likeSingoDTO) {
		sqlSession.update("simriSQL.testLike", likeSingoDTO);			
	}

	@Override
	public void testLikeCancel(LikeSingoDTO likeSingoDTO) {
		sqlSession.update("simriSQL.testLikeCancel",likeSingoDTO);
		
	}
	public void minusPoint(Map<Object, Object> map) {
		sqlSession.update("simriSQL.minusPoint", map);
		
	}

	@Override
	public int getCheckPoint(Map<Object, Object> map) {
		Integer a = sqlSession.selectOne("simriSQL.getCheckPoint", map);
		if(a == null) {
			a = 2;
		}
		return a;
	}

	@Override
	public void updateCheckPoint(Map<Object, Object> map) {
		sqlSession.update("simriSQL.updateCheckPoint", map);
		
	}

	@Override
	public void checkPointInsert(Map<Object, Object> map) {
		sqlSession.insert("simriSQL.checkPointInsert", map);
		
	}

	@Override
	public List<SimriDTO> getLoveSide(Map<String, Integer> map) {
		return sqlSession.selectList("simriSQL.getLoveSide", map);
	}

	@Override
	public List<SimriDTO> getFavoriteSimriTest_List() {
		return sqlSession.selectList("simriSQL.getFavoriteSimriTest_List");
	}

	@Override
	public List<SimriDTO> getFavoriteMagazine_List() {
		return sqlSession.selectList("simriSQL.getFavoriteMagazine_List");
	}

	@Override
	public List<SimriDTO> getRecentlySimriTest_List() {
		return sqlSession.selectList("simriSQL.getRecentlySimriTest_List");
	}

	@Override
	public List<SimriDTO> getSimriSide(Map<String, Integer> map) {
		return sqlSession.selectList("simriSQL.getSimriSide", map);
	}

	@Override
	public List<AdvertiseDTO> getCFList() {
		return sqlSession.selectList("simriSQL.getCFList");
	}

}
