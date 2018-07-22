<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
long languageId = new LanguageHttp().getLanguageId(request);
%>
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
			
			$(".languagei8").click(function(){
				$(".regV").hide();
				$(".reviewwindow").show();
			})
			
			
			$("#subi8").click(function(){
				$.ajax({
					url: '/diandian/homeajax.html?nexttime=t',
					dateType:'json',
				    success: function(data){
				    }
			   });
			   $(".regV").hide();
			   $(".languagewindow").show();
			   	
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
				    	alert("<%=Menu.getMenu("update_succ", languageId) %>");
				    	$(".regV").hide();
						$(".languagewindow").show();
				    }
			   });
			})
			
			
			$(".cardsub").click(function(){
				src=$(this).attr("src");
				cardId=$(this).attr("cardId");
				$(".regVimg").attr("src",src);
				$(".regVhref").attr("href","/card/cardlist.html?cardId="+cardId);
				$(".cardwindow").show();
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


		
		<!--          注册成功提示            -->
		<%
		Kehu k = new KehuUtil().getKehu(request, response);
		if(k.b==null && request.getSession().getAttribute("nexttime")==null){
		//if(true){
		%>
		<ol class="regV">
			<div>
				<div class="hd">
					<div class="i1"></div>
					<div class="i2"><%=Menu.getMenu("reg_succ", languageId) %></div>
					<div class="i3"><%=Menu.getMenu("you_get", languageId) %><span>3</span><i></i></div>
				</div>
				<div class="bd">
					<div class="i4">
						<%=Menu.getMenu("for_better_1", languageId) %>：
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
					<a class="i8" id="subi8"><%=Menu.getMenu("next_time", languageId) %></a>
				</div>
			</div>
		</ol>
		
		<%} %>
		
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
				<div class="bd" style="height: 7.5rem;">
					<div style="text-align:center;">
						<img class="regVimg" src="/myplan/upload/historypic/1527556550466.jpg" style="height: 3.5rem;margin: 0.3rem;border-radius: 20px;">
					</div>
					<div style="text-align: center;font-size: 0.4rem;padding-bottom: 0.2rem">
						<input type="checkbox" checked="checked" id="wordFlag"/><%=Menu.getMenu("edit", languageId) %>
						<input type="checkbox" checked="checked" id="soundFlag"/><%=Menu.getMenu("sound", languageId) %>
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
		
		<ol class="regV languagewindow" style="display:none">
			<div>
				
				<div class="bd" style="padding: 1rem 0;height: 3rem;background: none;">
					<img src="frame/home-2.png" style="position: fixed;top: 0;right: 0;width: 2rem;">
					<p style="text-align:center;color:white;font-size:1rem;"><%=Menu.getMenu("changelanguage", languageId) %></p>
					<a class="i8 languagei8" style="position: relative;"><%=Menu.getMenu("nextstep", languageId) %></a>
				</div>
			</div>
		</ol>
		
		
		<ol class="regV reviewwindow" style="display:none">
			<div>
				<div class="bd" style="padding: 1rem 0;height: 3rem;background: none;">
					<img src="frame/eye.png" style="position: fixed;bottom: 1.5rem;right: 0.5rem;width: 1.2rem;">
					<p style="text-align:center;color:white;font-size:1rem;"><%=Menu.getMenu("reviewdesc", languageId) %></p>
					<a class="i8" style="position: relative;"><%=Menu.getMenu("close", languageId) %></a>
				</div>
			</div>
		</ol>
		
		<%
		if(request.getParameter("jifen")!=null){
		%>
		<ol class="regV" style="">
			<div>
				<div class="bd" style="padding: 1rem 0;height: 3rem;background: none;">
					<img src="frame/newmy.jpeg" style="position: fixed;bottom: 2.5rem;right: 0.5rem;width: 1.5rem;">
					<p style="text-align:center;color:white;font-size:1rem;"><%=Menu.getMenu("jifen_fabu_jiangli", languageId) %></p>
					<a class="i8" style="position: relative;"><%=Menu.getMenu("close", languageId) %></a>
				</div>
			</div>
		</ol>
		<%}%>
		
		<%
		if(request.getParameter("recomm")!=null){
		%>
		<ol class="regV" style="">
			<div>
				<div class="hd">
					<div class="i1"></div>
					<div class="i2">
						<%=Menu.getMenu("for_you_recomm", languageId) %>：
					</div>
					<div class="i3"><%=Menu.getMenu("you_curr_have", languageId) %><span><%=new KehuDAO().getJifen(k.getId()) %></span><i></i></div>
				</div>
				<div class="bd" style="height: 7.5rem;">
					<div style="text-align:center;">
							<div class="recently">
									<div>
										<%
										if(true){
											Iterator recentIt = Card.getCardFavColl().iterator();
											int temp=0;
											while(recentIt.hasNext() && temp++<8){
												Card c = (Card)recentIt.next();
												%>
												<a href="/card/cardlist.html?cardId=<%=c.getCardId() %>" class="cardsub" src="<%=c.getImg()%>" cardId="<%=c.getCardId()%>">
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
					<div style="text-align: center;font-size: 0.4rem;padding-bottom: 0.2rem">
						<input type="checkbox" checked="checked" id="wordFlag"/><%=Menu.getMenu("edit", languageId) %>
						<input type="checkbox" checked="checked" id="soundFlag"/><%=Menu.getMenu("sound", languageId) %>
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
		<%}%>