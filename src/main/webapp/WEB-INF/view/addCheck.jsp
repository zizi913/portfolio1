<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<title>addCheck</title>
</head>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>

<body>
	<div class="container">
		<div class="row mt-5 align-items-center">
			<h1 class="col-4 m-3">確認</h1>
			<p class="col-2 ms-auto me-0 my-3">
				<a href="/ShiftMgmtPro2/home/owner/owner"
					class="btn btn-outline-primary w-100">戻る</a>
			</p>
		</div>
		<div class="outline">
			<div class="row">
				<div class="form-group row mb-3">
					<label class="col-2" for="name">氏名</label>
					<div class="col-10">
						<p>
							<c:out value="${addmember.name }" />
						</p>
					</div>
				</div>
				<div class="form-group row mb-3">
					<label class="col-2" for="id">ログインID</label>
					<div class="col-10">
						<p>

							<c:out value="${addmember.loginId }" />
						</p>
					</div>
				</div>
				<div class="form-group row mb-3">
					<label class="col-2 col-form-label" for="email">e-mail</label>
					<div class="col-10">
						<p>

							<c:out value="${addmember.email }" />
						</p>
					</div>
				</div>

				<div class="form-group row mb-3">
					<label class="col-2 col-form-label" for="pass">Pass</label>
					<div class="col-10">
						<p>

							<c:forEach var="i" begin="1" end="${fn:length(pass)}">
        &#8226;
        </c:forEach>
						</p>
					</div>
				</div>
			</div>
			<div class="form-group row mb-3">
				<label class="col-2 col-form-label" for="tel">TEL</label>
				<div class="col-10">
					<p>

						<c:out value="${addmember.tel }" />
					</p>
				</div>
			</div>
			<div class="form-group row mb-3">
				<label class="col-2 col-form-label" for="status">雇用形態</label>
				<div class="col-10">
					<p>
						<c:out value="${addmember.status }" />
					</p>
				</div>
			</div>
			<div class="form-frizontal">
				<div class="d-flex justify-content-center">
					<div class="col-3 mx-1">
						<a href="insert" class="btn btn-outline-primary w-100">修正</a>
					</div>
						<div class="col-3 mx-1">
					<form action="addCheck" method="post">
							<input type="submit" value="追加" class="btn btn-primary w-100">
					</form>
						</div>
				</div>
			</div>
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