<%@page import="com.babycard.wx.*"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Collection coll = (Collection)request.getAttribute("voiceColl");
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>voice coll</h1>
<%
Iterator it = coll.iterator();
while(it.hasNext()){
	Voice v = (Voice)it.next();
	%>
	<audio preload="auto" controls >
						<source src="/<%=v.getVoiceUrl()%>">
	</audio>
	<%
}
%>

</body>
</html>