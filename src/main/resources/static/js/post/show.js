const onClickCreateCommentBtn = (postId, logined_id) => {
	if (!logined_id) {
		alert("로그인 필요");
	} else {
		fetch('/post/' + postId + '/comment/createForm').then(res => {
		res.text().then(text => {
			document.getElementById("create-comment").innerHTML = text;
		})
	})
	}
}

const onClickCreateBtn = (e, postId, logined_id) => {
	e.preventDefault();
				
	if (!logined_id) {
		alert("로그인 필요");
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
					
					newComment.innerHTML = text;
					commentList.append(newComment);
				}
			})
		})
	}
}