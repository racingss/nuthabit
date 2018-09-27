<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
long languageId = new LanguageHttp().getLanguageId(request);
String url = request.getRequestURI();
%>
<footer>
    <a <%if(url.indexOf("home")!=-1){ %>class="active"<%} %> href="/diandian/">
        <i></i>
        <span><%=Menu.getMenu("home", languageId) %></span>
    </a>
    <!-- 
    <a href="discover.html">
        <i></i>
        <span><%=Menu.getMenu("discover", languageId) %></span>
    </a>
     -->
    <a  <%if(url.indexOf("booklist")!=-1){ %>class="active"<%} %> href="booklist.html">
        <i></i>
        <span>精品</span>
    </a>
    <a <%if(url.indexOf("subscribe")!=-1){ %>class="active"<%} %>  href="subscribe.html">
        <i></i>
        <span><%=Menu.getMenu("order", languageId) %></span>
    </a>
    <a <%if(url.indexOf("parents")!=-1){ %>class="active"<%} %>  href="parents.html">
        <i></i>
        <span><%=Menu.getMenu("parent", languageId) %></span>
    </a>
</footer>
