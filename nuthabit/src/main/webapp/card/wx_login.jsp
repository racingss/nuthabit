<%@page import="com.babycard.util.KehuUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信登录</title>
<script src="js/jquery.js"></script>
</head>
<body>

<script language="JavaScript">
  $(document).ready(function(){
    //alert(navigator.platform);
    checkPlatform();
  });
  var returnUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=<%=KehuUtil.appId%>&redirect_uri=http://www.suyufuwu.com/card/wx_login_result.jsp&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect";
  function checkPlatform(){
	  if(/android/i.test(navigator.userAgent)){
	      //document.write("This is Android'browser.");//这是Android平台下浏览器
		  window.location.href=returnUrl;
	  }
	  else if(/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)){
	      //document.write("This is iOS'browser.");//这是iOS平台下浏览器
		  window.location.href=returnUrl;
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
		  window.location.href=returnUrl;
	  }
  }
</script>
</body>
</html>