<%@ page language="java" import="adon.word.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Artical a = (Artical)request.getAttribute("artical");
Collection articalWordColl = (Collection)request.getAttribute("articalWordColl");

%>    
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="/js/jquery-2.1.1.min.js"></script>
<script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<style>
p{ text-indent:1em; padding:0px; margin-bottom:5px; }
</style>
</head>
<body>

<div data-role="page" id="pageone">
  
  <div data-role="header">
  <h1><%=a.getTitle() %></h1>
  </div>
  
  <div data-role="main" class="ui-content">
    <p><strong><%=a.getTitle() %></strong></p>
    <p>文章长度:<%=a.getTotalNums() %> ,词汇量:<%=a.getWordNums() %> 
    <br/>
    <a href="/yoyoword/articallist.html?id=<%=a.getId()%>" data-role="button" data-inline="true">读文章</a>
    <a href="/yoyoword/articalword.html?id=<%=a.getId()%>" data-role="button" data-inline="true">记单词</a>
    <a href="/yoyoword/articallist.html?id=<%=a.getId()%>&clean=t" data-role="button" data-inline="true">清除简单单词</a>
    
   	</p>
    
    
    <%
  	if(a!=null && articalWordColl!=null){
	  	Iterator it = articalWordColl.iterator();
	  	while(it.hasNext()){
	  		ArticalWord aw = (ArticalWord)it.next();
	  		%><p><a href="/yoyoword/articallist.html?id=<%=a.getId()%>&line=t&word=<%=aw.getWord() %>"><%=aw.getWord() %></a>
	  		（<%=aw.getNums() %>）
	  		<a href="/yoyoword/articallist.html?id=<%=a.getId()%>&deleteword=t&word=<%=aw.getWord() %>">删除</a>
	  		</p><%
	  	}
  	}
  	%>
  	
    
 </div>
  
  
  
  
	<%@ include file="foot.jsp" %>  
  
  
  
  
</div>

  

</body>