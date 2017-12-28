//*******************************************
//2017年2月24日 0:05 由 向立凯 结束编写
//初步功能实现，仅做参考使用
//生产中使用需要继续完善，无js注入防御
//*******************************************


var IMG_LENGTH = 10;//图片最大1MB
var IMG_MAXCOUNT = 3;//最多选中图片张数
var IMG_AJAXPATH = "experience.html";//异步传输服务端位置


var UP_IMGCOUNT = 0;//上传图片张数记录
function winload(){ 
    ld.style.display="none"; 
    f8=true; 
} 

//打开文件选择对话框
$("#div_imgfile").click(function () {
    if ($(".lookimg").length >= IMG_MAXCOUNT) {
        alert("一次最多上传" + IMG_MAXCOUNT + "张图片");
        return;
    }

    var _CRE_FILE = document.createElement("input");
    if ($(".imgfile").length <= $(".lookimg").length) {//个数不足则新创建对象
        _CRE_FILE.setAttribute("type", "file");
        _CRE_FILE.setAttribute("class", "imgfile");
        //_CRE_FILE.setAttribute("accept", ".png,.jpg,.jpeg");
        _CRE_FILE.setAttribute("num", UP_IMGCOUNT);//记录此对象对应的编号
        $("#div_imgfile").after(_CRE_FILE);
    }
    else { //否则获取最后未使用对象
        _CRE_FILE = $(".imgfile").eq(0).get(0);
    }
    return $(_CRE_FILE).click();//打开对象选择框
});
//创建预览图，在动态创建的file元素onchange事件中处理
//$(".imgfile").on("change",null, function () {
$(".imgfile").live("change", function () {
    if ($(this).val().length > 0) {//判断是否有选中图片

        //判断图片格式是否正确
        var FORMAT = $(this).val().substr($(this).val().length - 3, 3);
        if (FORMAT != "png" && FORMAT != "jpg" && FORMAT != "peg"  && FORMAT != "JPG" && FORMAT != "PNG" && FORMAT != "PEG" && FORMAT != "JPEG" && FORMAT != "jpeg" && FORMAT != "gif" && FORMAT != "GIF") {
            alert("文件格式不正确！！！");
            return;
        }

        //判断图片是否过大，当前设置1MB
        var file = this.files[0];//获取file文件对象
        if (file.size > (IMG_LENGTH * 1024 * 1024)) {
            alert("图片大小不能超过" + IMG_LENGTH + "MB");
            $(this).val("");
            return;
        }
        //创建预览外层
        var _prevdiv = document.createElement("div");
        _prevdiv.setAttribute("class", "lookimg");
        //创建内层img对象
        var preview = document.createElement("img");
        $(_prevdiv).append(preview);
        //创建删除按钮
        var IMG_DELBTN = document.createElement("div");
        IMG_DELBTN.setAttribute("class", "lookimg_delBtn");
        IMG_DELBTN.innerHTML = "移除";
        $(_prevdiv).append(IMG_DELBTN);
        //创建进度条
        var IMG_PROGRESS = document.createElement("div");
        IMG_PROGRESS.setAttribute("class", "lookimg_progress");
        $(IMG_PROGRESS).append(document.createElement("div"));
        $(_prevdiv).append(IMG_PROGRESS);
        //记录此对象对应编号
        _prevdiv.setAttribute("num", $(this).attr("num"));
        //对象注入界面
        $("#div_imglook").children("div:last").before(_prevdiv);
        UP_IMGCOUNT++;//编号增长防重复

        //预览功能 start
        var reader = new FileReader();//创建读取对象
        reader.onloadend = function () {
            preview.src = reader.result;//读取加载，将图片编码绑定到元素
        }
        if (file) {//如果对象正确
            reader.readAsDataURL(file);//获取图片编码
        } else {
            preview.src = "";//返回空值
        }
        //预览功能 end
    }
});

//删除选中图片
$(".lookimg_delBtn").live("click", function () {
    $(".imgfile[num=" + $(this).parent().attr("num") + "]").remove();//移除图片file
    $(this).parent().remove();//移除图片显示
});

//删除按钮移入移出效果
$(".lookimg").live("mouseover", function () {
    if ($(this).attr("ISUP") != "1")
        ;
});
$(".lookimg").live("mouseout", function () {
    ;
});

//确定上传按钮
$("#signin").click(function () {

    //if ($(".lookimg").length <= 0) {
        //alert("还未选择需要上传的图片");
        //return;
    //}

    //全部图片上传完毕限制
    //if ($(".lookimg[ISUP=1]").length == $(".lookimg").length) {
        //alert("图片已全部上传完毕！");
        //return;
    //}
    
    var data = new FormData();
    data.append("processadd","t");
    data.append("id",$("#planId").attr("value"));
    data.append("review",$("#review").val());
    if($(".imgfile").get(0)!=null){
    var files = $(".imgfile").get(0).files;
	    for(i=0;i<=2;i++){
	    	if($(".imgfile").get(i)!=null){
	    		data.append(i.toString(), $(".imgfile").get(i).files[0]);
	    	}
	    }
    }
    
    try {rval.remove(); rval='';}catch(e) {rval=getBusyOverlay(this,{color:'white', opacity:0.5},{color:'#00f', size:32, type:'c', iradius:10, weight:6});}
    
    $.ajax({
        type: "POST",
        url: IMG_AJAXPATH,
        contentType: false,  
        cache: false,  
        currentType: false,  
        processData: false,  
        data: data, 
        success: function (data) {
            //alert("发布成功");
            window.location.href='detail.html?planId='+$("#planId").attr("value");
            return;
        },
        error: function (err) {
            //服务器连接失败报错处理
            alert("error");
            //alert(err.responseText);
        },
        beforeSend: function () {
            //图片上传之前执行的操作，当前为进度条显示
            //NOWLOOK.children(".lookimg_progress").eq(0).css("display", "block");//进度条显示
        }
    });

});


//确定上传按钮
$("#pubPlan").click(function () {

    var data = new FormData();
    var files = $("#pic").get(0).files;
    data.append("processadd","t");
    data.append("title",$("#title").attr("value"));
    data.append("times",$("#times").attr("value"));
    data.append("discription",$("#discription").val());
    for(i=0;i<=2;i++){
    	if($("#pic").get(i)!=null){
    		data.append(i.toString(), $("#pic").get(i).files[0]);
    	}
    }
    
    try {rval.remove(); rval='';}catch(e) {rval=getBusyOverlay(this,{color:'white', opacity:0.5},{color:'#00f', size:32, type:'c', iradius:10, weight:6});}
    
    $.ajax({
        type: "POST",
        url: "addplan.html",
        contentType: false,  
        cache: false,  
        currentType: false,  
        processData: false,  
        data: data, 
        success: function (data) {
            //alert("发布成功");
            window.location.href='index.html';
            return;
        },
        error: function (err) {
            //服务器连接失败报错处理
            alert("error");
            //alert(err.responseText);
        },
        beforeSend: function () {
            //图片上传之前执行的操作，当前为进度条显示
            //NOWLOOK.children(".lookimg_progress").eq(0).css("display", "block");//进度条显示
        }
    });
    
});