<%@ page language="java" import="com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>登录成功</title>
<meta name="keywords"  content="KEYWORDS..." />
<meta name="description" content="DESCRIPTION..." />
<meta name="author" content="DeathGhost" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name='apple-touch-fullscreen' content='yes'>
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta name="format-detection" content="address=no">
<link rel="icon" href="images/icon/favicon.ico" type="image/x-icon">
<link rel="apple-touch-icon-precomposed" sizes="57x57" href="images/icon/apple-touch-icon-57x57-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="120x120" href="images/icon/apple-touch-icon-120x120-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="196x196" href="images/icon/apple-touch-icon-196x196-precomposed.png">
<meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script src="js/jquery.js"></script>
</head>
<body>
<%
 new KehuUtil().getKehuFromWX(request, response);
String url = "/diandian/";
if(session.getAttribute("cardId")!=null){
	url = "/card/cardlist.html?cardId="+session.getAttribute("cardId").toString();
	session.removeAttribute("cardId");
}else if(session.getAttribute("bookId")!=null){
	url = "/diandian/book.html?bookId="+session.getAttribute("bookId").toString();
	session.removeAttribute("bookId");
}else if(session.getAttribute("shuyishu")!=null){
	url = "/diandian/shuyishu.html";
	session.removeAttribute("shuyishu");
}else if(session.getAttribute("subscribe")!=null){
	url = "/diandian/subscribe.html";
	session.removeAttribute("subscribe");
}else if(session.getAttribute("booklist")!=null){
	url = "/diandian/booklist.html";
	session.removeAttribute("booklist");
}else if(session.getAttribute("poster")!=null){
	url = "/diandian/poster.html";
	session.removeAttribute("poster");
}


 %>
<script type="text/javascript">
window.location.href='<%=url%>';
</script>





 
</body>
</html>