package community.dao;


import java.util.List;
import java.util.Map;

import community.bean.CommunityDTO;
import community.bean.LikeSingoDTO;
import community.bean.ReplyDTO;

public interface CommunityDAO {

	public void community_Write(CommunityDTO communityDTO);

	public List<CommunityDTO> getTotalCommunity_List(Map<String, Integer> map);
	
	public List<CommunityDTO> getTotalPaletteCommunity_List(Map<String, Integer> map);

	public List<CommunityDTO> getElseCommunity_List(Map<String, String> map);
	
	public int getTotalA();

	public List<CommunityDTO> community_totSearch(Map<String, String> map);
	
	public List<CommunityDTO> community_paletteSearch(Map<String, String> map);
	
	public List<CommunityDTO> community_elseSearch(Map<String, String> map);

	public int getSearchTotalA(Map<String, String> map);

	public void hitUpdate(String seq);

	public CommunityDTO getCommunity(String seq);

	public void community_Modify(CommunityDTO communityDTO);

	public void community_Delete(String seq);

	public void replyWrite(ReplyDTO replyDTO);

	public List<ReplyDTO> getReplyList(String seq);

	public ReplyDTO getReply(int replySeq);

	public void rereplyWrite(ReplyDTO replyDTO);

	public void reply_Delete(String replySeq);

	public void reply_Modify(ReplyDTO replyDTO);

	public int getElseTotalA(String sideCategory);

	public int getPaletteTotalA();

	public int getSeq();
	
	public List<CommunityDTO> getCommunity_Notice(Map<String, Integer> map);

	public List<CommunityDTO> getCommunity_Main(Map<String, Integer> map);

	public List<CommunityDTO> getCommunity_Humor(Map<String, Integer> map);

	public List<CommunityDTO> getCommunity_Love(Map<String, Integer> map);

	public List<CommunityDTO> getCommunity_QnA(Map<String, Integer> map);

	public void like(LikeSingoDTO likeSingoDTO);

	public int likeCheck(LikeSingoDTO likeSingoDTO);

	public void likeCancel(LikeSingoDTO likeSingoDTO);

	public void likeInsert(LikeSingoDTO likeSingoDTO);

	public int singoCheck(LikeSingoDTO likeSingoDTO);

	public void singo(Map<Object, Object> map);

	public void singoCancel(Map<Object, Object> map);

	public void singoInsert(LikeSingoDTO likeSingoDTO);

	public void replySingo(Map<String, String> map);

//	public List<Integer> getReplyCount(int seq);
//
//	public void updateCommunityReplyCount(Map<String, Object> map);




}
