<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int nums = Integer.parseInt(request.getParameter("num"));
String imgUrl = request.getParameter("imgUrl");
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
	.carddetail {
	    border-radius: 1rem;
	    padding: 3rem 0;
	    box-shadow: 0px 0.08rem 0.3rem rgba(0, 0, 0, 0.1);
	    text-align: center;
	    width: 100%;
	    position: absolute;
	    background: #fffefe;
	    opacity: 0.8;
	    bottom: 1rem;
	    font-size: 3rem;
	    
	}
	a{
		text-decoration:none;
	    color:#524f4f;
	}
	.item{
		width: 12rem;
		position: absolute;
	}
	.numberdetail{
		border-radius: 1rem;
	    box-shadow: 0px 0.08rem 0.3rem rgba(0, 0, 0, 0.1);
	    text-align: center;
	    width: 20%;
	    position: absolute;
	    background: #fffefe;
	    opacity: 0.8;
	    font-size: 10rem;
	    top: 0;
	    z-index: 100;
	    left: 0;
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
	    border: 1px solid red;
	    background: red;
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
	.enddetail{
		border-radius: 1rem;
	    box-shadow: 0px 0.08rem 0.3rem rgba(0, 0, 0, 0.1);
	    text-align: center;
	    width: 60%;
	    position: absolute;
	    background: #fffefe;
	    opacity: 0.8;
	    top: 20%;
	    font-size: 30rem;
	    margin-left: 20%;
	}
</style>
</head>
<body>
	<div class="page">
		<div id="shuyishu">
			
			<div class="carddetail">
				<a href="#" class="click">小朋友，小手点一点</a>
			</div>
			
			<%
			int index=1;
			int j=1;
			Collection coll= new ShuyishuUtil().getCoordinate(nums, 75, 70, 5);
			Iterator it = coll.iterator();
			int finalNum=coll.size();
			while(it.hasNext()){
				double[] temp = (double[]) it.next();
				%>
				<img src="<%=imgUrl %>" class="item item<%=index %>"  showFlag ="0" style="top: <%=temp[1]%>%;left: <%=temp[0]%>%;">
				<div class="footnum footnum<%=j %>" style="left:<%=(j-1)*10%>%;display:none">
					<%=j %>
				</div>
				<%
				j++;
				
			}
			
			
			%>
			<div class="numberdetail" style="display:none">
				<%=index %>
			</div>
			
			<div class="enddetail" style="display:none">
				1
			</div>	
				
				
			
		
		</div>
	
	</div>
	
<audio  controls id="1_audio" style="display:none">
	<source src="/myplan/upload/historypic/1534257669541.mp3">
</audio>


<audio  controls id="2_audio" style="display:none">
	<source src="/myplan/upload/historypic/1534257701852.mp3">
</audio>


<audio  controls id="3_audio" style="display:none">
	<source src="/myplan/upload/historypic/1534257726246.mp3">
</audio>

<audio  controls id="4_audio" style="display:none">
	<source src="/myplan/upload/historypic/1534257750931.mp3">
</audio>

<audio  controls id="5_audio" style="display:none">
	<source src="/myplan/upload/historypic/1534257771774.mp3">
</audio>

<audio  controls id="6_audio" style="display:none">
	<source src="/myplan/upload/historypic/1534257796960.mp3">
</audio>

<audio  controls id="7_audio" style="display:none">
	<source src="/myplan/upload/historypic/1534257817243.mp3">
</audio>

<audio  controls id="8_audio" style="display:none">
	<source src="/myplan/upload/historypic/1534257838446.mp3">
</audio>

<audio  controls id="9_audio" style="display:none">
	<source src="/myplan/upload/historypic/1534257859991.mp3">
</audio>

<audio  controls id="10_audio" style="display:none">
	<source src="/myplan/upload/historypic/1534257882791.mp3">
</audio>	

<audio  controls id="end_audio" style="display:none">
	<source src="/myplan/upload/historypic/1534258353946.mp3">
</audio>	

<audio  controls id="click_audio" style="display:none">
	<source src="/myplan/upload/historypic/1534258381713.mp3">
</audio>	


	
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	
	$(function(){
		index =1;
		itemId=1;
		$(".item").click(function(){
			if($(this).attr("showFlag")==0){
				$(this).animate({top:'80%',left:(index-1)*10+'%',width:'10rem',opacity:'0.8'},2000);
				
				$(this).attr("showFlag","1");
				$(".numberdetail").css({"display":"block"});
				$(".numberdetail").text(index);
				document.getElementById(index+"_audio").play();
				index++;
			}
			if(index><%=finalNum%>){
				index=1;
				setTimeout(function(){
					<%
					int jIndex=1;
					for(;jIndex<=nums;jIndex++){%>
						setTimeout(function(){
							$(".footnum<%=jIndex%>").css({"display":"block"});	
							$(".enddetail").css({"display":"block"});
							$(".enddetail").text(<%=jIndex%>);
							document.getElementById("<%=jIndex%>_audio").play();
						},2000*<%=jIndex%>);
					<%
					}%>
					
					setTimeout(function(){
						document.getElementById("end_audio").play();
					},2000*<%=jIndex%>+1000);
						
					setTimeout(function(){
						//$(".item").css({"display":"none"});
						//$(".msg").css({"display":"block"});		
						//$(".footnum").css({"display":"none"});
						location.href="shuyishu_begin.jsp?end=t";
					},2000*<%=jIndex+2%>+1000);
					$(".numberdetail").css({"display":"none"});
					itemId++;
				},3000);
			}
		})	
		
		$(".click").click(function(){
			document.getElementById("click_audio").play();
		})
		
		
	})
</script>	
</body>
</html>