<%--
  Created by IntelliJ IDEA.
  User: lcf12307
  Date: 2018/10/27
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <title>错题上传</title>
    <%@include file="common/header.jsp"%>
    <style type="text/css">
        img[src=""]{
            opacity: 0;
        }
    </style>
</head>
<body>
<div class="page__hd"  style="background:#778899;">
    <div class="weui-flex">
        <div class="weui-flex__item">
            <h1 class="page__title" style="color: #FFFFFF;text-align: center">错题上传</h1>
        </div>
    </div>
</div>

<div class="weui-gallery" id="gallery">
    <span class="weui-gallery__img" id="galleryImg"></span>
    <div class="weui-gallery__opr">
        <a href="javascript:" class="weui-gallery__del">
            <i class="weui-icon-delete weui-icon_gallery-delete delete"></i>
        </a>
    </div>
</div>

<div class="js_dialog" id="dialog" style="display: none;">
    <div class="weui-mask"></div>
    <div class="weui-dialog">
        <div class="weui-dialog__bd" id="dialogContent"></div>
        <div class="weui-dialog__ft">
            <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary">知道了</a>
        </div>
    </div>
</div>
<div id="toast" style="display: none;">
    <div class="weui-mask_transparent"></div>
    <div class="weui-toast">
        <i class="weui-icon-success-no-circle weui-icon_toast"></i>
        <p class="weui-toast__content"></p>
    </div>
</div>
<div id="students" class="weui-cells">

    <div class="weui-search-bar" id="searchBar" style="height: 46px">
        <form class="weui-search-bar__form">
            <div class="weui-search-bar__box">
                <i class="weui-icon-search"></i>
                <input type="search" class="weui-search-bar__input"  id="searchInput" placeholder="搜索" required/>
                <a href="javascript:" class="weui-icon-clear" id="searchClear"></a>
            </div>
            <label class="weui-search-bar__label" id="searchText">
                <i class="weui-icon-search"></i>
                <span>搜索</span>
            </label>
        </form>
        <a href="javascript:" class="weui-search-bar__cancel-btn" id="searchCancel">取消</a>
    </div>
    <br>
    <div class="weui-cells__title">请先选择一个同学</div>
    <div id="list" class="weui-grids"></div>
</div>
<div id="upload" class="weui-cells" hidden>
    <ul>
        <li>

            <div class="weui-cells__title">
                <button type="button" id="return" class="weui-btn weui-btn_mini weui-btn_default"> 重新选择学生</button>
            </div>
            <form id="uploadForm" method="post" enctype="multipart/form-data">
                <div class="weui-cells__title">上传题目</div>
                <div class="weui-uploader">
                    <div class="weui-uploader__bd">
                        <ul class="weui-uploader__files" id="uploaderFiles">
                            <li class="weui-uploader__file"></li></ul>
                        <div class="weui-uploader__input-box">
                            <input id="file" name="file" class="weui-uploader__input" type="file" accept="image/*" multiple="">
                        </div>
                    </div>
                </div>
            </form>

        </li>
        <li>
          <form id="questionForm"  method="post" >
              <div class="weui-cells__title">题目相关信息</div>
              <div class="weui-cells weui-cells_form">
                  <div class="weui-cell">
                      <div class="weui-cell__hd"><label class="weui-label">标题</label></div>
                      <div class="weui-cell__bd">
                          <input name="name" class="weui-input" type="text" placeholder="请输入错题题目"/>
                      </div>
                  </div>
                  <div class="weui-cell weui-cell_select weui-cell_select-after">
                      <div class="weui-cell__hd"><label class="weui-label">分类</label></div>
                      <div class="weui-cell__bd">
                          <select class="weui-select" name="cid" id="category">
                          </select>
                      </div>
                  </div>
                  <div class="weui-cell">
                      <div class="weui-cell__hd"><label class="weui-label">题目</label></div>
                      <div class="weui-cell__bd">
                          <textarea class="weui-textarea" placeholder="" rows="3" id="question" name="question" ></textarea>
                          <div class="img-show" style="height: 100px;width: 150px;overflow: hidden">
                              <img id="question1" src=""/>
                          </div>
                          <div class="weui-textarea-counter"><span>0</span>/200</div>
                      </div>
                  </div>
                  <input type="hidden" id="student" name="sid" value="2" ／>
              </div>
          </form>

        </li>
    </ul>
    <br>
    <button type="button" id="confirm" class="weui-btn weui-btn_plain-primary">确认上传</button>

</div>
</div>


<script type="text/javascript">
    $(function(){
        var $gallery = $("#gallery"), $galleryImg = $("#galleryImg"),
            $searchBar = $('#searchBar'),
            $searchText = $('#searchText'),
            $searchInput = $('#searchInput'),
            $searchClear = $('#searchClear'),
            $searchCancel = $('#searchCancel'),

            $students = $("#students"),
            $upload = $("#upload"),
            $question = $("#question"),
            $question1 = $("#question1"),
            $file = $("#file"),
            $category = $("#category"),
            $confirm = $("#confirm"),
            $student = $("#student"),
            $return = $("#return"),
            $toast = $("#toast"),
            $content = $(".weui-toast__content"),
            $dialog = $("#dialog"),
            $dialogContent = $("#dialogContent"),
            $list = $("#list"),
            uploadUrl = "/image/uploadImage",
            questionUrl = "/question/add",
            categoryUrl = "/teacher/scoreCategory?type=0",
            studentUrl = "/student/list",
            searchUrl = "/student/search"
        ;
        $question.hide();
        reloadStudents(studentUrl);
        $.ajax({
            url: categoryUrl,
            method: "get",
            success: function (data) {
                data = data.data;
                for (var i=0;i<data.length;i++){
                    $category.append('<option value="'+ data[i].id+'">' + data[i].name + '</option>');
                }
            }
        });
        function photoCompress(file,w,objDiv){
            var ready=new FileReader();
            /*开始读取指定的Blob对象或File对象中的内容. 当读取操作完成时,readyState属性的值会成为DONE,如果设置了onloadend事件处理程序,则调用之.同时,result属性中将包含一个data: URL格式的字符串以表示所读取文件的内容.*/
            ready.readAsDataURL(file);
            ready.onload=function(){
                var re=this.result;
                canvasDataURL(re,w,objDiv)
            }
        }
        function canvasDataURL(path, obj, callback){
            var img = new Image();
            img.src = path;
            img.onload = function(){
                var that = this;
                // 默认按比例压缩
                var w = that.width,
                    h = that.height,
                    scale = w / h;
                w = obj.width || w;
                h = obj.height || (w / scale);
                var quality = 0.07;  // 默认图片质量为0.7
                //生成canvas
                var canvas = document.createElement('canvas');
                var ctx = canvas.getContext('2d');
                // 创建属性节点
                var anw = document.createAttribute("width");
                anw.nodeValue = w;
                var anh = document.createAttribute("height");
                anh.nodeValue = h;
                canvas.setAttributeNode(anw);
                canvas.setAttributeNode(anh);
                ctx.drawImage(that, 0, 0, w, h);
                // 图像质量
                if(obj.quality && obj.quality <= 1 && obj.quality > 0){
                    quality = obj.quality;
                }
                // quality值越小，所绘制出的图像越模糊
                var base64 = canvas.toDataURL('image/jpeg', 0.0005);
                // 回调函数返回base64的值
                callback(base64);
            }
        }
        /**
         *       用url方式表示的base64图片数据
         */
        function convertBase64UrlToBlob(urlData){
            var arr = urlData.split(','), mime = arr[0].match(/:(.*?);/)[1],
                bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
            while(n--){
                u8arr[n] = bstr.charCodeAt(n);
            }
            return new Blob([u8arr], {type:mime});
        }
        var bl="",fileObj;
        function filess(e){
            fileObj = document.getElementById("file").files[0]; // js 获取文件对象
            console.log("压缩前：" + fileObj.size / 1024 + "k");
            if(fileObj.size/1024 > 100) { //大于1M，进行压缩上传
                photoCompress(fileObj, {
                    quality: 0.01
                }, function(base64Codes){
                    console.log("压缩后：" + base64Codes.length / 1024 + "k");
                    bl = convertBase64UrlToBlob(base64Codes);
                    console.log(bl);

                });

            }
        }
        $file.on("change", function(){
            filess($file)
            var params = new FormData();
            if (bl=="") {
                params.append("file",fileObj);
            }else{
                params.append("file",bl,"file_"+Date.parse(new Date())+".jpg");
            }

            $.ajax({
                url:uploadUrl,
                type:"post",
                data:params,
                processData:false,
                contentType:false,
                success:function(data){
                    ind = data.msg.indexOf("/upload");
                    png_src = data.msg.substring(ind);
                    console.log(png_src);
                    $question.text(data.msg);
                    $question1.attr('src', png_src);
                },
                error:function(e){
                    alert(e);
                }
            });
        });

        $question1.on("click", function(){
            $galleryImg.attr("style", "background-image:url(" + $question1.attr("src")+")");
            $gallery.fadeIn(100);
        });

        $gallery.on("click", function(){
            $gallery.fadeOut(100);
        });

        $list.on("click", ".weui-grid", function () {

            $student.val($(this).find(".id").val());
            $students.hide();
            $upload.show();
        });
        $return.on("click", function(){
            $student.val('');
            $students.show();
            $upload.hide();
        });

        $confirm.on("click", function () {
            console.log($question.val());
            if ($question.val() === ""){
                alert("请先上传图片");
                return;
            }
            var form = new FormData(document.getElementById('questionForm'));
            $.ajax({
                url:questionUrl,
                type:"post",
                data:form,
                processData:false,
                contentType:false,
                success:function(data){
                    if (data.code != 0){
                        alert(data.msg);
                    } else {
                        hint(data.msg);
                        $student.val('');
                        $("input[name='name']").val("");
                        // $students.show();
                        // $upload.hide();
                        $question.val("");
                        $question1.attr("src", "");
                    }
                },
                error:function(error){
                    alert("error:"+error);
                }

            })
        });
        $searchText.on('click', function(){
            $searchBar.addClass('weui-search-bar_focusing');
            $searchInput.focus();
        });
        $searchInput
            .on('blur', function () {
                if(!this.value.length) cancelSearch();
            })
            .on('input', function(){
                if(this.value.length) {
                    //展示搜索结果
                    var url = searchUrl + '?name='+this.value;
                    reloadStudents(url)
                } else {
                    //展示默认结果
                    reloadStudents(studentUrl)
                }
            })
        ;
        $searchClear.on('click', function(){
            //展示默认结果
            reloadStudents(studentUrl);
            $searchInput.val('');
            $searchInput.focus();
        });
        $searchCancel.on('click', function(){
            cancelSearch();
            $searchInput.blur();
        });
        function reloadStudents(url) {
            $.ajax({
                url: url,
                method: "get",
                success: function (data) {
                    data = data.data;
                    $list.empty();
                    for (var i=0; i<data.length; i++){
                        var status = data[i].pid==null || data[i].pid==0?"<p style='color: #FF0000'>未绑定家长</p>":"<p style='color: #00CD00'>已绑定家长</p>";
                        $list.append('<a class="weui-grid" href="javascript:;">' +
                            '        <div class="weui-grid__icon">' +
                            '            <img class="circleImg" src="'+ data[i].icon +'"  />' +
                            '        </div>' +
                            '        <p class="weui-grid__label">'+data[i].name+'</p>' +
                            '        <input class="id" hidden value="' +data[i].id+ '">' +
                            '    </a>');
                    }

                }
            });

        }
        function cancelSearch(){
            //展示默认结果
            reloadStudents(studentUrl);
            $searchBar.removeClass('weui-search-bar_focusing');
            $searchText.show();
        }

        function hint(msg) {
            if ($toast.css('display') != 'none') return;
            $content.text(msg);
            $toast.fadeIn(100);
            setTimeout(function () {
                $toast.fadeOut(100);
            }, 2000);
        }
        function alert(msg) {
            $dialogContent.text(msg);
            $dialog.fadeIn(100);
        }
    });

</script>



</body>
</html>
