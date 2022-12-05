package app.member.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Service;

import app.member.repository.MemberRepository;
import app.member.vo.LoginResponse;
import app.member.vo.MemberVO;
import app.notice.repository.NoticeRepository;
import app.notice.vo.NoticeVO;
import lombok.RequiredArgsConstructor;

@Service("memberService")
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	@Override
	public void join(MemberVO memberVO) {
		String mbrId = memberVO.getMbrId();
		String mbrPswd = memberVO.getMbrPswd();
		
		//단방향 암호화 시키기
		String enc = encryptPassword(mbrPswd, mbrId);
		memberVO.setMbrPswd(enc);
		
		memberVO.setRgtrId(mbrId);
		memberVO.setRgtrDt(LocalDateTime.now());
		
		memberRepository.insertMember(memberVO);
		
	}
	
	@Override
	public LoginResponse login(MemberVO memberVO) {
		
		MemberVO loginMember = memberRepository.selectLoginMember(memberVO);
		
		LoginResponse loginResponse = new LoginResponse();
		
		// 회원정보 없는 경우
		if(loginMember == null) {
			loginResponse.setSuccess(false);
			loginResponse.setMessage("등록된 회원 정보가 없습니다.");
			return loginResponse;
			
		}
		// 잠금 처리된 회원인 경우
		if(loginMember.getLockYn().equals("Y")) {
			loginResponse.setSuccess(false);
			loginResponse.setMessage("잠금 처리된 계정입니다.");
			return loginResponse;
		}
		// 비밀번호가 틀린 경우
		if (!comparePassword(memberVO.getMbrPswd(), loginMember.getMbrPswd(), memberVO.getMbrId())) {
			int failCnt = loginMember.getFailCnt();
			loginMember.setFailCnt(failCnt + 1);
			
			if(loginMember.getFailCnt() >= 5) {
				loginMember.setLockYn("Y");
			}

			memberRepository.updateMemberLogin(loginMember);
			
			loginResponse.setSuccess(false);
			loginResponse.setMessage("올바르지 않은 정보입니다.");
			
			return loginResponse;
			
		}
		
		// 로그인 성공
		loginMember.setFailCnt(0);
		loginMember.setLockYn("N");
		memberRepository.updateMemberLogin(loginMember);
		
		loginResponse.setLoginMember(loginMember);
		loginResponse.setSuccess(true);
		
		return loginResponse;
	}
	
	
	/**
     * 비밀번호를 암호화하는 기능(복호화가 되면 안되므로 SHA-256 인코딩 방식 적용)
     *
     * @param password 암호화될 패스워드
     * @param id salt로 사용될 사용자 ID 지정
     * @return
     */
    public static String encryptPassword(String password, String id) {

        if (password == null) return "";
        if (id == null) return ""; // KISA 보안약점 조치 (2018-12-11, 신용호)

        byte[] hashValue = null; // 해쉬값

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        md.reset();
        md.update(id.getBytes());

        hashValue = md.digest(password.getBytes());

        return new String(Base64.encodeBase64(hashValue));
    }

    /**
     * 비밀번호를 비교하는 기능
     *
     * @param password 사용자가 입력한 패스워드
     * @param encryptPassword DB 에 저장된 암호화된 패스워트
     * @param id salt로 사용될 사용자 ID 지정
     * @return
     */
    public static boolean comparePassword(String password, String encryptPassword, String id) {
        return encryptPassword(password, id).equals(encryptPassword);
    }

	
}
