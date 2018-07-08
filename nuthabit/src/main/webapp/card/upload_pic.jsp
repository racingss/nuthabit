<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
CardTag ct = null;
if(request.getSession().getAttribute("cardTag")!=null)
	ct = (CardTag)request.getSession().getAttribute("cardTag");
if(request.getParameter("tagId")!=null)
	ct = CardTag.getCartTagByTagId(Long.parseLong(request.getParameter("tagId")));
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
		<h2 style="opacity: 0.9;text-align: center;font-size: 150%">上传图片</h2>
	</section>
		              <%
					   	if(request.getParameter("mainPicId")!=null){
					   	%>
					   		<input type="hidden" name="mainPicId" id="mainPicId" value="<%=request.getParameter("mainPicId") %>" />
					   		<input type="hidden" name="cardId" id="cardId" value="<%=request.getParameter("cardId") %>" />
					   		<input type="hidden" name="slide" id="slide" value="<%=request.getParameter("slide") %>" />
					   	<%} %>

	<div class="htmleaf-container">
		<section role="main" class="l-main">
		      <div class="uploader__box js-uploader__box l-center-box">
		              <div class="uploader__contents">
		                  <label class="button button--secondary" for="fileinput">请选择图片</label>
		                  <input id="fileinput" class="uploader__file-input" type="file" multiple value="Select Files">
		              </div>
		              <input class="button button--big-bottom" type="submit" value="Upload Selected Files">
		      </div>
		  </section>
	</div>
	
	<script src="js/jquery.imageuploader1.js"></script>
	<script type="text/javascript" src="js/dialog.js"></script>
	<script type="text/javascript">
		(function(){
	            var options = {};
	            $('.js-uploader__box').uploader({
	            	'selectButtonCopy':'请选择图片文件',
	            	'instructionsCopy':'你可以选择3个图片',
	            	'submitButtonCopy':'上传选择的图片',
	            	'furtherInstructionsCopy':'你可以选择3个图片文件',
	            	'secondarySelectButtonCopy':'选择更多的图片',
	            	'ajaxUrl':'uploadbabycard.html'
	            });
	        }());
		
		function load()
		{
			$.dialog({
				showTitle : false,
				contentHtml : '<p>请上传卡片图片，一次最多上传3张图片</p>'
		    });
		}
	</script>
	
	
</body>
</html>