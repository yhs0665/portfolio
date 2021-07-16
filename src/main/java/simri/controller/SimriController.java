package simri.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import community.bean.CommunityDTO;
import community.bean.LikeSingoDTO;
import community.bean.ReplyDTO;
import community.service.CommunityService;
import member.bean.MemberDTO;
import member.service.MemberService;
import myPage.bean.TotalPointHistoryDTO;
import myPage.service.MyPageService;
import simri.bean.AdvertiseDTO;
import simri.bean.SimriDTO;
import simri.bean.SimriTestPaging;
import simri.service.SimriService;

@Controller
public class SimriController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private SimriService simriService;
	@Autowired
	private CommunityService communityService;
	@Autowired
	private MyPageService myPageService;
	
	
	
	@RequestMapping(value = "/simriIntroduce/simriIntroduce", method = RequestMethod.GET)
	public String simriIntroduce(Model model) {
		model.addAttribute("com", "2");
		model.addAttribute("display", "/simriIntroduce/simriIntroduce.jsp");
		return "/index";
	}
	
	

	@RequestMapping(value = "/simriTest/simriTest_List", method = RequestMethod.GET)
	public String simriTest_List(Model model) {
		model.addAttribute("display", "/simriTest/simriTest_List.jsp");
		return "/index";
	}

	@RequestMapping(value = "/simriTest/getSimriTest_List", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getSimriTest_List(HttpSession session, HttpServletResponse response) {

		List<SimriDTO> list = simriService.getSimriTest_List();

		// 조회수 - 새로고침 방지
		if (session.getAttribute("memNickname") != null) {
			Cookie cookie = new Cookie("memSimriHit", "0"); // 쿠키생성
			cookie.setMaxAge(30 * 60); // 초단위 // 30분
			response.addCookie(cookie); // 클라이언트 보내기
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("memNickname", session.getAttribute("memNickname"));
		mav.addObject("list", list);
		mav.setViewName("jsonView"); // json으로 자동으로 변환
		return mav;
	}

	@RequestMapping(value = "/simriTest/simriTestRead", method = RequestMethod.POST)
	public String simriTestRead(Model model, @RequestParam String seq,
			@RequestParam(required = false, defaultValue = "0") String similarSeq, HttpSession session,
			@CookieValue(value = "memSimriHit", required = false) Cookie simriCookie,
			@CookieValue(value = "totalSearchCookie", required = false) Cookie totalSearchCookie,
			@CookieValue(value = "simriSideCookie", required = false) Cookie simriSideCookie,
			@CookieValue(value = "similarCookie", required = false) Cookie similarCookie,
			HttpServletResponse response
			) {

	   
	      int sw=0;
		    
	      if (simriCookie != null | totalSearchCookie != null | simriSideCookie != null) {				
				
	    	  if(totalSearchCookie != null) {
	    		  sw=1;
	    		  communityService.hitUpdate(seq); // 조회수 증가
	    		  totalSearchCookie.setMaxAge(0); // 쿠키 삭제
	    		  totalSearchCookie.setPath("/");
	    		  response.addCookie(totalSearchCookie); // 클라이언트 보내기
					
				}
	    	  if (simriSideCookie != null) {
	    		  sw=1;
	    		  communityService.hitUpdate(seq); // 조회수 증가
	    		  simriSideCookie.setMaxAge(0); // 쿠키 삭제
	    		  simriSideCookie.setPath("/");
	    		  response.addCookie(simriSideCookie); // 클라이언트 보내기
	    	  }
	    	  

	    	  if (similarCookie != null) {
	    		  sw=1;
	    		  communityService.hitUpdate(seq); // 조회수 증가
	    		  similarCookie.setMaxAge(0); // 쿠키 삭제
	    		  similarCookie.setPath("/");
	    		  response.addCookie(similarCookie); // 클라이언트 보내기
	    	  }
	    	  
				if (simriCookie != null) {
				if(sw != 1) {
					communityService.hitUpdate(seq); // 조회수 증가
						
				}
					simriCookie.setMaxAge(0); // 쿠키 삭제
					response.addCookie(simriCookie); // 클라이언트 보내기
					
				}
			}
		
		LikeSingoDTO likeSingoDTO = new LikeSingoDTO();
		
		

		SimriDTO simriDTO = null;
		if (similarSeq.equals("0")) {
			if (session.getAttribute("memId") == null) {
				likeSingoDTO.setEmail("null");
			} else {
				likeSingoDTO.setEmail((String) session.getAttribute("memId"));
				likeSingoDTO.setSeq(Integer.parseInt(seq));
			}
			simriDTO = simriService.getCommunity(seq);
			model.addAttribute("seq", seq);
		} else {
			if (session.getAttribute("memId") == null) {
				likeSingoDTO.setEmail("null");
			} else {
				likeSingoDTO.setEmail((String) session.getAttribute("memId"));
				likeSingoDTO.setSeq(Integer.parseInt(similarSeq));
			}
			simriDTO = simriService.getCommunity(similarSeq);
			model.addAttribute("seq", similarSeq);
		}
		
		int onoff = simriService.testLikeCheck(likeSingoDTO);

		if (onoff == 2)
			onoff = 0;
		model.addAttribute("onoff", onoff);
		model.addAttribute("memId", session.getAttribute("memId"));
		model.addAttribute("simriDTO", simriDTO);
		model.addAttribute("display", "/simriTest/simriTestRead.jsp");
		return "/index";
	}

	@RequestMapping(value = "/simriTest/getSimilar", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getSimilar(@RequestParam String seq) {
		
		SimriDTO simriDTO = simriService.getCommunity(seq);
		if (simriDTO.getHashTag2() == null) {
			simriDTO.setHashTag2("");
		}
		if (simriDTO.getHashTag3() == null) {
			simriDTO.setHashTag3("");
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("seq", seq);
		map.put("category", simriDTO.getCategory());
		map.put("hashTag1", simriDTO.getHashTag1());
		map.put("hashTag2", simriDTO.getHashTag2());
		map.put("hashTag3", simriDTO.getHashTag3());
		List<SimriDTO> list = simriService.getSimriList(map);

		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("jsonView"); // json으로 자동으로 변환
		return mav;
	}

	@RequestMapping(value = "/simriTest/reply_Delete", method = RequestMethod.GET)
	public String reply_Delete(Model model, @RequestParam(required = false, defaultValue = "28") String seq,
			@RequestParam String replySeq) {
		communityService.reply_Delete(replySeq);
		SimriDTO simriDTO = simriService.getCommunity(seq);

		model.addAttribute("seq", seq);
		model.addAttribute("simriDTO", simriDTO);
		model.addAttribute("display", "/simriTest/simriTestRead.jsp");

		return "/index";
	}

	@RequestMapping(value = "/love/magazineReply_Delete", method = RequestMethod.GET)
	public String magazineReply_Delete(Model model, @RequestParam(required = false, defaultValue = "28") String seq,
			@RequestParam String replySeq) {
		communityService.reply_Delete(replySeq);
		SimriDTO simriDTO = simriService.getCommunity(seq);

		model.addAttribute("seq", seq);
		model.addAttribute("simriDTO", simriDTO);
		model.addAttribute("display", "/love/magazineContent.jsp");

		return "/index";
	}

	@RequestMapping(value = "/simriTest/testLike", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView testLike(@RequestParam String seq,
			@RequestParam(required = false, defaultValue = "11") String category, HttpSession session) {

		LikeSingoDTO likeSingoDTO = new LikeSingoDTO();
		likeSingoDTO.setEmail((String) session.getAttribute("memId"));
		likeSingoDTO.setSeq(Integer.parseInt(seq));
		int onoff = simriService.testLikeCheck(likeSingoDTO);

		if (onoff == 2) {
			if (!category.equals("11")) {
				likeSingoDTO.setCategory(category);
			} else {
				CommunityDTO communityDTO = simriService.getMagazine(seq);
				likeSingoDTO.setCategory(communityDTO.getCategory());
			}

			likeSingoDTO.setNickName((String) session.getAttribute("memNickname"));
			likeSingoDTO.setPalette((String)session.getAttribute("memPalette"));
			simriService.testLikeInsert(likeSingoDTO);
			onoff = 0;
		}

		if (category.equals("11")) {
			CommunityDTO communityDTO = simriService.getMagazine(seq);
			likeSingoDTO.setCategory(communityDTO.getCategory());
		}

		if (onoff == 0) {
			simriService.testLike(likeSingoDTO);
		} else {
			simriService.testLikeCancel(likeSingoDTO);
		}
		onoff = simriService.testLikeCheck(likeSingoDTO);
		ModelAndView mav = new ModelAndView();
		mav.addObject("onoff", onoff);
		mav.setViewName("jsonView");
		return mav;
	}

	// --댓글 신고 체크
	@RequestMapping(value = "/simriTest/checkReplySingo", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView checkReplySingo(@RequestParam Map<String, String> map, HttpSession session) {
		LikeSingoDTO likeSingoDTO = new LikeSingoDTO();
		likeSingoDTO.setEmail((String) session.getAttribute("memId"));
		likeSingoDTO.setSeq(Integer.parseInt(map.get("seq")));
		if (!map.get("replySeq").equals("")) {
			likeSingoDTO.setReplySeq(Integer.parseInt(map.get("replySeq")));
		} else {
			likeSingoDTO.setReplySeq(0);
		}
		int replySingoOnoff = simriService.singoCheck(likeSingoDTO);
		ModelAndView mav = new ModelAndView();
		mav.addObject("replySingoOnoff", replySingoOnoff);
		mav.setViewName("jsonView");
		return mav;
	}

	// 신고---------------------------
	@RequestMapping(value = "/simriTest/replySingo", method=RequestMethod.POST)
 	@ResponseBody
 	public void replySingo(@RequestParam Map<String,String> map, HttpSession session) {
 		 		
		LikeSingoDTO likeSingoDTO = new LikeSingoDTO();
		likeSingoDTO.setEmail((String) session.getAttribute("memId"));
		likeSingoDTO.setSingoReason(map.get("singoReason"));
		likeSingoDTO.setSeq(Integer.parseInt(map.get("seq")));
		if(!map.get("replySeq").equals("")) {
			likeSingoDTO.setReplySeq(Integer.parseInt(map.get("replySeq")));
		}else {
			likeSingoDTO.setReplySeq(0);
		}
		
		int replySingoOnoff = simriService.singoCheck(likeSingoDTO);
		
		
		if (replySingoOnoff == 2) {
			likeSingoDTO.setCategory(map.get("category"));
			likeSingoDTO.setNickName((String) session.getAttribute("memNickname"));
			likeSingoDTO.setPalette((String) session.getAttribute("memPalette"));
			simriService.singoInsert(likeSingoDTO);
			replySingoOnoff = 0;
		}
		ReplyDTO replyDTO = communityService.getReply(map.get("replySeq"));
		if (replySingoOnoff == 0) {
			map.put("email", (String) session.getAttribute("memId"));
			map.put("singoEmail", replyDTO.getEmail());
			simriService.replySingo(map);
		}
	
 	}

	// -----------totalSearch------------------------------------------------------------------------

	@RequestMapping(value = "/simriTest/totalSearch", method = RequestMethod.POST)
	public String community_List(Model model, @RequestParam String totalKeyword, @RequestParam(required = false, defaultValue = "1") String searchPg) {
		
		model.addAttribute("pg", searchPg);
		model.addAttribute("totalKeyword", totalKeyword);
		model.addAttribute("display", "/search/totalSearch.jsp");
		return "/index";
	}

	/* 리스트 검색 */
	@RequestMapping(value = "/simriTest/getTotalSearch", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getTotalSearch(@RequestParam Map<String, String> map, HttpSession session,
			@RequestParam(required = false, defaultValue = "0") int searchMemberPoint,
			@RequestParam(required = false, defaultValue = "1") String pg) {
		
		
		map.put("pg", pg);
		map.put("keyword", "");
		List<CommunityDTO> list = simriService.getTotalSearch(map);

		// 세션
		String memNickname = (String) session.getAttribute("memNickname");

		// 페이징 처리
		SimriTestPaging simriTestPaging = simriService.simriTestPaging(map);

		ModelAndView mav = new ModelAndView();
		if (session.getAttribute("memId") != null) {
			MemberDTO memberDTO = memberService.getMemberDTO((String) session.getAttribute("memId"));
			
			// 한사람의 포인트 갖고오기
			int memberPoint = memberDTO.getPoint();
			mav.addObject("memberPoint", memberPoint);
			
		}
		mav.addObject("pg", map.get("pg"));
		mav.addObject("list", list);
		mav.addObject("memPalette", session.getAttribute("memPalette"));
		mav.addObject("memNickname", memNickname);
		mav.addObject("simriTestPaging", simriTestPaging);
		mav.addObject("totalKeyword", map.get("totalKeyword"));
		mav.setViewName("jsonView");
		return mav;
	}

	// ---------지경-------------------------------------------------------------------
	// 연애심리글 리스트
	@RequestMapping(value = "/love/magazineList", method = RequestMethod.GET)
	public String magazineList(Model model, @RequestParam(required = false, defaultValue = "1") String pg,
			HttpSession session) {

		if (session.getAttribute("memId") != null) {
			MemberDTO memberDTO = memberService.getMemberDTO((String) session.getAttribute("memId"));

			// 한사람의 포인트 갖고오기
			int memberPoint = memberDTO.getPoint();
			model.addAttribute("memberPoint", memberPoint);

		}

		model.addAttribute("pg", pg);
		model.addAttribute("display", "/love/magazineList.jsp");
		return "/index";
	}

	/* 리스트 검색 */
	@RequestMapping(value = "/love/magazine_Search", method = RequestMethod.POST)
	public @ResponseBody ModelAndView magazine_Search(@RequestParam Map<String, String> map, HttpSession session,
			@RequestParam(required = false, defaultValue = "0") int SearchmemberPoint) {

		List<CommunityDTO> list = simriService.magazine_Search(map);

		// 세션
		String memNickname = (String) session.getAttribute("memNickname");

		// 페이징 처리
		SimriTestPaging simriTestPaging = simriService.simriTestPaging(map);


		ModelAndView mav = new ModelAndView();
		mav.addObject("pg", map.get("pg"));
		mav.addObject("list", list);
		mav.addObject("memberPoint", SearchmemberPoint);
		mav.addObject("memNickname", memNickname);
		mav.addObject("simriTestPaging", simriTestPaging);
		mav.setViewName("jsonView");
		return mav;
	}

	// 연애심리글 글들 갖고오기
	@RequestMapping(value = "/love/getMagazine_List", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getMagazine_List(HttpSession session, HttpServletResponse response,
			@RequestParam(required = false, defaultValue = "1") String pg,
			@RequestParam(required = false, defaultValue = "0") int memberPoint) {

		List<SimriDTO> list = simriService.getMagazine_List(pg);

		// 세션
		String memNickname = (String) session.getAttribute("memNickname");

		// 조회수 - 새로고침 방지
		if (session.getAttribute("memNickname") != null) {
			Cookie cookie = new Cookie("memMagazineHit", "0"); // 쿠키생성
			cookie.setMaxAge(30 * 60); // 초단위 // 30분
			response.addCookie(cookie); // 클라이언트 보내기
		}

		// 페이징 처리
		SimriTestPaging simriTestPaging = simriService.simriTestPaging(pg);

		ModelAndView mav = new ModelAndView();

		mav.addObject("list", list);
		mav.addObject("pg", pg);
		mav.addObject("memberPoint", memberPoint);
		mav.addObject("memNickname", memNickname);
		mav.addObject("simriTestPaging", simriTestPaging);
		mav.setViewName("jsonView"); // json으로 자동으로 변환
		return mav;
	}

	// 연애심리글 뷰로 가기
	@RequestMapping(value = "/love/magazineContent", method = RequestMethod.POST)
	public String magazineContent(Model model, @RequestParam(required = false, defaultValue = "1") String pg,
			@RequestParam String seq, HttpSession session,
			@RequestParam(required = false, defaultValue = "0") String similarSeq,
			@RequestParam(required = false, defaultValue = "0") int memberPoint,
			@RequestParam(required = false, defaultValue = "0") int boardPoint) {

		// 포인트 글 누르면 회원 포인트 차감
		String memId = (String) session.getAttribute("memId");
		String memNickname = (String) session.getAttribute("memNickname");
		String memPalette = (String) session.getAttribute("memPalette");

		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("memId", memId);
		map.put("boardPoint", boardPoint);
		map.put("seq", seq);
		int checkPoint = simriService.getCheckPoint(map);
		if (checkPoint == 2) {
			map.put("category", "연애 심리글");
			map.put("email", memId);
			map.put("nickName", memNickname);
			map.put("palette", memPalette);
			simriService.checkPointInsert(map);
			simriService.minusPoint(map);
		}


		// 좋아요
		LikeSingoDTO likeSingoDTO = new LikeSingoDTO();
		likeSingoDTO.setEmail((String) session.getAttribute("memId"));
		likeSingoDTO.setSeq(Integer.parseInt(seq));
		int onoff = simriService.likeCheck(likeSingoDTO);
		

		if (onoff == 2)
			onoff = 0;
		model.addAttribute("onoff", onoff);
		
		if (similarSeq.equals("0")) {
			model.addAttribute("seq", seq);
		} else {
			model.addAttribute("seq", similarSeq);
		}
		model.addAttribute("memberPoint", memberPoint);
		model.addAttribute("pg", pg);
		model.addAttribute("checkPoint", checkPoint);
		model.addAttribute("display", "/love/magazineContent.jsp");
		return "/index";
	}

	/* 1인 게시글 갖고오기 view */
	@RequestMapping(value = "/love/getMagazine", method = RequestMethod.POST) // 페이지가 안들어올수도있으니까 이렇게 디폴트값주기
	@ResponseBody
	public ModelAndView getMagazine(@RequestParam String seq, HttpSession session,
			@CookieValue(value = "memMagazineHit", required = false) Cookie magazineCookie,
			@CookieValue(value = "loveSideCookie", required = false) Cookie loveSideCookie,
			@CookieValue(value = "totalSearchCookie", required = false) Cookie totalSearchCookie,
			@CookieValue(value = "similarCookie", required = false) Cookie similarCookie,
			HttpServletResponse response) {
		  // 조회수 - 새로고침 방지
	   
		int sw=0;
	    
		if (loveSideCookie != null | magazineCookie != null | totalSearchCookie != null | similarCookie !=null) {
			if (loveSideCookie != null) {
				sw=1;
				communityService.hitUpdate(seq); // 조회수 증가
				loveSideCookie.setMaxAge(0); // 쿠키 삭제
				loveSideCookie.setPath("/");
				response.addCookie(loveSideCookie); // 클라이언트 보내기
			}
	
			if(totalSearchCookie != null) {
				sw=1;
				communityService.hitUpdate(seq); // 조회수 증가
				totalSearchCookie.setMaxAge(0); // 쿠키 삭제
				totalSearchCookie.setPath("/");
				response.addCookie(totalSearchCookie); // 클라이언트 보내기
				
			}
			
			if (similarCookie != null) {
	    		  sw=1;
	    		  communityService.hitUpdate(seq); // 조회수 증가
	    		  similarCookie.setMaxAge(0); // 쿠키 삭제
	    		  similarCookie.setPath("/");
	    		  response.addCookie(similarCookie); // 클라이언트 보내기
	    	  }

			if (magazineCookie != null) {
				if(sw != 1) {
					communityService.hitUpdate(seq); // 조회수 증가
					
				}
				magazineCookie.setMaxAge(0); // 쿠키 삭제
				response.addCookie(magazineCookie); // 클라이언트 보내기
				
			}
		}
		
		// 한사람의 글을 가지고오기
		CommunityDTO communityDTO = simriService.getMagazine(seq);

		// 세션
		String memNickname = (String) session.getAttribute("memNickname");

		ModelAndView mav = new ModelAndView();
		mav.addObject("communityDTO", communityDTO);
		mav.addObject("memNickname", memNickname);
		mav.setViewName("jsonView");
		return mav;
	}

	// 연애 게시글 포인트 사용 유무 체크
	@RequestMapping(value = "/love/checkPoint", method = RequestMethod.POST) // 페이지가 안들어올수도있으니까 이렇게 디폴트값주기
	@ResponseBody
	public ModelAndView checkPoint(@RequestParam String seq, @RequestParam(required = false, defaultValue = "1") String pg,
									HttpSession session) {

		String memId = (String) session.getAttribute("memId");
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("memId", memId);
		map.put("seq", seq);
		int checkPoint = simriService.getCheckPoint(map);
		ModelAndView mav = new ModelAndView();
		mav.addObject("pg", pg);
		mav.addObject("seq", seq);
		mav.addObject("checkPoint", checkPoint);
		mav.setViewName("jsonView");
		return mav;
	}

	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "/member/login";
	}

	@RequestMapping(value = "/member/memberWriteForm", method = RequestMethod.GET)
	public String memberWriteForm(Model model) {
		return "/member/memberWriteForm";
	}

	@RequestMapping(value = "/member/findPassword", method = RequestMethod.GET)
	public String findPassword(Model model) {
		return "/member/findPassword";
	}

	@RequestMapping(value = "/member/myPage", method = RequestMethod.GET)
	public String myPage(@RequestParam(required = false, defaultValue = "1") String pg, HttpSession session,
			Model model) {
		String id = (String) session.getAttribute("memId"); // 한사람 분의 데이터를 꺼내온다
		MemberDTO memberDTO = memberService.getMemberDTO(id);
		model.addAttribute("memberDTO", memberDTO); // dto를 가지고 와야 현재값을 뿌려준다
		model.addAttribute("pg", pg); // pg값을 싣고 다녀야 하니까 model생성
		return "/member/myPage";
	}

	@RequestMapping(value = "/member/pointList", method = RequestMethod.GET)
	public String pointList(HttpSession session, Model model) {
		String id = (String) session.getAttribute("memId");
		MemberDTO memberDTO = memberService.getMemberDTO(id);
		TotalPointHistoryDTO totalPointHistoryDTO = myPageService.getTotalPointHistory(id);
		model.addAttribute("totalPointHistoryDTO", totalPointHistoryDTO);
		model.addAttribute("memberDTO", memberDTO);
		model.addAttribute("display", "/member/pointList.jsp");
		model.addAttribute("check", "1");
		return "/member/myPage";
	}

	@RequestMapping(value = "/member/userModify", method = RequestMethod.GET)
	public String userModify(HttpSession session, Model model) {
		String id = (String) session.getAttribute("memId"); // 한사람 분의 데이터를 꺼내온다
		MemberDTO memberDTO = memberService.getMemberDTO(id);
		model.addAttribute("memberDTO", memberDTO); // dto를 가지고 와야 현재값을 뿌려준다
		model.addAttribute("display", "/member/userModify.jsp");
		model.addAttribute("check", "1");
		return "/member/myPage";
	}

	@RequestMapping(value = "/member/history", method = RequestMethod.GET)
	public String history(Model model) {
		model.addAttribute("display", "/member/history.jsp");
		model.addAttribute("check", "1");
		return "/member/myPage";
	}

	@RequestMapping(value = "/member/pointStore", method = RequestMethod.GET)
	public String pointStore(HttpSession session, Model model) {
		String id = (String) session.getAttribute("memId");
		MemberDTO memberDTO = memberService.getMemberDTO(id);
		model.addAttribute("memberDTO", memberDTO);
		model.addAttribute("display", "/member/pointStore.jsp");
		model.addAttribute("check", "1");
		return "/member/myPage";
	}

	@RequestMapping(value = "/love/like", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView like(@RequestParam String seq,
			@RequestParam(required = false, defaultValue = "11") String category, HttpSession session) {

		LikeSingoDTO likeSingoDTO = new LikeSingoDTO();
		likeSingoDTO.setEmail((String) session.getAttribute("memId"));
		likeSingoDTO.setSeq(Integer.parseInt(seq));
		int onoff = simriService.likeCheck(likeSingoDTO);

		if (onoff == 2) {
			if (!category.equals("11")) {
				likeSingoDTO.setCategory(category);
			} else {
				CommunityDTO communityDTO = simriService.getMagazine(seq);
				likeSingoDTO.setCategory(communityDTO.getCategory());
			}

			likeSingoDTO.setNickName((String) session.getAttribute("memNickname"));
			likeSingoDTO.setPalette((String)session.getAttribute("memPalette"));
			simriService.likeInsert(likeSingoDTO);
			onoff = 0;
		}

		if (onoff == 0) {
			simriService.like(likeSingoDTO);
		} else {
			simriService.likeCancel(likeSingoDTO);
		}
		onoff = simriService.likeCheck(likeSingoDTO);
		ModelAndView mav = new ModelAndView();
		mav.addObject("onoff", onoff);
		mav.setViewName("jsonView");
		return mav;
	}

	@RequestMapping(value = "/love/loveShare", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView loveShare(@RequestParam String seq, HttpSession session) {
		String id = (String) session.getAttribute("memId");
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("memId", id);
		map.put("seq", Integer.parseInt(seq));

		simriService.loveShare(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("jsonView");
		return mav;
	}

	@RequestMapping(value = "/simriTest/simriShare", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView simriShare(@RequestParam String seq, HttpSession session) {
		String id = (String) session.getAttribute("memId");
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("memId", id);
		map.put("seq", Integer.parseInt(seq));

		simriService.loveShare(map);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("jsonView");
		return mav;
	}

	// 연애심리글 글들 갖고오기
	@RequestMapping(value = "/getLoveSide", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getLoveSide(HttpSession session, HttpServletResponse response,@RequestParam(required = false, defaultValue = "1") String pg) {

		List<SimriDTO> list = simriService.getLoveSide();
		// 세션
		String memNickname = (String) session.getAttribute("memNickname");

		ModelAndView mav = new ModelAndView();
		
		if (session.getAttribute("memId") != null) {
			MemberDTO memberDTO = memberService.getMemberDTO((String) session.getAttribute("memId"));

			// 한사람의 포인트 갖고오기
			int memberPoint = memberDTO.getPoint();
			mav.addObject("memberPoint", memberPoint);
		}


			mav.addObject("list", list);
			mav.addObject("pg", pg);
			mav.addObject("memNickname", memNickname);
			mav.setViewName("jsonView"); // json으로 자동으로 변환
			return mav;
		}
	
	@RequestMapping(value = "/getIndexData",method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getIndexData(HttpSession session) {
		List<SimriDTO> favoriteSimriTestlist = simriService.getFavoriteSimriTest_List();
		List<SimriDTO> favoriteMagazinelist = simriService.getFavoriteMagazine_List();
		List<SimriDTO> recentlySimriTestlist = simriService.getRecentlySimriTest_List();
		
		ModelAndView mav = new ModelAndView();
		String id = (String) session.getAttribute("memId");
		if(session.getAttribute("memId") != null) {
			MemberDTO memberDTO = memberService.getMemberDTO(id);
			mav.addObject("memberDTO", memberDTO);
		}
		
		mav.addObject("memId", id);
		mav.addObject("favoriteSimriTestlist", favoriteSimriTestlist);
		mav.addObject("favoriteMagazinelist", favoriteMagazinelist);
		mav.addObject("recentlySimriTestlist", recentlySimriTestlist);
		mav.setViewName("jsonView"); // json으로 갈 수 있도록 제이슨으로 자동으로 변환하라?
		return mav;
}
	// 심리테스트 글들 갖고오기
	@RequestMapping(value = "/getSimriSide", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getSimriSide(HttpSession session, HttpServletResponse response,@RequestParam(required = false, defaultValue = "1") String pg) {
		
		List<SimriDTO> list = simriService.getSimriSide();
		// 세션
		String memNickname = (String) session.getAttribute("memNickname");
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("list", list);
		mav.addObject("pg", pg);
		mav.addObject("memNickname", memNickname);
		mav.setViewName("jsonView"); // json으로 자동으로 변환
		return mav;
	}

	
	@RequestMapping(value = "/getCFList",method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getCFList(HttpSession session){
		List<AdvertiseDTO> cfList = simriService.getCFList();
		
		ModelAndView mav = new ModelAndView();
		String id = (String) session.getAttribute("memId");
		mav.addObject("memberDTO", null);
		if(session.getAttribute("memId") != null) {
			MemberDTO memberDTO = memberService.getMemberDTO(id);
			mav.addObject("memberDTO", memberDTO);
		}
		mav.addObject("memId", id);	
		mav.addObject("cfList",cfList);
		mav.setViewName("jsonView");
		
		return mav;
	}


}
