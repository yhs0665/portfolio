package community.service;

import java.util.List;
import java.util.Map;

import community.bean.CommunityDTO;
import community.bean.CommunityPaging;
import community.bean.LikeSingoDTO;
import community.bean.ReplyDTO;

public interface CommunityService {

	public void community_Write(CommunityDTO communityDTO);

	public List<CommunityDTO> getTotalCommunity_List(String pg);

	public List<CommunityDTO> getTotalPaletteCommunity_List(String pg);
	
	public List<CommunityDTO> getElseCommunity_List(String pg, String category);
	
	public CommunityPaging communityPaging(String pg, String category);

	public List<CommunityDTO> community_totSearch(Map<String, String> map);

	public List<CommunityDTO> community_paletteSearch(Map<String, String> map);
	
	public List<CommunityDTO> community_elseSearch(Map<String, String> map);
	
	public CommunityPaging communityPaging(Map<String, String> map);

	public void hitUpdate(String seq);

	public CommunityDTO getCommunity(String seq);

	public void community_Modify(CommunityDTO communityDTO);

	public void community_Delete(String seq);

	public void replyWrite(ReplyDTO replyDTO);

	public List<ReplyDTO> getReplyList(String seq);

	public void rereplyWrite(ReplyDTO replyDTO);

	public void reply_Delete(String replySeq);

	public ReplyDTO getReply(String replySeq);

	public void reply_Modify(ReplyDTO replyDTO);

	public int getSeq();

	public List<CommunityDTO> getCommunity_Notice();

	public List<CommunityDTO> getCommunity_Main();

	public List<CommunityDTO> getCommunity_Humor();

	public List<CommunityDTO> getCommunity_Love();

	public List<CommunityDTO> getCommunity_QnA();

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
