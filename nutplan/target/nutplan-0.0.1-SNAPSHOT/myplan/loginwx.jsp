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
<meta charset="utf-8"/>
<title>请登录</title>

</head>
<body>
<!--header-->

<script language="JavaScript">
  $(document).ready(function(){
    //alert(navigator.platform);
    //checkPlatform();
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

<section class="order_msg">
 <h2>微信登录</h2>
 <dd style="text-align: center;">
 	<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx68ddf714b6a29ea5&redirect_uri=http://www.suyufuwu.com/myplan/wxresult.jsp&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect"><img alt="" src="/images/weixin.jpg">
 	<br/><br/>
    点击图标直接登录<br/></a>
 </dd>
</section>

 


</body>
</html>