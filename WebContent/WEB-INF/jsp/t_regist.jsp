<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/s-regist.css">
</head>
<body>
<h1 id="logo"><img src="img/くらすぼーどロゴ.png" alt="くらすぼーど" width="350"></h1>
       <h2 class="regist">新規登録（講師）</h2>
    <div class="box">
        <div class="content">

        </div>
<form id="login_form" method="post" action="/A1/TUserRegistServlet" class="form">

        <label>名前
        <input  class="text" type="text" name="id" placeholder="山田太郎">
        </label>

        <label>メールアドレス
        <input  class="text" type="text" name="mail" placeholder="メールアドレス">
        </label>

        <label>パスワード
        <input  class="text" type="password" name="pw" placeholder="8∼16桁の英数字混合で設定してください">
        </label>
        <input class="textsub" type="submit" name="submit" value="新規登録">
        <span id="error_message">
            <input type="hidden" name="jobflag" value="0">
        </span>
  </form>
  <% if (request.getAttribute("error") != null) { %>
                <%= request.getAttribute("error") %>
            <% } %>
    </div>
</body>
</html>