<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Collection chanpinlist = (Collection)request.getAttribute("chanpinlist");
%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>点点商城</title>
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript">
	$(function(){
		
	})
	</script>
</head>
<body>
	<div class="page integral">
	
		<div>
			<div class="top">
				<h1 style="font-size: 0.6rem;text-align: center;margin: 0.2rem;">第一步，提交文字</h1>
			</div>
	        
	        
	        <div class="tabBd">
				<div id="sortable" class="wrapper">
					<div class="inputtitle">简单描述：</div>
					<div class="inputshuru"><textarea rows="" cols="" style="height: 3rem;"></textarea></div>
					<div class="inputtitle">产品价格：</div>
					<div class="inputshuru">
						<input type="text" name="jiage" style="width: 2rem;
    height: 0.8rem;
    font-size: 0.4rem;
    padding: 0.1rem;
    text-align: center;margin-bottom: 0.2rem;" value="0">
						<br/>
						<input type="radio" name="status" value="0">人民币
						<input type="radio" name="status" value="1">积分
						<input type="radio" name="status" value="2">面议
					</div>
					<div class="inputtitle">详细描述：</div>
					<div class="inputshuru"><textarea rows="" cols="" style="height: 6rem;"></textarea></div>
					
					
					<p style="text-align:center;">
						<button type="submit" class="bigbutom" style="box-shadow: 0px 0.4rem 0.5rem rgba(0, 0, 0, 0.1);">下一步</button>
					</p>
					
				</div>
	        </div>
		</div>
		
		<style>
		.inputtitle,.inputshuru{
			display:block;
			float:left;
			padding-left: 5%;
    		font-size: 0.4rem;
    		margin-bottom: 0.4rem;
		}
		.inputtitle{
			width:30%;
		}
		.inputshuru{
			width:70%;
		}
		.inputshuru textarea{
			width: 5rem;
		}
		.bigbutom {
		    padding: .2rem;
		    font-size: 0.6rem;
		    /* line-height: 1.5; */
		    color: #fff;
		    background-color: #007bff;
		    border-color: #007bff;
		    /* display: inline-block; */
		    /* font-weight: 400; */
		    text-align: center;
		    white-space: nowrap;
		    vertical-align: middle;
		    -webkit-user-select: none;
		    -moz-user-select: none;
		    -ms-user-select: none;
		    width: 150px;
		    border: 1px solid transparent;
		    border-radius: 3px;
		    box-shadow: 0px 0.4rem 0.5rem rgba(0, 0, 0, 0.1);
		}
		
		</style>
		
		
		
		<jsp:include page="foot.jsp" flush="true"/>
		
		
	</div>
	
	
	
	
</body>
</html>