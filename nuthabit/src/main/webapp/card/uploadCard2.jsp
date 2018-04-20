<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String picUrl = null;
if(request.getAttribute("picurl")!=null){
	picUrl=request.getAttribute("picurl").toString();	
}else if(request.getSession().getAttribute("picurl")!=null){
	picUrl=request.getSession().getAttribute("picurl").toString();	
}

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
	        $(".tagspan").click(function(){
	        	console.log(1);
		        $("#cardTag").attr("value",$("#cardTag").attr("value")+" "+$(this).text());
		    })
	    })
        
	 </script>
</head>
<body style="display: block">
<jsp:include page="head.jsp" flush="true"/>
    
<div class="">
	<div style="width: 100%;
    text-align: center;
    background-color: #ef4684;
    color: #fff;
    font-size: 2.0em;
    padding: 10px;">
		第二步 设置文字
	</div>
    <p style="text-align:center;padding-top:10px;">
    <img alt="" src="/<%=picUrl%>" width="80%">
    </p>
    <form action="/card/addbabycard.html" method="post"  data-ajax="false">
    <input type="hidden" name="cardpic" value="<%=picUrl%>" /> 
    <div>
        <div class="inputBox3">
            <div class="title">
            	<span >文字</span>
            	<input type="text" name="meaning"/>
            </div>
            <div class="title">
      			<span >标签</span><input type="text" name="tag" id="cardTag" value=""/>
      		</div>
      		<div class="title">
      			<span class="tagspan">水果</span> <span class="tagspan">数字</span> <span class="tagspan">动物</span> <span class="tagspan">身体</span> <span class="tagspan">人物</span> <span class="tagspan">自然</span> <span class="tagspan">交通</span> <span class="tagspan">家电</span> <span class="tagspan">颜色</span>
      		</div>
        </div>
        <button class="submitBtn" id="signin" style="background:#ed4d83">提交</button>
    </div>
    </form>
    <div class="pageLast"></div>
</div>
</body>
</html>