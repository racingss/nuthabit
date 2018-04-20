<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
System.out.println("cardjsp");
Card c = (Card)request.getAttribute("card");
Collection cardColl = (Collection)request.getAttribute("cardColl");
CardPic p = (CardPic)cardColl.iterator().next();
Collection meaningColl = c.cardMeaningColl;
Collection soundColl = c.cardSoundColl;
long languageId = 0;
boolean autoPlay=false;
if (request.getSession().getAttribute("languageId") != null) {
	languageId = Long.parseLong(request.getSession().getAttribute("languageId").toString());
}
long tagId=0;
if(request.getParameter("tagId")!=null)
	tagId = Long.parseLong(request.getParameter("tagId"));
else if(request.getSession().getAttribute("tagId")!=null)
	tagId = Long.parseLong(request.getSession().getAttribute("tagId").toString());

String displayMeaning=null;
%>    
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta charset="utf-8">
    <title>看图识物</title>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/card.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/main.js"></script>
    <script src="js/util.js"></script>
    <script type="text/javascript">
	    $(function(){
	    	
	    	var currPicId=<%=p.getPicId()%>;
	    	
	        $("body").height($(window).height());
	
	        $(".card").click(function(){
	        	window.location.href='/card/cardlist.html?cardIndex=<%=c.getCardIndex()%>&flag=n&tagId=<%=tagId%>';
	        })
	        
	        $("#deleteImg").click(function(){
	            $("#deletePopup").show();
	        })
	        
	        $("#removeImg").click(function(){
	            $("#deletePopup").show();
	        })
	        
	        
	        $("#tagImg").click(function(){
	            $("#tagPopup").show();
	        })
	        
	        $("#soundImg").click(function(){
	            $("#soundPopup").show();
	        })
	        
	        $("#soundQueImg").click(function(){
	            $("#soundQuePopup").show();
	        })
	        
	        $("#favImg").click(function(){
	        	window.location.href='cardlist.html?static=t&fav=t&picId='+currPicId+'&cardId=<%=c.getCardId()%>'
	        })
	        
	        
	        $("#picImg").click(function(){
	            $("#picPopup").show();
	        })
	        
	        
	        $("#languageImg").click(function(){
	            $("#languagePopup").show();
	        })
	        
	        $("#deletehref").click(function(){
	        	window.location.href='cardlist.html?static=t&delete=t&picId='+currPicId+'&cardId=<%=c.getCardId()%>'
	        })
	        
	        $(".coursePopup .close").click(function(){
	            $(".coursePopup").hide();
	        })
	        $(".closeAll").click(function(){
	            $("#deletePopup").hide();
	        })
	        
	        $(".smallpic").click(function(){
	        	currPicId= $(this).attr("picId");
	            $(".card").attr("src",$(this).attr("src"));
	        })
	        
	        $(".tagspan").click(function(){
	        	console.log(1);
		        $("#cardTag").attr("value",$("#cardTag").attr("value")+" "+$(this).text());
		    })
		    
		    //语言菜单
		    var languageflag=0;
		    $("#mainlanguage").click(function(){
		    	if(languageflag==0){
		    		$(".secondlanguage").show();
		    		languageflag=1;
		    	}else{
		    		$(".secondlanguage").hide();
		    		languageflag=0;
		    	}
		    })
		    $(".secondlanguage").click(function(){
		    	window.location.href="/card/cardlist.html?static=t&cardId=<%=c.getCardId()%>&languageId="+$(this).attr("languageId");
		    })
		    
	    })
	 </script>
	 <style type="text/css">
	 	.cardsmallimg{
			text-align:center;
		}
		.smallpic{
			margin-top:15px;width:90%;
		}
		.addimg{
			//position: absolute;
		    //top: 30px;
		    //right: 33px;
		    background: #ece9e9;
		    padding: 2px 5px;
		    background: #fff;
		    border-radius: .1rem;
		}
		.dianzan{
			background: #ece9e9;
		    padding: 2px 5px;
		    background: #fff;
		    border-radius: .1rem;
		    position: absolute;
		    top: 30px;
		    right: 33px;
    	}
		.dianzanimg{
			width:20px;
		}
		.soundpic{
			width: 25px;
		}
	 </style>
</head>
<body style="display: block">
<header style="text-align: center;">
	<div class="sdPlan" style="text-align:center;background: #97d9e6;height: 35px;">
    	<a href="/card/cardlist.html?cardIndex=<%=c.getCardIndex()%>&flag=p&tagId=<%=tagId%>">
    		<img alt="" src="images/left.jpg" class="menupic"/>
    	</a>
    	<a href="index.html">
    		<img alt="" src="images/first.jpg" class="menupic"/>
    	</a>
    	<a href="#">
    		<img alt="" src="images/faveriout.jpg" class="menupic" id="favImg" />
    	</a>
    	<a href="#">
    		<img alt="" src="images/new.jpg" class="menupic" id="picImg" />
    	</a>
    	<a href="#">
    	</a>
    	<a href="#">
    		<img alt="" src="images/soundQue.jpg" class="menupic" id="webImg" />
    	</a>
    	<a href="#">
    		<img alt="" src="images/remove.jpg" class="menupic" id="removeImg" />
    	</a>
    	<a href="/card/cardlist.html?cardIndex=<%=c.getCardIndex()%>&flag=n&tagId=<%=tagId%>">
    		<img alt="" src="images/right.jpg" class="menupic"/>
    	</a>
    </div>
</header>
    
<div class="page paleBlue">
    
    <div class="sdPlan" style="text-align:center;">
    	<!--                主图               -->
    	<img alt="" src="<%=p.getImgurl()%>" style="margin-top:15px;width:90%;" class="card" picId="<%=p.getPicId() %>" cardId="<%=c.getCardId() %>" />
    	
    	<!--                点赞               -->
    	<div style="" class="dianzan"><span><em>👍</em><em>189</em></span></div>	
	    </div>
	    
	    <!--                语言               -->
    	<div id="mainlanguage" style="position: absolute;
	    top: 30px;
	    left: 35px;
	    padding: 2px 5px;
	    background: #34b9de;
	    border-radius: .5rem;
	    font-size: 15px;
	    color: white;">
	    	<a href="#"><%
	    	if(languageId==0){
	    		out.print("中");
	    	}else if(languageId==1){
	    		out.print("English");
	    	}else if(languageId==2){
	    		out.print("日本語");
	    	}else{
	    		out.print("火星语");
	    	}
	    	%></a>
	    </div>
	    
		<!--                语言               -->	    
	    <div class="secondlanguage" languageId="0" style="position: absolute;
	    top: 20px;
	    left: 90px;
	    padding: 2px 5px;
	    background: <%if(languageId==0){out.print("#34b9de;");}else{out.print("#d6eaff;");}%>
	    border-radius: .5rem;
	    font-size: 12px;
	    color: <%if(languageId==0){out.print("white;");}else{out.print("#74b319;");}%>
	    display: none;">
	    	<a href="#">中</a>
	    </div>
	    
	    <!--                语言               -->
	    <div class="secondlanguage" languageId="1" style="position: absolute;
	    top: 50px;
	    left: 80px;
	    padding: 2px 5px;
	    background: <%if(languageId==1){out.print("#34b9de;");}else{out.print("#d6eaff;");}%>;
	    border-radius: .5rem;
	    font-size: 12px;
	    color:<%if(languageId==1){out.print("white;");}else{out.print("#74b319;");}%> ;
	    display: none;">
	    	<a href="#">English</a>
	    </div>
	    
	    <!--                语言               -->
	    <div class="secondlanguage" languageId="2" style="position: absolute;
	    top: 70px;
	    left: 40px;
	    padding: 2px 5px;
	    background: <%if(languageId==2){out.print("#34b9de;");}else{out.print("#d6eaff;");}%>;
	    border-radius: .5rem;
	    font-size: 12px;
	    color: <%if(languageId==2){out.print("white;");}else{out.print("#74b319;");}%>;
	    display: none;">
	    	<a href="#">日本語</a>
	    </div>	
	    
	    <!--                文字图层               -->
	    <div class="" style="position: absolute;
    bottom: 30px;
    right: 30px;
    padding: 2px 5px;
    background: transparent;
    border-radius: 0.5rem;
    font-size: 5em;
    color: white;">
	    	<%
	    	if(meaningColl!=null&&meaningColl.size()>0){
		  		Iterator it = meaningColl.iterator();
		  		while(it.hasNext()){
		  			CardMeaning cm = (CardMeaning)it.next();
		  			if(cm.getLanguageId()!=languageId)
		  				continue;
		  			displayMeaning = cm.getMeaning();
		  			////暂时先不用，图层文字
		  			if(false){
			  			%>
			  			<%=displayMeaning%>
					    	<%
					    	if(cm.getSound()!=null){
					    	%>
					    	<audio id="mp3Btn<%=cm.getMeaningId() %>" <%if(autoPlay){ %>autoplay="true"<%} %> ><source src="/<%=cm.getSound() %>" type="audio/mpeg" /></audio>
					    	<%} %>
			  		   <%
			  		   autoPlay=false;
		  			}
		  		}
	 		}
	 		%>
	    </div>	
	    
	<!--            文字             -->    
    <div class="sdPlan" style="text-align: center;
    background: #97d9e6;
    /* height: 35px; */
    padding-top: 10px;
    font-size: 35px;
    color:white;
    " id="showmeaning">
        <p>
			<img src="images/plus.png" style="width:25px;" id="soundImg">  <%=displayMeaning%>  <img src="images/language.svg" style="width:25px;" id="languageImg">
		</p>		
	</div>
	
	<!--            音频             -->
	<%
	if(soundColl!=null&&soundColl.size()>0){
  		Iterator it = soundColl.iterator();
  		while(it.hasNext()){
  			CardSound s = (CardSound)it.next();
  			if(s.getLanguageId()!=languageId)
  				continue;
  			%>
			<div class="sdPlan" style="text-align: center;
    background: #97d9e6;
    /* height: 35px; */
    padding-top: 10px;
    font-size: 35px;
    color: white;
    text-align: left;
    padding-left: 25px;
		    " id="showmeaning">
		    	<p style="
    font-size: 20px;
		    	">
		    		<img style="width: 25px;
    //height: .55rem;
    overflow: hidden;
    border-radius: 50%;"src="http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJRmN2LQ0g82D7Nlzkw7oPKTMW6f2uxbaIXVtPWIgLq8urkkFsUArqF4JNdWfdQ6KjG9HqCaNRr6g/0">
		    		<span>Adonwang</span>
		    		<a href="cardlist.html?static=t&cardId=<%=c.getCardId()%>&soundId=<%=s.getSoundId()%>" style="float: right;margin-right: 20px;">
		    			<img alt="" src="images/remove.jpg" class="soundpic" />
		    		</a>
		    		<span style="float: right;margin-right: 20px;"><em>👍</em><em>189</em></span>
		    		
		    	</p>
		    	<p>
					<audio controls="controls" <%if(autoPlay){ %>autoplay="true"<%} %>>  
				   		<source src="/<%=s.getSound() %>"> 
					</audio>	
		    	</p>
	    	</div>	
	    	<%
	    	autoPlay=false;
  			}
	}
	%>
    
    
    <script type="text/javascript">
    $(function(){
	    $("#webImg").click(function(){
		    var API_KEY = '8642964-6d029d012d52e8af2d6ae089b';
		    var URL = "https://pixabay.com/api/?key="+API_KEY+"&q="+encodeURIComponent('<%=displayMeaning%>');
		    $.getJSON(URL, function(data){
		    if (parseInt(data.totalHits) > 0)
		        $.each(data.hits, function(i, hit){ console.log(hit.webformatURL);addElementDiv(hit.previewURL,hit.webformatURL); });
		    else
		        console.log('No hits');
		    });
	    });
	    
	    
	    function addElementDiv(src,bigsrc) {
			var parent = document.getElementById("imglist");
			var div = document.createElement("div");
			div.setAttribute("class", "sdPlan cardsmallimg");
			
			var dianzan  = document.createElement("div");
			dianzan.setAttribute("class", "addimg");
			dianzan.innerHTML ="<img src='images/plus.png' class='dianzanimg'/>";
			dianzan.onclick= function(){
				console.log('0');
				$.ajax({
					url: 'addcardpic.html?cardId=<%=c.getCardId()%>&displayurl='+src+'&weburl='+bigsrc,
					dateType:'json',
				    success: function(data){
				    	alert('添加成功');
				    }
			   });
			};
			
			var img = document.createElement("img");
			img.src = bigsrc;	
			img.setAttribute("class", "smallpic");
			img.onclick= function(){
		    	//$(".card").attr("src",src);
			};
			div.appendChild(img);
			div.appendChild(dianzan);
			parent.appendChild(div);
		}
    });
    </script>
    
    
    
    <!--                副图               -->
    <div id="imglist">
    <%
    int i=0;//跳过第一张
    if(cardColl.size()>1){
    	Iterator it = cardColl.iterator();
    	while(it.hasNext()){
    		CardPic cp = (CardPic)it.next();
    		if(i++<1)
    			continue;
    		%>
    		<div class="sdPlan cardsmallimg" >
		    	<img alt="" src="<%=cp.getImgurl()%>" style="" class="smallpic" picId='<%=cp.getPicId() %>' />
		    	<div class="addimg">
		    		<a href="cardlist.html?fav=t&static=t&cardId=<%=c.getCardId()%>&picId=<%=cp.getPicId()%>">
		    			<img src='images/plus.png' class='dianzanimg'/>
		    		</a>
		    	</div>	
		    </div>
    	<%
    	}
    }
    %>
    </div>
    
</div>


<div class="coursePopup">
    <div class="coursePopupBox" style="height:10.4rem;">
    	<form action="/card/addbabycard.html" method="post"  data-ajax="false">
    	<div class="inputBox3">
    		<%
    		long meaningId=0;
    		if(meaningColl!=null&&meaningColl.size()>0){
	    		Iterator it = meaningColl.iterator();
	    		while(it.hasNext()){
	    			CardMeaning cm = (CardMeaning)it.next();
	    			meaningId = cm.getMeaningId();
	    			%>
	    			<div class="title">
            			<span ><%=cm.getLanguage() %></span>
            			<br>
            			<input type="text" name="<%=cm.getLanguageInput() %>" value="<%=cm.getMeaning()%>"/>
            		</div>
	    			<%
	    		}
	    		
    		}
    		%>
    		<div class="title">
	    		<select name="language">
	    			<option>中文</option>
	    			<option>English</option>
	    		</select>
    		</div>
    		<div class="title">
    			<input type="text" name="" value=""/>
    		</div>
    		<div class="title">
      			<span >标签</span><input type="text" name="tag" value=""/>
      		</div>
      		<div class="title">
      			<span >水果</span> <span >数字</span> <span >人体</span>
      		</div>	
    		<button class="submitBtn" id="signin">提交</button>
    		<button class="submitBtn" id="signin">删除</button>
    		<div class="close" style="bottom: -1rem;"></div>
        </div>
        </form>
    </div>
</div>

<!--                标签               -->
<div class="coursePopup" id="tagPopup">
    <div class="coursePopupBox" style="height:10.4rem;">
    	<form action="/card/addbabycard.html" method="post"  data-ajax="false">
    	<input type="hidden" name="cardId" value="<%=c.getCardId()%>" />
    	<div class="inputBox3">
    		<div class="title">
      			<span >标签</span><input type="text" name="tag" id="cardTag" value=""/>
      		</div>
      		<div class="title">
      			<span class="tagspan">水果</span> <span class="tagspan">数字</span> <span class="tagspan">人体</span>
      		</div>
        </div>
        <button class="submitBtn" id="signin" style="background:#ed4d83">提交</button>
    		<div class="close" style=""></div>
        </div>
        </form>
    </div>
</div>

<!--                上传图片               -->
<div class="coursePopup" id="picPopup">
    <div class="coursePopupBox" style="height:10.4rem;">
    	<form action="uploadbabycard.html" method="post" enctype="multipart/form-data"  data-ajax="false">
    	<input type="hidden" name="cardId" value="<%=c.getCardId()%>" />
    	<<div class="inputBox3">
            <div class="title">
                <input type="file" name="cardfile" id="cardfile" />
            </div>
        </div>
        <button class="submitBtn" id="signin" style="background-color: #ef4684;">上传</button>
    		<div class="close" style=""></div>
        </div>
        </form>
    </div>
</div>

<!--                上传语音               -->
<div class="coursePopup" id="soundPopup">
    <div class="coursePopupBox" style="height:10.4rem;">
    	<form action="uploadbabycard.html" method="post" enctype="multipart/form-data"  data-ajax="false">
    	<input type="hidden" name="cardId" value="<%=c.getCardId()%>" />    	
    	<input type="hidden" name="sound" value="t" />
    	<<div class="inputBox3">
    		<div class="title">
    			<select name="languageId" id="languageId" >
    				<option value="0">中文</option>
		    		<option value="1">English</option>
		    		<option value="2">日本語</option>
    			</select>
    		</div>
            <div class="title">
                <input type="file" name="cardfile" id="cardfile" />
            </div>
        </div>
        <button class="submitBtn" id="signin" style="background-color: #ef4684;">上传</button>
    		<div class="close" style=""></div>
        </div>
        </form>
    </div>
</div>

<!--                上传语音问题               -->
<div class="coursePopup" id="soundQuePopup">
    <div class="coursePopupBox" style="height:10.4rem;">
    	<form action="uploadbabycard.html" method="post" enctype="multipart/form-data"  data-ajax="false">
    	<input type="hidden" name="cardId" value="<%=c.getCardId()%>" />    	
    	<input type="hidden" name="soundQue" value="t" />
    	<<div class="inputBox3">
    		<div class="title">
    			<select name="meaningId" id="meaningId" >
		    		<%
		    		if(meaningColl!=null&&meaningColl.size()>0){
			    		Iterator it = meaningColl.iterator();
			    		while(it.hasNext()){
			    			CardMeaning cm = (CardMeaning)it.next();
			    			meaningId = cm.getMeaningId();
			    			%>
			    			<option value="<%=cm.getMeaningId()%>"><%=cm.getMeaning() %></option>
			    			<%
			    		}
		    		}
		    		%>
    			</select>
    		</div>
            <div class="title">
                <input type="file" name="cardfile" id="cardfile" />
            </div>
        </div>
        <button class="submitBtn" id="signin" style="background-color: #ef4684;">上传</button>
    		<div class="close" style=""></div>
        </div>
        </form>
    </div>
</div>

<!--                多语种文字输入               -->
<div class="coursePopup" id="languagePopup">
    <div class="coursePopupBox" style="height: 100%;margin-top: 40px;">
    	<form action="cardlist.html" method="post"  data-ajax="false">
    	<input type="hidden" name="cardId" value="<%=c.getCardId()%>" />
    	<div class="inputBox3">
    		<div class="title">
    			<span >中文</span><input type="text" name="chinese"  value="<%=c.getMeaning(0)%>"/>
      		</div>
      		<div class="title">
      			<span >English</span><input type="text" name="english"  value="<%=c.getMeaning(1)%>"/>
      		</div>
      		<div class="title">
      			<span >日本語</span><input type="text" name="japan"  value=""/>
      		</div>
      		<div class="title">
      			<span >Français</span><input type="text" name="franch"  value=""/>
      		</div>
      		<div class="title">
      			<span >한국어.</span><input type="text" name="franch"  value=""/>
      		</div>
      		<button class="submitBtn" id="signin" style="background-color: #ef4684;">提交</button>
    		<div class="close" style="position: fixed;bottom: 100px;"></div>
        </div>
        </form>
    </div>
</div>

<!--                删除图片               -->
<div class="coursePopup" id="deletePopup">
    <div class="coursePopupBox" style="height:10.4rem;">
    	<a href="#" id="deletehref">
        <div class="item">
            	<i class="i1"></i>
            	<p>请删除</p>
        </div>
        </a>
        <a href="#" class="closeAll">
        <div class="item" >
            	<i class="i2"></i>
            	<p>返回</p>
        </div>
        </a>
        <div class="close"></div>
    </div>
</div>


</body>
</html>