<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.nuthabit.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Collection coll = (Collection)request.getAttribute("detailColl");
%>    
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>每日计划</title>
<link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<link rel="stylesheet" href="plan.css">
<script src="/js/jquery-2.1.1.min.js"></script>
<script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
</head>
<body>

<div data-role="page" id="pageone">
  <div data-role="header" role="banner" class="ui-header ui-bar-inherit">
  	<h1 class="ui-title" role="heading" aria-level="1">发现</h1>
  </div>
  
  <div data-role="content">
        
    	
  		
  		<div class="rili">
  			<%
  			Iterator it = coll.iterator();
  			while(it.hasNext()){
  				MyplanHistory mh = (MyplanHistory)it.next();
  				if(mh.getReview()!=null && mh.getReview().trim().length()>0){
  			%>
				<ul>
					<span class="rilidate">Adon:</span>
					<%=mh.getReview() %>
					<br><%=mh.getPlanDate() %>
				</ul>
			<%	} 
			}%>
		</div>
		
  </div>
  
  <%@ include file="foot.jsp" %>
  
</div>

<div data-role="page" id="pagetwo">
  <div data-role="header">
    <h1>我是一个对话框！</h1>
  </div>

  <div data-role="content">
    <p>对话框与普通页面不同，它显示在当前页面的顶端。它不会横跨整个页面宽度。对话框页眉中的图标 “X” 可关闭对话框。</p>
    <a href="#pageone">转到页面一</a>
  </div>

  <div data-role="footer">
  <h1>页脚文本</h1>
  </div>
</div> 

</body>