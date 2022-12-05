<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
	<title>공지사항 등록</title>
	
	<!-- jQuery CDN 사용하기 위해 -->
	<script src="https://code.jquery.com/jquery-3.6.1.js"
			integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="
			crossorigin="anonymous"></script>
				
</head>


<body>
	<form action="/notice/enroll" method="post">
		<table>
			<tbody>
				<tr>
					<td>제목</td>
					<td><input type="text" id="ntcTtl" name="ntcTtl"/></td>
					<td>상단고정여부</td>
					<td>
						<label for="">예</label><input type="radio" value="Y" name="topFix">
						<label for="">아니요</label><input type="radio" value="N" name="topFix">
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea id="ntcTtl" name="ntcCn"></textarea></td>			
				</tr>
			
			</tbody>
		</table>
		
		<button type="submit">공지사항 등록</button>
		<button type="button" onclick="callApi();">공지사항 등록-AJAX</button>
	</form>
</body>
</html>

<script>
	function callApi() {
		// 1. 데이터 만들기
		var data = {
			"ntcTtl" : $('#ntcTtl').val(),
			"ntcCn" : $('#ntcCn').val(),
			"topFix" : $('input[name="topFix"]:checked').val()
		}
		
		console.log(data);
		
		// 2. $.ajax
		$.ajax({ //비동기 방식으로 요청 한다는 뜻
			type: "post",
			url: "/notice/enroll",
			data: JSON.stringify(data), // 자바스크립트에서 JSON쓰는거
			headers: {'Content-Type' : 'application/json'},
			success: function(res) {
				console.log(res);
				if(res.code == 400) {
					alert("다시 입력하시오.");
				} else if(res.code == 200) {
					// redirect
					window.location.href = "/notice/" + res.ntcSn;
				}
			}
		});
	}
</script>