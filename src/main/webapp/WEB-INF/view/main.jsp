<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>main</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<style>
/* * {
	outline: 1px solid blue;
}
 */
.outline {
	border: 2px solid #ffffff;
	border-radius: 15px;
	padding: 20px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
	margin: 20px auto;
	width: 50%;
}

li {
	text-decoration: none; /* 下線を消す */
	list-style-type: none; /* まるぽちを消す */
}

a {
	text-decoration: none;
	color: #007bff;
}
</style>

<body>
	<div class="container">
		<div class="row justify-content-center mt-5">
			<div class="d-flex align-items-center">
				<div class="m-3">
					<h1>シフト</h1>
				</div>
				<ul
					class="d-flex justify-content-end list-unstyled me-1 ms-auto pull-right">
					<li class="btn btn-outline-primary me-2"><a href="thread">掲示板</a></li>
					<li class="btn btn-outline-primary me-2"><a href="contact">コンタクト</a></li>
					<li class="btn btn-outline-primary me-2"><a href="myPage">マイページ</a></li>
					<li class="btn btn-outline-primary"><a href="logout">ログアウト</a></li>
				</ul>
			</div>
		</div>
		<form action="main" method="post">
			<p>
				<input type="date" name="start" id="today">～<input
					type="date" name="end" id="end"> <input type="submit"
					value="表示">
			</p>
		</form>
		<!-- 以下にシフトカレンダー表示 -->
		<table border="1" class="table table-striped table-hover p-2 m-2">
			<thead>
				<tr>
					<th>日付</th>
					<c:forEach items="${shift}" var="s">
						<th><fmt:formatDate value="${s.date}" pattern="MM/dd" /></th>
					</c:forEach>
				</tr>
			</thead>
			<tr>
				<th>シフト</th>
				<c:forEach items="${shift}" var="s">
					<td><c:out value="${s.strShift}" /></td>
				</c:forEach>
			</tr>
			<tr>
				<th>出勤時刻</th>
				<c:forEach items="${shift}" var="s">
					<td><fmt:formatDate value="${s.checkin}" pattern="HH時mm分ss秒" /></td>
				</c:forEach>
			</tr>
			<tr>
				<th>退勤時刻</th>
				<c:forEach items="${shift}" var="s">
					<td><fmt:formatDate value="${s.checkout}" pattern="HH時mm分ss秒" /></td>
				</c:forEach>
			</tr>
		</table>
		<c:if test="${!empty check}">
			<p class="text-center alert alert-danger w-25 m-auto">
				<c:out value="${check }" />
			</p>
		</c:if>
		<div class="row d-flex justify-content-center mt-3">
			<div class="d-flex justify-content-center">
				<form action="checkin" method="get" class="me-3 col-4">
					<input type="submit" name="checkin" value="出勤"
						class="btn btn-primary btn-lg w-100">
				</form>
				<form action="checkout" method="get" class="col-4">
					<input type="submit" name="checkout" value="退勤"
						class="btn btn-primary btn-lg w-100">
				</form>
			</div>
		</div>
	</div>
</body>

<script>
	window.onload = function() {
		// 今日の日時を表示
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var day = date.getDate();

		var toTwoDigits = function(num) {
			return ('0' + num).slice(-2);
		};

		// 今日の日付を yyyy-MM-dd 形式にフォーマット
		var yyyy = year;
		var mm = toTwoDigits(month);
		var dd = toTwoDigits(day);
		var ymd = yyyy + "-" + mm + "-" + dd;

		// 今日の日付を設定
		document.getElementById("today").value = ymd;

		// 1週間後の日付を計算
		date.setDate(date.getDate() + 7);

		// 1週間後の日付を yyyy-MM-dd 形式にフォーマット
		var yearLater = date.getFullYear();
		var monthLater = toTwoDigits(date.getMonth() + 1);
		var dayLater = toTwoDigits(date.getDate());

		// フォーマットされた日付を設定
		var output = yearLater + '-' + monthLater + '-' + dayLater;

		// 1週間後の日付を設定
		document.getElementById('end').value = output;
	};
</script>
</html>