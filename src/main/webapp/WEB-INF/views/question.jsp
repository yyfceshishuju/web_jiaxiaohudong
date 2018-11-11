<%--
  Created by IntelliJ IDEA.
  User: lcf12307
  Date: 2018/10/27
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <title>错题详情</title>
    <%@include file="common/header.jsp"%>
</head>
<body>

<div class="page__hd"  style="background:#778899;">
    <div class="weui-flex">
        <div class="weui-flex__item">
            <h1 class="page__title" style="color: #FFFFFF;text-align: center">错题详情</h1>
        </div>
    </div>

</div>

<div class="weui-cells">
    <div class="page__bd">
        <article class="weui-article">
            <section>
                <h3 class="page__title">标题</h3>
                <p class="page__desc">${question.name}</p>
                <p>
                    ${question.question}
                </p>
            </section>
        </article>
    </div>
    <div class="page__ft">
        <a href="javascript:home()"><img src="/img/logo.png" /></a>
    </div>
</div>
</div>

</body>
</html>
