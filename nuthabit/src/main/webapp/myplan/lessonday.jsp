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
    <title>行者知之成——行动力课程</title>
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
                <li><a href="">加入计划</a></li>
                <li><a href="">分享计划</a></li>
                <li><a href="">退出秘书</a></li>
            </ul>
        </nav>
        <div class="sdBg">
            <img src="frame/supeD-5.png">
        </div>
        <div class="sdPlan">
            <div class="hd">
                <a href="<%if(dayId>1){%>lesson.html?lessonId=1&dayId=<%=(dayId-1)%><%}%>"><i></i>前一天</a>
                <h4><%=l.getTitle() %></h4>
                <a href="<%if(dayId<2){%>lesson.html?lessonId=1&dayId=<%=(dayId+1)%><%}%>">后一天<i></i></a>
            </div>
            <div class="bd">
                <h4>
                    <p>第<%=d.getDayIndex()%>天 <%=d.getTitle()%> </p>
                    <a class="ckjc"><i></i>查看进程</a>
                </h4>
                <div class="item">
                	<%=d.getDiscription() %>
                </div>
                <div class="item">
                    <h5 class="orange">任务1</h5>
                    <p>早餐：牛奶、鸡蛋X1、水果色拉</p>
                    <p>午餐：蔬菜色拉500克</p>
                    <p>晚餐：苹果X1、鸡蛋X1</p>
                </div>
                <div class="item">
                    <h5 class="green">任务2</h5>
                    <p>运动：早晚各跳绳5分钟，深蹲10个1组，做3组</p>
                </div>
                <div class="tip">
                    <h4>提示</h4>
                    <p>光减少摄入无法有持续降低体脂，只有减脂的同时增加肌肉，肌肉能帮助人体每天消耗更多的热量</p>
                    <p>另外人体每日需要不同的营养，所以每日必须摄入足够的钙、蛋白质以及各类维生素</p>
                </div>
            </div>
        </div>
        <div class="sdOther">
            <div class="left">
                <div class="hd">
                    <img src="frame/hd-1.png">
                </div>
                <h4>大蓝老师</h4>
                <div class="tip">
                    <span class="blue">美体专家</span>
                    <span class="green">健康师</span>
                    <span class="orange">营养师</span>
                </div>
                <a class="blue" href="">立即咨询</a>
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

        <div class="pdRecord sdRecord">
            <h4>讨论区</h4>
            <ul>
                <li class="show">
                    <div class="userHd">
                        <img src="frame/hd-1.png">
                    </div>
                    <div class="content">
                        <div class="hd">
                            <span>Adon</span>
                        </div>
                        <div class="bd">
                            身体是革命的本钱，赚再多的钱，不如拥有一个健康的身体以及健美的体魄，加油!
                        </div>
                        <div class="imgUl">
                            <div class="img">
                                <img src="frame/img-2.png">
                            </div>
                        </div>
                        <div class="comment">
                            <ul>
                                <li>
                                    <div class="left">
                                        <img src="frame/hd-1.png">
                                    </div>
                                    <div class="right">
                                        <h4>
                                            <span>汪汪队小力</span>
                                        </h4>
                                        <div>加油！你一定行的！希望你能越来越好，活出真正的自己！</div>   
                                    </div>
                                </li>
                                <li>
                                    <div class="left">
                                        <img src="frame/hd-1.png">
                                    </div>
                                    <div class="right">
                                        <h4>
                                            <span>汪汪队小力</span>
                                        </h4>
                                        <div>加油！你一定行的！希望你能越来越好，活出真正的自己！</div>   
                                    </div>
                                </li>
                            </ul>
                        </div>

                    </div>
                </li>
                <li>
                    <div class="userHd">
                        <img src="frame/hd-1.png">
                    </div>
                    <div class="content">
                        <div class="hd">
                            <span>Adon</span>
                        </div>
                        <div class="bd">
                            身体是革命的本钱，赚再多的钱，不如拥有一个健康的身体以及健美的体魄，加油!
                        </div>
                        <div class="imgUl">
                            <div class="img">
                                <img src="frame/img-1.png">
                            </div>
                            <div class="img">
                                <img src="frame/img-1.png">
                            </div>
                            <div class="img">
                                <img src="frame/img-1.png">
                            </div>
                        </div>
                        <div class="comment">
                            <ul>
                                <li>
                                    <div class="left">
                                        <img src="frame/hd-1.png">
                                    </div>
                                    <div class="right">
                                        <h4>
                                            <span>汪汪队小力</span>
                                        </h4>
                                        <div>加油！你一定行的！希望你能越来越好，活出真正的自己！</div>   
                                    </div>
                                </li>
                                <li>
                                    <div class="left">
                                        <img src="frame/hd-1.png">
                                    </div>
                                    <div class="right">
                                        <h4>
                                            <span>汪汪队小力</span>
                                        </h4>
                                        <div>加油！你一定行的！希望你能越来越好，活出真正的自己！</div>   
                                    </div>
                                </li>
                            </ul>
                        </div>

                    </div>
                </li>

            </ul>
            <div class="more">
                <span>查看更多</span>
                <i></i>
            </div>
        </div>
        <div class="pdBtns">
            <a href="index.html">首页</a>
            <a href="pubPlan.jsp">发布</a>
            <a href="">微信</a>
        </div>
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
</body>
</html>