const onClickCreateBtn = (e, postId, logined_id) => {
	e.preventDefault();
				
	if (!logined_id) {
		alert("로그인 필요");
	} else {
		var description = e.target.description.value;
		
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
					description: e.target.description.value
				})
			}).then(res => {
				res.text().then(text => {
					if (text) {
						var newComment = document.createElement('li');
						var commentList = document.getElementById("comment-list");
						
						newComment.setAttribute("class", "list-group-item");
						newComment.innerHTML = text;
						commentList.append(newComment);
					} else {
						alert("다시 로그인 필요");
					}
				})
			})
		}
	}
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
			var newTag = document.createElement("div");
			var comment = document.getElementById("comment-" + commentId);
			
			newTag.setAttribute("class", "update-comment");
			newTag.setAttribute("id", "update-comment-" + commentId);
			newTag.innerHTML = text;
			
			if (text) {
				comment.append(newTag);
			} else {
				alert("오류 발생");
			}
		})
	})
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