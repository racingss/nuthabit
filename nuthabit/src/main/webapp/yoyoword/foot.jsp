<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div data-role="footer" style="text-align:center;">
  	<div data-role="controlgroup" data-type="horizontal">
	    <a href="articallist.html?rand=<%=System.currentTimeMillis() %>" class="ui-btn ui-icon-heart ui-btn-icon-left">读文章</a>
	    <a href="mylist.html?rand=<%=System.currentTimeMillis() %>" class="ui-btn ui-icon-heart ui-btn-icon-left">词汇表</a>
	    <!--a href="study.html?rand=<%=System.currentTimeMillis() %>" class="ui-btn ui-icon-eye ui-btn-icon-left">Study</a-->
	    <a href="review.html?rand=<%=System.currentTimeMillis() %>" class="ui-btn ui-icon-eye ui-btn-icon-left">背单词</a>
	    <!-- a href="book.html?rand=<%=System.currentTimeMillis() %>" class="ui-btn ui-icon-grid ui-btn-icon-left">默写</a -->
	    
	</div>
  </div>