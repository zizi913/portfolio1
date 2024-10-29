<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>従業員データ削除</title>
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
			<h1 class="col-4 m-3">データ削除の確認</h1>
			<p class="col-2 ms-auto me-0 my-3">
				<a href="/ShiftMgmtPro2/home/owner/owner"
					class="btn btn-outline-primary w-100">戻る</a>
			</p>
		</div>
		<div class="outline">
			<div class="row">
				<p class="col-8 text-center alert alert-danger p-3 m-3 mx-auto h5">
					以下の従業員のデータを削除します<br>この動作の取り消しはできません
				</p>
				<p>
					<span class="col-2 d-inline-block">氏名 :</span> <span
						class="col-8 offset-1 d-inline-block"><c:out
							value="${member.name }" /></span>
				</p>

				<p>
					<span class="col-2 d-inline-block ">ログインID :</span> <span
						class="col-8 offset-1 d-inline-block"><c:out
							value="${member.loginId }" /></span>
				</p>
				<p>
					<span class="col-2 d-inline-block">e-mail :</span> <span
						class="col-8 offset-1 d-inline-block"><c:out
							value="${member.email }" /></span>
				</p>
				<p>
					<span class="col-2 d-inline-block">TEL :</span> <span
						class="col-8 offset-1 d-inline-block"><c:out
							value="${member.tel }" /></span>
				</p>
				<p>
					<span class="col-2 d-inline-block">雇用形態 :</span> <span
						class="col-8 offset-1 d-inline-block"><c:out
							value="${member.status }" /></span>
				</p>
				<div class="d-flex justify-content-center">
					<div class="col-3 m-2">
						<form action="deleteDone" method="post">
							<input type="submit" value="削除" class="btn btn-primary w-100">
						</form>
					</div>
					<div class="col-3 m-2">
						<p>
							<a href="owner" class="btn btn-outline-primary w-100">キャンセル</a>
						</p>
					</div>
				</div>

			</div>
		</div>
	</div>

</body>
</html>