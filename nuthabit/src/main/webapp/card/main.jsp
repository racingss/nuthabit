<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Collection tagColl = (Collection)request.getAttribute("tagColl");
Collection myColl = (Collection)request.getAttribute("myColl");
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
<body style="background: #e8e8e8;">
	<header>
		<img alt="" src="images/head1.png" style="width: 100%;margin: 0;">
	</header>
	<div class="htmleaf-container">
		
		<section class="accordion">
			<!--           自建的卡片组                  -->
			<%
			Iterator myIt = myColl.iterator();
			while(myIt.hasNext()){
			   CardTag ct = (CardTag)myIt.next();
			   %>
		       <div class="item">
		            <img src="img/<%=ct.getHeadpng() %>" class="headpng">
		            <h3><%=ct.getTag() %>
		            	<a href="#" class="deletecardgroup" tagId="<%=ct.getTagId() %>" >
		            		<img alt="" src="img/delete.png" style="width:15px">
		            	</a>
		            </h3>
		       </div>
		            <p>
		            <span>
		                <a href="create_own_card_create_tag.html?tagId=<%=ct.getTagId()%>">
		            		<button type="button" class="btn btn-pink btnwidth30" >添加<i class="fa fa-book"></i></button>
		            	</a>
		                <a href="cardlist.html?tagId=<%=ct.getTagId()%>">
		            		<button type="button" class="btn btn-green btnwidth30" >学习<i class="fa fa-book"></i></button>
		            	</a>
		            	<a href="test_iop.html">
		            		<button type="button" class="btn btn-orange btnwidth30">复习<i class="fa fa-book"></i></button>
		            	</a>
		            </span>
		            <%
		            int i=0;
		            if(true){
		            	Iterator it = new CardDAO().getCardListByTag(ct.getTagId()).iterator();
		            	while(it.hasNext()){
		            		Card c = (Card)it.next();
		            		int a=90;
		            		if(i++>4)
		            			a = 25;
		            		else if(i>1)
		            			a = 40;
		            		%>
		            		<span class="imgspan" style="width:<%=a%>%">
		            			<a href="cardlist.html?static=t&cardId=<%=c.getCardId()%>&tagId=<%=ct.getTagId()%>">
		            				<img alt="" src="<%=c.getImg() %>" style="margin-top:10px;width:90%;" class="card">
		            			</a>
		            		</span>
    						<%
		            	}
		            }
		            %>
		            </p>
		       <%} %> 
			
			
			
			
			
			<!--           默认的卡片组                  -->
			<%
			Iterator tagIt = tagColl.iterator();
			while(tagIt.hasNext()){
			   CardTag ct = (CardTag)tagIt.next();
			   %>
		       <div class="item">
		            <img src="img/<%=ct.getHeadpng() %>" class="headpng">
		            <h3><%=ct.getTag() %></h3>
		       </div>
		            <p>
		            <span>
		            	<a href="cardlist.html?tagId=<%=ct.getTagId()%>">
		            		<button type="button" class="btn btn-green btnwidth40" >学习<i class="fa fa-book"></i></button>
		            	</a>
		            	<a href="test_iop.html">
		            		<button type="button" class="btn btn-orange btnwidth40">复习<i class="fa fa-book"></i></button>
		            	</a>
		            </span>
		            <%
		            int i=0;
		            if(true){
		            	Iterator it = new CardDAO().getCardListByTag(ct.getTagId()).iterator();
		            	while(it.hasNext()){
		            		Card c = (Card)it.next();
		            		int a=90;
		            		if(i++>4)
		            			a = 25;
		            		else if(i>1)
		            			a = 40;
		            		%>
		            		<span class="imgspan" style="width:<%=a%>%">
		            			<a href="cardlist.html?static=t&cardId=<%=c.getCardId()%>&tagId=<%=ct.getTagId()%>">
		            				<img alt="" src="<%=c.getImg() %>" style="margin-top:10px;width:90%;" class="card">
		            			</a>
		            		</span>
    						<%
		            	}
		            }
		            %>
		            </p>
		       <%} %> 
		       <div class="item">
		            <img src="img/add.png" class="headpng">
		            <h3><a href="create_own_card_create_tag.html" style="color:#ffe9c1">新建我的卡片组</a></h3>
		       </div>            
		   </section>
		
	</div>
	
	<script src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/dialog.js"></script>
	<script type="text/javascript">
		(function ($) {
		    'use strict';
		    $('.item').on("click", function () {
		        $(this).next().slideToggle(100);
		        $('p').not($(this).next()).slideUp('fast');
		    });
		    
		    $('.deletecardgroup').on("click", function () {
		    	var tagId = $(this).attr("tagId");
		    	$.dialog({
		    	       type : 'confirm',
		    	       onClickOk : function(){
		    	    	   window.location.href='deletetag.html?tagId='+tagId;
		    	       },
		    	       onClickCancel : function(){        		
		    	       },
		    	       contentHtml : '<p>我是confirm类型的对话框。</p> <p>我只是用来占位的内容展示，仅仅用来占位撑起提示内容的高度。我只是用来占位的内容展示，仅仅用来占位撑起提示内容的高度。</p>'
		    	    });
		    });
		}(jQuery));
	</script>

</body></html>