<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>学生信息</title>
    <%@include file="../common/header.jsp"%>
</head>
<body>
<div class="page__hd"  style="background:#778899;">
    <div class="weui-flex">
        <div class="weui-flex__item">
            <h1 class="page__title" style="color: #FFFFFF;text-align: center">学生信息</h1>
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
            <%--<img class="circleImg" src="/img/logo.png"  />--%>
            <img class="circleImg" src="http://localhost:8080/${stu.icon}"/>
        </div>
    </a>
    <a class="weui-cell weui-cell_access" href="javascript:;">
        <div class="weui-cell__bd">
            <p>名字</p>
        </div>
        <div class="weui-cell__ft">${stu.name}</div>
    </a>
    <a class="weui-cell weui-cell_access" href="javascript:;">
        <div class="weui-cell__bd">
            <p>性别</p>
        </div>
        <div class="weui-cell__ft">
            <c:choose>
                <c:when test="${stu.sex == 0}">
                    男
                </c:when>
                <c:otherwise>
                    女
                </c:otherwise>
            </c:choose>
        </div>
    </a>
    <a class="weui-cell weui-cell_access" href="javascript:;">
        <div class="weui-cell__bd">
            <p>手机号</p>
        </div>
        <div class="weui-cell__ft">${stu.phone}</div>
    </a>
    <a class="weui-cell weui-cell_access" href="javascript:;">
        <div class="weui-cell__bd">
            <p>老师</p>
        </div>
        <div class="weui-cell__ft">${stu.tid}</div>
    </a>
    <a class="weui-cell weui-cell_access" href="javascript:;">
        <div class="weui-cell__bd">
            <p>学号</p>
        </div>
        <div class="weui-cell__ft">${stu.studentid}</div>
    </a>
    <a class="weui-cell weui-cell_access" href="javascript:;">
        <div class="weui-cell__bd">
            <p>年级</p>
        </div>
        <div class="weui-cell__ft">${stu.grad}</div>
    </a>
</div>

</body>
</html>
