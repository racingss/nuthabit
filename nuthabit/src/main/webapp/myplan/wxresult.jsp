<%@ page language="java" import="com.nuthabit.util.*,java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%
 
 System.out.println(1);
 //new KehuUtil().getKehu(request, response);
 request.getSession().setAttribute("kehu",new KehuUtil().getKehuByWeixin(request, response));
 System.out.println(2);
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>操作结果</title>
<meta name="keywords"  content="KEYWORDS..." />
<meta name="description" content="DESCRIPTION..." />
<meta name="author" content="DeathGhost" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name='apple-touch-fullscreen' content='yes'>
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta name="format-detection" content="address=no">
<link rel="icon" href="images/icon/favicon.ico" type="image/x-icon">
<link rel="apple-touch-icon-precomposed" sizes="57x57" href="images/icon/apple-touch-icon-57x57-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="120x120" href="images/icon/apple-touch-icon-120x120-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="196x196" href="images/icon/apple-touch-icon-196x196-precomposed.png">
<meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script src="js/jquery.js"></script>
<script language="JavaScript">
window.location.href='index.html?login=t';
<%
if(request.getSession().getAttribute("sessionURL")!=null){
	%>
	window.location.href='<%=request.getSession().getAttribute("sessionURL").toString()%>';
	<%
}else{
	%>
	window.location.href='index.html';
	<%
}
%>
</script>
</head>
<body>
<!--header-->
<dl class="cart">
	<img alt="吃货" src="images/pinzhi.png" style="width:100%;">
</dl> 
 <section class="return_state" style="width:100%;float:left;display:block;height:200px;padding-top:80px;">
 
<%if(request.getAttribute("fanhui")!=null){ %>
<div style="width:100%;text-align:center;"><span id="daojishi">5</span>秒钟后返回</div>
<%} %> 
</section>

<!--bottom nav-->
<div style="height:1rem;"></div>






 
</body>
</html>