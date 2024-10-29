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
<title>insert shift</title>
</head>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<style>
.outline {
	border: 2px solid #ffffff;
	border-radius: 15px;
	padding: 20px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
	margin: 20px auto;
	width: 50%;
}
</style>
<body>
	<div class="container">
		<div class="row mt-5 align-items-center">
			<h1 class="col-4 m-3">シフト編集</h1>
			<p class="col-2 ms-auto me-0 my-3">
				<a href="/ShiftMgmtPro2/home/owner/owner"
					class="btn btn-outline-primary w-100">戻る</a>
			</p>
		</div>
		<div class="outline">
			<div class="row">
				<div class="m-4 mx-auto w-75">
					<c:if test="${!empty error}">
						<p class="alert alert-danger w-75　m-2 text-center p-2">
							<c:out value="${error}" />
						</p>
					</c:if>
					<c:if test="${!empty msg}">
						<p class="alert alert-primary w-75　m-2 text-center p-2">
							<c:out value="${msg}" />
						</p>
					</c:if>
					<form action="" method="post">
						<div class="m-2">
							<select name="loginId" class="form-select">
								<!-- 従業員を増やしても選択できるように一覧からリストを持ってくる -->
								<c:forEach items="${nameIdMap}" var="map">
									<option value="${map.key}">${map.value}</option>
								</c:forEach>
							</select>
						</div>
						<div class="m-2">
							<input type="date" name="date" class="form-control">
						</div>
						<div class="m-2">
							<select name="shift" id="" class="form-select">
								<option value="1">早</option>
								<option value="2">遅</option>
								<option value="3">中</option>
								<option value="4">フル</option>
								<option value="5">休み</option>
							</select>
						</div>
						<div class="m-2 col-4 mx-auto">
							<input type="submit" value="確定"
								class="form-control btn btn-primary">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>