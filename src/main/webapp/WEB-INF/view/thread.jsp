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
<title>thread</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<style>
/* * {
	outline: 1px solid blue;
} */
</style>
<body>
	<div class="container">
		<div class="row mt-5 align-items-center">
			<h1 class="col-4 m-3">掲示板</h1>
			<p class="col-2 ms-auto me-0">
				<a href="main" class="btn btn-outline-primary w-100">戻る</a>
			</p>
		</div>
		<c:if test="${!empty msg }">
			<p>
				<c:out value="${msg}" />
			</p>
		</c:if>
		<div class="m-3 d-flex justify-content-center">
			<form action="" method="post" id="postForm" class="w-100">
				<div class="form-group">
					<textarea name="post" id="text" cols="30" rows="10" maxlength="400"
						class="form-control w-100"></textarea>
				</div>
				<div class="row">
					<div class="ms-auto me-0 mt-2 col-2">
						<input type="submit" value="投稿" class="btn btn-primary w-100">
					</div>
				</div>
			</form>
		</div>
	<hr>

		<c:forEach items="${thread }" var="t">
			<div class="row">
				<p class="pb-0 mb-0 col-6 h-5">
					投稿者:
					<c:out value="${t.name}" />
				</p>
				<p class="col-6">
					投稿日:
					<c:out value="${t.date }" />
				</p>
			</div>
			<p class="p-1">
				<c:out value="${t.post }" />
			</p>
			<c:if test="${loginId==t.loginId}">
				<div class="mx-auto"></div>
				<form action="${pageContext.request.contextPath}/home/deletePost"
					method="post" class="d-flex justify-content-end">
					<input type="hidden" name="id" value="${t.id}" id="id"> <input
						type="submit" name="delete" value="削除"
						class="btn btn-secondary btn-sm">
				</form>

			</c:if>
			<hr>
			<c:set var="currentId" value="${t.id}" scope="request" />
		</c:forEach>

		<nav aria-label="Page navigation" class="mx-auto">
			<ul class="pagination">
				<!-- 現在のページが1より大きかったら前のページボタン表示 -->
				<c:if test="${currentPage > 1}">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/home/thread?page=${currentPage - 1}"
						aria-label="Previous"> <span aria-hidden="true">&laquo;
								前のページ</span>
					</a></li>
				</c:if>

				<!-- 1からトータルページ数分ページ番号を表示かつ現在のページ番号を強調 -->
				<c:forEach begin="1" end="${totalPages}" var="i">
					<c:choose>
						<c:when test="${i == currentPage}">
							<li class="page-item active"><span class="page-link">${i}</span>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link"
								href="${pageContext.request.contextPath}/home/thread?page=${i}">${i}</a>
							</li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<!-- 現在のページがトータルページより小さかったら次へボタン表示 -->
				<c:if test="${currentPage < totalPages}">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/home/thread?page=${currentPage + 1}"
						aria-label="Next"> <span aria-hidden="true">次のページ
								&raquo;</span>
					</a></li>
				</c:if>
			</ul>
		</nav>

	</div>
</body>
<script>
$(function(){
	$('submit').on('click', function(){
		$.ajax({
			url:contextPath+'/thread'
			type:'POST',
			data:,
			dateType:'JSON'
		})
		//Ajax通信が成功したら発動
		.done((date))=>{
			//変数dateには受け取ったデータの値が入る
			$('.result').html(date);
			console.log('Ajax通信に失敗しました。');
		})
	 	//Ajax通信が失敗したら発動
	 	.fail(function(){
	 		alert('Ajax通信に失敗しました。')
	 		})
	 	}
})
</script>
</head>
<body>
</html>