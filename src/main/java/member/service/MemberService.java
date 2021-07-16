package member.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.bean.MemberDTO;

public interface MemberService {

	public String login(Map<String, String> map, HttpSession session);

	public String checkId(String email);

	public void write(MemberDTO memberDTO);

	public String getPwd(String email, HttpServletResponse response);

	public void send_mail(MemberDTO memberDTO);

	public String socialLogin(String email);

	public void socialJoin(String email, String nickname, String profile, String joinType);

	public MemberDTO getMemberDTO(String id);

	public String createRandomNickname(String email);

	public String confirmEmail(String email);

	public MemberDTO conditionCheck(String email);

	


}
