<%@ page language="java" import="adon.word.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Collection wordColl = (Collection)request.getAttribute("wordColl");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="/js/jquery-2.1.1.min.js"></script>
<script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
</head>
<body>

<div data-role="page" id="pageone">
  <div data-role="header">
  <h1>本次默写还有<%=wordColl.size() %>个</h1>
  </div>

 

 <div data-role="main" class="ui-content">
    <%
    
    Iterator wordIt = wordColl.iterator();
    int i=0;
    if(wordIt.hasNext()){
    	String word = wordIt.next().toString();
    	i++;
    %>
	    <div data-role="collapsible" data-collapsed-icon="arrow-d" data-expanded-icon="arrow-u" id="<%=i%>_bar">
	      <h1 class="title_word" word="<%=word %>"><%=word %></h1>
	    </div>
	    
	    <div data-role="controlgroup" data-type="horizontal" id="<%=i%>_process">
	    	<form action="/yoyoword/test.html" method="post" >
			  	<input type="hidden" name="word" value="<%=word %>" id="wordinput" />
			  	<input type="hidden" name="process" value="passed" id="processinput" />
			  	<input type="submit" value="正确">
			</form>
			<form action="/yoyoword/test.html" method="post" >
			  	<input type="hidden" name="word" value="<%=word %>" id="wordinput" />
			  	<input type="hidden" name="process" value="nopass" id="processinput" />
			  	<input type="submit" value="错误">
			</form>
			<form action="/yoyoword/test.html" method="post" >
			  	<input type="hidden" name="word" value="<%=word %>" id="wordinput" />
			  	<input type="hidden" name="process" value="skip" id="processinput" />
			  	<input type="submit" value="跳过">
			</form>
		</div>
	<%} %>
	  
  </div>
  
  <form action="/yoyoword/test.html" method="post" id="testform">
  	<input type="hidden" name="word" value="" id="wordinput" />
  	<input type="hidden" name="process" value="passed" id="processinput" />
  </form>
  
  
  <script>
	$(document).ready(function(){
	  $(".ui-icon-check").click(function(){
		  var word = $(this).attr("word").replace(' ','_');
		  $("#processinput").attr("value","passed");
		  document.getElementById("testform").submit();
		  alert(4);
	  });
	  $(".ui-icon-delete").click(function(){
		  var word = $(this).attr("word").replace(' ','_');
		  var index =  $(this).attr("index");
		  $("#processinput").attr("value","nopass");
		  document.getElementById("testform").submit();
	  });
	})
  </script>
  
  
  
  
 
  
  
  
  
  
  <%@ include file="foot.jsp" %>
  
</div>
</body>