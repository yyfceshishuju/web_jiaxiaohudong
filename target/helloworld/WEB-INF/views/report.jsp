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
    <title>每周报告</title>
    <%@include file="common/header.jsp"%>
</head>
<body>

<div class="page js_show">

    <div class="page__hd"  style="background:#778899;">
        <div class="weui-flex">

            <div class="weui-flex__item">
                <h1 class="page__title" style="color: #FFFFFF">每周报告</h1>
                <p class="page__desc" style="color: #C0BFC4">下面是每周的报告哦</p>
            </div>
        </div>
    </div>
    <div class="page__bd">
        <div class="weui-form-preview">
            <div class="weui-form-preview__hd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">本周评分</label>
                    <em class="weui-form-preview__value">A</em>
                </div>
            </div>
            <div class="weui-form-preview__bd">

                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">姓名</label>
                    <span class="weui-form-preview__value">小明同学</span>
                </div>
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">日期</label>
                    <span class="weui-form-preview__value">2018/10/22-2018/10/26</span>
                </div>
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">说明</label>
                    <span class="weui-form-preview__value">本周提交的错题数目为2道，打败了20%的同学，继续努力哦。</span>
                </div>
            </div>
            <div class="weui-form-preview__ft">
                <a class="weui-form-preview__btn weui-form-preview__btn_primary" href="/report/detail.do">查看详情</a>
            </div>
        </div>
        <br>
        <div class="weui-form-preview">
            <div class="weui-form-preview__hd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">本周评分</label>
                    <em class="weui-form-preview__value">A</em>
                </div>
            </div>
            <div class="weui-form-preview__bd">

                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">姓名</label>
                    <span class="weui-form-preview__value">小明的老师</span>
                </div>
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">日期</label>
                    <span class="weui-form-preview__value">2018/10/22-2018/10/26</span>
                </div>
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">说明</label>
                    <span class="weui-form-preview__value">本周您的学生提交的错题数目为2道，打败了20%的老师，小明同学提交了最多题目2道，请及时奖励，小华同学仅仅提交了0道题目，请进行监督</span>
                </div>
            </div>
            <div class="weui-form-preview__ft">
                <a class="weui-form-preview__btn weui-form-preview__btn_primary" href="/report/detail.do">查看详情</a>
            </div>
        </div>
    </div>
    <div class="page__ft">
        <a href="javascript:home()"><img src="/img/logo.png" /></a>
    </div>
</div>

</body>
</html>
