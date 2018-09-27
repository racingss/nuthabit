<%@page import="com.babycard.wx.*"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Collection bookColl = (Collection)request.getAttribute("bookColl");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>精品卡片书_卡片点点Cardpopo</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<style type="text/css">
.everycard{
	width:96%;
	margin:2%
}
.cardhead{
	width:25%;
	float:left;
	text-align: center;
    color: grey;
}
.cardimg,.picimg{
	width:100%;
	border-radius: 0.2rem;
	box-shadow: 0px 0.4rem 0.5rem rgba(0, 0, 0, 0.1);
}
.carddetail{
	width:75%;
	float:left;
}
.pichead, .picselect,picword{
	width:100%;
}
.picdetail{
	width: 21%;
    float: left;
    margin: 1% 0 1% 4%;
}
.picword{
	text-align: center;
    height: 1rem;
    color:grey;
}
.bookcover{
	display:block;
	width:100%;
}
</style>
</head>
<body>
	<div class="page home">

		<div>
			<%
			Iterator it =bookColl.iterator();
			while(it.hasNext()){
				Book b = (Book)it.next();
				if(b.getCover()==null)
					continue;
				%>
				<a href="/diandian/book.html?bookId=<%=b.getBookId() %>"  class="bookcover">
					<img alt="" src="/<%=b.getCover()%>" style="width:100%;border:0;margin:0">
				</a>
				<%
			}
			%>
			<div>
			
			</div>
			
			
			<a href="" style="display:none" id="searchhidden"></a>
			
			
		
		
		</div>

		

		<jsp:include page="foot.jsp" flush="true" />
		
		<jsp:include page="window.jsp" flush="true"/>


	</div>




<%
String title= "卡片点点精品书_亲子教育好帮手_卡片点点为您精心准备";
String detail="卡片点点—儿童语言启蒙教育平台，支持中英双语音，法德日韩俄等全球主流26种以上的语言";
String cardImg = "http://www.suyufuwu.com/diandian/bookimg/paidui/1.jpg";

String url = "http://www.suyufuwu.com/diandian/booklist.html"; 
Map<String, String> ret = new AccessToken().webSign(url,request, response);
String appId = KehuUtil.appId;
String timestamp = ret.get("timestamp");
String nonceStr = ret.get("nonceStr");
String signature = ret.get("signature");

if(request.getSession().getAttribute("subscribe")!=null && request.getSession().getAttribute("subscribe").toString().equals("0")){
	request.getSession().removeAttribute("subscribe");
	%>
	<script type="text/javascript">
	$(function(){
		setTimeout(function(){
			//$(".itemimg").attr("src","/diandian/frame/erweima_gzz.jpg");
			//$(".spanline1").text("请先关注我们的公众号");
			//$(".spanline2").text("谢谢");
			location.href="http://www.suyufuwu.com/diandian/weiguanzhu.jsp";
			return;
		},10000);
	});
	</script>
	<%	
}


%>
<script typet="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script>
$(function(){
			wx.config({
                debug: false,////生产环境需要关闭debug模式
                appId: '<%=appId %>',
                timestamp: <%=timestamp %>,
                nonceStr: '<%=nonceStr%>',
                signature: '<%=signature%>',
                jsApiList: [//需要调用的JS接口列表
                    'checkJsApi',//判断当前客户端版本是否支持指定JS接口
                    'onMenuShareTimeline',//分享给好友
                    'onMenuShareAppMessage'//分享到朋友圈
                ]
            });   
});

wx.ready(function () {
    var link = window.location.href;
    var protocol = window.location.protocol;
    var host = window.location.host;
    //分享朋友圈
    wx.onMenuShareTimeline({
        title: '<%=title%>',
        link: link,
        imgUrl: '<%=cardImg%>',// 自定义图标
        trigger: function (res) {
            // 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回.
            //alert('click shared');
        },
        success: function (res) {
            //alert('shared success');
            //some thing you should do
        },
        cancel: function (res) {
            //alert('shared cancle');
        },
        fail: function (res) {
            alert(JSON.stringify(res));
        }
    });
    //分享给好友
    wx.onMenuShareAppMessage({
        title: '<%=title%>', // 分享标题
        desc: '<%=detail%>', // 分享描述
        link: link, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
        imgUrl: '<%=cardImg%>', // 自定义图标
        type: 'link', // 分享类型,music、video或link，不填默认为link
        dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
        success: function () {
            // 用户确认分享后执行的回调函数
        },
        cancel: function () {
            // 用户取消分享后执行的回调函数
        }
    });
    wx.error(function (res) {
        alert(res.errMsg);
    });
});
</script>
</body>
</html>