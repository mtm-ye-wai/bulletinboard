<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="/bulletinboard/resources/assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="/bulletinboard/resources/assets/css/app.css" />
<script src="/bulletinboard/resources/assets/js/bootstrap.bundle.min.js"></script>
<script src="/bulletinboard/resources/assets/js/jquery-3.7.0.min.js"></script>
<script src="/bulletinboard/resources/assets/js/app.js"></script>
</head>
<body>
	<c:choose>
	    <c:when test="${confirm}">
	        <c:set var="url" value="/bulletinboard/posts/update/${postId}" />
	    </c:when>
	    <c:otherwise>
	        <c:set var="url" value="/bulletinboard/posts/edit/${postId}/confirm" />
	    </c:otherwise>
	</c:choose>
	<%@ include file="../layouts/header.jspf"%>
	<div class="bg-body-tertiary content">
		<div class="container py-5">
			<div class="row justify-content-center">
				<div class="col-md-8">
					<div class="card">
						<div class="card-header">Edit Post</div>
						<div class="card-body">
							<form:form class="py-5" method="POST"
								action="${url}"
								modelAttribute="postForm">
								<div class="row mb-3">
									<div class="col-4 text-end">
										<label for="postTitle" class="form-label required">Title</label>
									</div>
									<div class="col-6">
										<form:input path="title" id="postTitle"
											class="form-control ${
												requestScope['org.springframework.validation.BindingResult.postForm']
													.hasFieldErrors('title') ? 'is-invalid' : ''
													}"
											aria-describedby="validTitle" 
											readonly="${confirm ? 'true' : 'false' }"/>
										<div id="validTitle" class="invalid-feedback">
											<form:errors path="title" />
										</div>
									</div>
								</div>
								<div class="row mb-3">
									<div class="col-4 text-end">
										<label for="postDesc" class="form-label required">Description</label>
									</div>
									<div class="col-6">
										<form:textarea path="description" id="postDesc"
											class="form-control ${
										requestScope['org.springframework.validation.BindingResult.postForm']
											.hasFieldErrors('description') ? 'is-invalid' : ''
											}"
											aria-describedby="validDescription" 
											readonly="${confirm ? 'true' : 'false' }"/>
										<div id="validDescription" class="invalid-feedback">
											<form:errors path="description" />
										</div>
									</div>
								</div>
								<div class="row mb-3">
									<div class="col-4 text-end">
										<label for="postStatus" class="form-label">Status</label>
									</div>
									<div class="col-6">
										<div class="form-check form-switch">
											<form:checkbox class="form-check-input" path="status"
												value="ACTIVE" id="postStatus" role="switch" onclick="${confirm ? 'return false' : '' }"/>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-6 offset-4">
										<input class="btn btn-primary" type="submit" value="${confirm ? 'Confirm' : 'Create' }">
										<input class="btn btn-secondary" type="button" value="${confirm ? 'Cancel' : 'Clear' }" id="${confirm ? 'postCancel' : 'postClear'}">
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