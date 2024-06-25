<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- ↑の記述がないとfor Eachは使えないよ！！ -->

<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<title>履歴閲覧|<c:forEach var="bb" items="${blackBoardList}" >
		${bb.blackBoardDatetime}
		</c:forEach>
</title>
<link rel="stylesheet" href="css/view-board-record.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<header>


	<!-- 結果 -->


                    <div class="contents" id="contents">
                        <!-- ここにテキストエリアの内容が表示されます -->
                    </div>
		<a class="pagechange" href="/A1/MainServlet"><span>板書</span><span>ページへ</span></a>
        		<h1 class= "title">板書履歴</h1>

		<div  class = "date">

		<c:forEach var="bb" items="${blackBoardList}" >
		${bb.blackBoardDatetime}
		</c:forEach>

		</div >




        <!-- <a href="/A1/MainServlet">
    		<div class="change">
                <p class="center">板書</p>
                </div>


        </a> -->

    </header>

    <div class="board">
	<c:forEach var="bb" items="${blackBoardList}" >
		${bb.boardContents}<br>
</c:forEach>
</div>
</body>

	<footer>
	<div class="btn">
		<button class="backbtn" onclick="history.back()">戻る</button>
	</div>

 </footer class>


</html>