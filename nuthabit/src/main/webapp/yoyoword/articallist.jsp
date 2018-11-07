<%@ page language="java" import="adon.word.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Collection articalColl=(Collection)request.getAttribute("articalColl");
%>    
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="/js/jquery-2.1.1.min.js"></script>
<script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
</head>
<body>

<div data-role="page" id="pageone">
  
  <div data-role="header">
  <h1>最新文章</h1>
  </div>
  
  <div data-role="main" class="ui-content">
    <form method="get" action="/yoyoword/index.html">
      <div class="ui-field-contain">
        <input type="search" name="word" id="word" placeholder="搜索内容...">
      </div>
      <input type="submit" data-inline="true" value="search">
    </form> 
    
    
    
    
    <ul data-role="listview" data-autodividers="true" data-inset="true">
  	<%
  	Iterator it = articalColl.iterator();
  	while(it.hasNext()){
  		Artical a = (Artical)it.next();
  		%><div data-role="collapsible" data-collapsed="true">
  			  <h1><%=a.getTitle() %></h1>
  			  <p>
  			  文章长度:<%=a.getTotalNums() %> ,词汇量:<%=a.getWordNums() %> <br/>
  			  <a href="/yoyoword/articallist.html?id=<%=a.getId()%>" data-role="button" data-inline="true">读文章</a>
  			  <a href="/yoyoword/articallist.html?id=<%=a.getId()%>&analysis=t" data-role="button" data-inline="true">分析词汇</a>
  			  <a href="/yoyoword/articallist.html?id=<%=a.getId()%>&delete=t" data-role="button" data-inline="true">删除</a>
  			  </p>
  		  </div>
  	   <%
  	}
  	%>
  	</ul>
    
 </div>
  
  
  
  
	<%@ include file="foot.jsp" %>  
  
  
</div>
</body>