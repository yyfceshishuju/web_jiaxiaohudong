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
    <title>错题本</title>
    <%@include file="common/header.jsp"%>
</head>
<body>


<div class="page__hd"  style="background:#778899;">
    <div class="weui-flex">
        <div class="weui-flex__item">
            <h1 class="page__title" style="color: #FFFFFF;text-align: center">错题本</h1>
        </div>
    </div>
</div>
<div class="weui-cells">
    <div class="weui-panel">
        <div class="weui-panel__hd">我的错题本</div>
        <div class="weui-panel__bd">
            <c:forEach items="${questions}" var="question">

            <a class="weui-cell weui-cell_access" href="/question/getquestion.do?id=${question.id}">
                <div class="weui-media-box weui-media-box_text">
                    <h4 class="weui-media-box__title"><c:out value="${question.name}"/></h4>
                    <p class="weui-media-box__desc"><c:out value="${question.question}"/></p>
                    <ul class="weui-media-box__info">
                        <li class="weui-media-box__info__meta">${question.student.name}</li>
                        <li class="weui-media-box__info__meta">${question.utilTime}</li>
                        <li class="weui-media-box__info__meta weui-media-box__info__meta_extra">${question.commonCategory.name}</li>
                    </ul>
                </div>

                <div class="weui-cell__ft"></div>
            </a>
    </c:forEach>

        </div>
    </div>
</div>
</div>

</body>
</html>
