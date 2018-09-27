<%@page import="com.babycard.wx.*"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//切换语言
long languageId = new LanguageHttp().getLanguageId(request);
long languageId_2 = new LanguageHttp().getLanguageId_2(request);
long cardId=Long.parseLong(request.getParameter("cardId"));
Card c = Card.getStaticCard(cardId);
Collection cardColl = new CardPicDAO().getCardPicByCardId(cardId);

long  nums =cardColl.size();

Kehu k = new KehuUtil().getKehu(request, response);

TestHttp testHttp = new TestHttp();

String title= c.getMeaning(languageId,c.getCardId())+"_快来测一测_看看你能认出多少_卡片点点为您精心准备";
String detail="卡片点点—儿童语言启蒙教育平台，支持中英双语音，法德日韩俄等全球主流26种以上的语言";
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
    	background: url(/diandian/frame/home-18.jpg);
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
		width: 15rem;
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
	    z-index:99999;
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
		bottom:0;
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
    	top: 0;
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
	.resultimg{
		position: absolute;
	    top: 25%;
	    left: 65%;
	    z-index: 99999;
	    width: 6em;
	    display:none;
	}
	.msg{
		position: absolute;
	    bottom: 0;
	    width: 100%;
	    text-align: center;
	    font-size: 3rem;
	    display: inline-block;
	    line-height: 20rem;
	    background: white;
	    z-index:0;
	}
	.msgspan,.numspan{
		font-size: 4rem;
	    padding: 1rem;
	    color: #fac833;
	}
	.numspan{
		margin-bottom:25rem;
	}

</style>
</head>
<body onload="load()">
	<div class="page">
		<div class="headbar">
			<a href="/diandian/">
				<img alt="" src="/diandian/frame/h0.png" class="hearbarimg">
			</a>
			<a href="/card/cardlist.html?cardId=<%=c.getCardId()%>">
				<img alt="" src="/diandian/frame/h1.png" class="hearbarimg">
			</a>
			<a href="/card/test_iop.html?cardId=<%=c.getCardId() %>&languageId=<%if(languageId==0)out.print(1);else out.print(0); %>" >
				<img alt="" src="/diandian/frame/l<%=languageId %>.png" class="hearbarimg">
			</a>

		</div>		
	
	
		<%
		for(int i=1;i<=nums;i++){
			System.out.println("board_"+i);
		%>	
		<div class="board" id="board_<%=i%>" style="<%if(i>1)out.print("display:none");%>">
		
			<%
			int index=1;
			int j=1;
			Collection testColl = new ShuyishuUtil().getRandColl(cardColl, i, 3);
			Iterator picit = testColl.iterator();
			Iterator poIt = new ShuyishuUtil().getCoordinate(3, 60, 50, 11).iterator();
			while(picit.hasNext() &&j<=3){
				double[] temp = (double[]) poIt.next();
				CardPic fpic = (CardPic)picit.next();
				%>
				<div class="item" id="item<%=fpic.index %>" minij=<%=j%> picId="<%=fpic.getPicId() %>" style="top: <%=temp[1]%>%;left: <%=temp[0]%>%;width: 22<%//=fpic.getWidthP()%>rem;z-index:<%=100-j%>">
					<img src="<%=fpic.getImgurl() %>" class="itemimg"  >
					<img alt="" src="/card/img/result_<%
					if(fpic.index==i)out.print("true");
					else out.print("false");
					%>.png" class="resultimg">
					<span class="spanline1"  style="margin-top:<%=fpic.getMarginTop()%>rem;display:none;"><%=CardMeaning.getStaticCard(fpic.getPicId(), languageId).getMeaning() %></span>
					<span class="spanline2"  style="margin-top:<%=fpic.getMarginTop2()%>rem;display:none;" ><%=CardMeaning.getStaticCard(fpic.getPicId(), languageId_2).getMeaning() %></span>
				</div>
			<%
			j++;
			} %>
		</div>
		<%} %>
		
		<div class="msg">
			<span class="askspan">小朋友，图中哪一个是</span><span class="msgspan"></span>
			<p class="nump" style="display:none">你答对了<span class="numspan" id="chenggonglv">90%</span></p>
			<p class="nump" style="display:none">超过了<span class="numspan" id="xiaopengyou">75%</span>的小朋友</p>
		</div>

		<audio id="succaudio" ><source src="/card/sound/1535525556036.mp3" type="audio/mpeg" /></audio>
		<audio id="failaudio" ><source src="/card/sound/1535525619635.mp3" type="audio/mpeg" /></audio>
		<audio id="finalaudio" ><source src="/card/sound/1535525939025.mp3" type="audio/mpeg" /></audio>
		<audio id="final1audio" ><source src="/myplan/upload/historypic/1535526598529.mp3" type="audio/mpeg" /></audio>
		<audio id="beginaudio" ><source src="/card/sound/1535526122095.mp3" type="audio/mpeg" /></audio>
	
	</div>
	

	
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">

	var msgspan = new Array(<%
	Iterator spanIt = cardColl.iterator();
	int i=1;
	while(spanIt.hasNext()){
		CardPic cp =(CardPic)spanIt.next();
		out.print("\""+CardMeaning.getStaticCard(cp.getPicId(), languageId).getMeaning()+"\"");
		if(i++<nums)
			out.print(",");
	}
	%>);

	var index=1;
	var stopFlag=0;
	
	function load()
	{
		$(".msgspan").text(msgspan[index-1]);
	}
	

	
	function audioAutoPlay1(id){
	    var audio = document.getElementById(id);
	    audio.load();
	    audio.play();
	    document.addEventListener("WeixinJSBridgeReady", function () {
	            audio.play();
	    }, false);
	}
	
	
	
	$(function(){
		var succNum=0;
		var failNum=0;
		
		$(".item").click(function(){
			if(stopFlag==1)
				return;
			
			stopFlag=1;
			setTimeout(function(){
				stopFlag=0;
			},4000);
			
			t=showResult(this);
			$(this).children(".resultimg").show();
			
			minij=$(this).attr("minij");
			if(minij==1){
				$(".msgspan").text("恭喜,你答对啦");
				document.getElementById('succaudio').play();
				if(failNum==0){
					succNum++;
				}
				failNum=0;
			}else{
				failNum=1;
				$(".msgspan").text("答错啦，再来一次吧");
				document.getElementById('failaudio').play();
				
				setTimeout(function(){
					$(".msgspan").text(msgspan[index-1]);
					$(".askspan").text("小朋友，图中哪一个是");	
				},3500);
				return;
			}
			
			setTimeout(function(){
				$("#board_"+index).hide();
				$(".spanline1").hide();
				$(".spanline2").hide();
				
				index++;
				if(index<=<%=nums%>){
					$("#board_"+index).show();
					$(".msgspan").text(msgspan[index-1]);
					$(".askspan").text("小朋友，图中哪一个是");	
				}else{
					$(".msgspan").text("结束啦，你真棒");
					$(".msg").animate({height:'70rem'},"slow");
					document.getElementById('final1audio').play();
					document.getElementById('finalaudio').play();
					$("#chenggonglv").text(parseInt(succNum/<%=nums%>*100)+'%');
					$("#xiaopengyou").text(Math.min(parseInt(succNum/<%=nums%>*100*1.07),100)+"%")
					
					$(".nump").show();
					
				}
				
			},4000);
		})
		
		function showResult(obj){
			$(".spanline1").show();
			$(".spanline2").show();
			$(".msgspan").text("");
			$(".askspan").text("");
		}	

	})
</script>	





<%


String url = "http://www.suyufuwu.com/card/test_iop.html?"+ (request.getQueryString()); 
Map<String, String> ret = new AccessToken().webSign(url,request, response);
String appId = KehuUtil.appId;
String timestamp = ret.get("timestamp");
String nonceStr = ret.get("nonceStr");
String signature = ret.get("signature");

if(request.getSession().getAttribute("subscribe")!=null && request.getSession().getAttribute("subscribe").toString().equals("0")){
	request.getSession().removeAttribute("subscribe");
	%>
	<script type="text/javascript">
	location.href='/diandian/weiguanzhu.jsp';
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