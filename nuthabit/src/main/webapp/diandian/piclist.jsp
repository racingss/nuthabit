<%@page import="java.net.URLDecoder"%>
<%@ page language="java"
	import="com.babycard.dao.*,com.babycard.util.*,java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
long languageId = new LanguageHttp().getLanguageId(request);

Iterator resultIt = ((Collection)request.getAttribute("result")).iterator();
String title="";
if (request.getParameter("tagId") != null && request.getParameter("tagId").length()>0) {
	CardTag t = CardTag.getCartTagByTagId(Long.parseLong(request.getParameter("tagId")));
	title= Menu.getMenu("tag_" + t.getTagId(), languageId);
}else if (request.getParameter("qString") != null && request.getParameter("qString").length() > 0){ 
	title=request.getParameter("qString") ;
}else if (request.getParameter("pop") != null && request.getParameter("pop").length() > 0){ 
	title="最受欢迎" ;
}else if (request.getParameter("new") != null && request.getParameter("new").length() > 0){ 
	title="最新上架" ;
}else{
	title = "level "+request.getParameter("level");
}

long level=0;
if(request.getParameter("level")!=null)
	level = Long.parseLong(request.getParameter("level"));
long tagId=0;
if(request.getParameter("tagId")!=null)
	tagId = Long.parseLong(request.getParameter("tagId"));
String qString=null;
if(request.getParameter("qString")!=null);
qString = request.getParameter("qString");

long pop=0;
if(request.getParameter("pop")!=null)
pop=1;

long order=0;
if(request.getParameter("order")!=null)
order=1;

long newFlag=0;
if(request.getParameter("new")!=null)
newFlag=1;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title><%=title %>_卡片点点Cardpopo</title>
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
</style>
</head>
<body>
	<div class="page home">

		<div>
			<%
			while(resultIt.hasNext()){
				Card c = (Card)resultIt.next();
				%>
				<div class="everycard">
					<div class="cardhead">
						<img alt="" src="<%=c.getImg() %>" class="cardimg cardsub" cardId="<%=c.getCardId()%>">
						<br/>
						<%=c.getMeaning(languageId, c.getCardId()) %>
					</div>
					<div class="carddetail">
						<%
						Collection picColl = new CardPicDAO().getCardPicByCardId(c.getCardId());
						Iterator picIt = picColl.iterator();
						while(picIt.hasNext()){
							CardPic cp = (CardPic)picIt.next();
							%>
							<div class="picdetail">
								<div class="pichead">
									<img alt="" src="<%=cp.getImgurl() %>" class="picimg  cardsub" cardId="<%=c.getCardId()%>">
								</div>
								<div class="picword"><%=CardMeaning.getStaticCard(cp.getPicId(), languageId).getMeaning() %></div>
								<div class="picselect"></div>
							</div>
							<%
						}
						%>
					</div>
				</div>
				<%
			}
			%>
			
			<a href="" style="display:none" id="searchhidden"></a>
			
			<div class="everycard morecard" style="text-align: center;
    ont-size: 0.4rem;
    color: #332e2e;
    background: #d3fcf6;
    /* height: 5rem; */
    display: inline-block;
    float: left;
    opacity: 0.6;
    width: 60%;
    margin-left: 20%;
    padding: 0.3rem;">
				加载更多
			</div>
		
		
		</div>

		

		<jsp:include page="foot.jsp" flush="true" />
		
		<jsp:include page="window.jsp" flush="true"/>


	</div>




<script type="text/javascript">
		$(function(){
			
		    
		    var level=<%=level%>;
		    var pop=<%=pop%>;
		    var order=<%=order%>;
		    var newFlag=<%=newFlag%>;
		    var tagId=<%=tagId%>;
		    var qString = '<%=qString%>';
		    var page=1;
		    $(".morecard").click(function(){
		    	page=page+1;
				if(level!=0){
					uslstring ='piclist.html?level=<%=level%>&page='+page; 
				}else if(tagId!=0){
					uslstring ='piclist.html?tagId=<%=tagId%>&page='+page;
				}else if(pop!=0){
					uslstring ='piclist.html?pop=<%=pop%>&page='+page;
				}else if(order!=0){
					uslstring ='piclist.html?order=<%=order%>&page='+page;
				}else if(newFlag!=0){
					uslstring ='piclist.html?new=<%=newFlag%>&page='+page;
				}else{
					uslstring ='piclist.html?qString=<%=qString%>&page='+page;
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
			
			
		})
	</script>
</body>
</html>