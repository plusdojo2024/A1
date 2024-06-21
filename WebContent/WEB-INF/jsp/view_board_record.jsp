<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
過去の板書を詳細表示するよ。


	<!-- 結果 -->
	<c:forEach var="e" items="${list}" >
		${boardContents}<br>
	<hr>
</c:forEach>




</body>
</html>