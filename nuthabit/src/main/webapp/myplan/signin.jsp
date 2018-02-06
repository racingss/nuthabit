<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.nuthabit.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Myplan m = (Myplan)request.getAttribute("plan");
%>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>坚果习惯——发布心得</title>
    <link rel="stylesheet" href="css/IMGUP.css">
    <link rel="stylesheet" href="css/main.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/main.js"></script>
    <script type="text/javascript" src="js/cvi_busy_lib.js"></script>
    <script >
        $(function(){
            $("body").css({"min-height":$(window).height(),"display":"block"});
            $(".page").css("min-height",$(window).height());

            $(".bianji").click(function(){
                if($(this).hasClass("active")){
                    $(this).removeClass("active");
                    $(this).text("编辑");
                    $(".lookimg_delBtn").hide();
                }else {
                    $(this).addClass("active");
                    $(this).text("编辑中");
                    $(".lookimg_delBtn").show();

                }
            })
        })
    </script>
</head>
<body>
<div class="page paleBlue" >
    <nav class="blue">
        <a class="left back" onclick="goBack()"></a>
        <span>发布心得体会</span>
    </nav>
    <!--form action="experience.html" method="post" enctype="multipart/form-data"  data-ajax="false"-->
    <input type="hidden" name="processadd" value="t" />
    <input type="hidden" name="id" value="<%=m.getPlanId() %>" id="planId"/>
    <div>
        <div class="textArea1">
            <textarea id="review" name="review" placeholder="写下这一刻的心得体会(可空)"></textarea>
        </div>
        <div class="inputBox1">
            <span>隐私</span>
            <p>公开大家都能看到</p>
            <div class="switchBox active">
                <i></i>
                <a></a>
                <input type="text" name="" value="1">
            </div>
        </div>
        <!--div class="inputBox2">
            <span>完成度</span>
            <ul class="checkBox">
                <li class="active">
                    <i></i>
                    <a>全部完成</a>
                </li>
                <li>
                    <i></i>
                    <a>部分完成</a>
                </li>
                <li>
                    <i></i>
                    <a>未完成</a>
                </li>
                <input type="text" name="" value="">
            </ul>
        </div-->
        <div class="inputBox3">
            <div class="title">
                <span >图片</span>
                <p>(0/3)可空</p>
                <a class="bianji">编辑</a>
            </div>
            <div class="uploadImg">
                <ul class="z_photo">
                    <div>
                        <div id="div_imgfile" class="file"></div>
                        <ul id="div_imglook">
                            <div style="clear: both;width: auto;"></div>
                        </ul>
                    </div>
                </ul>
            </div>
        </div>
        <button class="submitBtn" id="signin">发布</button>
    </div>
    <!--/form-->
    <div class="pageLast"></div>
</div>
<script src="js/IMGUP.js"></script>


</body>
</html>