<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Card c = Card.getStaticCard(Long.parseLong(request.getParameter("cardId")));
Collection cardColl = new CardPicDAO().getCardPicByCardId(c.getCardId());

//切换语言
long languageId = new LanguageHttp().getLanguageId(request);
long languageId_2 = new LanguageHttp().getLanguageId_2(request);

Kehu k = new KehuUtil().getKehu(request, response);

%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数一数</title>
<style type="text/css">
    
</style>
</head>
<body>
	<div class="page">
		<div id="shuyishu">
			<img src="/myplan/upload/historypic/1533891001398.jpg" >	
		</div>
	
	</div>
	
	


	
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	
	$(function(){
		
	})
</script>	
</body>
</html>