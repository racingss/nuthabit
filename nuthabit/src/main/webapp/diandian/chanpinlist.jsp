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
		//瀑布流信息每次有变更，需执行一次这个
		$(".grid .item").map(function(index,item){
			if(index%2!=0){
				$(".grid").append($(this));
			}
		})
	})
	</script>
</head>
<body>
	<div class="page integral">
	
		<div>
	        <div class="search">
	        	<a></a>
				<div id="sousuokuang">
					<i></i>
					<input type="text" name="" placeholder="输入商品名称关键词">
				</div>
	        </div>
	        <div class="tabHd">
	        	<a href="">
	        		<div>
		        		<i></i>
		        		<span>全部</span>
		        		<p>(2532)</p>
	        		</div>
	        	</a>
	        	<a href="">
	        		<div>
		        		<i></i>
		        		<span>图书</span>
		        		<p>(2532)</p>
	        		</div>
	        	</a>
	        	<a class="active" href="">
	        		<div>
		        		<i></i>
		        		<span>玩具</span>
		        		<p>(2532)</p>
	        		</div>
	        	</a>
	        	<a href="">
	        		<div>
		        		<i></i>
		        		<span>数码</span>
		        		<p>(2532)</p>
	        		</div>
	        	</a>
	        	<a href="">
	        		<div>
		        		<i></i>
		        		<span>家电</span>
		        		<p>(2532)</p>
	        		</div>
	        	</a>
	        	<a href="">
	        		<div>
		        		<i></i>
		        		<span>其他</span>
		        		<p>(2532)</p>
	        		</div>
	        	</a>


	        </div>
	        <div class="tabBd">
				<div id="sortable" class="wrapper">
					<div class="grid">
					<%
					Iterator chanpinIt = chanpinlist.iterator();
					while(chanpinIt.hasNext()){
						Chanpin c = (Chanpin)chanpinIt.next();
						%>
						
							<div class="grid-item item">
							  	<img class="pic" src="/<%=c.getZhutu() %>" alt="">
							  	<div class="info">
							  		<p><%=c.getJiancheng() %></p>
							  		<%
							  		if(c.getJiage()==0){
							  		%>
							  		<span class="mianyi">价格面议</span>
							  		<%}else{ %>
							  			<%
							  			if(c.getStatus()==1){
							  			%>
							  				<span class="jifen"><i></i><%=c.getJiage() %>积分</span>
							  			<%}else{ %>
							  				<span class="yuan"><i>￥</i><%=c.getJiage() %>元</span>
							  			<%} %>
							  		<%} %>
							  		<div>
							  			<div class="left">
							  				<div>
							  					<img src="frame/found-6.jpg">
							  				</div>
							  				<span>Adon</span>
							  			</div>
							  			<div class="right">
							  				<i></i>
							  				<span>505</span>
							  			</div>
							  		</div>
							  	</div>	
							</div>
						
						<%
					}
					%>
					</div>
				
					
					
					
				</div>
	        </div>
		</div>
		
		<a class="add" style="width: 1.2rem;
    height: 1.2rem;
    border-radius: 50%;
    background: red;
    position: fixed;
    bottom: 1.7rem;
    right: .4rem;
    background: url(frame/found-3.png);
    background-size: .6rem .6rem;
    background-position: center;
    background-repeat: no-repeat;
    background-color: #ffc600;
    box-shadow: 0.15rem 0.15rem 0.2rem 0px rgba(255, 225, 0, 0.2);
    display: block;">
		</a>
		
		
		<jsp:include page="foot.jsp" flush="true"/>
		
		
	</div>
	
	
	
	
</body>
</html>