<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
Kehu k = new KehuUtil().getKehu(request, response);
Tutorial t = new Tutorial();
t.setkId(k.getId());
t.setTutorialType(Tutorial.MAIN);
new TutorialDAO().add(t);
%>    
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
div{
	display:block;
	border: 0;
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
}
</style>
<div>
<img alt="" src="images/tutorial1.jpeg" class="tutorial" id="tutorial1" index="1" style="display:block">
<img alt="" src="images/tutorial2.jpeg" class="tutorial" id="tutorial2" index="2">
<img alt="" src="images/tutorial3.jpeg" class="tutorial" id="tutorial3" index="3">
<img alt="" src="images/tutorial4.jpeg" class="tutorial" id="tutorial4" index="4">
<img alt="" src="images/tutorial5.jpeg" class="tutorial" id="tutorial5" index="5">
</div>

<script src="/card/assets/js/jquery-1.11.0.min.js"></script>




<script type="text/javascript">
$(function(){
	var index = 1;
	$('div').on("click", function () {
		if(index<=4){
			$('#tutorial'+index).hide();
			index=parseInt(index)+1;
			$('#tutorial'+index).show();
		}else{
			location.href="/diandian/index.html";
		}
	});
});
</script>

</body>
</html>