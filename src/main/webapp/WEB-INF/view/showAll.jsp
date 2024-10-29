<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<title>Show All Staff</title>
</head>
<body>
<div class="container">
	<div class="row mt-5 align-items-center">
			<h1 class="col-4 m-3">従業員一覧</h1>
			<p class="col-2 ms-auto me-0">
				<a href="owner" class="btn btn-outline-primary w-100">戻る</a>
			</p>
			</div>
	<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>氏名</th>
			<th>ログインID</th>
			<th>メールアドレス</th>
			<th>電話番号</th>
			<th>雇用形態</th>
		</tr>
		</thead>
		<c:forEach items="${member}" var="m">
			<tr>
				<th scope="col"><c:out value="${m.name}" /></th>
				<td><c:out value="${m.loginId}" /></td>
				<td><c:out value="${m.email}" /></td>
				<td><c:out value="${m.tel}" /></td>
				<td><c:out value="${m.status}" /></td>
			</tr>
		</c:forEach>
	</table>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

</body>
</html>