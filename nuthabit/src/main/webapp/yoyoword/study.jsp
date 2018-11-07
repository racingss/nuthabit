<%@ page language="java" import="adon.word.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String word = null;
if(request.getAttribute("newWord")!=null)
	word = request.getAttribute("newWord").toString();
Wordlist w = null;
if(word!=null)
 	w = new WordlistDAO().getWord(word);
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
    <h2>Your book</h2>
    <%
    if(request.getAttribute("plan")!=null){
  	  Plan p = (Plan)request.getAttribute("plan");
  	  Book b =new BookDAO().get(p.getBookId());
  	  %>
  	  <p>
	  	  Your study plan of book《<%=b.getBookName() %>》
	  	  <br/> 
	  	  <br/>
	  	  Plan: <%=p.getPlanCount() %> words every day
	  	  <br/> 
	  	  Totol words:<%=b.getWordcount() %>
	  	  <br/>
	  	  Current:<%=p.getBookIndex()%>
	  	  <br/>
	  	  <form method="get" action="/yoyoword/plan.html">
		  	  <input type="hidden" name="bookId" value="<%=p.getBookId()%>"/>
		  	  <input type="hidden" name="modify" value="<%=p.getBookId()%>"/>
			  <label for="points">
			  <input type="range" name="planCount" id="planCount" value="<%=p.getPlanCount()%>" min="0" max="100" data-popup-enabled="true" data-highlight="true">
			  <input type="submit" data-inline="true" value="modify">
			  <input type="button" data-rel="close"  data-inline="true" value="back">
		  </form>
	  </p>
	  	
  	  
  	  
  	  
  	  <%
    }
    %>
  </div> 
  
  <div data-role="header">
  <h1>Study</h1>
  </div>

 
 <%
 if(word!=null){
 %>
 <div data-role="main" class="ui-content">
    <div data-role="collapsible" data-collapsed-icon="arrow-d" data-expanded-icon="arrow-u">
      <h1 class="title_word" word="<%=word %>"><%=word %></h1>
      <%
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
	    <a href="/yoyoword/study.html?word=<%=word %>&process=passed&timeline=<%=System.currentTimeMillis() %>" class="ui-btn ui-corner-all ui-shadow ui-icon-check ui-btn-icon-notext" word="<%=word %>">yes</a>
	    <a href="/yoyoword/study.html?word=<%=word %>&process=nopass&timeline=<%=System.currentTimeMillis() %>" class="ui-btn ui-corner-all ui-shadow ui-icon-delete ui-btn-icon-notext" word="<%=word %>">no</a>
	  </div>
  </div>
  <%} %>
  
  
  
  <div data-role="content">
  	  <p>You need learn <%
  	  if(Integer.parseInt(request.getAttribute("planCount").toString())>0)
  		  out.print(Integer.parseInt(request.getAttribute("planCount").toString()));
  	  else if(Integer.parseInt(request.getAttribute("tempAddCount").toString())>=0)
  		  out.print(Integer.parseInt(request.getAttribute("tempAddCount").toString()));
  	  
  	  %> new words today.<br/>
	  <a href="#myPanel">See my Plan.</a>
	  </p>
  
  <%
  if(Integer.parseInt(request.getAttribute("planCount").toString())<=0 && Integer.parseInt(request.getAttribute("tempAddCount").toString())==0){
	  %>
	<form method="post" action="/yoyoword/study.html">
	  <p>
	  Do you want to study more words today day?</label>
	  <input type="range" name="tempAddCount" id="tempAddCount" value="10" min="0" max="100" data-popup-enabled="true" data-highlight="true">
	  <input type="submit" data-inline="true" value="start">
	  </p>
	</form>	
	 <%
  }
  %>
 </div>
  
  
  
  
  
  <%@ include file="foot.jsp" %>
  
</div>
</body>