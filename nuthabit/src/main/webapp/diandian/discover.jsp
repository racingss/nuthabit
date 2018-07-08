<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
long languageId = new LanguageHttp().getLanguageId(request);
%>
<html data-dpr="1" style="font-size: 36px;"><head>
	<meta charset="utf-8">
	<title>点兵点将</title>
	<link rel="stylesheet" type="text/css" href="css/swiper.min.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/swiper.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script><meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
	<script type="text/javascript">
		$(function(){
			var mySwiper = new Swiper('.swiper-container',{
          		loop: true,
        		autoplay: 3000,
        		pagination: '.swiper-pagination',
        	});

			/* 语言选择 */
			$("#yuyan").click(function(){
				if($(this).hasClass("active")){
					$(this).removeClass("active");
					$(this).next(".language").css("display","none");
				}else {
					$(this).addClass("active");
					$(this).next(".language").css("display","flex");
				}
			})
			$("#sousuo").click(function(){
				if($(this).hasClass("active")){
					$(this).removeClass("active");
					$(".homeSearch").hide();
				}else {
					$(this).addClass("active");
					$(".homeSearch").show();
				}
			})
			$("#quxiao").click(function(){
				$("#sousuo").removeClass("active");
				$(".homeSearch").hide();
			})
			$("#sousuokuang input").focus(function(){
				$(this).next("a").show();
				$("#sousuokuang a").click(function(){
					$("#sousuokuang input").val("");
					$(this).hide();
				})
			})
		})
	</script>
</head>
<body style="font-size: 12px;">
	<div class="page found">
		<ul class="foundtab">
			<div>
				<a href="">推荐</a>
				<a class="active" href="">使用教程</a>
				<a href="">分享</a>
				<a href="">活动</a>
				<a href="">推荐</a>
				<a href="">亲子关系</a>
				<a href="">志愿活动</a>
				<a href="">使用教程</a>				
			</div>
		</ul>
		<div>
			<div class="tip">
				<div>
					<h4>	
						<i></i>
						<span>今日学习</span>
					</h4>
					<ul>
						<li>
							<i></i>
							<p>如何通过卡片点点应用快速教会孩子</p>
						</li>
						<li>
							<i></i>
							<p>升小拿满了四大名校的offer，只因为妈妈从小就 帮孩子养成了这个好习惯</p>
						</li>
						<li>
							<i></i>
							<p>记忆曲线的好用处，卡片点点创始人<br>现身说法</p>
						</li>
					</ul>
				</div>
			</div>
			<div class="box">
				<div class="item1">
					<div class="i1">警惕！如果您的孩子正在做这些 事情，请赶忙停止下来，否则...</div>
					<div class="i2">
						<div><img src="frame/found-6.jpg"></div>
						<div><img src="frame/found-6.jpg"></div>
						<div><img src="frame/found-6.jpg"></div>
					</div>
					<div class="i3">
						<div class="o1">李志伟</div>
						<div class="o3">57分钟前</div>
						<div class="o2">
							<i></i>
							<span>10</span>
						</div>
					</div>
				</div>
				<div class="item2">
					<div class="left">
						<div class="i1">警惕！如果您的孩子正在做这些 事情，请赶忙停止下来，否则...</div>
						<div class="i2">
							<div class="o1">李志伟</div>
							<div class="o3">57分钟前</div>
							<div class="o2">
								<i></i>
								<span>10</span>
							</div>
						</div>
					</div>
					<div class="right">
						<img src="frame/found-6.jpg">
					</div>
				</div>
			</div>
			<a class="more">
				查看更多
			</a>
			<a class="add">
				
			</a>
		</div>
		<footer>
		    <a href="/diandian/">
		        <i></i>
		        <span><%=Menu.getMenu("home", languageId) %></span>
		    </a>
		    <a class="active" href="discover.html">
		        <i></i>
		        <span><%=Menu.getMenu("discover", languageId) %></span>
		    </a>
		    <a href="subscribe.html">
		        <i></i>
		        <span><%=Menu.getMenu("order", languageId) %></span>
		    </a>
		    <a href="parents.html">
		        <i></i>
		        <span><%=Menu.getMenu("parent", languageId) %></span>
		    </a>
		</footer>
	</div>

</body></html>