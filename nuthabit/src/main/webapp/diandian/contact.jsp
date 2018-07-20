<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
long languageId = new LanguageHttp().getLanguageId(request);
//Kehu k = new KehuUtil().getKehu(request, response);
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
			<div style="background: white;padding: .5rem;margin: 1rem;border-radius: 0.2rem;position: relative;z-index: 2;">
				<h1 style="font-size: .45rem;text-align: center;">联系我们</h1>
				<p style="font-size: .4rem;text-align:center;">
					<img alt="" src="frame/erweima_gzz.jpg" style="width: 5rem;padding-top: 0.2rem;">
					<br/>
					公众号二维码
					<img alt="" src="frame/erweima_adon.jpg" style="width: 5rem;padding-top: 1rem;">
					<br/>
					客服二维码
				</p>
			</div>
			
		</div>
		
		
		
		<jsp:include page="foot.jsp" flush="true"/>
		
		
	</div>
</body>
</html>