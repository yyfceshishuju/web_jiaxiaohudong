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
    <title>个人中心</title>
    <%@include file="common/header.jsp"%>
</head>
<body>
<div class="page__hd"  style="background:#778899;">
    <div class="weui-flex">
        <div class="weui-flex__item">
            <h1 class="page__title" style="color: #FFFFFF;text-align: center">个人中心</h1>
        </div>
    </div>
</div>
<div class="weui-cells">
    <a class="weui-cell weui-cell_access" href="javascript:;">
        <div class="weui-cell__bd">
            <p>头像</p>
        </div>
        <div class="weui-cell__ft">
            <input id="uploaderInput" class="weui-uploader__input" type="file" accept="image/*"  multiple="">
            <img class="circleImg" src="/img/logo.png"  />
        </div>
    </a>
    <a class="weui-cell weui-cell_access" href="javascript:;">
        <div class="weui-cell__bd">
            <p>名字</p>
        </div>
        <div class="weui-cell__ft">小明的爸爸</div>
    </a>

    <a class="weui-cell weui-cell_access" href="javascript:;">
        <div class="weui-cell__bd">
            <p>老师</p>
        </div>
        <div class="weui-cell__ft">小名的老师</div>
    </a>

    <a class="weui-cell weui-cell_access" href="javascript:;">
        <div class="weui-cell__bd">
            <p>身份</p>
        </div>
        <div class="weui-cell__ft">家长</div>
    </a>
    <a class="weui-cell weui-cell_access" href="javascript:;">
        <div class="weui-cell__bd">
            <p>修改密码</p>
        </div>
    </a>
</div>
<a href="javascript:;" class="weui-btn weui-btn_plain-primary">退出登录</a>
</body>
</html>
