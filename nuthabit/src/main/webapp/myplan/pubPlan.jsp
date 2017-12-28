<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.nuthabit.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Collection plancoll = (Collection)request.getAttribute("plancoll");
Myplan m = null;
if(request.getAttribute("myplan")!=null)
	m = (Myplan)request.getAttribute("myplan");
%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta charset="utf-8">
    <title>习惯养成</title>
    <link rel="stylesheet" href="css/main.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/imgPlugin.js"></script>
    <script src="js/DateSelector.js"></script>
    <script src="js/main.js"></script>
    <script type="text/javascript" src="js/cvi_busy_lib.js"></script>
    <script >
        $(function(){
            dateBox();
            $("body").css({"min-height":$(window).height(),"display":"block"});
            $(".page").css("min-height",$(window).height());
        })
        
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
<div class="page paleBlue">
    <nav class="blue">
        <a class="left back" onclick="goBack()"></a>
        <span><%if(m!=null){out.print("修改计划");}else out.print("发起计划"); %></span>
    </nav>
    <!--form enctype="multipart/form-data" method="post" action="addplan.html"-->
    <%
    if(m!=null){
    	%>
    	<input type="hidden" name="processedit" value="t" />
    	<input type="hidden" name="id" value="<%=m.getId() %>" />
    	<%
    }
    %>
    <div>
        <div class="inputBox4">
            <div class="i1">
                <span>标题</span>
                <input type="text" name="title" id="title" 
                <%if(m!=null){
                	%>value="<%=m.getTitle()%>"<%
                }else{
                	%>placeholder="标题必输入，如跑步，读书，学英文等"<%
                }%>
                />
            </div>
            <div class="i2">
                <span>目标</span>
                <div>
                    <textarea name="discription" id="discription"  
                <%if(m!=null){
                }else{
                	%>placeholder="可填写为什么或者目标，例如为了家人，越详细越能激励自己，可以后再填。"<%
                }%>
                /><%if(m!=null){%><%=m.getDiscription()%><%}%></textarea>
                </div>
            </div>
            <div class="i1">
                <span>频率</span>
                <input type="text" name="times" id="times"  
                <%if(m!=null){
                	%>value="<%=m.getTimes()%>"<%
                }else{
                	%>placeholder="可填写30分钟，每周5次之类的，可以后再填。"<%
                }%>
                />
            </div>
        </div>
        <div class="inputBox5">
            <div class="hd">
                <span>图片</span>
                <p>可上传能激励自己的图片</p>
                <div class="switchBox hasson">
                    <i></i>
                    <a></a>
                    <input  type="text" name="" value="0">
                </div>
            </div>
            
            <div class="bd">
                <div>
                    <p class="jlImg"></p>
                    <a>上传文件</a>
                    <input type="file" id="pic" onchange="getPhoto(this)" value="" />
				    <input name="upload" type="file" style="position:absolute;filter:alpha(opacity=20);width:30px;" id="t_file" hidefocus>  
                    
                </div>
                <p>
                	<%
                	if(m!=null && m.getPic()!=null){
                		out.print(m.getPic());
                	}
                	%>
                </p>
            </div>
        </div>
        <div class="inputBox6">
            <div class="hd">
                <span>时间范围</span>
                <div class="switchBox hasson">
                    <i></i>
                    <a></a>
                    <input type="text" name="dateperiod" value="0">
                </div>
            </div>
            <div class="bd">
                <ul>
                    <li>
                        <span>计划开始时间</span>
                        <div class="dateBox">
                            <input id="date-selector-input" name="startdate" value="请选择日期" type="text" readonly/>
                            <i></i>
                        </div>
                    </li>
                    <li>
                        <span>计划结束时间</span>
                        <div class="dateBox">
                            <input id="date-selector-inputs" name="enddate" value="请选择日期" type="text" readonly/>
                            <i></i>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <div class="inputBox1">
            <span>隐私</span>
            <p>公开计划大家都能看到</p>
            <div class="switchBox">
                <i></i>
                <a></a>
                <input type="text" name="" value="0">
            </div>
        </div>
        <div class="inputBox7">
            <div class="hd">
                <span>高级服务</span>
                <div class="switchBox hasson">
                    <i></i>
                    <a></a>
                    <input type="text" name="enabledvip" value="0">
                </div>
            </div>
            <div class="bd">
                <ul class="checkBox checkBox1">
                    <li class="active">
                        <i></i>
                        <a>使用私人秘书</a>
                        <p>每日定时提醒</p>
                    </li>
                    <li>
                        <i></i>
                        <a>使用全民监督</a>
                        <p>大家一起来监督</p>
                    </li>
                    <li>
                        <i></i>
                        <a>不，谢谢</a>
                        <p>不使用服务</p>
                    </li>
                    <input type="text" name="vipopt" value="">
                </ul>
            </div>
        </div>

        <button type="submit" class="submitBtn" id="pubPlan">提交计划</button>
    </div>
    <!--/form-->
    <div class="pageLast"></div>

</div>
        <div id="targetContainer"></div>
        <div id="targetContainers"></div>
<script src="js/IMGUP.js"></script>
</body>

</html>