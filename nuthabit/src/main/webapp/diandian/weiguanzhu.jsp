<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>请关注我们的公众号</title>
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript">
		$(function(){

		})
	</script>
	<style type="text/css">
	.jianguo{
		width: .6rem;
	    height: .6rem;
	    display: inline-block;
	    background-image: url(/diandian/frame/parents-2.png);
	    background-size: cover;
	    margin-left: .1rem;
	}
	</style>
</head>
<body>
	<div class="page subscribe">
		<div>
			<div class="tip"> 
				<p>请先关注公众号 卡片点点</p>
			</div>
			<div class="vip">
				<div class="left">
					<img src="frame/subscribe-1.png">
				</div>
				<ul class="right">
					<li>
						<i></i>
						<p>每周有海量新书上线</p>
					</li>
					<li>
						<i></i>
						<p>超多积分兑换礼品</p>
					</li>
					<li>
						<i></i>
						<p>育儿资源交换互助群</p>
					</li>
				</ul>
			</div>
			<div class="list" style="text-align: center;">
				<img alt="" src="/diandian/frame/erweima_gzz.jpg" style="width:60%">
			</div>
			
		</div>
		
		
		<jsp:include page="foot.jsp" flush="true"/>
		
		
		
	</div>
</body>
</html>