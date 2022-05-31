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
			res.json().then(json => {
				if (json.valid) {
					alert("성공");
				} else {
					alert("오류 발생");
				}
			})
		})
	}
}