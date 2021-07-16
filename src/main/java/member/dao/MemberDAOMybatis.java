package member.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import member.bean.ExitMemberDTO;
import member.bean.MemberDTO;

@Transactional
@Repository
public class MemberDAOMybatis implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;
	
	//로그인
	@Override
	public MemberDTO login(Map<String, String> map) {
		MemberDTO memberDTO = sqlSession.selectOne("memberSQL.login", map);
		return memberDTO;
	}

	
	@Override
	public MemberDTO getMemberDTO(String email) {
		MemberDTO memberDTO = null;
		memberDTO = sqlSession.selectOne("memberSQL.getMemberDTO", email);
		if(memberDTO == null) {
			return memberDTO;
		}
		return memberDTO;
	}

	
	@Override//횐가입
	public void write(MemberDTO memberDTO) {
		sqlSession.insert("memberSQL.write",memberDTO);
	}


	@Override //비번변경
	public void find_pwd(MemberDTO memberDTO) {
		sqlSession.update("memberSQL.update_pwd",memberDTO);
	}


	  @Override
	   public MemberDTO socialLogin(String email) {
	      MemberDTO memberDTO = sqlSession.selectOne("memberSQL.socialLogin", email);
	      return memberDTO;
	   }


	@Override
	public void socialWrite(MemberDTO memberDTO) {
		sqlSession.insert("memberSQL.socialWrite",memberDTO);
		
	}


	@Override
	public void nickChangePlus(String email) {
		sqlSession.update("memberSQL.nickChangePlus",email);
		
	}


	@Override
	public ExitMemberDTO checkExitId(String id) {
		return sqlSession.selectOne("memberSQL.checkExitId", id);
		
		 
	}


	@Override
	public MemberDTO conditionCheck(String email) {
		return sqlSession.selectOne("memberSQL.conditionCheck",email);

	}


	@Override
	public void todayWriteCountReset(String email) {
		sqlSession.update("memberSQL.todayWriteCountReset", email);
		
	}
}
