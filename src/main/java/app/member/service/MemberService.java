package app.member.service;

import java.util.Map;

import app.member.vo.LoginResponse;
import app.member.vo.MemberVO;

public interface MemberService {
	
	void join(MemberVO memberVO);
	
	LoginResponse login(MemberVO memberVO);
}
