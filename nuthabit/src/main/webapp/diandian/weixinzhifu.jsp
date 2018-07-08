<%@page import="com.babycard.wx.*"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
long languageId = new LanguageHttp().getLanguageId(request);
Dingdan dingdan = (Dingdan)request.getAttribute("dingdan");
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title><%=Menu.getTitle(languageId) %></title>
	<link rel="stylesheet" type="text/css" href="css/swiper.min.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/swiper.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript">
		$(function(){
			var mySwiper = new Swiper('.swiper-container',{
          		loop: true,
        		autoplay: 3000,
        		pagination: '.swiper-pagination',
        	});
			
			$(".i7").click(function(){
				$(this).text("查询中。。。");
				$.ajax({
					url: '/diandian/order.html?type=query&dingdanId=<%=dingdan.getDingdanId()%>',
					dateType:'json',
				    success: function(data){
				    	if(data=='<%=Dingdan.STATUS_YIZHIFU%>'){
				    		alert("支付成功，您可以在家长页面查看订阅有效期");
				    		window.location.href='/diandian/parents.html';
				    	}else  if(data=='<%=Dingdan.STATUS_DAIZHIFU%>'){
				    		alert("等待支付");
				    	}
				    }
			   });
			})

		})
	</script>
	<style type="text/css">
	
	</style>
</head>
<body>

<script type="text/javascript">

	function onBridgeReady(){
	   WeixinJSBridge.invoke(
	       'getBrandWCPayRequest', {
	    	   "appId" : "<%=request.getAttribute("appid") %>", //公众号名称，由商户传入
				"timeStamp" : "<%=request.getAttribute("timeStamp") %>", //时间戳
				"nonceStr" : "<%=request.getAttribute("nonceStr") %>", //随机串
				"package" : "<%=request.getAttribute("package") %>", ////扩展包
				"signType" : "MD5", //微信签名方式:1.sha1
				"paySign" : "<%=request.getAttribute("paySign") %>" ////微信签名
	       },
	       function(res){     
	           if(res.err_msg == "get_brand_wcpay_request：ok" ) {
	        	   alert('支付成功1');
	           }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
	           
	           if(res.err_msg == "chooseWXPay:ok" ) {
	        	   alert('支付成功2');
	           }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
	       }
	   ); 
	}
	if (typeof WeixinJSBridge == "undefined"){
	   if( document.addEventListener ){
	       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
	   }else if (document.attachEvent){
	       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
	       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
	   }
	}else{
	   onBridgeReady();
	}
	
 </script>

	<div class="page home">
				
		
		<jsp:include page="foot.jsp" flush="true"/>
		
		
		
		<ol class="regV">
			<div>
				<div class="hd" style="height: 2.5rem;">
					<div class="i1"></div>
					<div class="i2">您需要支付<%=request.getParameter("amount") %>元</div>
				</div>
				<div class="bd" style="padding: 1rem 0;height: 3rem;">
					<a class="i7">查看支付结果</a>
					<a class="i8" style="position: relative;" href="Javascript:history.back();">取消支付</a>
				</div>
			</div>
		</ol>
		
		
		
		
		
	</div>
	
	
</body>
</html>