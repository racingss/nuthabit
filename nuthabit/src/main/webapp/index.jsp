<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="zh"><head>    
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>Cardpopo卡片点点</title>
<script src="/card/js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<style>
.grid {
	//width: 600px; height: 300px; margin: 100px auto 50px auto;
	//perspective: 500px; /*For 3d*/
	width: 600px;
    height: 300px;
    margin: auto;
    margin-top: 4rem
}
.grid img {width: 60px; height: 60px; display: block; float: left;}

.animate {
	text-transform: uppercase;
    background: rgb(65, 175, 232);
    color: white;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    margin: 10px auto;
    width: 30rem;
    text-align: center;
}
.animate:hover {background: rgb(0, 75, 0);}
footer{
	border-top: 1px solid #303030;
    background-color: #222;
    text-align: center;
    color: white;
    bottom: 0;
    position: fixed;
    right: 0;
    left: 0;
    padding: 0.5rem;
    font-size: 0.8rem;
}
footer a{
	color: white;
}
</style>
</head>
<body>
<div id="main">
<div class="demo">
<div class="grid"></div>
<div class="animate">
	1-4岁学前教育好帮手，儿童认知教育AI平台
</div>
</div>
<footer>
	备案号:
	<a href="http://www.miitbeian.gov.cn/" target="_blank">
		沪ICP备18029395号-1
	</a>
</footer>
</div>
<script>
//Creating 50 thumbnails inside .grid
//the images are stored on the server serially. So we can use a loop to generate the HTML.
var images = "", count = 40;
for(var i = 1; i <= count; i++)
	images += '<img src="images/'+i+'.jpg" />';
	
//appending the images to .grid
$(".grid").append(images);

var d = 0; //delay
var ry, tz, s; //transform params

//animation time
$(".animate").on("click", function(){
	//fading out the thumbnails with style
	$("img").each(function(){
		d = Math.random()*1000; //1ms to 1000ms delay
		$(this).delay(d).animate({opacity: 0}, {
			step: function(n){
				s = 1-n; //scale - will animate from 0 to 1
				$(this).css("transform", "scale("+s+")");
			}, 
			duration: 1000, 
		})
	}).promise().done(function(){
		//after *promising* and *doing* the fadeout animation we will bring the images back
		storm();
	})
})
//bringing back the images with style
function storm()
{
	$("img").each(function(){
		d = Math.random()*1000;
		$(this).delay(d).animate({opacity: 1}, {
			step: function(n){
				//rotating the images on the Y axis from 360deg to 0deg
				ry = (1-n)*360;
				//translating the images from 1000px to 0px
				tz = (1-n)*1000;
				//applying the transformation
				$(this).css("transform", "rotateY("+ry+"deg) translateZ("+tz+"px)");
			}, 
			duration: 3000, 
			//some easing fun. Comes from the jquery easing plugin.
			easing: 'easeOutQuint', 
		})
	})
}
</script>
</body>
</html>   