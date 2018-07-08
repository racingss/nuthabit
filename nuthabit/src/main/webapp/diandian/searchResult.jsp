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
long languageId = new LanguageHttp().getLanguageId(request);
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
		    var tagId=<%=tagId%>;
		    var qString = '<%=qString%>';
		    var page=1;
		    $(".morecard").click(function(){
		    	page=page+1;
				if(age!=0){
					uslstring ='search.html?age=<%=age%>&page='+page; 
				}else if(tagId!=0){
					uslstring ='search.html?tagId=<%=tagId%>&page='+page;
				}else{
					uslstring ='search.html?qString=<%=qString%>&page='+page;
				}
				
				$.ajax({
					url: uslstring,
					dateType:'json',
				    success: function(data){
				    	if(data==0){
				    		alert("没有更多啦");
				    	}else{
				    		$("#searchhidden").before(data);	
				    	}
				    		
				    	
				    }
		   	   });
			
			})
		    

		})
	</script>
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
						<a href="/card/cardlist.html?cardId=<%=c.getCardId()%>">
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
					<a>
						<span><%=Menu.getMenu("search_pop", languageId) %></span>
						<i></i>						
					</a>
					<a class="active">
						<span><%=Menu.getMenu("search_new", languageId) %></span>
						<i></i>						
					</a>
					<a>
						<span>A~Z</span>
						<i></i>						
					</a>
					<input type="text" name="">
				</label>
				<label class="i2">
					<a href="search.html?subAge=1" <%if(request.getParameter("subAge")!=null && request.getParameter("subAge").equals("1")){ out.print("class='active'");} %>>
						<span>1-2<%=Menu.getMenu("sui", languageId) %></span>
						<i></i>	
					</a>
					<a href="search.html?subAge=2" <%if(request.getParameter("subAge")!=null && request.getParameter("subAge").equals("2")){ out.print("class='active'");} %>>
						<span>2-3<%=Menu.getMenu("sui", languageId) %></span>
						<i></i>	
					</a>
					<a href="search.html?subAge=3" <%if(request.getParameter("subAge")!=null && request.getParameter("subAge").equals("3")){ out.print("class='active'");} %>>
						<span>3-4<%=Menu.getMenu("sui", languageId) %></span>
						<i></i>	
					</a>
					<a href="search.html?subAge=4" <%if(request.getParameter("subAge")!=null && request.getParameter("subAge").equals("4")){ out.print("class='active'");} %>>
						<span>4-5<%=Menu.getMenu("sui", languageId) %></span>
						<i></i>	
					</a>
					<a href="search.html?subAge=5" <%if(request.getParameter("subAge")!=null && request.getParameter("subAge").equals("5")){ out.print("class='active'");} %>>
						<span>4-5<%=Menu.getMenu("sui", languageId) %></span>
						<i></i>	
					</a>
					<input type="text" name="">
				</label>
				<a href=""><%=Menu.getMenu("search_ok", languageId) %></a>
			</div>
		</ul>
	</div>
</body>
</html>