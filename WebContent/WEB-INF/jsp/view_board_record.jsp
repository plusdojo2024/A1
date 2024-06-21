<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- ↑の記述がないとfor Eachは使えないよ！！ -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
過去の板書を詳細表示するよ。<br>


	<!-- 結果 -->
	<c:forEach var="bb" items="${blackBoardList}" >
		中身：${bb.boardContents}<br>
		日付：${bb.blackBoardDatetime}
	<hr>
</c:forEach>



</body>
</html>