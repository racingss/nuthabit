<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
long languageId = new LanguageHttp().getLanguageId(request);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title><%=Menu.getTitle(languageId) %></title>
	<link rel="stylesheet" type="text/css" href="css/swiper.min.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/swiper.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript">
		$(function(){
			var mySwiper = new Swiper('.swiper-container',{
          		loop: true,
        		autoplay: 3000,
        		pagination: '.swiper-pagination',
        	});

		})
	</script>
	<style type="text/css">
	
	</style>
</head>
<body>
	<div class="page home">
		
		<div>
			<div>打卡天数</div>
			<div>20</div>
			<div></div>
		</div>
		
		<div>
			<div></div>
			<div>
				<div>卡片点点奖学金</div>
				<div>连续打卡90天，返还用户终身使用费299元。</div>
			</div>
		</div>
		
		
		<jsp:include page="foot.jsp" flush="true"/>
		
		
		
		<ol class="regV" style="display:none">
			<div>
				<div class="hd" style="height: 2.5rem;">
					<div class="i1"></div>
					<div class="i2">您需要支付29元</div>
				</div>
				<div class="bd" style="padding: 1rem 0;height: 3rem;">
					<a class="i7">查看支付结果</a>
					<a class="i8" style="position: relative;">取消支付</a>
				</div>
			</div>
		</ol>
		
		
		
		
		
	</div>
	
	
</body>
</html>