<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
long languageId = new LanguageHttp().getLanguageId(request);
%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta charset="utf-8">
    <title><%=Menu.getTitle(languageId) %></title>
    <script src="js/jquery.min.js"></script>
    <script src="js/main.js"></script>
    <script src="js/util.js"></script>
	 <link rel="stylesheet" type="text/css" href="css/normalize.css">
	 <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.2.0/css/font-awesome.min.css">
	 <link rel="stylesheet" type="text/css" href="css/demo.css">
	 <link rel="stylesheet" type="text/css" href="css/component.css">
	 <link rel="stylesheet" type="text/css" href="css/button.css"> 
	 <link rel="stylesheet" type="text/css" href="css/andrew.mobile.style.css">
	 <link rel="stylesheet" type="text/css" href="css/theme.default.css">
</head>
<body style="background-image: url(/diandian/frame/home-18.jpg);">

<section class="content">
	<h2 style="opacity: 0.9;text-align: center;font-size: 150%"><%=Menu.getMenu("create_owner_card", languageId) %></h2>
	
	
	 <!--             3步骤                  -->
	 <div class="module list press mt_5">
	    <h3 class="bg_white c_title pl_3 line_h_36em bor_bottom bor_gray_ddd"><%=Menu.getMenu("create_seq", languageId) %></h3>
	    <article class="plug_step pa_3 bg_white dis_none rel ovh" style="display: block;">
	      <cite class="dis_block rel w_100 bor_gray_ccc bor_bottom_dashed2" style="top: 23.5px;"></cite><cite class="dis_block rel bor_title bor_bottom2" style="top: 21.5px; width: 108.547px;"></cite><ul class="length3 rel ovh">
	        <li class="fl text_al_c ak-is_active">
	          <strong class="wh_3em line_h_3em bg_gray_ccc c_white border2 bor_white bor_rad_50 bg_title c_yellow dis_block center text_al_c">1</strong>
	          <h6 class="c_gray_777 line_h_24em c_title"><%=Menu.getMenu("create_card_name", languageId) %></h6>
	        </li>
	        <li class="fl text_al_c">
	          <strong class="wh_3em line_h_3em bg_gray_ccc c_white border2 bor_white bor_rad_50 dis_block center text_al_c">2</strong>
	          <h6 class="c_gray_777 line_h_24em"><%=Menu.getMenu("upload_card_pic", languageId) %></h6>
	        </li>
	        <li class="fl text_al_c">
	          <strong class="wh_3em line_h_3em bg_gray_ccc c_white border2 bor_white bor_rad_50 dis_block center text_al_c">3</strong>
	          <h6 class="c_gray_777 line_h_24em"><%=Menu.getMenu("create_card_word", languageId) %></h6>
	        </li>
	      </ul>
	    </article>
	  </div>
	
	    
	    <form action="create_own_card.html" method="post" >
		<span class="input input--kyo input--filled">
			<input class="input__field input__field--kyo" type="text" id="input-19" name="meaning">
			<label class="input__label input__label--kyo" for="input-19">
				<span class="input__label-content input__label-content--kyo"><%=Menu.getMenu("create_card_word_nam", languageId) %></span>
			</label>
		</span>
		
		<p style="text-align:center;">
			<button type="submit" class="bigbutom" style="box-shadow: 0px 0.4rem 0.5rem rgba(0, 0, 0, 0.1);"><%=Menu.getMenu("create_but", languageId) %></button>
		</p>
		</form>
		
</section>


</body>
</html>