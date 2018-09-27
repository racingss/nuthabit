<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
long languageId = new LanguageHttp().getLanguageId(request);
Kehu k = new KehuUtil().getKehu(request, response);
int max=5;
int curr = new KehuDAO().getFromlist(k.getId()).size();
if(curr>5)
	max=0;
else
	max=max- curr;
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
							<%
							KehuCardMember m = new KehuDAO().getMember(k.getKehuId());
							if(m==null || m.getMemberLevel()!=m.MEMBER_LEVEL_LIFELONG){
							%>
							<a href="/diandian/order.html?amount=299&level=<%=KehuCardMember.MEMBER_LEVEL_LIFELONG%>"><%=Menu.getMenu("order_now", languageId) %></a>
							<%}else{ %>
							<a href="#">已兑换</a>
							<%} %>
						</li>
						<li>
							<i></i>
							<span><%=Menu.getMenu("order_year", languageId) %></span>
							<div>
								<p><span>19</span>元<!--i class="jianguo"></i--></p>	
								<i><%=Menu.getMenu("order_month", languageId) %></i>
							</div>
							<%
							if(m==null || m.getMemberLevel()!=m.MEMBER_LEVEL_LIFELONG){
							%>
							<a href="/diandian/order.html?amount=228&level=<%=KehuCardMember.MEMBER_LEVEL_YEAR%>"><%=Menu.getMenu("order_now", languageId) %></a>
							<%}else{ %>
							<a href="#">已兑换</a>
							<%} %>
							
						</li>
						<li>
							<i></i>
							<span><%=Menu.getMenu("order_monthly", languageId) %></span>
							<div>
								<p><span>29</span>元<!--i class="jianguo"></i--></p>	
								<i><%=Menu.getMenu("order_month", languageId) %></i>
							</div>
							<%
							if(m==null || m.getMemberLevel()!=m.MEMBER_LEVEL_LIFELONG){
							%>
							<a href="/diandian/order.html?amount=29&level=<%=KehuCardMember.MEMBER_LEVEL_MONTH%>"><%=Menu.getMenu("order_now", languageId) %></a>
							<%}else{ %>
							<a href="#">已兑换</a>
							<%} %>
							
						</li>
					</ul>
				</div>
			</div>
			<!--div class="balance">
				<div>
					<p><%=Menu.getMenu("order_curr", languageId) %></p>
					<div>
						<p><span><%=new KehuDAO().getJifen(k.getId()) %></span><%=Menu.getMenu("jifen", languageId) %></p>
						<a href=""><%=Menu.getMenu("chongzhi", languageId) %></a>
					</div>					
				</div>
			</div>
			<div class="other">
				<a href="parents.html"><%=Menu.getMenu("othergetway", languageId) %></a>
			</div-->
		</div>
		
		
		
		<ol class="regV" style="">
			<div>
				<div class="hd">
					<div class="i1"></div>
					<div class="i2">
						299元终身会员
					</div>
					<div class="i3">还需推荐<span><%=max %>个</span>朋友</div>
				</div>
				<div class="bd" style="height: 5rem;float: left;">
					<div style="text-align:center;">
							<div class="recently">
									<div style="padding: 0;display: block;width: 86%;white-space: normal;margin-left: 7%;margin-top: 10%;font-size: 0.4rem;text-indent: 0.6rem;text-align: left;">
										卡片点点活动期间，您只要推荐5个朋友关注，就可以<font color="red">免费</font>获取299元的终身会员资格啦！
										<br/>
										<span class="showlist" style="float: right;font-size: 0.4rem;padding-right: 0.3rem;color: #7ad125;">查看进展</span>
										<script type="text/javascript">
										$(".showlist").click(function(){
											location.href="/diandian/showsubscribe.html";
											return;
										})
										</script>
											
									</div>
								</div>						

					</div>
					
					<div style="text-align: center;margin: 0.5rem;">
					<a class="i7" id="subi7" style="text-align: center;margin-top: 1rem;" href="/diandian/poster.html">生成海报</a>
					<a class="i8" style="bottom: 0.5rem;">关闭</a>
					
				</div>
			</div>
		</ol>
		<script type="text/javascript">
		$(function(){
			
			$(".regV .i7,.regV .i8").click(function(){
				$(".regV").hide();
			})
			
			

		})
	</script>	
			
		
		
		<jsp:include page="foot.jsp" flush="true"/>
		
		
		
	</div>
</body>
</html>