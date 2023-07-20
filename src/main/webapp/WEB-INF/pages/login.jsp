<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./resources/assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="./resources/assets/css/app.css" />
</head>
<body>
	<%@ include file="../layouts/header.jspf"%>
	<div class="bg-body-tertiary content">
		<div class="container py-5">
			<div class="row justify-content-center">
				<div class="col-md-8">
					<div class="card">
						<div class="card-header">Login</div>
						<div class="card-body">
							<form:form class="py-5" method="POST"
								action="/bulletinboard/login" modelAttribute="loginForm">
								<div class="row mb-3">
									<div class="col-4 text-end">
										<label for="postTitle" class="form-label text-end">Email
											Address:</label>
									</div>
									<div class="col-6">
										<form:input path="email" id="postTitle"
											class="form-control ${
												requestScope['org.springframework.validation.BindingResult.loginForm']
													.hasFieldErrors('email') ? 'is-invalid' : ''
													}"
											aria-describedby="validTitle" />
										<div id="validTitle" class="invalid-feedback">
											<form:errors path="email" />
										</div>
									</div>
								</div>
								<div class="row mb-3">
									<div class="col-4 text-end">
										<label for="postDesc" class="form-label text-end">Password</label>
									</div>
									<div class="col-6">
										<form:input path="password" type="password" id="postDesc"
											class="form-control ${
												requestScope['org.springframework.validation.BindingResult.loginForm']
													.hasFieldErrors('password') ? 'is-invalid' : ''
													}"
											aria-describedby="validDescription" />

										<div id="validDescription" class="invalid-feedback">
											<form:errors path="password" />
										</div>
									</div>
								</div>
								<div class="row mb-3">
									<div class="col-6 offset-4 d-flex justify-content-between">
										<div class="">
											<form:checkbox path="rememberMe" id="remember"
												class="btn btn-primary" value="Login" />
											<label class="form-check-label" for="remember">
												Remember Me </label>
										</div>
										<div>
											<a href="/">forgotten password?</a>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-6 offset-4">
										<input class="btn btn-primary" type="submit" value="Login">
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../layouts/footer.jspf"%>
</body>
</html>