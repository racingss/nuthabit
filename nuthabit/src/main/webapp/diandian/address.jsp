<%@page import="com.babycard.wx.*"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Dizhi d = null;
if(request.getAttribute("address")!=null)
	d = (Dizhi)request.getAttribute("address");
%>    
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
		.addressp{
			width:100%;
		}
		.addressspan{
			width: 15%;
    		display: inline-block;
    		text-align: left;
		}
		.addresstext{
			width: 80%;
    /* background: grey; */
    border: 1px solid grey;
    height: 1.8rem;
    margin-bottom: 0.3rem;
    padding: 0.5rem;
    font-size: 1rem;
        border-radius: 0.5rem;
		}
		.adbut{
			border: 1px solid green;
		    background: #99c32a;
		    border-radius: 20%;
		    padding: 0.2rem;
		    color: white;
		    font-size: 1rem;
		    margin: 0.2rem 0.5rem;
		}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=aBlZtcxhVDShaFmWX5n8B3dC"></script>
	<title>我的地址</title>
</head>
<body>
	<div id="allmap" style="height:40%;"></div>
	<div class="page parents">
		<div>
			<img class="bg" src="frame/parents-11.png">
			<div style="background: white;padding: .5rem;margin: 1rem;border-radius: 0.2rem;position: relative;z-index: 2;">
		
				<p class="addressp">
					<span class="addressspan">省:</span><input type="text" value="<%if(d!=null)out.print(d.getProvince()); %>" class="addresstext" id="province" name="province" />
				</p>
				<p class="addressp">
					<span class="addressspan">市:</span><input type="text" value="<%if(d!=null)out.print(d.getCity()); %>" class="addresstext" id="city" name="city" />
				</p>
				<p class="addressp">
					<span class="addressspan">区:</span><input type="text" value="<%if(d!=null)out.print(d.getQu()); %>" class="addresstext" id="district" name="district" />
				</p>
				<p class="addressp">
					<span class="addressspan">地址:</span><input type="text" value="<%if(d!=null)out.print(d.getXiangxi()); %>" class="addresstext" id="street" name="street" />
				</p>
				<p class="addressp">
					<span class="addressspan">姓名:</span><input type="text" value="<%if(d!=null)out.print(d.getShoujianren()); %>" class="addresstext" id="person" name="person" />
				</p>
				<p class="addressp">
					<span class="addressspan">手机:</span><input type="text" value="<%if(d!=null)out.print(d.getDianhua()); %>" class="addresstext" id="mobile" name="mobile" />
				</p>
				<p class="addressp" style="text-align:center;">
					<input type="submit" value="提交" class="adbut" id="submitcomm">
					<input type="button" value="返回" class="adbut" id="fanhuibut">
				</p>
				
			</div>
			
		</div>
		
		
		
		
	</div>
	
</body>
</html>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
//百度地图API功能
var map = new BMap.Map("allmap");
var point = new BMap.Point(116.331398,39.897445);
map.centerAndZoom(point,14);

function myFun(result){
	var cityName = result.name;
	map.setCenter(cityName);
	//alert("当前定位城市:"+cityName);
}
var myCity = new BMap.LocalCity();
myCity.get(myFun);


	
	map.centerAndZoom(point,14);
	var geoc = new BMap.Geocoder(); 
	
	map.addEventListener("click", function(e){        
		var pt = e.point;
		geoc.getLocation(pt, function(rs){
			var addComp = rs.addressComponents;
			document.getElementById('province').value=addComp.province;
			document.getElementById('city').value=addComp.city;
			document.getElementById('district').value=addComp.district;
			document.getElementById('street').value=addComp.street+addComp.streetNumber;
			alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
		});        
	});
	
	$(function(){	
		$('#fanhuibut').on("click", function () {
			location.href="/diandian/parents.html";
		});
		
		$('#submitcomm').on("click", function () {
			if(document.getElementById('mobile').value==null || document.getElementById('mobile').value.length<11){
				alert("请输入手机号码");
				return;
			}
			if(document.getElementById('person').value==null || document.getElementById('person').value.length<2){
				alert("请输入收件人姓名");
				return;
			}
			if(document.getElementById('street').value==null || document.getElementById('street').value.length<2){
				alert("请输入地址");
				return;
			}
			$.post("/diandian/address.html",
					  {
						province:document.getElementById('province').value,
						city:document.getElementById('city').value,
						district:document.getElementById('district').value,
						street:document.getElementById('street').value,
						person:document.getElementById('person').value,
						mobile:document.getElementById('mobile').value
					  },
					  function(data,status){
						  alert('提交成功');
						   
			 });
	    });
	})
</script>
    