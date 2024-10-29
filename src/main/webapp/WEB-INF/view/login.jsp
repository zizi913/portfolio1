<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>login</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="row justify-content-center mt-5">
			<div class="col-md-4">
				<div class="card">
					<div class="card-body text-center">
						<h2 class="mb-4">ログイン</h2>

						<c:if test="${!empty error}">
							<p class="alert alert-danger">
								<c:out value="${error}" />
							</p>
						</c:if>

						<form action="" method="post">
							<div class="mb-3">
								<input type="text" name="loginId" placeholder="LoginID"
									class="form-control" />
							</div>
							<div class="mb-3">
								<input type="password" name="pass" placeholder="Password"
									class="form-control" />
							</div>
							<div class="d-grid">
								<input type="submit" value="ログイン"
									class="btn btn-primary btn-block" />
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous">
		
	</script>
</body>
</html>