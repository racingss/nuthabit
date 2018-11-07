<%@ page language="java" import="adon.word.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Collection mylist=(Collection)request.getAttribute("mylist");
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
  <div data-role="panel" id="myPanel">
  	<h2>WARNING!</h2>
  	<p>您词汇量表中所有单词都会被删除！</p>
  	<a href="/yoyoword/mylist.html?clean=t" class="ui-btn ui-icon-power ui-btn-icon-left " >continue</a>
  	<a href="#pageone" data-rel="close" class="ui-btn ui-icon-back ui-btn-icon-left ">back</a>
  </div>
  
  <div data-role="header">
  <h1>我的词汇表</h1>
  </div>
  
  
  
  <div data-role="main" class="ui-content">
    <form method="get" action="/yoyoword/index.html">
      <div class="ui-field-contain">
        <input type="search" name="word" id="word" placeholder="搜索内容...">
      </div>
      <input type="submit" data-inline="true" value="search">
    </form> 
    
    <%
    if(mylist!=null && mylist.size()>0){
    %>
    
    <a href="/yoyoword/review.html?random=<%=System.currentTimeMillis()%>" class="ui-btn ui-icon-star ui-btn-icon-left " >开始今天的复习!</a>
    
    
    <ul data-role="listview" data-autodividers="true" data-inset="true">
  	<%
  	Iterator it = mylist.iterator();
  	while(it.hasNext()){
  		Myword m = (Myword)it.next();
  		%><li><a href="/yoyoword/index.html?word=<%=m.getWord() %>"><%=m.getWord() %> <span style="color:#F90;font-size:0.8em;float:right">lv: <%=m.getLevel() %></span></a></li><%
  	}
  	%>
  	
  	<a href="#myPanel" class="ui-btn ui-icon-power ui-btn-icon-left " >Clean my list!</a>
    </ul>
    <%}else{ %>
    
    <a href="articallist.html?rand=<%=System.currentTimeMillis() %>" class="ui-btn ui-icon-star ui-btn-icon-left " >词汇表是空的，开始看文章记单词吧!</a>
    
    <%} %>
 </div>
  
  
  
  
	<%@ include file="foot.jsp" %>  
  
  
  
  
</div>

  
  
  
<script>
	$(document).ready(function(){
	  $(".title_word").click(function(){
		 var word = $(this).attr("word");
		 level = $.ajax({url:"/yoyoword/addtomyword.html?word="+word,async:false});
		  $(".processresult_"+word.replace(' ','_')).text('level:'+level.responseText);
		  $(".processresult_"+word.replace(' ','_')).attr('level',+level.responseText);
	  });
	})
</script>


</body>