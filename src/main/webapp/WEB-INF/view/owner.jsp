<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<title>owner</title>
</head>
<style>
.outline {
	border: 2px solid #ffffff;
	border-radius: 15px;
	padding: 20px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
	margin: 20px auto;
	width: 50%;
}

a {
	text-decoration: none;
}

li {
	list-style: none;
}
</style>
<body>
	<div class="container">
		<div class="row mt-5 align-items-center">
			<h1 class="col-4 m-3">管理者ページ</h1>
			<p class="col-2 ms-auto me-0 my-3">
				<a href="/ShiftMgmtPro2/home/myPage"
					class="btn btn-outline-primary w-100">戻る</a>
			</p>
		</div>


		<div class="outline">
			<h2 class="text-center">従業員管理</h2>
			<div class="d-flex justify-content-center">
				<div class="row">
					<ul class="list-group list-group-horizontal">
						<li><a href="insert" class="btn btn-primary btn-lg m-1">追加</a></li>
						<li><a href="delete" class="btn btn-primary btn-lg m-1">削除</a></li>
						<li><a href="showAll" class="btn btn-primary btn-lg m-1">一覧</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="outline">
			<h2 class="text-center">シフト管理</h2>
			<div class="d-flex justify-content-center">
				<ul class="list-group list-group-horizontal">
					<li><a href="insertShift" class="btn btn-primary btn-lg m-1">編集</a></li>
					<li><a href="showShift" class="btn btn-primary btn-lg m-1">一覧</a></li>
				</ul>
			</div>
		</div>
	</div>

	<!-- .container -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

</body>
</html>