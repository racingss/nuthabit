<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Collection soundColl = (Collection)request.getAttribute("soundColl");
%>    
<!DOCTYPE html>
<html lang="zh"><head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>后台管理</title>
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
			<%
			if(true){
				  Iterator it = soundColl.iterator();
				  while(it.hasNext()){
					    CardSound cs = (CardSound)it.next();
				  
						%>
						<div class="item" id="item_<%=cs.getSoundId()%>">
								<a href="#" class="soundhead" soundId="<%=cs.getSoundId()%>">
					            	<img src="img/play.png" class="headpng">
					            </a>
					            <h3 >
					            	<%=Card.getStaticCard(cs.getCardId()).getMeaning() %>
					            	<a href="#" class="soundgroup" soundId="<%=cs.getSoundId()%>" process="<%=CardSound.STATUS_CONFIRMED%>">
					            		<img alt="" src="img/yes.png" style="width:18px">
					            	</a>
					            	<a href="#" class="soundgroup" soundId="<%=cs.getSoundId()%>" process="<%=CardSound.STATUS_REJECT%>">
					            		<img alt="" src="img/no.png" style="width:18px">
					            	</a>
					            </h3>
					    </div>
					    <span id="wrapper" style="display:none">
							<audio preload="auto" controls id="sound_<%=cs.getSoundId()%>">
								<source src="/<%=cs.getSound() %>">
							</audio>
						</span>					
					    <%
				  } %>
		    <%
		    } %>
		    
			    <div class="item">
			    	<a href="system_cards_sound.html" >
					       <img src="img/next.png" class="headpng">
					</a>
					<h3 >
		            	   下十个
		            	
		            </h3>
				</div>
		    
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
		
		//播放语音
		$('.soundhead').on("click", function () {
			soundId = 'sound_'+$(this).attr("soundId");
			document.getElementById(soundId).play();
		});
		
		//处理结果
		
		$('.soundgroup').on("click", function () {
			soundId = $(this).attr("soundId");
			process = $(this).attr("process");
			$.ajax({
				url: 'system_cards_sound.html?soundId='+soundId+'&process='+process,
				dateType:'json',
			    success: function(data){
			    	$("#item_"+soundId).hide();
			    }
		   });
		});
	}
	</script>

</body></html>