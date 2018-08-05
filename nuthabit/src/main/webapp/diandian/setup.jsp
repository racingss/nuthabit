<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
long languageId = new LanguageHttp().getLanguageId(request);
long languageId_2 = new LanguageHttp().getLanguageId_2(request);
Kehu k = new KehuUtil().getKehu(request, response);
%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>阅读设置</title>
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript">
		$(function(){
			$(".regV .i5 a").click(function(){
				if($(this).hasClass("active")){

				}else {
					$(".regV .i5 a").removeClass("active");
					$(this).addClass("active")
				}
			})
			$(".regV .i7,.regV .i8").click(function(){
				location.href='setup.html<%if(request.getParameter("cardId")!=null)out.print("?cardId="+request.getParameter("cardId"));%>';
			})
			
			$("#wordFlag").change(function(){
				var wordFlag=$(this).val();
				$.ajax({
					url: '/card/cardlist.html?wordFlag='+wordFlag,
					dateType:'json',
				    success: function(data){
				    }
			    });
			})
			
			$("#soundFlag").change(function(){
				var soundFlag=$(this).val();
				$.ajax({
					url: '/card/cardlist.html?soundFlag='+soundFlag,
					dateType:'json',
				    success: function(data){
				    }
			   });
			})
			
			$("#secondFlag").change(function(){
				var secondFlag=$(this).val();
				$.ajax({
					url: '/card/cardlist.html?secondFlag='+secondFlag,
					dateType:'json',
				    success: function(data){
				    }
			   });
			})
			
			$("#languageFlag").change(function(){
				var languageId=$(this).val();
				$.ajax({
					url: '/card/language.html?languageId='+languageId,
					dateType:'json',
				    success: function(data){
				    }
			   });
			})
			
			$("#language2Flag").change(function(){
				var languageId_2=$(this).val();
				$.ajax({
					url: '/card/language.html?languageId_2='+languageId_2,
					dateType:'json',
				    success: function(data){
				    }
			   });
			})
			
			$("#subi7").click(function(){
			   $(".regV").hide();
			   $(".setupnextwindow").show();
			})
			
			$(".wenzi").click(function(){
			   $(".wenziV").show();
			})
			
			$(".yuyin").click(function(){
			   $(".yuyinV").show();
			})
			
			$(".xianshi").click(function(){
			   $(".xianshiV").show();
			})
			
			$(".yuyan1").click(function(){
			   $(".yuyan1V").show();
			})
			$(".yuyan2").click(function(){
			   $(".yuyan2V").show();
			})
		})
	</script>
</head>
<body>
	<div class="page parents">
		<div>
			<img class="bg" src="frame/parents-11.png">
			<div class="list" style="z-index:  1;position:  relative;margin-top: 1rem;">
				<a href="#" class="wenzi">
					<span>文字</span>
					<p class="wenzip">
						<%if(request.getSession().getAttribute("wordFlag")==null ||request.getSession().getAttribute("wordFlag").toString().equals("0")){%>								
							显示
						<%}else{ %>
							不显示
						<%} %>
					</p>
				</a>
				<a href="#" class="yuyin">
					<span>语音</span>
					<p class="yuyinp">
						<%if(request.getSession().getAttribute("soundFlag")==null ||request.getSession().getAttribute("soundFlag").toString().equals("0")){%>								
							播放
						<%}else{ %>
							静音
						<%} %>	
					</p>
				</a>
				<a href="#" class="xianshi">
					<span>显示</span>
					<p class="xianship">
						<%if(request.getSession().getAttribute("secondFlag")==null ||request.getSession().getAttribute("secondFlag").toString().equals("0")){%>								
							双语
						<%}else{ %>
							单一语言
						<%} %>	
					</p>
				</a>
			</div>
			
			<div class="list" style="z-index:  1;position:  relative;margin-top: 1rem;">
				<a href="#" class="yuyan1">
					<span>语言一</span>
					<p class="yuyan1p"><%=Language.getLanguageByid(languageId).getLname() %></p>
				</a>
				<a href="#" class="yuyan2">
					<span>语言二</span>
					<p class="yuyan2p">
						<%if(languageId_2==-1){
						 	%>  		
							<option selected>未设置</option>
							<%
						 }else{
							 	%>  		
								<%=Language.getLanguageByid(languageId_2).getLname() %>
								<%
						 }
						%>
					</p>
				</a>
			</div>
			
			<%
			if(request.getParameter("cardId")!=null){
				Card c = Card.getStaticCard(Long.parseLong(request.getParameter("cardId")));
			%>
			<div style="z-index:  1;position:  relative;margin-top: 1rem;text-align:center;width:100%;">
				<div style="padding-bottom: 0.2rem;width:20%;margin-left:40%;background: white;border-radius: 0.2rem;">
						<a href="/card/cardlist.html?cardId=<%=c.getCardId() %>&replay=<%=System.currentTimeMillis() %>" class="cardsub" src="<%=c.getImg()%>" cardId="<%=c.getCardId()%>">
							<img src="<%=c.getImg()%>" style="width:100%;border-top-left-radius:0.2rem;border-top-right-radius:0.2rem">
							<br>
							<span style="font-size:0.5rem;color:black;">返回</span>
						</a>
				</div>		
			</div>
			<%} %>
			
			
		</div>
		
		
		
		<jsp:include page="foot.jsp" flush="true"/>
		
		
		
		
		
		
		<ol class="regV wenziV" style="display:none">
			<div>
				<div class="hd" style="height: 2.5rem;">
					<div class="i1"></div>
					<div class="i2">阅读设置</div>
				</div>
				<div class="bd" style="height: 3.5rem">
					<div class="i6">
						<label>
							<div class="setupdiv">
								<span >文字：</span>
								<select id="wordFlag" style="font-size: 0.4rem;width: 3rem;margin-left: 0.1rem;">
                                    <%if(request.getSession().getAttribute("wordFlag")==null ||request.getSession().getAttribute("wordFlag").toString().equals("0")){%>								
										<option value="0" selected >显示</option>
										<option value="1">不显示</option>
									<%}else{ %>
										<option value="1" selected >不显示</option>
										<option value="0">显示</option>
									<%} %>	
								</select>
							</div>
						</label>
					</div>
					<a class="i7" id="subi7">确定</a>
				</div>
			</div>
		</ol>
		
		<ol class="regV yuyinV" style="display:none">
			<div>
				<div class="hd" style="height: 2.5rem;">
					<div class="i1"></div>
					<div class="i2">阅读设置</div>
				</div>
				<div class="bd" style="height: 3.5rem">
					<div class="i6">
						<label>
							<div class="setupdiv">
								<span >语音：</span>
								<select id="soundFlag" style="font-size: 0.4rem;width: 3rem;margin-left: 0.1rem;">
									<%if(request.getSession().getAttribute("soundFlag")==null ||request.getSession().getAttribute("soundFlag").toString().equals("0")){%>								
										<option value="0" selected >播放</option>
										<option value="1">静音</option>
									<%}else{ %>
										<option value="1" selected >静音</option>
										<option value="0">播放</option>
									<%} %>	
								</select>
							</div>
						</label>
					</div>
					<a class="i7" id="subi7">确定</a>
				</div>
			</div>
		</ol>
		
		<ol class="regV xianshiV" style="display:none">
			<div>
				<div class="hd" style="height: 2.5rem;">
					<div class="i1"></div>
					<div class="i2">阅读设置</div>
				</div>
				<div class="bd" style="height: 3.5rem">
					<div class="i6">
						<label>
							<div class="setupdiv">
								<span >显示：</span>
								<select id="secondFlag" style="font-size: 0.4rem;width: 3rem;margin-left: 0.1rem;">
									<%if(request.getSession().getAttribute("secondFlag")==null ||request.getSession().getAttribute("secondFlag").toString().equals("0")){%>								
										<option value="0" selected >双语</option>
										<option value="1" >单一语言</option>
									<%}else{ %>
										<option value="1" selected >单一语言</option>
										<option value="0">双语</option>
									<%} %>	
								</select>
							</div>
						</label>
					</div>
					<a class="i7" id="subi7">确定</a>
				</div>
			</div>
		</ol>
		
		
		<ol class="regV yuyan1V" style="display:none">
			<div>
				<div class="hd" style="height: 2.5rem;">
					<div class="i1"></div>
					<div class="i2">阅读设置</div>
				</div>
				<div class="bd" style="height: 3.5rem">
					<div class="i6">
						<label>
							<div class="setupdiv">
								<span >语言1：</span>
								<select id="languageFlag" style="font-size: 0.4rem;width: 3rem;margin-left: 0.1rem;">
									<option value="<%=languageId %>" selected><%=Language.getLanguageByid(languageId).getLname() %></option>
									<%
									if(true){
										  Iterator it = Language.languageColl.iterator();
										  while(it.hasNext()){
											    Language l = (Language)it.next();
										  		if(l.getLanguageId()==languageId)
										  			continue;
												%>
												<option value="<%=l.getLanguageId() %>"><%=l.getLname()%></option>
											    <%
										  } %>
								    <%
								    } %>
								</select>
							</div>
						</label>
					</div>
					<a class="i7" id="subi7">确定</a>
				</div>
			</div>
		</ol>
		
		<ol class="regV yuyan2V" style="display:none">
			<div>
				<div class="hd" style="height: 2.5rem;">
					<div class="i1"></div>
					<div class="i2">阅读设置</div>
				</div>
				<div class="bd" style="height: 3.5rem">
					<div class="i6">
						<label>
							<div class="setupdiv">
								<span >语言2：</span>
								<select id="language2Flag" style="font-size: 0.4rem;width: 3rem;margin-left: 0.1rem;">
									<%
									if(true){
										 if(languageId_2==-1){
											 	%>  		
												<option selected>未设置</option>
												<%
										 }else{
											 	%>  		
												<option value="<%=languageId_2 %>" selected><%=Language.getLanguageByid(languageId_2).getLname() %></option>
												<%
										 }
										
										  Iterator it = Language.languageColl.iterator();
										  while(it.hasNext()){
											    Language l = (Language)it.next();
										  		if(l.getLanguageId()==languageId_2)
										  			continue;
												%>
												<option value="<%=l.getLanguageId() %>"><%=l.getLname()%></option>
											    <%
										  } %>
								    <%
								    } %>
								</select>
							</div>
						</label>
					</div>
					<a class="i7" id="subi7">确定</a>
				</div>
			</div>
		</ol>
		
		
		
		
		
		<style>
		.setupdiv{
			margin: 0.2rem;padding-left: 0.7rem;
		}
		.setupdiv span{
			font-size: 0.45rem;
    		width: 1.8rem;
    		display: inline-block;
		}
		</style>
		
		
		
		
		
		
		
		
		
	</div>
</body>
</html>