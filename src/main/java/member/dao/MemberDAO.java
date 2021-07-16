package member.dao;

import java.util.Map;

import member.bean.ExitMemberDTO;
import member.bean.MemberDTO;

public interface MemberDAO {

	public MemberDTO login(Map<String,String> map);
	
	public MemberDTO getMemberDTO(String email);

	public void write(MemberDTO memberDTO);

	public void find_pwd(MemberDTO memberDTO);

	public MemberDTO socialLogin(String email);

	public void socialWrite(MemberDTO memberDTO);

	public void nickChangePlus(String email);

	public ExitMemberDTO checkExitId(String id);

	public MemberDTO conditionCheck(String email);

	public void todayWriteCountReset(String email);
}
