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
    <title>家校社区</title>
    <%@include file="common/header.jsp"%>
    <style>
        body{
            width: 100%;
            height: 100%;
            background: url("/img/welcome.jpg") no-repeat;
            background-size: 100% 100%;
        }
    </style>
</head>
<body>

<div class="page__bd page__bd_spacing">
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">账号</label></div>
            <div class="weui-cell__bd weui-cell_primary"><input type="cell" class="weui-input" placeholder="请输入手机号"/></div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">密码</label></div>
            <div class="weui-cell__bd weui-cell_primary"><input type="password" class="weui-input" placeholder="请输入密码"/></div>
        </div>
        <div class="weui-btn-area">
            <a href="" class="weui-btn weui-btn_primary">登录</a>
        </div>
        <a href="/register.do">
            <div class="weui-cells__tips">注册新账号</div>
        </a>
        <div class="weui-loadmore weui-loadmore_line">
            <span class="weui-loadmore__tips">使用其他方式登录</span><br>
            <a>
                <img src="/img/wechat.png" class="circleImg" alt="微信登录" align="center" width="50" height="50">
            </a>
        </div>
        <div class="weui-cell">
        </div>
    </div>

</div>


</body>
</html>
