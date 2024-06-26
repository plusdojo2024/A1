<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>講師 | 板書履歴ページ</title>
<link rel="stylesheet" href="/A1/css/t-board-record.css">
</head>
<body>
    <a class="btn" href="/A1/MainServlet"><span>板書</span><span>ページへ</span>
        <!--  <div class="boardpage">板書</div> -->
    </a>
    <div class="tab">
        <a href="/A1/RecordServlet">

            <!-- <div class="tab1">
                板書履歴
            </div> -->

        </a>
		<h1 class="title">板書履歴　選択一覧</h1>
    </div>
<div class="board-box">
    <div class="board-list">
        <c:forEach var="board" items="${blackBoardList}">
            <div class="board-item">
                <a href="/A1/RecordViewServlet?boardId=${board.boardId} ">
                    ${board.blackBoardDatetime}
                    ${board.boardId}

                </a>
            </div>
        </c:forEach>
    </div>
 </div>


</body>
</html>