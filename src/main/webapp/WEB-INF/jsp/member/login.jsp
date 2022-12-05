<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
    <span style="color: red;">${message}</span>
    
    <form action="/login" method="post">
        <input type="text" name="mbrId" value="${memberVO.mbrId}">
        <input type="password" name="mbrPswd" value="${memberVO.mbrPswd}">

        <button type="submit">로그인</button>
        <button type="button" onclick="goJoinPage()">회원가입</button>
    </form>
    
</body>
</html>


<script>
	function goJoinPage() {
		window.location.href = "/join"
	}
</script>