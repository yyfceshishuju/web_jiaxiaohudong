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

<form action="/student/add" method="post" enctype="multipart/form-data">
<div class="weui-cells weui-cells_form">
    <div class="weui-cells__title">学生详细信息</div>
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">上传头像</label></div>
            <div class="weui-uploader">
                <div class="weui-uploader__bd">
                    <ul class="weui-uploader__files" id="uploaderFiles">
                        <li class="weui-uploader__file"></li></ul>
                    <div class="weui-uploader__input-box">
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
        <div class="weui-cell weui-cell_select weui-cell_select-after">
            <div class="weui-cell__hd"><label class="weui-label">年级</label></div>
            <div class="weui-cell__bd">
                <select class="weui-select" name="select2">
                    <option value="1">一年级</option>
                    <option value="2">二年级</option>
                    <option value="3">三年级</option>
                    <option value="4">四年级</option>
                    <option value="5">五年级</option>
                    <option value="6">六年级</option>
                </select>
            </div>
        </div>
    </div>

</div>
<br>
<%--<a href="javascript:;" class="weui-btn weui-btn_plain-primary">确认上传</a>--%>
    <input type = "submit" value = "确认上传" class="weui-btn weui-btn_plain-primary">
</div>
</form>


<script type="text/html" id="tpl_gallery">
    <div class="page">
        <div class="page__hd">
            <h1 class="page__title">Gallery</h1>
            <p class="page__desc"></p>
        </div>
        <div class="weui-gallery" style="display: block">
            <span class="weui-gallery__img" style="background-image: url(/img/logo.png);"></span>
            <div class="weui-gallery__opr">
                <a href="javascript:" class="weui-gallery__del">
                    <i class="weui-icon-delete weui-icon_gallery-delete"></i>
                </a>
            </div>
        </div>
    </div>
</script>
</body>
</html>
