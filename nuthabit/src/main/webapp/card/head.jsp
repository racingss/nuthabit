<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header style="text-align: center;">
	<div style="width:100%;background-color: #fdfdfd;padding-top:5px;text-align:left;padding-left: 0px;">
		<a href="index.jsp">
			<img alt="" src="images/home.jpg" class="headpic" />
		</a>
		<a href="uploadCard.jsp">
			<img alt="" src="images/add.jpg" class="headpic" />
		</a>
		<a href="#">
			<img alt="" src="images/delete.jpg" class="headpic" />
		</a>
		<a href="#">
			<img alt="" src="images/tag.jpg" class="headpic" />
		</a>
		<a href="#">
			<img alt="" src="images/meaning.jpg" class="headpic" />
		</a>
		<a href="#">
			<img alt="" src="images/language.jpg" class="headpic" />
		</a>
	</div>
	<a href="uploadCard.jsp?rand=<%=System.currentTimeMillis() %>" >
		<img alt="" src="images/head1.png" style="width: 98%;margin-top: 3px;">
	</a>
</header>