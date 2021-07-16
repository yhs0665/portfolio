package simri.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import community.bean.CommunityDTO;
import community.bean.LikeSingoDTO;
import member.bean.MemberDTO;
import simri.bean.AdvertiseDTO;
import simri.bean.SimriDTO;
import simri.bean.SimriTestPaging;
import simri.dao.SimriDAO;
@Service
public class SimriServiceImpl implements SimriService {
	@Autowired
	private SimriDAO simriDAO;
	@Autowired
	private SimriTestPaging simriTestPaging;
	
	@Override
	public List<SimriDTO> getSimriTest_List() {
		return simriDAO.getSimriTest_List();
	}

	@Override
	public SimriDTO getCommunity(String seq) {
		return simriDAO.getCommunity(seq);
		
	}

	@Override
	public List<SimriDTO> getSimriList(Map<String, String> map) {
				
		return simriDAO.getSimriList(map);
	}
	@Override
	public List<SimriDTO> getMagazine_List(String pg) {
			//1페이지당 5개씩
	      int endNum = Integer.parseInt(pg)*5; 
	      int startNum = endNum-4; 
	      
	      Map<String , Integer> map = new HashMap<String, Integer>();
	      map.put("startNum", startNum);
	      map.put("endNum", endNum);
		
		return simriDAO.getMagazine_List(map);
	}

	@Override
	public SimriTestPaging simriTestPaging(String pg) {
		int totalA = simriDAO.getLoveTotalA();
		
		simriTestPaging.setCurrentPage(Integer.parseInt(pg)); //현재 페이지
		simriTestPaging.setPageBlock(3); //3
		simriTestPaging.setPageSize(5);  //5
		simriTestPaging.setTotalA(totalA);
		simriTestPaging.makePagingHTML();
	      
	     return simriTestPaging;
	}

	@Override
	public void hitUpdate(String seq) {
		simriDAO.hitUpdate(seq);
	}

	@Override
	public CommunityDTO getMagazine(String seq) {
		return simriDAO.getMagazine(seq);
	}

	@Override
	public List<CommunityDTO> magazine_Search(Map<String, String> map) {
		 //1페이지당 5개씩
	      int endNum = Integer.parseInt(map.get("pg"))*5;
	      int startNum = endNum-4;
	      
	      map.put("startNum", startNum+"");
	      map.put("endNum", endNum+"");
	      
	      return simriDAO.magazine_Search(map);
	}

	@Override
	public List<CommunityDTO> getTotalSearch(Map<String, String> map) {
		//1페이지당 5개씩
	    int endNum = Integer.parseInt(map.get("pg"))*5;
	    int startNum = endNum-4;
	     
	    map.put("startNum", startNum+"");
	    map.put("endNum", endNum+"");
	      
	    return simriDAO.getTotalSearch(map);
	}

	@Override
	public SimriTestPaging simriTestPaging(Map<String, String> map) {
		int totalA = 0;
		
		if(!map.get("keyword").equals("")) {
			totalA = simriDAO.getloveSearchTotalA(map); //검색한 총글수
		}else {
			totalA = simriDAO.getSearchTotalA(map);
		}
		simriTestPaging.setCurrentPage(Integer.parseInt(map.get("pg"))); //현재 페이지
		simriTestPaging.setPageBlock(3);
		simriTestPaging.setPageSize(5);
		simriTestPaging.setTotalA(totalA);
		simriTestPaging.makePagingHTML();
	      
	    return simriTestPaging;
	}

	@Override
	public void singoInsert(LikeSingoDTO likeSingoDTO) {
		simriDAO.singoInsert(likeSingoDTO);
		
	}

	@Override
	public int singoCheck(LikeSingoDTO likeSingoDTO) {
		
		return simriDAO.singoCheck(likeSingoDTO);
	}

	@Override
	public void replySingo(Map<String, String> map) {
		simriDAO.replySingo(map);
		
	}


	@Override
	public int likeCheck(LikeSingoDTO likeSingoDTO) {
		return simriDAO.likeCheck(likeSingoDTO);
	}

	@Override
	public void likeInsert(LikeSingoDTO likeSingoDTO) {
		simriDAO.likeInsert(likeSingoDTO);
		
	}

	@Override
	public void like(LikeSingoDTO likeSingoDTO) {
		simriDAO.like(likeSingoDTO);		
	}

	@Override
	public void likeCancel(LikeSingoDTO likeSingoDTO) {
		simriDAO.likeCancel(likeSingoDTO);
		
	}

	@Override
	public void loveShare(Map<Object, Object> map) {
		simriDAO.loveShare(map);
		
	}

	@Override
	public int testLikeCheck(LikeSingoDTO likeSingoDTO) {
		return simriDAO.testLikeCheck(likeSingoDTO);
	}

	@Override
	public void testLikeInsert(LikeSingoDTO likeSingoDTO) {
		simriDAO.testLikeInsert(likeSingoDTO);
		
	}

	@Override
	public void testLike(LikeSingoDTO likeSingoDTO) {
		simriDAO.testLike(likeSingoDTO);	
		
	}

	@Override
	public void testLikeCancel(LikeSingoDTO likeSingoDTO) {
		simriDAO.testLikeCancel(likeSingoDTO);
		
	}
	public void minusPoint(Map<Object, Object> map) {
		simriDAO.minusPoint(map);
		
	}

	@Override
	public int getCheckPoint(Map<Object, Object> map) {
		return simriDAO.getCheckPoint(map);
	}

	@Override
	public void updateCheckPoint(Map<Object, Object> map) {
		simriDAO.updateCheckPoint(map);
		
	}

	@Override
	public void checkPointInsert(Map<Object, Object> map) {
		simriDAO.checkPointInsert(map);
		
	}


	@Override
	public List<SimriDTO> getLoveSide() {
		 int endNum = 5;
	     int startNum = endNum-4; 
		
	     Map<String , Integer> map = new HashMap<String, Integer>();
	     map.put("startNum", startNum);
	     map.put("endNum", endNum);
	     
		return simriDAO.getLoveSide(map);
	}

	@Override
	public List<SimriDTO> getFavoriteSimriTest_List() {
		return simriDAO.getFavoriteSimriTest_List();
	}
	public List<SimriDTO> getSimriSide() {
		 int endNum = 3;
	     int startNum = endNum-2; 
		
	     Map<String , Integer> map = new HashMap<String, Integer>();
	     map.put("startNum", startNum);
	     map.put("endNum", endNum);
	     
		return simriDAO.getSimriSide(map);
	}


	@Override
	public List<SimriDTO> getFavoriteMagazine_List() {
		return simriDAO.getFavoriteMagazine_List();
	}

	@Override
	public List<SimriDTO> getRecentlySimriTest_List() {
		return simriDAO.getRecentlySimriTest_List();
	}

	@Override
	public List<AdvertiseDTO> getCFList() {
		return simriDAO.getCFList();
	}


}
