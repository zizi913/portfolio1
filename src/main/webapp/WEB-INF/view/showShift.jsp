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

<title>shift</title>
</head>
<body>
	<div class="container">
		<div class="row mt-5 align-items-center">
			<h1 class="col-4 m-3">シフト</h1>
			<p class="col-2 ms-auto me-0 my-3">
				<a href="/ShiftMgmtPro2/home/owner/owner"
					class="btn btn-outline-primary w-100">戻る</a>
			</p>
		</div>
		<div class="text-center">
			<form action="" method="post">
				<input type="date" name="start">～<input type="date"
					name="end"> <input type="submit" value="表示">
			</form>
		</div>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th></th>
					<c:forEach var="name" items="${nameList}">
						<th>${name}</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="shift" items="${shiftList}">
					<tr>
						<td><fmt:formatDate value="${shift.key}" pattern="MM/dd" /></td>
						<c:forEach var="name" items="${nameList}">
							<td><c:choose>
									<c:when test="${shift.value[name] != null}">
                                    ${shift.value[name]}
                                </c:when>
									<c:otherwise>
								-
								</c:otherwise>
								</c:choose></td>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>
</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>

<style>

</style>
</html>