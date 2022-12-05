package app.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import app.member.service.MemberService;
import app.member.vo.LoginResponse;
import app.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("/join")
	public String joinPage() {
		return "member/join";
	}
	
	@PostMapping("join")
	public String join(MemberVO memberVO) {
		
		String mbrNm = memberVO.getMbrNm();
		String mbrId = memberVO.getMbrId();
		String mbrPswd = memberVO.getMbrPswd();
		
		// 유효성 검사
		if(StringUtils.isEmpty(mbrNm) || StringUtils.isEmpty(mbrId) || StringUtils.isEmpty(mbrPswd)) {
			return "member/join";
		}
		
		//회원가입하는 join메서드 사용
		memberService.join(memberVO);
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String loginPage(HttpSession session) {
		
		if(session.getAttribute("mbrSn") != null) {
			return "redirect:/notice/enroll";
		}
		
		return "member/login";
	}
	
	@PostMapping("/login")
	public String login(MemberVO memberVO, ModelMap model, HttpSession session, HttpServletRequest request) {
		
		String mbrId = memberVO.getMbrId();
		String mbrPswd = memberVO.getMbrPswd();
		
		// 유효성 검사
		if(StringUtils.isEmpty(mbrId) || StringUtils.isEmpty(mbrPswd)) {
			model.addAttribute("message", "모든 정보를 입력해주세요");
			return "member/login";
		}
		
		//로그인하는 login매서드 사용
		LoginResponse loginResponse = memberService.login(memberVO);
		
		if (!loginResponse.isSuccess()) {
			model.addAttribute("message", loginResponse.getMessage());
			return "member/login";
		}
		
		MemberVO loginMember = loginResponse.getLoginMember();
		session.setAttribute("mbrSn", loginMember.getMbrSn());
		session.setAttribute("mbrId", loginMember.getMbrId());
		session.setAttribute("mbrNm", loginMember.getMbrNm());
		
		return "redirect:/notice";
	}

}
