<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.nuthabit.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta charset="utf-8">
    <title>计划</title>
    <link rel="stylesheet" href="css/main.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/main.js"></script>
    <link rel="stylesheet" type="text/css" href="css/imgareaselect-default.css" />
  	<script type="text/javascript" src="js/jquery.imgareaselect.pack.js"></script>
</head>
<body>
<div class="page blue">
    <nav class="blue">
        <span>计划</span>
        <a class="right add" href="pubPlan.jsp?rand=<%=System.currentTimeMillis() %>"></a>
    </nav>
    <div>
        <h4 class="title"><img src="frame/h4-1.png">今日目标</h4>
        <div class="todayPlan">
        	<img id="ladybug" src="upload/planpic/1512369531419.png" alt="" style="width:100%">
        	<script>
        	$(document).ready(function () {
        	    $('#ladybug').imgAreaSelect({
        	    	handles: true,
        	        onSelectEnd: function (img, selection) {
        	            $('input[name="x1"]').val(selection.x1);
        	            $('input[name="y1"]').val(selection.y1);
        	            $('input[name="x2"]').val(selection.x2);
        	            $('input[name="y2"]').val(selection.y2);            
        	        }
        	    });
        	});
        	</script>
        	<form action="crop.php" method="post">
			  <input type="hidden" name="x1" value="" />
			  <input type="hidden" name="y1" value="" />
			  <input type="hidden" name="x2" value="" />
			  <input type="hidden" name="y2" value="" />
			  <input type="submit" name="submit" value="Submit" style="width: calc(100% - 2rem);
    background: #046fdb;
    height: 1.25rem;
    text-align: center;
    line-height: 1.25rem;
    color: #fff;
    font-size: .48rem;
    border-radius: .066rem;
    margin-top: .3rem;
    margin-left: 1rem;
    margin-right: 1rem;"/>
			</form>
        </div>
        
    </div>
</div>
<footer>
    <a class="active" href="">
        <i></i>
        <span>计划</span>
    </a>
    <a href="discovery.html">
        <i></i>
        <span>发现</span>
    </a>
    <a href="">
        <i></i>
        <span>我的</span>
    </a>
</footer>

</body>
<%@ include file="ground.jsp" %>
</html>