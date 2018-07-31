<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
long age=0;
if(request.getParameter("age")!=null)
	age = Long.parseLong(request.getParameter("age"));
long tagId=0;
if(request.getParameter("tagId")!=null)
	tagId = Long.parseLong(request.getParameter("tagId"));
String qString=request.getAttribute("qString").toString();

long pop=0;
if(request.getParameter("pop")!=null)
pop=1;

long order=0;
if(request.getParameter("order")!=null)
order=1;

long newFlag=0;
if(request.getParameter("new")!=null)
newFlag=1;

long languageId = new LanguageHttp().getLanguageId(request);
Kehu k = new KehuUtil().getKehu(request, response);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title><%=Menu.getTitle(languageId) %></title>
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#saixuan").click(function(){
				if($(this).hasClass("active")){
					$(this).removeClass("active");
					$(".filterPopup").hide();
				}else {
					$(this).addClass("active");
					$(".filterPopup").show();
				}
			})
			$("#guanbi").click(function(){
				$(".filterPopup").hide();
			})

			$(".filterPopup label a").click(function(){
		        var i = $(this).children("span").text();
		        if($(this).hasClass("active")){

		        }else {
		            $(this).parent().children("a").removeClass("active");
		            $(this).addClass("active");
		            $(this).parent().children("input").attr("value",i);
		        }
		    })
		    
		    var age=<%=age%>;
		    var pop=<%=pop%>;
		    var order=<%=order%>;
		    var newFlag=<%=newFlag%>;
		    var tagId=<%=tagId%>;
		    var qString = '<%=qString%>';
		    var page=1;
		    $(".morecard").click(function(){
		    	page=page+1;
				if(age!=0){
					uslstring ='search.html?age=<%=age%>&page='+page; 
				}else if(tagId!=0){
					uslstring ='search.html?tagId=<%=tagId%>&page='+page;
				}else if(pop!=0){
					uslstring ='search.html?pop=<%=pop%>&page='+page;
				}else if(order!=0){
					uslstring ='search.html?order=<%=order%>&page='+page;
				}else if(newFlag!=0){
					uslstring ='search.html?new=<%=newFlag%>&page='+page;
				}else{
					uslstring ='search.html?qString=<%=qString%>&page='+page;
				}
				
				$.ajax({
					url: uslstring,
					dateType:'json',
				    success: function(data){
				    	if(data==0){
				    		alert("<%=Menu.getMenu("no_more", languageId) %>");
				    	}else{
				    		$("#searchhidden").before(data);	
				    	}
				    		
				    	
				    }
		   	   });
			
			})
			
			$(".cardsub").click(function(){
				src=$(this).attr("src");
				cardId=$(this).attr("cardId");
				$(".regVimg").attr("src",src);
				$(".regVhref").attr("href","/card/cardlist.html?cardId="+cardId);
				$(".regV").show();
			})
			
			$(".regV .i7,.regV .i8").click(function(){
				$(".regV").hide();
			})
			
			$("#wordFlag").click(function(){
				var wordFlag=0;
				if($(this).is(':checked')){
					wordFlag=0;
				}else{
					wordFlag=1;
				}
				$.ajax({
					url: '/card/cardlist.html?wordFlag='+wordFlag,
					dateType:'json',
				    success: function(data){
				    }
			   });
				
			})
		    

		})
	</script>
	<style type="text/css">
	.i1 a span{
		color:#000;
	}
	.a{
		color:#000;
	}
	</style>
</head>
<body>
	<div class="page searchResults">
		<nav class="srNav">
			<a class="i1" href="/diandian/">&nbsp;&nbsp;</a>
			<span><%=request.getAttribute("qString") %></span>
			<a class="i2" id="saixuan"></a>
		</nav>
		<div>
			<div class="box">
				<!--               搜索结果                -->
				<%
				if(true){
					Iterator it = ((Collection)request.getAttribute("searchResult")).iterator();
					boolean result=false;
					while(it.hasNext()){
						result=true;
						Card c = (Card)it.next();
						%>
						<a href="#" class="cardsub" src="<%=c.getImg()%>" cardId="<%=c.getCardId()%>">
							<img src="<%=c.getImg()%>">
							<i class="i<%=c.getAge()%><%=c.getAge()+1%>"><%=c.getAge()%>~<%=c.getAge()+1%><%=Menu.getMenu("sui", languageId) %></i>
						</a>		
						<%
					}
					if(!result){
						%>
						<span style="margin: 3em 1em 0 1em;font-size: 2em;color: #fb5959;">
							<%=Menu.getMenu("notfound", languageId) %>
						</span>
						<span style="width: 100%;margin:1em;text-align: center;font-size: 1.2em;color: #fb5959;">
							<%=Menu.getMenu("try_another", languageId) %>
						</span>
						<p style="text-align:center">
							<img alt="" src="frame/cloud-computing.png">
						</p>
						<%
					}
					
				}
				%>	
				
				<a href="" style="display:none" id="searchhidden"></a>
				
				
			</div>
			<div class="more">
				<a href="#miao" class="morecard" id="miao"><%=Menu.getMenu("more", languageId) %></a>
			</div>
		</div>
		<ul class="filterPopup">
			<div>
				<div class="hd">
					<span><%=Menu.getMenu("search_order", languageId) %></span>
					<a id="guanbi"></a>
				</div>
				<label class="i1">
					<a  <%if(request.getParameter("pop")!=null) {%>class="active"<%} %> href="search.html?pop=1">
						<span><%=Menu.getMenu("search_pop", languageId) %></span>
						<i></i>						
					</a>
					<a <%if(request.getParameter("new")!=null) {%>class="active"<%} %> href="search.html?new=1">
						<span><%=Menu.getMenu("search_new", languageId) %></span>
						<i></i>						
					</a>
					<a <%if(request.getParameter("order")!=null) {%>class="active"<%} %> href="search.html?order=1">
						<span>A~Z</span>
						<i></i>						
					</a>
					<input type="text" name="">
				</label>
				<label class="i2">
					<a href="search.html?age=1" <%if(request.getParameter("subAge")!=null && request.getParameter("subAge").equals("1")){ out.print("class='active'");} %>>
						<span>1-2<%=Menu.getMenu("sui", languageId) %></span>
						<i></i>	
					</a>
					<a href="search.html?age=2" <%if(request.getParameter("subAge")!=null && request.getParameter("subAge").equals("2")){ out.print("class='active'");} %>>
						<span>2-3<%=Menu.getMenu("sui", languageId) %></span>
						<i></i>	
					</a>
					<a href="search.html?age=3" <%if(request.getParameter("subAge")!=null && request.getParameter("subAge").equals("3")){ out.print("class='active'");} %>>
						<span>3-4<%=Menu.getMenu("sui", languageId) %></span>
						<i></i>	
					</a>
					<input type="text" name="">
				</label>
				<a href=""><%=Menu.getMenu("search_ok", languageId) %></a>
			</div>
		</ul>
		
		
		
		
		
		<ol class="regV cardwindow" style="display:none">
			<div>
				<div class="hd">
					<div class="i1"></div>
					<div class="i2">
					<%
					KehuCardMember m = new KehuDAO().getMember(k.getKehuId());
					if (m == null || m.getCloseDate().getTime() < System.currentTimeMillis()) {
						%><%=Menu.getMenu("you_are_not_member", languageId) %><%
					}else{
						%><%=Menu.getMenu("you_are_member", languageId) %><%
					}
					%></div>
					<div class="i3"><%=Menu.getMenu("you_curr_have", languageId) %><span><%=new KehuDAO().getJifen(k.getId()) %></span><i></i></div>
				</div>
				<div class="bd">
					<div style="text-align:center;">
						<img class="regVimg" src="/myplan/upload/historypic/1527556550466.jpg" style="height: 3.5rem;margin: 0.3rem;border-radius: 20px;">
					</div>
					<div style="text-align: center;font-size: 0.45rem;padding-bottom: 0.1rem;vertical-align: middle;">
						<input type="checkbox" style="zoom: 1.5;" <%if(request.getSession().getAttribute("wordFlag")==null ||request.getSession().getAttribute("wordFlag").toString().equals("0")){%>checked="checked"<%} %> class="wordFlag"/>
						<%=Menu.getMenu("edit", languageId) %>
						<input type="checkbox" style="zoom: 1.5;" <%if(request.getSession().getAttribute("soundFlag")==null ||request.getSession().getAttribute("soundFlag").toString().equals("0")){%>checked="checked"<%} %> class="soundFlag"/>
					    <%=Menu.getMenu("sound", languageId) %>
					</div>
					<a class="i7 regVhref" href="#" >
					<%
					if (m == null || m.getCloseDate().getTime() < System.currentTimeMillis()) {
						%><%=Menu.getMenu("use1point", languageId) %><%
					}else{
						%><%=Menu.getMenu("free_read", languageId) %><%
					}
					%>
					</a>
					<div style="text-align: center;margin: 0.5rem;">
						<a href="subscribe.html"><%=Menu.getMenu("orderuser", languageId) %></a><%=Menu.getMenu("freeread", languageId) %></div>
					<a class="i8"><%=Menu.getMenu("giveup", languageId) %></a>
				</div>
			</div>
		</ol>
	</div>
</body>
</html>