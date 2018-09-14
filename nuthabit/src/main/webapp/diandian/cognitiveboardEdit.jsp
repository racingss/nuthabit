<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//切换语言
long languageId = new LanguageHttp().getLanguageId(request);
long languageId_2 = new LanguageHttp().getLanguageId_2(request);

Card c = (Card)request.getAttribute("card");
Collection cardColl = (Collection)request.getAttribute("cardColl");
Collection wordColl = (Collection)request.getAttribute("wordColl");

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
    	background: url(<%
    	if(c.getSecondPic()!=null && c.getSecondPic().length()>10){
    		out.print("/"+c.getSecondPic());
    	}else{
    		out.print("/diandian/frame/home-18.jpg");
    	}
    	%>);
    	background-size: cover;
    }
	.board{
		//background: url(shuyishuimg/sunbackground.jpg);
		//background: #e8e7d9;
	    height: 100%;
	    //background-size: cover;
	}
	a{
		text-decoration:none;
	    color:#524f4f;
	}
	.item{
		position: absolute;
	}
	.word{
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
	.quanbianda{
		top:0;
		right: 0;
	}
	.quanbianxiao{
		top:0;
		right: 10%;
	}
	.wordmenu{
		font-size: 3rem;
	    background: #f2d542;
	    color: white;
	    border-radius: 50%;
	    text-align: center;
	    font-weight: bold;
	    display: flex;
	    width: 5rem;
	    height: 5rem;
	    justify-content: center;
	    align-items: center;
		left:5%;
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
	
	
	.regV{
		position: fixed;
	    height: 100%;
	    width: 100%;
	    top: 0;
	    left: 0;
	    background: rgba(0,0,0,.6);
	    z-index: 9;
	    display:none;
	}
	.innerReg{
		width: 100%;
    	top: 20%;
    	position: absolute;
	}
	.innerReg p{
		text-align: center;
    	padding: 5rem;
	}
	.innerTextArea{
		width: 60%;
	    margin-left: 15%;
	    height: 15rem;
	    font-size: 5rem;
	    padding: 5%;
	    border-radius: 3rem;
	}
	.innerbut{
		padding: 1rem 2rem;
	    font-size: 3.5rem;
	    color: white;
	    background: #67bee4;
	    border-radius: 2.5rem;
	    margin: 3rem;
	}
	
	



</style>
</head>
<body>
	<div class="page">
		<div class="board" id="board">
		
			<%
			int index=1;
			Iterator picit = cardColl.iterator();
			while(picit.hasNext()){
				CardPic fpic = (CardPic)picit.next();
				%>
				<div class="item<%if(request.getParameter("picId")!=null && Long.parseLong(request.getParameter("picId"))==fpic.getPicId())out.print(" boxShadow");%>" id="item<%=fpic.getPicId() %>" picId="<%=fpic.getPicId() %>" style="top: <%=fpic.getTopP()%>%;left: <%=fpic.getLeftP()%>%;width: <%=fpic.getWidthP()%>rem;">
					<img src="<%=fpic.getImgurl() %>" class="itemimg draggable"  alt="<%=fpic.getPicId() %>"/>
					<span class="spanline1" style="margin-top:<%=fpic.getMarginTop()%>rem;"><%
					CardMeaning cm1=CardMeaning.getStaticCard(fpic.getPicId(), languageId);
					if(cm1!=null)
						out.print(cm1.getMeaning());
					%></span>
					<span class="spanline2" style="margin-top:<%=fpic.getMarginTop2()%>rem;"><%
					CardMeaning cm2 =CardMeaning.getStaticCard(fpic.getPicId(), languageId_2);
					if(cm2!=null)
						out.print(cm2.getMeaning());
					%></span>
				</div>
				<%
			}
			
			Iterator wordit = wordColl.iterator();
			while(wordit.hasNext()){
				CardWord cw = (CardWord)wordit.next();
				%>
				<div class="word" wordId="<%=cw.getWordId() %>" style="top: <%=cw.getTopP()%>%;left: <%=cw.getLeftP()%>%;width: <%=cw.getWidthP()%>rem;">
					<p class="itemimg draggable" id="<%=cw.getWordId() %>" style="font-size:<%=cw.getSizeP()%>rem"><%=cw.getMeaning() %></p>
				</div>
				<%
			}
			
			%>
				

			
			<a href="#" class="arrow wordmenu">W</a>
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
	
	
	<div class="regV">
		<form id="regVform" action="/diandian/cardposition.html" method="post" >
			<input type="hidden" name="cardId" value="<%=c.getCardId() %>" />
			<div class="innerReg">
				<textarea rows="" cols="" class="innerTextArea" name="meaning" id="meaning"></textarea>
				<p>
					<a href="#" class="innerbut tijiao" >提交</a>
					<a href="#" class="innerbut quxiao">取消</a>
				</p>
			</div>
		</form>
	
	</div>
	
	

	
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/draggin.js"></script>
<script type="text/javascript">
	
	$(function(){
		var currPicId=<%if(request.getParameter("picId")!=null)out.print(request.getParameter("picId")); else out.print(0); %>;
		
		var currWordId=<%if(request.getParameter("wordId")!=null)out.print(request.getParameter("wordId")); else out.print(0); %>;
		
		$(".item").click(function(){
			//alert("已选中");
			currPicId=$(this).attr("picId");
			$(".boxShadow").removeClass("boxShadow")
			$(this).addClass("boxShadow");
		})

		$(".word").click(function(){
			//alert("已选中");
			currWordId=$(this).attr("wordId");
			$(".boxShadow").removeClass("boxShadow")
			$(this).addClass("boxShadow");
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
			location.href="/card/cardlist.html?cardId=<%=c.getCardId()%>";
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
		
		$(".wordmenu").click(function(){
			$('.regV').show();
		})
		
		$(".quxiao").click(function(){
			$('.regV').hide();
		})
		
		$(".tijiao").click(function(){
			document.getElementById('regVform').submit();
		})
		
		
		
		
		
		
	})
</script>	
</body>
</html>