<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
long languageId = new LanguageHttp().getLanguageId(request);
Kehu k = new KehuUtil().getKehu(request, response);
Baby b = (Baby)request.getAttribute("baby");
%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title><%=Menu.getMenu("edit_profie", languageId) %></title>
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
				$(".regV").hide();
			})
			
			var sex=0;
			
			$(".sex").click(function(){
				sex=$(this).attr("sex");
			})
			
			$("#subi7").click(function(){
				birthyear=$("#birthyear option:selected").text();
				birthmonth=$("#birthmonth option:selected").text();
				$.ajax({
					url: '/diandian/homeajax.html?birthyear='+birthyear+'&birthmonth='+birthmonth+'&sex='+sex,
					dateType:'json',
				    success: function(data){
				    	if(sex==0)
				    		$("#sexp").text('<%=Menu.getMenu("sex_0", languageId) %>');
				    	else if(sex==1)
				    		$("#sexp").text('<%=Menu.getMenu("sex_1", languageId) %>');
				    	$("#birthp").text(birthyear+'-'+birthmonth);
				    	alert("<%=Menu.getMenu("update_succ", languageId) %>");
				    	$(".regV").hide();
				    }
			   });
			})
			
			$("#subiname").click(function(){
				$.post("/diandian/homeajax.html",
						  {
							babyName:document.getElementById('babyName').value
						  },
						  function(data,status){
							  $("#babyNamep").text(document.getElementById('babyName').value);
						      alert("<%=Menu.getMenu("update_succ", languageId) %>");
						      $(".regV").hide();
							   
	    		 });
			})
			
			$("#subimobile").click(function(){
				$.post("/diandian/homeajax.html",
						  {
							mobile:document.getElementById('mobile').value
						  },
						  function(data,status){
							  $("#mobilep").text(document.getElementById('mobile').value);
						      alert("<%=Menu.getMenu("update_succ", languageId) %>");
						      $(".regV").hide();
							   
	    		 });
			})
			
			$("#subiemail").click(function(){
				$.post("/diandian/homeajax.html",
						  {
							email:document.getElementById('email').value
						  },
						  function(data,status){
							  $("#emailp").text(document.getElementById('email').value);
						      alert("<%=Menu.getMenu("update_succ", languageId) %>");
						      $(".regV").hide();
							   
	    		 });
			})
			
			$("#subiwx").click(function(){
				$.post("/diandian/homeajax.html",
						  {
							wx:document.getElementById('wx').value
						  },
						  function(data,status){
							  $("#wxp").text(document.getElementById('wx').value);
						      alert("<%=Menu.getMenu("update_succ", languageId) %>");
						      $(".regV").hide();
							   
	    		 });
			})
			
		})
	</script>
</head>
<body>
	<div class="page parents">
		<div>
			<img class="bg" src="frame/parents-11.png">
			<div class="list" style="z-index:  1;position:  relative;margin-top: 1rem;">
				<a href="edit.html?name=t">
					<span><%=Menu.getMenu("baby_name", languageId) %></span>
					<p id="babyNamep"><%=b.getBabyName() %></p>
				</a>
				<a href="edit.html?bir=t">
					<span><%=Menu.getMenu("chushengnianyue", languageId) %></span>
					<p id="birthp"><%=b.getbYear() %>-<%=b.getbMonth() %></p>
				</a>
				<a href="edit.html?bir=t">
					<span><%=Menu.getMenu("chushengnianyue", languageId) %></span>
					<p id="sexp"><%=Menu.getMenu("sex_"+b.getSex(), languageId) %></p>
				</a>
			</div>
			
			<div class="list" style="z-index:  1;position:  relative;margin-top: 1rem;">
				<a href="edit.html?mob=t">
					<span><%=Menu.getMenu("jiazhangshouji", languageId) %></span>
					<p id="mobilep"><%=k.getShouji() %></p>
				</a>
				<a href="edit.html?mail=t">
					<span><%=Menu.getMenu("jiazhangyouxiang", languageId) %></span>
					<p id="emailp"><%=k.getEmail() %></p>
				</a>
				<a href="edit.html?weixin=t">
					<span><%=Menu.getMenu("weixinhao", languageId) %></span>
					<p id="wxp"><%=k.getWx() %></p>
				</a>
				<a href="#">
					<span><%=Menu.getMenu("diqu", languageId) %></span>
				</a>
			</div>
			
			<div class="list" style="z-index:  1;position:  relative;margin-top: 1rem;">
				<a href="/card/language.html?type=languageId">
					<span><%=Menu.getMenu("morenyuyan", languageId) %></span>
					<p><%=Language.getLanguageByid(languageId).getLname() %></p>
				</a>
			</div>
			
		</div>
		
		
		
		<jsp:include page="foot.jsp" flush="true"/>
		
		
		
		
		<%
		if(request.getParameter("bir")!=null){
		%>
		<ol class="regV">
			<div>
				<div class="hd">
					<div class="i1"></div>
					<div class="i2"><%=Menu.getMenu("chushengnianyue", languageId) %></div>
				</div>
				<div class="bd">
					<div class="i4">
					</div>
					<div class="i5">
						<a class="active sex" sex="0"></a>
						<a class="sex" sex="1"></a>
					</div>
					<div class="i6">
						<label>
							<span><%=Menu.getMenu("birth", languageId) %><%=Menu.getMenu("year", languageId) %></span>
							<div>
								<select id="birthyear">
									<option selected>2012</option>
									<%
									for(int i=2013;i<=2018;i++){
									%>
									<option><%=i %></option>
									<%} %>
								</select>								
							</div>
						</label>
						<label>
							<span><%=Menu.getMenu("birth", languageId) %><%=Menu.getMenu("month", languageId) %></span>
							<div>
								<select id="birthmonth">
									<option selected>1</option>
									<%
									for(int i=2;i<=12;i++){
									%>
									<option><%=i %></option>
									<%} %>
								</select>
							</div>
						</label>
					</div>
					<a class="i7" id="subi7"><%=Menu.getMenu("search_ok", languageId) %></a>
					<a class="i8" id="subi8" style="bottom: -1.5rem;"><%=Menu.getMenu("next_time", languageId) %></a>
				</div>
			</div>
		</ol>
		<%} %>
		
		
		<%
		if(request.getParameter("name")!=null){
		%>
		<ol class="regV">
			<div>
				<div class="hd">
					<div class="i1"></div>
					<div class="i2"><%=Menu.getMenu("baby_name", languageId) %></div>
				</div>
				<div class="bd" style="height:5rem;">
					<div class="i4">
					</div>
					<div class="i6">
						<input type="text" name="babyName" id="babyName" style="text-align:center;width: 6rem;font-size: 0.6rem;height: 1rem;border: 0.05rem solid #9ea295;border-radius: 0.2rem;padding: .5rem 0.8rem;margin: 0.5rem;">	
					</div>
					<a class="i7" id="subiname"><%=Menu.getMenu("search_ok", languageId) %></a>
					<a class="i8" id="subi8" style="bottom: -0.5rem;"><%=Menu.getMenu("next_time", languageId) %></a>
				</div>
			</div>
		</ol>
		<%} %>
		
		<%
		if(request.getParameter("mob")!=null){
		%>
		<ol class="regV">
			<div>
				<div class="hd">
					<div class="i1"></div>
					<div class="i2"><%=Menu.getMenu("jiazhangshouji", languageId) %></div>
				</div>
				<div class="bd" style="height:5rem;">
					<div class="i4">
					</div>
					<div class="i6">
						<input type="text" name="mobile" id="mobile" style="text-align:center;width: 6rem;font-size: 0.6rem;height: 1rem;border: 0.05rem solid #9ea295;border-radius: 0.2rem;padding: .5rem 0.8rem;margin: 0.5rem;">	
					</div>
					<a class="i7" id="subimobile"><%=Menu.getMenu("search_ok", languageId) %></a>
					<a class="i8" id="subi8" style="bottom: -0.5rem;"><%=Menu.getMenu("next_time", languageId) %></a>
				</div>
			</div>
		</ol>
		<%} %>
		
		<%
		if(request.getParameter("mail")!=null){
		%>
		<ol class="regV">
			<div>
				<div class="hd">
					<div class="i1"></div>
					<div class="i2"><%=Menu.getMenu("jiazhangyouxiang", languageId) %></div>
				</div>
				<div class="bd" style="height:5rem;">
					<div class="i4">
					</div>
					<div class="i6">
						<input type="text" name="email" id="email" style="text-align:center;width: 6rem;font-size: 0.6rem;height: 1rem;border: 0.05rem solid #9ea295;border-radius: 0.2rem;padding: .5rem 0.8rem;margin: 0.5rem;">	
					</div>
					<a class="i7" id="subiemail"><%=Menu.getMenu("search_ok", languageId) %></a>
					<a class="i8" id="subi8" style="bottom: -0.5rem;"><%=Menu.getMenu("next_time", languageId) %></a>
				</div>
			</div>
		</ol>
		<%} %>
		
		<%
		if(request.getParameter("weixin")!=null){
		%>
		<ol class="regV">
			<div>
				<div class="hd">
					<div class="i1"></div>
					<div class="i2"><%=Menu.getMenu("weixinhao", languageId) %></div>
				</div>
				<div class="bd" style="height:5rem;">
					<div class="i4">
					</div>
					<div class="i6">
						<input type="text" name="wx" id="wx" style="text-align:center;width: 6rem;font-size: 0.6rem;height: 1rem;border: 0.05rem solid #9ea295;border-radius: 0.2rem;padding: .5rem 0.8rem;margin: 0.5rem;">	
					</div>
					<a class="i7" id="subiwx"><%=Menu.getMenu("search_ok", languageId) %></a>
					<a class="i8" id="subi8" style="bottom: -0.5rem;"><%=Menu.getMenu("next_time", languageId) %></a>
				</div>
			</div>
		</ol>
		<%} %>
		
		
	</div>
</body>
</html>