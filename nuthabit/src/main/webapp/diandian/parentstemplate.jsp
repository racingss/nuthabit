<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
long languageId = new LanguageHttp().getLanguageId(request);
//Kehu k = new KehuUtil().getKehu(request, response);
%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title><%=Menu.getMenu("parent", languageId) %></title>
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript">
		$(function(){
			
		})
	</script>
</head>
<body>
	<div class="page parents">
		<div>
			<img class="bg" src="frame/parents-11.png">
			<div style="background: white;padding: .5rem;margin: 1rem;border-radius: 2%;position: relative;z-index: 2;">
				<h1 style="font-size: .45rem;text-align: center;">联系我们</h1>
				<p style="font-size: .4rem;">
				模版消息用来帮助公众号进行业务通知，是开发者在模版内容中设定参数（参数以“{{”开头，以“.DATA}}”结尾），并在调用时为这些参数赋值并发送的接口。

				公众号只能在模版库中按照自己的行业来选择模版。如果模版库中暂时没有你想要的模版，则在满足下述要求的情况下，你可以为你所在的行业贡献新模版，帮助充实模版库。
				
				一  审核标准
				1. 必须是用户触发事件后的通知，或用户极其重视的通知，不允许群发、公告类。附错误示例1
				
				2. 通知场景必须明确、细分，固定文字必须能够体现具体场景。附错误示例2
				
				3. 发送频率不能过高，不得引起用户反感。附错误示例3
				
				4. 不允许广告、骚扰、营销类模版。附错误示例4
				
				5. 必须按照下述的填写规则来填写。附错误示例5
				
				6. 与模版库中已有模版类似，不能成为模版消息审核通过的凭据，审核将仅根据审核标准进行
				</p>
			</div>
			
		</div>
		
		
		
		<jsp:include page="foot.jsp" flush="true"/>
		
		
	</div>
</body>
</html>