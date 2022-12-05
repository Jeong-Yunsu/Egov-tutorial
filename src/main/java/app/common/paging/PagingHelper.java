package app.common.paging;

import app.common.vo.SearchVO;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public class PagingHelper {
    public static PaginationInfo getDefaultPaginationInfo(SearchVO comDefaultVO) {
        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(comDefaultVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(comDefaultVO.getRecordCountPerPage());
        paginationInfo.setPageSize(comDefaultVO.getPageSize());

        comDefaultVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        comDefaultVO.setLastIndex(paginationInfo.getLastRecordIndex());
        comDefaultVO.setPageSize(paginationInfo.getPageSize());

        return paginationInfo;
    }

    public static BasePaginationInfo getPaginationInfo(SearchVO comDefaultVO, String path) {
        BasePaginationInfo paginationInfo = new BasePaginationInfo();

        paginationInfo.setCurrentPageNo(comDefaultVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(comDefaultVO.getRecordCountPerPage());
        paginationInfo.setPageSize(comDefaultVO.getPageSize());

        comDefaultVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        comDefaultVO.setLastIndex(paginationInfo.getLastRecordIndex());
        comDefaultVO.setPageSize(paginationInfo.getPageSize());

        StringBuilder url = new StringBuilder();
        url.append("'").append(path).append("'");

        paginationInfo.setUrl(url.toString());
        return paginationInfo;
    }
}
