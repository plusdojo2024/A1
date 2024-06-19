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
    <div class="box">
        <div class="content">
            <h2 class="login">くらすぼーど</h2>
        </div>
<form id="login_form" method="post" action="/A1/LoginServlet" class="form">

        <label>メールアドレス<br>
        <input type="text" name="mail">
        </label><br>

        <label>パスワード<br>
        <input type="password" name="pw">
        </label><br>

        <input type="submit" name="submit" value="ログイン">
        <input type="reset" name="reset" value="リセット"><br>
        <span id="error_message">
            <% if (request.getAttribute("error") != null) { %>
                <%= request.getAttribute("error") %>
            <% } %>
        </span>
  </form>
    </div>
</body>
</html>