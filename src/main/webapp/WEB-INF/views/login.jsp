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
<div id="toast" style="display: none;">
    <div class="weui-mask_transparent"></div>
    <div class="weui-toast">
        <i class="weui-icon-success-no-circle weui-icon_toast"></i>
        <p class="weui-toast__content"></p>
    </div>
</div>
<div class="js_dialog" id="dialog" style="display: none;">
    <div class="weui-mask"></div>
    <div class="weui-dialog">
        <div class="weui-dialog__bd" id="dialogContent"></div>
        <div class="weui-dialog__ft">
            <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary">知道了</a>
        </div>
    </div>
</div>
<div class="page__bd page__bd_spacing">
    <div class="weui-cells weui-cells_form">
        <form id="form" method="post">
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">账号</label></div>
                <div class="weui-cell__bd weui-cell_primary"><input id="phone" name="phone" type="tel" class="weui-input" placeholder="请输入手机号"/></div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">密码</label></div>
                <div class="weui-cell__bd weui-cell_primary"><input id="password" name="password" type="password" class="weui-input" placeholder="请输入密码"/></div>
            </div>
            <div class="weui-btn-area">
                <button id="login" type="button" class="weui-btn weui-btn_primary">登录</button>
            </div>
        </form>
        <a href="/register.do">
            <div class="weui-cells__tips">注册家长账号</div>
        </a>
        <div class="weui-loadmore weui-loadmore_line">
            <span class="weui-loadmore__tips">使用其他方式登录</span><br>
            <a href="/mp/login">
                <img src="/img/wechat.png" class="circleImg" alt="微信登录" align="center" width="50" height="50">
            </a>
        </div>
        <div class="weui-cell">
        </div>
    </div>

</div>
<script type="application/javascript">
    var $login = $("#login"),
        $toast = $("#toast"),
        $content = $(".weui-toast__content"),
        $dialog = $("#dialog"),
        $dialogContent = $("#dialogContent"),
        loginUrl = "/user/login",
        homeUrl = "/home.do"
    ;
    $login.on("click", function () {
        var form = new FormData(document.getElementById('form'));
        $.ajax({
            url:loginUrl,
            type:"post",
            data:form,
            processData:false,
            contentType:false,
            success:function(data){
                if (data.code != 0){

                    console.log(data.code);
                    console.log(data.msg);
                    alert(data.msg);
                } else {
                    hint(data.msg);
                    window.location.href = homeUrl;
                }

            },
            error:function(e){
                alert("未知错误");
            }
        });
        $dialog.on('click', '.weui-dialog__btn', function(){
            $(this).parents('.js_dialog').fadeOut(200);
        });
        function hint(msg) {
            if ($toast.css('display') != 'none') return;
            $content.text(msg);
            $toast.fadeIn(100);
            setTimeout(function () {
                $toast.fadeOut(100);
            }, 2000);
        }
        function alert(msg) {
            $dialogContent.text(msg);
            $dialog.fadeIn(100);
        }
    });

</script>

</body>
</html>
