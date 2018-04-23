<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Collection cardColl = (Collection)request.getAttribute("cardColl");
long tagId=0;
if(request.getParameter("tagId")!=null)
	tagId = Long.parseLong(request.getParameter("tagId"));
else if(request.getSession().getAttribute("tagId")!=null)
	tagId = Long.parseLong(request.getSession().getAttribute("tagId").toString());
CardTag ct = CardTag.getCartTagByTagId(tagId);
int headlength=3;
if(ct.getTag().length()>4)
	headlength=2;
%>    
<!DOCTYPE html>
<html lang="zh"><head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>点兵点将 儿童卡片</title>
	<link rel="stylesheet" href="css/list_style.css">
	<link rel="stylesheet" href="css/dialog.css">
	<link rel="stylesheet" href="css/card.css">
	<style type="text/css">
	a{
		text-decoration:none;
		color: #524f4f;
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
	//卡片详细
	.carddetail{
		background: #fff;
	    border-radius: .1rem;
	    padding: 20px;
	    box-shadow: 0px 0.08rem 0.3rem rgba(0, 0, 0, 0.1);
	    display: inline-block;
	    margin: 10px;
	    color: #524f4f;
	    width:80%;
	}
	</style>
	<script type="text/javascript">
	</script>
</head>
<body style="background: #d5441c;" onload="load()">
	<div class="htmleaf-container">
		
		<section class="accordion">
			<div class="item">
					<a href="index.html">
		            	<img src="img/<%=ct.getHeadpng() %>" class="headpng">
		            </a>
		            <h3 style="font-size:<%=headlength%>em;"><%=ct.getTag() %></h3>
		    </div>
		    <p style="display: block;">
		    		<span class="carddetail" style="background: #fff;
	    border-radius: .1rem;
	    padding: 20px;
	    box-shadow: 0px 0.08rem 0.3rem rgba(0, 0, 0, 0.1);
	    display: inline-block;
	    margin: 10px;
	    color: #524f4f;
	    width:80%;">
		            	卡片内容：20个常见的动物
		            	<br/>图片数量：100  &nbsp;&nbsp;&nbsp;&nbsp; 语音数量：38
		            	<br/>使用方式：免费  &nbsp;&nbsp;可获积分：50
		            	<br/>支持语音：🇨🇳🇺🇸🇯
		            	<br/><br/>
		            	<a href="create_own_card_create_tag.html?tagId=<%=ct.getTagId()%>">
		            		<button type="button" class="btn btn-pink btnwidth30">添加<i class="fa fa-book"></i></button>
		            	</a>
		                <a href="cardlist.html?tagId=<%=ct.getTagId()%>">
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
		            				<br/>
		            				<%=c.getMeaning() %>
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
		<%
		if(request.getParameter("creat_card_succ")!=null){
		%>
		$.dialog({
			showTitle : false,
			contentHtml : '卡片创建成功，您可以继续添加新的卡片'
	    });
		<%}%>
	}
	</script>

</body></html>