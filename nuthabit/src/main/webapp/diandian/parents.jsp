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
	<title><%=Menu.getMenu("parent", languageId) %></title>
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript">
		$(function(){
			
		})
	</script>
</head>
<body>
	<div class="page parents">
		<div>
			<img class="bg" src="frame/parents-11.png">
			<div class="user">
				<div class="hd">
					<img src="<%=k.getHeadimgurl()%>" style="border-radius: 2px;">
				</div>
				<span><%=k.getNickname() %></span>
			</div>
			<div class="integral">
				<div>
					<div class="hd">
						<i></i>
						<span><%=Menu.getMenu("mypoint", languageId) %></span>
					</div>
					<div class="bd">
						<span><%=new KehuDAO().getJifen(k.getId()) %></span>
						<i></i>
					</div>
					<div class="btn">
						<a href="">
							<img src="frame/parents-3.png">
							<span><%=Menu.getMenu("sharepoint", languageId) %></span>
						</a>
						<a href="/diandian/?jifen=1">
							<img src="frame/parents-4.png">
							<span><%=Menu.getMenu("pushpoint", languageId) %></span>
						</a>
					</div>					
				</div>
			</div>
			<div class="list">
				<a href="subscribe.html">
					<img src="frame/parents-5.png">
					<%
					KehuCardMember m = new KehuDAO().getMember(k.getKehuId());
					%>
					<span>
						<%
						if(m!=null){
							if(m.getMemberLevel()==2){
								%>
								<%=Menu.getMenu("lifelong_member", languageId) %>
								<%
							}else{
								%>
								<%=Menu.getMenu("member_lastdate", languageId) %>
								<%
							}
						}else{
							%>
							<%=Menu.getMenu("order", languageId) %>
							<%
						}
						%>
					</span>
					<p>
					<%
					
					if(m!=null){
						if(m.getMemberLevel()!=2){
						%>
							<%=m.getCloseDate().toString().substring(0,10) %>
						<%} 
					}%>
					</p>
				</a>
				<a href="">
					<img src="frame/parents-6.png">
					<span><%=Menu.getMenu("coupon", languageId) %></span>
					<p><%=k.getCode() %></p>
				</a>
			</div>
			<div class="list">
				<a href="contact.jsp">
					<img src="frame/parents-7.png">
					<span><%=Menu.getMenu("contactus", languageId) %></span>
				</a>
				<a href="terms.jsp">
					<img src="frame/parents-8.png">
					<span><%=Menu.getMenu("userpr", languageId) %></span>
				</a>
			</div>
		</div>
		
		
		
		<jsp:include page="foot.jsp" flush="true"/>
		
		
	</div>
</body>
</html>