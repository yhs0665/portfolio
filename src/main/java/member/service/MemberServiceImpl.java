package member.service;

import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.mail.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.bean.ExitMemberDTO;
import member.bean.MemberDTO;
import member.dao.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private HttpSession session;

	// 로그인
	@Override
	public String login(Map<String, String> map, HttpSession session) {
		MemberDTO memberDTO = memberDAO.login(map);

		if (memberDTO == null) {
			return "fail";
		}
		session.setAttribute("memId", memberDTO.getEmail());
		session.setAttribute("memNickname", memberDTO.getNickname());
		session.setAttribute("memPalette", memberDTO.getPalette());
		session.setMaxInactiveInterval(60 * 60);
		return "success";
	}

	// 중복체크
	@Override
	public String checkId(String email) {
		//탈퇴된 내역이 있으면
		if(memberDAO.checkExitId(email) != null) {
			return "fail";
		}
		MemberDTO memberDTO = memberDAO.getMemberDTO(email);
		if(memberDTO != null) {
			
			if(memberDTO.getJoinType().equals("kakao")) {
				return "kakao";
			}else if(memberDTO.getJoinType().equals("google")) {
				return "google";
			}else if(memberDTO.getJoinType().equals("normal")) {
				return "normal";
			}
		}
		//가입된 내역이 없으면 
		return "non_exist";
	
	}

	// 회원가입
	@Override
	public void write(MemberDTO memberDTO) {
			memberDAO.write(memberDTO);

	}

	// 메일발송
	@Override
	public void send_mail(MemberDTO memberDTO) {
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com";
		String hostSMTPid = "mohagosimri@gmail.com";
		String hostSMTPpwd = "bitcamp199";

		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "mohagosimri@gmail.com";
		String fromName = "뭐하고 심리?";
		String subject = "";
		String msg = "";

		subject = "뭐하고 심리? 임시 비밀번호 입니다.";
		msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
		msg += "<h3 style='color: blue;'>";
		msg += memberDTO.getEmail() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
		msg += "<p>임시 비밀번호 : ";
		msg += memberDTO.getPwd() + "</p></div>";

		// 받는 사람 E-Mail 주소
		String mail = memberDTO.getEmail();
		try {
			HtmlEmail email = new HtmlEmail();
			// HtmlEmail email = new HtmlEmail();
			email.setDebug(false);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(465);

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
	}

	// 비번변경
	@Override
	public String getPwd(String email, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");

		MemberDTO memberDTO = memberDAO.getMemberDTO(email);
		if (memberDTO == null) {
			return "non_exist";
		} else {
			// 임시비밀번호 생성
			String pwd = "";
			for (int i = 0; i < 5; i++) {
				pwd += (char) ((Math.random() * 26) + 97);
			}
			memberDTO.setPwd(pwd);
			// 비밀번호 변경
			memberDAO.find_pwd(memberDTO);
			// 비밀번호 변경 메일 발송
			send_mail(memberDTO);
		}
		return "exist";
	}

	   @Override
	   public String socialLogin(String email) {
	      MemberDTO memberDTO = memberDAO.socialLogin(email);
	      
	      if (memberDTO == null) {
	         return "fail";
	      }
	      
	      session.setAttribute("memId", memberDTO.getEmail());
		  session.setAttribute("memNickname", memberDTO.getNickname());
		  session.setAttribute("memPalette", memberDTO.getPalette());

	      return "success";
	   }

	   @Override
	   public void socialJoin(String email, String nickname, String profile, String joinType) {
	      MemberDTO memberDTO = new MemberDTO();
	      memberDTO.setName(nickname);
	      memberDTO.setEmail(email);
	      memberDTO.setNickname(nickname);
	      memberDTO.setProfile(profile);
	      memberDTO.setPwd("simriPwd");
	      memberDTO.setPalette("white");
	      memberDTO.setJoinType(joinType);
	      memberDAO.socialWrite(memberDTO);
	   }

	@Override
	public MemberDTO getMemberDTO(String email) {
		return memberDAO.getMemberDTO(email);
	}

	@Override
	public String createRandomNickname(String email) {
		memberDAO.nickChangePlus(email);
		
		String random1[] = {"귀여운","무서운","멋있는","예쁜","아름다운","착한","용맹한","영리한","빠른"};
		String random2[] = {"호랑이","사자","비둘기","독수리","코끼리","물개","토끼","알맹이","펭귄","원숭이","강아지","악어","다람쥐","기린","치타"};

		int num1 = (int) (Math.random() * (random1.length+1));
		int num2 = (int) (Math.random() * (random2.length+1));
		String randomNickname = random1[num1] + random2[num2];
		return randomNickname;
		
		
	}

	@Override
	public String confirmEmail(String email) {
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com";
		String hostSMTPid = "mohagosimri@gmail.com";
		String hostSMTPpwd = "bitcamp199";

		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "mohagosimri@gmail.com";
		String fromName = "뭐하고 심리?";
		String subject = "";
		String msg = "";
		
		String random1[] = {"귀여운","무서운","멋있는","예쁜","아름다운","착한","용맹한","영리한","빠른"};
		String random2[] = {"호랑이","사자","비둘기","독수리","코끼리","물개","토끼","알맹이","펭귄","원숭이","강아지","악어","다람쥐","기린","치타"};

		int num1 = (int) (Math.random() * (random1.length));
		int num2 = (int) (Math.random() * (random2.length));
		
		String confirmEmail = random1[num1] + random2[num2];
		
		subject = "뭐하고 심리? 이메일 인증문자 입니다.";
		msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
		msg += "<h3 style='color: blue;'>";
		msg +="뭐하고 심리? 이메일 인증문자 입니다. 아래의 문자를 입력해 주세요.</h3>";
		msg += "<p>이메일 인증 문자 : ";
		msg += confirmEmail+ "</p></div>";
		// 받는 사람 E-Mail 주소
		String mail = email;
		try {
			HtmlEmail HtmlEmail = new HtmlEmail();
			// HtmlEmail email = new HtmlEmail();
			HtmlEmail.setDebug(false);
			HtmlEmail.setCharset(charSet);
			HtmlEmail.setSSL(true);
			HtmlEmail.setHostName(hostSMTP);
			HtmlEmail.setSmtpPort(465);

			HtmlEmail.setAuthentication(hostSMTPid, hostSMTPpwd);
			HtmlEmail.setTLS(true);
			HtmlEmail.addTo(mail, charSet);
			HtmlEmail.setFrom(fromEmail, fromName, charSet);
			HtmlEmail.setSubject(subject);
			HtmlEmail.setHtmlMsg(msg);
			HtmlEmail.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
		return confirmEmail;
	}

	//정지회원체크
	@Override
	public MemberDTO conditionCheck(String email) {
		MemberDTO stopMemberDTO = memberDAO.conditionCheck(email);
		//정지회원이면
		if(stopMemberDTO!=null) {
		
			return stopMemberDTO;
		}
		//일반회원이면
		return null;
	}

	


}
