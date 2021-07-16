package myPage.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import community.bean.CommunityDTO;
import community.bean.LikeSingoDTO;
import community.bean.ReplyDTO;
import community.service.CommunityService;
import member.bean.MemberDTO;
import member.service.MemberService;
import myPage.bean.LikeListDTO;
import myPage.bean.MyPageLikePaging;
import myPage.bean.MyPagePaging;
import myPage.bean.MyPageReplyPaging;
import myPage.bean.PointHistoryDTO;
import myPage.bean.PointHistoryPaging;
import myPage.bean.TotalPointHistoryDTO;
import myPage.service.MyPageService;

@Controller
public class MyPageController {
	@Autowired
	MyPageService myPageService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private CommunityService communityService;

	@RequestMapping(value = "/member/modify", method = RequestMethod.POST)
	public ModelAndView modify(@ModelAttribute MemberDTO memberDTO) {
		myPageService.modify(memberDTO);
		return new ModelAndView("redirect:/member/userModify");
	}

	@RequestMapping(value = "/member/nicknameChange", method = RequestMethod.GET)
	public String nicknameChange() {
		return "/member/nicknameChange";
	}

	@RequestMapping(value = "/member/useNicknameItem", method = RequestMethod.POST)
	@ResponseBody
	public String useNicknameItem(@RequestParam Map<String, String> map) {
		myPageService.nicknameChange(map);
		return "/member/nicknameChange";
	}

	// 내가 쓴글 뿌리기
	// ------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/member/getMypageBoardList", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getMypageBoardList(@RequestParam(required = false, defaultValue = "1") String pg,
			@RequestParam String email) {

		// 1페이지 당 5개의 글
		List<CommunityDTO> list = myPageService.getMypageBoardList(pg, email);

		// 페이징처리
		MyPagePaging mypageBoardPaging = myPageService.MypageBoardPaging(pg, email);

		ModelAndView mav = new ModelAndView();

		mav.addObject("list", list);
		mav.addObject("MypageBoardPaging", mypageBoardPaging);
		mav.setViewName("jsonView");
		return mav;
	}

	// 선택한글 쫙 삭제---------------------------------------------
	@RequestMapping(value = "/member/myPageBoardDelete", method = RequestMethod.POST)
	public ModelAndView myPageboardDelete(String[] check) {
		myPageService.myPageBoardDelete(check);

		return new ModelAndView("redirect:/member/myPage");
	}

	// 글 하나하나 삭제---------------------------------------------
	@RequestMapping(value = "/member/myPageBoardDeleteOne", method = RequestMethod.POST)
	@ResponseBody
	public void myPageBoardDeleteOne(String[] check) {
		myPageService.myPageBoardDelete(check);
	}

	// 내가 쓴 댓글 뿌리기
	// ------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/member/getMypageReplyList", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getMypageReplyList(@RequestParam(required = false, defaultValue = "1") String pg,
			@RequestParam String email) {

		// 1페이지 당 5개의 글
		List<ReplyDTO> list = myPageService.getMypageReplyList(pg, email);
		// 페이징처리
		MyPageReplyPaging mypageReplyPaging = myPageService.MypageReplyPaging(pg, email);
		ModelAndView mav = new ModelAndView();

		mav.addObject("list", list);
		mav.addObject("MypageReplyPaging", mypageReplyPaging);
		mav.setViewName("jsonView"); // json으로 갈 수 있도록 제이슨으로 자동으로 변환하라?
		return mav;
	}

	// 선택한 댓글 쫙 삭제---------------------------------------------
	@RequestMapping(value = "/member/myPageReplyDelete", method = RequestMethod.POST)
	@ResponseBody
	public void myPageReplyDelete(String[] check) {
		
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("check",check);
		List<ReplyDTO> list = myPageService.getCommunitySeq(map);
		myPageService.updateReplyCount(list);
		myPageService.myPageReplyDelete(map);
	}

	// 내가작성한글로가기
	@RequestMapping(value = "/community/community_myView", method = RequestMethod.GET)
	public String community_myView(Model model, @RequestParam(required = false, defaultValue = "1") String pg,
			@RequestParam String seq, @RequestParam String com) {

		model.addAttribute("pg", pg);
		model.addAttribute("seq", seq);
		model.addAttribute("com", com);
		model.addAttribute("display", "/community/community_myView.jsp");
		return "/index";
	}

//프로필사진 업로드 ----------------------------------------------------------------
	@RequestMapping(value = "/member/profile_upload", method = RequestMethod.POST)
	public String profile_upload(@ModelAttribute MemberDTO memberDTO, @RequestParam MultipartFile img) {
		String filePath = "D:\\Spring\\FinalProject\\git_Project\\simriTest\\simri\\src\\main\\webapp\\storage";
		String filePathAdmin = "D:\\Spring\\FinalProject\\git_Project\\gitAdmin\\adminPage\\src\\main\\webapp\\storage";
		String fileName = img.getOriginalFilename();
		File file = new File(filePath, fileName);// 파일 생성
		File fileAdmin = new File(filePathAdmin, fileName);
		// 파일 복사
		try {
			FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(file));
			FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(fileAdmin));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (fileName.equals("")) {
			memberDTO.setProfile("null.jpg");
		} else {
			fileName = "/simriTest/storage/" + img.getOriginalFilename();
			memberDTO.setProfile(fileName);
		}
		myPageService.profile_upload(memberDTO);
		return "profileChange";
	}

//상점------------------------

	@RequestMapping(value = "/member/pointstore", method = RequestMethod.POST)
	@ResponseBody
	public void pointstore(@RequestParam Map<String, Object> map) {
		myPageService.pointstore(map);
	}

//탈퇴---------------------------
	@RequestMapping(value = "/member/exitMember", method = RequestMethod.GET)
	public String exitMember(HttpSession session, Model model) {
		String id = (String) session.getAttribute("memId");
		MemberDTO memberDTO = memberService.getMemberDTO(id);
		model.addAttribute("display", "/member/exitMember.jsp");
		model.addAttribute("memberDTO", memberDTO);
		model.addAttribute("check", "1");
		return "/member/myPage";

	}

//탈퇴폼 띄우기---------------------------
	@RequestMapping(value = "/member/pointCharge", method = RequestMethod.GET)
	@ResponseBody
	public void pointCharge(@RequestParam Map<String, Object> map) {
		myPageService.pointCharge(map);
	}

	// 레알탈퇴
	@RequestMapping(value = "/member/realExitMember", method = RequestMethod.POST)
	@ResponseBody
	public String realExitMember(@RequestParam String email, @RequestParam String reason, HttpSession session) {
		myPageService.realExitMember(email, reason, session);
		session.invalidate();
		return "logout";
	}

	// 마이페이지 좋아요 뿌리기
	// ------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/member/getMypageLikeList", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getMypageLikeList(@RequestParam(required = false, defaultValue = "1") String pg,
			@RequestParam String email) {

		// 1페이지 당 5개의 글
		List<LikeListDTO> list = myPageService.getMypageLikeList(pg, email);
		// 페이징처리
		MyPageLikePaging mypageLikePaging = myPageService.mypageLikePaging(pg, email);
		

		ModelAndView mav = new ModelAndView();

		mav.addObject("list", list);
		mav.addObject("MypageLikePaging", mypageLikePaging);
		mav.setViewName("jsonView"); // json으로 갈 수 있도록 제이슨으로 자동으로 변환하라?
		return mav;
	}

	// 포인트사용,충전리스트 ---------------------------------------------
	@RequestMapping(value = "/member/pointHistoryList", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView pointHistoryList(@RequestParam(required = false, defaultValue = "1") String pg, HttpSession session, Model model) {
		
		String id = (String) session.getAttribute("memId");
		MemberDTO memberDTO = memberService.getMemberDTO(id);
		TotalPointHistoryDTO totalPointHistoryDTO = myPageService.getTotalPointHistory(id);
		List<PointHistoryDTO> list = myPageService.pointHistoryList(pg, id);
		
		// 페이징처리
		PointHistoryPaging pointHistoryPaging = myPageService.pointHistoryPaging(pg, id);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("totalPointHistoryDTO",totalPointHistoryDTO);
		mav.addObject("pointPg", pg);
		mav.addObject("list", list);
		mav.addObject("memberDTO", memberDTO);
		mav.addObject("PointHistoryPaging", pointHistoryPaging);
		mav.setViewName("jsonView"); // json으로 갈 수 있도록 제이슨으로 자동으로 변환하라?
		return mav;

	}
}
