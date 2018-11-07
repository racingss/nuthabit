<%@ page language="java" import="adon.word.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Artical a = (Artical)request.getAttribute("artical");
%>    
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
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
    <p>
    文章长度:<%=a.getTotalNums() %> ,词汇量:<%=a.getWordNums() %> <br/>
    <a href="/yoyoword/articallist.html?id=<%=a.getId()%>&analysis=t" data-role="button" data-inline="true">分析词汇</a>
    </p>
    
    
    <%
  	if(a!=null && a.articalLineColl!=null){
	  	Iterator it = a.articalLineColl.iterator();
	  	while(it.hasNext()){
	  		ArticalLine l = (ArticalLine)it.next();
	  		%><p><%=FotmatUtil.getFormat(l.getDetail()) %></p><%
	  	}
  	}
  	%>
  	
    
 </div>
  
  
  
  
	<%@ include file="foot.jsp" %>  
  
  
  
  
</div>


<div data-role="page" id="pagetwo">
  <div data-role="header">
    <h1 id="pagetwoh1">我是一个对话框！</h1>
  </div>

  <div data-role="content">
    <p id="pagetowp">
    	English phonetics:
      	<audio controls="controls">  
		   <source src=""/> 
		</audio>
		American phonetics:
      	<audio controls="controls">  
		   <source src=""/> 
		</audio>
    <p>
    <a href="#pageone">返回</a>
  </div>

  <div data-role="footer">
  <h1>词义解释</h1>
  </div>
</div>

<script>
	$(document).ready(function(){
	  $(".wordpress").click(function(){
		  word = $(this).attr("word");
		  console.log(word);
		  $("#pagetwoh1").text(word);
		  ajaxResp = $.ajax({url:'wordajax.html?word='+word,async:false});
		  $("#pagetowp").text(ajaxResp.responseText);
	  });
	})
</script>

</body>