<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>
<h1 id="logo"><img src="img/くらすぼーどロゴ .png" alt="くらすぼーど" width="350"></h1>
    <h2 class="login">ログイン</h2>
    <div class="box">
        <div class="content">
        </div>
<form id="login_form" method="post" action="/A1/LoginServlet" class="form">

        <input class="text" type="text" name="mail" placeholder="メールアドレス">
        <br>

        <input class="text"type="password" name="pw" placeholder="パスワード             ">
        <br>
        <input class="textsub"type="submit" name="submit" value="ログイン"
        		>
        <input class="textres"type="reset" name="reset" value="リセット">
        <br>
		<br>
        <span id="error_message">
            <% if (request.getAttribute("error") != null) { %>
                <%= request.getAttribute("error") %>
            <% } %>
        </span>
  </form>
    </div>
</body>
</html>