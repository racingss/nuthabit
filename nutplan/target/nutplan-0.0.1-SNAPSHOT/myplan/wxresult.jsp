<%@ page language="java" import="com.nuthabit.util.*,java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%
 new KehuUtil().getKehuByWeixin(request, response);
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
<aside class="btmNav">
<%if(request.getAttribute("fanhui")!=null){ %>
<script language="JavaScript">
	function myrefresh(){
		window.location.href="<%=request.getAttribute("fanhui").toString()%>";
		setTimeout('myrefresh()',5000);
	}
	setTimeout('myrefresh()',5000); //指定1秒刷新一次
</script>
<script>
var start = 5;
var step = 1;
function count()
{
document.getElementById("daojishi").innerHTML = start;
if(start == 5 || start == 0)
step *= -1;
start += step;
setTimeout("count()",1000);
}
window.onload = count;
</script>
<%} %>
<%
if(request.getSession().getAttribute("fanhui")!=null){
	String temp = request.getSession().getAttribute("fanhui").toString();
	request.getSession().removeAttribute("fanhui");
	%>
	<script language="JavaScript">
		window.location.href="<%=temp%>";
	</script>
	<%
}
%>
<%if(request.getAttribute("fanhui")!=null){ %>
 <a href="<%=request.getAttribute("fanhui") %>" style="background:#00a1e9;color:white;text-shadow:none;">返回上个页面</a>
<%}%>
 <a style="width:0px;" href="javascript:showNav();"><!--img src="images/tou.jpg" alt"" style="height:30px;margin-bottom:10px;"-->菜单</a>
 <a href="gerenzhongxin.html">个人中心</a>
 <a href="gouwuche.html" style="background:#F60;color:white;text-shadow:none;">购物车</a>
</aside>





 
</body>
</html>