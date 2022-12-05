<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<html>
<head>
    <title>공지사항 목록</title>
</head>
<body>

<form id="frm" name="frm">
	<input type="hidden" name="pageIndex"/>
    <div>
        전체 <span>${totalRecordCount }</span>건		
        <select name="recordCountPerPage" onchange="getNoticeList(1);">
        	<option ${noticeVO.recordCountPerPage == 1 ? 'selected':''} value = "1">1개</option>
            <option ${noticeVO.recordCountPerPage == 10 ? 'selected':''} value = "10">10개</option>
            <option ${noticeVO.recordCountPerPage == 30 ? 'selected':''} value = "30">30개</option>
            <option ${noticeVO.recordCountPerPage == 50 ? 'selected':''} value = "50">50개</option>
        </select>

        <select name="searchCnd1">
            <option ${noticeVO.searchCnd1 == 0 ? 'selected':''} value="0">전체</option>
            <option ${noticeVO.searchCnd1 == 1 ? 'selected':''} value="1">제목</option>
            <option ${noticeVO.searchCnd1 == 2 ? 'selected':''} value="2">내용</option>
        </select>

        <input type="text" name="searchWrd">
        <button type="button" onclick="getNoticeList(1);">검색</button>
    </div>

    <table>
        <thead>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>등록자</th>
            <th>등록일시</th>
        </tr>
        </thead>
        <tbody>
     		<!-- topFix jstl 이것도 하나의 반복문임-->
     		<c:forEach var="topFix" items="${topFixList}">
	     		<tr>
	                <td>[고정]</td>
	                <td>${topFix.ntcTtl }</td>
	                <td>${topFix.rgtrId }</td>
	                <td>${topFix.rgtrDt }</td>
	            </tr>
     		</c:forEach>
     		
     		<!-- notice jstl -->
     		<c:forEach var="notice" items="${noticeList}" varStatus="status">
	     		<tr>
	                <td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }</td>
	                <td>${notice.ntcTtl}</td>
	                <td>${notice.rgtrId}</td>
	                <td>${notice.rgtrDt}</td>
	            </tr>
     		</c:forEach>
            

        </tbody>
    </table>

    <div>
		<ui:pagination paginationInfo="${paginationInfo}" jsFunction="getNoticeList"/>
	</div>
</form>
</body>
</html>

<script>
	function getNoticeList(pageIndex) {
		document.frm.pageIndex.value = pageIndex
		document.frm.action="/notice";
		document.frm.submit();
	}

</script>