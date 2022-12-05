package app.notice.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import app.common.dao.SqlComAbstractDAO;
import app.notice.vo.NoticeVO;

@Repository
public class NoticeRepositoryImpl extends SqlComAbstractDAO implements NoticeRepository {

	@Override
	public void insertNotice(NoticeVO noticeVO) {
		insert("Notice.insertNotice", noticeVO);
	}

	@Override
	public NoticeVO selectNotice(int ntcSn) {
		return selectOne("Notice.selectNotice", ntcSn);
	}

	@Override
	public List<NoticeVO> selectNoticeList(NoticeVO noticeVO) {
		return selectList("Notice.selectNoticeList", noticeVO);
	}

	@Override
	public int selectNoticeListCnt(NoticeVO noticeVO) {
		return selectOne("Notice.selectNoticeListCnt", noticeVO);
	}

	@Override
	public List<NoticeVO> selectNoticeListTopFix(NoticeVO noticeVO) {
		return selectList("Notice.selectNoticeListTopFix", noticeVO);
	}

	
	
}
