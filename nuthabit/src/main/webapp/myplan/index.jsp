<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.nuthabit.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Collection plancoll = (Collection)request.getAttribute("plancoll");
%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta charset="utf-8">
    <title>计划</title>
    <link rel="stylesheet" href="css/main.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/main.js"></script>
    <script src="js/util.js"></script>
    <script src="js/reconnecting-websocket.js"></script>
    <script src="js/sockjs.js"></script>
    <script type="text/javascript">
    		var websocket = initWebsocket();
	    function editPlan(planId){
	        window.location.href='index.html?edit=t&planId='+planId;
	        return;
	    }
	    function deletePlan(planId){
	        window.location.href='index.html?delete=t&planId='+planId;
	        return;
	    }
	    window.onbeforeunload = function() {
	    		websocket.close();
	    }
    </script>
</head>
<body>
<div class="page blue">
    <nav class="blue">
    	<a class="left message active"></a>
        <span>计划</span>
        <a class="right add" href="pubPlan.jsp?rand=<%=System.currentTimeMillis() %>"></a>
    </nav>
    <div>
    	<div class="newMess">
            <div class="img">
                <img src="frame/hd-1.png">
            </div>
            <span>3条新信息</span>
        </div>
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
                        <h4><a href="detail.html?planId=<%=m.getPlanId()%>"><%=m.getTitle() %></a></h4>
                        <i class="time"><%if(m.getTimes()!=null && m.getTimes().trim().length()>0){ %><span class="ui-li-count"><%=m.getTimes() %></span><%} %></i>
                        <%
                        if(m.getContinued()!=0){
                        %>
                        <i class="day">已连续<%=m.getContinued() %>天</i>
                        <%}else{ %>
                        <i class="new">新计划</i>
                        <%} %>
                    </div>
                    <span><%=m.getDiscription()%></span>
                </div>
                <div class="right">
                    <a class="btn" href="detail.html?planId=<%=m.getPlanId()%>"></a>
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
                        <h4><a href="detail.html?planId=<%=m.getPlanId()%>"><%=m.getTitle() %></a></h4>
                        <i class="day">累计<%=m.getContinued() %>天</span></i>
                    </div>
                    <span><%=m.getDiscription()%></span>
                </div>
                <div class="right">
                    <a class="btn" href="detail.html?planId=<%=m.getPlanId()%>"></a>
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
    <a class="active" href="index.html">
        <i></i>
        <span>计划</span>
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
<%@ include file="ground.jsp" %>
</html>