<%@page import="com.babycard.wx.*"%>
<%@ page language="java" import="com.babycard.dao.*,java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Dingdan dingdan = (Dingdan)request.getAttribute("dingdan");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title><%if(dingdan.getStatus()==dingdan.STATUS_DAIZHIFU) {%>请支付<%}
else if(dingdan.getStatus()==dingdan.STATUS_YIZHIFU) {%>订单已支付支付<%}
%></title>
</head>
<body>
<!--header-->
<header>
 <a href="index.html" class="iconfont backIcon">&#60;</a>
 <h1><%if(dingdan.getStatus()==dingdan.STATUS_DAIZHIFU) {%>请支付<%}
else if(dingdan.getStatus()==dingdan.STATUS_YIZHIFU) {%>订单已支付支付<%}
%></h1>
</header>
<div style="height:1rem;"></div>
<section class="return_state">
 <!--订单状态图标：0为成功；1为失败-->
 <h2 class="state_0" style="color:#F60"><%if(dingdan.getStatus()==dingdan.STATUS_DAIZHIFU) {%>为了保证按时发货，请您尽快完成支付！<%}
else if(dingdan.getStatus()==dingdan.STATUS_YIZHIFU) {%>订单已支付成功，我们会尽快发货！<%}
%></h2>
 <p>订单号码：<%=dingdan.getDingdanId() %></p>
 <p>订单状态：<%=dingdan.getStatusJieshi() %></p>
 <p>订单金额：<strong style="color:#00a1e9"><%=dingdan.getZhifujine() %></strong></p>
 <p>订单时间：<time><%=dingdan.getRiqi() %></time></p>
</section>
<section class="order_msg">
 <h2></h2>
 <dd style="text-align: center;">
 <%if(dingdan.getStatus()==dingdan.STATUS_DAIZHIFU) {%>
 	<a href="chongxinzhifu.html?id=<%=dingdan.getId() %>" class="chooseWXPay"><img alt="" src="/images/weixin.jpg">
 	<br/><br/>
    点击使用微信支付<br/></a>
<%} %>    
    
 </dd>
</section>


<%
if(request.getAttribute("wx")!=null){
%>
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
	        	   alert('支付成功');
	           }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
	           alert(res);
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
 <script language="JavaScript">
	function myrefresh(){
		alert(1);
		$.ajax({
			url: '/diandian/order.html?type=query&dingdanId=<%=dingdan.getDingdanId()%>',
			dateType:'json',
		    success: function(data){
		    	alert(data);
		    }
	    });
		setTimeout('myrefresh()',3000);
	}
	setTimeout('myrefresh()',3000); //指定1秒刷新一次
</script>
 
<%}%>
<%@ include file="foot.jsp" %>

<!--bottom nav-->
<div style="height:1rem;"></div>
<aside class="btmNav">
 <a href="javascript:showNav();"  style="background:#00a1e9;color:white;text-shadow:none;">网站导航</a>
 <a href="dingdanliebiao.html" style="background:#00a1e9;color:white;text-shadow:none;">返回订单列表</a>
 <%if(dingdan.getStatus()==dingdan.STATUS_DAIZHIFU) {%>
 <a href="chongxinzhifu.html?id=<%=dingdan.getId() %>" style="background:#F60;color:white;text-shadow:none;" class="chooseWXPay">立即支付</a>
 <a href="chongxinzhifu.html?id=<%=dingdan.getId() %>" style="background:#00a1e9;color:white;text-shadow:none;" id="shuaxinbut">刷新支付结果</a>
 <%}%>
 
</aside>


</body>
</html>