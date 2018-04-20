<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
System.out.println("cardjsp");
Card c = (Card)request.getAttribute("card");
Collection cardColl = (Collection)request.getAttribute("cardColl");
CardPic p = (CardPic)cardColl.iterator().next();
Collection meaningColl = c.cardMeaningColl;
Collection soundColl = c.cardSoundColl;
long languageId = 0;
boolean autoPlay=false;
if (request.getSession().getAttribute("languageId") != null) {
	languageId = Long.parseLong(request.getSession().getAttribute("languageId").toString());
}
long tagId=0;
if(request.getParameter("tagId")!=null)
	tagId = Long.parseLong(request.getParameter("tagId"));
else if(request.getSession().getAttribute("tagId")!=null)
	tagId = Long.parseLong(request.getSession().getAttribute("tagId").toString());

StringBuffer displayMeaning=new StringBuffer("");
if(meaningColl!=null&&meaningColl.size()>0){
		Iterator it = meaningColl.iterator();
		while(it.hasNext()){
			CardMeaning cm = (CardMeaning)it.next();
			displayMeaning.append(cm.getMeaning());
			displayMeaning.append("   ");
		}
}
int headlength=3;
if(displayMeaning.length()>4)
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
	<link rel="stylesheet" href="css/audioplayer.css" />
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
		            <img src="img/car.png" class="headpng">
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
		            			a = 25;%>
		            		<span class="imgspan" style="width:<%=a%>%">
		            			<a href="#">
		            				<img alt="" src="<%=cp.getImgurl() %>" style="margin-top:10px;width:90%;" class="card" id="card_<%=i%>" picId="<%=cp.getPicId()%>">
		            			</a>
		            		</span>
    						<%
		            	}
		            }
		            %>
    		</p>
    		
    		
    		<!--             语音              -->
		    <span id="wrapper">
		    	<%
				if(soundColl!=null&&soundColl.size()>0){
			  		Iterator it = soundColl.iterator();
			  		while(it.hasNext()){
			  			CardSound s = (CardSound)it.next();
			  			//if(s.getLanguageId()!=languageId)
			  			//	continue;
			  			%>
						<audio preload="auto" controls>
							<source src="/<%=s.getSound() %>">
						</audio>
						<%
					}
				}%>
			</span>
			           
		</section>
		
	</div>
	
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
	})
	</script>

</body></html>