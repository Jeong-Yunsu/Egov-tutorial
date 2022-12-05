package app.member.repository;


import org.springframework.stereotype.Repository;

import app.common.dao.SqlComAbstractDAO;
import app.member.vo.MemberVO;

@Repository
public class MemberRepositoryImpl extends SqlComAbstractDAO implements MemberRepository {

	@Override
	public void insertMember(MemberVO memberVO) {
		insert("Member.insertMember", memberVO);
	}
	
	@Override
	public MemberVO selectLoginMember(MemberVO memberVO) {
		return selectOne("Member.selectLoginMember", memberVO);
	}

	@Override
	public void updateMemberLogin(MemberVO memberVO) {
		update("Member.updateMemberLogin", memberVO);

	}
	
	
	
}
