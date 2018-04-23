<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Collection tagColl = (Collection)request.getAttribute("tagColl");
Collection myColl = (Collection)request.getAttribute("myColl");

//切换语言
long languageId = 0;
if (request.getSession().getAttribute("languageId") != null) {
	languageId = Long.parseLong(request.getSession().getAttribute("languageId").toString());
}

long languageId_2 = -1;
if (request.getSession().getAttribute("languageId_2") != null) {
	languageId_2 = Long.parseLong(request.getSession().getAttribute("languageId_2").toString());
}
if(request.getParameter("languageId_2")!=null){
	languageId_2 = Long.parseLong(request.getParameter("languageId_2").toString());
	request.getSession().setAttribute("languageId_2", languageId_2);
}
if(request.getParameter("type")!=null){
	languageId_2 = -1;
	request.getSession().setAttribute("languageId_2", -1);
}


%>    
<!DOCTYPE html>
<html lang="zh"><head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>点兵点将 儿童卡片</title>
	<link rel="stylesheet" href="css/list_style.css">
	<link rel="stylesheet" href="css/dialog.css">
	<link href="css/mfb.css" rel="stylesheet">
	<script src="js/mfb.js"></script>  
	<style type="text/css">
	a{
		text-decoration:none;
		color: #524f4f;
	}
	.headpng{
		width:70px;
	}
	.imgspan{
		background: #fff;
	    border-radius: .1rem;
	    padding-bottom: .35rem;
	    box-shadow: 0px 0.08rem 0.3rem rgba(0, 0, 0, 0.1);
	    display: inline-block;
	    text-align: center;
	    margin: 10px;
	}
	.btn{
		cursor: pointer;
		-webkit-appearance: button;
		display: inline-block;
	    font-weight: 400;
	    text-align: center;
	    white-space: nowrap;
	    vertical-align: middle;
	    -webkit-user-select: none;
	    -moz-user-select: none;
	    -ms-user-select: none;
	    user-select: none;
	    border: 1px solid transparent;
	    padding: .375rem .75rem;
	    font-size: 1rem;
	    line-height: 1.5;
	    border-radius: .25rem;
	    transition: color .15s ease-in-out,background-color .15s ease-in-out,border-color .15s ease-in-out,box-shadow .15s ease-in-out;
	    text-transform: none;
	    
	    margin-left:15px;
	}
	.btnwidth40{
		width: 40%;
	}
	.btnwidth30{
		width: 25%;
	}
	
	.fa{
		display: inline-block;
    font: normal normal normal 14px/1 FontAwesome;
    font-size: inherit;
    text-rendering: auto;
    -webkit-font-smoothing: antialiased;
	}
	.btn-green{
		background-color: #34c70f;
    	color: #fff;
	}
	.btn-green{
		background-color: #34c70f;
    	color: #fff;
	}
	.btn-pink{
		background-color: #e129ec;
    	color: #fff;
	}
	.btn-orange{
		background-color: #F57C02;
    	color: #fff;
	}
	</style>
	<script type="text/javascript">
	</script>
</head>
<body style="background: #e8e8e8;">
	<header>
		<img alt="" src="img/head.png" style="width: 100%;margin: 0;">
	</header>
	<div class="htmleaf-container">
		
		<section class="accordion">
			<!--           自建的卡片组                  -->
			<%
			Iterator myIt = myColl.iterator();
			while(myIt.hasNext()){
			   CardTag ct = (CardTag)myIt.next();
			   %>
		       <div class="item">
		            <img src="img/<%=ct.getHeadpng() %>" class="headpng">
		            <h3><%=ct.getTag() %>
		            	<a href="#" class="deletecardgroup" tagId="<%=ct.getTagId() %>" >
		            		<img alt="" src="img/delete.png" style="width:18px;padding-left: 5px;">
		            	</a>
		            	<a href="#" class="">
		            		<img alt="" src="img/lock.png" style="width:18px;padding-left: 5px;">
		            	</a>
		            	
		            </h3>
		       </div>
		            <p>
		            <span>
		                <a href="create_own_card_create_tag.html?tagId=<%=ct.getTagId()%>">
		            		<button type="button" class="btn btn-pink btnwidth30" ><%=Menu.getMenu("add", languageId) %><i class="fa fa-book"></i></button>
		            	</a>
		                <a href="cardlist.html?tagId=<%=ct.getTagId()%>">
		            		<button type="button" class="btn btn-green btnwidth30" ><%=Menu.getMenu("study", languageId) %><i class="fa fa-book"></i></button>
		            	</a>
		            	<a href="test_iop.html">
		            		<button type="button" class="btn btn-orange btnwidth30"><%=Menu.getMenu("test", languageId) %><i class="fa fa-book"></i></button>
		            	</a>
		            </span>
		            <%
		            int i=0;
		            if(true){
		            	Iterator it = new CardDAO().getCardListByTag(ct.getTagId()).iterator();
		            	while(it.hasNext()){
		            		Card c = (Card)it.next();
		            		int a=90;
		            		if(i++>4)
		            			a = 25;
		            		else if(i>1)
		            			a = 40;
		            		%>
		            		<span class="imgspan" style="width:<%=a%>%">
		            			<a href="cardlist.html?static=t&cardId=<%=c.getCardId()%>&tagId=<%=ct.getTagId()%>">
		            				<img alt="" src="<%=c.getImg() %>" style="margin-top:10px;width:90%;" class="card">
		            				<br/>
		            				<%=c.getMeaning() %>
		            			</a>
		            		</span>
    						<%
		            	}
		            }
		            %>
		            </p>
		       <%} %> 
			
			
			
			
			
			<!--           默认的卡片组                  -->
			<%
			Iterator tagIt = tagColl.iterator();
			while(tagIt.hasNext()){
			   CardTag ct = (CardTag)tagIt.next();
			   %>
		       <div class="item">
		            <img src="img/<%=ct.getHeadpng() %>" class="headpng">
		            <h3>
		            <%=CardTagLanguage.getTagLanguage(ct.getTagId(), languageId) %></h3>
		       </div>
		            <p>
		            <span class="carddetail" style="background: #fff;
	    border-radius: .1rem;
	    padding: 20px;
	    box-shadow: 0px 0.08rem 0.3rem rgba(0, 0, 0, 0.1);
	    display: inline-block;
	    margin: 10px;
	    color: #524f4f;
	    width:80%;">
		            	<%=Menu.getMenu("tag_detail", languageId) %>：20个常见的动物
		            	<br/><%=Menu.getMenu("tag_pic_count", languageId) %>：100  
		            	<br/><%=Menu.getMenu("tag_sound_count", languageId) %>：38
		            	<br/><%=Menu.getMenu("tag_use", languageId) %>：<%=Menu.getMenu("free", languageId) %> 
		            	<br/><%=Menu.getMenu("get_point", languageId) %>：50
		            	<br/><%=Menu.getMenu("support_language", languageId) %>：🇨🇳🇺🇸🇯
		            	<br/>
		            	<br/>
		            	<a href="cardlist.html?tagId=<%=ct.getTagId()%>">
		            		<button type="button" class="btn btn-green btnwidth40" ><%=Menu.getMenu("study", languageId) %><i class="fa fa-book"></i></button>
		            	</a>
		            	<a href="test_iop.html">
		            		<button type="button" class="btn btn-orange btnwidth40"><%=Menu.getMenu("test", languageId) %><i class="fa fa-book"></i></button>
		            	</a>
		            </span>
		            <span>
		            	
		            </span>
		            <%
		            int i=0;
		            if(true){
		            	Iterator it = new CardDAO().getCardListByTag(ct.getTagId()).iterator();
		            	while(it.hasNext()){
		            		Card c = (Card)it.next();
		            		int a=90;
		            		if(i++>4)
		            			a = 25;
		            		else if(i>1)
		            			a = 40;
		            		%>
		            		<span class="imgspan" style="width:<%=a%>%">
		            			<a href="cardlist.html?static=t&cardId=<%=c.getCardId()%>&tagId=<%=ct.getTagId()%>">
		            				<img alt="" src="<%=c.getImg() %>" style="margin-top:10px;width:90%;" class="card">
		            				<br/>
		            				<%=c.getMeaning(languageId,c.getCardId()) %>
		            			</a>
		            		</span>
    						<%
		            	}
		            }
		            %>
		            </p>
		       <%} %> 
		       <div class="item" style="position: sticky;bottom: -5px;height: 60px;background-color: #2f89f9;">
		            
		            <h3 style="text-align: center;width: 100%;padding: 0px;"><a href="create_own_card_create_tag.html" style="color:#ffe9c1"><%=Menu.getMenu("add_my_card", languageId) %></a></h3>
		       </div>            
		   </section>
		
	</div>
	
	
	<!--                   菜单                     -->
	<ul id="menu" class="mfb-component--br mfb-zoomin" data-mfb-toggle="hover">
	  <li class="mfb-component__wrap">
	    <a href="#" class="mfb-component__button--main">
	      <i class="mfb-component__main-icon--resting ion-plus-round"><%=Menu.getMenu("lang", languageId) %></i>
	      <i class="mfb-component__main-icon--active ion-close-round"><%=Menu.getMenu("lang", languageId) %></i>
	    </a>
	    <ul class="mfb-component__list">
	      <%
	      if(languageId_2!=-1){
	    	  %>
	          <li>
		        <a href="index.html?type=delete_language_2" data-mfb-label="Waiting..." class="mfb-component__button--child">
		          <i class="mfb-component__child-icon ion-social-github">-</i>
		        </a>
		      </li>
		  <%}else{ %>
		      <li>
		        <a href="language.html?type=languageId_2" data-mfb-label="Waiting..." class="mfb-component__button--child">
		          <i class="mfb-component__child-icon ion-social-github">+</i>
		        </a>
		      </li>
		  <%} %>    
	      
	      <%
	      if(languageId_2!=-1){
	    	  %>
	    	  <li>
		        <a href="language.html?type=languageId_2" data-mfb-label="Waiting..." class="mfb-component__button--child">
		          <i class="mfb-component__child-icon ion-social-octocat"><%=Language.getLanguageByid(languageId_2).getLname() %></i>
		        </a>
		      </li>
	    	  <%
	      }
	      %>
	      <%
	      if(true){
	    	  %>
	    	  <li>
		        <a href="language.html?type=languageId" data-mfb-label="Waiting..." class="mfb-component__button--child">
		          <i class="mfb-component__child-icon ion-social-octocat"><%=Language.getLanguageByid(languageId).getLname() %></i>
		        </a>
		      </li>
	    	  <%
	      }
	      %>
	    </ul>
	  </li>
	</ul> 
	
	<script src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/dialog.js"></script>
	<script type="text/javascript">
		(function ($) {
		    'use strict';
		    $('.item').on("click", function () {
		        $(this).next().slideToggle(100);
		        $('p').not($(this).next()).slideUp('fast');
		    });
		    
		    $('.deletecardgroup').on("click", function () {
		    	var tagId = $(this).attr("tagId");
		    	$.dialog({
		    	       type : 'confirm',
		    	       onClickOk : function(){
		    	    	   window.location.href='deletetag.html?tagId='+tagId;
		    	       },
		    	       onClickCancel : function(){        		
		    	       },
		    	       contentHtml : '<p>是否删除</p>'
		    	    });
		    });
		}(jQuery));
	</script>

</body></html>