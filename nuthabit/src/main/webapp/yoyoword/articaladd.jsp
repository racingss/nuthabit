<%@ page language="java" import="adon.word.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="/js/jquery-2.1.1.min.js"></script>
<script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
</head>
<body>

<div data-role="page" id="pageone">
  
  <div data-role="header">
  <h1>添加文章</h1>
  </div>
  
  <div data-role="main" class="ui-content">
    <form method="post" action="/yoyoword/articaladd.html">
      <div class="ui-field-contain">
        <textarea name="addinfo" id="info"></textarea>
        <br/>
        第一行标题，第二行URl，第三行开始正式内容
      </div>
      <input type="submit" data-inline="true" value="add">
    </form> 
    
    
 </div>
  
  
  
  
	<%@ include file="foot.jsp" %>  
  
  
</div>
</body>