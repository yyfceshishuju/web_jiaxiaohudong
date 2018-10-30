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
    <title>绑定同学</title>
    <%@include file="common/header.jsp"%>
</head>
<body>

<div class="page__hd"  style="background:#778899;">
    <div class="weui-flex">
        <div class="weui-flex__item">
            <h1 class="page__title" style="color: #FFFFFF;text-align: center">绑定同学</h1>
        </div>
    </div>
</div>

<form action="/bind" method="post" enctype="multipart/form-data">
<div class="weui-cells weui-cells_form">
    <div class="weui-cells__title">开始绑定</div>
    <div class="weui-cells weui-cells_form">

        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">学号</label></div>
            <div class="weui-cell__bd weui-cell_primary"><input class="weui-input" type="text" placeholder="请输入学号" name="studentId"></div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label class="weui-label">手机号</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="tel" placeholder="请输入手机号" name="phoneNum"/>
            </div>
        </div>

    </div>

</div>
<br>
<%--<a href="javascript:;" class="weui-btn weui-btn_plain-primary">开始绑定</a>--%>
    <input type = "submit" value = "开始绑定" class="weui-btn weui-btn_plain-primary">
</div>
</form>


</body>
</html>
