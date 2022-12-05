package app.notice.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Service;

import app.notice.repository.NoticeRepository;
import app.notice.vo.NoticeVO;
import lombok.RequiredArgsConstructor;

@Service("noticeService")
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

	private final NoticeRepository noticeRepository;
	
//	public NoticeServiceImpl(NoticeRepository noticeRepository) {
//		super();
//		this.noticeRepository = noticeRepository;
//	}


	@Override
	public void enroll(NoticeVO noticeVO) {
		
		// 1. 디폴트 값 설정
		noticeVO.setRgtrId("admin");
		noticeVO.setRgtrDt(LocalDateTime.now());
		
		if(StringUtils.isEmpty(noticeVO.getTopFix())) {
			noticeVO.setTopFix("N");	
		}
		
		// 2. DB 에 저장
		noticeRepository.insertNotice(noticeVO);
	}

	@Override
	public NoticeVO read(int ntcSn) {
		return noticeRepository.selectNotice(ntcSn);
	}

	@Override
	public Map<String, Object> readAll(NoticeVO noticeVO) {

		// 1. 페이징
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(noticeVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(noticeVO.getRecordCountPerPage());
		paginationInfo.setPageSize(noticeVO.getPageSize());
		
		noticeVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		noticeVO.setLastIndex(paginationInfo.getFirstRecordIndex());

		// 2. repository
		List<NoticeVO> noticeList = noticeRepository.selectNoticeList(noticeVO);
		int totalRecordCount = noticeRepository.selectNoticeListCnt(noticeVO);
		List<NoticeVO> topFixList  = noticeRepository.selectNoticeListTopFix(noticeVO);
		
		paginationInfo.setTotalRecordCount(totalRecordCount);
		
		// 3. 응답 데이터 생성
		Map<String, Object> result = new HashMap<>();
		result.put("noticeList", noticeList);
		result.put("totalRecordCount", totalRecordCount);
		result.put("topFixList", topFixList);
		result.put("paginationInfo", paginationInfo);
		
		return result;
	}
	
	
	
	

	
}
