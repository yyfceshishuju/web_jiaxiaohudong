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
<div class="weui-cells weui-cells_form">

    <div class="weui-cells__title">账号注册</div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">上传头像</label></div>
        <div class="weui-uploader">
            <div class="weui-uploader__bd">
                <ul class="weui-uploader__files" id="uploaderFiles">
                    <li class="weui-uploader__file"></li></ul>
                <div class="weui-uploader__input-box">
                    <input id="uploaderInput" class="weui-uploader__input" type="file" accept="image/*" multiple="">
                </div>
            </div>
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
        <div class="weui-cell__bd weui-cell_primary"><input class="weui-input" type="text" placeholder="请输入姓名"></div>
    </div>
    <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
            <label class="weui-label">手机号</label>
        </div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="number" pattern="[0-11]*" placeholder="请输入手机号">

        </div>
        <div class="weui-cell__ft">
            <button class="weui-vcode-btn">获取验证码</button>
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">验证码</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="number" placeholder="请输入验证码">
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">密码</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="tel" placeholder="请输入密码">
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">确认密码</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="tel" placeholder="请再次输入密码">
        </div>
    </div>

</div>
<div class="weui-btn-area">
    <a href="" class="weui-btn weui-btn_primary">注册</a>
</div>

</body>
</html>
