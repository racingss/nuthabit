<%@page import="com.babycard.wx.*"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
long languageId = new LanguageHttp().getLanguageId(request);
String title="卡片点点_儿童语言启蒙教育平台_亲子教育好帮手";
String cardImg="http://www.suyufuwu.com/images/logo.jpg";
String detail="卡片点点—儿童语言启蒙教育平台，支持中英双语音，法德日韩俄等全球主流26种以上的语言";
Collection bookColl = (Collection)request.getAttribute("bookColl");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title><%=title%></title>
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

			/* 语言选择 */
			$("#yuyan").click(function(){
				if($(this).hasClass("active")){
					$(this).removeClass("active");
					$(this).next(".language").css("display","none");
				}else {
					$(this).addClass("active");
					$(this).next(".language").css("display","flex");
				}
			})
			$(".language a").click(function(){
				if($(this).hasClass("active")){
					//$(".language a").removeClass("active");
				}else {
					$(".language a").removeClass("active");
					$(this).addClass("active");
				}
			})
			
			
			$("#sousuo").click(function(){
				if($(this).hasClass("active")){
					$(this).removeClass("active");
					$(".homeSearch").hide();
				}else {
					$(this).addClass("active");
					$(".homeSearch").show();
				}
			})
			$("#quxiao").click(function(){
				$("#sousuo").removeClass("active");
				$(".homeSearch").hide();
			})
			$("#sousuokuang input").focus(function(){
				$(this).next("a").show();
				$("#sousuokuang a").click(function(){
					$("#sousuokuang input").val("");
					$(this).hide();
				})
			})

		})
	</script>
	<style type="text/css">
	.review{
		width: 1.2rem;
	    height: 1.2rem;
	    border-radius: 50%;
	    background: red;
	    position: fixed;
	    bottom: 1.7rem;
	    right: .4rem;
	    background: url(frame/eye.png);
	    background-size: 1rem 1rem;
	    background-position: center;
	    background-repeat: no-repeat;
	    background-color: #ffc600;
	    box-shadow: 0.15rem 0.15rem 0.2rem 0px rgba(255, 225, 0, 0.2);
	    display: block;
	}
	</style>
</head>
<body>
	<div class="page home">
		<nav>
			<span><%=Menu.getTitle(languageId) %></span>
			<a class="i2" id="sousuo" style="right: .4rem;"><img src="frame/home-1.png"></a>
			<!--a class="i1" id="yuyan"><img src="frame/home-2.png"></a-->
			<ul class="language">
			
			
				<%
				//所有语言
				if(true){
					  Iterator it = Language.languageColl.iterator();
					  while(it.hasNext()){
						    Language l = (Language)it.next();
					  
							%>
							<a href="index.html?languageId=<%=l.getLanguageId()%>"
								<%if(languageId==l.getLanguageId())out.print("class='active'"); %>
							><%=l.getLname() %></a>
						    <%
					  }
				}	  
				%>
			</ul>
		</nav>
		<div>
			<!--div style="position:relative;  
                    left:0px;  
                    top:0px;  
                    margin:0;  
                    padding:0;  ">
            	<a href="/card/cardlist.html?cardId=2189">
					<img src="frame/animal_bak.jpg" style="width:100%;">
					<img src="frame/mv1.gif" style="width: 20%;position: absolute;top: 31%;left: 53%;">
					<img src="frame/mv2.gif" style="width: 16%;position: absolute;top: 38%;left: 30%;">
					<img src="frame/nv3.gif" style="width: 16%;position: absolute;top: 5%;left: 80%;">
				</a>
            </div-->
			<div class="banner">
			    <div class="swiper-container">
			        <div class="swiper-wrapper">
			        	<%
						Iterator it =bookColl.iterator();
			        	int limiti=4;
						while(it.hasNext() && limiti-->0){
							Book b = (Book)it.next();
							if(b.getCover()==null)
								continue;
							%>
							<div class="swiper-slide">
				            	<a href="/diandian/booklist.html">
									<img src="/<%=b.getCover()%>">
								</a>
				            </div>
				        	<%
						}
						%>
			        	
			        </div>
			        <div class="swiper-pagination"></div>
			    </div>
			</div>
			
			<div class="category">
				<div>
					<%
					if(true){
						Iterator tagIt = ((Collection)request.getAttribute("tagColl")).iterator();
						int hi=1;
						while(tagIt.hasNext()){
							CardTag ct = (CardTag)tagIt.next();
							%>
							<a href="piclist.html?tagId=<%=ct.getTagId()%>">
								<i><img src="frame/h_<%=hi++%>.png"></i>
								<span><%=Menu.getMenu("tag_"+ct.getTagId(), languageId) %></span>
							</a>
							<%
						}
					}
					%>
								
				</div>
			</div>
			<div class="age">
				<div>
					<a href="piclist.html?level=1">
						<span>level 1</span>
					</a>
					<a href="piclist.html?level=2">
						<span>level 2</span>
					</a>
					<a href="piclist.html?level=3">
						<span>level 3</span>
					</a>
					<a href="piclist.html?level=4">
						<span>level 4</span>
					</a>			
				</div>
			</div>
			
			<h4 class="h42"><a href="piclist.html?new=t" style="color: #24b2c7;"><%=Menu.getMenu("menu_new", languageId) %></a></h4>
			<div class="recently">
				<div>
					<!--              最新上架               -->
					<%
					if(true){
						Iterator recentIt = ((Collection)request.getAttribute("newColl")).iterator();
						while(recentIt.hasNext()){
							Card c = (Card)recentIt.next();
							%>
							<a href="#" class="cardsub" src="<%=c.getImg()%>" cardId="<%=c.getCardId()%>">
								<img src="<%=c.getImg()%>">
								<i class="i<%=c.getAge()%><%=c.getAge()+1%>">level <%=c.getAge()%></i>
							</a>	
							<%
						}
					}
					%>
									
				</div>
			</div>
			
			
			<h4 class="h41"><a href="piclist.html?pop=t" style="color: #4baf6f;"><%=Menu.getMenu("search_pop", languageId) %></a></h4>
			<div class="recently">
				<div>
					<!--              最受欢迎               -->
					<%
					if(true){
						Iterator recentIt = ((Collection)request.getAttribute("popColl")).iterator();
						int temp=0;
						while(recentIt.hasNext() && temp++<8){
							Card c = (Card)recentIt.next();
							%>
							<a href="#" class="cardsub" src="<%=c.getImg()%>" cardId="<%=c.getCardId()%>">
								<img src="<%=c.getImg()%>">
								<i class="i<%=c.getAge()%><%=c.getAge()+1%>">level <%=c.getAge()%></i>
							</a>	
							<%
						}
					}
					%>
									
				</div>
			</div>
			
			
			
			
			<h4 class="h41"><%=Menu.getMenu("menu_my", languageId) %></h4>
			<div class="my">
				<div>
					<!--              我的卡片               -->
					<%
					if(true){
						Iterator myIt = ((Collection)request.getAttribute("myColl")).iterator();
						while(myIt.hasNext()){
							Card c = (Card)myIt.next();
							%>
							<a href="/card/cardlist.html?cardId=<%=c.getCardId()%>">
								<img src="<%=c.getImg()%>">
							</a>
							<%
						}
					}
					%>
					<a class="add" href="/card/create_own_card.html"></a>
				</div>
			</div>
			
			
			
			
			<%
			if(((Collection)request.getAttribute("myRecentColl")).size()>0){
			%>
			<h4 class="h42"><%=Menu.getMenu("menu_recent", languageId) %></h4>
			<div class="recently">
				<div>
					<!--              最近阅读               -->
					<%
					if(true){
						Iterator recentIt = ((Collection)request.getAttribute("myRecentColl")).iterator();
						while(recentIt.hasNext()){
							long cardId =Long.parseLong(recentIt.next().toString());
							Card c = Card.getStaticCard(cardId);
							if(c==null)
								continue;
							%>
							<a href="#" class="cardsub" src="<%=c.getImg()%>" cardId="<%=c.getCardId()%>">
								<img src="<%=c.getImg()%>">
								<i class="i<%=c.getAge()%><%=c.getAge()+1%>">level <%=c.getAge()%></i>
							</a>	
							<%
						}
					}
					%>
									
				</div>
			</div>
			<%} %>
			
			
			
			<h4 class="h41"><%=Menu.getMenu("menu_fav", languageId) %></h4>
			<div class="recently">
				<div>
					<!--              搜藏               -->
					<%
					if(true){
						Iterator recentIt = ((Collection)request.getAttribute("favColl")).iterator();
						while(recentIt.hasNext()){
							Card c = (Card)recentIt.next();
							%>
							<a href="#" class="cardsub" src="<%=c.getImg()%>" cardId="<%=c.getCardId()%>">
								<img src="<%=c.getImg()%>">
								<i class="i<%=c.getAge()%><%=c.getAge()+1%>">level <%=c.getAge()%></i>
							</a>	
							<%
						}
					}
					%>
									
				</div>
			</div>
			
		</div>
		
		
		
		
		
		<jsp:include page="foot.jsp" flush="true"/>
		
		
		
		
		<!--                   搜索                    -->
		<form action="piclist.html" method="post" id="search_form">
			<ul class="homeSearch">
				<div class="nav">
					<div id="sousuokuang">
							<i></i>
							<input type="text" name="qString" placeholder="<%=Menu.getMenu("searchinfo", languageId) %>" id="qStringinput">
							<a id="lf"></a>
					</div>
					<span id="quxiao"><%=Menu.getMenu("cancel", languageId) %></span>
				</div>
				<div class="history">
					<h4><%=Menu.getMenu("history", languageId) %></h4>
					<div>
						<%
						if(true){
							Collection searchHistoryColl =(Collection)request.getAttribute("searchHistory");
							Iterator searchIt = searchHistoryColl.iterator();
							while(searchIt.hasNext()){
								Search search = (Search)searchIt.next();
								%>
								<a href="#" class="searchhistory"><%=search.getqString()%></a>
								<%
							}
						}
						%>
						<script type="text/javascript">
						$(function(){
							$(".searchhistory").click(function(){
								var form = document.getElementById('search_form');
								document.getElementById('qStringinput').value=$(this).text();
								form.submit();
							})
						})
						</script>
					</div>
				</div>
				<div class="empty">
					<a href=""><%=Menu.getMenu("cleanhistory", languageId) %></a>
				</div>
			</ul>
		</form>
		
		
		
		<jsp:include page="window.jsp" flush="true"/>
		
		
		
	</div>
	
	<!-- 复习按钮 -->
	<div>
		<!--a class="review" href="/card/review.html"></a-->
		<a class="review" href="setup.html"></a>
	</div>
	
<%


String url = "http://www.suyufuwu.com/diandian/"; 
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
			location.href="/diandian/weiguanzhu.jsp";	
			return;
		},5000);
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
	
<jsp:include page="tracking.jsp" flush="true"/>	
</body>
</html>