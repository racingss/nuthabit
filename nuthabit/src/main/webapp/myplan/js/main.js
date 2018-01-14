!function(N,M){function L(){var a=I.getBoundingClientRect().width;a/F>540&&(a=540*F);var d=a/10;I.style.fontSize=d+"px",D.rem=N.rem=d}var K,J=N.document,I=J.documentElement,H=J.querySelector('meta[name="viewport"]'),G=J.querySelector('meta[name="flexible"]'),F=0,E=0,D=M.flexible||(M.flexible={});if(H){console.warn("将根据已有的meta标签来设置缩放比例");var C=H.getAttribute("content").match(/initial\-scale=([\d\.]+)/);C&&(E=parseFloat(C[1]),F=parseInt(1/E))}else{if(G){var B=G.getAttribute("content");if(B){var A=B.match(/initial\-dpr=([\d\.]+)/),z=B.match(/maximum\-dpr=([\d\.]+)/);A&&(F=parseFloat(A[1]),E=parseFloat((1/F).toFixed(2))),z&&(F=parseFloat(z[1]),E=parseFloat((1/F).toFixed(2)))}}}if(!F&&!E){var y=N.navigator.userAgent,x=(!!y.match(/android/gi),!!y.match(/iphone/gi)),w=x&&!!y.match(/OS 9_3/),v=N.devicePixelRatio;F=x&&!w?v>=3&&(!F||F>=3)?3:v>=2&&(!F||F>=2)?2:1:1,E=1/F}if(I.setAttribute("data-dpr",F),!H){if(H=J.createElement("meta"),H.setAttribute("name","viewport"),H.setAttribute("content","initial-scale="+E+", maximum-scale="+E+", minimum-scale="+E+", user-scalable=no"),I.firstElementChild){I.firstElementChild.appendChild(H)}else{var u=J.createElement("div");u.appendChild(H),J.write(u.innerHTML)}}N.addEventListener("resize",function(){clearTimeout(K),K=setTimeout(L,300)},!1),N.addEventListener("pageshow",function(b){b.persisted&&(clearTimeout(K),K=setTimeout(L,300))},!1),"complete"===J.readyState?J.body.style.fontSize=12*F+"px":J.addEventListener("DOMContentLoaded",function(){J.body.style.fontSize=12*F+"px"},!1),L(),D.dpr=N.dpr=F,D.refreshRem=L,D.rem2px=function(d){var c=parseFloat(d)*this.rem;return"string"==typeof d&&d.match(/rem$/)&&(c+="px"),c},D.px2rem=function(d){var c=parseFloat(d)/this.rem;return"string"==typeof d&&d.match(/px$/)&&(c+="rem"),c}}(window,window.lib||(window.lib={}));
$(function(){
    frame();
    clockIn();
    checkBox();
    switchBox();
    navSet();
    daka();

})

function frame(){
    //$("body").height($(window).height());
}
function clockIn() {
    $(".todayPlan .item .btn").click(function(){
        $(this).addClass("active")
    })
}
function switchBox(){
    $(".switchBox").click(function(){
        if($(this).hasClass("active")){
            $(this).removeClass("active");
            $(this).children("input").attr("value","0");
            if(!$(this).hasClass("hasson")){
                $(this).addClass("hasson");
                $(this).parent().next(".bd").hide();
            }
        }else{
            $(this).addClass("active");
            $(this).children("input").attr("value","1");
            if($(this).hasClass("hasson")){
                $(this).removeClass("hasson");
                $(this).parent().next(".bd").show();
            }
        }        
    })

}

function daka() {
    $(".daka").click(function(){
        $(".popup1").show();
    })
    $(".popup1 .close").click(function(){
        $(".popup1").hide();
    })
}

function checkBox(){
    $(".checkBox li").click(function(){
        var i = $(this).children("a").text();
        if($(this).hasClass("active")){

        }else {
            $(this).parent().children("li").removeClass("active");
            $(this).addClass("active");
            $(this).parent().children("input").attr("value",i);
        }
    })
}

function navSet() {
    $("nav .setting").click(function(){
        if($(this).hasClass("active")){
            $(".setBox").hide();
            $(this).removeClass("active")
        }else {
            $(".setBox").show();
            $(this).addClass("active")
        }
    })
}


var imgurl = "";  
function getPhoto(node) {  
    var imgURL = "";  
    try{  
        var file = null;  
        if(node.files && node.files[0] ){  
            file = node.files[0];  
        }else if(node.files && node.files.item(0)) {  
            file = node.files.item(0);  
        }  
        //Firefox 因安全性问题已无法直接通过input[file].value 获取完整的文件路径  
        try{  
            imgURL =  file.getAsDataURL();  
        }catch(e){  
            imgRUL = window.URL.createObjectURL(file);  
        }  
    }catch(e){  
        if (node.files && node.files[0]) {  
            var reader = new FileReader();  
            reader.onload = function (e) {  
                imgURL = e.target.result;  
            };  
            reader.readAsDataURL(node.files[0]);  
        }  
    }  
    $(".jlImg").text(imgRUL);
    return imgURL;  
}  

function creatImg(imgRUL){  
    var textHtml = "<img src='"+imgRUL+"'width='414px' height='600px'/>";  
    $(".ge_pic_icon_Infor").html(textHtml);  
}  





function dateBox() {
          new DateSelector({
        input: 'date-selector-input',//点击触发插件的input框的id
        container: 'targetContainer',//插件插入的容器id
        type: 0,
        //0：不需要tab切换，自定义滑动内容，建议小于三个；
        //1：需要tab切换，【年月日】【时分】完全展示，固定死，可设置开始年份和结束年份
        param: [1, 1, 1, 0, 0],
        //设置['year','month','day','hour','minute'],1为需要，0为不需要,需要连续的1
        beginTime: [],//如空数组默认设置成1970年1月1日0时0分开始，如需要设置开始时间点，数组的值对应param参数的对应值。
        endTime: [],//如空数组默认设置成次年12月31日23时59分结束，如需要设置结束时间点，数组的值对应param参数的对应值。
        recentTime: [],//如不需要设置当前时间，被为空数组，如需要设置的开始的时间点，数组的值对应param参数的对应值。
        success: function (arr) {
            var i =arr[0] +"-"+arr[1]+"-"+arr[2];
            $("#date-selector-input").css("color","#888888").attr("value",i);
        }//回调
      });

      new DateSelector({
        input: 'date-selector-inputs',//点击触发插件的input框的id
        container: 'targetContainers',//插件插入的容器id
        type: 0,
        //0：不需要tab切换，自定义滑动内容，建议小于三个；
        //1：需要tab切换，【年月日】【时分】完全展示，固定死，可设置开始年份和结束年份
        param: [1, 1, 1, 0, 0],
        //设置['year','month','day','hour','minute'],1为需要，0为不需要,需要连续的1
        beginTime: [],//如空数组默认设置成1970年1月1日0时0分开始，如需要设置开始时间点，数组的值对应param参数的对应值。
        endTime: [],//如空数组默认设置成次年12月31日23时59分结束，如需要设置结束时间点，数组的值对应param参数的对应值。
        recentTime: [],//如不需要设置当前时间，被为空数组，如需要设置的开始的时间点，数组的值对应param参数的对应值。
        success: function (arr) {
            var i =arr[0] +"-"+arr[1]+"-"+arr[2];
            $("#date-selector-inputs").css("color","#888888").attr("value",i);
        }//回调
      });

}


// JavaScript Document
(function($){
    $.fn.myScroll = function(options){
    //榛樿閰嶇疆
    var defaults = {
        speed:40,  //婊氬姩閫熷害,鍊艰秺澶ч€熷害瓒婃參
        rowHeight:24 //姣忚鐨勯珮搴�
    };
    
    var opts = $.extend({}, defaults, options),intId = [];
    
    function marquee(obj, step){
    
        obj.find("ul").animate({
            marginTop: '-=1'
        },0,function(){
                var s = Math.abs(parseInt($(this).css("margin-top")));
                if(s >= step){
                    $(this).find("li").slice(0, 1).appendTo($(this));
                    $(this).css("margin-top", 0);
                }
            });
        }
        
        this.each(function(i){
            var sh = opts["rowHeight"],speed = opts["speed"],_this = $(this);
            intId[i] = setInterval(function(){
                if(_this.find("ul").height()<=_this.height()){
                    clearInterval(intId[i]);
                }else{
                    marquee(_this, sh);
                }
            }, speed);

            _this.hover(function(){
                clearInterval(intId[i]);
            },function(){
                intId[i] = setInterval(function(){
                    if(_this.find("ul").height()<=_this.height()){
                        clearInterval(intId[i]);
                    }else{
                        marquee(_this, sh);
                    }
                }, speed);
            });
        
        });

    }

})(jQuery);