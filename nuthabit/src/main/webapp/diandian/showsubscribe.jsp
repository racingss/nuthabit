<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
long languageId = new LanguageHttp().getLanguageId(request);
Kehu k = new KehuUtil().getKehu(request, response);
Collection coll =(Collection)request.getAttribute("fromList");
%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>我的推荐</title>
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript">
		$(function(){
			
		})
	</script>
</head>
<body>
	<div class="page parents">
		<div>
			<img class="bg" src="frame/parents-11.png">
			<div style="background: white;padding: .5rem;margin: 1rem;border-radius: 0.2rem;position: relative;z-index: 2;">
				<h1 style="font-size: .45rem;text-align: center;">我的推荐</h1>
				<%
				Iterator it = coll.iterator();
				int i = coll.size();
				while(it.hasNext()){
					Kehu fk = (Kehu)it.next();
					%>
					<div class="fromlist">
						<div class="listhead">
							<img src="<%=fk.getHeadimgurl()%>" style="border-radius: 2rem;">
						</div>
						<div class="listname">
							<%=i--%>、<%=fk.getNickname() %>
						</div>
						<div class="listdate">
							<%=fk.getZhuceri() %>
						</div>
					</div>	
					<%
				}
				%>
				<style>
				.fromlist div{
					display:block;
				}
				.fromlist{
					text-align: center;
   					display: block;
    				width: 100%;
    				padding: 1rem;
				}
				</style>
				
				
					
					
				</p>
			</div>
			
		</div>
		
		
		
		<jsp:include page="foot.jsp" flush="true"/>
		
		
	</div>
</body>
</html>