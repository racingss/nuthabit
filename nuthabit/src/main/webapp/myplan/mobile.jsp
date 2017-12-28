<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.nuthabit.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Kehu k = (Kehu)request.getSession().getAttribute("kehu");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>习惯养成</title>
    <link rel="stylesheet" href="css/main.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/main.js"></script>
    <script type="text/javascript">
        $(function(){
            $("body").height($(window).height());

            $(".bindBox div input").focus(function(){
                $(".bindBox div i").show();
            })
            /*$(".bindBox div input").blur(function(){
                $(".bindBox div i").hide();
            })*/
            $(".bindBox div i").click(function(){
                $(".bindBox div input").attr("value","");
                $(".bindBox div i").hide();
            })
        })
    </script>
</head>
<body>
    <div class="page paleBlue">
        <nav class="default">
            <span>绑定手机号</span>
            <a class="left close" onclick="goBack()"></a>
        </nav>
        <div class="bindBox">
            <p>请输入11位手机号码<span>(仅支持大陆手机号)</span></p>
            <div>
                <input type="number" name="">  
                <i></i>
            </div>
            <button>下一步</button>
        </div>

    </div>
</body>
<%@ include file="ground.jsp" %>
</html>