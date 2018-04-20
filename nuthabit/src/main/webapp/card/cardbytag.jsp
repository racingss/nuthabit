<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Collection cardColl = (Collection)request.getAttribute("cardColl");
%>    
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta charset="utf-8">
    <title>看图识物</title>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/card.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/main.js"></script>
    <script src="js/util.js"></script>
    <script type="text/javascript">
	    $(function(){
	        $("body").height($(window).height());
	        $(".submitBtn").click(function(){
	        	window.location.href='/card/cardlist.html?tagId=<%=request.getParameter("tagId")%>';
	        })
	    })
	 </script>
	 <style type="text/css">
	 .meaninglist{
	 	text-align: center;
	    background: #97d9e6;
	    /* height: 35px; */
	    padding-top: 10px;
	    font-size: 35px;
	    color:white;
	 }
	 .cardlist{
	 	height: 170px;
	 }
	 .updiv{
	 	height: 120px;
	 	line-height: 120px;
	 }
	 .downdiv{
	 	height: 30px;
	 }
	 .cardimg{
	 	width:90%;
	 	display: inline-block;
    	vertical-align: middle
	 }
	 </style>
</head>
<body style="display: block;background: #f3f6f9;">
<jsp:include page="head.jsp" flush="true"/>
    
<button class="submitBtn" id="signin" style="background: #96d9e6;">开始学习</button>

<%
Iterator it = cardColl.iterator();
while(it.hasNext()){
	Card c = (Card)it.next();
	%>
	<div class="page paleBlue" style="float: left;
    width: 50%;">
	    <div class="sdPlan meaninglist cardlist" style="font-size: 25px;">
	    	<div class="updiv">
	    		<a href="cardlist.html?static=t&cardId=<%=c.getCardId()%>&tagId=<%=request.getParameter("tagId")%>"><img src="<%=c.getImg() %>" class="cardimg"></a>
	    	</div>
	    	<div class="downdiv">
	    	<input name="cardId" type="checkbox" value="<%=c.getCardId()%>" checked="checked" />
	    	<%=c.getMeaning() %></div>
	    </div>
	</div>
	<%
}
%>

<button class="submitBtn" id="signin" style="background: #96d9e6;">开始学习</button>


</body>
</html>