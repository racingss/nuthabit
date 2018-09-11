<%@page import="java.net.URLDecoder"%>
<%@ page language="java"
	import="com.babycard.dao.*,com.babycard.util.*,java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
long languageId = new LanguageHttp().getLanguageId(request);

Iterator resultIt = ((Collection)request.getAttribute("result")).iterator();
String title=request.getParameter("qString");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title><%=title %>_卡片点点Cardpopo</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<style type="text/css">
.everycard{
	width:96%;
	margin:2%
}
.cardhead{
	width: 90%;
    margin: 5%;
    color: grey;
    display: block;
    /* float: left; */
    text-align: center;
    font-size: 0.6rem;
}
.cardimg,.picimg{
	width:100%;
	border-radius: 0.2rem;
	box-shadow: 0px 0.4rem 0.5rem rgba(0, 0, 0, 0.1);
}
.carddetail{
	width:75%;
	float:left;
}
.pichead, .picselect,picword{
	width:100%;
}
.picdetail{
	width: 21%;
    float: left;
    margin: 1% 0 1% 4%;
}
.picword{
	text-align: center;
    height: 1rem;
    color:grey;
}
</style>
</head>
<body>
	<div class="page home">

		<div>
			<%
			while(resultIt.hasNext()){
				CardPic cp = (CardPic)resultIt.next();
				%>
				<div class="everycard">
					<a href="/card/carddetail.html?cardId=<%=request.getParameter("cardId")%>&copyId=<%=cp.getPicId()%>">
					<div class="cardhead">
						<img alt="" src="<%=cp.getImgurl() %>" class="cardimg cardsub">
						<br/>
						<%
						CardMeaning tempCm =CardMeaning.getStaticCard(cp.getPicId(), languageId);
						out.print(tempCm.getMeaning());
						%>
					</div>
					</a>
					
				</div>
				<%
			}
			%>
		
		</div>

		




	</div>




	<script type="text/javascript">
		$(function(){
			
		})
	</script>
</body>
</html>