<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Collection cardColl = (Collection)request.getAttribute("cardColl");
%>    
<!DOCTYPE html>
<html lang="zh"><head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>点兵点将 儿童卡片</title>
	<link rel="stylesheet" href="css/list_style.css">
	<link rel="stylesheet" href="css/dialog.css">
	<style type="text/css">
	a{
		text-decoration:none;
	}
	.headpng{
		width:70px;
	}
	.imgspan{
		background: #fff;
	    border-radius: .1rem;
	    padding-bottom: .35rem;
	    box-shadow: 0px 0.08rem 0.3rem rgba(0, 0, 0, 0.1);
	    display: inline-block;
	    text-align: center;
	    margin: 10px;
	}
	.btn{
		cursor: pointer;
		-webkit-appearance: button;
		display: inline-block;
	    font-weight: 400;
	    text-align: center;
	    white-space: nowrap;
	    vertical-align: middle;
	    -webkit-user-select: none;
	    -moz-user-select: none;
	    -ms-user-select: none;
	    user-select: none;
	    border: 1px solid transparent;
	    padding: .375rem .75rem;
	    font-size: 1rem;
	    line-height: 1.5;
	    border-radius: .25rem;
	    transition: color .15s ease-in-out,background-color .15s ease-in-out,border-color .15s ease-in-out,box-shadow .15s ease-in-out;
	    text-transform: none;
	    
	    margin-left:15px;
	}
	.btnwidth40{
		width: 40%;
	}
	.btnwidth30{
		width: 25%;
	}
	
	.fa{
		display: inline-block;
    font: normal normal normal 14px/1 FontAwesome;
    font-size: inherit;
    text-rendering: auto;
    -webkit-font-smoothing: antialiased;
	}
	.btn-green{
		background-color: #34c70f;
    	color: #fff;
	}
	.btn-green{
		background-color: #34c70f;
    	color: #fff;
	}
	.btn-pink{
		background-color: #e129ec;
    	color: #fff;
	}
	.btn-orange{
		background-color: #F57C02;
    	color: #fff;
	}
	</style>
	<script type="text/javascript">
	</script>
</head>
<body style="background: #d5441c;" onload="load()">
	<div class="htmleaf-container">
		
		<section class="accordion">
			<div class="item">
		            <img src="img/car.png" class="headpng">
		            <h3>交通  transport</h3>
		    </div>
		    <p style="display: block;">
		            <span>
		                <a href="create_own_card_create_tag.html?tagId=30">
		            		<button type="button" class="btn btn-pink btnwidth30">添加<i class="fa fa-book"></i></button>
		            	</a>
		                <a href="cardlist.html?tagId=30">
		            		<button type="button" class="btn btn-green btnwidth30">学习<i class="fa fa-book"></i></button>
		            	</a>
		            	<a href="test_iop.html">
		            		<button type="button" class="btn btn-orange btnwidth30">复习<i class="fa fa-book"></i></button>
		            	</a>
		            </span>
		            
		            <%
		            int i=0;
		            if(true){
		            	Iterator it = cardColl.iterator();
		            	while(it.hasNext()){
		            		Card c = (Card)it.next();
		            		int a=90;
		            		if(i++>4)
		            			a = 25;
		            		else if(i>1)
		            			a = 40;
		            		%>
		            		<span class="imgspan" style="width:<%=a%>%">
		            			<a href="cardlist.html?static=t&cardId=<%=c.getCardId()%>&tagId=<%=request.getParameter("tagId")%>">
		            				<img alt="" src="<%=c.getImg() %>" style="margin-top:10px;width:90%;" class="card">
		            			</a>
		            		</span>
    						<%
		            	}
		            }
		            %>
    						
    						
		            </p>
			           
		</section>
		
	</div>
	
	<script src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/dialog.js"></script>
	<script type="text/javascript">
	function load()
	{
		$.dialog({
			showTitle : false,
			contentHtml : '卡片创建成功，您可以继续添加新的卡片'
	    });
	}
	</script>

</body></html>