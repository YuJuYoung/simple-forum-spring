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
					if (text != null) {
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

const onClickDeleteCommentBtn = (postId, commentId, logined_id) => {
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