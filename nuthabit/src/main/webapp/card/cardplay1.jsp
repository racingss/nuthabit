<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Card c = (Card)request.getAttribute("card");
Collection cardColl = (Collection)request.getAttribute("cardColl");
CardPic p = (CardPic)cardColl.iterator().next();
Collection meaningColl = c.cardMeaningColl;
Collection soundColl = c.cardSoundColl;

//切换语言
long languageId = new LanguageHttp().getLanguageId(request);
boolean autoPlay=false;


Kehu k = new KehuUtil().getKehu(request, response);

String autoUrl="/diandian/";
String tempUrl="/card/cardlist.html?page=t&cardId=";
Iterator recentIt = new CardDAO().getCardListByAge(k.b.getAge(), 1).iterator();
int temp=0;
while(recentIt.hasNext() && temp++<8){
	Card tempC = (Card)recentIt.next();
	if(request.getParameter("auto")==null){
		if(c.getCardId()==tempC.getCardId())
			continue;
		autoUrl = tempUrl+tempC.getCardId()+"&auto=t";
		break;
	}else{
		if(c.getCardId()==tempC.getCardId()){
			if(recentIt.hasNext()){
				tempC = (Card)recentIt.next();
				autoUrl = tempUrl+tempC.getCardId()+"&auto=t";
				break;
			}else{
				autoUrl=tempUrl+"2221&auto=t";;
			}
		} 
	}
}
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
    .littleMenu, .audiohidden, .audiohidden2{
    	display: block;
	    width: 24px;
	    height: 24px;
	    background: url(img/sound30.png);
	    position: absolute;
	    top: 1%;	    
    }
    
    
    .carddetail{
    	border-radius: .1rem;
	    /* padding: 10px; */
	    box-shadow: 0px 0.08rem 0.3rem rgba(0, 0, 0, 0.1);
	    /* display: inline-block; */
	    margin: 3%;
	    text-align: center;
	    width: 90%;
	    //position: absolute;
	    background: #fffefe;
	    opacity: 0.8;
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
    
    
.bookli{
	overflow: hidden;
    //width: 90px;
    height: 50px;
    border-radius: 5px;
    background: #FFF;
}

	.headbar{
		top: 10px;
    	left: 10px;
    	position: absolute;
	}
	.headbar a{
		//display:block;
		float:left;
		width:32px;
		height:32px;
		margin-left:1px;
	}
	.headmenu{
		display:none;
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
	.jiaoxueshili{
		display: block;
	    text-align: left;
	    font-size: 1rem;
	    margin: 0 1.5rem;
	    text-indent: 1.5rem;
	    line-height: 1.4rem;
	    background: #fae1d8;
	    color: #383834;
	    padding: 1rem;
	    border-radius: 1rem;
	    box-shadow: 0px 0.4rem 0.5rem rgba(0, 0, 0, 0.1);
	}
	.slide{
		position: absolute;
	}
	</style>  
	<script src="assets/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	
	<script type="text/javascript">
	var index=1;
	var max_index=<%=cardColl.size()%>;
	var times=8500;
	
	function load()
	{
		setTimeout(function(){
			if(document.getElementById("sound_1")!=null)
				audioAutoPlay1("sound_1");},1000);
		setTimeout(function(){
			if(document.getElementById("effect_1")!=null)
				audioAutoPlay1("effect_1");},4000);
		
		t=setTimeout("move()",times);
	}
	
	function audioAutoPlay1(id){
	    var audio = document.getElementById(id);
	    audio.load();
	    audio.play();
	    document.addEventListener("WeixinJSBridgeReady", function () {
	            audio.play();
	    }, false);
	}
	
	function audioAutoPlay(id){
	    var audio = document.getElementById(id),
	        play = function(){
	        audio.play();
	        document.removeEventListener("touchstart",play, false);
	    };
	    audio.play();
	    document.addEventListener("WeixinJSBridgeReady", function () {//微信
	       play();
	    }, false);
	    document.addEventListener("YixinJSBridgeReady", function() {//易信
	              play();
	        }, false);
	    document.addEventListener("touchstart",play, false);
	}
	
	function move(){
		$("#slide_"+index).animate({right:'200%',opacity:'0'},1000);
		//$("#slide_"+index).animate({left:'100%',opacity:'0.4'},100);
		index=index+1;
		$("#slide_"+index).animate({left:'0'},1500);
		setTimeout(function(){
			if(document.getElementById("sound_"+index)!=null)
				audioAutoPlay1("sound_"+index);},1500);
		setTimeout(function(){
			if(document.getElementById("effect_"+index)!=null)
				audioAutoPlay1("effect_"+index);},4500);
		if(index<=max_index){
			setTimeout("move()",times);
		}else{
			$(".regV").show();
			end();
		}
	}
	
	var daojishi=7;
	var stopFlag=0;
	
	function end(){
		if(stopFlag==0){
			if(daojishi>0){
				$("#daojishi").text(daojishi);
				daojishi-=1;
				setTimeout("end()",1000);
			}else{
				//alert("时间到了");
				location.href="<%=autoUrl%>";
				return;
			}	
		}
		
	}
	
	
	
	$(function(){
		
		
		
		
		//播放语音
		$('.playsound').on("click", function () {
			soundId = "sound_"+$(this).attr("index");
			//this.play();
			document.getElementById(soundId).play();
		});
		
		
		$("#subi7").click(function(){
			   $(".regV").hide();
			   stopFlag=1;
		})
			
		
		
		//播放音效
		$('.effectimg,.effecthidden').on("click", function () {
			i = $(this).attr("index");
			$('.effecthidden').css({'box-shadow':'0rem'});
			setTimeout(function(){
				if(document.getElementById("effect_"+i)!=null)
					audioAutoPlay1("effect_"+i);},100);
			setTimeout(function(){ $('.effecthidden').css({'box-shadow':'0.2rem 0.2rem 0.3rem rgba(0, 0, 0, 0.1)'}); }, 2000);
		});
		
		//0表示已收藏
		var favFlag=<%=request.getAttribute("isFav").toString()%>;
		
		
		$('.favImg').on("click", function () {
			if(favFlag==0){
				$.ajax({
					url: 'cardlist.html?cardId=<%=c.getCardId()%>&favdelete=t',
					dateType:'json',
				    success: function(data){
				    	alert('<%=Menu.getMenu("cancle_fav_succ", languageId) %>');
				    }
			    });
				$("#shoucang_txt").text('<%=Menu.getMenu("menu_fav_cancle", languageId) %>');
				//$("#shoucangImg").css({'background':'url(img/f4.png)'});
				favFlag=1;
			}else{
				$.ajax({
					url: 'cardlist.html?cardId=<%=c.getCardId()%>&favcard=t',
					dateType:'json',
				    success: function(data){
				    	alert('<%=Menu.getMenu("fav_succ", languageId) %>');
				    }
			   });
			   $("#shoucang_txt").text('<%=Menu.getMenu("menu_fav", languageId) %>');
			   //$("#shoucangImg").css({'background':'url(img/f4.png)'});
			   favFlag=0;
			}
	    });
		
		
		$('.scoreImg').on("click", function () {
			score = $(this).attr("score");
			$.ajax({
				url: 'cardlist.html?cardId=<%=c.getCardId()%>&score='+score,
				dateType:'json',
			    success: function(data){
			    	alert('<%=Menu.getMenu("ganxie_pingfeng", languageId) %>');
			    }
		    });
	    });
		
		$('.scoreHref').on("click", function () {
			score = $(this).attr("score");
			$.ajax({
				url: 'cardlist.html?cardId=<%=c.getCardId()%>&score='+score,
				dateType:'json',
			    success: function(data){
			    	alert('<%=Menu.getMenu("ganxie_pingfeng", languageId) %>');
			    }
		    });
	    });
		
		$('#submitcomm').on("click", function () {
			$.post("/card/cardlist.html",
					  {
						comm:document.getElementById('comm').value,
						cardId:'<%=c.getCardId()%>'
					  },
					  function(data,status){
						  alert('<%=Menu.getMenu("tijiao_chenggong", languageId) %>');
						   
			 });
	    });
		
		
		
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
        			<img alt="" src="<%=cp.getImgurl() %>" index="<%=index %>" picId="<%=cp.getPicId() %>" next="2" pre="0" style="width:100%;"  class="pic_<%=cp.getPicId()%> effectimg">
        			<%if(picColl.size()>1){ %>
        			    
        			    
        			    <!--       箭头        -->
	        			<a class="pichidden" mainPicId="<%=cp.getPicId() %>" picId="<%=cp.getPicId() %>" style="background: url(img/left.png);" id="left_<%=cp.getPicId() %>"></a>
	        			<a href="#" class="pichiddenright" mainPicId="<%=cp.getPicId() %>" picId="<%=cp.getPicId() %>" style="background: url(img/right.png);right: 0;" id="left_<%=cp.getPicId() %>"></a>
	        			<!--           幅图                   -->
	        			<div class="inc-scroll-landscape-container">
						    <div class="inc-scroll-landscape-content">
						        <ul>
						        	<%
						        	int i=0;
						        	Iterator picIt = picColl.iterator();
						        	while(picIt.hasNext()){
						        		CardPic fpic = (CardPic)picIt.next();
						        		++i;
						        		if(picColl.size()==1)
						        			continue;
						        	%>
						            <li class="bookli">
						            	<img alt="" picId="<%=fpic.getPicId() %>" src="<%=fpic.getImgurl()%>" onload="DrawImage(this, 50, 50)" id="futu_<%=cp.getPicId() %>_<%=i %>" next="<%=i+1%>" pre="<%=i-1 %>"  />
						            </li>
						            <%
						        	}%>
						        </ul>
						     </div>
						 </div> 
	        			<%
	        			
       				}
        			
        			
		    		if(request.getSession().getAttribute("wordFlag")==null ||request.getSession().getAttribute("wordFlag").toString().equals("0") ){
		    			int l_i=0;
		    				CardMeaning cm = CardMeaning.getStaticCard(cp.getPicId(), languageId);
		    				%>
		    				<div class="carddetail" style="bottom: 0;">
						            
						            <!--                   文字左侧                     -->
						            <%
						            double defaultSize=2.2;
						            if(true){
							            String display = null;
							            
							            if(cm!=null)
							            	display = cm.getMeaning();
							            else
							            	display = c.getMeaning(languageId, c.getCardId());
							            if(display!=null){
							            	if(display.length()>30)
							            		defaultSize=1;
							            	else if(display.length()>15)
							            		defaultSize=1.1;
							            	else if(display.length()>10)
							            		defaultSize=1.3;
							            	else if(display.length()>6)
							            		defaultSize=1.7;
							            }
							            %>
							    		<div style="font-size:<%=defaultSize%>rem;" class="wenzidiv">
							    			<%
							    			Collection picSoundColl = CardSound.getSoundCollByPicId(cp.getPicId());
					    					Iterator picSoundIt = picSoundColl.iterator();
					    					soundI++;
					    					boolean soundFlag=true;
					    					%>
							    			<a href="#" index="<%=index %>" style="color:#524f4f" class="playsound">
							    				<%=display %>
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
					    				</div>
					    			<%} %>
					    			
					    			
				    		</div>		
		    				<%		    			
		    		}
		    		%>
		    		<div>
			    		<a class="love favhead" href="#"></a>
						<a class="happy favhead" href="#"></a>
						<a class="nogood favhead" href="#"></a>
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