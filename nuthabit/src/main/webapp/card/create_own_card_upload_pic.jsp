<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
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
		
		<!--             3步骤                  -->
		 <div class="module list press mt_5">
		    <h3 class="bg_white c_title pl_3 line_h_36em bor_bottom bor_gray_ddd">创建步骤</h3>
		    <article class="plug_step pa_3 bg_white dis_none rel ovh" style="display: block;">
		      <cite class="dis_block rel w_100 bor_gray_ccc bor_bottom_dashed2" style="top: 23.5px;"></cite><cite class="dis_block rel bor_title bor_bottom2" style="top: 21.5px; width: 238.547px;"></cite><ul class="length3 rel ovh">
		        <li class="fl text_al_c ak-is_active">
		          <strong class="wh_3em line_h_3em bg_gray_ccc c_white border2 bor_white bor_rad_50 bg_title c_yellow dis_block center text_al_c">1</strong>
		          <h6 class="c_gray_777 line_h_24em c_title">设定卡片组名称</h6>
		        </li>
		        <li class="fl text_al_c ak-is_active">
		          <strong class="wh_3em line_h_3em bg_gray_ccc c_white border2 bor_white bor_rad_50 bg_title c_yellow dis_block center text_al_c">2</strong>
		          <h6 class="c_gray_777 line_h_24em c_title">上传卡片图片</h6>
		        </li>
		        <li class="fl text_al_c">
		          <strong class="wh_3em line_h_3em bg_gray_ccc c_white border2 bor_white bor_rad_50 dis_block center text_al_c">3</strong>
		          <h6 class="c_gray_777 line_h_24em">设定卡片文字</h6>
		        </li>
		      </ul>
		    </article>
		  </div>
	</section>

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
	
	<script src="js/jquery.imageuploader.js"></script>
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
				contentHtml : '<%
				if(request.getAttribute("popup")==null){
					%><p>标签创建已经成功，请继续上传卡片图片，一次最多上传3张图片</p><%
				}else{
					%><p>请继续上传卡片图片，一次最多上传3张图片</p><%
				}%>'
		    });
		}
	</script>
	
	
</body>
</html>