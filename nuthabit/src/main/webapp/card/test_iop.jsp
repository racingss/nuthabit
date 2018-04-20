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
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta charset="utf-8">
    <title>看图识物</title>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/card.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/main.js"></script>
    <script src="js/util.js"></script>
    <script type="text/javascript">
	    $(function(){
	    	$("body").height($(window).height());
	    	
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
<body style="display: block">
<header style="text-align: center;">
	<div style="overflow:scroll;background-color: #fdfdfd;padding-top:5px;text-align:left;padding-left: 0px;">
		<a href="index.jsp">
			<img alt="" src="images/home.jpg" class="headpic" />
		</a>
		<a href="uploadCard.jsp">
			<img alt="" src="images/add.jpg" class="headpic" />
		</a>
		<a href="#">
			<img alt="" src="images/delete.jpg" class="headpic" id="deleteImg"/>
		</a>
		<a href="#">
			<img alt="" src="images/tag.jpg" class="headpic" id="tagImg"/>
		</a>
		<a href="#">
			<img alt="" src="images/meaning.jpg" class="headpic" />
		</a>
		<a href="#">
			<img alt="" src="images/language.jpg" class="headpic" id="languageImg" />
		</a>
	</div>
	
	<div style="width:100%;background-color: #fdfdfd;padding-top:5px;text-align:left;padding-left: 0px;">
		
	</div>
</header>
    
<div class="page paleBlue">
    
    <div class="sdPlan" style="text-align:center;" id="img_<%=flag%>">
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
    </div>
    
    <div class="sdPlan" style="text-align: center;
    background: #97d9e6;
    /* height: 35px; */
    padding-top: 10px;
    font-size: 25px;
    color: white;">
    	哪张图片是<span style="color:red;font-size: 35px;"> <%=c.getMeaning() %> </span>?
    	<a href="#" id="nexturl">
    		<img id="nexttest" src="images/right.jpg" style="width: 35px;
    padding-top: 5px;
    float: right;
    margin-right: 20px;
    margin-top: 5px;
    display:none;
    "/>
    	</a>
    </div>
    <audio  autoplay="true"><source src="/<%=cm.getSoundQue() %>" type="audio/mpeg" /></audio>
    
    <div class="sdPlan" style="text-align:center;" id="img_<%=!flag%>">
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
    </div>
    
</div>

<audio id="succaudio" ><source src="/<%=request.getAttribute("soundSuss").toString() %>" type="audio/mpeg" /></audio>
<audio id="failaudio" ><source src="/<%=request.getAttribute("soundFail").toString() %>" type="audio/mpeg" /></audio>

</body>
</html>