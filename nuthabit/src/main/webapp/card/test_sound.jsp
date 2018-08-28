<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
long languageId = new LanguageHttp().getLanguageId(request);
%>
<!doctype html>
<html lang="zh" class="no-js">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title><%=Menu.getTitle(languageId) %></title>
	<link rel="stylesheet" type="text/css" href="css/default_uploadfile.css" />
	<link rel="stylesheet" type="text/css" href="css/component_uploadfile.css" />
	<link rel="stylesheet" type="text/css" href="css/demo.css">
	<link rel="stylesheet" type="text/css" href="css/andrew.mobile.style.css">
	<link rel="stylesheet" type="text/css" href="css/button.css">
	
	<!-- remove this if you use Modernizr -->
		<script>(function(e,t,n){var r=e.querySelectorAll("html")[0];r.className=r.className.replace(/(^|\s)no-js(\s|$)/,"$1js$2")})(document,window,0);</script>
</head>
<body style="font-size: 12px;
    margin: 0;
    padding: 0;
    font-weight: normal;
    line-height: inherit;
    background-image: url(/diandian/frame/home-18.jpg);
    color: #404d5b;">
<section class="content">    
    <h2 style="opacity: 0.9;text-align: center;font-size: 150%;margin: 0;">
    	<%if(request.getParameter("cover")!=null) {%>
    		<%=Menu.getMenu("upload_1", languageId) %>：80X108）
    	<%}else if(request.getParameter("sound")!=null) {%>
			<%=Menu.getMenu("upload_2", languageId) %>
		<%}else{ %>
    		<%=Menu.getMenu("upload_3", languageId) %>
    	<%} %>
    </h2>
	
	
	<form action="uploadbabycard.html" method="post" enctype="multipart/form-data"  data-ajax="false">
	
	<input type="hidden" name="cardId" value="<%=request.getParameter("cardId") %>" />    	
   	<input type="hidden" name="sound" value="t" />
   	<%
   	if(request.getParameter("languageId")!=null){
   	%>
   		<input type="hidden" name="languageId" value="<%=request.getParameter("languageId") %>" />
   	<%} %>
   	<%
   	if(request.getParameter("distLanguage")!=null){
   	%>
   		<input type="hidden" name="distLanguage" value="<%=request.getParameter("distLanguage") %>" />
   	<%} %>
   	
   	
   	<%
   	if(request.getParameter("effect")!=null){
   	%>
   		<input type="hidden" name="effect" value="<%=request.getParameter("effect") %>" />
   	<%} %>
   	
   	<%
   	if(request.getParameter("picId")!=null){
   	%>
   		<input type="hidden" name="picId" value="<%=request.getParameter("picId") %>" />
   	<%} %>
   	
   	<%
   	if(request.getParameter("cover")!=null){
   	%>
   		<input type="hidden" name="cover" value="t" />
   	<%} %>
   	
   	<%
   	if(request.getParameter("second")!=null){
   	%>
   		<input type="hidden" name="second" value="t" />
   	<%} %>
   	
   	<%
   	if(request.getParameter("mainPicId")!=null){
   	%>
   		<input type="hidden" name="mainPicId" value="<%=request.getParameter("mainPicId") %>" />
   	<%} %>
	
	<%
   	if(request.getParameter("slide")!=null){
   	%>
   		<input type="hidden" name="slide" value="<%=request.getParameter("slide") %>" />
   	<%} %>
	<div class="container">
			<div class="content">
				
				<div class="box">
					<input type="file" name="file-1[]" id="file-1" class="inputfile inputfile-1" data-multiple-caption="{count} files selected" multiple />
					<label for="file-1" style="font-size: 1.5rem;padding: 2rem 3rem;">
						<svg style="width: 2em;height: 2em;"xmlns="http://www.w3.org/2000/svg" width="20" height="17" viewBox="0 0 20 17"><path d="M10 0l-5.2 4.9h3.3v5.1h3.8v-5.1h3.3l-5.2-4.9zm9.3 11.5l-3.2-2.1h-2l3.4 2.6h-3.5c-.1 0-.2.1-.2.1l-.8 2.3h-6l-.8-2.2c-.1-.1-.1-.2-.2-.2h-3.6l3.4-2.6h-2l-3.2 2.1c-.4.3-.7 1-.6 1.5l.6 3.1c.1.5.7.9 1.2.9h16.3c.6 0 1.1-.4 1.3-.9l.6-3.1c.1-.5-.2-1.2-.7-1.5z"/></svg> <span>Choose a file&hellip;</span></label>
				</div>

			</div>
			
	</div><!-- /container -->
	
	<p style="text-align:center;">
		<button type="submit" class="bigbutom" style="font-size: 25px;padding: 10px;"><%=Menu.getMenu("upload_but", languageId) %></button>

	</p>
	</form>
</section>	
	
	
	<script src="js/custom-file-input.js"></script>

	<!-- // If you'd like to use jQuery, check out js/jquery.custom-file-input.js
	<script src="js/jquery-2.1.1.min.js"></script>
	<script src="js/jquery-v1.min.js"></script>
	<script src="js/jquery.custom-file-input.js"></script>
	-->
</body>
</html>