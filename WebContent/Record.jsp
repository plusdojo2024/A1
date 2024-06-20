<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:forEach var="e" items="${blackBoardlist}">
				<div class="resultbody">
				<div class="resulttext">
						<form id="resultform" class="h-adr" method="post"
							action="/simpleBC/UpdateDeleteServlet">
							<span class="p-country-name" style="display: none;">Japan</span>
							<table class="結果table" id="管理table">
								<tr>
									<td><label class="resulttext">番号<br> <input
											type="text" name="number" class="imputtext"
											value="${e.number}">
									</label></td>
									<td><label class="resulttext">氏名（※必須）<br> <input
											type="text" name="name" class="imputtext" value="${e.name}">
									</label></td>
									<td><label class="resulttext">ふりがな<br> <input
											type="text" name="huri" class="imputtext" value="${e.huri}">
									</label></td>
								</tr>
								<tr>
									<td><label class="resulttext">郵便番号<br> <input
											type="text" name="zipcode" class="p-postal-code"
											value="${e.zipcode}">
									</label></td>
									<td><label class="resulttext">住所<br> <input
											type="text" name="address" id="imputtext"
											class="p-region p-locality p-street-address p-extended-address"
											value="${e.address}">
									</label></td>
								</tr>
								<tr>
									<td><label class="resulttext" class="resulttext">会社名<br>
											<input type="text" name="company" class="imputtext"
											value="${e.company}">
									</label></td>
									<td><label class="resulttext">部署名<br> <input
											type="text" name="department" class="imputtext"
											value="${e.department}">
									</label>
									<td><label class="resulttext">役職名<br> <input
											type="text" name="position" class="imputtext"
											value="${e.position}">
									</label></td>
								</tr>
								<tr>
									<td><label class="resulttext">電話番号<br> <input type="text"
											name="phone" class="imputtext" value="${e.phone}">
									</label></td>
									<td><label class="resulttext">e-mail<br> <input type="text"
											name="email" class="imputtext" value="${e.email}">
									</label></td>
									<td class="resulttext"><input type="submit" name="submit" value="更新" onclick="return confirm('本当に更新しますか？')">
										<input type="submit" name="submit" value="削除" onclick="return confirm('本当に削除しますか？')"></td>
								</tr>
							</table>
						</form>
					</div>
				</div>
	${boardDate}
	</c:forEach>

</body>
</html>