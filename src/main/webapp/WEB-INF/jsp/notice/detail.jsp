<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
    <title>공지사항 상세</title>
</head>
<body>
<table>
    <tbody>
    <tr>
        <td>제목</td>
        <td>${noticeVO.ntcTtl}</td>
        <td>상단고정여부</td>
        <td>${noticeVO.topFix}</td>
    </tr>

    <tr>
        <td>작성자</td>
        <td>${noticeVO.rgtrId}</td>
        <td>작성일</td>
        <td>${noticeVO.rgtrDt}</td>
    </tr>


    <tr>
        <td>내용</td>
        <td>${noticeVO.ntcCn}</td>
    </tr>
    </tbody>
</table>

</body>
</html>