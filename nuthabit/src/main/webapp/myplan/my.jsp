<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.nuthabit.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Kehu k = (Kehu)request.getSession().getAttribute("kehu");
%>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>习惯养成</title>
    <link rel="stylesheet" href="css/main.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/main.js"></script>
    <script type="text/javascript">
    $(function(){
        $("body").height($(window).height());

    })
    </script>
</head>
<body>
<div class="page paleBlue">
    <nav class="blue" style="background: none;z-index: 2;">
        <span>我的</span>
        <a class="left message active"></a>
        <a class="right setting"></a>
    </nav>
    <div style="  margin-top: -1.2rem;z-index: 1;">
        <div class="myInfo">
            <div class="img">
                <img src="<%=k.getHeadimgurl()%>">
            </div>
            <div>
                <span><%=k.getNickname() %></span>
                <p>关注 10<i></i>粉丝 15</p>
                <a href=""></a>
            </div>
        </div>
        <div class="my1">
            <div class="item">
                <i class="blue active"></i>
                <div>
                    <div>
                        <span>系统通知</span><i>5分钟前1</i>
                    </div>
                    <p>恭喜连续打卡20天，获得三等奖<a href="">点击领取</a></p>
                </div>
            </div>
            <div class="item">
                <i class="orange"></i>
                <div>
                    <div>
                        <span>Adon</span><i>5分钟前</i>
                    </div>
                    <p>Hi, 欢迎你使用我们!</p>
                </div>
            </div>
        </div>
        <div class="my2">
            <a class="item">
                <img src="frame/my-2.png">
                <span>已完成的计划</span>
                <p></p>
                <i></i>
            </a>
            <a class="item">
                <img src="frame/my-3.png">
                <span>已完成的计划</span>
                <p></p>
                <i></i>
            </a>
        </div>
        <div class="my2">
            <div class="item">
                <img src="frame/my-5.png">
                <span>绑定手机</span>
                <p><a href="mobile.jsp"><%=k.getShouji() %></a></p>
                <i></i>
            </div>
            <div class="item">
                <img src="frame/my-4.png">
                <span>绑定微信</span>
                <p><a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx68ddf714b6a29ea5&redirect_uri=http://www.suyufuwu.com/myplan/wxresult.jsp&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect">已绑定</a></p>
                <i></i>
            </div>
        </div>
        <div class="my2">
            <a class="item" href="logout.html" >
                <img src="frame/my-2.png">
                <span>退出</span>
                <p></p>
                <i></i>
            </a>
        </div>
    </div>
</div>
<footer>
    <a href="index.html">
        <i>
            <span class="spot"></span>
        </i>
        <span>计划</span>
    </a>
    <a href="discovery.html">
        <i>
            <span class="number">1</span>
        </i>
        <span>发现</span>
    </a>
    <a class="active" href="my.html">
        <i></i>
        <span>我的</span>
    </a>
</footer>
</body>
</body>
<%@ include file="ground.jsp" %>
</html>