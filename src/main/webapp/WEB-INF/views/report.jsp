<%--
  Created by IntelliJ IDEA.
  User: lcf12307
  Date: 2018/10/27
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <title>每周报告</title>
    <%@include file="common/header.jsp"%>
</head>
<body>


<div class="page__hd"  style="background:#778899;">
    <div class="weui-flex">

        <div class="weui-flex__item">
            <h1 class="page__title" style="color: #FFFFFF">每周报告</h1>
            <p class="page__desc" style="color: #C0BFC4">下面是每周的报告哦</p>
        </div>
    </div>
</div>

<div class="weui-cells">

    <div class="page__bd">
        <c:forEach items="${reports}" var="report">
            <div class="weui-form-preview">
                <div class="weui-form-preview__hd">
                    <div class="weui-form-preview__item">
                        <label class="weui-form-preview__label">本周评分</label>
                        <em class="weui-form-preview__value">${report.score}</em>
                    </div>
                </div>
                <div class="weui-form-preview__bd">

                    <div class="weui-form-preview__item">
                        <label class="weui-form-preview__label">姓名</label>
                        <span class="weui-form-preview__value">${report.uid}</span>
                    </div>
                    <div class="weui-form-preview__item">
                        <label class="weui-form-preview__label">日期</label>
                        <span class="weui-form-preview__value">2018/10/22-2018/10/26</span>
                    </div>
                    <div class="weui-form-preview__item">
                        <label class="weui-form-preview__label">说明</label>
                        <span class="weui-form-preview__value">${report.detail}</span>
                    </div>
                </div>
                <div class="weui-form-preview__ft">
                    <a class="weui-form-preview__btn weui-form-preview__btn_primary" href="../question/getquestion_five.do?sid=${report.uid}&thispage=0&pagesize=5">查看详情</a>
                </div>
            </div>
            <br>
        </c:forEach>
    </div>
    <div class="page__ft">
        <a href="javascript:home()"><img src="/img/logo.png" /></a>
    </div>

</div>
</body>
</html>
