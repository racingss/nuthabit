<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//切换语言
long languageId = new LanguageHttp().getLanguageId(request);
long languageId_2 = new LanguageHttp().getLanguageId_2(request);

Card c = Card.getStaticCard(Long.parseLong(request.getParameter("cardId")));
Collection cardColl = (Collection)request.getAttribute("cardColl");

int nums = cardColl.size();
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=c.getMeaning()%>_卡片点点Cardpopo_幼儿语言启蒙教育平台</title>
<style type="text/css">
	body{
		margin: 0;
    	padding: 0;
	}
    .page{
    	width: 100%;
    	height: 100%;
    	position: absolute;
    	top: 0;
    	left: 0;
    }
	.board{
		//background: url(shuyishuimg/sunbackground.jpg);
		background: #e8e7d9;
	    height: 100%;
	    background-size: cover;
	}
	a{
		text-decoration:none;
	    color:#524f4f;
	}
	.item{
		position: absolute;
	}
	.itemimg{
		width:100%;
	}
	.item span{
		display: inline-block;
	    width: 100%;
	    text-align: center;
	    font-weight: bold;
	    color: #584307;
	    font-size: 2rem;
	    line-height: 3rem;
	}
	.spanline1{
		margin-top:-2.2rem;
	}
	.spanline2{
		margin-top:-0.5rem;
	}
	
	.opacityShadow{
		opacity: 0.5;
	}
	
	.boxShadow{
		box-shadow: 0.5rem 0.5rem 0.5rem rgba(0, 0, 0, 0.1);
	}
	.arrow{
		position: absolute;
    	bottom: 1rem;
    	width: 5rem;
    }
	.shang{
    	left: 10%;
	}
	.xia{
    	left: 0;
	}
	.you{
    	left: 20%;
	}
	.zuo{
		left: 30%;
	}
	.quanbianda{
		top:0;
		right: 0;
	}
	.quanbianxiao{
		top:0;
		right: 10%;
	}
	.bianda{
		left: 40%;
	}
	.bianxiao{
		left: 50%;
	}
	.refresh{
		left: 20%;
		top:0%;
	}
	.see{
		left: 0;
		top:0;
	}
	.return{
		left: 10%;
		top:0%;
	}
	.shangMarginTop{
    	left: 70%;
	}
	.xiaMarginTop{
    	left: 60%;
	}
	.shangMarginTop2{
    	left: 90%;
	}
	.xiaMarginTop2{
    	left: 80%;
	}
	
	
	
	



</style>
</head>
<body>
	<div class="page">
		<div class="board">
		

			
			<%
			int index=1;
			int j=1;
			Iterator picit = cardColl.iterator();
			while(picit.hasNext()){
				CardPic fpic = (CardPic)picit.next();
				%>
				<div class="item<%if(request.getParameter("picId")!=null && Long.parseLong(request.getParameter("picId"))==fpic.getPicId())out.print(" boxShadow");%>" id="item<%=fpic.getPicId() %>" picId="<%=fpic.getPicId() %>" style="top: <%=fpic.getTopP()%>%;left: <%=fpic.getLeftP()%>%;width: <%=fpic.getWidthP()%>rem;">
					<img src="<%=fpic.getImgurl() %>" class="itemimg"  >
					<span class="spanline1" style="margin-top:<%=fpic.getMarginTop()%>rem;"><%=CardMeaning.getStaticCard(fpic.getPicId(), languageId).getMeaning() %></span>
					<span class="spanline2" style="margin-top:<%=fpic.getMarginTop2()%>rem;"><%=CardMeaning.getStaticCard(fpic.getPicId(), languageId_2).getMeaning() %></span>
				</div>
				
				<%
						
				j++;
				
			}
			
			
			%>
				
				
			<img src="frame/xia.png" class="arrow xia"/>
			<img src="frame/shang.png" class="arrow shang"/>
			<img src="frame/you.png" class="arrow you"/>
			<img src="frame/zuo.png" class="arrow zuo"/>
			<img src="frame/bianda.png" class="arrow bianda"/>
			<img src="frame/bianxiao.png" class="arrow bianxiao"/>
			<img src="frame/bianda.png" class="arrow quanbianda"/>
			<img src="frame/bianxiao.png" class="arrow quanbianxiao"/>
			<img src="frame/rotate.png" class="arrow refresh"/>
			<img src="frame/see.png" class="arrow see"/>
			<img src="frame/footer-1_active.png" class="arrow return"/>
			<img src="frame/xia.png" class="arrow xiaMarginTop"/>
			<img src="frame/shang.png" class="arrow shangMarginTop"/>
			<img src="frame/xia.png" class="arrow xiaMarginTop2"/>
			<img src="frame/shang.png" class="arrow shangMarginTop2"/>
			
		</div>
	
	</div>
	

	
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	
	$(function(){
		var currPicId=<%if(request.getParameter("picId")!=null)out.print(request.getParameter("picId")); else out.print(0); %>;
		
		$(".item").click(function(){
			//alert("已选中");
			currPicId=$(this).attr("picId");
			$(".boxShadow").removeClass("boxShadow")
			$(this).addClass("boxShadow");
		})
		
		
		$(".board").click(function(){

		})
		
		
		$(".shang").click(function(){
			location.href="cardposition.html?cardId=<%=c.getCardId()%>&picId="+currPicId+"&topP=-3";
		})
		
		$(".xia").click(function(){
			location.href="cardposition.html?cardId=<%=c.getCardId()%>&picId="+currPicId+"&topP=3";
		})
		
		$(".zuo").click(function(){
			location.href="cardposition.html?cardId=<%=c.getCardId()%>&picId="+currPicId+"&leftP=-3";
		})
		
		$(".you").click(function(){
			location.href="cardposition.html?cardId=<%=c.getCardId()%>&picId="+currPicId+"&leftP=3";
		})
				
		$(".bianda").click(function(){
			location.href="cardposition.html?cardId=<%=c.getCardId()%>&picId="+currPicId+"&widthP=1";
		})
		
		$(".bianxiao").click(function(){
			location.href="cardposition.html?cardId=<%=c.getCardId()%>&picId="+currPicId+"&widthP=-1";
		})
		
		$(".quanbianda").click(function(){
			location.href="cardposition.html?cardId=<%=c.getCardId()%>&widthP=1";
		})
		
		$(".quanbianxiao").click(function(){
			location.href="cardposition.html?cardId=<%=c.getCardId()%>&widthP=-1";
		})
			
		$(".refresh").click(function(){
			location.href="cardposition.html?cardId=<%=c.getCardId()%>&init=t";
		})
		
		$(".see").click(function(){
			location.href="cognitiveboard.jsp?cardId=<%=c.getCardId()%>";
		})
		
		$(".return").click(function(){
			location.href="/card/carddetail.html?cardId=<%=c.getCardId()%>";
		})
		
		$(".shangMarginTop").click(function(){
			location.href="cardposition.html?cardId=<%=c.getCardId()%>&picId="+currPicId+"&marginTop=-1";
		})
		
		$(".xiaMarginTop").click(function(){
			location.href="cardposition.html?cardId=<%=c.getCardId()%>&picId="+currPicId+"&marginTop=1";
		})
		
		$(".shangMarginTop2").click(function(){
			location.href="cardposition.html?cardId=<%=c.getCardId()%>&picId="+currPicId+"&marginTop2=-1";
		})
		
		$(".xiaMarginTop2").click(function(){
			location.href="cardposition.html?cardId=<%=c.getCardId()%>&picId="+currPicId+"&marginTop2=1";
		})
		
		
		
		
	})
</script>	
</body>
</html>