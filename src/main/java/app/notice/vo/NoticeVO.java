package app.notice.vo;

import java.time.LocalDateTime;

import app.common.vo.SearchVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeVO extends SearchVO {
	private int ntcSn;
	private String ntcTtl;
	private String ntcCn;
	private String topFix;
	private String rgtrId;
	private LocalDateTime rgtrDt;
	private String mdfrId;
	private LocalDateTime mdfrDt;
}
