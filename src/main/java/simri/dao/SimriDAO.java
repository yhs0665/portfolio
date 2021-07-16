package simri.dao;

import java.util.List;
import java.util.Map;

import community.bean.CommunityDTO;
import community.bean.LikeSingoDTO;
import member.bean.MemberDTO;
import simri.bean.AdvertiseDTO;
import simri.bean.SimriDTO;

public interface SimriDAO {

	public List<SimriDTO> getSimriTest_List();

	public SimriDTO getCommunity(String seq);

	public List<SimriDTO> getSimriList(Map<String, String> map);

	public List<SimriDTO> getMagazine_List(Map<String , Integer> map);

	public int getLoveTotalA();

	public void hitUpdate(String seq);

	public CommunityDTO getMagazine(String seq);

	public List<CommunityDTO> magazine_Search(Map<String, String> map);
	
	public List<CommunityDTO> getTotalSearch(Map<String, String> map);

	public int likeCheck(LikeSingoDTO likeSingoDTO);

	public void likeInsert(LikeSingoDTO likeSingoDTO);

	public void like(LikeSingoDTO likeSingoDTO);

	public void likeCancel(LikeSingoDTO likeSingoDTO);

	public void loveShare(Map<Object, Object> map);

	public int testLikeCheck(LikeSingoDTO likeSingoDTO);

	public void testLikeInsert(LikeSingoDTO likeSingoDTO);

	public void testLike(LikeSingoDTO likeSingoDTO);

	public void testLikeCancel(LikeSingoDTO likeSingoDTO);
	
	public void minusPoint(Map<Object, Object> map);

	public int getloveSearchTotalA(Map<String, String> map);

	public int getCheckPoint(Map<Object, Object> map);

	public void updateCheckPoint(Map<Object, Object> map);

	public void checkPointInsert(Map<Object, Object> map);

	public int getSearchTotalA(Map<String, String> map);

	public void singoInsert(LikeSingoDTO likeSingoDTO);
	
	public int singoCheck(LikeSingoDTO likeSingoDTO);

	public void replySingo(Map<String, String> map);

	public List<SimriDTO> getFavoriteSimriTest_List();

	public List<SimriDTO> getFavoriteMagazine_List();
	
	public List<SimriDTO> getLoveSide(Map<String, Integer> map);

	public List<SimriDTO> getSimriSide(Map<String, Integer> map);

	public List<SimriDTO> getRecentlySimriTest_List();

	public List<AdvertiseDTO> getCFList();


	


}
