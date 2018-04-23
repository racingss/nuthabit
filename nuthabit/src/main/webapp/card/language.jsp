<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Collection languageColl = (Collection)request.getAttribute("languageColl");

//切换语言
long languageId = 0;
if (request.getSession().getAttribute("languageId") != null) {
	languageId = Long.parseLong(request.getSession().getAttribute("languageId").toString());
}
String type=request.getParameter("type");

%>    
<!DOCTYPE html>
<html lang="zh"><head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title><%=Menu.getMenu("language_title", languageId) %></title>
	<link rel="stylesheet" href="css/list_style.css">
	<link rel="stylesheet" href="css/dialog.css">
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
<body style="background: #d5441c;" onload="load()">
	<div class="htmleaf-container">
		<section class="accordion">
			<%
			if(true){
				  Iterator it = languageColl.iterator();
				  while(it.hasNext()){
					    Language l = (Language)it.next();
				  
						%>
						<div class="item" >
								<a href="index.html?<%=type %>=<%=l.getLanguageId() %>" >
					            	<img src="img/play.png" class="headpng">
					            
					            <h3 >
					            	<a href="index.html?<%=type %>=<%=l.getLanguageId() %>" >
					            		<%=l.getLname()%>
					            	</a>	
					            </h3>
					    </div>			
					    <%
				  } %>
		    <%
		    } %>
		    </section>
		    
		    
	</div>
	
	<script src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/dialog.js"></script>
	<script type="text/javascript">
	function load()
	{
		$.dialog({
			showTitle : false,
			contentHtml : '<%if(type.equals("languageId"))out.print(Menu.getMenu("language_1", languageId));
			else if(type.equals("languageId_2"))out.print(Menu.getMenu("language_2", languageId));
			%>'
	    });
	}
	</script>

</body></html>