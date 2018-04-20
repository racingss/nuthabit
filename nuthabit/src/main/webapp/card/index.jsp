<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String bg[]= {"#ed4d83","#97d9e6","#93fb99","b6d627","#e4a727","#e42727","#e635ff","#fbcd55","#a0d467","#ef88c8","#fbce54"};
%>    
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta charset="utf-8">
    <title>看图识物</title>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/card.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/main.js"></script>
    <script src="js/util.js"></script>
    <script type="text/javascript">
	    $(function(){
	        $("body").height($(window).height());
	    })
	    
	    
	 </script>
	 
	 <script type="text/javascript">  
        /******最完美解决 图片在图片框内按宽高比例自动缩放！！！***/  
        //Img:要放图片的img元素，onload时传参可用this  
  
        //maxHeight  :img元素的高度，像素（图片框 最大高度）  
  
        //maxWidth:img元素的宽度，像素（图片框 最大宽度）  
        function AutoSize(Img, maxWidth, maxHeight) {  
            var image = new Image();  
            //原图片原始地址（用于获取原图片的真实宽高，当<img>标签指定了宽、高时不受影响）  
            image.src = Img.src;    
            // 当图片比图片框小时不做任何改变   
            if (image.width < maxWidth&& image.height < maxHeight) {  
                Img.width = image.width;  
                Img.height = image.height;  
            }  
            else //原图片宽高比例 大于 图片框宽高比例,则以框的宽为标准缩放，反之以框的高为标准缩放  
	        {  
            if (maxWidth/ maxHeight  <= image.width / image.height) //原图片宽高比例 大于 图片框宽高比例  
            {  
                Img.width = maxWidth;   //以框的宽度为标准  
                Img.height = maxWidth* (image.height / image.width);  
            }   
            else {   //原图片宽高比例 小于 图片框宽高比例  
                Img.width = maxHeight  * (image.width / image.height);  
                Img.height = maxHeight  ;   //以框的高度为标准  
            }  
        }  
  
    }  
</script> 
	 <style type="text/css">
	 	.meaninglist{
		 	text-align: center;
		    background: #97d9e6;
		    /* height: 35px; */
		    padding-top: 10px;
		    font-size: 35px;
		    color:white;
		 }
		 .cardlist{
		 	height: 170px;
		 }
		 .updiv{
		 	height: 120px;
		 	line-height: 120px;
		 }
		 .downdiv{
		 	height: 30px;
		 }
		 .cardimg{
		 	width:90%;
		 	display: inline-block;
	    	vertical-align: middle
		 }
	 </style>
</head>
<body style="display: block">
<jsp:include page="head.jsp" flush="true"/>
    
<div class="page paleBlue">

	<div style="width: 100%;
    text-align: center;
    background-color: #ef4684;
    color: #fff;
    font-size: 2.0em;
    padding: 10px;">
		点兵点将 看图识物
	</div>
    
    
</div>


<div class="page paleBlue" style="float: left;
    width: 50%;">
		<div class="sdPlan meaninglist cardlist" style="font-size: 25px;">
	    	<div class="updiv">
	    		<a href="cardbytag.html?tagId=3"><img src="/myplan/upload/historypic/1523265475834.jpg" class="cardimg" onload="AutoSize(this,145,100)"></a>
	    	</div>
	    	<div class="downdiv">水果</div>
	    </div>
</div>
<div class="page paleBlue" style="float: left;
    width: 50%;">	    
	    <div class="sdPlan meaninglist cardlist" style="font-size: 25px;">
	    	<div class="updiv">
	    		<a href="cardbytag.html?tagId=5"><img src="/myplan/upload/historypic/1523270038429.png" class="cardimg" onload="AutoSize(this,145,100)"></a>
	    	</div>
	    	<div class="downdiv">数字</div>
	    </div>
</div>
<div class="page paleBlue" style="float: left;
    width: 50%;">	    
	    <div class="sdPlan meaninglist cardlist" style="font-size: 25px;">
	    	<div class="updiv">
	    		<a href="cardbytag.html?tagId=7"><img src="/myplan/upload/historypic/1522987921478.gif" class="cardimg" onload="AutoSize(this,145,100)"></a>
	    	</div>
	    	<div class="downdiv">动物</div>
	    </div>
</div>
<div class="page paleBlue" style="float: left;
    width: 50%;">
	    <div class="sdPlan meaninglist cardlist" style="font-size: 25px;">
	    	<div class="updiv">
	    		<a href="cardbytag.html?tagId=9"><img src="/myplan/upload/historypic/1523269300527.jpg" class="cardimg" onload="AutoSize(this,145,100)"></a>
	    	</div>
	    	<div class="downdiv">人物</div>
	    </div>
	</div>
<div class="page paleBlue" style="float: left;
    width: 50%;">
	    <div class="sdPlan meaninglist cardlist" style="font-size: 25px;">
	    	<div class="updiv">
	    		<a href="cardbytag.html?tagId=10"><img src="/myplan/upload/historypic/1523267647133.jpg" class="cardimg" onload="AutoSize(this,145,100)"></a>
	    	</div>
	    	<div class="downdiv">自然</div>
	    </div>
	</div>
<div class="page paleBlue" style="float: left;
    width: 50%;">
	    <div class="sdPlan meaninglist cardlist" style="font-size: 25px;">
	    	<div class="updiv">
	    		<a href="cardbytag.html?tagId=11"><img src="/myplan/upload/historypic/1523266607694.jpg" class="cardimg" onload="AutoSize(this,145,100)"></a>
	    	</div>
	    	<div class="downdiv">交通</div>
	    </div>
	</div>
<div class="page paleBlue" style="float: left;
    width: 50%;">
	    <div class="sdPlan meaninglist cardlist" style="font-size: 25px;">
	    	<div class="updiv">
	    		<a href="cardbytag.html?tagId=12"><img src="/myplan/upload/historypic/1523325505542.jpg" class="cardimg" onload="AutoSize(this,145,100)"></a>
	    	</div>
	    	<div class="downdiv">家电</div>
	    </div>
	</div>		
	
<div class="page paleBlue" style="float: left;
    width: 50%;">
	    <div class="sdPlan meaninglist cardlist" style="font-size: 25px;">
	    	<div class="updiv">
	    		<a href="cardbytag.html?tagId=13"><img src="/myplan/upload/historypic/1523498007199.jpg" class="cardimg" onload="AutoSize(this,145,100)"></a>
	    	</div>
	    	<div class="downdiv">颜色</div>
	    </div>
	</div>		    


<!--div style="position: fixed;
    display: block;
    top: 5px;
    left: 5px;">
    	<a href="index.jsp">
    		<img src="images/home.gif" style="width:30px;" />
    	</a>
</div-->






<footer>
    <a class="active" href="index.html">
        <i></i>
        <span>学习</span>
    </a>
    <a href="discovery.html">
        <i>
            <span class="number">5</span>
        </i>
        <span>复习</span>
    </a>
    <a href="my.html">
        <i>
            <span class="spot"></span>
        </i>
        <span>我的</span>
    </a>
</footer>
</body>
</html>