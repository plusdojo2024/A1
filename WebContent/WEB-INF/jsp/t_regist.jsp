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
    <div class="box">
        <div class="content">
            <h2 class="login">くらすぼーど</h2>
        </div>
<form id="login_form" method="post" action="/A1/LoginServlet" class="form">
    
        <label>名前<br>
        <input type="text" name="id">
        </label><br>
        
        <label>メールアドレス<br>
        <input type="text" name="mail">
        </label><br>

        <label>Pass word<br>
        <input type="password" name="pw">
        </label><br>
        
            <input type="hidden" name="jobflag" value="0">
        
        <input type="submit" name="submit" value="log in">
        <input type="reset" name="reset" value="reset"><br>
        <span id="error_message">

        </span>
  </form>
    </div>
</body>
</html>