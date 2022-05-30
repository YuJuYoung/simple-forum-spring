function onClickCreateCommentBtn(postId, logined_id) {
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