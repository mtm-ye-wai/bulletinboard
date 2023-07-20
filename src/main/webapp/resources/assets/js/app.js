$(document).ready(function() {
	$("#postCancel").click(() => {
		history.back()
	})
	$("#postClear").click(() => {
		$('#postTitle').val('');
		$('#postDesc').val('');
	})
	const urlString = window.location.href;
	const url = new URL(urlString);
	const baseURL = url.origin + url.pathname;
	const segments = baseURL.split('/');
	const pageName = segments[segments.length - 1];
	const params = new URLSearchParams(window.location.search);
	const successAlert = $("#postAlert");
	const postSuccessMessages = {
		'created': 'post successfully created',
		'updated': 'post successfully edited',
		'deleted': 'post successfully deleted'
	};
	const userSuccessMessages = {
		'created': 'user successfully created',
		'updated': 'user successfully edited',
		'deleted': 'user successfully deleted'
	};
	let alertMsg;
	if (params.get('created') === 'true') {
		alertMsg = pageName === "posts" ? postSuccessMessages.created : userSuccessMessages.created;
	} else if (params.get('updated') === 'true') {
		alertMsg = pageName === "posts" ? postSuccessMessages.updated : userSuccessMessages.updated;
	} else if (params.get('deleted') === 'true') {
		alertMsg = pageName === "posts" ? postSuccessMessages.deleted : userSuccessMessages.deleted;
	}

	if (alertMsg) {
		successAlert.html(alertMsg);
		successAlert.show();

		setTimeout(function() {
			successAlert.hide();
		}, 5000);
	}

});
const showDeleteConfirm = (id, title, description, status) => {
	$('#postId').html(id)
	$('#postTitle').html(title)
	$('#postDescription').html(description)
	$('#postStatus').html(status)
	$('#deleteCofirmBtn').attr("href", "/bulletinboard/posts/destroy/" + id);
}

const showPostDetail = (title, description, status, createdAt, createdUserName, updatedAt, updatedUserName) => {
	$('#detailPostTitle').html(title)
	$('#detailPostDescription').html(description)
	$('#detailPostStatus').html(status)
	$('#detailPostCreatedAt').html(createdAt)
	$('#detailPostCreatedUser').html(createdUserName)
	$('#detailPostUpdatedAt').html(updatedAt)
	$('#detailPostUpdatedUser').html(updatedUserName)
}







