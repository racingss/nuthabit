<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.nuthabit.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Collection plancoll = (Collection)request.getAttribute("plancoll");
%>
<!DOCTYPE html>
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<html>
<head>
    <meta charset="utf-8">
    <title>计划</title>
    <link rel="stylesheet" href="css/main.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/main.js"></script>
    <script type="text/javascript">
        function editPlan(planId){
            window.location.href='index.html?edit=t&planId='+planId;
            return;
        }
        function deletePlan(planId){
            window.location.href='index.html?delete=t&planId='+planId;
            return;
        }
    </script>
</head>
<body>
<div class="page blue">
    <nav>
        <span>计划</span>        
        <a class="right add text-long" href="index.html?pageadd=t&rand=<%=System.currentTimeMillis() %>"></a>
    </nav>
    <div>
        <h4 class="title"><img src="frame/h4-1.png">今日目标</h4>
        <div class="todayPlan">
            <%
            if(plancoll!=null && plancoll.size()>0 && request.getParameter("pageadd")==null&& request.getParameter("edit")==null){
                Iterator it = plancoll.iterator();
            %>
            <%
            while(it.hasNext()){
            Myplan m = (Myplan)it.next();
            if(m.isToday()){
            %>
            <div class="item">
                <div class="left">
                    <div>
                        <h4><a href="detail.html?id=<%=m.getId()%>&daka=t"><%=m.getTitle() %></a></h4>
                        <i class="time"><%if(m.getTimes()!=null && m.getTimes().trim().length()>0){ %><span class="ui-li-count"><%=m.getTimes() %></span><%} %></i>
                        <i class="day">已连续五天</i>
                    </div>
                    <span>身体是革命的本钱，身体好什么都好！</span>
                </div>
                <div class="right">
                    <a class="btn"></a>
                </div>
            </div>
            <%
            }
            } 
            %>           
        </div>
        <%
            it = plancoll.iterator();
        %>
        <h4 class="title"><img src="frame/h4-2.png">今日达标</h4>
        <div class="todayFinished">
        <%
            while(it.hasNext()){
                Myplan m = (Myplan)it.next();
                if(!m.isToday()){
        %>
            <div class="item">
                <div class="left">
                    <div>
                        <h4><a href="detail.html?id=<%=m.getId()%>"><%=m.getTitle() %></a></h4>
                        <i class="day"><span class="ui-li-count">连续打卡<%=m.getContinued() %>次</span></i>
                    </div>
                    <span>身体是革命的本钱，身体好什么都好！</span>
                </div>
                <div class="right">
                    <a class="btn"></a>
                </div>
            </div>            
        <%
                    }
                } 
            }
        %>            
        </div>
    </div>
</div>
<footer>
    <a class="active" href="">
        <i></i>
        <span>计划</span>
    </a>
    <a href="">
        <i></i>
        <span>发现</span>
    </a>
    <a href="">
        <i></i>
        <span>我的</span>
    </a>
</footer>
</body>
</html>