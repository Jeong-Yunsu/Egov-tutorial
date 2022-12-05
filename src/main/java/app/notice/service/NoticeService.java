package app.notice.service;

import java.util.Map;

import app.notice.vo.NoticeVO;

public interface NoticeService {
	void enroll(NoticeVO noticeVO);
	
	NoticeVO read(int ntcSn);
	
	Map<String, Object> readAll(NoticeVO noticeVO);
}
