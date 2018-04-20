<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Card c = (Card)request.getAttribute("card");
CardMeaning cm = (CardMeaning)request.getAttribute("cm");
Collection cardColl = (Collection)request.getAttribute("cardColl");
CardPic p = (CardPic)cardColl.iterator().next();

Card cTest = (Card)request.getAttribute("cardTest");
Collection cardCollTest = (Collection)request.getAttribute("cardCollTest");
CardPic pTest = (CardPic)cardCollTest.iterator().next();

boolean flag = Math.random()>0.5;
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
	</style>
	<script src="js/jquery.min.js"></script>
	<script type="text/javascript">
	    $(function(){
	    	$("#img_true").click(function(){
	    		$("#img_true").css({'background':'#74b319'});
	    		
	    		$("#nexturl").attr("href","test_iop.html?result=s&cardId=<%=p.getCardId()%>");
	    		$("#nexttest").show();

	    		//播放成功语音
	    		var audio = audio = document.getElementById('succaudio');
	        	audio.volume = .3;
	            event.stopPropagation();//防止冒泡
	            if(audio.paused){ //如果当前是暂停状态
	                audio.play(); //播放
	                return;
	            }
	    		
	    		//setTimeout("window.location.href='test_oip.html?result=s&cardId=<%=p.getCardId()%>'", 5); 
	        })
	        
	        $("#img_false").click(function(){
	        	$("#img_true").css({'background':'#74b319'});
	        	
	        	$("#nexturl").attr("href","test_iop.html?result=f&cardId=<%=p.getCardId()%>");
	    		$("#nexttest").show();
	        	
	        	var audio = audio = document.getElementById('failaudio');
	        	audio.volume = .3;
	            event.stopPropagation();//防止冒泡
	            if(audio.paused){ //如果当前是暂停状态
	                audio.play(); //播放
	                return;
	            }
	    		//setTimeout("window.location.href='test_oip.html?result=f&cardId=<%=p.getCardId()%>'", 5); 
	        })
	    })
	 </script>
</head>
<body style="background: #d5441c;" onload="load()">
	<div class="htmleaf-container">
		
		<section class="accordion">
			
			
			<!--            菜单               -->
		    <span class="sdPlan" style="display: block;text-align: center;background: #97d9e6;">
		    	<a href="index.html">
		    		<img alt="" src="images/first.jpg" class="menupic"/>
		    	</a>
		    	<a href="#" id="nexturl">
		    		<img alt="" src="images/right.jpg" class="menupic" />
		    	</a>
		    </span>
		
		
		
			<div class="item">
					<a href="index.html">
		            	<img src="img/question.png" class="headpng">
		            </a>
		            <h3 style="font-size:1.5em;">哪张图片是<span style="color:#64c2ff;font-size: 35px;"> <%=c.getMeaning() %> </span>?</h3>
		    </div>
			<p style="display:block;">
					
					<span class="imgspan" style="width:90%" id="img_<%=flag%>">
				    	<%
				    	if(true){
				    		CardPic temp = null;
				    		String url = null;
				    		if(flag){
				    			temp = p;
				    		}else{
				    			temp =pTest;
				    		}
				    		%>
				    		<img alt="" src="/<%=temp.getCardpic() %>" style="margin-top:15px;width:90%;"/>
				    	<%} %>
				    </span>
				    
				    <span class="imgspan" style="width:90%" id="img_<%=!flag%>">
				    	<%
				    	if(true){
				    		CardPic temp = null;
				    		if(flag)
				    			temp = pTest;
				    		else
				    			temp =p;
				    	%>
				    	<a href="#">
				    		<img alt="" src="/<%=temp.getCardpic() %>" style="margin-top:15px;width:90%;" />
				    	</a>
				    	<%}
				    	%>
				    </span>
					
			
			</p>
			
						           
		</section>
		
	</div>
	
	<audio id="succaudio" ><source src="/<%=request.getAttribute("soundSuss").toString() %>" type="audio/mpeg" /></audio>
	<audio id="failaudio" ><source src="/<%=request.getAttribute("soundFail").toString() %>" type="audio/mpeg" /></audio>
	
	
	<script type="text/javascript" src="js/dialog.js"></script>
	<script type="text/javascript">
	function load()
	{
		
	}
	</script>

</body></html>