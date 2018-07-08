<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.nuthabit.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Kehu k = null;
if (request.getSession().getAttribute("kehu") != null) {
	k = (Kehu) request.getSession().getAttribute("kehu");
} else {
	request.getRequestDispatcher("loginwx.jsp").forward(request, response);
	return;
}
Myplan m = new MyplanDAO().getDefault(k.getKehuId());
%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta charset="utf-8">
    <title>坚果习惯</title>
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
        <span>我的列表</span>
    </nav>
    <form enctype="multipart/form-data" method="post" action="addplan.html">
    <input type="hidden" name="defaultFlag" id="defaultFlag" value="9" />
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
            
            <div class="i2">
                <div  style="height: 8rem;">
                    <textarea name="discription" id="discription"  
                <%if(m!=null){
                }else{
                	%>placeholder="请输入"<%
                }%>
                /><%if(m!=null){%><%=m.getDiscription()%><%}%></textarea>
                </div>
            </div>
        </div>
        
        
        <button type="submit" class="submitBtn" id="pubPlan">提交</button>
    </div>
    </form>
    <div class="pageLast"></div>

</div>

</body>

</html>