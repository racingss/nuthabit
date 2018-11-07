<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Chanpin c = new Chanpin();
if (request.getSession().getAttribute("chanpin") != null) {
	c = (Chanpin)request.getSession().getAttribute("chanpin");
}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>发布</title>
	<link rel="stylesheet" type="text/css" href="css/swiper.min.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/swiper.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#mainfile').change(function(){
				alert(document.getElementById('mainfile').files[0].name);
				document.getElementById('addproductform').submit();
			});
			var zhutu = '<%if(c.getZhutu()!=null)out.print(c.getZhutu());%>';
			var jiancheng='';
			var jiage='';
			
			$('#jiancheng').change(function(){
				jiancheng=document.getElementById('jiancheng').value;
				postDate();	
			});
			
			$('#jiage').change(function(){
				jiage=document.getElementById('jiage').value;
				postDate();
			});
			
			function postDate(){
				$.post("/diandian/productajax.html",
						  {
							zhutu:zhutu,
							jiage:jiage,
							jiancheng:jiancheng
						  },
						  function(data,status){
							  alert('ok');
				});
			}
			               
			
		})
	</script>

</head>
<body>
	<form action="addproduct.html" id="addproductform" method="post" enctype="multipart/form-data"  data-ajax="false">
	<div class="page addGoods">
        <nav>
            <a class="back"></a>
            <p>发布商品</p>
            <a class="preview">预览</a>
        </nav>
		<div>
            <div class="box1">
                <div class="add">
                	<input type="file" name="mainfile" id="mainfile"/>
                	<%
                	if(request.getAttribute("zhutu")!=null){
                		%>
                		<img src="<%if(c.getZhutu()!=null)out.print(c.getZhutu());%>"/>
                		<%
                	}else{
                	%>
                    	<img src="frame/add-goods-1.png">
                    <%} %>
                </div>
                <label>
                    <span>简要描述</span>
                    <input type="text" name="" id="jiancheng" value="<%if(c.getJiancheng()!=null)out.print(c.getJiancheng());%>" placeholder="一句话描述你的商品作为标题">
                </label>
            </div>
            <div class="box2">
                <span>商品价格</span>
                <input type="text" name="" id="jiage" value="<%if(c.getJiage()!=0)out.print(c.getJiage());%>" placeholder="给宝贝定个价吧">
                <div class="select">
                    <select>
                        <option>人民币</option>
                        <option>韩元</option>
                        <option>美元</option>
                        <option>英镑</option>
                    </select>
                </div>
            </div>
            <div class="box3">
                <h4 class="title">商品详图</h4>
                <div class="add">
                    <div class="item">
                        <img src="frame/found-6.jpg">
                    </div>
                    <div class="item">
                        <img src="frame/home-12.jpg">
                    </div>
                    <div class="item">
                        <img src="frame/home-17.jpg">
                    </div>
                    <div class="item addImg">
                        <input type="file" name="">
                    </div>


                </div>
            </div>
            <div class="box4">
                <h4 class="title">商品详图<span>0/200</span></h4>
                <textarea placeholder="请对宝贝做出详细描述"></textarea>
            </div>
            <div class="box5">
                <button class="submit">提交</button>
		    </div>
        </div>

	</div>
	</form>

</body>
</html>