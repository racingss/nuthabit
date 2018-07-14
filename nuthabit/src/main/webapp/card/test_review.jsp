<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
CardPic p = (CardPic)request.getAttribute("test");

//切换语言
long languageId = new LanguageHttp().getLanguageId(request);

boolean autoPlay=false;
Kehu k = new KehuUtil().getKehu(request, response);
%>    
<!DOCTYPE html>
<html lang="zh" class="fsvs"><head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, user-scalable=no">
	<title><%=Menu.getTitle(languageId) %></title>
	<link rel="stylesheet" href="/card/css/list_style.css">
	<link rel="stylesheet" href="/card/css/dialog.css">
	<link rel="stylesheet" href="/card/css/card.css">
	<link rel="stylesheet" href="/card/css/audioplayer.css" />
	<link rel="stylesheet" href="/card/assets/css/default.css">
    <link rel="stylesheet" href="/card/assets/css/style.css">
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

	
    </style>  
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

        		
        		<!--             图片           -->
        		<div class="slide">        		
        		
	 				<!--         主图           -->        		
        			<img alt="" src="<%=p.getImgurl() %>" picId="<%=p.getPicId() %>" next="2" pre="0" style="width:100%;"  class="pic_<%=p.getPicId()%>">
        			<%
        			
		    		if(true){
		    			int l_i=0;
		    				CardMeaning cm = CardMeaning.getStaticCard(p.getPicId(), languageId);
		    				%>
		    				<div class="carddetail" style="bottom: 0;">
						            
						            <!--                   文字                     -->
						    		<div style="font-size:50px;color: #524f4f;">
						    			<a href="#" style="color:#524f4f">
						    				<%if(cm!=null)out.print(cm.getMeaning()); %>
						    			</a>
						    			<%
				    					Collection picSoundColl = CardSound.getSoundCollByPicId(p.getPicId());
				    					Iterator picSoundIt = picSoundColl.iterator();
				    					while(picSoundIt.hasNext()){
				    						CardSound cs = (CardSound)picSoundIt.next();
				    						if(cs.getLanguageId()!=languageId)
				    							continue;
				    						%>
				    						<a href="#" class="audiohidden" style="background: url(img/sound30.png);float:right"></a>
				    						<audio autoplay="true" preload="auto" controls style="display:none" id="soundaudio">
												<source src="<%=cs.getSound() %>">
											</audio>	
											
				    						<%
				    					}
				    					
				    					%>
				    					
				    					
				    					<p>
					    					<a class="footbatbut" href="/diandian/" style="float: left;width: 45%;">
									    			<%=Menu.getMenu("home", languageId) %>
								    		</a>
								    		<a class="footbatbut" href="review.html" style="float: left;width: 45%;">
									    			<%=Menu.getMenu("next", languageId) %>
								    		</a>
								    		
							    		</p>
				    					
				    					
				    				</div>
				    				
				    		</div>		
		    				<%		    			
		    		}
		    		%>
        			
        		</div>
        		
        		
    	
    </div>
    
    <a class="footbatbut" href="review.html?delete=t&picId=<%=p.getPicId() %>" style="width: 2.2rem;
    height: 2.2rem;
    border-radius: 30%;
    position: fixed;
    top: .2rem;
    right: .2rem;
    background: url(/diandian/frame/sr-5.png);
    background-size: 2rem 2rem;
    background-position: center;
    background-repeat: no-repeat;
    background-color: red;
    box-shadow: 0.15rem 0.15rem 0.2rem 0px rgba(255, 225, 0, 0.2);
    z-index: 999;">
 		
	</a>

    





<script type="text/javascript">
	
	$(function(){
		//播放语音
		$('.audiohidden').on("click", function () {
			document.getElementById("soundaudio").play();
		});
		
	})
</script>

		

    
</body>
</html>