<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int nums = (int)(Math.random() *3+3);
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数一数</title>
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
							   <span class="numspan beginspan" style="width: 35%;font-size: 3rem;display:none">开始</span>
							</p>
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
							<span class="imgspan">
								<img src="frame/nv3.gif">
							</span>
							<span class="imgspan">
								<img src="frame/mv2.gif">
							</span>
							<span class="imgspan">
								<img src="frame/mv1.gif">
							</span>
							<span class="imgspan">
								<img src="shuyishuimg/1.png">
							</span>
							<span class="imgspan">
								<img src="shuyishuimg/2.png">
							</span>
							<span class="imgspan">
								<img src="shuyishuimg/3.gif">
							</span>
							<span class="imgspan">
								<img src="/myplan/upload/historypic/1533091246161.gif">
							</span>
							<span class="imgspan">
								<img src="/myplan/upload/historypic/1533091275736.gif">
							</span>
							<span class="imgspan">
								<img src="/myplan/upload/historypic/1532683217412.jpeg">
							</span>
							
							
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
			imgUrl=$(this).children("img").attr("src");
			$(this).animate({width:'200%',marginTop:'-20%'},5500);
			document.getElementById("begin_audio").play();
			setTimeout(function(){
				location.href="shuyishu.jsp?num="+num+"&imgUrl="+imgUrl;
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
</body>
</html>