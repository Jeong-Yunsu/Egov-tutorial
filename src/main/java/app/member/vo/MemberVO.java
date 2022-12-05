package app.member.vo;

import java.time.LocalDateTime;

import app.common.vo.SearchVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO extends SearchVO {
	private int mbrSn;
	private String mbrNm;
	private String mbrId;
	private String mbrPswd;
	private int failCnt;
	private String lockYn;
	private String rgtrId;
	private LocalDateTime rgtrDt;
	private String mdfrId;
	private LocalDateTime mdfrDt;
}
