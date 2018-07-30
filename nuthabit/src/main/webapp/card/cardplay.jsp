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
long languageId_2 = new LanguageHttp().getLanguageId_2(request);


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

int slide=1;

Kehu k = new KehuUtil().getKehu(request, response);
%>    
<!DOCTYPE html>
<html lang="zh" class="fsvs"><head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, user-scalable=no">
	<title><%=Menu.getTitle(languageId) %></title>
	<link rel="stylesheet" href="css/list_style.css">
	<link rel="stylesheet" href="css/dialog.css">
	<link rel="stylesheet" href="css/card.css">
	<link rel="stylesheet" href="css/audioplayer.css" />
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
    .littleMenu, .audiohidden{
    	display: block;
	    width: 24px;
	    height: 24px;
	    float: left;
    }
    .pichidden, .pichiddenright{
    	display: block;
	    width: 32px;
	    height: 32px;
	    position: absolute;
	    top: 100px;
	}
    
    .carddetail{
    	border-radius: .1rem;
	    padding: 10px;
	    box-shadow: 0px 0.08rem 0.3rem rgba(0, 0, 0, 0.1);
	    /* display: inline-block; */
	    margin: 10px;
	    text-align: center;
	    width: 95%;
	    position: absolute;
	    background: #fffefe;
	    opacity: 0.8;
    }
    
    .inc-scroll-landscape-container { 
	width: 100%; 
	overflow: hidden; 
}
.inc-scroll-landscape-container > .inc-scroll-landscape-content {
   
}
.inc-scroll-landscape-container > .inc-scroll-landscape-content > ul {
	margin: 0 5px;
}
.inc-scroll-landscape-container > .inc-scroll-landscape-content > ul > li { 
	display: inline-block; 
    text-align: center; 
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
	
    </style>  
	<script src="assets/js/jquery-1.11.0.min.js"></script>
   <script src="assets/js/prismjs.js"></script>
   <script src="assets/js/fsvs.js"></script>
   <script src="assets/js/main.js"></script>
   <script type="text/javascript" src="js/dialog.js"></script>
   <script type="text/javascript">
	//获取缩略图盒子宽高后再执行缩放
	function DrawImage_box(ImgID) {
	    var width_img=$("#imgBox").width();
	    var height_img=$("#imgBox").height();
	    DrawImage(ImgID, width_img, height_img);
	}
	
	
	//图片缩放居中核心功能
	function DrawImage(ImgID, width_s, height_s) {
	    var image = new Image();
	    image.src = ImgID.src;
	    
	    if (image.width > 0 && image.height > 0) {
	    	flag = true;
	        if (image.width / image.height <= width_s / height_s) {
	            ImgID.width = width_s;
	            var height = (image.height * width_s) / image.width;
	            ImgID.height = height;
	            ImgID.style.marginTop = -(height - height_s)/2 + "px";
	        } else {
	            ImgID.height = height_s;
	            var width = (image.width * height_s) / image.height;
	            ImgID.width = width;
	            ImgID.style.marginLeft = -(width - width_s)/2 + "px";
	        }
	    }
	}
</script> 
</head>
<body onload="load()">
    <div id="fsvs-body">
    		
    		    <!--                 首页                      -->
    			<div class="slide">
    					<p style="text-align: center;font-size: 22px;margin-top:20px;">
    						&nbsp;&nbsp;<%=Menu.getMenu("begin_to_read", languageId) %>：
    						<br/>
    						<img alt="" src="<%=c.getImg()%>" style="width:25%;margin: 20px;border-radius: 10px;">
    						<br/>
    						<%=Menu.getMenu("finge_up", languageId) %>
    						<br/>
    						<img alt="" src="img/touch.png" style="width: 25%;background: #FFF;padding: 10px;margin: 20px;border-radius: 10px;">
    					</p>
    					
    					<div class="carddetail" style="bottom: 0;">
							    	<a class="footbatbut" href="/diandian/" style="color: 000;">
							    			<%=Menu.getMenu("back_to_home", languageId) %>
						    		</a>
						    		<a  class="footbatbut" href="language.html?type=languageId&cardId=<%=c.getCardId() %>" style="color: 000;">
						    				<%=Menu.getMenu("language", languageId) %>
						    		</a>
						    		<%
						    		if(k.getId()==c.getkId() ||k.getGuanlibiaoji()==1){
						    		%>
						    			<a class="footbatbut" href="carddetail.html?cardId=<%=c.getCardId() %>" style="color: 000;">
								    		 <%=Menu.getMenu("weihu", languageId) %>
							    		</a>
						    		<%	
						    		}else{
						    		%>
							    		<a class="footbatbut" href="test_iop.html?cardId=<%=c.getCardId() %>" style="color: 000;">
								    			<%=Menu.getMenu("test", languageId) %>
							    		</a>
						    		<%} %>
					    </div>
    			</div>
    
    
    
    	
    	<%
    	if(true){
    		int soundI=1;
        	Iterator itPic = cardColl.iterator();
        	while(itPic.hasNext()){
        		slide++;
        		CardPic cp = (CardPic)itPic.next();
        		Collection picColl = CardPic.getPicCollByMainPicId(cp.getPicId());
        		%>
        		
        		<!--             图片           -->
        		<div class="slide" style="background:000;"> 
        			
        			<!--         音效           -->
        			<%
   					if(cp.getSound()!=null && cp.getSound().length()>2){
   					%>
   						<a style="background: url(frame/trumpet.png);display:inline-block;width:45px;height:45px;position: absolute;top: 1rem;right: 1rem;box-shadow: 0.2rem 0.2rem 0.3rem rgba(0, 0, 0, 0.1);border-radius: 0.5rem;" class="effecthidden" picId="<%=cp.getPicId()%>"></a>
  						<audio preload="auto" controls id="effect_<%=cp.getPicId() %>" style="display:none">
							<source src="/<%=cp.getSound() %>">
						</audio>
					<%} %>       		
        		
	 				<!--         主图           -->        		
        			<img alt="" src="<%=cp.getImgurl() %>" picId="<%=cp.getPicId() %>" next="2" pre="0" style="width:100%;"  class="pic_<%=cp.getPicId()%>">
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
						            
						            <!--                   文字                     -->
						            <%
						            String display = null;
						            double defaultSize=3.5;
						            if(cm!=null)
						            	display = cm.getMeaning();
						            else
						            	display = c.getMeaning(languageId, c.getCardId());
						            if(display!=null){
						            	if(display.length()>30)
						            		defaultSize=1.5;
						            	else if(display.length()>20)
						            		defaultSize=1.8;
						            	else if(display.length()>12)
						            		defaultSize=2.2;
						            	else if(display.length()>6)
						            		defaultSize=2.5;
						            }
						            %>
						    		<div style="font-size:<%=defaultSize%>rem;color: #524f4f;">
						    			<a href="language.html?type=languageId&cardId=<%=cp.getCardId() %>" style="color:#524f4f">
						    				<%=display %>
						    			</a>
						    			<%
				    					Collection picSoundColl = CardSound.getSoundCollByPicId(cp.getPicId());
				    					Iterator picSoundIt = picSoundColl.iterator();
				    					soundI++;
				    					boolean soundFlag=true;
				    					while(picSoundIt.hasNext()){
				    						CardSound cs = (CardSound)picSoundIt.next();
				    						if(cs.getLanguageId()!=languageId)
				    							continue;
				    						soundFlag=false;
				    						%>
				    						
				    						<%
				    						if(request.getSession().getAttribute("soundFlag")==null ||request.getSession().getAttribute("soundFlag").toString().equals("0")){
				    						%>  <a href="#" class="audiohidden" soundId="<%=soundI %>" style="background: url(img/sound30.png);float:right"></a>
					    						<audio <%if(soundI==1 ){%>autoplay="true"<%}%> preload="auto" controls id="sound_<%=soundI%>" style="display:none">
													<source src="<%=cs.getSound() %>">
												</audio>	
											<%
				    						}
				    					}
				    					if(soundFlag && cm!=null){
				    						// 如果是中英文重设需要抓取语音
				    						if (languageId == 0 || languageId == 1) {
				    							System.out.println("抓取语音了");
				    							new Thread(new BaiduTools(cm.getPicId(), languageId, c.getCardId(),
				    									cm.getMeaning(), request.getSession().getServletContext().getRealPath("/")))
				    											.start();
				    						}
				    					}
				    					%>
				    					<br/>
				    					<!--   第二语言     -->
				    					<a href="language.html?type=languageId_2&cardId=<%=cp.getCardId() %>" style="color:#524f4f;font-size:15px;float:right">
						    				<%
						    				if(languageId_2==-1 || languageId==languageId_2){
						    					out.print(Menu.getMenu("can_second_language", languageId));
						    				}else{
						    					cm = CardMeaning.getStaticCard(cp.getPicId(), languageId_2);
						    					if(cm!=null)out.print(cm.getMeaning());else out.print(c.getMeaning(languageId_2, c.getCardId())); 
						    				}
						    				%>
						    			</a>
				    					
				    				</div>
				    				
						    		
						    		
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
    			<div class="slide">
    					
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
    					
    					
    					<div style="bottom: 0;margin: 10px;width: 100%;margin-top: 4rem;">
    							<div class="ftmu">
    								<span class="ftmu_s1">
    									<a  href="cardlist.html?static=t&cardId=<%=c.getCardId() %>&play=<%=System.currentTimeMillis() %>" style="background: url(img/exchange.png);display:inline-block;width:64px;height:64px"></a>
    								</span>
    								<span class="ftmu_s2">
    									<%=Menu.getMenu("re_read", languageId) %>
    								</span>
    							</div>
    							<div class="ftmu">
    								<span class="ftmu_s1">
    									<a  href="/diandian/?recomm=t" style="background: url(img/home.png);display:inline-block;width:64px;height:64px"></a>
    								</span>
    								<span class="ftmu_s2">
    									<%=Menu.getMenu("home", languageId) %>
    								</span>
    							</div>
    							<div class="ftmu">
    								<span class="ftmu_s1">
    									<a  href="test_iop.html?cardId=<%=c.getCardId() %>" style="background: url(img/medical-result.png);display:inline-block;width:64px;height:64px"></a>
    								</span>
    								<span class="ftmu_s2">
    									<%=Menu.getMenu("test", languageId) %>
    								</span>
    							</div>
    							<div class="ftmu">
    								
    									<%
						    			if(Long.parseLong(request.getAttribute("isFav").toString())==0){
						    			%>
    										<span class="ftmu_s1">
		    									<a  href="#" class="favImg" id="shoucangImg" style="background: url(img/star2.png);display:inline-block;width:64px;height:64px"></a>
		    								</span>
		    								<span class="ftmu_s2" id="shoucang_txt">
		    									<%=Menu.getMenu("menu_fav", languageId) %>
		    								</span>
    									<%
    									}else{
						    			%>
						    				<span class="ftmu_s1">
		    									<a  href="#" class="favImg" id="shoucangImg" style="background: url(img/star3.png);display:inline-block;width:64px;height:64px"></a>
		    								</span>
		    								<span class="ftmu_s2" id="shoucang_txt">
		    									<%=Menu.getMenu("menu_fav_cancle", languageId) %>
		    								</span>
						    			<%
						    		}
						    		%>	
    								
    							</div>
    					
						    		
						    		
						    		
					    </div>
    			
    			</div>
    </div>

    





<script type="text/javascript">
	function load()
	{
	}
	
	$(function(){
		
		//播放语音
		$('.audiohidden').on("click", function () {
			soundId = "sound_"+$(this).attr("soundId");
			document.getElementById(soundId).play();
		});
		
		//播放音效
		$('.effecthidden').on("click", function () {
			soundId = "effect_"+$(this).attr("picId");
			$(this).css({'box-shadow':'0rem'});
			document.getElementById(soundId).play();
			setTimeout(function(){ $('.effecthidden').css({'box-shadow':'0.2rem 0.2rem 0.3rem rgba(0, 0, 0, 0.1)'}); }, 2000);
		});
		
		
		
		$(".pichidden").click(function(){
			var picId = $(this).attr("picId");
			var mainPicId = $(this).attr("mainPicId");
			var next = $(".pic_"+picId).attr("next");
			var pre = $(".pic_"+picId).attr("pre");
			console.log(next+","+pre);
			$(".pic_"+picId).attr("src",$("#futu_"+picId+"_"+pre).attr("src"));
			$("#remove_"+mainPicId).attr("picId",$("#futu_"+picId+"_"+pre).attr("picId"));
        	$(".pic_"+picId).attr("next",$("#futu_"+picId+"_"+pre).attr("next"));
        	$(".pic_"+picId).attr("pre",$("#futu_"+picId+"_"+pre).attr("pre"));
	    })
	    $(".pichiddenright").click(function(){
			var picId = $(this).attr("picId");
			var mainPicId = $(this).attr("mainPicId");
			var next = $(".pic_"+picId).attr("next");
			var pre = $(".pic_"+picId).attr("pre");
			console.log(picId);
			console.log(next+","+pre);
			$(".pic_"+picId).attr("src",$("#futu_"+picId+"_"+next).attr("src"));
			$("#remove_"+mainPicId).attr("picId",$("#futu_"+picId+"_"+next).attr("picId"));
        	$(".pic_"+picId).attr("next",$("#futu_"+picId+"_"+next).attr("next"));
        	$(".pic_"+picId).attr("pre",$("#futu_"+picId+"_"+next).attr("pre"));
	    })
	    
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
				$("#shoucangImg").css({'background':'url(img/star3.png)'});
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
			   $("#shoucangImg").css({'background':'url(img/star2.png)'});
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

		

    
</body>
</html>