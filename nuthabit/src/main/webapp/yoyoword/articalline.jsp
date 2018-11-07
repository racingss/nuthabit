<%@ page language="java" import="adon.word.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  <h1><%=request.getAttribute("word") %></h1>
  </div>
  
  <div data-role="main" class="ui-content">
    
    <%
  	Collection coll = (Collection)request.getAttribute("articalLineWordColl");
  	Iterator it = coll.iterator();
  	while(it.hasNext()){
  		ArticalLine l = (ArticalLine)it.next();
  		%><p><%=FotmatUtil.getFormat(l.getDetail(),request.getAttribute("word").toString()) %></p><%
  	}
  	
  	%>
  	
    
 </div>
  
  
  
  
	<%@ include file="foot.jsp" %>  
  
  
  
  
</div>

  

</body>