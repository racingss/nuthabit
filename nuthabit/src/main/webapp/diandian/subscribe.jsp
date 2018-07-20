<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
long languageId = new LanguageHttp().getLanguageId(request);
Kehu k = new KehuUtil().getKehu(request, response);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title><%=Menu.getTitle(languageId) %></title>
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
				<p><%=Menu.getMenu("order_1", languageId) %></p>
				<p><%=Menu.getMenu("order_2", languageId) %></p>
			</div>
			<div class="vip">
				<div class="left">
					<img src="frame/subscribe-1.png">
				</div>
				<ul class="right">
					<li>
						<i></i>
						<p><%=Menu.getMenu("order_3", languageId) %></p>
					</li>
					<li>
						<i></i>
						<p><%=Menu.getMenu("order_4", languageId) %></p>
					</li>
					<li>
						<i></i>
						<p><%=Menu.getMenu("order_5", languageId) %></p>
					</li>
				</ul>
			</div>
			<div class="list">
				<div>
					<ul>
						<li class="i1">
							<i></i>
							<span><%=Menu.getMenu("order_lifelong", languageId) %></span>
							<div>
								<p><span>299</span>元<!--i class="jianguo"></i--></p>	
								<i><%=Menu.getMenu("order_onetime", languageId) %></i>
							</div>
							<a href="/diandian/order.html?amount=299&level=<%=KehuCardMember.MEMBER_LEVEL_LIFELONG%>"><%=Menu.getMenu("order_now", languageId) %></a>
						</li>
						<li>
							<i></i>
							<span><%=Menu.getMenu("order_year", languageId) %></span>
							<div>
								<p><span>19</span>元<!--i class="jianguo"></i--></p>	
								<i><%=Menu.getMenu("order_month", languageId) %></i>
							</div>
							<a href="/diandian/order.html?amount=228&level=<%=KehuCardMember.MEMBER_LEVEL_YEAR%>"><%=Menu.getMenu("order_now", languageId) %></a>
						</li>
						<li>
							<i></i>
							<span><%=Menu.getMenu("order_monthly", languageId) %></span>
							<div>
								<p><span>29</span>元<!--i class="jianguo"></i--></p>	
								<i><%=Menu.getMenu("order_month", languageId) %></i>
							</div>
							<a href="/diandian/order.html?amount=29&level=<%=KehuCardMember.MEMBER_LEVEL_MONTH%>"><%=Menu.getMenu("order_now", languageId) %></a>
						</li>
					</ul>
				</div>
			</div>
			<div class="balance">
				<div>
					<p><%=Menu.getMenu("order_curr", languageId) %></p>
					<div>
						<p><span><%=new KehuDAO().getJifen(k.getId()) %></span><%=Menu.getMenu("order_gejianguo", languageId) %></p>
						<a href=""><%=Menu.getMenu("chongzhi", languageId) %></a>
					</div>					
				</div>
			</div>
			<div class="other">
				<a href="parents.html"><%=Menu.getMenu("othergetway", languageId) %></a>
			</div>
		</div>
		
		
		<jsp:include page="foot.jsp" flush="true"/>
		
		
		
	</div>
</body>
</html>