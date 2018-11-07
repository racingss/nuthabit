<%@page import="java.net.URLEncoder"%>
<%@ page language="java" import="adon.word.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Collection bookColl=(Collection)request.getAttribute("bookColl");
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
     <%
      if(true){
	      Iterator it = bookColl.iterator();
	      while(it.hasNext()){
	    	  Book b = (Book)it.next();
	      %>
		  <div data-role="panel" id="myPanel<%=b.getId()%>">
			  <h2>My study plan</h2>
			  <p>
			  	<form method="post" action="/yoyoword/plan.html">
			  	  <input type="hidden" name="bookId" value="<%=b.getId()%>"/>
				  <label for="points">
				  《<%=b.getBookName() %>》<br/>
				  How many words do you want to study from this book every day?</label>
				  <input type="range" name="planCount" id="planCount" value="10" min="0" max="100" data-popup-enabled="true" data-highlight="true">
				  <input type="submit" data-inline="true" value="start">
				  <input type="button" data-rel="close"  data-inline="true" value="back">
				</form>	
			  </p>
		  </div>
		  <%}
      }%>
      
  <%
  if(request.getAttribute("msg")!=null){
	  %>
	  <a href="#myPopup" data-rel="popup" class="ui-btn ui-btn-inline ui-corner-all msgrequest" style="display:hidden">显示弹窗</a>

		<div data-role="popup" id="myPopup" data-overlay-theme="b">
		  
		  <div data-role="main" class="ui-content">
	        <h2>操作结果!</h2>
	        <p><%=request.getAttribute("msg").toString() %></p>
	        <a href="#" class="ui-btn ui-corner-all ui-shadow" data-rel="back">返回</a>
	      </div>
		</div>
		<script>
			$(document).ready(function(){
			  $(".msgrequest").trigger("click");
			  $(".msgrequest").css({"display":"none"});
			})
		</script>
	  <%
  }
  %>

  <div data-role="main" class="ui-content">
  
    <h2>My Book list:</h2>
    <%
    if(true){
    	int i=0;
    	Iterator myIt = ((Collection)request.getAttribute("mybookColl")).iterator();
    	while(myIt.hasNext()){
    		Book b = (Book)myIt.next();
    		%>
    		<div data-role="collapsible" <%if(i++<1){%>data-collapsed="false"<% } %>>
				<h1><%=b.getBookName() %></h1>
				<p> 
				    词汇量：<%=b.getWordcount() %><br/>
					
					<!--a href="#myPanel<%=b.getId()%>" data-role="button" data-inline="true">自建学习计划</a-->
				</p>
				<div data-role="collapsible">
				<h1>开始学习</h1>
				<p>
				<%
				Iterator bookkIt = b.getBookke();
				Collection keHistoryColl = (Collection)request.getAttribute("keHistoryColl");
				while(bookkIt.hasNext()){
					Bookke kk = (Bookke)bookkIt.next();
				%>
					<a href="/yoyoword/test.html?bookId=<%=b.getId() %>&kId=<%=kk.getId() %>&rand=<%=System.currentTimeMillis() %>" data-role="button" data-inline="true"
					<%if(kk.isStuay(keHistoryColl)){%> style="color:red" <%} %>
					><%=kk.getKe() %></a>
				<%} %>
				</p>
				
				</div>
				<a href="/yoyoword/review.html?bId=<%=b.getId()%>&rand=<%=System.currentTimeMillis() %>" data-role="button" data-inline="true">开始复习</a>
				<a href="/yoyoword/book.html?delete=t&bookId=<%=b.getId() %>&rand=<%=System.currentTimeMillis() %>" data-role="button" data-inline="true">删除本书</a>
				
							    
			</div>
			<%
    	}%>
    	<div data-role="collapsible">
			<h1>Add Book</h1>
			<p>
			<%
		    if(true){
		    	Iterator myIt2 = ((Collection)request.getAttribute("bookColl")).iterator();
		    	while(myIt2.hasNext()){
		    		Book b2 = (Book)myIt2.next();
		    		%>
		    		<div data-role="collapsible">
						<h1><%=b2.getBookName() %></h1>
						<p> 
						    词汇量：<%=b2.getWordcount() %><br/>
							<a href="/yoyoword/book.html?add=t&bookId=<%=b2.getId() %>&rand=<%=System.currentTimeMillis() %>" data-role="button" data-inline="true">收藏</a>
							
						</p>
					</div>
					<%
		    	}
		    } %>
		    </p>
	    </div>
    	
    	<%
    } %>
	
	
    
  </div>
  
  <%@ include file="foot.jsp" %>
    
</div>

</body>