<%@ page language="java" import="adon.word.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//Map map = (HashMap)request.getAttribute("reviewMap");
Myword m = null;
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
  <h1>Review</h1>
  </div>

 <div data-role="content">
  <p>You need review <%=request.getAttribute("reviewNums").toString() %> words today.</p>
 </div>
 <%
 if(request.getAttribute("reviewWord")!=null){
	 m = (Myword)request.getAttribute("reviewWord");
 %>
 <div data-role="main" class="ui-content">
    <div data-role="collapsible" data-collapsed-icon="arrow-d" data-expanded-icon="arrow-u">
      <h1 class="title_word" word="<%=m.getWord() %>"><%=m.getWord() %><span style="float:right">level:<%=m.getLevel() %></span></h1>
      <%
      Wordlist w = new WordlistDAO().getWord(m.getWord());
      if(w!=null){
      %>
      <p>English phonetics:<%=w.getEnPh() %></p>
      	<p>
      	<audio controls="controls">  
		   <source src="<%=w.getEnPhMp3() %>" /> 
		</audio>
		</p>
      	<p>American phonetics:<%=w.getAmPh() %></p>
      	<p>
      	<audio controls="controls">  
		   <source src="<%=w.getAmPhMp3() %>" /> 
		</audio>
		</p>
      <p><%=w.getMeaningFormat() %></p>
      <%} %>
    </div>
    
    <div data-role="controlgroup" data-type="horizontal">
	    <a href="#" class="ui-btn ui-corner-all ui-shadow ui-icon-check ui-btn-icon-notext" word="<%=m.getWord() %>">对</a>
	    <a href="#" class="ui-btn ui-corner-all ui-shadow ui-icon-delete ui-btn-icon-notext" word="<%=m.getWord() %>">错</a>
	    <a href="#" class="ui-btn ui-corner-all ui-shadow ui-icon-forward ui-btn-icon-notext" word="<%=m.getWord() %>">跳过</a>
	  </div>
  </div>
  <%} %>
  
  
   <form action="/yoyoword/review.html" method="post" id="testform">
  	<input type="hidden" name="word" value="" id="wordinput" />
  	<input type="hidden" name="review" value="passed" id="processinput" />
  </form>
  
  
  <script>
	$(document).ready(function(){
	  $(".ui-icon-check").click(function(){
		  var word = $(this).attr("word").replace(' ','_');
		  $("#wordinput").attr("value",word);
		  $("#processinput").attr("value","passed");
		  $("#testform").submit();
		  
	  });
	  $(".ui-icon-delete").click(function(){
		  var word = $(this).attr("word").replace(' ','_');
		  $("#wordinput").attr("value",word);
		  $("#processinput").attr("value","nopass");
		  $("#testform").submit();
	  });
	  $(".ui-icon-forward").click(function(){
		  var word = $(this).attr("word").replace(' ','_');
		  $("#wordinput").attr("value",word);
		  $("#processinput").attr("value","skip");
		  $("#testform").submit();
	  });
	})
  </script>
  
  
  <%@ include file="foot.jsp" %>
  
  
  
</div>



  
</body>