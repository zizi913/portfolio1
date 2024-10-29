<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>mypage</title>
<style>
 /* * {
	outline: 1px solid blue;
} */
.outline{
border:2px solid #ffffff;
border-radius:15px;
padding:20px;
box-shadow:0 2px 4px rgba(0,0,0,0.2);
margin:20px auto;
width:50%;
}
a {
	text-decoration: none;
}

li {
	list-style-type: none;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row justify-content-center mt-5">
			<div class="d-flex align-items-center">
				<div class="m-3">
					<h1>マイページ</h1>
				</div>
				<ul class="d-flex justify-content-end list-unstyled me-1 ms-auto pull-right">
				<c:if test="${member.statusId==1}">
					<li class="btn btn-primary me-2"><a href="/ShiftMgmtPro2/home/owner/owner" class="col-2 text-white">管理者ページ</a></li>
					</c:if>
					<li class="me-2"><a href="main" class="btn btn-outline-primary w-100" >戻る</a></li>
					
				</ul>
			</div>
		</div>
		<div class="outline">
	<div class="row">
		<p>
			<span class="col-2 d-inline-block">氏名 :</span>
			<span class="col-8 offset-1 d-inline-block"><c:out value="${member.name }" /></span>
		</p>

		<p>
			<span class="col-2 d-inline-block ">ログインID :</span>
			<span class="col-8 offset-1 d-inline-block"><c:out value="${member.loginId }" /></span>
		</p>
		<p>
			<span class="col-2 d-inline-block">e-mail :</span>
			<span class="col-8 offset-1 d-inline-block"><c:out value="${member.email }" /></span>
		</p>
		<p>
			<span class="col-2 d-inline-block">TEL :</span>
			<span class="col-8 offset-1 d-inline-block"><c:out value="${member.tel }" /></span>
		</p>
		<p>
			<span class="col-2 d-inline-block">雇用形態 :</span>
			<span class="col-8 offset-1 d-inline-block"><c:out value="${member.status }" /></span>
		</p>
		<div class="d-flex justify-content-center">
		<div class="col-3 text-center">
		<p class="">
			<a href="edit" class="btn btn-primary w-100">編集</a>
		</p>
		</div>
		</div>
		</div><!-- outline -->
</div><!-- .row -->

	</div>
	<!-- .container -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

</body>
</html>