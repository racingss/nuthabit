<%@page import="com.babycard.wx.*"%>
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

Kehu k = new KehuUtil().getKehu(request, response);

String autoplay="0";
if(request.getParameter("autoplay")!=null)
	autoplay="1";

String title= c.getMeaning(languageId,c.getCardId())+"_幼儿认知卡片_亲子教育好帮手_卡片点点为您精心准备";
String detail="卡片点点—幼儿语言启蒙教育平台，支持中英双语音，法德日韩俄等全球主流26种以上的语言";
String cardImg = "http://www.suyufuwu.com";
if(c.getImg().indexOf("gif")!=-1 && c.getSecondPic()!=null)
	cardImg+="/"+c.getSecondPic();
else
	cardImg+=c.getImg();

System.out.println(cardImg);
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=title%></title>
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
	.word{
		position: absolute;
	}
	.itemimg{
		width:100%;
		
	}
	.magrginbottom{
		margin-bottom: 5rem;
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
		margin-top:-4.2rem;
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
    	top: 9%;
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
	.headspan{
		display: inline-block;
	    width: 100%;
	    text-align: center;
	    font-weight: bold;
	    color: #584307;
	    font-size: 4rem;
	    padding-bottom: 4rem;
	}
	
	.cancelimg{
		top: 3%;
	    position: absolute;
	    left: 3%;
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
		bottom:0;
	}
	
	.level_foot{
		position: absolute;
		bottom:0;
		right:0;
		font-size: 2.5rem;
    	padding: 1rem;
	}
	.picarrow{
		position: absolute;
	    top: 50%;
	}
	.picarrow img{
		width:7rem;
	}
	.leftpicarrow{
		left: -10%;
	}
	.rightpicarrow{
    	left: 95%;
	}
	.headbar{
		position: absolute;
    	top: <%if(autoplay.equals("0"))out.print("0");else out.print("-10%");%>;
    	padding:1rem;
    	z-index:99999;
	}
	.hearbarimg{
		height:7rem;
	}
	.timediv{
		width: 0%;
    	height: 0.3rem;
    	position: absolute;
    	bottom: 0;
    	background: red;
	}
	.xialadiv{
		position: absolute;
    	top: -10%;
    	text-align:center;
    	z-index: 99999;
    	width: 100%;
    	display:block;
	}
</style>

</head>
<body onload="load()">
	<div class="page" style="background-size: cover;">
		<div class="xialadiv">
			<a href="#" class="xiala">
				<img alt="" src="/diandian/frame/shou.png" style="width:12rem;">
			</a>
		</div>
		<div class="headbar">
			<a href="/diandian/">
				<img alt="" src="/diandian/frame/h0.png" class="hearbarimg">
			</a>
			<a href="javascript:history.back();">
				<img alt="" src="/diandian/frame/h1.png" class="hearbarimg">
			</a>
			<a href="/card/cardlist.html?cardId=<%=c.getCardId() %>&pre=t" >
				<img alt="" src="/diandian/frame/h6.png" class="hearbarimg">
			</a>
			<a href="#" class="autoplay">
				<img alt="" src="/diandian/frame/h2<%
				if(request.getParameter("autoplay")!=null)
					out.print("_1");
				%>.png" class="hearbarimg autoimg">
			</a>
			<a href="/card/cardlist.html?cardId=<%=c.getCardId() %>&next=t" >
				<img alt="" src="/diandian/frame/h7.png" class="hearbarimg">
			</a>
			<a href="test_iop.html?cardId=<%=c.getCardId() %>" >
				<img alt="" src="/diandian/frame/h3.png" class="hearbarimg">
			</a>
			<a href="#">
				<img alt="" src="<%
						if(Long.parseLong(request.getAttribute("isFav").toString())==0){
							%>/diandian/frame/h4_1.png<%
						}else{
							%>/diandian/frame/h4.png<%
						}
				%>" class="hearbarimg favImg">
			</a>
			<%
    		if(k.getId()==c.getkId() ||k.getGuanlibiaoji()==1){
    		%>
			<a href="carddetail.html?cardId=<%=c.getCardId() %>" >
				<img alt="" src="/diandian/frame/h5.png" class="hearbarimg">
			</a>
			<%}else{ %>
			<a href="/card/cardlist.html?cardId=<%=c.getCardId() %>&languageId=<%if(languageId==0)out.print(1);else out.print(0); %>" >
				<img alt="" src="/diandian/frame/l<%=languageId %>.png" class="hearbarimg">
			</a>
			<%} %>
			
			
			
			
			
		</div>		
	
		<div class="board">
			<span class="headnum headspan">1/<%=nums %></span>
			<span class="level_foot">level <%=c.getAge() %></span>
			
			<%
    		if(false && (k.getId()==c.getkId() ||k.getGuanlibiaoji()==1)){
    		%>
    		<a href="/card/carddetail.html?cardId=<%=c.getCardId()%>">	
				<img src="/diandian/frame/footer-1_active.png" class="arrow return">
			</a>
			<%} %>
			
		    <%
		    CardPic first = (CardPic)cardColl.iterator().next();
		    %>
			<div class="mainboard boxShadow"  style="<%if(c.getShowType()==1)out.print("display:none");%>">
					<img src="<%=first.getImgurl()%>" class="itemimg magrginbottom" >
					<span class="spanline1"><%
					CardMeaning cmf1 =CardMeaning.getStaticCard(first.getPicId(), languageId);
					if(cmf1!=null)
						out.print(cmf1.getMeaning());
					%></span>
					<span class="spanline2"><%CardMeaning cmf2 =CardMeaning.getStaticCard(first.getPicId(), languageId_2);
					if(cmf2!=null)
						out.print(cmf2.getMeaning());
					%></span>
					<img class="cancelimg" src="/diandian/frame/cancel.png" style="<%if(c.getShowType()==0)out.print("display:none");%>">
					<%
					if(first.getSound()!=null && first.getSound().length()>2){
					%>
					<a href="#" class="effecthidden boxShadow">
					<%} %>
					</a>
					<div class="picarrow leftpicarrow" >
						<img alt="" src="/diandian/frame/left.png">
					</div>
					<div class="picarrow rightpicarrow" >
						<img alt="" src="/diandian/frame/right.png">
					</div>
			</div>
			
			<%
			int index=1;
			int j=1;
			Iterator picit = cardColl.iterator();
			while(picit.hasNext()){
				CardPic fpic = (CardPic)picit.next();
				%>
				<div class="item" id="item<%=fpic.index %>" index="<%=fpic.index %>" picId="<%=fpic.getPicId() %>" style="top: <%=fpic.getTopP()%>%;left: <%=fpic.getLeftP()%>%;width: <%=fpic.getWidthP()%>rem;<%if(c.getShowType()==0)out.print("display:none");%>">
					<img src="<%=fpic.getImgurl() %>" class="itemimg"  >
					<span class="spanline1"  style="margin-top:<%=fpic.getMarginTop()%>rem;"><%
					CardMeaning cm1 = CardMeaning.getStaticCard(fpic.getPicId(), languageId);
					if(cm1!=null)
						out.print(cm1.getMeaning());
					%></span>
					<span class="spanline2"  style="margin-top:<%=fpic.getMarginTop2()%>rem;" ><%
					CardMeaning cm2=CardMeaning.getStaticCard(fpic.getPicId(), languageId_2);
					if(cm2!=null)out.print(cm2.getMeaning());
					%></span>
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
				Iterator picSoundIt = picSoundColl.iterator();
				while(picSoundIt.hasNext()){
					CardSound cs = (CardSound)picSoundIt.next();
					if(cs.getLanguageId()!=1&&cs.getLanguageId()!=0)
						continue;
					if(request.getSession().getAttribute("soundFlag")==null ||request.getSession().getAttribute("soundFlag").toString().equals("0")){
						String sound = cs.getSound();
						if(sound!=null && sound.indexOf("sound")!=-1){
							sound="/card/"+sound;
						}
						if(cs.getLanguageId()==1){
							CardMeaning temp =CardMeaning.getStaticCard(fpic.getPicId(), languageId);
							if(temp!=null &&temp.getAmPhMp3()!=null){
								sound=temp.getAmPhMp3();
							}
						}
					%> 
						<audio controls id="sound_<%=fpic.index%>_<%=cs.getLanguageId()%>" style="display:none">
							<source src="<%=sound %>">
						</audio>	
					<%
					}
				}

				
				j++;
				
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
			
			
			
				
				
			
		
		</div>
		
	
		
		<div class="timediv">
    	</div>
	
	</div>
	

	
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	var index=<%=first.index%>;
	var autoplay=<%=autoplay%>;
	var times=8500;
	var dwidth=1;
	var stopFlag=0;
	
	function load()
	{
		t=doCurrent();
		
		if(autoplay==1){
			$(".xialadiv").css({top:'0%'});
		}else{
			setTimeout(function(){
				$(".xialadiv").animate({top:'0%'},2000);
				$(".headbar").animate({top:'-10%'},2000);
			},3000);	
		}
		
		
	}
	
	function doCurrent(){
		if(autoplay==1){
			t=playCurrent();
			$(".autoimg").attr("src","/diandian/frame/h2_1.png")
			setTimeout(function(){
				auto=next();
			},times);
			$(".timediv").css({'width':'0%'});
			$(".timediv").animate({width:'100%',opacity:'0.4'},times);
		}else{
			setTimeout(function(){
				document.getElementById("sound_"+index+"_<%=languageId%>").play();
			},500);
			
		}
	}
	
	function playCurrent(){
		setTimeout(function(){
			document.getElementById("sound_"+index+"_<%=languageId%>").play();
		},500);
		if(document.getElementById("effect_"+index)!=null){
			$(".effecthidden").show();
			if(autoplay==1){
				setTimeout(function(){
					if(document.getElementById("effect_"+index)!=null)
						audioAutoPlay1("effect_"+index);},4500);	
			}
		}else{
			$(".effecthidden").hide();
		}
	}
	
	function audioAutoPlay1(id){
	    var audio = document.getElementById(id);
	    audio.load();
	    audio.play();
	    document.addEventListener("WeixinJSBridgeReady", function () {
	            audio.play();
	    }, false);
	}
	
	function next(){
		if(stopFlag==1){
			stopFlag=0;
			return;
		}
			
		if(index<<%=nums%>){
			index=parseInt(index)+1;
			t=changeItem();
			if(autoplay==1){
				setTimeout(function(){
					auto=next();
				},times);
				
				if(dwidth==1){
					dwidth=0;
					$(".timediv").animate({width:'0%'},times);
				}else{
					dwidth=1;
					$(".timediv").animate({width:'100%'},times);
				}
			}
			return;
		}else{
			if(autoplay==0){
				location.href="/card/cardlist.html?cardId=<%=c.getCardId()%>&next=t";	
			}else{
				location.href="/card/cardlist.html?cardId=<%=c.getCardId()%>&next=t&autoplay=t";
			}
			
		}
		return;	
	}
	
	
	
	function pre(){
		if(index>1){
			index=index-1;
			t=changeItem();	
			return;
		}else{
			if(autoplay==0){
				location.href="/card/cardlist.html?cardId=<%=c.getCardId()%>&pre=t";	
			}else{
				location.href="/card/cardlist.html?cardId=<%=c.getCardId()%>&pre=t&autoplay";
			}
		}
	}
	
	function changeItem(){
		$(".headnum").text(index+"/<%=nums%>");
		$(".mainboard").children(".itemimg").attr("src",$("#item"+index).children("img").attr("src"));
		console.log($("#item"+index).children("spanline1").text());
		$(".mainboard").children(".spanline1").text($("#item"+index).children(".spanline1").text());
		$(".mainboard").children(".spanline2").text($("#item"+index).children(".spanline2").text());
		$(".mainboard").show();
		t=playCurrent();
	}
	
	$(function(){
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
		
		$(".xialadiv").click(function(){
			$(".xialadiv").animate({top:'-10%'},1000);
			$(".headbar").animate({top:'0%'},1000);
			
			setTimeout(function(){
				$(".xialadiv").animate({top:'0%'},2000);
				$(".headbar").animate({top:'-10%'},2000);
			},times);
		})			
			
		
		$(".spanline1").click(function(){
			document.getElementById("sound_"+index+"_<%=languageId%>").play();
		})
		
		$(".spanline2").click(function(){
			document.getElementById("sound_"+index+"_<%=languageId_2%>").play();
		})
		
		$(".item").click(function(){
			index=$(this).attr("index");
			$(".item").addClass("opacityShadow");
			
			t=changeItem();
		})
		
		$(".autoplay").click(function(){
			if(autoplay==0){
				autoplay=1;
				t=doCurrent();
			}else{
				autoplay=0;
				stopFlag=1;
				$(".autoimg").attr("src","/diandian/frame/h2.png")
			}
		})
		
		
		
		
		var windowHeight = $(window).height(),
		$body = $("body");
		$body.css("height", windowHeight); //重要代码
		
		$("body").on("touchstart", function(e) {
			//e.preventDefault();
			startX = e.originalEvent.changedTouches[0].pageX,
			startY = e.originalEvent.changedTouches[0].pageY;
		});
		
		
		$(".leftpicarrow").click(function(){
			t=pre();
		})
		
		$(".rightpicarrow").click(function(){
			t=next();
		})
		
		
		
		
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
					t=pre();
				}
				else if ( Math.abs(X) > Math.abs(Y) && X < -25 ) {
					t=next();
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
				$(this).attr("src","/diandian/frame/h4.png");
				favFlag=1;
			}else{
				$.ajax({
					url: 'cardlist.html?cardId=<%=c.getCardId()%>&favcard=t',
					dateType:'json',
				    success: function(data){
				    	alert('<%=Menu.getMenu("fav_succ", languageId) %>');
				    }
			   });
				$(this).attr("src","/diandian/frame/h4_1.png");
			   favFlag=0;
			}
	    });
		

	})
</script>	





<%


String url = "http://www.suyufuwu.com/card/cardlist.html?"+ (request.getQueryString()); 
Map<String, String> ret = new AccessToken().webSign(url,request, response);
String appId = KehuUtil.appId;
String timestamp = ret.get("timestamp");
String nonceStr = ret.get("nonceStr");
String signature = ret.get("signature");

if(request.getSession().getAttribute("subscribe")!=null && request.getSession().getAttribute("subscribe").toString().equals("0")){
	request.getSession().removeAttribute("subscribe");
	%>
	<script type="text/javascript">
	$(function(){
		setTimeout(function(){
			$(".itemimg").attr("src","/diandian/frame/erweima_gzz.jpg");
			$(".spanline1").text("请先关注我们的公众号");
			$(".spanline2").text("谢谢");	
		},15000);
	});
	</script>
	<%	
}


%>
<script typet="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script>
$(function(){
			wx.config({
                debug: false,////生产环境需要关闭debug模式
                appId: '<%=appId %>',
                timestamp: <%=timestamp %>,
                nonceStr: '<%=nonceStr%>',
                signature: '<%=signature%>',
                jsApiList: [//需要调用的JS接口列表
                    'checkJsApi',//判断当前客户端版本是否支持指定JS接口
                    'onMenuShareTimeline',//分享给好友
                    'onMenuShareAppMessage'//分享到朋友圈
                ]
            });   
});

wx.ready(function () {
    var link = window.location.href;
    var protocol = window.location.protocol;
    var host = window.location.host;
    //分享朋友圈
    wx.onMenuShareTimeline({
        title: '<%=title%>',
        link: link,
        imgUrl: '<%=cardImg%>',// 自定义图标
        trigger: function (res) {
            // 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回.
            //alert('click shared');
        },
        success: function (res) {
            //alert('shared success');
            //some thing you should do
        },
        cancel: function (res) {
            //alert('shared cancle');
        },
        fail: function (res) {
            alert(JSON.stringify(res));
        }
    });
    //分享给好友
    wx.onMenuShareAppMessage({
        title: '<%=title%>', // 分享标题
        desc: '<%=detail%>', // 分享描述
        link: link, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
        imgUrl: '<%=cardImg%>', // 自定义图标
        type: 'link', // 分享类型,music、video或link，不填默认为link
        dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
        success: function () {
            // 用户确认分享后执行的回调函数
        },
        cancel: function () {
            // 用户取消分享后执行的回调函数
        }
    });
    wx.error(function (res) {
        alert(res.errMsg);
    });
});
</script>
</body>
</html>