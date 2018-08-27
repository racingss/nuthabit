<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//切换语言
long languageId = new LanguageHttp().getLanguageId(request);
long languageId_2 = new LanguageHttp().getLanguageId_2(request);

Card c = Card.getStaticCard(Long.parseLong(request.getParameter("cardId")));
Collection cardColl = new CardPicDAO().getCardPicByCardId(c.getCardId());

int nums = cardColl.size();

Kehu k = new KehuUtil().getKehu(request, response);
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
		width: 18rem;
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
		box-shadow: 0.2rem 0.2rem 0.3rem rgba(0, 0, 0, 0.1);
	}
	.mainboard{
		background: white;
    	position: absolute;
    	width: 80%;
    	top: 10%;
    	z-index: 9999999999;
    	border-radius: 1rem;
   		margin-left: 10%;
	}
	.mainboard span{
		display: inline-block;
	    width: 100%;
	    text-align: center;
	    font-weight: bold;
	    color: #584307;
	    font-size: 4rem;
	    padding-bottom: 4rem;
	    
	}
	.cancelimg{
		bottom: -10%;
	    position: absolute;
	    left: 46%;
	    width: 10%;
	}
	.effecthidden{
		background: url(/card/frame/sound.gif);
	    background-size: 10rem 10rem;
	    display: inline-block;
	    width: 10rem;
	    height: 10rem;
	    position: absolute;
	    top: 1rem;
	    right: 1rem;
	    border-radius: 0.5rem;
	}
	.arrow{
		position: absolute;
    	bottom: 1rem;
    	width: 5rem;
    }
    .return{
		left: 10%;
		top:0%;
		width:100px;
	}
	.headnum{
		position: absolute;
	}

</style>
</head>
<body>
	<div class="page">
		<div class="board">
		
			<%
    		if(k.getId()==c.getkId() ||k.getGuanlibiaoji()==1){
    		%>
    		<a href="/card/carddetail.html?cardId=<%=c.getCardId()%>">	
				<img src="/diandian/frame/footer-1_active.png" class="arrow return">
			</a>
			<%} %>
			
			
			
			<a href="/diandian/" style="position: absolute;top: 1rem;left: 1rem;box-shadow: 0.2rem 0.2rem 0.3rem rgba(0, 0, 0, 0.1);border-radius: 2rem;">
				<img src="/card/img/f2s.png"/ style="width: 5rem;">
			</a>
		
			<div class="mainboard"  style="display:none">
					<span class="headnum">1/2</span>
					<img src="/myplan/upload/historypic/1534326494671.png" class="itemimg" >
					<span class="spanline1">be surprised</span>
					<span class="spanline2">惊讶</span>
					<img class="cancelimg" src="/diandian/frame/cancel.png">
					<a class="effecthidden boxShadow" ></a>
			</div>
			
			<%
			int index=1;
			int j=1;
			Iterator picit = cardColl.iterator();
			while(picit.hasNext()){
				CardPic fpic = (CardPic)picit.next();
				%>
				<div class="item" id="item<%=fpic.index %>" index="<%=fpic.index %>" picId="<%=fpic.getPicId() %>" style="top: <%=fpic.getTopP()%>%;left: <%=fpic.getLeftP()%>%;width: <%=fpic.getWidthP()%>rem;">
					<img src="<%=fpic.getImgurl() %>" class="itemimg"  >
					<span class="spanline1"  style="margin-top:<%=fpic.getMarginTop()%>rem;"><%=CardMeaning.getStaticCard(fpic.getPicId(), languageId).getMeaning() %></span>
					<span class="spanline2"  style="margin-top:<%=fpic.getMarginTop2()%>rem;" ><%=CardMeaning.getStaticCard(fpic.getPicId(), languageId_2).getMeaning() %></span>
				</div>
				
				
				<!--         音效           -->
       			<%
  					if(fpic.getSound()!=null && fpic.getSound().length()>2){
  						if(request.getSession().getAttribute("soundFlag")==null ||request.getSession().getAttribute("soundFlag").toString().equals("0")){
  					%>
  						<audio preload="auto" controls id="effect_<%=fpic.index %>" style="display:none">
						<source src="/<%=fpic.getSound() %>">
					</audio>
				<%  } 
				}%>   
				
				<!--         语音           -->
				<%
				Collection picSoundColl = CardSound.getSoundCollByPicId(fpic.getPicId());
				System.out.println(picSoundColl.size());
				Iterator picSoundIt = picSoundColl.iterator();
				while(picSoundIt.hasNext()){
					System.out.println(1);
					CardSound cs = (CardSound)picSoundIt.next();
					if(cs.getLanguageId()!=1&&cs.getLanguageId()!=0)
						continue;
					if(request.getSession().getAttribute("soundFlag")==null ||request.getSession().getAttribute("soundFlag").toString().equals("0")){
						String sound = cs.getSound();
						if(sound.indexOf("sound")!=-1){
							sound="card/"+sound;
						}
					%> 
						<audio controls id="sound_<%=fpic.index%>_<%=cs.getLanguageId()%>" style="display:none">
							<source src="/<%=sound %>">
						</audio>	
					<%
					}
				}

				
				j++;
				
			}
			
			
			%>
				
				
			
		
		</div>
	
	</div>
	

	
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	
	$(function(){
		var currPicId=0;
		var index=0;
		
		$(".cancelimg").click(function(){
			$(".item").removeClass("opacityShadow");
			$(".mainboard").hide();
		})
		
		$(".effecthidden").click(function(){
			$(this).removeClass("boxShadow");
			document.getElementById("effect_"+index).play();
			setTimeout(function(){ 
				$(".effecthidden").addClass("boxShadow");
			}, 2000);	
			
		})
		
		
		$(".spanline1").click(function(){
			document.getElementById("sound_"+index+"_<%=languageId%>").play();
		})
		
		$(".spanline2").click(function(){
			document.getElementById("sound_"+index+"_<%=languageId_2%>").play();
		})
		
		$(".item").click(function(){
			//alert("已选中");
			currPicId=$(this).attr("picId");
			index=$(this).attr("index");
			$(".item").addClass("opacityShadow");
			
			t=changeItem();
		})
		
		function changeItem(){
			$(".headnum").text(index+"/<%=nums%>");
			$(".mainboard").children(".itemimg").attr("src",$("#item"+index).children("img").attr("src"));
			console.log($("#item"+index).children("spanline1").text());
			$(".mainboard").children(".spanline1").text($("#item"+index).children(".spanline1").text());
			$(".mainboard").children(".spanline2").text($("#item"+index).children(".spanline2").text());
			$(".mainboard").show();
			document.getElementById("sound_"+index+"_<%=languageId%>").play();
			
			if(document.getElementById("effect_"+index)!=null){
				$(".effecthidden").show();
			}else{
				$(".effecthidden").hide();
			}
		}
		
		var windowHeight = $(window).height(),
		$body = $("body");
		$body.css("height", windowHeight); //重要代码
		
		$("body").on("touchstart", function(e) {
			//e.preventDefault();
			startX = e.originalEvent.changedTouches[0].pageX,
			startY = e.originalEvent.changedTouches[0].pageY;
		});
		
		var moveFlag=0;
		$("body").on("touchmove", function(e) {
			//e.preventDefault();
			moveEndX = e.originalEvent.changedTouches[0].pageX,
			moveEndY = e.originalEvent.changedTouches[0].pageY,
			X = moveEndX - startX,
			Y = moveEndY - startY;
			
			if(moveFlag==0){
				moveFlag=1;
				setTimeout(function(){ 
					moveFlag=0;
				}, 300);	
				
				if ( Math.abs(X) > Math.abs(Y) && X > 25 ) {
					//alert("left 2 right："+X);
					if(index>1){
						index=index-1;
						t=changeItem();	
						return;
					}else{
						alert("没有了");
					}
				}
				else if ( Math.abs(X) > Math.abs(Y) && X < -25 ) {
					//alert("right 2 left："+X);
					if(index<<%=nums%>){
						index=parseInt(index)+1;
						t=changeItem();
						return;
					}else{
						alert("到头了");
					}
					return;
					
					
					
				}
				else if ( Math.abs(Y) > Math.abs(X) && Y > 0) {
					//alert("top 2 bottom");
				}
				else if ( Math.abs(Y) > Math.abs(X) && Y < 0 ) {
					//alert("bottom 2 top");
				}
				else{
					//alert("just touch");
				}
				
			}
			
		});
		

	})
</script>	
</body>
</html>