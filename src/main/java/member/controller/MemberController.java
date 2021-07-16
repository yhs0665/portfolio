package member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import member.bean.MemberDTO;
import member.dao.GoogleOAuthRequest;
import member.dao.GoogleOAuthResponse;
import member.dao.KakaoProfile;
import member.dao.OAuthToken;
import member.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;

	// 로그인------------------------------------------------------------------------------------------
	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView login(@RequestParam Map<String, String> map, HttpSession session) { // HttpServletRequest request
		// DB
		MemberDTO memberCondition = memberService.conditionCheck(map.get("email"));
		ModelAndView mav = new ModelAndView();
		if(memberCondition!=null) {
			mav.addObject("memberCondition",memberCondition);
			mav.setViewName("jsonView");
			return mav;
		}
		String result = memberService.login(map, session);
		mav.addObject("result",result);
		mav.setViewName("jsonView");
		return mav;
	}// login()

	// 로그아웃------------------------------------------------------------------------------------------
	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate(); // 모든 세션 죽이기!! 무효화

		return "logout"; // index.jsp로 가자
	}

	// 중복체크------------------------------------------------------------------------------------------
	@RequestMapping(value = "/member/checkId", method = RequestMethod.POST)
	@ResponseBody
	public String checkId(@RequestParam String email) {
		return memberService.checkId(email);
	}// checkId()

	
// 이메일 인증------------------------------------------------------------------------------------------
	@RequestMapping(value = "/member/emailCheckMail", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView emailCheckMail(@RequestParam String email) {
		ModelAndView mav = new ModelAndView();
		String result = memberService.confirmEmail(email);
		mav.addObject("result",result);
		mav.setViewName("jsonView"); // json으로 갈 수 있도록 제이슨으로 자동으로 변환하라?
		return mav;
	}// emailCheckMail()
	
		
	// 비밀번호 찾기
	// 폼------------------------------------------------------------------------------------------
	@RequestMapping(value = "/member/findPassword")
	public String findPassword() throws Exception {
		return "/member/findPassword";
	}

	// 메일로 임시비번 보내
	// ------------------------------------------------------------------------------------------
	@RequestMapping(value = "/member/getPwd", method = RequestMethod.POST)
	@ResponseBody
	public String getPwd(@RequestParam String email, HttpServletResponse response) {
		return memberService.getPwd(email, response);
	}

	// 카카오
	// 로그인-----------------------------------------------------------------------------------------
	@RequestMapping(value = "/member/kakao", method = RequestMethod.GET)
	public String kakao(String code, Model model) {
		RestTemplate rt = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// Httpbody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "5a9b475eb2415e82114c765c3f09c849");
		params.add("redirect_uri", "http://localhost:8080/simriTest/member/kakao");
		params.add("code", code);

		// HttpHeader 와 // Httpbdy를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		// Http 요청하기 - POST 방식으로 - 그리고 response 변수의 응답 받음
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				kakaoTokenRequest, String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}


		RestTemplate rt2 = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

		// Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
				kakaoProfileRequest2, String.class);


		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		// 카카오 계정 이메일
		String email = kakaoProfile.getKakao_account().getEmail();
		String nickname = kakaoProfile.getProperties().getNickname();
        String profile = kakaoProfile.getKakao_account().getProfile().getProfile_image_url();
		
		String checkIdResult = memberService.checkId(email);
		MemberDTO memberCondition = memberService.conditionCheck(email);
		if(memberCondition!=null) {
			model.addAttribute("memberCondition", memberCondition);
			return "socialStopLogin";
		}
		
		if(checkIdResult == "kakao") {
			memberService.socialLogin(email);
			return "socialLogin";
		}
		
		if (checkIdResult == "non_exist") {
			if(nickname.equals("admin") || nickname.equals("관리자")) {
				String randomNickname = memberService.createRandomNickname(email);
				memberService.socialJoin(email, randomNickname, profile, "kakao");
				memberService.socialLogin(email);
				return "socialLogin";
			}
			
			memberService.socialJoin(email, nickname, profile, "kakao");
			memberService.socialLogin(email);
			return "socialLogin";
		}
		return "socialLoginFail";
	}// kakao()
	
	
	//google 구글
	@RequestMapping(value = "/member/google", method = RequestMethod.GET)
	public String google(Model model, @RequestParam(value = "code") String authCode)
			throws JsonProcessingException {
		// HTTP Request를 위한 RestTemplate
		RestTemplate restTemplate = new RestTemplate();
		// Google OAuth Access Token 요청을 위한 파라미터 세팅
		GoogleOAuthRequest googleOAuthRequestParam = GoogleOAuthRequest.builder()
				.clientId("743752575152-svdejceorb61u42i3g7cvrf5q23ejtms.apps.googleusercontent.com")
				.clientSecret("MYcDmZPg00MJonWGlfoP0lCy").code(authCode)
				.redirectUri("http://localhost:8080/simriTest/member/google").grantType("authorization_code").build();
			
		// JSON 파싱을 위한 기본값 세팅
		// 요청시 파라미터는 스네이크 케이스로 세팅되므로 Object mapper에 미리 설정해준다.
		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		mapper.setSerializationInclusion(Include.NON_NULL);
		// AccessToken 발급 요청
		ResponseEntity<String> resultEntity = restTemplate.postForEntity("https://oauth2.googleapis.com/token",
				googleOAuthRequestParam, String.class);
		// Token Request
		GoogleOAuthResponse result = mapper.readValue(resultEntity.getBody(), new TypeReference<GoogleOAuthResponse>() {
		});
		// ID Token만 추출 (사용자의 정보는 jwt로 인코딩 되어있다)
		String jwtToken = result.getIdToken();
		String requestUrl = UriComponentsBuilder.fromHttpUrl("https://oauth2.googleapis.com/tokeninfo")
				.queryParam("id_token", jwtToken).encode().toUriString();
		String resultJson = restTemplate.getForObject(requestUrl, String.class);

		Map<String, String> userInfo = mapper.readValue(resultJson, new TypeReference<Map<String, String>>() {
		});
		// 소셜 계정 이메일
		String email = userInfo.get("email");
		String frontName = "";
		if(userInfo.get("family_name") == null) {
    	  frontName = "";
		};
		String backName = userInfo.get("given_name");
		String nickname = frontName + backName;
		String profile = userInfo.get("picture");
		
		
		MemberDTO memberCondition = memberService.conditionCheck(email);
		if(memberCondition!=null) {
			model.addAttribute("memberCondition", memberCondition);
			return "socialStopLogin";
		}
		
		String checkIdResult = memberService.checkId(email);
		if(checkIdResult == "google") {
			memberService.socialLogin(email);
			return "socialLogin";
		}
		
		if (checkIdResult == "non_exist") {
			if(nickname.equals("admin") || nickname.equals("관리자")) {
				String randomNickname = memberService.createRandomNickname(email);
				memberService.socialJoin(email, randomNickname, profile, "google");
				memberService.socialLogin(email);
				return "socialLogin";
			}
			
			memberService.socialJoin(email, nickname, profile, "google");
			memberService.socialLogin(email);
			return "socialLogin";
		}
		return "socialLoginFail";

	}

	// 회원가입
	@RequestMapping(value = "/member/write", method = RequestMethod.POST)
	@ResponseBody
	public void write(@ModelAttribute MemberDTO memberDTO) {
		memberService.write(memberDTO);
	}
	
}
