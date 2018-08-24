<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="zh"><head>    
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>卡片点点Cardpopo——幼儿启蒙教育陪伴平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-transform" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="renderer" content="webkit" />
<meta name="description"  content="卡片点点Cardpopo——幼儿启蒙教育陪伴平台，帮助拓展孩子的可能性，引导孩子发展成为具有国际性视野和思维的全球化人才。 我们提供1-4岁+的幼儿认知卡片软件，参考格塞尔婴幼儿发展量表，分为5大类3个级别，支持中音双语音，20种以上文字。" />
<script src="/card/js/jquery.js"></script>
<link rel="stylesheet" href="css/mainpage.css">
<link rel="stylesheet" href="css/body.css">
<style>
</style>
</head>
<body class="g_body g_locale2052 g_cusSiteWidth">
	<div id="g_main" class="g_main g_col2  forMacAndChrome" style="width: 1425px;">
		<div id="web" class="g_web">
			
			
			<!--  header -->
			<table class="webTopTable" cellpadding="0" cellspacing="0">
				<tbody><tr>
					<td align="center">
						<div id="webTop" class="webTop">
							<div id="corpTitle" class="corpTitle corpTitle2" fontpatterntitle="false" style="min-height: 40px; min-width: 160px;" _linktype="0">
								<div style="" class="newPrimaryTitle">
									<b>
									<span style="color: rgb(255, 101, 55);"></span>
									<span style="">
										<span style="color: rgb(255, 101, 55);">卡片点点</span>
										<span style="color: rgb(204, 204, 204);">Cardpopo</span>
									</span>
									</b>
								</div>
							</div>
						</div>
					</td>
				</tr>
			</tbody></table>
			
			
			<table class="absTopTable" cellpadding="0" cellspacing="0">
				<tbody><tr>
					<td align="center">
						<div id="absTopForms" class="forms sideForms absForms">
							<div style="position:absolute;"></div><!-- for ie6 -->
							
						</div>
					</td>
				</tr>
			</tbody></table>
			
			
			<table class="webNavTable" cellpadding="0" cellspacing="0">
				<tbody><tr>
					<td align="center">
						<div id="webNav" class="webNav webNavDefault">
							<div id="navV2Wrap"><div id="navV2" class="nav navV2">
	<div class="navMainContent"><div class="navContent">
	<div id="navCenterContent" class="navCenterContent">
	<div id="navCenter" class="navCenter" style="width: 496px;">
	<div class="itemPrev" style="display: none;"></div>
	<div class="itemContainer">
	<div title="" id="nav2" class="item itemCol2 itemIndex1 itemSelected itemSelectedIndex1" colid="2" onclick="window.open(&quot;/&quot;, &quot;_self&quot;)" _jump="window.open(&quot;/&quot;, &quot;_self&quot;)">
	<div class="itemCenter"><a hidefocus="true" style="outline:none;" href="/" onclick="return false;"><span class="itemName0">首页</span></a></div></div>
	<div class="itemSep"></div><div title="" id="nav103" class="item itemCol103 itemIndex2" colid="103" onclick="window.open(&quot;/col.jsp?id=103&quot;, &quot;_self&quot;)" _jump="window.open(&quot;/col.jsp?id=103&quot;, &quot;_self&quot;)">
	<div class="itemCenter"><a hidefocus="true" style="outline:none;" href="/col.jsp?id=103" onclick="return false;"><span class="itemName0">关于我们</span></a></div></div>
	<div class="itemSep"></div><div title="" id="nav104" class="item itemCol104 itemIndex3" colid="104" onclick="window.open(&quot;/col.jsp?id=104&quot;, &quot;_self&quot;)" _jump="window.open(&quot;/col.jsp?id=104&quot;, &quot;_self&quot;)">
	<div class="itemCenter"><a hidefocus="true" style="outline:none;" href="/col.jsp?id=104" onclick="return false;"><span class="itemName0">功能介绍</span></a></div></div>
	<div class="itemSep"></div><div title="" id="nav105" class="item itemCol105 itemIndex4" colid="105" onclick="window.open(&quot;/col.jsp?id=105&quot;, &quot;_self&quot;)" _jump="window.open(&quot;/col.jsp?id=105&quot;, &quot;_self&quot;)">
	<div class="itemCenter"><a hidefocus="true" style="outline:none;" href="/col.jsp?id=105" onclick="return false;"><span class="itemName0">App下载</span></a></div></div>
	<div class="itemSep"></div><div title="" id="nav106" class="item itemCol106 itemIndex5" colid="106" onclick="window.open(&quot;/col.jsp?id=106&quot;, &quot;_self&quot;)" _jump="window.open(&quot;/col.jsp?id=106&quot;, &quot;_self&quot;)">
	<div class="itemCenter"><a hidefocus="true" style="outline:none;" href="/col.jsp?id=106" onclick="return false;"><span class="itemName0">联系我们</span></a></div></div>
	</div>
	<div class="itemNext" style="display: none;"></div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	
						</div>
					</td>
				</tr>
			</tbody></table>
			
			<table class="webHeaderTable J_webHeaderTable" cellpadding="0" cellspacing="0" id="webHeaderTable">
				<tbody><tr>
					<td align="center" class="J_webHeaderTable webHeaderTd fk-moduleZoneWrap">
						
						<div id="fk-webHeaderZone" class="elemZone elemZoneModule J_moduleZone fk-webHeaderZone fk-moduleZone forms sideForms">
							<div class="fk-moduleZoneBg fk-elemZoneBg J_zoneContentBg elemZoneBg"></div>
							
						</div>
						
						<div id="webHeader" class="webHeader">
							<table class="headerTable" cellpadding="0" cellspacing="0">
								<tbody><tr>
									<td class="headerCusLeft"></td>
									<td class="headerCusMiddle" align="center" valign="top">
										<div class="headerNav headerNavDefault">
									
										</div>
									</td>
									<td class="headerCusRight"></td>
								</tr>
							</tbody></table>
						</div>
					</td>
				</tr>
			</tbody></table>
		
		
		</div>
	
	
	
	</div>
	

</body>
</html>   