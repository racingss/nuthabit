<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Card c = (Card)request.getAttribute("card");
Collection cardColl = (Collection)request.getAttribute("cardColl");
Collection meaningColl = c.cardMeaningColl;
Collection soundColl = c.cardSoundColl;

//切换语言
long languageId = new LanguageHttp().getLanguageId(request);


String displayMeaning=c.getMeaning(languageId, c.getCardId());

double headlength=3;
if(displayMeaning.length()>4)
	headlength=2;
if(displayMeaning.length()>8)
	headlength=1.5;

int slide=0;
%>    
<!DOCTYPE html>
<html lang="zh" class="fsvs"><head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, user-scalable=no">
	<title><%=Menu.getTitle(languageId) %></title>
	<style type="text/css">
    
    
    .carddetail{
    	text-align: center;
    	margin:1em;
    }
    
    .inc-scroll-landscape-container { 
	width: 100%; 
	overflow: hidden; 
}
.inc-scroll-landscape-container > .inc-scroll-landscape-content {
   
}
.inc-scroll-landscape-container > .inc-scroll-landscape-content > ul {
	margin: 0 5px;
}
.inc-scroll-landscape-container > .inc-scroll-landscape-content > ul > li { 
	display: inline-block; 
    text-align: center; 
}
.bookli{
	overflow: hidden;
    //width: 90px;
    height: 50px;
    border-radius: 5px;
    background: #FFF;
    box-shadow: 0px 0.08rem 0.3rem rgba(0, 0, 0, 0.1);
}

	.headbar{
		top: 10px;
    	left: 10px;
    	position: absolute;
	}
	.headbar a{
		//display:block;
		float:left;
		width:32px;
		height:32px;
		margin-left:1px;
	}
	.headmenu{
		display:none;
	}
	
	.piccontrolbutton{
		background-color: #f49731;
	    border-radius: 0.5rem;
	    margin: 0.3em;
	    padding: 0.5em;
	    box-shadow: 0px 0.4rem 0.5rem rgba(0, 0, 0, 0.1);
	    display: inline-block;
	    color: white;
	    text-decoration: none;
	    font-size: 1em;
	}
	
	.footbarbutton{
		border-radius: 0.5rem;
		margin: 1em;
		padding: 0.5em;
		box-shadow: 0px 0.4rem 0.5rem rgba(0, 0, 0, 0.1);
		display: inline-block;
		color: white;
		text-decoration: none;
	}
	
	.wenzishuru{
		font-size: 1em;
	    padding: 5px;
	    border-radius: 0.3em;
	    background: #fffefb;
	    color: #535d92;
	    width: 90%;
	    box-shadow: 0px 0.1rem 0.2rem rgba(0, 0, 0, 0.1);
	}
	
	.slide{
		background-color: #fffefe;
		border-radius: 0.3rem;
		margin:1em;
		box-shadow: 0px 0.08rem 0.3rem rgba(0, 0, 0, 0.1);
		padding-bottom: 0.5em;
		background-image: url(/diandian/frame/home-18.jpg);
    	
	}
	.tiship{
		display:inline-block;
		text-align:center;
		width:100%;
		color: #ed4630;
	}
	
	h4{
		text-align: center;
	    margin: 0;
	    color: #63d169;
	    margin: 0.5em;
    }
    
    .cardselect{
    	background-color: #5cd06a;
	    padding: 0.5em;
	    box-shadow: 0px 0.4rem 0.5rem rgba(0, 0, 0, 0.1);
	    color: white;
	    text-decoration: none;
	    border-radius: 0.5rem;
	    font-size: 1em;
	    text-align: center;
    }

	body{
		background-color: #effefc;
	}
	.add{
		width: 3rem;
	    height: 3rem;
	    border-radius: 50%;
	    position: fixed;
	    bottom: 4rem;
	    right: 2rem;
	    background: url(/diandian/frame/found-3.png);
	    background-size: 2rem 2rem;
	    background-position: center;
	    background-repeat: no-repeat;
	    background-color: #ffc600;
	    box-shadow: 0.15rem 0.15rem 0.2rem 0px rgba(255, 225, 0, 0.2);
	    display: block;
	}
	.delete_button{
		width: 1.5rem;
	    height: 1.5rem;
	    border-radius: 50%;
	    float: left;
	    margin-left: 0.5rem;
	    background: url(/diandian/frame/sr-5.png);
	    background-size: 1.0rem 1.0rem;
	    background-position: center;
	    background-repeat: no-repeat;
	    background-color: #effefd;
	    box-shadow: 0.15rem 0.15rem 0.2rem 0px rgba(255, 225, 0, 0.2);
	    display: block;
	}
	.trashImg{
		width: 3rem;
	    height: 3rem;
	    border-radius: 50%;
	    position: fixed;
	    bottom: 0.5rem;
	    right: 2rem;
	    background: url(/diandian/frame/clear-button.png);
	    background-size: 1.5rem 1.5rem;
	    background-position: center;
	    background-repeat: no-repeat;
	    background-color: #6cdad6;
	    box-shadow: 0.15rem 0.15rem 0.2rem 0px rgba(255, 225, 0, 0.2);
	    display: block;
	}
	a{
		text-decoration: none;
	}
	.menutab{
		background-image: url(/diandian/frame/home-18.jpg);
	    border-radius: 0.2rem;
	    padding: 0.3em 0.6em;
	    margin: 0.1em;
	    color: #f49731;
	    box-shadow: 0px 0.08rem 0.3rem rgba(0, 0, 0, 0.1);
	}
	.active{
		
	}
	.slide{
		display:none;
	}
	.audiosound{
		width: 90%;
		background-color: #fac633;
		padding: 0.5em;
		box-shadow: 0px 0.4rem 0.5rem rgba(0, 0, 0, 0.1);
		border-radius: 0.5rem;
		margin-top: 0.3em;
	}
    </style>  
    <link rel="stylesheet" href="css/dialog.css">
    <script src="assets/js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="js/dialog.js"></script>
	
   <script type="text/javascript">
	//获取缩略图盒子宽高后再执行缩放
	function DrawImage_box(ImgID) {
	    var width_img=$("#imgBox").width();
	    var height_img=$("#imgBox").height();
	    DrawImage(ImgID, width_img, height_img);
	}
	
	
	//图片缩放居中核心功能
	function DrawImage(ImgID, width_s, height_s) {
	    var image = new Image();
	    image.src = ImgID.src;
	    
	    if (image.width > 0 && image.height > 0) {
	    	flag = true;
	        if (image.width / image.height <= width_s / height_s) {
	            ImgID.width = width_s;
	            var height = (image.height * width_s) / image.width;
	            ImgID.height = height;
	            ImgID.style.marginTop = -(height - height_s)/2 + "px";
	        } else {
	            ImgID.height = height_s;
	            var width = (image.width * height_s) / image.height;
	            ImgID.width = width;
	            ImgID.style.marginLeft = -(width - width_s)/2 + "px";
	        }
	    }
	}
</script> 
</head>
<body>
	<div class="category" style="width: 100%;overflow-x: scroll;">
		<div style="border-radius: 0.5rem;
    white-space: nowrap;
    padding: .4rem .4rem;
    padding-bottom: 0;
    padding-bottom: 0.5em;">
			<a href="#" class="menutab" slideId='1' style="background: #f49731;
    	color: white;"><%=Menu.getMenu("detail_cover", languageId) %></a>
			<a href="#" class="menutab"  slideId='2'><%=Menu.getMenu("basic_info", languageId) %></a>
			<%
			long tempSlideId=3;
			if(true){
	        	Iterator itPic = cardColl.iterator();
	        	while(itPic.hasNext()){
	        		CardPic cp = (CardPic)itPic.next();
	        		%>
	        		<a href="#" class="menutab" slideId='<%=tempSlideId++%>'><%=Menu.getMenu("card", languageId) %><%=tempSlideId-3%></a>
	        		<%
	        	}
			}
			%>
			<a href="test_sound.jsp?cardId=<%=c.getCardId() %>" class="menutab"><%=Menu.getMenu("add_card", languageId) %></a>
			<a href="cardlist.html?cardId=<%=c.getCardId() %>" class="menutab"><%=Menu.getMenu("review", languageId) %></a>
			<%
			if(cardColl.size()>1){
			%>
			<a href="test_iop.html?cardId=<%=c.getCardId() %>" class="menutab"><%=Menu.getMenu("test", languageId) %></a>
			<%} %>
			<a href="/diandian/" class="menutab"><%=Menu.getMenu("back_to_home", languageId) %></a>
		</div>
	</div>


    <div >
    	<!--                  封面                 -->
    	<div class="slide" style="display:block" id="slide_1">
    		<h2 style="color: #FF9800;text-align:  center;padding-top: 0.5em;">
	    		<%=Menu.getMenu("detail_cover", languageId) %>
    		</h2>
    		<p class="tiship">
    			
    			<%
    			if(c.getDefaultPic()!=null){
    			%>
    			<span style="width:100%;display:block;text-align:center;">        		
       				<img alt="" src="/<%=c.getDefaultPic()%>" style="width:40%;box-shadow: 0px 0.08rem 0.3rem rgba(0, 0, 0, 0.1);margin:0.5em;">
       			</span>
       			<%} %>
       			<span style="font-size: 0.8em;">
    				<%=Menu.getMenu("detail_1", languageId) %>
    			</span>
    			<br/>
    			<a href="test_sound.jsp?cardId=<%=c.getCardId() %>&cover=t" class="piccontrolbutton"><%=Menu.getMenu("upload_but", languageId) %></a>
    		</p>
    		
    	</div>
    	
    	<!--                  卡片信息                 -->
    	<div class="slide"  id="slide_2">
    		<h2 style="color: #FF9800;text-align:  center;padding-top: 0.5em;">
    			<%=Menu.getMenu("basic_info", languageId) %>
    		</h2>
    		<%
		    for(int i=0;i<=1;i++){
		    %>
    			<h4><%=Language.getLanguageByid(i).getLname() %><%=Menu.getMenu("detail_name", languageId) %></h4>
    		<p class="tiship">
    			<input type="text" value="<%=c.getMeaning(i, c.getCardId())%>" class="cardselect cardnameinput" distLanguage="<%=i%>">
    		</p>
    		<%} %>
    		
    		<h4><%=Menu.getMenu("suit_age", languageId) %></h4>
    		<p class="tiship">
    			<select class="cardselect ageselect">
    				<option value="<%=c.getAge() %>"><%=c.getAge() %>-<%=c.getAge()+1 %><%=Menu.getMenu("sui", languageId) %></option>
    				<%
    				for(int i=1;i<=5;i++){
    					if(i==c.getAge())
    						continue;
    				%>
    				<option value="<%=i%>"><%=i %>-<%=i+1 %><%=Menu.getMenu("sui", languageId) %></option>
    				<%} %>
    			</select>		
    		</p>
    		
    		<h4><%=Menu.getMenu("suit_tag", languageId) %></h4>
    		<p class="tiship">
    			<select class="cardselect tagselect">
    				<%
    				CardTag ct = new CardTagDAO().getCardTagByCardId(c.getCardId());
    				if(ct!=null){
    					%>
    					<option value="<%=ct.getTagId()%>"><%=Menu.getMenu("tag_"+ct.getTagId(), languageId) %></option>	
    					<%
    				}
    				CardTag.getCartTagByTagId(1);
    				Iterator tagIt = CardTag.coll.iterator();
					while(tagIt.hasNext()){
						ct = (CardTag)tagIt.next();
						%>
						<option value="<%=ct.getTagId()%>"><%=Menu.getMenu("tag_"+ct.getTagId(), languageId) %></option>
						<%
					}
    				%>
    				
    			</select>		
    		</p>
    		
    	</div>
    	
    	<%
    	tempSlideId=3;
    	if(true){
    		int soundI=1;
        	Iterator itPic = cardColl.iterator();
        	while(itPic.hasNext()){
        		slide++;
        		CardPic cp = (CardPic)itPic.next();
        		Collection picColl = CardPic.getPicCollByMainPicId(cp.getPicId());
        		%>
        		
        		<!--             图片           -->
        		<div class="slide"  id="slide_<%=tempSlideId++%>">
        			<h2 style="color: #FF9800;text-align:  center;padding-top: 0.5em;"><%=Menu.getMenu("detail_cover", languageId) %>卡片<%=slide %>
        				<a href="#" mainPicId="<%=cp.getPicId() %>" class="delete_button"></a>
        			</h2>
        			        		
	 				<!--         主图           -->
	 				<h4><%=Menu.getMenu("detail_mainpic", languageId) %></h4>
	 				<div style="width:100%;display:block;text-align:center;">        		
        				<img alt="" src="<%=cp.getImgurl() %>" picId="<%=cp.getPicId() %>" next="2" pre="0"  class="pic_<%=cp.getPicId()%>" style="width:40%;box-shadow: 0px 0.08rem 0.3rem rgba(0, 0, 0, 0.1);margin:0.5em;">
        			</div>
        			
    
        			
        			<%if(picColl.size()>1){ %>
        			    <h4><%=Menu.getMenu("detail_fupic", languageId) %></h4>
	        			<!--           幅图                   -->
	        			<div class="inc-scroll-landscape-container">
						    <div class="inc-scroll-landscape-content">
						        <ul>
						        	<%
						        	int i=0;
						        	Iterator picIt = picColl.iterator();
						        	while(picIt.hasNext()){
						        		CardPic fpic = (CardPic)picIt.next();
						        		++i;
						        		if(cp.getPicId()==fpic.getPicId())
						        			continue;
						        		if(picColl.size()==1)
						        			continue;
						        	%>
						            <li class="bookli">
						            	<img class="futuimg" alt="" picId="<%=fpic.getPicId() %>" src="<%=fpic.getImgurl()%>" onload="DrawImage(this, 50, 50)" id="futu_<%=cp.getPicId() %>_<%=i %>" mainId="<%=cp.getPicId()%>"  />
						            </li>
						            <%
						        	}%>
						        	
						        </ul>
						     </div>
						 </div> 
	        			<%
	        			
       				}
        			
        			%>
        			
        			<div class="carddetail" style="margin-bottom: 1em;">
	    			<!--                   音效                     -->
	    				<h4>音效</h4>
	    				<p id="" >
	    					<%
	    					if(cp.getSound()!=null && cp.getSound().length()>2){
	    					%>
    						<audio preload="auto" controls id="" class="audiosound" >
								<source src="/<%=cp.getSound() %>">
							</audio>
							<%} %>
							<a href="test_sound.jsp?sound=t&cardId=<%=c.getCardId() %>&mainPicId=<%=cp.getPicId() %>&effect=t" >
				        			<img alt="" src="img/add1.png" />
				  			</a>
				  			<a href="carddetail.html?cardId=<%=c.getCardId() %>&picId=<%=cp.getPicId() %>&cleansound=t" soundId="" picId="<%=cp.getPicId() %>" cardId="<%=c.getCardId() %>">
				        			<img alt="" src="img/x-button.png" />
				  			</a>
						 <p>		
	    			</div>
        		
        			
        			
        			
		    		<div class="carddetail" style="margin-bottom: 1em;">
		    			<!--                   文字                     -->
        			<%
		    		for(int i=0;i<=1;i++){
		    				CardMeaning cm = CardMeaning.getStaticCard(cp.getPicId(), i);
		    				if(cm!=null){
			    				%>
			    				<h4><%=Language.getLanguageByid(i).getLname() %>:</h4>
			    				<input type="text" name="" value="<%if(cm!=null)out.print(cm.getMeaning());else out.print(c.getMeaning(languageId, c.getCardId())); %>" class="wenzishuru cardselect" distLanguage="<%=i%>" picId="<%=cp.getPicId() %>" />
			    				<div style="display:inline-block;width:100%;margin:0">
			    				<%
		    					Collection picSoundColl = CardSound.getSoundCollByPicId(cp.getPicId());
		    					Iterator picSoundIt = picSoundColl.iterator();
		    					soundI++;
		    					boolean soundFlag=true;
		    					while(picSoundIt.hasNext()){
		    						CardSound cs = (CardSound)picSoundIt.next();
		    						if(cs.getLanguageId()!=cm.getLanguageId())
		    							continue;
		    						soundFlag=false;
		    						%>
		    						<p id="p_<%=cs.getSoundId() %>" >
		    						<audio preload="auto" controls id="sound_<%=soundI%>" class="audiosound" >
										<source src="<%=cs.getSound() %>">
									</audio>
									<a href="test_sound.jsp?sound=t&cardId=<%=c.getCardId() %>&mainPicId=<%=cp.getPicId() %>" >
						        			<img alt="" src="img/add1.png" />
						  			</a>
						  			
						  			<a href="#" picId="<%=cp.getPicId() %>" cardId="<%=c.getCardId() %>" distLanguage="<%=cs.getLanguageId() %>" class="refresh-button" >
						        			<img alt="" src="img/refresh.png" />
						  			</a>
						  			<a href="#" soundId="<%=cs.getSoundId() %>" picId="<%=cp.getPicId() %>" cardId="<%=c.getCardId() %>" distLanguage="<%=cs.getLanguageId() %>" class="x-button">
						        			<img alt="" src="img/x-button.png" />
						  			</a>
						  			<p>	
									<%
		    					}if(soundFlag){
		    						%>
		    						<a href="#" picId="<%=cp.getPicId() %>" cardId="<%=c.getCardId() %>" distLanguage="<%=cm.getLanguageId() %>" class="refresh-button" id="refresh-<%=cp.getPicId() %>">
						        			<img alt="" src="img/refresh.png" />
						  			</a>
						  			<%
		    					}
		    					%>
		    					</div>
		    					<%
					    		
		    				}else{
		    					%>
		    					<h4><%=Language.getLanguageByid(i).getLname() %>:</h4>
			    				<input type="text" name="" value="" class="wenzishuru cardselect" distLanguage="<%=i%>" picId="<%=cp.getPicId() %>" />
		    					<%
		    				}
		    		}
		    		%>
		    		</div>
		    		
		    		<div style="display:block;text-align:center">
		    			<%if(picColl.size()>1){ %>
		    			<a href="#" mainPicId="<%=cp.getPicId() %>" class="main_button piccontrolbutton ">
		    				<%=Menu.getMenu("detail_set_mainpic", languageId) %>
		    			</a>
		    			<%} %>
		    		
		    			<!--a href="upload_pic.jsp?cardId=<%=c.getCardId() %>&mainPicId=<%=cp.getPicId() %>" class="piccontrolbutton">
						        			新增幅图
						  </a-->
		    			  
						  
					</div>						        		
						        		
        			
        			
        		</div>
        		
        		
        		<%
        	}
        }	
    	%>
    	
    	
    	<!--                  底部菜单                 -->
    	<div>
    		<a class="add" href="test_sound.jsp?cardId=<%=c.getCardId() %>"></a>
			<a class="trashImg" href="#"></a>
    	</div>
    	
    			
    </div>

    





<script type="text/javascript">
	
	$(function(){
		//删除语音
		$('.x-button').on("click", function () {
			soundId = $(this).attr("soundId")
			picId = $(this).attr("picId");
			cardId = $(this).attr("cardId");
			$.ajax({
				url: 'cardmanage.html?soundId='+soundId+'&picId='+picId+'&cardId='+cardId,
				dateType:'json',
			    success: function(data){
			    	$('#p_'+soundId).css({'display':'none'});
			    	alert('<%=Menu.getMenu("detail_delete_sound", languageId) %>');
			    }
	   	   });
		});
		
		//更新语音
		$('.refresh-button').on("click", function () {
			picId = $(this).attr("picId");
			cardId = $(this).attr("cardId");
			distLanguage = $(this).attr("distLanguage");
			$.ajax({
				url: 'cardmanage.html?reflashSound=t&distLanguage='+distLanguage+'&picId='+picId+'&cardId='+cardId,
				dateType:'json',
			    success: function(data){
			    	alert('<%=Menu.getMenu("detail_refesh_sound", languageId) %>');
			    	location.reload();
			    }
	   	   });
		});
		
		//菜单
		$('.menutab').on("click", function () {
			$('.slide').css({'display':'none'});
			$('#slide_'+$(this).attr('slideId')).css({'display':'block'});
			$('.menutab').css({'background-image':'url(/diandian/frame/home-18.jpg);','color':'#f49731'});
			$(this).css({'background':'#f49731','color':'white'});
		});
		
		//幅图主图切换
		$('.futuimg').on("click", function () {
			futuId=$(this).attr("picId");
			futuSrc=$(this).attr("src");
			mainId=$(this).attr("mainId");
			mainSrc=$(".pic_"+mainId).attr("src");
			console.log(futuId);
			console.log(futuSrc);
			console.log(mainId);
			console.log(mainSrc);
			
			$(".pic_"+mainId).attr("src",futuSrc);
			$(".pic_"+mainId).attr("picId",futuId)
			$(".delete_"+mainId).attr("picId",futuId)
			
			$(this).attr("src",mainSrc);
			$(this).attr("picId",mainId);
		});
		
		//设为主图
		$('.main_button').on("click", function () {
			mainPicId=$(this).attr("mainPicId");
			updateId=$(".pic_"+mainPicId).attr("picId");
			picId=$(".pic_"+mainId).attr("picId");
			$.ajax({
				url: 'cardmanage.html?cardId=<%=c.getCardId() %>&main=t&picId='+updateId,
				dateType:'json',
			    success: function(data){
			    	alert('<%=Menu.getMenu("detail_update_pic_succ", languageId) %>');
			    	location.reload();
			    }
	   	   });
		});
		
		
		//删除图片
		$('.delete_button').on("click", function () {
			mainPicId=$(this).attr("mainPicId");
			deleteId=$(".pic_"+mainPicId).attr("picId");
			$.dialog({
	    	       type : 'confirm',
	    	       onClickOk : function(){
	    	    	   $.ajax({
	   					url: 'cardmanage.html?cardId=<%=c.getCardId() %>&delete=t&picId='+deleteId,
	   					dateType:'json',
	   				    success: function(data){
	   				    	alert('<%=Menu.getMenu("detail_delete_succ", languageId) %>');
	   				    	location.reload();
	   				    }
	   			   	   });
	    	    	   
	    	       },
	    	       onClickCancel : function(){        		
	    	       },
	    	       contentHtml : '<p><%=Menu.getMenu("detail_deltete_confi", languageId) %></p> <p><%=Menu.getMenu("detail_delete_cant", languageId) %></p>'
	    	});
		});
		
		//删除整套卡片
		$('.trashImg').on("click", function () {
	    	$.dialog({
	    	       type : 'confirm',
	    	       onClickOk : function(){
	    	    	   $.ajax({
		   					url: 'cardmanage.html?cardId=<%=c.getCardId() %>&trash=t',
		   					dateType:'json',
		   				    success: function(data){
		   				    	alert('<%=Menu.getMenu("detail_delete_succ", languageId) %>');
		   				    	window.location.href='/diandian/';
		   				    }
		   			   });
	    	       },
	    	       onClickCancel : function(){        		
	    	       },
	    	       contentHtml : '<p><%=Menu.getMenu("detail_card_confirm", languageId) %></p> <p><%=Menu.getMenu("detail_delete_cant", languageId) %></p>'
	    	    });
	    });
		
		//修改文字
		$('.wenzishuru').on("change", function () {
			distLanguage = $(this).attr("distLanguage");
			meaning = this.value;
			picId= $(this).attr("picId");
			
			$.ajax({
					type: 'POST',
					url: 'cardmanage.html',
					data: {cardId:<%=c.getCardId() %>,picId:picId,distLanguage:distLanguage,meaning:meaning},
					dateType:'json',
				    success: function(data){
				    	alert('<%=Menu.getMenu("update_succ", languageId) %>');
				    }
			});
			
		});
		
		//修改卡片说明
		$('.cardnameinput').on("change", function () {
			distLanguage = $(this).attr("distLanguage");
			meaning = this.value;
			
			$.ajax({
					type: 'POST',
					url: 'cardmanage.html',
					data: {cardId:<%=c.getCardId() %>,distLanguage:distLanguage,meaning:meaning},
					dateType:'json',
				    success: function(data){
				    	alert('<%=Menu.getMenu("update_succ", languageId) %>');
				    }
			});
		});
		
		
		//修改年龄
		$('.ageselect').on("change", function () {
			age = this.value;
			
			$.ajax({
					type: 'POST',
					url: 'cardmanage.html',
					data: {cardId:<%=c.getCardId() %>,age:age},
					dateType:'json',
				    success: function(data){
				    	alert('<%=Menu.getMenu("update_succ", languageId) %>');
				    }
			});
		});
		
		//修改标签
		$('.tagselect').on("change", function () {
			tagId = this.value;
			
			$.ajax({
					type: 'POST',
					url: 'cardmanage.html',
					data: {cardId:<%=c.getCardId() %>,tagId:tagId},
					dateType:'json',
				    success: function(data){
				    	alert('<%=Menu.getMenu("update_succ", languageId) %>');
				    }
			});
		});
		
		
	})
</script>

    
</body>
</html>