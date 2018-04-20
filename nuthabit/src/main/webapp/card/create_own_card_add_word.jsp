<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String picUrl = null;
if(request.getAttribute("picurl")!=null){
	picUrl=request.getAttribute("picurl").toString();	
}else if(request.getSession().getAttribute("picurl")!=null){
	picUrl=request.getSession().getAttribute("picurl").toString();	
}
CardTag ct = (CardTag)request.getSession().getAttribute("cardTag");
%>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta charset="utf-8">
    <title>看图识物</title>
    <script src="js/jquery.min.js"></script>
    <script src="js/main.js"></script>
    <script src="js/util.js"></script>
	 <link rel="stylesheet" type="text/css" href="css/normalize.css">
	 <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.2.0/css/font-awesome.min.css">
	 <link rel="stylesheet" type="text/css" href="css/demo.css">
	 <link rel="stylesheet" type="text/css" href="css/component.css">
	 <link rel="stylesheet" type="text/css" href="css/button.css">
	 <link rel="stylesheet" type="text/css" href="css/styles.imageuploader.css"> 
	 <link rel="stylesheet" href="css/dialog.css">
	 <link rel="stylesheet" type="text/css" href="css/andrew.mobile.style.css">
	 <link rel="stylesheet" type="text/css" href="css/theme.default.css">
	 
</head>
<body class="bgcolor-3" onload="load()">
	<section class="content bgcolor-3">
		<h2 style="opacity: 0.9;text-align: center;font-size: 150%"><%=ct.getTag() %></h2>
		
		<p style="text-align:center;padding-top:10px;">
    		<img alt="" src="/<%=picUrl %>" width="80%">
    	</p>
		
		
		<span class="input input--kyo input--filled">
			<input class="input__field input__field--kyo" type="text" id="input-19" name="meaning">
			<label class="input__label input__label--kyo" for="input-19">
				<span class="input__label-content input__label-content--kyo">请输入卡片的文字</span>
			</label>
		</span>
		
		<p style="text-align:center;">
			<button type="button" class="bigbutom">设置</button>
		</p>
	
	</section>
	
	
	
	<script type="text/javascript" src="js/dialog.js"></script>
	<script type="text/javascript">
		function load()
		{
			$.dialog({
				showTitle : false,
		        contentHtml : '<p>图片上传成功，请添加文字</p>'
		    });
		}
		
		$(".bigbutom").click(function(){
			$.post("create_own_card_add_word.html",
					  {
					    meaning:$("#input-19").attr("value"),
					  },
					  function(data,status){
						   window.location.href='cardbytag.html?tagId=<%=ct.getTagId()%>';
    		 });
			
        })
        
		
	</script>
</body>
</html>