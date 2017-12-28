<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
if(request.getParameter("fanhui")!=null){
	String fanhui = request.getParameter("fanhui");
	System.out.println(fanhui);
	try{
	fanhui = fanhui.replaceAll("-", "?");
	fanhui = fanhui.replaceAll("_", "=");
	fanhui = fanhui.replaceAll("~", "&");
	}catch(Exception e){
		e.printStackTrace();
	}
	System.out.println(fanhui);
	request.getSession().setAttribute("fanhui", fanhui);
}
%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta charset="utf-8">
    <title>习惯养成</title>
    <link rel="stylesheet" href="css/main.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/main.js"></script>
    <script src="js/util.js"></script>
    <script type="text/javascript">        
		var nh_redirectUrl = getRootPath() + "/myplan/wxresult.jsp";
		$(document).ready(function(){
		  //alert(navigator.platform);
		  //checkPlatform();
		  var redirectUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx68ddf714b6a29ea5&redirect_uri=" + nh_redirectUrl+ "&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect";
		  $(".othLogin a:first").attr("href", redirectUrl);
		});
		function checkPlatform(){
		 if(/android/i.test(navigator.userAgent)){
		     //document.write("This is Android'browser.");//这是Android平台下浏览器
		  window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx68ddf714b6a29ea5&redirect_uri=http://www.suyufuwu.com/caozuojieguo.jsp&response_type=code&scope=snsapi_base&state=STATE&connect_redirect=1#wechat_redirect";
		 }
		 else if(/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)){
		     //document.write("This is iOS'browser.");//这是iOS平台下浏览器
		  window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx68ddf714b6a29ea5&redirect_uri=http://www.suyufuwu.com/caozuojieguo.jsp&response_type=code&scope=snsapi_base&state=STATE&connect_redirect=1#wechat_redirect";
		 }
		 else if(/Linux/i.test(navigator.userAgent)){
		     //document.write("This is Linux'browser.");//这是Linux平台下浏览器
		 }
		 else if(/Linux/i.test(navigator.platform)){
		     //document.write("This is Linux operating system.");//这是Linux操作系统平台
		 }
		 else if(/MicroMessenger/i.test(navigator.userAgent)){
		     //document.write("This is MicroMessenger'browser.");//这是微信平台下浏览器
		     //alert("This is MicroMessenger'browser.");
		  window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx68ddf714b6a29ea5&redirect_uri=http://www.suyufuwu.com/caozuojieguo.jsp&response_type=code&scope=snsapi_base&state=STATE&connect_redirect=1#wechat_redirect";
		 }
		}
	</script>
</head>
<body>
    <div class="page paleBlue">
        <nav class="default">
            <span>登陆</span>
            <a class="left close"></a>
        </nav>
        <form action="login.html" method="post">
        <div class="loginBox">
            <div>
                <span></span>
                <input type="" name="mobile" id="mobile" placeholder="请在此输入手机号码">
            </div>
            <div>
                <span></span>
                <input type="" name="pwd" placeholder="请在此输入验证码">
                <a onclick="sendSms()">获取验证码</a>
            </div>
            <p id="result" style="text-align: center;color: #F60;font-size: 1.5em;">
            <%
            if(request.getAttribute("msg")!=null)
            	out.print(request.getAttribute("msg"));
            %>
            </p>
            <button>登陆</button>
        </div>
        </form>
        <div class="othLogin">
<%
//String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx68ddf714b6a29ea5&redirect_uri=http://www.suyufuwu.com/myplan/wxresult.jsp&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect";
String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx68ddf714b6a29ea5&redirect_uri=www.suyufuwu.com/caozuojieguo.jsp&response_type=code&scope=snsapi_base&state=STATE&connect_redirect=1#wechat_redirect";
System.out.println(url);

%>            
            <a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx68ddf714b6a29ea5&redirect_uri=http://www.suyufuwu.com/myplan/wxresult.jsp&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect"></a>
            <p>我已阅读并同意《用户服务协议》</p>
            <p>
            <a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx68ddf714b6a29ea5&redirect_uri=http://www.suyufuwu.com/myplan/wxresult.jsp&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect">
            <img alt="" src="frame/login-1.png" style="width: 1.3rem;height: 1.3rem;"/>
            </a>
            </p>
        </div>
    </div>
</body>
<%@ include file="ground.jsp" %>
<script type="text/javascript">
 	function sendSms(){
 		if(document.getElementById("mobile").value.length<1){
 			$("#result").text("请输入手机号码");
 			return;
 		}
 		
 		$.ajax({
			url: 'login.html?sms=t&mobile='+document.getElementById("mobile").value,
			dateType:'json',
		    success: function(data){
		    	if(data=='9')
		    		$("#result").text("短信已发送");
		    	else
		    		$("#result").text(data);
		    }
	  });
 	}
 </script>
</html>