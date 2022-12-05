<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>

<form action="/join" method="post">

  이름 : <input type="text" name="mbrNm"/>
  ID : <input type="text" name="mbrId"/>
  비밀번호 : <input type="password" name="mbrPswd"/>

  <button type="submit">등록</button>

</form>
</body>
</html>