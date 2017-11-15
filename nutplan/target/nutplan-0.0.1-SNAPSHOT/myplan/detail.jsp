<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.nuthabit.dao.*,com.nuthabit.util.*,java.util.*" contentType="text/html; charset=UTF-8"
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

  <!--div data-role="header" role="banner" class="ui-header ui-bar-inherit">
  	<h1 class="ui-title" role="heading" aria-level="1"><%=m.getTitle() %>
  	<a href="/myplan/index.html?planId=<%=m.getId()%>&edit=t" data-role="button" data-icon="gear" data-iconpos="notext"></a>
  	</h1>
  </div-->
  
  <div data-role="content">
       
  		
    	<div class="rili">
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
    	
  
  		
  		<div class="rili" style="text-align: center;">
  			<div class="rilititle"><%=new java.text.SimpleDateFormat("yyyy/MM").format(new Date()) %></div>
  			<ul>
  				<li>日</li>
  				<li>一</li>
  				<li>二</li>
  				<li>三</li>
  				<li>四</li>
  				<li>五</li>
  				<li>六</li>
  			</ul>
  			<%
  			for(int i=1;i<=6;i++){
  				%>
  				<ul>
  					<%
  					for(int j=1;j<=7;j++){
  						DataResult r =DataFormatUtil.getDayOfMonth(i, j);
  						long isDo=PlanUtil.isDo(coll, r.formatDate);
  						%>
  						<li  class="dangri<%=isDo %>" ><%
  						if(r.thisMonth){
  							%>
  							<a href="#"><%=r.dayOfMonth %></a>
  							<%
  						}else{
  							%>
  							<span class="notthismonth"><%=r.dayOfMonth %></span>
  							<%
  						}
  						%></li>
  						<%
  					}
  					%>
  				</ul>
  				<%
  			}
  			%>
  		</div>
  		
  		<h2></h2>
  		
  		<%
	    if(m.getPic()!=null){
	    	%>
	    	<div class="rili">
	    	<img alt="" src="upload/planpic/<%=m.getPic()%>" style="width:100%" />
	    	</div>
	    	<h2></h2>
	    	<%
	    }
	    %>
	    
	    <%
  		if(m.getDiscription()!=null){
  		%>
  		<div class="rili" style="text-align:center;font-weight: bolder;">
  		    <div class="rilititle">为什么要坚持做这件事</div>
  			<%=m.getDiscription() %>
  		</div>
  		
  		<h2></h2>
  		<%} %>
  		
  		<%
  		if(coll.size()>0){
  		%>
  		<div class="rili">
  		<div class="rilititle">心得感悟</div>
  			<%
  			Iterator it = coll.iterator();
  			while(it.hasNext()){
  				MyplanHistory mh = (MyplanHistory)it.next();
  				if(mh.getReview()!=null && mh.getReview().trim().length()>0){
  			%>
				<ul>
					<span class="rilidate"><%=mh.getPlanDate() %></span>
					<%=mh.getReview() %>
					<%
					if(mh.getPic1()!=null){
					%>
					<span class="pic"><img src="upload/historypic/<%=mh.getPic1()%>" style="max-width: 95%;" /></span>
					<%} %>
				</ul>
			<%	} 
			}%>
		</div>
		<%} %>
		
		<h2></h2>
		
		<div class="rili" style="text-align:center;">
			<a href="/myplan/index.html?planId=<%=m.getId()%>&edit=t" data-role="button" data-inline="true">修改计划</a>
		</div>
  </div>
  
  <%@ include file="foot.jsp" %>
  
</div>



</body>