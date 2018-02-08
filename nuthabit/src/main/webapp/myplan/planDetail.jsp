<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.nuthabit.dao.*,com.nuthabit.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Myplan m = (Myplan)request.getAttribute("plan");
Boolean hasSignin = (Boolean)request.getAttribute("hasSignin");
Collection coll = (Collection)request.getAttribute("detailColl");
Collection exColl = (Collection)request.getAttribute("experienceColl");
Collection thumbupColl = (Collection)request.getAttribute("thumbupColl");
%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta charset="utf-8">
    <title>坚果习惯</title>
    <link rel="stylesheet" href="css/calendar.css">
    <link rel="stylesheet" href="css/main.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/calendar.js"></script>
    <script src="js/main.js"></script>
    <script type="text/javascript">
    $(function(){
        $(".zan").click(function(){
        	if($(this).hasClass("active")){
            	$(this).removeClass("active")
            }else {
                $(this).addClass("active")
            }
        	experienceId = $(this).attr("experienceId");
        	$.ajax({
        		url: 'thumbup.html?planId='+$(this).attr("planId")+'&experienceId='+$(this).attr("experienceId"),
    			dateType:'json',
    		    success: function(data){
    		    	//$("#ex_"+experienceId).html(data);
    		    }
    	  	});
            location.replace(location.href);            
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
            <span><%=m.getTitle() %></span>
            <a class="left back" onclick="self.location=document.referrer"></a>
            <a class="right setting">
            </a>
            <ul class="setBox">
                <li><a href="index.html?edit=t&planId=<%=m.getPlanId()%>">修改计划</a></li>
                <li><a href="index.html?delete=t&planId=<%=m.getPlanId()%>">删除计划</a></li>
                <li><a href="secretary.html">私人计划</a></li>
                <li><a href="supervision.html">全民监督</a></li>
                <li><a href="supeDetail.html">课程计划</a></li>
            </ul>
        </nav>
        <div class="pdBg">
            <img src="<%
            if(m.getPic()!=null && m.getPic().length()>2){
            	out.print("upload/planpic/"+m.getPic());
            }else{
            	out.print("frame/pbBg.png");
            }
            %>">
        </div>
        <div class="pdInfo">
            <h4>计划目标</h4>
            <div><%=m.getDiscription() %></div>
        </div>
        <div class="pdBtn">
            <div class="left">
                <div>
                    <div class="hd"><img src="<%=m.getHeadimgurl()%>"></div>
                    <div class="bd">
                        <span><%=m.getKehuNick()%></span>
                        <a><%=m.getCreateDate()%></a>
                    </div>
                </div>
                <p>已累计坚持<span> <%=m.getContinued() %> </span>天</p>
            </div>
            <% if (hasSignin) { %>
            <div>已打卡</div>            
            <% } else { %>
            <div class="middle daka">
            </div>
            <% } %>
            <script type="text/javascript">
            $(function(){
                daka();
            })
            function daka() {
                $(".daka").click(function(){
                	$.ajax({
            			url: 'signin.html?ajax=t&id=<%=m.getId()%>',
            			dataType:'json',
            		    success: function(data){
            		    	if(data=='-1')
            		    		$("#result").text("错误");
            		    	else
            		    		$("#result").text("已经连续打卡"+data+"天，再连续打卡99天就可以完成目标了");
            		    }
            	  	});
                    $("#dakawindow").show();
                })
                $(".popup1 .close").click(function(){
                    $(".popup1").hide();
                	window.location.href='detail.html?planId=<%=m.getPlanId()%>';
                	return;
                })
                $(".reviewimg").click(function(){
                	$("#showimg").attr("src",$(this).attr("src"));
                	 $("#imgwindow").show();
                })
                $("#imgwindow").click(function(){
                	$(".popup1").hide();
                })
            }
            </script>
            <div class="right">
                <ul>
                	<%
                	Iterator thIt = thumbupColl.iterator();
                	int j=0;
                	while(thIt.hasNext()&&j++<7){
                		MyplanThumbup th = (MyplanThumbup)thIt.next();
                	%>
                    	<li><img src="<%=th.getHeadimgurl()%>"></li>
                    <%} %>
                    
                    <li class="more">...</li>
                </ul>
                <p><%=m.getThumbup() %>人支持</p>
            </div>
        </div>

        <div class="bigDate">
            <div id="container"></div>
            <div class="bigDateInfo">
                <span>本月连续打卡18天</span>
            </div>
        </div>
        <script>
        var dakaDaysArray = [];
        $.ajax({
			url: 'dakahistory.html?planId=<%=m.getId()%>',
			dataType:'json',
		    success: function(data){
		    	if(data!='') {
		    		dakaDaysArray = data;
		    	}
		    	new Calendar({
		            // 用户传入实际的数据
		            container: 'container',
		            angle: 14,
		            isMask: false, // 是否需要弹层
		            beginTime: [2017,1,1],//如空数组默认设置成1970年1月1日开始,数组的每一位分别是年月日。
		            endTime: [],//如空数组默认设置成次年12月31日,数组的每一位分别是年月日。
		            recentTime: [],//如空数组默认设置成当月1日,数组的每一位分别是年月日。
		            isSundayFirst: true, // 周日是否要放在第一列
		            isShowNeighbor: true, // 是否展示相邻月份的月尾和月头
		            isToggleBtn: true, // 是否展示左右切换按钮
		            isChinese: true, // 是否是中文
		            monthType: 0, // 0:1月, 1:一月, 2:Jan, 3: April
		            canViewDisabled: true, // 是否可以阅读不在范围内的月份
		            beforeRenderArr: dakaDaysArray,
		            success: function (item, arr) {
		                console.log(item, arr);
		            },
		            switchRender: function (year, month, cal) {
		                console.log('计算机识别的 - 年份: ' + year + ' 月份: ' + month);
		                var dakaKey = year + ',' + month;
		                var dakaCache = $("#container").data(dakaKey);
		                if (dakaCache != undefined) {
		                		cal.renderCallbackArr(dakaCache);
		                } else {
			                $.ajax({
			            			url: 'dakahistory.html?planId=<%=m.getId()%>&from=' + year + ',' + month,
			            			dataType:'json',
			            		    success: function(data){
			            		    		$("#container").data(dakaKey , data);
				            		    	if(data!='') {
				            		    		cal.renderCallbackArr(data);				            		    		
				            		    	}
			            		    }
			            	  	});
		                }
		            }
		        });
		    }
	  	});   
        </script>
        <div class="pdRecord">
            <h4>心得感悟<a class="pdRecordAdd" href="signin.html?id=<%=m.getId()%>"></a></h4>
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
        <div class="pdBtns">
            <a href="index.html">首页</a>
            <a href="pubPlan.jsp?rand=<%=System.currentTimeMillis() %>">发布</a>
            <a href="">微信</a>
        </div>
    </div>
    <div class="popup1" id="dakawindow">
        <div>
            <div class="hd">
                <span>恭喜你</span>
                <p id="result">已经连续打卡18天，再连续打卡99天就可以完成目标了</p>
            </div>
            <div class="bd">
                <ul class="checkBox">
                    <li>
                        <i></i>
                        <a>使用私人秘书</a>
                        <span>每日定时提醒</span>
                    </li>
                    <li>
                        <i></i>
                        <a>使用全民监督</a>
                        <span>大家一起监督</span>
                    </li>
                    <li>
                        <i></i>
                        <a href="signin.html?id=<%=m.getId()%>">发布心得体会</a>
                        <span>记录自己的成长</span>
                    </li>
                </ul>
                <p>书山有路勤为径，学海无涯苦作舟。每当你想放弃，想想为什么我要做这件事情..</p>
            </div>
            <div class="close">
                <span></span>
            </div>
        </div>
    </div>
    
    <div class="popup1" id="imgwindow">
    	<div>
	    	<div >
	        	<img src="frame/pbBg.png" id="showimg" style="width:100%;"/>
	        </div>
        </div>
    </div>
    
    <form action="experience.html" method="post" data-ajax="false">
    <div class="pubComment">
    	
    	<input type="hidden" name="id" value="<%=m.getId()%>">
        <input type="hidden" name="experienceId" id="experienceId" value="">
        <input type="text" name="review" placeholder="请输入评论内容">
        <i></i>
        
    </div>
    </form>
</body>
</html>