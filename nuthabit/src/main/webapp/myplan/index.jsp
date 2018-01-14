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
	    $(function(){
	        $("body").height($(window).height());
	
	        $("nav .switch").click(function(){
	            $(".coursePopup").show();
	        })
	        $(".coursePopup .close").click(function(){
	            $(".coursePopup").hide();
	        })
	    })
        $(function(){
            $(".todayPlan .item .left h4").each(function(e){
                    var i = $(this).text();
                    if(i.length>5){
                        i = i.substring(0,5) + "...";
                        $(this).text(i)
                    }
            })
        })

	    var host = window.location.host;
		var websocket;
		const msgDivId = "#newMessageDiv";
		
		if ('WebSocket' in window) {
			websocket = new WebSocket("ws://" + host
					+ "/socket");
		} else if ('MozWebSocket' in window) {
			websocket = new MozWebSocket("ws://" + host + "/socket");
		} else {
			websocket = new SockJS("http://" + host + "/socket/sockjs");
		}
	
		websocket.onopen = function(evnt) {
		    $(msgDivId).on("click",function(){
				alert(messages.join("\n"));
				hideMessageDiv();
			});
			console.log("websocket connected");
		}
	
		websocket.onerror = function(evnt) {
			console.log("websocket error");
		}
	
		websocket.onclose = function(evnt) {
			console.log("websocket close");
		}
		
	    function editPlan(planId){
	        window.location.href='index.html?edit=t&planId='+planId;
	        return;
	    }
	    
	    function deletePlan(planId){
	        window.location.href='index.html?delete=t&planId='+planId;
	        return;
	    }
	    
	    var messages = new Array();
	    function setMessage(msg) {
	    		messages.push(msg);
	    }
	    
	    function renderMessageNotification() {
	    		if (messages.length > 0) {
	    			$(msgDivId).css('display','flex');
	    			$(msgDivId + " > span:first").text(messages.length + "条新消息");
	    		} else {
	    			hideMessageDiv();
	    		}
	    }
	    
	    function hideMessageDiv() {
	    		$(msgDivId).css('display','none');
			$(msgDivId + " > span:first").text("");
	    }
		websocket.onmessage = function(evnt) {
			setMessage(evnt.data);
			renderMessageNotification();
			console.log("received a message " + evnt.data);
		}
		
	    window.onbeforeunload = function() {
	    		websocket.close();
	    }
    </script>
</head>
<body>
<div class="page blue">
    <nav class="blue">
    	<a class="left switch"></a>
        <span>计划</span>
        <a class="right add" href="pubPlan.jsp?rand=<%=System.currentTimeMillis() %>"></a>
    </nav>
    <div>
    	<div id="newMessageDiv" class="newMess">
            <div class="img">
                <img src="frame/hd-1.png">
            </div>
            <span>1条新信息</span>
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
<div class="coursePopup">
    <div class="coursePopupBox">
    	<a href="index.html">
        <div class="item">
                    
            	<i class="i1"></i>
            	<p>我要养成好习惯</p>
            
        </div>
        </a>
        <a href="course.html">
        <div class="item">
        	
            	<i class="i2"></i>
            	<p>我来教人养成好习惯</p>
        </div>
        </a>
        <div class="close"></div>
    </div>
</div>
</body>
<%@ include file="ground.jsp" %>
</html>