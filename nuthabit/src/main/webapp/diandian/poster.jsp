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
	<title>您的专属推广海报</title>
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript">
		$(function(){
			
		})
	</script>
</head>
<body>
	<img alt="" src="/<%=request.getAttribute("poster") %>" style="width: 100%;position: absolute;z-index: 99999999;top: 0;margin:0">
	
	<ol class="regV" style="z-index: 9999999999;">
		<div>
			<div class="hd" style="height: 2.2rem;">
				<div class="i1"></div>
				<div class="i2">
					温馨提示：
				</div>
				
			</div>
			<div class="bd" style="height: 3.5rem;">
				<div style="text-align:center;font-size: 0.45rem;padding: 0.5rem;color: red;">
					请截图或保存图片后分享
				</div>
				<a class="i7 regVhref" href="#" >知道了</a>
			</div>
		</div>
	</ol>
	<script type="text/javascript">
	$(function(){
		$(".regV .i7,.regV .i8").click(function(){
			$(".regV").hide();
		})
	})
	</script>
</body>
</html>