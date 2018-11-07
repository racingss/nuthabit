<%@ page language="java" import="adon.word.*,java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String word = null;
	Wordlist w = null;
	if(request.getAttribute("w")!=null){
		w= (Wordlist)request.getAttribute("w");
		word = w.getWord();
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="/js/jquery-2.1.1.min.js"></script>
<script
	src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
</head>
<body>

	<div data-role="page" id="pageone">
		<div data-role="header">
			<h1>Study</h1>
		</div>


		<%
			if (w != null) {
		%>
		<div data-role="main" class="ui-content">
			<div data-role="collapsible" data-collapsed-icon="arrow-d"
				data-expanded-icon="arrow-u">
				<h1 class="title_word" word="<%=w.getWord()%>"><%=w.getWord()%></h1>
				<%
					if (w != null) {
				%>
				<p>
					English phonetics:<%=w.getEnPh()%></p>
				<p>
					<audio controls="controls">
						<source src="<%=w.getEnPhMp3()%>" />
					</audio>
				</p>
				<p>
					American phonetics:<%=w.getAmPh()%></p>
				<p>
					<audio controls="controls">
						<source src="<%=w.getAmPhMp3()%>" />
					</audio>
				</p>
				<p><%=w.getMeaningFormat()%></p>
				<%
					}
				%>
			</div>

			<div data-role="controlgroup" data-type="horizontal">
				<a href="/yoyoword/articalword.html?id=<%=request.getParameter("id") %>&word=<%=word %>&process=7" data-role="button" data-inline="true">加入单词本</a>
				<a href="/yoyoword/articalword.html?id=<%=request.getParameter("id") %>&word=<%=word %>&process=0" data-role="button" data-inline="true">跳过</a>
				
			</div>
		</div>
		<%
			}
		%>

		<%@ include file="foot.jsp"%>

	</div>
</body>