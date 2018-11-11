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
    <title>添加新同学</title>
    <%@include file="../common/header.jsp"%>
</head>
<body>


<div class="page__hd"  style="background:#778899;">
    <div class="weui-flex">
        <div class="weui-flex__item">
            <h1 class="page__title" style="color: #FFFFFF;text-align: center">添加新同学</h1>
        </div>
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

<div class="weui-gallery" id="gallery">
    <span class="weui-gallery__img" id="galleryImg"></span>
    <div class="weui-gallery__opr">
        <a href="javascript:" class="weui-gallery__del">
            <i class="weui-icon-delete weui-icon_gallery-delete delete"></i>
        </a>
    </div>
</div>

<div id="toast" style="display: none;">
    <div class="weui-mask_transparent"></div>
    <div class="weui-toast">
        <i class="weui-icon-success-no-circle weui-icon_toast"></i>
        <p class="weui-toast__content"></p>
    </div>
</div>

<form action="/student/add" method="post" enctype="multipart/form-data" onsubmit="return checkNull()">
<div class="weui-cells weui-cells_form">
    <div class="weui-cells__title">学生详细信息</div>
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">上传头像</label></div>
            <div class="weui-uploader">
                <div class="weui-uploader__bd">
                    <div id="icondiv" class="weui-cell__hd" style="position: relative;margin-right: 10px;" hidden>
                        <img  id="icon" class="weui-uploader__file" alt="头像"  style="width: 50px;display: block">
                        <span class="weui-badge delete" style="position: absolute;top: -.4em;right: -.4em;">x</span>
                    </div>
                    <div class="weui-uploader__input-box" id="uploadbox">
                        <input id="uploaderInput" class="weui-uploader__input" type="file" accept="image/*" multiple="" name="pictureFile">
                    </div>
                </div>
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
            <div class="weui-cell__bd weui-cell_primary"><input class="weui-input" type="text" placeholder="请输入姓名" name="username"></div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">学号</label></div>
            <div class="weui-cell__bd weui-cell_primary"><input class="weui-input" type="text" placeholder="请输入学号" name="studentId"></div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">性别</label></div>
            <div class="weui-cells weui-cells_radio">
                <label class="weui-cell weui-check__label" for="x11">
                    <div class="weui-cell__bd">
                        <p>男</p>
                    </div>
                    <div class="weui-cell__ft">
                        <input type="radio" class="weui-check" name="radio1" value="男" id="x11"/>
                        <span class="weui-icon-checked"></span>
                    </div>
                </label>
                <label class="weui-cell weui-check__label" for="x12">

                    <div class="weui-cell__bd">
                        <p>女</p>
                    </div>
                    <div class="weui-cell__ft">
                        <input type="radio" name="radio1" class="weui-check" id="x12" checked="checked" value="女"/>
                        <span class="weui-icon-checked"></span>
                    </div>
                </label>
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label class="weui-label">手机号</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="tel" placeholder="请输入手机号" name="phoneNum"/>
            </div>
        </div>
        <%--<div class="weui-cell weui-cell_select weui-cell_select-after">--%>
            <%--<div class="weui-cell__hd"><label class="weui-label">年级</label></div>--%>
            <%--<div class="weui-cell__bd">--%>
                <%--<select class="weui-select" name="select2">--%>
                    <%--<option value="1">一年级</option>--%>
                    <%--<option value="2">二年级</option>--%>
                    <%--<option value="3">三年级</option>--%>
                    <%--<option value="4">四年级</option>--%>
                    <%--<option value="5">五年级</option>--%>
                    <%--<option value="6">六年级</option>--%>
                <%--</select>--%>
            <%--</div>--%>
        <%--</div>--%>
    </div>

</div>
<br>
<%--<a href="javascript:;" class="weui-btn weui-btn_plain-primary">确认上传</a>--%>
    <input type = "submit" value = "确认上传" class="weui-btn weui-btn_plain-primary">
</div>
</form>

</body>

<script type="text/javascript">
    $(function() {
        var $gallery = $("#gallery"), $galleryImg = $("#galleryImg"),
            $uploaderInput = $("#uploaderInput"),
            $icon = $("#icon"),
            $icondiv = $("#icondiv"),
            $icondelete = $(".delete"),
            $uploadbox = $("#uploadbox"),
            $toast = $("#toast"),
            $content = $(".weui-toast__content"),
            $dialog = $("#dialog"),
            $dialogContent = $("#dialogContent")
        ;

        $uploaderInput.on("change", function(e){
            var src, url = window.URL || window.webkitURL || window.mozURL, files = e.target.files;
            for (var i = 0, len = files.length; i < len; ++i) {
                var file = files[i];

                if (url) {
                    src = url.createObjectURL(file);
                } else {
                    src = e.target.result;
                }
                $icon.attr('src', src);
                $icondiv.show();
                $uploadbox.hide();
            }
        });

        $icondelete.on("click", function () {
            $icon.attr('src', "");
            $icondiv.hide();
            $uploadbox.show();
        });


        $icon.on("click", function(){
            $galleryImg.attr("style", "background-image:url(" + $icon.attr("src")+")");
            $gallery.fadeIn(100);
        });

        $gallery.on("click", function(){
            $gallery.fadeOut(100);
        });

        checkNull = function () {
            if(!$("input[name='username']").val()){
                alert("请填写姓名");
                return false;
            }
            if(!$("input[name='studentId']").val()){
                alert("请填写学号");
                return false;
            }
            var phNum = $("input[name='phoneNum']").val();
            if(!(/^1[34578]\d{9}$/.test(phNum))){
                alert("电话填写有误");
                return false;
            }
            return true;
        };

        function hint(msg) {
            if ($toast.css('display') != 'none') return;
            $content.text(msg);
            $toast.fadeIn(100);
            setTimeout(function () {
                $toast.fadeOut(100);
            }, 2000);
        }

        var info = "${info}";
        if(info == "success"){
            hint("添加成功");
        }
        else if(info == 'exist'){
            alert("已经存在");
        }

        function alert(msg) {
            $dialogContent.text(msg);
            $dialog.fadeIn(100);
        }
        $dialog.on('click', '.weui-dialog__btn', function(){
            $(this).parents('.js_dialog').fadeOut(200);
        });
    });

</script>
</body>
</html>
