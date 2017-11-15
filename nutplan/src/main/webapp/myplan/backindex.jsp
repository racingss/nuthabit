<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.nuthabit.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Collection plancoll = (Collection)request.getAttribute("plancoll");
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
<script type="text/javascript">
function editPlan(planId){
	window.location.href='index.html?edit=t&planId='+planId;
	return;
}
function deletePlan(planId){
	window.location.href='index.html?delete=t&planId='+planId;
	return;
}
</script>
<style type="text/css">

</style>
</head>
<body>
<div data-role="page" id="pageone">
  <div data-role="header" role="banner" class="ui-header ui-bar-inherit">
  	<img alt="" src="images/head.png" style="width:100%;">
  </div>
  
  <!--div class="pagehead">
  		<span>每日计划</span>
  		<span>
  		<a href="/myplan/index.html?pageadd=t&rand=<%=System.currentTimeMillis() %>"><img src="images/plus1.png" class="plusimg" /></a>
  		</span>
  </div-->
  
  <div data-role="content">
    <div data-role="collapsible-set">
  	<%
  	if(plancoll!=null && plancoll.size()>0 && request.getParameter("pageadd")==null&& request.getParameter("edit")==null){
		Iterator it = plancoll.iterator();
	%>
	    <h3 style="background-color: #ff6161;">待完成</h3>
	     <ul data-role="listview" data-inset="true">
	      <%
	      while(it.hasNext()){
	    	Myplan m = (Myplan)it.next();
	    	if(m.isToday()){
	      %>
	      <li><a href="detail.html?id=<%=m.getId()%>&daka=t"><%=m.getTitle() %><%if(m.getTimes()!=null && m.getTimes().trim().length()>0){ %><span class="ui-li-count"><%=m.getTimes() %></span><%} %></a></li>
	      <%
	        }
	      } 
	      %>
	      </ul>
	      
	    
	    
    <%
        it = plancoll.iterator();
    %>
    <h3 style="background-color: #1ef186;">已完成</h3>
    <ul data-role="listview" data-inset="true">
         <%
	      while(it.hasNext()){
	    	Myplan m = (Myplan)it.next();
	    	if(!m.isToday()){
	      %>
	      	<li><a href="detail.html?id=<%=m.getId()%>"><%=m.getTitle() %><span class="ui-li-count">连续打卡<%=m.getContinued() %>次</span></a></li>
	      <%
	        }
	      } 
	      %>
    </ul>
    
    <div class="rili" style="text-align:center;">
		<a href="/myplan/index.html?pageadd=t&rand=<%=System.currentTimeMillis() %>" data-icon="plus" data-role="button" data-inline="true">添加计划</a>
	</div>
    <%}else if(request.getParameter("pageadd")!=null){%>
    <h2>添加一个计划</h2>
    <form enctype="multipart/form-data" method="post" action="/myplan/addplan.html" data-ajax="false">
      <input type="hidden" name="add" value="t" />
      <div data-role="fieldcontain">
	    <label for="info" id="infoinfo">起个计划标题＊:</label>
	    <textarea name="title" id="info"></textarea>
	    <br/>
	    <label for="discription" id="discriptioninfo">可描述下计划:</label>
	    <textarea name="discription" id="discription"></textarea>
	    <br/>
	    <label for="times" id="timesinfo">可描述下频率:</label>
	    <textarea name="times" id="times"></textarea>
	    <br/>
	    <label for="pic" id="picinfo">能激励自己的照片:</label>
	    <input type="file" id="pic" name="pic">
	    <br/>
	    <input type="submit" data-inline="true" value="提交">
	  </div>
    </form>
    <%}else if(request.getParameter("edit")!=null){%>
    <%
    Myplan m = (Myplan)request.getAttribute("myplan");
    %>
    <h2>修改一个计划</h2>
    <form enctype="multipart/form-data" method="post" action="/myplan/addplan.html" data-ajax="false">
      <input type="hidden" name="processedit" value="t" />
      <input type="hidden" name="id" value="<%=m.getId() %>" />
      <div data-role="fieldcontain">
	    <label for="info">计划标题*:</label>
	    <textarea name="title" id="info"><%=m.getTitle() %></textarea>
	    <br/>
	    <label for="discription">计划描述:</label>
	    <textarea name="discription" id="discription"><%if(m.getDiscription()!=null)out.print(m.getDiscription()); %></textarea>
	    <br/>
	    <label for="times">频率描述:</label>
	    <textarea name="times" id="times"><%=m.getTimes() %></textarea>
	    <br/>
	    <label for="pic" id="picinfo">能激励自己的照片:</label>
	    <input type="file" id="pic" name="pic">
	    <%
	    if(m.getPic()!=null){
	    	%>
	    	<img alt="" src="upload/planpic/<%=m.getPic()%>" style="width:100%" />
	    	<%
	    }
	    %>
	    <br/>
	    <input type="submit" data-inline="true" value="修改">
	    <input type="button" data-inline="true" value="删除本计划" onclick="deletePlan(<%=m.getId() %>)"  />
	  </div>
    </form>
    <%} %>
    </div>
  </div>
  
  
  <img alt="" src="images/foot.png" style="width:100%;">
 		
  
  
  <%@ include file="foot.jsp" %>
  
</div>





</body>