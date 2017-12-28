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
.pagehead {
	background-color: #33afde;
    border-color: #97c3e2;
    color: #fff;
    /* text-shadow: 0 1px 0 #eee; */
    /* font-weight: 100; */
    border-width: 1px 0;
    border-style: solid;
    position: relative;
    padding: 10px;
    padding-left: 20px;
    font-size: 1.2em;
}
.plusimg{
	width: 25px;
    float: right
   }
h3{
color: #615555;
    /* display: block; */
    text-align: left;
    font-size: 0.9em;
    font-weight: normal
}
</style>
</head>
<body>
<div data-role="page" id="pageone">
  <!--div data-role="header" role="banner" class="ui-header ui-bar-inherit">
  	<h1 class="ui-title" role="heading" aria-level="1">每日计划</h1>
  </div-->
  
  <div class="pagehead">
  		<span>每日计划</span>
  		<span>
  		<a href="index.html?pageadd=t&rand=<%=System.currentTimeMillis() %>"><img src="images/plus1.png" class="plusimg" /></a>
  		</span>
  </div>
  
  <div data-role="content">
    <div data-role="collapsible-set">
  	<h2>添加一个计划</h2>
    <form enctype="multipart/form-data" method="post" action="addplan.html">
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
	    <input type="file" id="pic">
	    <br/>
	    <input type="submit" data-inline="true" value="提交">
	  </div>
    </form>
    
    </div>
  </div>
  
 
  
  
  <%@ include file="foot.jsp" %>
  
</div>





</body>