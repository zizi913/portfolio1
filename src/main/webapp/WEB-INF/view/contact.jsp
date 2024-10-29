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
<title>contact</title>
</head>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<body>
	<div class="container">
		<div class="row mt-5 align-items-center">
			<h1 class="col-4 m-3">コンタクト</h1>
			<p class="col-2 ms-auto me-0 my-3">
				<a href="main" class="btn btn-outline-primary w-100">戻る</a>
			</p>
		</div>
		<div class="outline">
			<div class="loading" id="loadingSpinner"></div>
			<div class="row d-flex justify-content-center">
				<c:if test="${!empty msg }">
					<p class="col-4 p-1 text-center alert alert-primary">
						<c:out value="${msg }" />
					</p>
				</c:if>
				<form action="" method="post" onsubmit="return validateAndSubmit()">
					<input type="hidden" name="to" value="zizi913@icloud.com">
					<p class="col-12 text-center">
						あなたのメールアドレス:
						<c:out value="${member.email }" />
					</p>
					<p class="col-12 text-center">※送信に30秒ほどかかります。画面が変化するまでそのままお待ちください。</p>
					<div class="d-flex justify-content-center">
						<form class="w-50">
							<div class="text-center">
								<p class="h5 p-2">
									<label for="subject" class="p-2">表題</label> <input id="subject"
										type="text" name="subject" list="example"
										class="form-control w-100">
								</p>
								<datalist id="example" >
									<option value="欠勤連絡"></option>
									<option value="シフト変更希望"></option>
									<option value="希望休申請"></option>
								</datalist>
								<input type="hidden" name="from" value="${member.email}">
								<p class="h5 p-2">
									<label for="maintext" class="p-2">本文</label>
									<textarea name="maintext" id="maintext" cols="30" rows="10"
										maxlength="400" class="form-control w-100"></textarea>
								</p>
								<div class="d-flex justify-content-center">
									<input type="submit" value="送信"
										class="btn btn-primary btn-lg w-100">
								</div>
							</div>
						</form>
					</div>

				</form>
			</div>
		</div>
	</div>
</body>

<style>
.outline{
border:2px solid #ffffff;
border-radius:15px;
padding:20px;
box-shadow:0 2px 4px rgba(0,0,0,0.2);
margin:20px auto;
width:50%;
}
*, *::before, *::after {
	box-sizing: border-box;
}

.loading {
	height: 100vh;
	width: 100vw;
	cursor: progress;
	display: none; /* 初期状態は非表示 */
	position: fixed; /* スクロールに影響されないように固定 */
	top: 0;
	left: 0;
	background-color: rgba(255, 255, 255, 0.7); /* 半透明背景 */
}

.loading::before {
	content: "";
	position: absolute;
	top: 50%;
	left: 50%;
	width: 5em;
	height: 5em;
	margin-top: -2.5em;
	margin-left: -2.5em;
	border-radius: 50%;
	border: 0.25em solid #ccc;
	border-top-color: #333;
	animation: spinner 1.5s linear infinite;
}

@keyframes spinner {
	to { 
		transform:rotate(360deg);
	}
}
</style>
<script>
	document.addEventListener("DOMContentLoaded", function() {
		const form = document.querySelector("form");

		form.addEventListener("submit", function(event) {
			const subject = form.subject.value.trim();
			const maintext = form.maintext.value.trim();

			// 件名と本文が空の場合は送信を中止し、アラートを表示
			if (subject === "" || maintext === "") {
				event.preventDefault(); // フォーム送信を中止
				alert("表題と本文は必須です。"); // アラート表示
				return false; // ローディングを表示させない
			}
		});
	});

	function validateAndSubmit() {
		const subject = document.querySelector("input[name='subject']").value
				.trim();
		const maintext = document.querySelector("textarea[name='maintext']").value
				.trim();

		if (subject !== "" && maintext !== "") {
			showLoading(); // ローディングアニメーションを表示
			return true; // フォーム送信を許可
		} else {
			return false; // フォーム送信を中止
		}
	}

	function showLoading() {
		document.getElementById('loadingSpinner').style.display = 'block'; // ローディング表示
	}
</script>
</html>