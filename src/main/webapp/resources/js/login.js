function validate(data){
	var adminName = $('.adminName').val();
	var password = $('.password').val();
	if(!adminName || adminName.length > 30){
		alert('이름을 30자 이내로 작성해주세요.');
		return false;
	}else if(!password || password.length >45){
		alert('비밀번호를 45자 이내로 작성해주세요.');
		return false;
	}else{
		return true;
	}
};

function result(data){
	var result = data;
	if(result == 'invalid input'){
		alert('다시 입력해주세요.');
	}else if(result == 'login failed'){
		alert('존재하지 않는 계정입니다.');
	}else{
		window.location.replace(document.referrer);
	}
};

function login(){
	$('.login').ajaxForm({
		beforeSubmit: validate,
		url: "/login/proc",
		method: "POST",
		success: result
	});
};

