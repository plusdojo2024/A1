<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>受講生 | 板書ページ</title>
<link rel="stylesheet" href="css/t-board-record.css">
</head>
<body>
    <a href="/A1/MainServlet">
        <div class="boardpage">板書</div>
    </a>
    <div class="tab">
        <a href="/A1/RecordServlet">
            <div class="tab1">
                板書履歴
            </div>
        </a>
        <a href="/A1/MyMarkLevelServlet">
            <div class="tab2">
                理解度履歴
            </div>
        </a>
    </div>

    <div class="board-list">
        <c:forEach var="board" items="${blackBoardList}">
            <div class="board-item">
                <a href="/A1/DisplayBoardContentServlet?boardId=${board.boardId}">
                    ${board.blackBoardDatetime}
                </a>
            </div>
        </c:forEach>
    </div>

    <a href="/A1/AllMarkSearchServlet">確認</a>

</body>
</html>