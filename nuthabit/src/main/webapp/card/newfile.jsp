<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<style type="text/css">
.inc-scroll-landscape-container { 
	width: 100%; 
	overflow: hidden; 
}
.inc-scroll-landscape-container > .inc-scroll-landscape-content {
    white-space: nowrap;
    overflow: hidden;
    overflow-x: scroll; /* 1 */
    -webkit-backface-visibility: hidden;
    -webkit-perspective: 1000;
    -webkit-overflow-scrolling: touch; /* 2 */
    &::-webkit-scrollbar { display: none;}
}
.inc-scroll-landscape-container > .inc-scroll-landscape-content > ul {
	margin: 0;
    margin-left: -40px;
}
.inc-scroll-landscape-container > .inc-scroll-landscape-content > ul > li { 
	display: inline-block; 
    text-align: center; 
}
.headpng{
	width: 75px;
}
.levelli{
	display:block;
	width:150px;
	height:100px;
	font-size:20px;
}
.titlediv{
	font-size: 20px;
    color: #e4d59f;
    padding-left: 20px;
    font-weight: 400;
}
.bookli{
	overflow: hidden;
    width: 80px;
    height: 100px;
    border-radius: 5px;
}
</style>
</head>
<body >

<div class="inc-scroll-landscape-container">
    <div class="inc-scroll-landscape-content">
        <ul>
            
            <li>
            	<img src="img/lion.png" class="headpng">
            	<br>动物
            </li>
            <li>
            	<img src="img/lion.png" class="headpng">
            	<br>动物
            </li>
            <li>
            	<img src="img/lion.png" class="headpng">
            	<br>动物
            </li>
            <li>
            	<img src="img/lion.png" class="headpng">
            	<br>动物
            </li>
            <li>
            	<img src="img/lion.png" class="headpng">
            	<br>动物
            </li>
            <li>
            	<img src="img/lion.png" class="headpng">
            	<br>动物
            </li>
            <li>
            	<img src="img/lion.png" class="headpng">
            	<br>动物
            </li>
            <li>
            	<img src="img/lion.png" class="headpng">
            	<br>动物
            </li>
        </ul>
     </div>
 </div>
 
 
 <div class="inc-scroll-landscape-container">
    <div class="inc-scroll-landscape-content">
        <ul>
        	<%for(int i=0;i<4;i++){ %>
            <li class="levelli" style="border-radius: .1rem;padding: 20px;box-shadow: 0px 0.08rem 0.3rem rgba(0, 0, 0, 0.1);height: 30px;margin: 20px;">
            	PRE-K
            </li>
            <%} %>
        </ul>
     </div>
 </div>
 
 <div class="titlediv">
 最近阅读
 </div>
 



<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript">
	//获取缩略图盒子宽高后再执行缩放
	function DrawImage_box(ImgID) {
	    var width_img=$("#imgBox").width();
	    var height_img=$("#imgBox").height();
	    console.log(width_img+","+height_img);
	    DrawImage(ImgID, width_img, height_img);
	}
	
	
	//图片缩放居中核心功能
	function DrawImage(ImgID, width_s, height_s) {
	    var image = new Image();
	    image.src = ImgID.src;
	    
	    if (image.width > 0 && image.height > 0) {
	    	console.log(image.width+":"+image.height);
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


 <div class="inc-scroll-landscape-container">
    <div class="inc-scroll-landscape-content">
        <ul>
        	<%
        	Iterator it = new CardDAO().getCardListByTag(7).iterator();
        	while(it.hasNext()){
        		Card c = (Card)it.next();
        	%>
            <li class="bookli">
            	<img alt="" src="<%=c.getImg() %>" onload="DrawImage(this, 60, 120)" />
            </li>
            <%} %>
        </ul>
     </div>
 </div> 

 
</body>
</html>