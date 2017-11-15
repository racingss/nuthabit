<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.nuthabit.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Myplan m = (Myplan)request.getAttribute("plan");
Collection coll = (Collection)request.getAttribute("detailColl");
%>    
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>每日计划</title>
<link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<link rel="stylesheet" href="plan.css">
<script src="/js/jquery-2.1.1.min.js"></script>
<script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
</head>
<body>

<div data-role="page" id="pageone">
  <div class="pagehead" style="background-color: #f5ef40;
    border-color: #eeff60;color: #949494;">
  		<span><%=m.getTitle() %></span>
  </div>

  
  
  <div data-role="content">
       
  		
    	<div class="daka">
    		<form action="/myplan/daka.html" method="post" enctype="multipart/form-data"  data-ajax="false">
    			  <input type="hidden" name="processadd" value="t" />
			      <input type="hidden" name="id" value="<%=m.getId() %>" />
			      <input type="hidden" name="planDate" value="<%=new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date()) %>" />
			      <div class="today">
			      	<%=new java.text.SimpleDateFormat("yyyy年MM月dd日").format(new Date()) %> 已连续打卡<%=m.getContinued() %>次
			      </div>
			       <%
			        if(request.getParameter("daka")!=null){
			        %>
			      <div data-role="fieldcontain">
				    <label for="review">心得体会:(可空)</label>
				    <textarea name="review" id="review"></textarea>
				    <br/>
				    <label for="status">是否完成</label>
			        <select name="status" id="status">
						<option value="0">全部完成</option>
			         	<option value="1">部分完成</option>
			         	<option value="2">今日放弃</option>
					</select>
					<br/>
				    <label for="pic" id="picinfo">照片:</label>
				    <input type="file" id="pic1" name="pic1">
				    <input type="file" id="pic2" name="pic2">
				    <input type="file" id="pic3" name="pic3">
				    <br/>
				  </div>
				  <div style="text-align: center;">
				  <input type="submit" data-inline="true" value="打卡">
				    
				  </div>
				  <%} %>
    		</form>
    	</div>
    	
  
  		
  		
  </div>
  
  <%@ include file="foot.jsp" %>
  
</div>



</body>