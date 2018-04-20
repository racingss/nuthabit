<%@ page language="java" import="java.util.*,com.nuthabit.dao.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
MyplanLesson l = (MyplanLesson)request.getAttribute("lesson");
MyplanLessonDay d = (MyplanLessonDay)request.getAttribute("day");
Collection dayColl = (Collection)request.getAttribute("dayColl");
long dayId = Long.parseLong(request.getAttribute("dayId").toString());
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title><%=d.getTitle() %></title>
    <link rel="stylesheet" href="css/calendar.css">
    <link rel="stylesheet" href="css/main.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/calendar.js"></script>
    <script src="js/main.js"></script>
    <script type="text/javascript">
    $(function(){
        $(".ckjc").click(function(){
            $(".popup2").show();
        })
        $(".popup2 .close").click(function(){
            $(".popup2").hide();
        })

        $(".more").click(function(){
            if($(this).hasClass("active")){
                $(this).removeClass("active");
                $(".pdRecord >ul li").removeClass("show");
                $(".pdRecord >ul li").eq(0).addClass("show");
                $(this).children("span").text("查看更多")
            }else {
                $(this).addClass("active");
                $(".pdRecord >ul li").addClass("show");
                $(this).children("span").text("s折叠起来")
            }
        })

        /* 只有一张照片时变大 */
        $(".pdRecord >ul >li ").each(function(){
            if($(this).children(".content").children(".imgUl").children(".img").length==1){
                $(this).children(".content").children(".imgUl").children(".img").css({"width":"4rem","height":"auto","overflow":"auto","max-height":"5rem"})
                    $(this).children(".content").children(".imgUl").children(".img").children("img").css({"width":"100%","height":"auto","position":"relative"})
            }else {
               
            }
        })

        $("nav .plan").click(function(){
            if($(this).hasClass("active")){
                $(".setBox").hide();
                $(this).removeClass("active")
            }else {
                $(".setBox").show();
                $(this).addClass("active")
            }
        })
    })
    </script>
</head>
<body style="display: block">
    <div class="page paleBlue">
        <nav class="blue">
            <span>行者知之成-<%=l.getTitle() %></span>
            <a class="left back"></a>
            <a class="right plan">
            </a>
            <ul class="setBox planBox">
                <li><a href="">加入</a></li>
                <li><a href="">分享</a></li>
                <!-- 
                <li><a href="">退出秘书</a></li>
                 -->
            </ul>
        </nav>
        <div class="sdBg">
            <img src="temp/633584715.jpg">
        </div>
        <div class="sdPlan">
            <div class="hd">
                <a href="<%if(dayId>1){%>lesson.html?lessonId=1&dayId=<%=(dayId-1)%><%}%>"><i></i>前一天</a>
                <h4><%=l.getTitle() %></h4>
                <a href="<%if(dayId<12){%>lesson.html?lessonId=1&dayId=<%=(dayId+1)%><%}%>">后一天<i></i></a>
            </div>
            <div class="bd">
                <h4>
                    <p>第<%=d.getDayIndex()%>天 <%=d.getTitle()%> </p>
                    <a class="ckjc"><i></i>查看进程</a>
                </h4>
                <div class="item">
                	<%=d.getDiscription() %>
                </div>
                <!--div class="item">
                    <h5 class="orange">任务1</h5>
                    <p>早餐：牛奶、鸡蛋X1、水果色拉</p>
                    <p>午餐：蔬菜色拉500克</p>
                    <p>晚餐：苹果X1、鸡蛋X1</p>
                </div>
                <div class="item">
                    <h5 class="green">任务2</h5>
                    <p>运动：早晚各跳绳5分钟，深蹲10个1组，做3组</p>
                </div-->
                <div class="tip">
                    <h4>提示</h4>
                    <p>即使今天的任务做不到早起也不要紧，我们明天会教你有用的方法</p>
                    <p>万事开头难，只要做了，就已经成功90%了</p>
                </div>
            </div>
        </div>
        <div class="sdOther">
            <div class="left">
                <div class="hd">
                    <img src="temp/head.jpg">
                </div>
                <h4>叶老师</h4>
                <div class="tip">
                    <span class="blue">时间管理专家</span>
                    <span class="green">易效能</span>
                    <span class="orange">认证机构</span>
                </div>
                <a class="blue" href="QAdetail.html">立即咨询</a>
            </div>
            <div class="right">
                <ul>
                    <li><img src="frame/hd-1.png"></li>
                    <li><img src="frame/hd-1.png"></li>
                    <li><img src="frame/hd-1.png"></li>
                    <li><img src="frame/hd-1.png"></li>
                    <li><img src="frame/hd-1.png"></li>
                    <li><img src="frame/hd-1.png"></li>
                    <li><img src="frame/hd-1.png"></li>
                    <li class="more">...</li>
                </ul>
                <p>56人已打卡</p>
                <a class="red" href="">打卡</a>
            </div>
        </div>

        <!-- delete footer menu -->
    </div>
    <div class="popup2">
        <div>
            <div class="itemBox">
            	<%
            	Iterator it = dayColl.iterator();
            	while(it.hasNext()){
            		MyplanLessonDay day = (MyplanLessonDay)it.next();
            	%>
                <div class="item<%if(day.getDayIndex()==dayId){%> active<%}%>">
                    <i></i>
                    <h4>第<%=day.getDayIndex() %>天</h4>
                    <p><%=day.getTitle() %></p>
                </div>
                <%} %>                
            </div>
            <div class="close">
                
            </div>

        </div>
    </div>
    
<footer>
    <a class="active" href="index.html">
        <i></i>
        <span>习惯</span>
    </a>
    <a href="discovery.html">
        <i>
            <span class="number">5</span>
        </i>
        <span>发现</span>
    </a>
    <a href="my.html">
        <i>
            <span class="spot"></span>
        </i>
        <span>我的</span>
    </a>
</footer>
</body>
</html>