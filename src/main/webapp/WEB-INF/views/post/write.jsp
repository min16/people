<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="/WEB-INF/views/common/common.jsp" />
	<title>글쓰기</title>
	<script type="text/javascript" src="//cdn.tinymce.com/4/tinymce.min.js"></script>
  	<script type="text/javascript">
  		tinymce.init({ 
  				selector: 'textarea',
		  		height: 500,
		  	 	plugins: [
			  	    'advlist autolink lists link image charmap print preview',
			  	    'searchreplace visualblocks code fullscreen',
			  	    'insertdatetime media table contextmenu code',
			  	    'textcolor colorpicker textpattern',
			  	],
			    toolbar1: 'undo redo | insert | formatselect fontselect fontsizeselect',
			    toolbar2: 'forecolor backcolor | bold italic | alignleft aligncenter alignright alignjustify |' +
    					  'bullist numlist outdent indent | imageButton',
			    content_css: '//www.tinymce.com/css/codepen.min.css',
			  	language_url: '/resources/langs/ko_KR.js',
				image_title: true,
				automatic_uploads: true,
				setup: function (editor) {
					editor.addButton('imageButton', {
						text: '이미지 추가하기',
						icon: 'image',
						onclick: function() {
							$(uploadModal).modal('show');
						}
					})
				}
  		});
  	</script>
  	<script type="text/javascript" src="/resources/js/write.js"></script>
  	<link rel="stylesheet" type="text/css" href="/resources/css/write.css" />
</head>

<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="container">
		<!-- Modal -->
		<div id="uploadModal" class="modal fade" role="dialog">
		  <div class="modal-dialog">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">이미지 업로드</h4>
		      </div>
		      <div class="modal-body">
		        <form method="POST" id="upload-form" name="upload-form" action="/posts/upload" enctype="multipart/form-data" class="form-horizontal">
		        	<input type="file" class="filestyle" name="file" data-input="false">
                    <br />
                    <input type="button" onClick="fileUpload()" value="upload" id="upload" class="btn btn-default btn-file"/>
		        </form>
		      </div>
		    </div>
		  </div>
		</div>
		<form id="form" action="/posts" method="POST">
		  <div class="form-group">
		    <label>제목</label>
		    <input type="text" id="title" name="title" class="form-control" maxlength="100">
		  </div>
		  <div class="form-group">
		    <label>카테고리</label>
		    <select id="category" name="category" class="form-control">
		   	  <option value="all">카테고리 선택 </option>
		   	  <option value="NOTICE">공지사항</option>
		   	  <option value="WELCOME">환영</option>
		   	  <option value="PERSONNEL">인사</option>
			  <option value="VACATION">휴가</option>
			  <option value="REQUIREMENT">요청사항</option>
			</select>
		  </div>
		  <div class="form-group">
		    <label>작성자</label>
		    <input id="userName" type="text" name="userName" class="form-control" maxlength="30" value="ohnew" readonly>
		  </div>
		  <textarea id="content" name="content" maxlength="100000"></textarea>
	  	  <div class="button">
	  	  	<a class="btn btn-default" id="goBack">돌아가기</a>	  		
	  	  	<button class="btn btn-success" onClick="save()">저장하기</button>
	  	  </div>
		</form>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>