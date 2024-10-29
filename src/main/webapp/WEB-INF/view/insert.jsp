<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<title>insert staff</title>
</head>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>

<body>
	<div class="container">
		<div class="row mt-5 align-items-center">
			<h1 class="col-4 m-3">従業員の追加</h1>
			<p class="col-2 ms-auto me-0 my-3">
				<a href="/ShiftMgmtPro2/home/owner/owner"
					class="btn btn-outline-primary w-100">戻る</a>
			</p>
		</div>
		<div class="outline">
			<div class="row">
				<c:if test="${!empty error}">
					<p class="alert alert-danger">
						<c:out value="${error }" />
					</p>
				</c:if>
				<c:if test="${!empty msg}">
					<p class="alert alert-danger text-center">
						<c:out value="${msg }" />
					</p>
				</c:if>
				<form action="insert" method="post" class="form-frizontal">
					<div class="form-group row mb-3">
						<label class="col-2 col-form-label" for="name">氏名</label>
						<div class="col-10">
							<input class="form-control" type="text" id="name" name="name"
								value="${addmember.name}" required>
						</div>
					</div>
					<div class="form-group row mb-3">
						<label class="col-2 col-form-label" for="id">ログインID</label>
						<div class="col-10">
							<input class="form-control" type="text" id="id" name="addloginId"
								value="${addmember.loginId}" required>
						</div>
					</div>
					<div class="form-group row mb-3">
						<label class="col-2 col-form-label" for="email">e-mail</label>
						<div class="col-10">
							<input class="form-control" type="email" name="email"
								value="${addmember.email}" required id="email">
						</div>
					</div>
					<c:if test="${!empty passerror}">
						<p class="alert alert-danger text-center">パスワードが一致しません</p>
					</c:if>
					<div class="form-group row mb-3">
						<label class="col-2 col-form-label" for="pass">Pass</label>
						<div class="col-10">
							<input class="form-control" type="password" name="pass" required
								id="pass">

						</div>
					</div>
					<div class="form-group row mb-3">
						<label class="col-2 col-form-label" for="confirmpass">確認:Pass</label>
						<div class="col-10">
							<input class="form-control" type="password" name="confirmPass"
								required id="confirmpass">
						</div>
					</div>
					<div class="form-group row mb-3">
						<label class="col-2 col-form-label" for="tel">TEL</label>
						<div class="col-10">
							<input class="form-control" type="text" name="tel"
								value="${addmember.tel }" required id="tel">
						</div>
					</div>
					<div class="form-group row mb-3">
						<label class="col-2 col-form-label" for="statusId">雇用形態</label>
						<div class="col-10">
							<select name="statusId" id="statusId" class="form-select">
								<option value="1">管理者</option>
								<option value="2">社員</option>
								<option value="3">アルバイト</option>
							</select>
						</div>
					</div>
					<div class="d-flex justify-content-center">
						<div class="col-3">
							<input type="submit" value="確認" class="btn btn-primary w-100">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
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
</html>
