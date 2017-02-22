$(document).ready(function(){
	var referrer = document.referrer;
	console.log(referrer);
});
$(function(){
	$('#goBack').click(function(){
		var referrer = document.referrer;
		window.location.replace(referrer);
	});
});

function fileUpload() { 
	$("#upload-form").ajaxForm({
		beforeSubmit: function(data) {
			var file = data[0].value;
			console.log(file);
			if(file.size == 0 || !file.type.includes("image")){
				alert("이미지파일을 선택해주세요.");
				return false;
			}
		},
		url: "/posts/upload",
		method: "POST",
		enctype: "multipart/form-data",
		success: function(data) {
			console.log(data);
			if(data.error == 'fileType error'){
				alert('이미지 파일만 올려주세요.');
			}else if(data.error == 'empty file'){
				alert('빈파일입니다.');
			}else if(data.location != undefined){
				tinymce.activeEditor.execCommand('mceInsertContent','false','<img src="' + data.location + '"/>');
			}
		}
	 });
	
	$('#upload-form').submit();
}; 

function validate(data){
	console.log(data);
	var title = $('#title').val();
	var category = $('#category').val();
	var content = $('#content').val();
	if(!title || title.length > 100) {
		alert("제목을 1자에서 100자 사이로 작성해주세요.");
		return false;
	}else if(category == 'all'){
		alert("카테고리를 선택해주세요.");
		return false;
	}else if(!content || content.length > 100000){
		alert("내용을 1자에서 100000자 사이로 작성해주세요.");
		return false;
	}else{
		return true;
	}
}

function result(data){
	console.log(data);
	var result = data;
	if(result == "invalid input"){
		alert("다시 입력해주세요.");
	}else{
		location.href="/posts?category=" + result;
	}
}

function save(){
	$('#form').ajaxForm({
		beforeSubmit: validate,
		url: "/posts",
		method: "POST",
		success: result
	});
}