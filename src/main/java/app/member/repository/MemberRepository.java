package app.member.repository;

import app.member.vo.MemberVO;

public interface MemberRepository {
	void insertMember(MemberVO memberVO);
	MemberVO selectLoginMember(MemberVO memberVO);
	void updateMemberLogin(MemberVO memberVO);
}
