<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>卡片点点Cardpopo</title>
</head>
<body>
<style>
.tutorial{
	border: 0;
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    display:none;
}
</style>
<img alt="" src="images/tutorial1.jpeg" class="tutorial" id="tutorial1" index="1" style="display:block">
<img alt="" src="images/tutorial2.jpeg" class="tutorial" id="tutorial2" index="2">
<img alt="" src="images/tutorial3.jpeg" class="tutorial" id="tutorial3" index="3">
<img alt="" src="images/tutorial4.jpeg" class="tutorial" id="tutorial4" index="4">
<script src="assets/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$('.tutorial').on("click", function () {
		var index = parseInt($(this).attr("index"));
		if(index<4){
			$(this).hide();
			$('#tutorial'+(index+1)).show();	
		}else{
			location.href="cardlist.html?cardId=<%=request.getParameter("cardId")%>";
		}
	});
});
</script>

</body>
</html>