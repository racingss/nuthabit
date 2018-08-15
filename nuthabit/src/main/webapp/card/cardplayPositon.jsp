<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Card c = (Card)request.getAttribute("card");
Collection cardColl = (Collection)request.getAttribute("cardColl");

//切换语言
long languageId = new LanguageHttp().getLanguageId(request);
long languageId_2 = new LanguageHttp().getLanguageId_2(request);

Kehu k = new KehuUtil().getKehu(request, response);

%>    
<!DOCTYPE html>
<html lang="zh" class="fsvs"><head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, user-scalable=no">
	<title><%=Menu.getTitle(languageId) %></title>
	<link rel="stylesheet" href="css/card.css">
	<link rel="stylesheet" href="css/regV.css">
	<link rel="stylesheet" href="assets/css/default.css">
	<link rel="stylesheet" href="assets/css/style.css">
	<style type="text/css">
    .footbatbut{
    	/* padding: 0px 5px;
    	font-size: 20px;
    	font-style: italic; */
    	background-color: #f49731;
	    border-radius: 0.5rem;
	    margin: 0.3em;
	    padding: 0.5em;
	    box-shadow: 0px 0.4rem 0.5rem rgba(0, 0, 0, 0.1);
	    display: inline-block;
	    color: white;
	    text-decoration: none;
	    font-size: 1em;
    }

    .carddetail{
    	color: #524f4f;
	    font-size: 1.8rem;
	    position: absolute;
	    top: 40%;
	    z-index: 99999;
	    left: 20%;
	    box-shadow: 0.2rem 0.2rem 0.3rem rgba(0, 0, 0, 0.1);
	    padding: 0.5rem;
	    border-radius: 1rem;
    }
    
    .wenzidiv{
		color: #524f4f;
		width:100%;
		float:left;
		padding:3%;
	}
	a{
		text-decoration: none;
	}
	.timediv{
		width: 0%;
    	height: 0.1rem;
    	position: absolute;
    	bottom: 0;
    	background: red;
	}
    

	.ftmu{
		display:block;
		width:24%;
		float:left;
		text-align:center;
	}
	.ftmu_s1{
		display:block;
		width:100%;
		text-align:center;
	}
	.ftmu_s2{
		text-align: center;
		  color: #FFF;
		  background: #f49731;
		  padding: 0.1rem 0.3rem;
		  border-radius: 0.2rem;
		  font-size: 1rem;
		  box-shadow: 0px 0.4rem 0.5rem rgba(0, 0, 0, 0.1);
	}
	.pingjiaspan{
		padding-bottom: .2rem;
		display: inline-block;
	}
	
	.scoreImg{
		display:inline-block;width:24px;height:24px;
	}

	.slide{
		position: absolute;
	}
	.playimg{
		width: 15%;
    	box-shadow: 0.2rem 0.2rem 0.3rem rgba(0, 0, 0, 0.1);
    	border-radius: 2rem;
	}
	.playbar{
	    bottom: 0.2rem;
	    margin: 10px;
	    width: 100%;
	    position: absolute;
	}
	</style>  
	<script src="assets/js/jquery-1.11.0.min.js"></script>
	
	<script type="text/javascript">
	
	$(function(){
		
		
		document.onmousemove = mouseMove;
		function mouseMove(ev){
		 ev = ev || window.event;
		 var mousePos = mousePosition(ev);
		}
		
		var zhutuWidth=document.getElementById('comm').clientWidth;
		var zhutuHeight=document.getElementById('comm').clientWidth;
		
		function mousePosition(event) {
		       var e = event || window.event;
		       var scrollX = document.documentElement.scrollLeft || document.body.scrollLeft;
		       var scrollY = document.documentElement.scrollTop || document.body.scrollTop;
		       var x = e.pageX || e.clientX + scrollX;
		       var y = e.pageY || e.clientY + scrollY;
		       //alert('x: ' + x/zhutuWidth*100 + '\ny: ' + y/zhutuHeight*100);
		       $(".carddetail").css({"left":x/zhutuWidth*100+"%","top":y/zhutuHeight*100+"%"});
		       return { 'x': x, 'y': y };
		}
		
	})
</script>

</head>
<body onload="load()">
    <div id="fsvs-body">
    		
    
    	
    	<%
    	if(true){
    		int soundI=1;
        	Iterator itPic = cardColl.iterator();
        	int index=0;
        	while(itPic.hasNext()){
        		index++;
        		CardPic cp = (CardPic)itPic.next();
        		Collection picColl = CardPic.getPicCollByMainPicId(cp.getPicId());
        		%>
        		
        		<!--             图片           -->
        		<div class="slide" id="slide_<%=index %>" index=<%=index %> style="<%if(index>1)out.print("left:100%");%>">
        		    <div style="position: relative;"> 
        			<a href="/diandian/" >
        				<img src="img/f2s.png" style="position: absolute;top: 1rem;left: 1rem;border-radius: 2rem;width: 45px;box-shadow: 0.2rem 0.2rem 0.3rem rgba(0, 0, 0, 0.1);"/>
        			</a>
        			<!--         音效           -->
        			<%
   					if(cp.getSound()!=null && cp.getSound().length()>2){
   						if(request.getSession().getAttribute("soundFlag")==null ||request.getSession().getAttribute("soundFlag").toString().equals("0")){
   					%>
   						<a style="background: url(frame/sound.gif);background-size: 45px 45px;display:inline-block;width:45px;height:45px;position: absolute;top: 1rem;right: 1rem;box-shadow: 0.2rem 0.2rem 0.3rem rgba(0, 0, 0, 0.1);border-radius: 0.5rem;" class="effecthidden" index="<%=index%>"></a>
   						<audio preload="auto" controls id="effect_<%=index %>" style="display:none">
							<source src="/<%=cp.getSound() %>">
						</audio>
					<%
   						}
   					} %>       		
        		
	 				<!--         主图           -->        		
        			<img alt="" src="<%=cp.getImgurl() %>" index="<%=index %>" picId="<%=cp.getPicId() %>" next="2" pre="0" style="width:100%;border:1px solid red;"  class="pic_<%=cp.getPicId()%> effectimg">
        			<%
		
		    		if(request.getSession().getAttribute("wordFlag")==null ||request.getSession().getAttribute("wordFlag").toString().equals("0") ){
		    			int l_i=0;
		    				CardMeaning cm = CardMeaning.getStaticCard(cp.getPicId(), languageId);
		    				%>
		    				
						            
						            <!--                   文字左侧                     -->
						            <%
						            double defaultSize=1.8;
						            if(true){
							            %>
							    			<%
							    			Collection picSoundColl = CardSound.getSoundCollByPicId(cp.getPicId());
					    					Iterator picSoundIt = picSoundColl.iterator();
					    					soundI++;
					    					boolean soundFlag=true;
					    					%>
							    			<a href="#" index="<%=index %>" style="color:#524f4f;font-size:<%=defaultSize%>rem;" class="playsound carddetail">
							    				<%=cm.getMeaning() %>
							    			</a>
							    			<%
					    					while(picSoundIt.hasNext()){
					    						CardSound cs = (CardSound)picSoundIt.next();
					    						if(cs.getLanguageId()!=languageId)
					    							continue;
					    						soundFlag=false;
					    						if(request.getSession().getAttribute("soundFlag")==null ||request.getSession().getAttribute("soundFlag").toString().equals("0")){
					    						%>
						    						<audio class="playsound" preload="auto" controls soundId="<%=index %>" id="sound_<%=index %>" style="display:none">
														<source src="<%=cs.getSound() %>">
													</audio>	
												<%
					    						}
					    					}
					    					%>
					    
					    			<%} %>
					    			
		    				<%		    			
		    		}
		    		%>
		    		</div>
		    		
		    		<div class="playbar">
		    			<a href="#" ><img src="play/back.png" class="playimg"/></a>
		    			<a href="#" ><img src="play/rewind.png" class="playimg"/></a>
		    			<a href="#" ><img src="play/play.png" class="playimg"/></a>
		    			<a href="#" ><img src="play/stop.png" class="playimg"/></a>
		    			<a href="#" ><img src="play/forward.png" class="playimg"/></a>
		    			<a href="#" ><img src="play/next.png" class="playimg"/></a>
		    		</div>
		    		
		    		
		    		
		    		<div class="timediv">
			    	</div>
        			
        			
        		</div>
        		
        		
        		<%
        	}
        }	
    	%>
    	
    	<!--                 尾页                      -->
    	<div class="slide" id="slide_<%=cardColl.size()+1 %>" index="<%=cardColl.size()+1%>" style="left:100%;background: #eb7347;" >
    					
    					<p style="text-align: center;font-size: 1.5rem;margin-top:2rem;">
    						<%=Menu.getMenu("qingpingfen", languageId) %>
    					</p>
    					<p style="text-align: left;font-size: 1.2rem;padding-left: 4rem;margin-top: -0.5rem;">
    						<a  class="scoreImg" score="3" href="#" style="display: inline-block;background: url(frame/star.png);"></a>
    						<a  class="scoreImg" score="3" href="#" style="display: inline-block;background: url(frame/star.png);"></a>
    						<a  class="scoreImg" score="3" href="#" style="display: inline-block;background: url(frame/star.png);"></a>
    						<span class="pingjiaspan" ><a href="#" class="scoreHref" score="3"><%=Menu.getMenu("henxihuan", languageId) %></a></span><br/>
    						<a  class="scoreImg" score="2" href="#" style="display: inline-block;background: url(frame/star.png);"></a>
    						<a  class="scoreImg" score="2" href="#" style="display: inline-block;background: url(frame/star.png);"></a>
    						<a  class="scoreImg" score="2" href="#" style="display: inline-block;background: url(frame/star1.png);"></a>
    						<span class="pingjiaspan" ><a href="#" class="scoreHref" score="2"><%=Menu.getMenu("youdaigaijin", languageId) %></a></span><br/>
    						<a  class="scoreImg" score="1" href="#" style="display: inline-block;background: url(frame/star.png);"></a>
    						<a  class="scoreImg" score="1" href="#" style="display: inline-block;background: url(frame/star1.png);"></a>
    						<a  class="scoreImg" score="1" href="#" style="display: inline-block;background: url(frame/star1.png);"></a>
    						<span class="pingjiaspan" ><a href="#" class="scoreHref" score="1"><%=Menu.getMenu("buxihuan", languageId) %></a></span><br/>
    					</p>
    					<p style="text-align: center;font-size: 1.5rem;margin-top:2rem;">
    						<%=Menu.getMenu("fabiaoxiangfa", languageId) %><br>
    						<form style="text-align: center;">
    							<textarea id="comm" rows="" cols="" style="width: 80%;color: #000;font-size: 1rem;margin-top:-0.5rem"></textarea>
    							<br/>
    							<a class="footbatbut" id="submitcomm" href="#" style="padding: 0.2rem 2rem;border-radius: 0.2rem;font-size: 1rem;margin-top:1.5rem;">
							    	<%=Menu.getMenu("tijiao", languageId) %>
						    	</a>
    						</form>
    					</p>
    					<!--p style="text-align: center;font-size: 22px;margin-top:15px">
    						&nbsp;&nbsp;<%=Menu.getMenu("u_just_read", languageId) %>：
    						<br/>
    						<img alt="" src="<%=c.getImg()%>" style="width:25%;margin: 5px;border-radius: 10px;" class="lastimg">
    					</p-->
    					<!--p style="text-align: center;font-size: 22px;margin-top:15px">
    						<%=Menu.getMenu("test_rightnow", languageId) %>
    						<br/>
    						<a  href="test_iop.html?cardId=<%=c.getCardId() %>" style="background: url(img/file.png);display:inline-block;width:64px;height:64px"></a>
    					</p-->
    					
    					
    					<div style="bottom: 0;margin: 10px;width: 100%;margin-top: 2rem;">
    							<div class="ftmu">
    								<span class="ftmu_s1">
    									<a  href="cardlist.html?static=t&cardId=<%=c.getCardId() %>&page=t" style="background: url(img/f1.png);background-size: 64px 64px;display:inline-block;width:64px;height:64px"></a>
    								</span>
    								<span class="ftmu_s2">
    									<a  href="cardlist.html?static=t&cardId=<%=c.getCardId() %>&page=t">
    										重读
    									</a>
    								</span>
    							</div>
    							<div class="ftmu">
    								<span class="ftmu_s1">
    									<a  href="/diandian/?recomm=t" style="background: url(img/f2.png);display:inline-block;width:64px;height:64px;background-size: 64px 64px"></a>
    								</span>
    								<span class="ftmu_s2">
    									<a  href="/diandian/?recomm=t">
    										<%=Menu.getMenu("home", languageId) %>
    									</a>	
    								</span>
    							</div>
    							<div class="ftmu">
    								<span class="ftmu_s1">
    									<a  href="test_iop.html?cardId=<%=c.getCardId() %>" style="background: url(img/f3.png);display:inline-block;width:64px;height:64px;background-size: 64px 64px"></a>
    								</span>
    								<span class="ftmu_s2">
    									<a  href="test_iop.html?cardId=<%=c.getCardId() %>">
    										<%=Menu.getMenu("test", languageId) %>
    									</a>
    								</span>
    							</div>
    							<div class="ftmu">
    								
    									<%
						    			if(Long.parseLong(request.getAttribute("isFav").toString())==0){
						    			%>
    										<span class="ftmu_s1">
		    									<a  href="#" class="favImg" id="shoucangImg" style="background: url(img/f4.png);display:inline-block;width:64px;height:64px;background-size: 64px 64px"></a>
		    								</span>
		    								<span class="ftmu_s2" id="shoucang_txt">
		    									<a  href="#" class="favImg">
		    										<%=Menu.getMenu("menu_fav", languageId) %>
		    									</a>
		    								</span>
    									<%
    									}else{
						    			%>
						    				<span class="ftmu_s1">
		    									<a  href="#" class="favImg" id="shoucangImg" style="background: url(img/f4.png);display:inline-block;width:64px;height:64px;background-size: 64px 64px"></a>
		    								</span>
		    								<span class="ftmu_s2" id="shoucang_txt">
		    									<a  href="#" class="favImg">
		    										<%=Menu.getMenu("menu_fav_cancle", languageId) %>
		    									</a>
		    								</span>
						    			<%
						    		}
						    		%>	
    								
    							</div>
    					
						    		
						    		
						    		
					    </div>
    			
    			</div>
    	    			
    </div>
    
    
    
    
    <ol class="regV" style="display:none">
			<div>
				<div class="hd" style="height: 2.5rem;">
					<div class="i1" style="width:3rem;height:3rem;"></div>
					<div class="i2">自动播放</div>
				</div>
				<div class="bd" style="height: 7.5rem">
					<div class="i6">
						<label>
							<div class="setupdiv" style="text-align: center;padding: 0.5rem;font-size:1.1rem;">
								。。。<span id="daojishi" style="font-size: 1.5rem;padding-bottom: .2rem;color: red;">5</span>秒。。。
								<br/>
								后开始自动播放
							</div>
						</label>
					</div>
					<a class="i7" id="subi7" style="width: 60%;height: 1.5rem;font-size: 1rem;padding-top: 0.2rem;color: black;">停止</a>
				</div>
			</div>
	</ol>

    







		

    
</body>
</html>