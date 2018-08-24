<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>打卡——卡片点点</title>
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<script>
        $(function(){
            //生成进度条长度
            var day = parseInt($("#progress").attr("data-day"));
            var medalWidth = $("#progress .round li").width();
            var barWidth = $("#progress .bar").width();
            var canUseBar = barWidth - 4 * medalWidth;
            var arr = [1,15,30,60,90];
            var num = 0;
            for(var i =0;i<arr.length;i++){
                if(day >=arr[i]){
                    num++;
                    $("#progress .round li:eq("+i+")").addClass("active");
                }
            }
            var barDivWidth = 0;
            if(num <3){
                barDivWidth = medalWidth*0.5 +  0.5*canUseBar*(day/30) + (num-1)*medalWidth;
                if(day==1||day==15){
                    barDivWidth = barDivWidth-10;
                }
                $("#progress .bar div").width(barDivWidth);
            }else {
                barDivWidth = medalWidth*0.5 + 0.5*canUseBar + 2*medalWidth +  0.5*canUseBar*((day-30)/60) + (num-3)*medalWidth;
                if(day==30||day==60||day==90){
                    barDivWidth = barDivWidth-10;
                }
                $("#progress .bar div").width(barDivWidth);
            }
        })
    </script>
	
</head>
<body>
	<div class="page clockIn">   
        <div>
            <div class="title">
                <h4>连续打卡天数</h4>
                <p>今天是:2018年08月18日</p>
            </div>
            <div class="calendar">   
                <div>
                    26
                </div>
            </div>
            <div class="progress" id="progress" data-day="90">
                <div class="bar">
                    <div></div>
                </div>
                <ul class="round">
                    <li class="active">
                        <div></div>
                        <span>第<i>1</i>天</span>
                    </li>
                    <li  class="active">
                        <div></div>
                        <span>第<i>15</i>天</span>
                    </li>
                    <li  class="active">
                        <div></div>
                        <span>第<i>30</i>天</span>
                    </li>
                    <li>
                        <div></div>
                        <span>第<i>60</i>天</span>
                    </li>
                    <li>
                        <div></div>
                        <span>第<i>90</i>天</span>
                    </li>
                </ul>
                <ul class="bg">
                    <i></i>
                    <i></i>
                    <i></i>
                    <i></i>
                    <i></i>
                </ul>
            </div>
            <div class="bonus">
                <div class="hd">
                    <img src="frame/clockIn-2.png">
                </div>
                <div class="bd">
                    <img src="frame/clockIn-3.png">
                    <p>连续打卡<i>90</i>天,返回终身使用费<i>299</i>元</p>
                    <p>还可以参加iphone抽奖活动</p>
                </div>
            </div>
        </div>
	<img class="bg" src="frame/clockIn-5.png"/>
        <footer>
            <a href="home.html">
                <i></i>
                <span>首页</span>
            </a>
            <a href="subscribe.html">
                <i></i>
                <span>订阅</span>
            </a>
            <a href="">
                <i></i>
                <span>打卡</span>
            </a>
            <a href="parents.html">
                <i></i>
                <span>家长</span>
            </a>
        </footer>
    </div>

	
	
</body>
</html>