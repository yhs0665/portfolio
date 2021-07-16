package community.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import community.bean.CommunityDTO;
import community.bean.CommunityPaging;
import community.bean.LikeSingoDTO;
import community.bean.ReplyDTO;
import community.service.CommunityService;
import member.bean.MemberDTO;
import member.service.MemberService;
import myPage.service.MyPageService;

@Controller
public class CommunityController {
   @Autowired
   private CommunityService communityService;
   @Autowired
   private MemberService memberService;
   
   //커뮤니티 메인 뷰
   @RequestMapping(value = "/community/community", method = RequestMethod.GET)
   public String community(Model model, @RequestParam String com) {
      model.addAttribute("com", com);
      model.addAttribute("display", "/community/community.jsp");
      return "/index";
   }
   
   //카테고리별 리스트 뷰
   @RequestMapping(value = "/community/community_List", method = RequestMethod.GET)
   public String community_List(Model model, @RequestParam String com,
         @RequestParam(required = false, defaultValue = "1") String pg, @RequestParam String category) {
	   
      model.addAttribute("com", com);
      model.addAttribute("pg", pg);
      model.addAttribute("category", category);
      model.addAttribute("display", "/community/community_List.jsp");
      return "/index";
   }
 
   //커뮤니티 글쓰기 폼
   @RequestMapping(value = "/community/community_WriteForm", method = RequestMethod.GET)
   public String community_WriteForm(Model model, @RequestParam String com, HttpSession session, @RequestParam String category) {
	   
	  String palette = ((String) session.getAttribute("memPalette"));
	   
      model.addAttribute("com", com);
      model.addAttribute("palette", palette);
      model.addAttribute("category", category);
      model.addAttribute("display", "/community/community_WriteForm.jsp");
      return "/index";
   }
   
   //커뮤니티 글 보기
   @RequestMapping(value = "/community/community_View", method = RequestMethod.POST)
   public String community_View(Model model, @RequestParam(required = false, defaultValue = "1") String pg,
         @RequestParam String seq, @RequestParam(required = false, defaultValue = "0") String com, @RequestParam String category,@RequestParam(required = false, defaultValue = "0") String myPage,
         							HttpSession session) {
	   
	   LikeSingoDTO likeSingoDTO = new LikeSingoDTO();
	   likeSingoDTO.setEmail((String)session.getAttribute("memId"));
	   likeSingoDTO.setSeq(Integer.parseInt(seq));
	   //좋아요 신고 체크하려고 on/off
	   int onoff = communityService.likeCheck(likeSingoDTO);
	   int singoOnoff = communityService.singoCheck(likeSingoDTO);
	   
	   if(onoff==2) onoff=0;
	   if(singoOnoff==2) singoOnoff=0;

	   model.addAttribute("pg", pg);
       model.addAttribute("onoff", onoff);
       model.addAttribute("singoOnoff", singoOnoff);
       model.addAttribute("myPage",myPage);
       model.addAttribute("seq", seq);
       model.addAttribute("com", com);
       model.addAttribute("category", category);
       model.addAttribute("display", "/community/community_View.jsp");
       return "/index";
   }

   // -----------------------------------------------------------------------------------

   @RequestMapping(value = "/community/community_Write", method = RequestMethod.POST)
   public String community_Write(Model model, @ModelAttribute CommunityDTO communityDTO, @RequestParam MultipartFile img,
		   HttpSession session, @RequestParam String pgCategory) {

	   String filePath = "D:\\Spring\\FinalProject\\git_Project\\gitAdmin\\adminPage\\src\\main\\webapp\\storage";
	   String filePathSimri = "D:\\Spring\\FinalProject\\git_Project\\simriTest\\simri\\src\\main\\webapp\\storage";
	   String fileName = img.getOriginalFilename();
	   File file = new File(filePath, fileName);//파일 생성
	   File fileSimri = new File(filePathSimri, fileName);//파일 생성

      if (fileName.equals("")) {
         communityDTO.setImage("null"+((int)(Math.random()*(7-1))+1)+".jpg");
         System.out.println("널이미지값" +communityDTO.getImage());
      } else {
         // 파일 복사
         try {
            FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(file));
            FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(fileSimri));
         } catch (IOException e) {
            e.printStackTrace();
         }
         communityDTO.setImage(fileName);
      }
      communityDTO.setPalette((String) session.getAttribute("memPalette"));
      communityDTO.setEmail((String) session.getAttribute("memId"));
      communityDTO.setNickName((String) session.getAttribute("memNickname"));
      communityService.community_Write(communityDTO);
      
      int seq = communityService.getSeq();
      model.addAttribute("pg", 1);
      model.addAttribute("seq", seq);
      model.addAttribute("com", 0);
      model.addAttribute("category", pgCategory);    
      model.addAttribute("display", "/community/community_View.jsp");
      return "/index";

   }// 수정끝

   
 //--------------------------------------------------------------
   @RequestMapping(value = "/community/getCommunity_List", method = RequestMethod.POST)
   @ResponseBody
   public ModelAndView getCommunity_List(@RequestParam(required = false, defaultValue = "1") String pg,
		   								HttpSession session,
		   								HttpServletResponse response,
		   								@RequestParam String category) {
	   
	   List<CommunityDTO> list=null;
	   //List<Integer> replyCountList=null;
      // 1페이지당 3개씩
	   if(category.equals("0")) {
          list = communityService.getTotalCommunity_List(pg);
	   }else if(category.equals("1")) {
		  list = communityService.getTotalPaletteCommunity_List(pg);
	   }else {
		   list = communityService.getElseCommunity_List(pg, category); 
	   }
//	   if(list != null) {
//		   for(CommunityDTO communityDTO : list) {
//			   replyCountList = communityService.getReplyCount(communityDTO.getSeq());
//		   }
//		   
//		   Map<String, Object> map = new HashMap<String, Object>();
//		   map.put("list", list);
//		   map.put("replyCountList", replyCountList);
//		   communityService.updateCommunityReplyCount(map);
//		   
//	   }
	   
      // 세션
      String memNickname = (String) session.getAttribute("memNickname");
      String memPalette = (String) session.getAttribute("memPalette");

      // 조회수 - 새로고침 방지
      if (session.getAttribute("memNickname") != null) {
         Cookie cookie = new Cookie("memCommunityHit", "0"); // 쿠키생성
         cookie.setMaxAge(30 * 60); // 초단위 // 30분
         response.addCookie(cookie); // 클라이언트 보내기
      }

      // 페이징 처리
      CommunityPaging communityPaging = communityService.communityPaging(pg, category);

      ModelAndView mav = new ModelAndView();
      mav.addObject("pg", pg);
      mav.addObject("list", list);
      mav.addObject("memNickname", memNickname);
      mav.addObject("memPalette", memPalette);
      mav.addObject("communityPaging", communityPaging);
      mav.setViewName("jsonView"); // json으로 자동으로 변환
      return mav;
   }// 수정 완료

   /* 리스트 검색 */
   @RequestMapping(value = "/community/community_Search", method = RequestMethod.POST)
   public @ResponseBody ModelAndView community_Search(@RequestParam Map<String, String> map, HttpSession session, @RequestParam String category) {
	   
	   
	   List<CommunityDTO> list=null;
	      // 1페이지당 3개씩
		   if(category.equals("0")) {
			  list = communityService.community_totSearch(map);
		   }else if(category.equals("1")) {
			   list = communityService.community_paletteSearch(map);
		   }else {
			   map.put("category", category);
			   list = communityService.community_elseSearch(map);
		   }
		   

      // 세션
      String memNickname = (String) session.getAttribute("memNickname");

      // 페이징 처리
      CommunityPaging communityPaging = communityService.communityPaging(map);

		ModelAndView mav = new ModelAndView();
		mav.addObject("pg", map.get("pg"));
		mav.addObject("list", list);
		mav.addObject("memNickname", memNickname);
		mav.addObject("memPalette", session.getAttribute("memPalette"));
		mav.addObject("communityPaging", communityPaging);
		mav.addObject("category", category);
		mav.setViewName("jsonView");
		return mav;
	}// 수정 완

	/* 1인 게시글 갖고오기 view */
	@RequestMapping(value = "/community/getCommunity", method = RequestMethod.POST) // 페이지가 안들어올수도있으니까 이렇게 디폴트값주기
	@ResponseBody
	public ModelAndView getCommunity(@RequestParam String seq, HttpSession session,
			@CookieValue(value = "memCommunityHit", required = false) Cookie communityCookie,
			@CookieValue(value = "totalSearchCookie", required = false) Cookie totalSearchCookie,
			@CookieValue(value = "totalCommunityCookie", required = false) Cookie totalCommunityCookie,
			HttpServletResponse response) {

		// 조회수 - 새로고침 방지
		int sw = 0;
		
		if (communityCookie != null | totalSearchCookie != null | totalCommunityCookie != null) {
			
			if (totalCommunityCookie != null) {
				sw = 1;
				communityService.hitUpdate(seq); // 조회수 증가
				totalCommunityCookie.setMaxAge(0); // 쿠키 삭제
				totalCommunityCookie.setPath("/");
				response.addCookie(totalCommunityCookie); // 클라이언트 보내기

			}
			
			if (totalSearchCookie != null) {
				sw = 1;
				communityService.hitUpdate(seq); // 조회수 증가
				totalSearchCookie.setMaxAge(0); // 쿠키 삭제
				totalSearchCookie.setPath("/");
				response.addCookie(totalSearchCookie); // 클라이언트 보내기

			}

			if (communityCookie != null) {
				if (sw != 1) {
					communityService.hitUpdate(seq); // 조회수 증가

				}
				communityCookie.setMaxAge(0); // 쿠키 삭제
				response.addCookie(communityCookie); // 클라이언트 보내기

			}
		}

		// 한사람의 글을 가지고오기
		CommunityDTO communityDTO = communityService.getCommunity(seq);

		// 세션
		String memNickname = (String) session.getAttribute("memNickname");

		ModelAndView mav = new ModelAndView();
		mav.addObject("communityDTO", communityDTO);
		mav.addObject("memNickname", memNickname);
		mav.setViewName("jsonView");
		return mav;
	}// 수정완

	@RequestMapping(value = "/community/community_ModifyForm", method = RequestMethod.POST)
	public String community_ModifyForm(Model model, @RequestParam String seq, @RequestParam String pg,
			@RequestParam String category, HttpSession session) {

		// 세션
		String memPalette = (String) session.getAttribute("memPalette");

		model.addAttribute("category", category);
		model.addAttribute("seq", seq);
		model.addAttribute("pg", pg);
		model.addAttribute("memPalette", memPalette);
		model.addAttribute("display", "/community/community_ModifyForm.jsp");
		return "/index";
	}

	@RequestMapping(value = "/community/community_Modify", method = RequestMethod.POST)
	public String community_Modify(Model model,@ModelAttribute CommunityDTO communityDTO,
         @RequestParam MultipartFile img, @RequestParam String pg, @RequestParam String pageCategory) {

      String filePath = "D:\\Spring\\git_project\\simriTest\\simri\\src\\main\\webapp\\storage";
      String fileName = img.getOriginalFilename();
      File file = new File(filePath, fileName);// 파일 생성

      // 파일 복사
      

      if (fileName.equals("")) {
         communityDTO.setImage(communityDTO.getImage());
      } else {
         
            try {
            FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(file));
         } catch (IOException e) {
            e.printStackTrace();
         }
         
         communityDTO.setImage(fileName);
      }

      communityService.community_Modify(communityDTO);
      
      model.addAttribute("com", "0");
      model.addAttribute("pg", pg);
      model.addAttribute("category", pageCategory);
      model.addAttribute("display", "/community/community_List.jsp");
      return "/index";
   }

   @RequestMapping(value = "/community/community_Delete", method = RequestMethod.POST)
   public String community_Delete(Model model , @RequestParam String seq, @RequestParam String pg, @RequestParam String category) {
      communityService.community_Delete(seq);

      model.addAttribute("com", "0");
      model.addAttribute("pg", pg);
      model.addAttribute("category", category);
      model.addAttribute("display", "/community/community_List.jsp");
      return "/index"; 
      }

   @RequestMapping(value = "/community/replyWrite", method = RequestMethod.POST)
   @ResponseBody
   public void replyWrite(@ModelAttribute ReplyDTO replyDTO, @RequestParam(required = false, defaultValue = "1") String pg, HttpSession session) {
	  MemberDTO memberDTO = memberService.getMemberDTO((String)session.getAttribute("memId"));
	   
	  replyDTO.setNickName((String)session.getAttribute("memNickname"));
	  replyDTO.setEmail((String)session.getAttribute("memId"));
	  replyDTO.setPalette((String)session.getAttribute("memPalette"));
	  replyDTO.setProfile(memberDTO.getProfile());
      communityService.replyWrite(replyDTO);

   }

   @RequestMapping(value = "/community/getReplyList", method = RequestMethod.POST)
   @ResponseBody
   public ModelAndView getReplyList(@RequestParam String seq, HttpSession session, HttpServletResponse response) {

      // 1페이지당 3개씩
      List<ReplyDTO> list = communityService.getReplyList(seq);

      // 페이징 처리
      // CommunityPaging communityPaging= communityService.communityPaging(pg);

      ModelAndView mav = new ModelAndView();
      // mav.addObject("pg", pg);
      mav.addObject("list", list);
      // mav.addObject("communityPaging", communityPaging);
      mav.setViewName("jsonView"); // json으로 자동으로 변환
      return mav;
   }

   @RequestMapping(value = "/community/rereplyWrite", method = RequestMethod.POST)
   @ResponseBody
   public void rereplyWrite(@ModelAttribute ReplyDTO replyDTO, @RequestParam(required = false, defaultValue = "1") String pg, HttpSession session) {
	   MemberDTO memberDTO = memberService.getMemberDTO((String)session.getAttribute("memId"));
	   
		replyDTO.setNickName((String) session.getAttribute("memNickname"));
		replyDTO.setEmail((String) session.getAttribute("memId"));
		replyDTO.setPalette((String)session.getAttribute("memPalette"));
		  replyDTO.setProfile(memberDTO.getProfile());
		communityService.rereplyWrite(replyDTO);
   }

   @RequestMapping(value = "/community/reply_Delete", method = RequestMethod.GET)
   public String reply_Delete(Model model, @RequestParam(required = false, defaultValue = "1") String pg,
         @RequestParam(required = false, defaultValue = "28") String seq, @RequestParam String replySeq,
         @RequestParam(required =false, defaultValue="0") String com) {
      communityService.reply_Delete(replySeq);
      model.addAttribute("pg", pg);
      model.addAttribute("seq", seq);
      model.addAttribute("com", com);
      model.addAttribute("display", "/community/community_View.jsp");

      return "/index";
   }

   /* 1인 게시글 갖고오기 */
   @RequestMapping(value = "/community/getReply", method = RequestMethod.POST) // 페이지가 안들어올수도있으니까 이렇게 디폴트값주기
   @ResponseBody
   public ModelAndView getReply(@RequestParam String replySeq) {

      // 한사람의 글을 가지고오기
      ReplyDTO replyDTO = communityService.getReply(replySeq);

      ModelAndView mav = new ModelAndView();
      mav.addObject("replyDTO", replyDTO);
      mav.setViewName("jsonView");

      return mav;
   }

   @RequestMapping(value = "/community/reply_Modify", method = RequestMethod.POST)
   @ResponseBody
   public void reply_Modify(@ModelAttribute ReplyDTO replyDTO, @RequestParam(required = false, defaultValue = "1") String pg) {

      communityService.reply_Modify(replyDTO);

   }
   
   @RequestMapping(value = "/community/getCommunity_Notice", method = RequestMethod.POST)
   @ResponseBody
   public ModelAndView getCommunity_Notice(HttpSession session, HttpServletResponse response) {
      // 1페이지당 5개씩
      List<CommunityDTO> list = communityService.getCommunity_Notice();
      
      ModelAndView mav = new ModelAndView();
      mav.addObject("list", list);
      String id = (String)session.getAttribute("memId");
      mav.addObject("id", id);
      mav.setViewName("jsonView"); // json으로 자동으로 변환
      return mav;
   }
   
   @RequestMapping(value = "/community/getCommunity_Main", method = RequestMethod.POST)
   @ResponseBody
   public ModelAndView getCommunity_Main(HttpSession session, HttpServletResponse response) {
      // 1페이지당 5개씩
      List<CommunityDTO> list = communityService.getCommunity_Main();
      
      ModelAndView mav = new ModelAndView();
      mav.addObject("list", list);
      String id = (String)session.getAttribute("memId");
      mav.addObject("id", id);
      mav.addObject("memPalette", session.getAttribute("memPalette"));
      mav.setViewName("jsonView"); // json으로 자동으로 변환
      return mav;
   }
   
   @RequestMapping(value = "/community/getCommunity_Humor", method = RequestMethod.POST)
   @ResponseBody
   public ModelAndView getCommunity_Humor(HttpSession session, HttpServletResponse response) {

      // 1페이지당 5개씩
      List<CommunityDTO> list = communityService.getCommunity_Humor();
      
      ModelAndView mav = new ModelAndView();
      String id = (String)session.getAttribute("memId");
      mav.addObject("id", id);
      mav.addObject("list", list);
      mav.setViewName("jsonView"); // json으로 자동으로 변환
      return mav;
   }
   
   @RequestMapping(value = "/community/getCommunity_Love", method = RequestMethod.POST)
   @ResponseBody
   public ModelAndView getCommunity_Love(HttpSession session, HttpServletResponse response) {

      // 1페이지당 5개씩
      List<CommunityDTO> list = communityService.getCommunity_Love();
      
      ModelAndView mav = new ModelAndView();
      String id = (String)session.getAttribute("memId");
      mav.addObject("id", id);
      mav.addObject("list", list);
      mav.setViewName("jsonView"); // json으로 자동으로 변환
      return mav;
   }
   
   @RequestMapping(value = "/community/getCommunity_QnA", method = RequestMethod.POST)
   @ResponseBody
   public ModelAndView getCommunity_QnA(HttpSession session, HttpServletResponse response) {

      // 1페이지당 5개씩
      List<CommunityDTO> list = communityService.getCommunity_QnA();
      
      ModelAndView mav = new ModelAndView();
      String id = (String)session.getAttribute("memId");
      mav.addObject("id", id);
      mav.addObject("list", list);
      mav.setViewName("jsonView"); // json으로 자동으로 변환
      return mav;
   }
   
   @RequestMapping(value = "/community/like", method = RequestMethod.POST)
   @ResponseBody
   public ModelAndView like(@RequestParam String seq, @RequestParam(required = false, defaultValue = "11") String category, HttpSession session) {
	   
	   LikeSingoDTO likeSingoDTO = new LikeSingoDTO();
	   likeSingoDTO.setEmail((String)session.getAttribute("memId"));
	   likeSingoDTO.setSeq(Integer.parseInt(seq));
	   int onoff = communityService.likeCheck(likeSingoDTO);
	   if(onoff==2) {
		   if(!category.equals("11")) {
			   likeSingoDTO.setCategory(category);
		   }else {
			   CommunityDTO communityDTO = communityService.getCommunity(seq);
			   likeSingoDTO.setCategory(communityDTO.getCategory());
		   }
		   likeSingoDTO.setNickName((String)session.getAttribute("memNickname"));
		   likeSingoDTO.setPalette((String)session.getAttribute("memPalette"));
		   communityService.likeInsert(likeSingoDTO);
		   onoff = 0;
	   }
	   if(onoff==0) {
		   communityService.like(likeSingoDTO);
	   }else {
		   communityService.likeCancel(likeSingoDTO);
	   }
	   onoff = communityService.likeCheck(likeSingoDTO);
	   ModelAndView mav = new ModelAndView();
	   mav.addObject("onoff", onoff);
	   mav.setViewName("jsonView");
	   return mav;
   }

   @RequestMapping(value = "/community/singo", method = RequestMethod.POST)
   @ResponseBody
   public ModelAndView singo(@RequestParam String seq, @RequestParam String category, @RequestParam String singoReason, HttpSession session) {
	   	      
	   LikeSingoDTO likeSingoDTO = new LikeSingoDTO();
	   likeSingoDTO.setEmail((String)session.getAttribute("memId"));
	   likeSingoDTO.setSingoReason(singoReason);
	   likeSingoDTO.setSeq(Integer.parseInt(seq));
	   likeSingoDTO.setReplySeq(0);
	   int singoOnoff = communityService.singoCheck(likeSingoDTO);
	   if(singoOnoff==2) {
		   likeSingoDTO.setCategory(category);
		   likeSingoDTO.setNickName((String)session.getAttribute("memNickname"));
		   likeSingoDTO.setPalette((String)session.getAttribute("memPalette"));
		   communityService.singoInsert(likeSingoDTO);
		   singoOnoff = 0;
	   }
	   
	   Map<Object, Object> map = new HashMap<Object, Object>();
	   map.put("category", category);
	   map.put("seq", Integer.parseInt(seq));
	   map.put("singoReason", singoReason);
	   map.put("email", (String)session.getAttribute("memId"));
	   CommunityDTO communityDTO = communityService.getCommunity(seq);
	   map.put("singoEmail", communityDTO.getEmail());

	   if(singoOnoff==0) {
		   communityService.singo(map);
	   }else {
		   communityService.singoCancel(map);
	   }
	   singoOnoff = communityService.singoCheck(likeSingoDTO);
	   ModelAndView mav = new ModelAndView();
	   mav.addObject("singoOnoff", singoOnoff);
	   mav.setViewName("jsonView");
	   return mav;
   }

 //댓글신고---------------------------
 	@RequestMapping(value = "/community/replySingo", method=RequestMethod.POST)
 	@ResponseBody
 	public ModelAndView replySingo(@RequestParam Map<String,String> map, HttpSession session) {
 		 		
		LikeSingoDTO likeSingoDTO = new LikeSingoDTO();
		likeSingoDTO.setEmail((String) session.getAttribute("memId"));
		likeSingoDTO.setSingoReason(map.get("singoReason"));
		likeSingoDTO.setSeq(Integer.parseInt(map.get("seq")));
		if(!map.get("replySeq").equals("")) {
			likeSingoDTO.setReplySeq(Integer.parseInt(map.get("replySeq")));
		}else {
			likeSingoDTO.setReplySeq(0);
		}
		
		int replySingoOnoff = communityService.singoCheck(likeSingoDTO);
		
		
		if (replySingoOnoff == 2) {
			likeSingoDTO.setCategory(map.get("category"));
			likeSingoDTO.setNickName((String) session.getAttribute("memNickname"));
			likeSingoDTO.setPalette((String) session.getAttribute("memPalette"));
			communityService.singoInsert(likeSingoDTO);
			replySingoOnoff = 0;
		}
		if (replySingoOnoff == 0) {
			ReplyDTO replyDTO = communityService.getReply(map.get("replySeq"));
			map.put("singoEmail", replyDTO.getEmail());
			map.put("email", (String) session.getAttribute("memId"));
			communityService.replySingo(map);
		}
		replySingoOnoff = communityService.singoCheck(likeSingoDTO);
 		ModelAndView mav = new ModelAndView();
 	    mav.addObject("replySingoOnoff", replySingoOnoff);
 	    mav.setViewName("jsonView");
 		return mav;
 	}
//--댓글 신고 체크
 	@RequestMapping(value = "/community/checkReplySingo", method=RequestMethod.POST)
 	@ResponseBody
 	public ModelAndView checkReplySingo(@RequestParam Map<String,String> map, HttpSession session) {
 		LikeSingoDTO likeSingoDTO = new LikeSingoDTO();
		likeSingoDTO.setEmail((String) session.getAttribute("memId"));
		likeSingoDTO.setSeq(Integer.parseInt(map.get("seq")));
		if(!map.get("replySeq").equals("")) {
			likeSingoDTO.setReplySeq(Integer.parseInt(map.get("replySeq")));
		}else {
			likeSingoDTO.setReplySeq(0);
		}
		int replySingoOnoff = communityService.singoCheck(likeSingoDTO);
 		ModelAndView mav = new ModelAndView();
 	    mav.addObject("replySingoOnoff", replySingoOnoff); 	 
 	    mav.setViewName("jsonView");
 		return mav;
 	}
   
   
}