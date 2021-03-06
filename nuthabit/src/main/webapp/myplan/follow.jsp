<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.nuthabit.dao.*,com.nuthabit.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Collection exColl = (Collection)request.getAttribute("experienceColl");
%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta charset="utf-8">
    <title>习惯养成</title>
    <link rel="stylesheet" href="css/main.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/main.js"></script>
    <script src="js/util.js"></script>
    <script type="text/javascript">
    $(function(){
        $(".zan").click(function(){
        	$.ajax({
    			url: 'thumbup.html?planId='+$(this).attr("planId")+'&experienceId='+$(this).attr("experienceId"),
    			dateType:'json',
    		    success: function(data){
    		    }
    	  	});
            if($(this).hasClass("active")){
            	$(this).removeClass("active")
            }else {
                $(this).addClass("active")
            }
        })

        $(".ping").click(function(){
        	$("#experienceId").attr("value",$(this).attr("experienceId"));
        	//alert($(this).attr("experienceId"));
            $(".pubComment").addClass("show");
            $(".pubComment input").focus();
            $(".pubComment input").blur(function(){
                $(".pubComment").removeClass("show");
            })
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

    })
    </script>
</head>
<body style="display: block">
    <div class="page paleBlue">
        <nav class="blue">
            <span>发现</span>
            <a class="left back" onclick="goBack()"></a>
            <a class="right search"></a>
            <!-- a class="right setting">
            </a>
            <ul class="setBox">
                <li><a href="">我的</a></li>
            </ul>
             -->
        </nav>
        <div class="tabHd">
            <a href="discovery.html">精选</a>
            <a class="active" href="discovery.html?follow=t">关注</a>
            <a href="">团队</a>
            <a href="">微信</a>
        </div>
        
        
        <div class="pdRecord">
            <ul>
            	<%
            	Iterator exIt = exColl.iterator();
            	int maxi=5;
            	while(exIt.hasNext()&&maxi-->0){
            		MyplanExperience myex = (MyplanExperience)exIt.next();
                    //todolist 临时使用
                    Collection thumbupExColl = new MyplanDAO().getExperienceThumbup(myex.getId());
            	%>
                <li class="show">
                    <div class="userHd">
                        <img src="<%=myex.getHeadimgurl()%>">
                    </div>
                    <div class="content">
                        <div class="hd">
                            <span><%=myex.getNickname()%></span>
                            <a class="zan<%=PageUtil.thumbupCss(thumbupExColl,request)%>" planId="<%=myex.getPlanId() %>" experienceId="<%=myex.getId()%>">点赞</a>
                            <a class="ping" experienceId="<%=myex.getId()%>">评论</a>
                        </div>
                        <div class="bd">
                            <%=myex.getReview() %>
                        </div>
                        <div class="imgUl">
                        	<%
                            if(myex.getPic1()!=null &&myex.getPic1().length()>2){
                            %>
                            <div class="img">
                                <img src="upload/historypic/<%=myex.getPic1()%>">
                            </div>
                            <%} %>
                            <%
                            if(myex.getPic2()!=null &&myex.getPic2().length()>2){
                            %>
                            <div class="img">
                                <img src="upload/historypic/<%=myex.getPic2()%>">
                            </div>
                            <%} %><%
                            if(myex.getPic3()!=null &&myex.getPic3().length()>2){
                            %>
                            <div class="img">
                                <img src="upload/historypic/<%=myex.getPic3()%>">
                            </div>
                            <%} %>
                        </div>
                        <div class="info">
                            <span><%=myex.getPlanDate() %></span>
                            <p><a href="detail.html?planId=<%=myex.getPlanId()%>"><%=myex.getPlanTitle() %><a></a></p>
                            <i></i>
                            <a href="detail.html?planId=<%=myex.getPlanId()%>&delete=t&exId=<%=myex.getId()%>">删除</a>
                        </div>
                        <div class="good" id="ex_<%=myex.getId()%>">
                        <%
                        if(thumbupExColl.size()>0){
                        %>
                        
                            <ul><%
                            	Iterator exThIt = thumbupExColl.iterator();
                                String tempNickname = "";
                                int i=0;
                                while(exThIt.hasNext() && i++<4){
                                	MyplanThumbup mt = (MyplanThumbup)exThIt.next();
                                	tempNickname = mt.getNickname();
                            		%>
                                	<li><img src="<%=mt.getHeadimgurl()%>"></li>
                                <%} %>
                            </ul>
                            <div><span>~<%=tempNickname %><%=thumbupExColl.size() %>位好友点了赞</div>
                        
                        <%} %>
                        </div>
                        <div class="comment">
                            <ul>
                            	<%
                            	//todolist 临时使用
                            	Collection replyColl = new MyplanDAO().getPlanExperienceReply(myex.getId());
                            	Iterator replyIt = replyColl.iterator();
                            	while(replyIt.hasNext()){
                            		MyplanExperience reply = (MyplanExperience)replyIt.next();
                            	%>
                                <li>
                                    <div class="left">
                                        <img src="<%=reply.getHeadimgurl()%>">
                                    </div>
                                    <div class="right">
                                        <h4>
                                            <span><%=reply.getNickname() %></span>
                                            <p><%=reply.getPlanDate().substring(5,16) %></p>
                                        </h4>
                                        <div><%=reply.getReview() %></div>   
                                    </div>
                                </li>
                                <%} %>
                                
                            </ul>
                        </div>

                    </div>
                </li>
                <%} %>
                
                
                <li>
                    <div class="userHd">
                        <img src="frame/hd-1.png">
                    </div>
                    <div class="content">
                        <div class="hd">
                            <span>Adon</span>
                            <a class="zan">点赞</a>
                            <a class="ping">评论</a>
                        </div>
                        <div class="bd">
                            身体是革命的本钱，赚再多的钱，不如拥有一个健康的身体以及健美的体魄，加油!
                        </div>
                        <div class="img">
                            <img src="frame/img-1.png">
                        </div>
                        <div class="info">
                            <span>2017年10月22日</span>
                            <p>锻炼身体</p>
                            <i></i>
                            <a href="">删除</a>
                        </div>
                        <div class="good">
                            <ul>
                                <li><img src="frame/hd-1.png"></li>
                                <li><img src="frame/hd-1.png"></li>
                                <li><img src="frame/hd-1.png"></li>
                                <li><img src="frame/hd-1.png"></li>

                            </ul>
                            <div><span>~琳琳</span>等16位好友点了赞</div>
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
                                            <p>10-22 12:34</p>
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
                                            <p>10-22 12:34</p>
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
        
    </div>
    
    <form action="experience.html" method="post" data-ajax="false">
    <div class="pubComment">
    	<input type="hidden" name="id" value="">
        <input type="hidden" name="experienceId" id="experienceId" value="">
        <input type="text" name="review" placeholder="请输入评论内容">
        <i></i>
    </div>
    </form>
    <footer>
    <a href="index.html">
        <i>
            <span class="spot"></span>
        </i>
        <span>计划</span>
    </a>
    <a href="discovery.html" class="active">
        <i>
            <span class="number">5</span>
        </i>
        <span>发现</span>
    </a>
    <a href="my.html">
        <i></i>
        <span>我的</span>
    </a>
</footer>
</body>
<%@ include file="ground.jsp" %>
</html>