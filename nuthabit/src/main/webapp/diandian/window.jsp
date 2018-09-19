<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
long languageId = new LanguageHttp().getLanguageId(request);
long languageId_2 = new LanguageHttp().getLanguageId_2(request);
Kehu k = new KehuUtil().getKehu(request, response);
KehuCardMember m = new KehuDAO().getMember(k.getKehuId());
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
			
			$(".clo").click(function(){
				$(".regV").hide();
			})
			
			
			//$(".languagei8").click(function(){
				//$(".regV").hide();
				//$(".reviewwindow").show();
			//})
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
				    	//alert("<%=Menu.getMenu("update_succ", languageId) %>");
				    	$(".regV").hide();
						//$(".setupnextwindow").show();
				    	//location.href='tutorial.jsp';
				    }
			   });
			})
			
			
			$(".cardsub").click(function(){
				cardId=$(this).attr("cardId");
				<%
				if (m == null || m.getCloseDate().getTime() < System.currentTimeMillis()) {
					%>
					src=$(this).attr("src");
					$(".regVimg").attr("src",src);
					$(".regVhref").attr("href","/card/cardlist.html?cardId="+cardId);
					$(".cardwindow").show();
					<%
				}else{
					%>
					location.href="/card/cardlist.html?cardId="+cardId;
					<%
				}
				%>
				
			})

		})
	</script>
		
		<!--          注册成功提示            -->
		<%
		
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
						请提供您家宝贝的出生年月，<br/>
						以便您获得更多适龄阅读内容：
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
					
					if (m == null || m.getCloseDate().getTime() < System.currentTimeMillis()) {
						%><%=Menu.getMenu("you_are_not_member", languageId) %><%
					}else{
						%><%=Menu.getMenu("you_are_member", languageId) %><%
					}
					%></div>
					<div class="i3"><%=Menu.getMenu("you_curr_have", languageId) %><span><%=new KehuDAO().getJifen(k.getId()) %></span><i></i></div>
				</div>
				<div class="bd" style="height: 6.5rem;">
					<div style="text-align:center;">
						<img class="regVimg" src="/myplan/upload/historypic/1527556550466.jpg" style="height: 3.5rem;margin: 0.3rem;border-radius: 20px;">
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
		
		
		
		
		
		
		
		
		
		
		
		<ol class="regV setupnextwindow" style="display:none">
			<div>
				<div class="bd" style="padding: 1rem 0;height: 3rem;background: none;">
					<img src="frame/select.png" style="position: fixed;left: 75%;top:35%;width: 15%;z-index: 99;" id="movehand">
					<p style="text-align:center;color:white;font-size:1rem;top: 40%;position: absolute;" class="clo">
						这里可以左右拖动，点击后按照分类显示
					</p>
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
		if(request.getParameter("recomm")!=null &&false){
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
				<div class="bd" style="height: 6.5rem;">
					<div style="text-align:center;">
							<div class="recently">
									<div>
										<%
										if(true){
											Iterator recentIt = new CardDAO().getCardListByAge(k.b.getAge(), 1).iterator();
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
		
		
		<style>
		.bigi5{
			display:block;
			width:100%;
		}
		.nianl{
		    float: left;
		    padding: 0.2rem 0.5rem;
		    color: #fff;
		    margin-left: 0.2rem;
		    border-radius: 0.5rem;
		    margin-top: 0.3rem;
		    font-size: 0.4rem;
		}
		.miaol{
			float: left;
    		padding: 0.5rem;
    		font-size: 0.35rem;
		}
		.dagou{
			    margin-left: 0.2rem;
    			margin-right: 0.2rem;
		}
		</style>
		
		
		<%
		if(request.getParameter("level")!=null && false){
		%>
		<ol class="regV">
			<div>
				<div class="hd">
					<div class="i1"></div>
					<div class="i2"><%=Menu.getMenu("readinglevel", languageId) %></div>
					<div class="i3">针对1-4岁儿童，仅供参考</div>
				</div>
				<div class="bd">
					<div class="bigi5">
						<span class="nianl" style="background:#ffd200" id="nian1" index="1">1-2岁</span>
						<span class="nianl" style="background:#b3b9b5" id="nian2" index="2">2-3岁</span>
						<span class="nianl" style="background:#b3b9b5" id="nian3" index="3">3-4岁</span>
					</div>
					<div class="bigi5">
						<span class="miaol" id="miaol1" >
							<img src="frame/check.png" class="dagou" />能够辨别出红黄蓝绿这样的原色，目光喜欢追随色彩鲜艳的物体。<br/>
							<img src="frame/check.png" class="dagou" />对形状有了初步的认识，并展现出初级的空间认知能力。<br/>
							<img src="frame/check.png" class="dagou" />计数雏形出现，尽管不具备计数的能力，但能区分“一个”和“多个”。<br/>
							<img src="frame/check.png" class="dagou" />“牙牙学语”以很快的速度发展成为发音清晰的词汇，更可发展成极简的短句。
						</span>	
						<span class="miaol" id="miaol2" style="display:none">
							<img src="frame/check.png" class="dagou" />分辨出更多的颜色如一些混合色（紫/橙/粉），但不总能记住名字<br/>
							<img src="frame/check.png" class="dagou" />能指认出简单的几何图形，能够在描点的前提下画出直线<br/>
							<img src="frame/check.png" class="dagou" />可以按照顺序数到5左右，用手指点数物体可以数出2-3个<br/>
							<img src="frame/check.png" class="dagou" />词汇量迅速发展为几百个甚至上千，有时依靠图片就可以说出物体名称。
						</span>
						<span class="miaol" id="miaol3" style="display:none">
							<img src="frame/check.png" class="dagou" />已经认识了大部分的颜色，并能对照色卡与物体的颜色配对<br/>
							<img src="frame/check.png" class="dagou" />能够辨别大部分形状（对于相似的形状缺少一些辨识力）<br/>
							<img src="frame/check.png" class="dagou" />能够数到十，复述3-4个乱序的数字，数出5个以内的物体个数<br/>
							<img src="frame/check.png" class="dagou" />从头脑中的词语库里拼组出意图清晰的句子，可以尽自己所能，详尽的回答问题<br/>
						</span>
					</div>
					
					<a class="i8"><%=Menu.getMenu("search_ok", languageId) %></a>
				</div>
			</div>
		</ol>
		<script type="text/javascript">
		$(function(){
			$(".nianl").click(function(){
				index=$(this).attr("index");
				$(".miaol").hide();
				$(".nianl").css({'background':'#b3b9b5'});
				if(index==1){
					$("#nian1").css({'background':'#ffd200'});
				}else if(index==2){
					$("#nian2").css({'background':'#38ce6d'});
				}else if(index==3){
					$("#nian3").css({'background':'#00adef'});
				}
				$("#miaol"+index).show();
			})
		})
		</script>
		
		<%}%>
		
		