<%@page import="javax.swing.text.html.CSS"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Book b = (Book)request.getAttribute("book");
Collection cardColl = (Collection)request.getAttribute("cardColl");

//切换语言
long languageId = new LanguageHttp().getLanguageId(request);
Kehu k = new KehuUtil().getKehu(request, response);
%>    
<!DOCTYPE html>
<html lang="zh" class="fsvs"><head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, user-scalable=no">
	<title><%=Menu.getTitle(languageId) %></title>
	<style type="text/css">
    
    
    .carddetail{
    	text-align: center;
    	margin:1em;
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
    box-shadow: 0px 0.08rem 0.3rem rgba(0, 0, 0, 0.1);
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
	
	.piccontrolbutton{
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
	
	.footbarbutton{
		border-radius: 0.5rem;
		margin: 1em;
		padding: 0.5em;
		box-shadow: 0px 0.4rem 0.5rem rgba(0, 0, 0, 0.1);
		display: inline-block;
		color: white;
		text-decoration: none;
	}
	
	.wenzishuru{
		font-size: 1em;
	    padding: 5px;
	    border-radius: 0.3em;
	    background: #fffefb;
	    color: #535d92;
	    width: 90%;
	    box-shadow: 0px 0.1rem 0.2rem rgba(0, 0, 0, 0.1);
	}
	
	.slide{
		background-color: #fffefe;
		border-radius: 0.3rem;
		margin:1em;
		box-shadow: 0px 0.08rem 0.3rem rgba(0, 0, 0, 0.1);
		padding-bottom: 0.5em;
		background-image: url(/diandian/frame/home-18.jpg);
    	
	}
	.tiship{
		display:inline-block;
		text-align:center;
		width:100%;
		color: #ed4630;
	}
	
	h4{
		text-align: center;
	    margin: 0;
	    color: #63d169;
	    margin: 0.5em;
    }
    
    .cardselect{
    	background-color: #5cd06a;
	    padding: 0.5em;
	    box-shadow: 0px 0.4rem 0.5rem rgba(0, 0, 0, 0.1);
	    color: white;
	    text-decoration: none;
	    border-radius: 0.5rem;
	    font-size: 1em;
	    text-align: center;
    }

	body{
		background-color: #effefc;
	}
	.add{
		width: 3rem;
	    height: 3rem;
	    border-radius: 50%;
	    position: fixed;
	    bottom: 4rem;
	    right: 2rem;
	    background: url(/diandian/frame/found-3.png);
	    background-size: 2rem 2rem;
	    background-position: center;
	    background-repeat: no-repeat;
	    background-color: #ffc600;
	    box-shadow: 0.15rem 0.15rem 0.2rem 0px rgba(255, 225, 0, 0.2);
	    display: block;
	}
	.delete_button{
		width: 1.5rem;
	    height: 1.5rem;
	    border-radius: 50%;
	    float: left;
	    margin-left: 0.5rem;
	    background: url(/diandian/frame/sr-5.png);
	    background-size: 1.0rem 1.0rem;
	    background-position: center;
	    background-repeat: no-repeat;
	    background-color: #effefd;
	    box-shadow: 0.15rem 0.15rem 0.2rem 0px rgba(255, 225, 0, 0.2);
	    display: block;
	}
	.trashImg{
		width: 3rem;
	    height: 3rem;
	    border-radius: 50%;
	    position: fixed;
	    bottom: 0.5rem;
	    right: 2rem;
	    background: url(/diandian/frame/clear-button.png);
	    background-size: 1.5rem 1.5rem;
	    background-position: center;
	    background-repeat: no-repeat;
	    background-color: #6cdad6;
	    box-shadow: 0.15rem 0.15rem 0.2rem 0px rgba(255, 225, 0, 0.2);
	    display: block;
	}
	.positionImg{
	width: 3rem;
	    height: 3rem;
	    border-radius: 50%;
	    position: fixed;
	    right: 2rem;
	    background-size: 1.5rem 1.5rem;
	    background-position: center;
	    background-repeat: no-repeat;
	    box-shadow: 0.15rem 0.15rem 0.2rem 0px rgba(255, 225, 0, 0.2);
	    display: block;
		bottom: 7.5rem;
	    text-align: center;
	    background: red;
	    font-size: 2rem;
	    color: white;
	}
	
	a{
		text-decoration: none;
	}
	.menutab{
		background-image: url(/diandian/frame/home-18.jpg);
	    border-radius: 0.2rem;
	    padding: 0.3em 0.6em;
	    margin: 0.1em;
	    color: #f49731;
	    box-shadow: 0px 0.08rem 0.3rem rgba(0, 0, 0, 0.1);
	}
	.active{
		
	}
	.slide{
		display:none;
	}
	.audiosound{
		width: 90%;
		background-color: #fac633;
		padding: 0.5em;
		box-shadow: 0px 0.4rem 0.5rem rgba(0, 0, 0, 0.1);
		border-radius: 0.5rem;
		margin-top: 0.3em;
	}
    </style>  
    <link rel="stylesheet" href="css/dialog.css">
    <script src="assets/js/jquery-1.11.0.min.js"></script>
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
<body>
	<div class="category" style="width: 100%;overflow-x: scroll;">
		<div style="border-radius: 0.5rem;
    white-space: nowrap;
    padding: .4rem .4rem;
    padding-bottom: 0;
    padding-bottom: 0.5em;">
			<a href="#" class="menutab" slideId='1' style="background: #f49731;
    	color: white;"><%=Menu.getMenu("detail_cover", languageId) %></a>
			<a href="#" class="menutab"  slideId='2'><%=Menu.getMenu("basic_info", languageId) %></a>
			<a href="book.html?bookId=<%=b.getBookId() %>" class="menutab"><%=Menu.getMenu("review", languageId) %></a>
			<a href="/diandian/" class="menutab"><%=Menu.getMenu("back_to_home", languageId) %></a>
		</div>
	</div>


    <div >
    	<!--                  封面                 -->
    	<div class="slide" style="display:block" id="slide_1">
    		<h2 style="color: #FF9800;text-align:  center;padding-top: 0.5em;">
	    		<%=Menu.getMenu("detail_cover", languageId) %>
    		</h2>
    		<p class="tiship">
    			
    			<%
    			if(b.getDefaultPic()!=null){
    			%>
    			<span style="width:100%;display:block;text-align:center;">        		
       				<img alt="" src="/<%=b.getDefaultPic()%>" style="width:40%;box-shadow: 0px 0.08rem 0.3rem rgba(0, 0, 0, 0.1);margin:0.5em;">
       			</span>
       			<%} %>
       			<span style="font-size: 0.8em;">
    				<%=Menu.getMenu("detail_1", languageId) %>
    			</span>
    			<br/>
    			<a href="test_sound.jsp?bookId=<%=b.getBookId() %>&cover=t" class="piccontrolbutton"><%=Menu.getMenu("upload_but", languageId) %></a>
    		</p>
    		
    		    		
    	</div>
    	
    	<!--                  书本信息                 -->
    	<div class="slide"  id="slide_2">
    		<h2 style="color: #FF9800;text-align:  center;padding-top: 0.5em;">
    			<%=Menu.getMenu("basic_info", languageId) %>
    			
    		</h2>
    		
    		<h4>卡片书名称</h4>
    		<p class="tiship">
    			<input type="text" value="<%=b.getBookName()%>" class="cardselect cardnameinput">
    		</p>
    		
    		
    		
    		
    		
    		<h4>卡片排序</h4>
    		<p class="tiship">
    			<%
    			Iterator it =cardColl.iterator();
    			while(it.hasNext()){
    				Card t = (Card)it.next();
    				%>
    				<%=t.getMeaning() %><br/>
    				<%
    			}
    			
    			%>		
    		</p>
    		
    		
    		
    		
    		
    		
    		
    	</div>
    	
    	    	
    			
    </div>

    





<script type="text/javascript">
	
	$(function(){
		
		
		//菜单
		$('.menutab').on("click", function () {
			$('.slide').css({'display':'none'});
			$('#slide_'+$(this).attr('slideId')).css({'display':'block'});
			$('.menutab').css({'background-image':'url(/diandian/frame/home-18.jpg);','color':'#f49731'});
			$(this).css({'background':'#f49731','color':'white'});
		});
		
		
		
		//修改文字
		$('.wenzishuru').on("change", function () {
			distLanguage = $(this).attr("distLanguage");
			meaning = this.value;
			picId= $(this).attr("picId");
		});
		
				
	})
</script>

    
</body>
</html>