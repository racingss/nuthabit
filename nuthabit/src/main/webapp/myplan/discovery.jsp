<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.nuthabit.dao.*,com.nuthabit.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Collection exColl = (Collection)request.getAttribute("experienceColl");
%>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>坚果习惯</title>
    <link rel="stylesheet" href="css/swiper.min.css">
    <link rel="stylesheet" href="css/main.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/swiper.min.js"></script>
    <script src="js/main.js"></script>
    <script type="text/javascript">
    $(function(){
        $("body").height($(window).height());
        var mySwiper = new Swiper('.swiper-container',{
            loop: true,
            autoplay: 3000,
            pagination: '.swiper-pagination',
        });
    })
    </script>
</head>
<body>
<div class="page paleBlue">
    <nav class="blue">
        <span>发现</span>
        <a class="left back"></a>
        <a class="right search"></a>
    </nav>
    <div>
        <div class="tabHd">
            <a class="active" href="">精选</a>
            <a href="discovery.html?follow=t">关注</a>
            <a href="">团队</a>
            <a href="">微信</a>
        </div>
        <div class="foundBr">
          <div class="swiper-container">
            <div class="swiper-wrapper">
              <div class="swiper-slide">
                <img src="frame/found-8.png">
              </div>
              <div class="swiper-slide">
                <img src="frame/found-8.png">
              </div>
              <div class="swiper-slide">
                <img src="frame/found-8.png">
              </div>
            </div>
            <!-- 如果需要分页器 -->
            <div class="swiper-pagination"></div>
          </div>
        </div>
        <div class="found1">
            <a class="title">推荐习惯</a>
            <div>
                <a class="item" href="">
                    <img src="frame/found-1.png">
                    <span>读好书</span>
                </a>
                <a class="item" href="">
                    <img src="frame/found-2.png">
                    <span>学一门外语</span>
                </a>
            </div>
            <div>
                <a class="item"  href="">
                    <img src="frame/found-3.png">
                    <span>锻炼身体</span>
                </a>
                <a class="item" href="">
                    <img src="frame/found-4.png">
                    <span>控制脾气</span>
                </a>
            </div>
        </div>
        <div class="found2">
            <a class="title">热门活动</a>
            <a class="item" href="lesson.html?lessonId=1&dayId=1" style="background-image: url(frame/found-5.png);">
                <span>读书王挑战赛</span>
                <div><i class="qing">学习</i><i class="orange">读好书</i></div>
            </a>
            <a class="item" href="lesson.html?lessonId=1&dayId=1" style="background-image: url(frame/found-6.png);">
                <span>健身强人大比拼</span>
                <div><i class="blue">健身</i><i class="green">美体健身</i></div>
            </a>
            <a class="item" href="lesson.html?lessonId=1&dayId=1" style="background-image: url(frame/found-7.png);">
                <span>国粹书法鉴赏会</span>
                <div><i class="pink">书法</i><i class="orange">国学精粹</i></div>
            </a>
            
        </div>
        
        
        <div class="pageLast" style="height: .4rem;"></div>
    </div>
    
</div>




<footer>
    <a href="index.html">
        <i>
            <span class="spot"></span>
        </i>
        <span>习惯</span>
    </a>
    <a class="active" href="">
        <i>
            <span class="number">1</span>
        </i>
        <span>发现</span>
    </a>
    <a href="my.jsp">
        <i></i>
        <span>我的</span>
    </a>
</footer>
<div class="popup1" id="imgwindow">
 	<div>
  	<div >
      	<img src="frame/pbBg.png" id="showimg" style="width:100%;"/>
      </div>
     </div>
 </div>
<form action="experience.html" method="post" data-ajax="false">
 <div class="pubComment">
 	
 	<input type="hidden" name="id" value="">
     <input type="hidden" name="experienceId" id="experienceId" value="">
     <input type="text" name="review" placeholder="请输入评论内容">
     <i></i>
     
 </div>
 </form>
</body>
<%@ include file="ground.jsp" %>
</html>