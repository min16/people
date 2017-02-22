$(document).ready(function(){
	
	var pagingTotal = document.getElementById("pagingTotal").value; 
	var pageNo = document.getElementById("pageNo").value;
	pageNo++;
	
	$('#page-selection').bootpag({
		page: pageNo,
		total: pagingTotal,
		maxVisible: 10,
	}).on("page", function(event, num){
		paging(num);
	});
	
	
});

function goDetail(postNo, category, query, queryType, pageNo){
	location.href = "/posts/" + postNo 
					+ "?category=" + category 
					+ "&query=" + query
					+ "&queryType=" + queryType
					+ "&pageNo=" + pageNo;
}

function categorySubmit(){
	document.getElementById('pageNo').value = 0;
	document.getElementById('boardForm').submit();
}

function paging(pageNo){
	document.getElementById('pageNo').value = pageNo-1;
	document.getElementById('boardForm').submit();
}

$(function(){
	$('#search').click(function(){
		$('#pageNo').val('0');
		$('#search').submit();
	});

});

