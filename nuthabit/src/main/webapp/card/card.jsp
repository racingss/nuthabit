<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Card c = (Card)request.getAttribute("card");
Collection cardColl = (Collection)request.getAttribute("cardColl");
CardPic p = (CardPic)cardColl.iterator().next();
Collection meaningColl = c.cardMeaningColl;
Collection soundColl = c.cardSoundColl;

//切换语言
long languageId = 0;
if (request.getSession().getAttribute("languageId") != null) {
	languageId = Long.parseLong(request.getSession().getAttribute("languageId").toString());
}

long languageId_2 = -1;
if (request.getSession().getAttribute("languageId_2") != null) {
	languageId_2 = Long.parseLong(request.getSession().getAttribute("languageId_2").toString());
}


boolean autoPlay=false;
long tagId=0;
if(request.getParameter("tagId")!=null)
	tagId = Long.parseLong(request.getParameter("tagId"));
else if(request.getSession().getAttribute("tagId")!=null)
	tagId = Long.parseLong(request.getSession().getAttribute("tagId").toString());
CardTag ct = CardTag.getCartTagByTagId(tagId);


String displayMeaning=c.getMeaning(languageId, c.getCardId());

double headlength=3;
if(displayMeaning.length()>4)
	headlength=2;
if(displayMeaning.length()>8)
	headlength=1.5;
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
	<link rel="stylesheet" href="css/audioplayer.css" />
	<link href="css/mfb.css" rel="stylesheet">
	<script src="js/mfb.js"></script>  
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
					    
		    <!--            菜单               -->
		    <span class="sdPlan" style="display: block;text-align: center;background: #97d9e6;">
		    	<a href="/card/cardlist.html?cardIndex=<%=c.getCardIndex()%>&flag=p&tagId=<%=tagId%>">
		    		<img alt="" src="images/left.jpg" class="menupic"/>
		    	</a>
		    	<a href="index.html">
		    		<img alt="" src="images/first.jpg" class="menupic"/>
		    	</a>
		    	<a href="#">
		    		<img alt="" src="images/faveriout.jpg" class="menupic" id="favImg" />
		    	</a>
		    	<a href="test_sound.jsp?cardId=<%=c.getCardId()%>&languageId=<%=languageId%>">
		    		<img alt="" src="images/new.jpg" class="menupic" />
		    	</a>
		    	<a href="#">
		    	</a>
		    	<a href="#">
		    		<img alt="" src="images/soundQue.jpg" class="menupic" id="webImg" />
		    	</a>
		    	<a href="#">
		    		<img alt="" src="images/remove.jpg" class="menupic" id="removeImg" />
		    	</a>
		    	<a href="/card/cardlist.html?cardIndex=<%=c.getCardIndex()%>&flag=n&tagId=<%=tagId%>">
		    		<img alt="" src="images/right.jpg" class="menupic"/>
		    	</a>
		    </span>
		    
		    
		    <!--             头部              -->
			<div class="item">
					<a href="cardbytag.html?tagId=<%=ct.getTagId()%>">
		            	<img src="img/<%=ct.getHeadpng() %>" class="headpng">
		            </a>
		            <h3 style="font-size:<%=headlength%>em;"><%=displayMeaning.toString()%></h3>
		    </div>
		    
		    <!--              图片                -->
		    <p style="display: block;">
		            <%
		            int i=0;
		            if(true){
		            	Iterator it = cardColl.iterator();
		            	while(it.hasNext()){
		            		CardPic cp = (CardPic)it.next();
		            		int a=90;
		            		if(i++>0)
		            			a=25;
		            		%>
		            		<span class="imgspan" style="width:<%=a%>%">
		            			<a href="#">
		            				<img alt="" src="<%=cp.getImgurl() %>" style="margin-top:10px;width:90%;" class="card" id="card_<%=i%>" picId="<%=cp.getPicId()%>">
		            			</a>
		            		</span>
    						<%
		            	}
		            }
		            %>
		            
		            
		            <!--             文字                  -->
		    		
	    		<%
	    		for(int wenzii=0;wenzii<2;wenzii++)
	    		if(true){
	    			Iterator it = meaningColl.iterator();
	    			int l_i=0;
	    			while(it.hasNext()){
	    				CardMeaning cm = (CardMeaning)it.next();
	    				if(wenzii==0){
	    					if(cm.getLanguageId()!=languageId)
	    						continue;
	    				}
	    				if(wenzii==1){
	    					if(cm.getLanguageId()!=languageId_2)
	    						continue;
	    				}
	    				%>
	    				<span class="carddetail" style="background: #fff;
					    border-radius: .1rem;
					    padding: 20px;
					    box-shadow: 0px 0.08rem 0.3rem rgba(0, 0, 0, 0.1);
					    display: inline-block;
					    margin: 10px;
					    color: #524f4f;
					    text-align:center;
					    width:80%;">
					    		<em style="font-size:50px;"><%=cm.getMeaning() %>
					    		<br/>
					    		<em style="font-size:20px;float:right;">
			    					<%=Language.getLanguageByid(cm.getLanguageId()).getLname() %>
			    				</em>	
			    		</span>		
	    				<%
	    			}
	    		}
	    		%>
				    
    		</p>
    		
    		
    		
    		
    		
    		<!--             语音              -->
		    <span id="wrapper" style="display:none">
		    	<%
				if(soundColl!=null&&soundColl.size()>0){
			  		Iterator it = soundColl.iterator();
			  		while(it.hasNext()){
			  			CardSound s = (CardSound)it.next();
			  			//if(s.getLanguageId()!=languageId)
			  			//	continue;
			  			%>
						<audio preload="auto" controls id="sound_<%=s.getSoundId()%>">
							<source src="/<%=s.getSound() %>">
						</audio>
						<%
					}
				}%>
			</span>
			           
		</section>
		
	</div>
	
	
	<!--                   菜单    给语音使用                 -->
	<%
	if(soundColl!=null&&soundColl.size()>0){
	%>
	<ul id="menu" class="mfb-component--br mfb-zoomin" data-mfb-toggle="hover">
	  <li class="mfb-component__wrap">
	    <a href="#" class="mfb-component__button--main" >
	      <i class="mfb-component__main-icon--resting ion-plus-round">语音</i>
	      <i class="mfb-component__main-icon--active ion-close-round"><img src="http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJRmN2LQ0g82D7Nlzkw7oPKTMW6f2uxbaIXVtPWIgLq8urkkFsUArqF4JNdWfdQ6KjG9HqCaNRr6g/0"  style="width: 45px;
    border-radius: 25px;
    margin-top: 5px;"></i>
	    </a>
	    <ul class="mfb-component__list">
	    	<%
	    	Iterator it = soundColl.iterator();
	    	int j=0;
	    	while(it.hasNext()){
	  			CardSound s = (CardSound)it.next();
	    		  %>
			      <li>
			        <a href="#" soundId="<%=s.getSoundId() %>" 
			            data-mfb-label="Child Button <%=++j %>" class="mfb-component__button--child">
			          <i class="mfb-component__child-icon ion-social-twitter">语音<%=j %></i>
			        </a>
			      </li>
			      <%
			}%>
	    </ul>
	    
	  </li>
	</ul>
	
	
	<%} %>
	
	<script src="js/jquery.js"></script>
	<script src="js/audioplayer.js"></script>
	<script>$( function() { $( 'audio' ).audioPlayer(); } );</script>
	<script type="text/javascript" src="js/dialog.js"></script>
	<script type="text/javascript">
	function load()
	{
		
	}
	
	$(function(){
		var picId=$("#card_1").attr("picId");;
		$(".card").click(function(){
        	var tempSrc1 =$("#card_1").attr("src");
        	var tempSrc2 =$(this).attr("src");
        	$(this).attr("src",tempSrc1);
        	$("#card_1").attr("src",tempSrc2);
        	picId=$(this).attr("picId");;
	    })
	    $('#removeImg').on("click", function () {
	    	var tagId = $(this).attr("tagId");
	    	$.dialog({
	    	       type : 'confirm',
	    	       onClickOk : function(){
	    	    	   window.location.href='cardlist.html?static=t&cardId=<%=c.getCardId()%>&delete=t&picId='+picId;
	    	       },
	    	       onClickCancel : function(){        		
	    	       },
	    	       contentHtml : '<p>您确定要删除这张图片吗？</p> <p>删除后图片将无法恢复</p>'
	    	    });
	    });
		
		
		//播放语音
		$('.mfb-component__button--child').on("click", function () {
			soundId = "sound_"+$(this).attr("soundId");
			document.getElementById(soundId).play();
		});
	})
	</script>

</body></html>