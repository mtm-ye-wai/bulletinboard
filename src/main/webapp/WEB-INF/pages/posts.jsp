<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./resources/assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="./resources/assets/css/app.css" />
<script src="./resources/assets/js/bootstrap.bundle.min.js"></script>

<script src="./resources/assets/js/jquery-3.7.0.min.js"></script>

<link rel="stylesheet"
	href="./resources/assets/css/dataTables.bootstrap5.css" />
<script src="./resources/assets/js/jquery.dataTables.js"></script>
<script src="./resources/assets/js/dataTables.bootstrap5.js"></script>
<script src="./resources/assets/js/app.js"></script>

<script>
	$(document).ready(function() {
		const postTable = $("#post-list").DataTable({
			sDom : "lrtip"
		});
		$("#search-btn").click(function() {
			postTable.search($("#search-box").val()).draw();
		});
	});
</script>
</head>
<body>
	<%@ include file="../layouts/header.jspf"%>
	<div class="bg-body-tertiary content">
		<div class="container py-5">
			<div class="row justify-content-center">
				<div class="col-md-12">
					<div class="card">
						<div class="card-header">Post List</div>
						<div class="alert alert-success" id="postAlert" role="alert"
							style="display: none;"></div>
						<div class="card-body">
							<div class="d-flex justify-content-end">
								<div
									class="mb-2 search-bar d-flex align-items-center dataTables_filter"
									id="post-list_filter">
									<label class="me-2">Keyword:</label> <input
										class="me-2 search-input form-control" type="text"
										id="search-box">
									<button class="me-2 btn btn-primary search-btn" id="search-btn">Search</button>
									<a href="/bulletinboard/posts/create"
										class="me-2 btn btn-primary search-btn">Create</a>
									<button class="me-2 btn btn-primary search-btn">Upload</button>
									<button class="btn btn-primary">Download</button>
								</div>
							</div>
							<table class="table table-striped table-bordered table-sm"
								id="post-list" role="grid" aria-describedby="post-list_info">
								<thead>
									<tr role="row">
										<th class="col-1 text-center" scope="col">Post Title</th>
										<th class="col-1 text-center" scope="col">Post Description</th>
										<th class="col-1 text-center" scope="col">Posted User</th>
										<th class="col-1 text-center" scope="col">Posted Date</th>
										<th class="col-1 text-center" scope="col">Operation</th>
									</tr>
								</thead>
								<tbody>
									<c:choose>
										<c:when test="${posts.size()>0}">
											<c:forEach items="${posts}" var="post">
												<tr role="row">
													<td class="text-center">
														<span class="post-name" data-bs-toggle="modal"
															data-bs-target="#postDetailModal"
															onclick="showPostDetail(
															'${post.title()}',
															'${post.description()}',
															'${post.status()}',
															'${post.createdAt()}',
															'${post.createdUserName()}',
															'${post.updatedAt()}',
															'${post.updatedUserName()}')">
															<c:out value="${post.title()}" />
														</span>
													</td>
													<td class="text-center whitespace-pre"><c:out
															value="${post.description()}" /></td>
													<td class="text-center"><c:out
															value="${post.createdUserName()}" /></td>
													<td class="text-center"><c:out
															value="${post.createdAt()}" /></td>
													<td class="text-center"><a class="btn btn-primary"
														href="/bulletinboard/posts/edit/${post.id()}">Edit</a>
														<button class="btn btn-danger" data-bs-toggle="modal"
															data-bs-target="#deleteModal"
															onclick="showDeleteConfirm(
																'${post.id()}',
																'${post.title()}',
																'${post.description()}',
																'${post.status()}'
															)">Delete</button>
													</td>
												</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<p>No data available in table</p>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div>
					</div>
				</div>


				<!-- Delete Modal -->
				<div class="modal fade" id="deleteModal" tabindex="-1"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-header">
								<h1 class="modal-title fs-5" id="exampleModalLabel">Delete
									Confirm</h1>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<p>Are you sure to delete post?</p>
								<div class="col-md-12">
									<div class="row mb-3">
										<label class="col-md-4 text-md-left">ID</label> <label
											class="col-md-8 text-md-left"><i class="post-text"
											id="postId"></i> </label>
									</div>
									<div class="row mb-3">
										<label class="col-md-4 text-md-left">Title</label> <label
											class="col-md-8 text-md-left"> <i class="post-text"
											id="postTitle"></i>
										</label>
									</div>
									<div class="row mb-3">
										<label class="col-md-4 text-md-left">Description</label> <label
											class="col-md-8 text-md-left"> <i class="post-text"
											id="postDescription"></i>
										</label>
									</div>
									<div class="row mb-3">
										<label class="col-md-4 text-md-left">Status</label> <label
											class="col-md-8 text-md-left"> <i class="post-text"
											id="postStatus"></i>
										</label>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Close</button>
								<a href="" type="button" class="btn btn-danger"
									id="deleteCofirmBtn">Delete</a>
							</div>
						</div>
					</div>
				</div>
				
				
				<!-- post Detail Modal -->
				<div class="modal fade" id="postDetailModal" tabindex="-1"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-header">
								<h1 class="modal-title fs-5" id="exampleModalLabel">
									Post Detail
								</h1>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<div class="col-md-12">
									<div class="row mb-3">
										<label class="col-md-4 text-md-left">Title</label>
										<label class="col-md-8 text-md-left">
											<i class="post-text" id="detailPostTitle"></i>
										</label>
									</div>
									<div class="row mb-3">
										<label class="col-md-4 text-md-left">Description</label>
										<label class="col-md-8 text-md-left">
											<i class="post-text" id="detailPostDescription"></i>
										</label>
									</div>
									<div class="row mb-3">
										<label class="col-md-4 text-md-left">Status</label>
										<label class="col-md-8 text-md-left">
											<i class="post-text" id="detailPostStatus"></i>
										</label>
									</div>
									<div class="row mb-3">
										<label class="col-md-4 text-md-left">Created Date</label>
										<label class="col-md-8 text-md-left">
											<i class="post-text" id="detailPostCreatedAt"></i>
										</label>
									</div>
									<div class="row mb-3">
										<label class="col-md-4 text-md-left">Created User</label>
										<label class="col-md-8 text-md-left">
											<i class="post-text" id="detailPostCreatedUser"></i>
										</label>
									</div>
									<div class="row mb-3">
										<label class="col-md-4 text-md-left">Updated Date</label>
										<label class="col-md-8 text-md-left">
											<i class="post-text" id="detailPostUpdatedAt"></i>
										</label>
									</div>
									<div class="row mb-3">
										<label class="col-md-4 text-md-left">Updated User</label>
										<label class="col-md-8 text-md-left">
											<i class="post-text" id="detailPostUpdatedUser"></i>
										</label>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../layouts/footer.jspf"%>
	<script>
		let a = "${posts}"
	</script>
</body>
</html>