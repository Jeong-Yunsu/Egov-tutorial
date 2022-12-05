package app.member.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
	MemberVO loginMember;
	boolean isSuccess;
	String message;
}
