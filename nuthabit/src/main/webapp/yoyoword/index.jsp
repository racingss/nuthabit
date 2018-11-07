<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="adon.word.*,java.util.*,com.baidu.translate.demo.TransApi" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
String APP_ID = "20170424000045560";
String SECURITY_KEY = "fJIUUFV0D8PhtHo80eGG";
TransApi api = new TransApi(APP_ID, SECURITY_KEY);
String query = "made";
String a = NetUnicode.convertUnicode(api.getTransResult(query, "auto", "zh"));
System.out.println(a);*/
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
  <h1>YOYO's English Dictionary</h1>
  </div>

 <%
  if(request.getAttribute("wordColl")==null){%>
  <div data-role="content">
  <p>Welcome！</p>
  </div>
  <%}%>
  
  <div data-role="main" class="ui-content">
    <form method="get" action="/yoyoword/index.html">
      <div class="ui-field-contain">
        <input type="search" name="word" id="word" placeholder="搜索内容...">
      </div>
      <input type="submit" data-inline="true" value="search">
    </form> 
  </div>
  <%
  long temp=System.currentTimeMillis();
  %>
  <%
  if(request.getAttribute("wordColl")!=null){
	  Collection wordColl = (Collection)request.getAttribute("wordColl");
  %>
  
  <div data-role="main" class="ui-content">
    <h2><%if(request.getParameter("word")!=null)out.print("'"+request.getParameter("word")+"' "); %>result：</h2>
    <ol data-role="listview">
      <%
      int i =0;
      Iterator it = wordColl.iterator();
      while(it.hasNext()){
    	  Wordlist w = (Wordlist)it.next();
    	  int level=0;
	      if(i<2){
	       	level = new MywordDAO().getLevel(new Myword("adon",w.getWord()));  		
	      }
      %>
      <div data-role="collapsible" <%if(i++<1){%>data-collapsed="false"<% } %>>
      	<h1 class="title_word" word="<%=w.getWord() %>"><%=w.getWord() %></h1>
      	<p>English phonetics:<%=w.getEnPh() %></p>
      	
      	<%
      	if(w.getEnPhMp3()!=null){
      	%>
      	<p>
      	<audio controls="controls" id="bgMusic<%=temp%>">  
		   <source src="<%=w.getEnPhMp3() %>" /> 
		</audio>
		</p>
		<%} %>
		<p>American phonetics:<%=w.getAmPh() %></p>
      	
      	<%
      	if(w.getAmPhMp3()!=null){
      	%>
      	<p>
      	<audio controls="controls">  
		   <source src="<%=w.getAmPhMp3() %>" /> 
		</audio>
		</p>
		<%} %>
      	
      	<p>
      	英英解释：<br/>
      	<%=w.getMeaningFormat() %>
      	<br/><br/>
      	英中解释：<br/>
      	<font color="grey">
      	<%
      	if(w.getPos1()!=null){
      		%><%=w.getPos1()%>  <%=w.getAcceptation1()%><br/><%
      	}
      	if(w.getPos2()!=null){
      		%><%=w.getPos2()%>  <%=w.getAcceptation2()%><br/><%
      	}
      	if(w.getPos3()!=null){
      		%><%=w.getPos3()%>  <%=w.getAcceptation3()%><br/><%
      	}
      	if(w.getPos4()!=null){
      		%><%=w.getPos4()%>  <%=w.getAcceptation4()%><br/><%
      	}
      	%>
      	</font>
      	</p>
      	<p>
      	<%
      	Collection lineColl = (Collection)request.getAttribute("articalLineWordColl");
      	if(lineColl.size()>0){
      		%>
      		例句：<br/>
      		<%
      		Iterator lineit = lineColl.iterator();
      		while(lineit.hasNext()){
      			ArticalLine al = (ArticalLine)lineit.next();
      			%>
      			<%=FotmatUtil.getFormat(al.getDetail(), w.getWord()) %> <br/><br/>
      			<%
      		}
      	}
      	%>
      	</p>
      	<div data-role="controlgroup" data-type="horizontal">
	      <a href="#" class="ui-btn ui-corner-all ui-shadow ui-icon-heart ui-btn-icon-notext add_mylist_but" word="<%=w.getWord() %>">Add</a>
	      <a href="#" class="ui-btn ui-corner-all ui-shadow ui-icon-delete ui-btn-icon-notext" word="<%=w.getWord() %>">delete</a>
	      <a href="#" class="ui-btn ui-corner-all ui-shadow ui-icon-arrow-u ui-btn-icon-notext" word="<%=w.getWord() %>">up</a>
	      <a href="#" class="ui-btn ui-corner-all ui-shadow ui-icon-arrow-d ui-btn-icon-notext" word="<%=w.getWord() %>">down</a>
	      <a href="#" class="ui-btn ui-corner-all ui-shadow ui-icon-back ui-btn-icon-notext" data-rel="back">back</a>
	    </div>
      	<p>
      	<a href="#" class="ui-btn ui-icon-star ui-btn-icon-left processresult_<%=w.getWord().replace(" ", "_")%>" word="<%=w.getWord() %>" level="<%=level %>">level:<%=level %>
      	</a>
      	</p>
      </div>
      <%} %>
    </ol>
  </div>
  <%} %>
  <script>
	$(document).ready(function(){
	  $(".add_mylist_but").click(function(){
		  var word = $(this).attr("word").replace(' ','_');
		  level = $.ajax({url:"/yoyoword/addtomyword.html?level=7&word="+word,async:false});
		  $(".processresult_"+word.replace(' ','_')).text('level:'+level.responseText);
		  $(".processresult_"+word.replace(' ','_')).attr('level',+level.responseText);
	  });
	  
	  $(".ui-icon-delete").click(function(){
		  var word = $(this).attr("word").replace(' ','_');
		  level = $.ajax({url:"/yoyoword/addtomyword.html?level=0&word="+word,async:false});
		  $(".processresult_"+word.replace(' ','_')).text('level:'+level.responseText);
		  $(".processresult_"+word.replace(' ','_')).attr('level',+level.responseText);
	  });
	  
	  $(".title_word").click(function(){
		 var word = $(this).attr("word");
		 level = $.ajax({url:"/yoyoword/addtomyword.html?word="+word,async:false});
		  $(".processresult_"+word.replace(' ','_')).text('level:'+level.responseText);
		  $(".processresult_"+word.replace(' ','_')).attr('level',+level.responseText);
	  });
	  
	  $(".ui-icon-arrow-d").click(function(){
		 var word = $(this).attr("word");
		 level = $(".processresult_"+word.replace(' ','_').replace(' ','_')).attr('level');
		 if(level>0){
			 level = level-1;
		 }
	     ajaxResp = $.ajax({url:"/yoyoword/addtomyword.html?level="+level+"&word="+word,async:false});
		 $(".processresult_"+word.replace(' ','_').replace(' ','_')).text('level:'+ajaxResp.responseText);
		 $(".processresult_"+word.replace(' ','_').replace(' ','_')).attr('level',+ajaxResp.responseText);
	  });
	  
	  $(".ui-icon-arrow-u").click(function(){
		 var word = $(this).attr("word");
		 level = $(".processresult_"+word.replace(' ','_').replace(' ','_')).attr('level');
		 if(level<7){
			 level = parseInt(level)+1;
		 }
	     ajaxResp = $.ajax({url:"/yoyoword/addtomyword.html?level="+level+"&word="+word,async:false});
		 $(".processresult_"+word.replace(' ','_').replace(' ','_')).text('level:'+ajaxResp.responseText);
		 $(".processresult_"+word.replace(' ','_').replace(' ','_')).attr('level',+ajaxResp.responseText);
	  });
	})
  </script>
 


  <%@ include file="foot.jsp" %>
  
</div>


<div data-role="page" data-dialog="true" id="pagethree">
  <div data-role="header">
    <h1>Hi!</h1>
  </div>
  

  <div data-role="main" class="ui-content">
    <p>Thanks for using ^-^ </p>
    <p>My WeChat: wangadon</p>
    <a href="#pageone">close</a>
  </div>
  
</div> 


</body>