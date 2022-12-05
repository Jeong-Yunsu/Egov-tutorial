package app.notice.repository;

import java.util.List;

import app.notice.vo.NoticeVO;

public interface NoticeRepository {
	void insertNotice(NoticeVO noticeVO);
	NoticeVO selectNotice(int ntcSn);
	List<NoticeVO> selectNoticeList(NoticeVO noticeVO);
	int selectNoticeListCnt(NoticeVO noticeVO);
	List<NoticeVO> selectNoticeListTopFix(NoticeVO noticeVO);
	
}
