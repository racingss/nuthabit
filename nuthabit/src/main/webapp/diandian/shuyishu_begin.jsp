<%@page import="com.babycard.wx.*"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String url = "http://www.suyufuwu.com/diandian/shuyishu.html"; 
Map<String, String> ret = new AccessToken().webSign(url,request, response);
String appId = KehuUtil.appId;
String timestamp = ret.get("timestamp");
String nonceStr = ret.get("nonceStr");
String signature = ret.get("signature");

Collection coll =(Collection)request.getAttribute("sysColl");

%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>宝贝数一数</title>
<style type="text/css">
    .page{
    	width: 100%;
    	height: 100%;
    	position: absolute;
    	top: 0;
    	left: 0;
    }
	#shuyishu{
		background: url(shuyishuimg/sunbackground.jpg);
	    height: 100%;
	    background-size: cover;
	}
	
	a{
		text-decoration:none;
	    color:#524f4f;
	}
	
	.msg{
		background: #fffefe;
	    opacity: 0.8;
	    top: 0;
	    z-index: 100;
	    height: 100%;
	    text-align: center;
	}
	.msg div p{
		font-size: 4rem;
	}
	.msg div p span img{
		width:100%;
	}
	.numspan{
		display: inline-block;
	    width: 15%;
	    font-size: 4rem;
	    border: 1px solid #03A9F4;
    	background: #03A9F4;
	    color: white;
	    border-radius: 1rem;
	    padding: 1rem;
	    margin: 1rem 0.2rem;
	}
	.imgspan{
		display: inline-block;
    	width: 30%;
	}
	.selectitem{
		width:80%;
		margin-left:10%;
	
	}
	.footnum{
		border-radius: 1rem;
	    box-shadow: 0px 0.08rem 0.3rem rgba(0, 0, 0, 0.1);
	    text-align: center;
	    width: 8rem;
	    position: absolute;
	    background: #fffefe;
	    opacity: 0.8;
	    font-size: 4rem;
	    top: 75%;
	    z-index: 100;
	    margin: 1rem;
	}
	.title{
		padding-top: 20%;
    	font-size: 6rem;
	}
</style>
</head>
<body onload="load()">
	<div class="page">
		<div id="shuyishu">
			
				
			<div class="msg" style="display:none">
				<div>
					恭喜你！过关啦
					<p>
						下一关
					</p>
					<p>
						数一数有几辆小汽车
					</p>
				</div>
				
			</div>
			
			
			<div class="msg" style="">
				<div>
				    <div class="title">
					宝贝数一数
					</div>
					<%
					if(request.getParameter("end")!=null){
						%>
						<div class="selectnums againmsg" style="display:block">
							<p>
								小朋友，你太棒啦！
							</p>
							<p>
								要不要再来一次？
							</p>
							<p>
							   <span class="numspan yesspan" style="width: 35%;font-size: 3rem;" num="35" >要</span>
							   <span class="numspan nospan" style="width: 35%;font-size: 3rem;" num="50" >不要</span>
							</p>
						</div>
						<%
					}else{
						%>
						<div class="initmsg" style="<%if(request.getParameter("end")!=null){%>display:none<%}%>">
							<p>
								<img src="/card/frame/sound.gif" class="initimg" style="margin-left: -50%;">
							</p>
							<p class="preparep">
								准备中
							</p>
							<p >
<%
if(false &&request.getSession().getAttribute("subscribe")!=null && request.getSession().getAttribute("subscribe").toString().equals("0")){
							%>
							<span class="numspan beginspan" style="width: 35%;font-size: 3rem;">您还没有关注我们的公众号</span>
							<div class="list" style="text-align: center;">
								<img alt="" src="/diandian/frame/kapiandiandian.jpeg"  style="width:70%">
								<br/>
								<img alt="" src="/diandian/frame/erweima_gzz.jpg" style="width:40%">
							</div>
							<%	
}else{
							%>
							<span class="numspan beginspan" style="width: 35%;font-size: 3rem;display:none">开始</span>
							<%
}
%>							</p>
							
						</div>
						<%
					}
					%>
					<div class="selectnums selectmsg" style="display:none">
						<p>
							选择一个数字
						</p>
						<p>
						   <%for(int i=3;i<=10;i++){ %>
						   <span class="numspan numclick" num="<%=i%>"><%=i %></span>
						   <%} %>
						   <span class="numspan numclick" style="width: 35%;font-size: 3rem;" num="35" >3-5随机</span>
						   <span class="numspan numclick" style="width: 35%;font-size: 3rem;" num="50" >5-10随机</span>
						</p>
					</div>
					<div class="selectitem" style="display:none">
						<p>
							选择一个物品
						</p>
						<p>
							<%
							Iterator it = coll.iterator();
							while(it.hasNext()){
								Shuyishu s = (Shuyishu)it.next();
								%>
								<span class="imgspan" imgId="<%=s.getId()%>">
									<img src="<%=s.getImgUrl()%>">
								</span>	
								<%
							}
							%>
							
							
						</p>
					</div>
				</div>
			</div>
				
			
		
		</div>
	
	</div>
	
	
	
	
<audio  controls id="init_audio" style="display:none">
	<source src="/myplan/upload/historypic/1534256970023.mp3">
</audio>	

<audio  controls id="select_audio" style="display:none">
	<source src="/myplan/upload/historypic/1534257226866.mp3">
</audio>	

<audio  controls id="begin_audio" style="display:none">
	<source src="/myplan/upload/historypic/1534257391960.mp3">
</audio>



	
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	
	$(function(){
		var num=0;
		var num35=Math.floor(Math.random()*3)+3;
		var num50=Math.floor(Math.random()*6)+5;
		var imgUrl="";
		
		$(".numclick").click(function(){
			num=$(this).attr("num");
			if(num==35)
				num = num35;
			if(num==50)
				num = num50;
			$(".selectnums").css({"display":"none"});
			$(".selectitem").css({"display":"block"});
			document.getElementById("select_audio").play();
		})
		
		$(".imgspan").click(function(){
			imgId = $(this).attr("imgId");
			$(this).animate({width:'200%',marginTop:'-20%'},5500);
			document.getElementById("begin_audio").play();
			setTimeout(function(){
				location.href="shuyishu.html?num="+num+"&imgId="+imgId;
			},5500);
		})
		
		$(".yesspan").click(function(){
			$(".againmsg").css({"display":"none"});
			$(".selectmsg").css({"display":"block"});
		})
		
		$(".nospan").click(function(){
			location.href="/diandian/";
		})
		
		
		$(".beginspan").click(function(){
			$(".initmsg").css({"display":"none"});
			$(".selectmsg").css({"display":"block"});
			document.getElementById("init_audio").play();
		})
		
		
	})
	
	function load()
	{
		for(var i=1;i<=7;i++){
			t=setTimeout("init()",500*i);
		}
		setTimeout(function(){
			$(".preparep").hide();
			$(".beginspan").show();
		},4000);
		$(".initimg").animate({marginLeft:'0'},4000);
	}
	
	function init(){
		$(".preparep").text($(".preparep").text()+".")
	};
</script>


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
        title: '宝贝数一数_你会数数了妈_卡片点点Cardpopo幼儿认知启蒙平台',
        link: link,
        imgUrl: 'http://www.suyufuwu.com/diandian/frame/eye.png',// 自定义图标
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
    	title: '宝贝数一数_你会数数了妈_卡片点点Cardpopo幼儿认知启蒙平台',
        desc: '来来来，快来检测一下，你们家的宝贝现在能数到几了', // 分享描述
        link: link, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
        imgUrl: 'http://www.suyufuwu.com/diandian/frame/eye.png',// 自定义图标
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