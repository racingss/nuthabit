<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="zh"><head>    
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>卡片点点Cardpopo</title>
<script src="/card/js/jquery.js"></script>
<style>
body{
	background: #d0fcfe;
}
#main{
	text-align: center;
}
.autotype{
	width: 25%;
    position: absolute;
    top: 20%;
    left: 70%;
    padding: 10px;
    box-shadow: 0px 0.08rem 0.3rem rgba(0, 0, 0, 0.1);
    margin: 10px;
    text-align: center;
    background: #fffefe;
    opacity: 0.6;
    color: #1d1b1b;
}

</style>
</head>
<body>
<div id="main">
		<div style="position: relative;left: 0;right: 0;top: 0px;margin: 0;padding: 0;width: 90%;padding-left: 5%;margin-top: -1%;">
		
		
					<img src="images/web.jpg" style="width: 100%;border-radius: 0.5rem;">
					<img src="/diandian/frame/eye.png" style="width: 4%;position: absolute;top: 5%;left: 8%;">
					<img src="images/logozi.png" style="width: 13%;
    position: absolute;
    top: 5.5%;
    left: 13.5%;">
					<img src="images/mv1.gif" style="width: 20%;position: absolute;top: 45%;left: 55%;">
					<img src="images/mv2.gif" style="width: 16%;
    position: absolute;
    top: 58%;
    left: 38%;">
					<img src="images/nv3.gif" style="width: 8%;
    position: absolute;
    top: 5%;
    left: 85%;">
    				<img src="images/a1.png" style="width: 13%;
    position: absolute;
    top: 3%;
    left: 55%;">
    				<img src="images/a2.png" style="width: 15%;
    position: absolute;
    top: 75%;
    right: 2%;">
    				<img src="images/a3.png" style="width: 5%;
    position: absolute;
    top: 80%;
    left: 8%;">
    				<img src="images/a4.png" style="width: 15%;
    position: absolute;
    top: 65%;
    left: 70%;">
    				<img src="images/a5.png" style="width: 15%;
    position: absolute;
    top: 72%;
    left: 50%;">
    				<img src="images/a6.png" style="width: 15%;
    position: absolute;
    top: 35%;
    left: 20%;">
		<div class="autotype" id="autotype">
		 <p>我们是。。。。。。</p>
		 <p>学前教育好帮手，儿童认知教育AI平台</p>
		 <p>海量幼儿认知卡片，每日都有新内容上线</p>
		 <p>阅读按照格塞尔婴幼儿发展量专业分级</p>
		 <p>使用艾宾浩斯记忆曲线记录，高效复习</p>
		 <p>支持中英双语音，同时可学26国文字</p>
		 <p>家长自建卡片如被平台选中还能赚钱</p>
		 <p>微信请搜索：卡片点点</p>
		</div>
    
        </div>
		<footer style="margin-top:1rem;">
			© 2018 - 2019 熵岚网络. All Rights Reserved 备案号:
			<a href="http://www.miitbeian.gov.cn/" target="_blank" style="color: #1b150f;text-decoration: none;">
				沪ICP备18029395号-1
			</a>
		</footer>
</div>


<script type="text/javascript">
		$(function(){
			var p = 0;
			$.fn.autotype = function() {
			    var $text = $(this);
			    console.log('this', this);
			    var str = $text.html(); //返回被选 元素的内容
			    var index = 0;
			    var x = $text.html('');
			    //$text.html()和$(this).html('')有区别
			    var timer = setInterval(function() {
			        //substr(index, 1) 方法在字符串中抽取从index下标开始的一个的字符
			        var current = str.substr(index, 1);
			        if (current == '<') {
			            //indexOf() 方法返回">"在字符串中首次出现的位置。
			            index = str.indexOf('>', index) + 1;
			        } else {
			            index++;
			        }
			        //console.log(["0到index下标下的字符",str.substring(0, index)],["符号",index & 1 ? '_': '']);
			        //substring() 方法用于提取字符串中介于两个指定下标之间的字符
			        $text.html(str.substring(0, index) + (index & 1 ? '_': ''));
			        if (index >= str.length) {
			            clearInterval(timer);
			        }
			    },
			    150);
			};
			$("#autotype").autotype();			
		})
</script>
</body>
</html>   