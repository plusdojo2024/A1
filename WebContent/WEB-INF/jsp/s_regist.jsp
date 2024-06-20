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
<h1 id="logo"><img src="img/くらすぼーどロゴ.png" alt="くらすぼーど" width="300"></h1>
    <div class="box">
        <div class="content">
            <h2 class="login"></h2>
        </div>
<form id="login_form" method="post" action="/A1/SUserRegistServlet" class="form">

        <label>名前<br>
        <input  class="text" type="text" name="id" placeholder="山田太郎">
        </label><br>

        <label>メールアドレス<br>
        <input  class="text" type="text" name="mail" placeholder="メールアドレス">
        </label><br>

        <label>Pass word<br>
        <input  class="text" type="password" name="pw" placeholder="パスワードは8∼16桁の英数字混合で設定してください">
        </label><br>
        <input type="submit" name="submit" value="log in">
        <input type="reset" name="reset" value="reset"><br>
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