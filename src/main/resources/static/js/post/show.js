function createComment(postId, logined_id) {
	return false;
	/*if (!logined_id) {
		alert("로그인 필요");
	} else {
		var description = document.getElementById("create-comment-desc").value;
		
		if (description === "") {
			alert("내용이 없으면 안됨");
		} else {
			fetch("/post/" + postId + "/comment/create", {
				method: "post",
				headers: {
					"Content-Type": "application/json"
				},
				body: JSON.stringify({
				    userId: logined_id,
					description: description
				})
			}).then(res => {
				res.text().then(text => {
					if (text) {
						var newComment = document.createElement('li');
						var commentList = document.getElementById("comment-list");
						
						newComment.setAttribute("class", "list-group-item");
						newComment.innerHTML = text;
						commentList.append(newComment);
						
						document.getElementById("create-comment-desc").value = "";
					} else {
						alert("다시 로그인 필요");
					}
				})
			})
		}
	}
	
	return false;*/
}

const onClickUpdateCommentBtn = (postId, commentId, loginedId) => {
	fetch("/post/" + postId + "/comment/updateForm", {
		method: "post",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			userId: loginedId,
			commentId: commentId
		})
	}).then(res => {
		res.text().then(text => {
			if (text) {
				var newTag = document.createElement("div");
				var comment = document.getElementById("comment-" + commentId);
				
				newTag.setAttribute("class", "update-comment");
				newTag.setAttribute("id", "update-comment-" + commentId);
				newTag.innerHTML = text;
			
				comment.append(newTag);
			} else {
				alert("오류 발생");
			}
		})
	})
}

function updateComment(postId, commentId, loginedId) {
	var desc = document.getElementById("update-comment-desc-" + commentId).value;
	
	fetch("/post/" + postId + "/comment/update", {
		method: "post",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			commentId: commentId,
			userId: loginedId,
			desc: desc
		})
	}).then(res => {
		res.text().then(text => {
			if (text) {
				document.getElementById("comment-" + commentId + "-desc").innerText = text;
				document.getElementById("update-comment-" + commentId).remove();
			} else {
				alert("오류 발생");
			}
		})
	})
	
	return false;
}

const deleteComment = (postId, commentId, logined_id) => {
	if (!logined_id) {
		alert("로그인 필요");
	} else {
		fetch("/post/" + postId + "/comment/delete", {
			method: "post",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify({
				userId: logined_id,
				commentId: commentId
			})
		}).then(res => {
			res.json().then(json => {
				if (!json.valid) {
					alert("오류 발생");
				} else {
					var comment = document.getElementById("comment-" + commentId);
					comment.parentElement.remove();
				}
			})
		})
	}
}