package simri.service;

import java.util.List;
import java.util.Map;

import community.bean.CommunityDTO;
import community.bean.LikeSingoDTO;
import simri.bean.AdvertiseDTO;
import simri.bean.SimriDTO;
import simri.bean.SimriTestPaging;

public interface SimriService {

	public List<SimriDTO> getSimriTest_List();

	public SimriDTO getCommunity(String seq);

	public List<SimriDTO> getSimriList(Map<String,String> map);

	public List<SimriDTO> getMagazine_List(String pg);

	public SimriTestPaging simriTestPaging(String pg);

	public void hitUpdate(String seq);

	public CommunityDTO getMagazine(String seq);

	public List<CommunityDTO> magazine_Search(Map<String, String> map);
	
	public List<CommunityDTO> getTotalSearch(Map<String, String> map);

	public SimriTestPaging simriTestPaging(Map<String, String> map);

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

	public int getCheckPoint(Map<Object, Object> map);

	public void updateCheckPoint(Map<Object, Object> map);

	public void checkPointInsert(Map<Object, Object> map);

	public void singoInsert(LikeSingoDTO likeSingoDTO);

	public List<SimriDTO> getLoveSide();

	public int singoCheck(LikeSingoDTO likeSingoDTO);

	public void replySingo(Map<String, String> map);

	public List<SimriDTO> getFavoriteSimriTest_List();

	public List<SimriDTO> getFavoriteMagazine_List();
	
	public List<SimriDTO> getRecentlySimriTest_List();

	public List<SimriDTO> getSimriSide();

	public List<AdvertiseDTO> getCFList();



}
