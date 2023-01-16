<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>BookStore::modify</title>
	</head>
<body>
	<h3>고객수정</h3>
	<a href="/BookStore">처음으로</a>
	<a href="/BookStore/customer/list">고객목록</a>
	
	<form action="/BookStore/customer/modify" method="post">
		<table border="1">
			<tr>
				<td>고객번호</td>
				<td><input type="number" name="custId" value="${customer.custId}" readonly="readonly"></td>
			</tr>
			<tr>
				<td>고객명</td>
				<td><input type="text" name="name" value="${customer.name}"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" name="address" value="${customer.address}"></td>
			</tr>
			<tr>
				<td>휴대폰</td>
				<td><input type="text" name="phone" value="${customer.phone}"></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="수정"></td>
			</tr>
		</table>
	</form>	
</body>
</html>