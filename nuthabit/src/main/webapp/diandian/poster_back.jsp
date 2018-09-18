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
	<title>您的专属推广海报</title>
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript">
		$(function(){
			
		})
	</script>
</head>
<body>
	<img alt="" src="/diandian/poster/save/2749.jpg" style="width: 100%;position: absolute;z-index: 99999999;top: 20%;">
	<div class="page parents">
		<div>
			<img class="bg" src="frame/parents-11.png">
			<div style="background: white;padding: .5rem;margin: 1rem;border-radius: 0.2rem;position: relative;z-index: 2;">
				<h1 style="font-size: .45rem;text-align: center;">您的专属推广海报</h1>
				<p style="font-size: .4rem;text-align:center;">
					
					
				</p>
			</div>
			
		</div>
		
		
		
		<jsp:include page="foot.jsp" flush="true"/>
		
		
	</div>
</body>
</html>