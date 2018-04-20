<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta charset="utf-8">
    <title>看图识物</title>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/card.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/main.js"></script>
    <script src="js/util.js"></script>
    <script type="text/javascript">
	    $(function(){
	        $("body").height($(window).height());
	    })
	 </script>
	 <style type="text/css">
	 	.leibie{
	 		width: 43%;
    		text-align: center;
    		float: left;
    		padding: 20px;
    		font-size: 1.5em;
    		color: white;
	 	}
	 </style>
</head>
<body style="display: block">
<jsp:include page="head.jsp" flush="true"/>
    
<div class="">
	<div style="width: 100%;
    text-align: center;
    background-color: #ef4684;
    color: #fff;
    font-size: 2.0em;
    padding: 10px;">
		第一步 上传图片
	</div>
    <form action="uploadbabycard.html" method="post" enctype="multipart/form-data"  data-ajax="false">
    <div>
        
        <div class="inputBox3">
            <div class="title">
                <input type="file" name="cardfile" id="cardfile" />
            </div>
        </div>
        <button class="submitBtn" id="signin" style="background-color: #ef4684;">上传</button>
    </div>
    </form>
    <div class="pageLast"></div>
    
    
</div>
</body>
</html>