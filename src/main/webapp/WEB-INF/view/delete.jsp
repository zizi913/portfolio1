<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<title>delete</title>
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

/* * {
	outline: 1px solid blue;
} */
</style>
<body>
	<div class="container">
		<div class="row mt-5 align-items-center">
			<h1 class="col-4 m-3">従業員データ削除</h1>
			<p class="col-2 ms-auto me-0 my-3">
				<a href="/ShiftMgmtPro2/home/owner/owner"
					class="btn btn-outline-primary w-100">戻る</a>
			</p>
		</div>
		<div class="outline">
			<div>
				<c:if test="${!empty  error}">
					<p class="alert alert-danger text-center col-8 m-4 mx-auto">
						<c:out value="${error }" />
					</p>
				</c:if>
				<form action="" method="post">
					<div class="d-flex flex-column align-items-center mb-2">
						<label for="loginId" class="text-center h5">ログインID</label>
						<div class="col-10">
							<input class="form-control mx-auto" type="text" name="loginId"
								id="loginId">
						</div>
					</div>
			</div>
			<div class="col-5 mx-auto m-4">
				<input type="submit" class="btn btn-primary w-100 text-center">
			</div>
			</form>
		</div>
	</div>
	</div>
</body>
</html>