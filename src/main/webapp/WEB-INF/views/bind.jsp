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

<div class="js_dialog" id="dialog" style="display: none;">
    <div class="weui-mask"></div>
    <div class="weui-dialog">
        <div class="weui-dialog__bd" id="dialogContent"></div>
        <div class="weui-dialog__ft">
            <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary">知道了</a>
        </div>
    </div>
</div>
<div id="toast" style="display: none;">
    <div class="weui-mask_transparent"></div>
    <div class="weui-toast">
        <i class="weui-icon-success-no-circle weui-icon_toast"></i>
        <p class="weui-toast__content"></p>
    </div>
</div>

<form action="/bind" method="post" enctype="multipart/form-data" onsubmit="return checkNull()">
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
    <c:if test="$(info != null)">${info}</c:if>
</form>

<script type="text/javascript">
    $(function () {
        var $toast = $("#toast"),
            $content = $(".weui-toast__content"),
            $dialog = $("#dialog"),
            $dialogContent = $("#dialogContent")
        ;

        var info = "${info}";
        if(info == "success"){
            hint("绑定成功");
        }
        else if(info == "failed"){
            alert("绑定失败");
        }

        checkNull = function () {
            if(!$("input[name='studentId']").val()){
                alert("请填写学号");
                return false;
            }
            var phNum = $("input[name='phoneNum']").val();
            if(!(/^1[34578]\d{9}$/.test(phNum))){
                alert("电话填写有误");
                return false;
            }
            return true;
        };

        function hint(msg) {
            if ($toast.css('display') != 'none') return;
            $content.text(msg);
            $toast.fadeIn(100);
            setTimeout(function () {
                $toast.fadeOut(100);
            }, 1000);
        }

        function alert(msg) {
            $dialogContent.text(msg);
            $dialog.fadeIn(100);
        }
    });

</script>
</body>
</html>
