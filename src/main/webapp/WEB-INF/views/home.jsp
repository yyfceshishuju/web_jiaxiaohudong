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
</head>
<body>


<div class="container">
    <div class="page__hd"  style="background:#778899;">
        <div class="weui-flex" style="width: 170px">
            <div class="weui-flex__item">
                <img class="circleImg" src="/img/logo.png"  id="icon" />
            </div>
            <div class="weui-flex__item">
                <h1 class="page__title" style="color: #FFFFFF;text-align: left" id="name">小明同学</h1>
                <p class="page__desc" style="color: #C0BFC4;text-align: left" id="type">五年级</p>
            </div>
        </div>
    </div>
    <ul>
        <li>
            <div id="teacher">
                <div class="weui-cells__title">家校社区-教师版</div>
                <div class="weui-grids">
                    <a href="/class.do" class="weui-grid">
                        <div class="weui-grid__icon">
                            <img src="/img/camera.png" alt="">
                        </div>
                        <p class="weui-grid__label">错题上传</p>
                    </a>
                    <!--<a href="javascript:;" class="weui-grid">-->
                    <!--<div class="weui-grid__icon">-->
                    <!--<img src="./img/class.png" alt="">-->
                    <!--</div>-->
                    <!--<p class="weui-grid__label">我的班级</p>-->
                    <!--</a>-->
                    <!--<a href="javascript:;" class="weui-grid">-->
                    <!--<div class="weui-grid__icon">-->
                    <!--<img src="./img/add.png" alt="">-->
                    <!--</div>-->
                    <!--<p class="weui-grid__label">创建班级</p>-->
                    <!--</a>-->
                    <!--<a href="note.html" class="weui-grid">-->
                    <!--<div class="weui-grid__icon">-->
                    <!--<img src="./img/book.png" alt="">-->
                    <!--</div>-->
                    <!--<p class="weui-grid__label">每日精选错题</p>-->
                    <!--</a>-->
                    <a href="javascript:;" class="weui-grid" id="showAndroidActionSheet">
                        <div class="weui-grid__icon">
                            <img src="/img/select.png" alt="">
                        </div>
                        <p class="weui-grid__label">年级选择</p>
                    </a>
                    <a href="/note.do" class="weui-grid">
                        <div class="weui-grid__icon">
                            <img src="/img/book.png" alt="">
                        </div>
                        <p class="weui-grid__label">学生错题库</p>
                    </a>
                    <a href="/student/add.do" class="weui-grid">
                        <div class="weui-grid__icon">
                            <img src="/img/add_student.png" alt="">
                        </div>
                        <p class="weui-grid__label">添加学生</p>
                    </a>
                    <a href="/report.do" class="weui-grid">
                        <div class="weui-grid__icon">
                            <img src="/img/report.png" alt="">
                        </div>
                        <p class="weui-grid__label">每周报告</p>
                    </a>
                    <a href="/user.do" class="weui-grid">
                        <div class="weui-grid__icon">
                            <img src="/img/student.png" alt="">
                        </div>
                        <p class="weui-grid__label">个人中心</p>
                    </a>
                </div>
            </div>
        </li>
        <li>
            <div id="parent">
                <div class="weui-cells__title">家校社区-家长版</div>
                <div class="weui-grids">
                    <a href="/student/detail.do" class="weui-grid">
                        <div class="weui-grid__icon">
                            <img src="/img/child.png" alt="">
                        </div>
                        <p class="weui-grid__label">学生信息</p>
                    </a>
                    <a href="/note.do" class="weui-grid">
                        <div class="weui-grid__icon">
                            <img src="/img/book.png" alt="">
                        </div>
                        <p class="weui-grid__label">错题本</p>
                    </a>
                    <a href="/report.do" class="weui-grid">
                        <div class="weui-grid__icon">
                            <img src="/img/report.png" alt="">
                        </div>
                        <p class="weui-grid__label">每周报告</p>
                    </a>
                    <!--<a href="note.html" class="weui-grid">-->
                    <!--<div class="weui-grid__icon">-->
                    <!--<img src="./img/book.png" alt="">-->
                    <!--</div>-->
                    <!--<p class="weui-grid__label">每日精选错题</p>-->
                    <!--</a>-->
                    <a href="/bind.do" class="weui-grid">
                        <div class="weui-grid__icon">
                            <img src="/img/add_student.png" alt="">
                        </div>
                        <p class="weui-grid__label">绑定学生</p>
                    </a>
                    <a href="/user.do" class="weui-grid">
                        <div class="weui-grid__icon">
                            <img src="/img/student.png" alt="">
                        </div>
                        <p class="weui-grid__label">个人中心</p>
                    </a>
                </div>
            </div>
        </li>
        <li>
            <div id="other">
                <div class="weui-cells__title">第三方服务</div>
                <div class="weui-grids">
                    <a href="javascript:;" class="weui-grid">
                        <div class="weui-grid__icon">
                            <img src="/img/wechat.png" alt="">
                        </div>
                        <p class="weui-grid__label">微信绑定</p>
                    </a>
                </div>
            </div>
        </li>
    </ul>

    <div class="weui-skin_android" id="androidActionsheet" style="display: none">
        <div class="weui-mask"></div>
        <div class="weui-actionsheet">
            <div class="weui-actionsheet__menu">
                <div class="weui-actionsheet__cell">一年级</div>
                <div class="weui-actionsheet__cell">二年级</div>
                <div class="weui-actionsheet__cell">三年级</div>
                <div class="weui-actionsheet__cell">四年级</div>
                <div class="weui-actionsheet__cell">五年级</div>
                <div class="weui-actionsheet__cell">六年级</div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        var $androidActionSheet = $('#androidActionsheet'),
            $androidMask = $androidActionSheet.find('.weui-mask'),
            $icon = $("#icon"),
            $name = $("#name"),
            $type = $("#type"),
            $userUrl = "/user/info"
        ;
        $.ajax({
            url: $userUrl,
            method: "get",
            success: function (data) {
                $icon.attr("src", data.icon);
                $name.text(data.name);
                $type.text(data.type);

            },
            error: function () {
                alert("请重新登录");
                window.location.href = "/login.do"
            }

        });
        $("#showAndroidActionSheet").on('click', function(){
            $androidActionSheet.fadeIn(200);
            $androidMask.on('click',function () {
                $androidActionSheet.fadeOut(200);
            });
        });
    });
</script>

</body>
</html>
