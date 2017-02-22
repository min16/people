
function update(postNo){
	location.href="/posts/" + postNo + "/proc";
}

function remove(postCategory, postNo){
	if(confirm("삭제하시겠습니까?")) {
		$.ajax({
			url: "/posts/" + postNo,
			type: "DELETE",
			success: function(data) {
				if(data == 1){
					if(postCategory == 'NOTICE'){
						location.href="/posts";
					}else{
						location.href="/posts?category=" + postCategory;
					}
				}else{
					alert("삭제를 실패했습니다.");
				}
			},
			error: function(data) {
				console.log(data);
				alert("삭제를 실패했습니다.");
			}
		});
	}
}

$(function(){
	$('#goBack').click(function(){
		var postCategory = $('#postCategory').val();
		var category = $('#category').val();
		var query = $('#query').val();
		var queryType = $('#queryType').val();
		var pageNo = $('#pageNo').val();
		if(category == 'all' && query == '' && queryType == 'all' && pageNo == 0){
			location.href = "/posts?category=" + postCategory;
		}
		else{
			location.href = "/posts?category=" + category 
							+ "&query=" + query
							+ "&queryType=" + queryType
							+ "&pageNo=" + pageNo;
		}
	});
});

	
	



