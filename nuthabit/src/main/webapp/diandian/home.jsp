<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
long languageId = new LanguageHttp().getLanguageId(request);
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
	    background-size: .6rem .6rem;
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
			<a class="i2" id="sousuo"><img src="frame/home-1.png"></a>
			<a class="i1" id="yuyan"><img src="frame/home-2.png"></a>
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
			<div >
            	<a href="/card/cardlist.html?cardId=2189">
					<img src="frame/animal.gif" style="width:100%;">
				</a>
            </div>
			<!--div class="banner">
			    <div class="swiper-container">
			        <div class="swiper-wrapper">
			            <div class="swiper-slide">
			            	<a href="/card/cardlist.html?cardId=2189">
								<img src="frame/animal.gif">
							</a>
			            </div>
			            <div class="swiper-slide">
			            	<img src="frame/home-17.jpg">
			            </div>
			        </div>
			        <div class="swiper-pagination"></div>
			    </div>
			</div-->
			
			<div class="category">
				<div>
					<%
					if(true){
						Iterator tagIt = ((Collection)request.getAttribute("tagColl")).iterator();
						while(tagIt.hasNext()){
							CardTag ct = (CardTag)tagIt.next();
							%>
							<a href="search.html?tagId=<%=ct.getTagId()%>">
								<i><img src="<%=ct.getHeadpng()%>"></i>
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
					<a href="search.html?age=1">
						<span>1~2</span><%=Menu.getMenu("sui", languageId) %>
					</a>
					<a href="search.html?age=2">
						<span>2~3</span><%=Menu.getMenu("sui", languageId) %>
					</a>
					<a href="search.html?age=3">
						<span>3~4</span><%=Menu.getMenu("sui", languageId) %>
					</a>			
				</div>
			</div>
			
			
			
			
			<h4 class="h42"><%=Menu.getMenu("search_pop", languageId) %></h4>
			<div class="recently">
				<div>
					<!--              最受欢迎               -->
					<%
					if(true){
						Iterator recentIt = Card.getCardFavColl().iterator();
						int temp=0;
						while(recentIt.hasNext() && temp++<8){
							Card c = (Card)recentIt.next();
							%>
							<a href="#" class="cardsub" src="<%=c.getImg()%>" cardId="<%=c.getCardId()%>">
								<img src="<%=c.getImg()%>">
								<i class="i<%=c.getAge()%><%=c.getAge()+1%>"><%=c.getAge()%>~<%=c.getAge()+1%><%=Menu.getMenu("sui", languageId) %></i>
							</a>	
							<%
						}
					}
					%>
									
				</div>
			</div>
			
			
			
			
			
			
			<h4 class="h41"><%=Menu.getMenu("menu_new", languageId) %></h4>
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
								<i class="i<%=c.getAge()%><%=c.getAge()+1%>"><%=c.getAge()%>~<%=c.getAge()+1%><%=Menu.getMenu("sui", languageId) %></i>
							</a>	
							<%
						}
					}
					%>
									
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
								<i class="i<%=c.getAge()%><%=c.getAge()+1%>"><%=c.getAge()%>~<%=c.getAge()+1%><%=Menu.getMenu("sui", languageId) %></i>
							</a>	
							<%
						}
					}
					%>
									
				</div>
			</div>
			<%} %>
			
			
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
			<h4 class="h42"><%=Menu.getMenu("menu_fav", languageId) %></h4>
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
								<i class="i<%=c.getAge()%><%=c.getAge()+1%>"><%=c.getAge()%>~<%=c.getAge()+1%><%=Menu.getMenu("sui", languageId) %></i>
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
		<form action="search.html" method="post" id="search_form">
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
		<a class="review" href="/card/review.html"></a>
	</div>
</body>
</html>