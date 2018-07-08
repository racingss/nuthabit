<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
TestHttp testHttp = new TestHttp();
long languageId =  new LanguageHttp().getLanguageId(request);
%>    
<!DOCTYPE html>
<html lang="zh" class="fsvs"><head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, user-scalable=no">
	<title><%=Menu.getTitle(languageId) %></title>
	<link rel="stylesheet" href="css/list_style.css">
	<link rel="stylesheet" href="css/dialog.css">
	<link rel="stylesheet" href="css/card.css">
	<link rel="stylesheet" href="css/audioplayer.css" />
	<link rel="stylesheet" href="assets/css/default.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <style type="text/css">
    .footbatbut{
    	background-color: #f49731;
	    border-radius: 0.5rem;
	    margin: 0.3em;
	    padding: 0.5em;
	    box-shadow: 0px 0.4rem 0.5rem rgba(0, 0, 0, 0.1);
	    display: inline-block;
	    color: white;
	    text-decoration: none;
	    font-size: 1em;
    }
    .bookli{
		margin-bottom: 20px;
		height:200px;
		float:left;
		width: 100%;
	}
	.resultimg{
		margin-top: -200px;
    	display: none;
    	margin-left: 50px;
	}
	.testimg{
		max-height: 200px;
	}

    </style>  
	<script src="assets/js/jquery-1.11.0.min.js"></script>
   <script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
   <script src="assets/js/jquery.swipe-events.js"></script>
   <script type="text/javascript" src="js/dialog.js"></script>
   <script type="text/javascript">
	
   </script> 
	
</head>
<body class="active-slide-1 active-nth-slide-1">
    <div id="fsvs-body" style="transition: all 1s;">
    		
        		<div class="slide nth-class-1 active-slide" >
        			<p style="text-align: center;margin-top: 40px;">
    						<img alt="" src="img/goodjob.png" style="width: 100px;">
    				</p>
        			<p style="text-align: center;font-size: 22px;margin-top:40px;">
    						&nbsp;&nbsp;<%=Menu.getMenu("test_over", languageId) %>：
    						<br/>
    						<%=Menu.getMenu("your_score", languageId) %>
    						<span style="font-size:2em"><%=testHttp.getTestResult(request) %>%</span>
    						<br/>
    						<img alt="" src="<%=testHttp.getCard(request).getImg()%>" style="width:50%;margin: 20px;border-radius: 10px;">
    				<p>
        		
        			<ul style="display:block;width:100%;text-align: center;">
        				
        				
        			</ul>
        			
        			
        			
        			
        			
        			
    				<!--                内部工具栏                     -->
	    			<span class="carddetail" style="background: #fffefe;
  border-radius: .1rem;
  /* padding: 10px; */
  box-shadow: 0px 0.08rem 0.3rem rgba(0, 0, 0, 0.1);
  display: inline-block;
  margin: 10px;
  text-align: center;
  width: 95%;
  z-index: 9999;
  /* top: 0; */
  position: absolute;
  bottom: 0;opacity: 0.8;display: block;
  float: left;">
					    	<a class="footbatbut" href="/diandian/" style="color: #514e4f;">
					    			<%=Menu.getMenu("home", languageId) %>
				    		</a>
				    		<a class="footbatbut" href="cardlist.html?static=t&cardId=<%=testHttp.getCard(request).getCardId() %>&play=<%=System.currentTimeMillis() %>" style="color: #514e4f;">
							    	<%=Menu.getMenu("re_read", languageId) %>
						    </a>
			    	</span>	
    		
        			
        			
        		</div>

    	
    			
    </div>
    

<script type="text/javascript">
	function load()
	{
		
	}
	
	$(function(){
		
	})
</script>

    
</body>
</html>