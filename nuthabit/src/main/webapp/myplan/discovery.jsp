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
    <title>习惯养成</title>
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
            <a class="item" href="" style="background-image: url(frame/found-5.png);">
                <span>读书王挑战赛</span>
                <div><i class="qing">学习</i><i class="orange">读好书</i></div>
            </a>
            <a class="item" href="" style="background-image: url(frame/found-6.png);">
                <span>健身强人大比拼</span>
                <div><i class="blue">健身</i><i class="green">美体健身</i></div>
            </a>
            <a class="item" href="" style="background-image: url(frame/found-7.png);">
                <span>国粹书法鉴赏会</span>
                <div><i class="pink">书法</i><i class="orange">国学精粹</i></div>
            </a>
            
            <a class="title">热门活动</a>
        </div>
        
        
        <div class="pageLast" style="height: .4rem;"></div>
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
                            	if(myex.getPic3()==null || myex.getPic3().length()<2){
                            		%>
                            		<div class="img" style="width: 4rem; height: auto; overflow: auto; max-height: 5rem;">
		                                <img class="reviewimg" src="upload/historypic/<%=myex.getPic1()%>" style="width: 100%; height: auto; position: relative;">
		                            </div>
                            		<%
                            	}else{
                            		%>
                            		<div class="img">
		                                <img class="reviewimg"  src="upload/historypic/<%=myex.getPic1()%>">
		                            </div>
                            		<%
                            	}
                            	%>
                            <%} %>
                            <%
                            if(myex.getPic2()!=null &&myex.getPic2().length()>2){
                            	if(myex.getPic3()==null || myex.getPic3().length()<2){
                            		%>
                            		<div class="img" style="width: 4rem; height: auto; overflow: auto; max-height: 5rem;">
		                                <img class="reviewimg"  src="upload/historypic/<%=myex.getPic2()%>" style="width: 100%; height: auto; position: relative;">
		                            </div>
                            		<%
                            	}else{
                            		%>
                            		<div class="img">
		                                <img class="reviewimg"  src="upload/historypic/<%=myex.getPic2()%>">
		                            </div>
                            		<%
                            	}
                            	%>
                            	
                            	
                            	
                            <%} %><%
                            if(myex.getPic3()!=null &&myex.getPic3().length()>2){
                            %>
                            <div class="img">
                                <img class="reviewimg"  src="upload/historypic/<%=myex.getPic3()%>">
                            </div>
                            <%} %>
                        </div>
                        <div class="info">
                            <span><%=myex.getPlanDate() %></span>
                            <p><a href="detail.html?planId=<%=myex.getPlanId()%>"><%=myex.getPlanTitle() %><a></a></p>
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
            </ul>
            <div class="more">
                <span>查看更多</span>
                <i></i>
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