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
    <title>手机注册</title>
    <%@include file="common/header.jsp"%>
</head>
<body>

<div class="page__hd"  style="background:#778899;">
    <div class="weui-flex" style="text-align: left ;width: 190px">
        <div class="weui-flex__item">

            <img class="circleImg" src="/img/logo.png"  />
        </div>
        <div class="weui-flex__item">
            <h1 class="page__title" style="color: #FFFFFF;text-align: left;font-size: 25px;width: 110px">家校社区</h1>
        </div>
    </div>

</div>

<div class="weui-gallery" id="gallery">
    <span class="weui-gallery__img" id="galleryImg"></span>
    <div class="weui-gallery__opr">
        <a href="javascript:" class="weui-gallery__del">
            <i class="weui-icon-delete weui-icon_gallery-delete"></i>
        </a>
    </div>
</div>


<form id="form"  enctype="multipart/form-data" method="post">

    <div class="weui-cells weui-cells_form">
    <div class="weui-cells__title">账号注册</div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">上传头像</label></div>
            <div class="weui-uploader">
                <div class="weui-uploader__bd">
                    <img class="weui-uploader__file" alt="头像" hidden>
                    <div class="weui-uploader__input-box" id="uploadbox">
                        <input id="uploaderInput" name="uploaderInput" class="weui-uploader__input" type="file" accept="image/*" multiple="" >
                    </div>
                </div>
            </div>
        </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
        <div class="weui-cell__bd weui-cell_primary"><input id="name" name="name"  class="weui-input" type="text" placeholder="请输入姓名"></div>
    </div>

    <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
            <label class="weui-label">手机号</label>
        </div>
        <div class="weui-cell__bd">
            <input id="phone" name="phone" class="weui-input" type="number" pattern="[0-11]*" placeholder="请输入手机号">

        </div>
        <div class="weui-cell__ft">
            <button id="codeButton" class="weui-vcode-btn" type="button">获取验证码</button>
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">验证码</label></div>
        <div class="weui-cell__bd">
            <input id="code" name="code" class="weui-input" type="number" placeholder="请输入验证码">
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">密码</label></div>
        <div class="weui-cell__bd">
            <input  id="password" name="password" class="weui-input" type="tel" placeholder="请输入密码">
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">确认密码</label></div>
        <div class="weui-cell__bd">
            <input id="pw" name="pw" class="weui-input" type="tel" placeholder="请再次输入密码" >
        </div>
    </div>

</div>
<div class="weui-btn-area">
    <button type="button" id="submit" class="weui-btn weui-btn_primary">注册</button>
</div>


</form>
<script type="text/javascript">
    $(function(){
        var tmpl = '<li class="weui-uploader__file" style="background-image:url(#url#)"></li>',
            $gallery = $("#gallery"), $galleryImg = $("#galleryImg"),
            $uploaderInput = $("#uploaderInput"),
            $uploaderFiles = $("#uploaderFiles"),
            $pw = $("#pw"),
            $password = $("#password"),
            $dialog = $("#dialog"),
            $codeButton = $("#codeButton"),
            $phone = $("#phone"),
            $submit = $("#submit"),
            $form = $("#form"),
            $countdown = 60,

            registerUrl = "/user/register",
            sendUrl = "/wechat/send",
            loginUrl = "/login.do"

        ;
        $submit.on("click", function () {
            var form = new FormData(document.getElementById('form'));
            $.ajax({
                url:registerUrl,
                type:"post",
                data:form,
                processData:false,
                contentType:false,
                success:function(data){
                    if (data.code != 0){
                        alert(data.msg);
                    } else {
                        alert(data.msg);
                        window.location.href(loginUrl);
                    }

                },
                error:function(e){
                    alert("未知错误");
                }
            });
        });
        $uploaderInput.on("change", function(e){
            var src, url = window.URL || window.webkitURL || window.mozURL, files = e.target.files;
            for (var i = 0, len = files.length; i < len; ++i) {
                var file = files[i];

                if (url) {
                    src = url.createObjectURL(file);
                } else {
                    src = e.target.result;
                }

                $uploaderFiles.append($(tmpl.replace('#url#', src)));
                $uploaderInput.hide();
            }
        });
        $uploaderFiles.on("click", "li", function(){
            $galleryImg.attr("style", this.getAttribute("style"));
            $gallery.fadeIn(100);
        });
        $gallery.on("click", function(){
            $gallery.fadeOut(100);
        });
        $pw.on("change", function () {
            if ($pw.val() != $password.val()){
                $('#password').val('');
                $('#pw').val('');
                alert("两次密码输入不一致");
            }
        });
        $codeButton.on("click", function () {
           var phone = $phone.val();
            $.ajax({
                url: sendUrl,
                method: 'get',
                data: {"phone": phone},
                success: function () {
                    alert("发送成功");
                    settime();
                },
                error: function () {
                    alert("发送失败")
                }
            })
        });
        function settime() {
            if ($countdown == 0) {
                $("#codeButton").attr("disabled","false");
                $("#codeButton").text("获取验证码");
                $countdown = 60;
                return false;
            } else {
                console.log($countdown);
                $("#codeButton").attr("disabled", "true");
                $("#codeButton").text("重新发送(" + $countdown + ")");
                $countdown--;
            }
            setTimeout(function() {
                settime();
            },1000);
        }
    });

</script>

</body>
</html>
