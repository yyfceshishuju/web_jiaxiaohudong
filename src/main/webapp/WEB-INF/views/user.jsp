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
    <title>个人中心</title>
    <%@include file="common/header.jsp"%>
</head>
<body>
<div class="page__hd"  style="background:#778899;">
    <div class="weui-flex">
        <div class="weui-flex__item">
            <h1 class="page__title" style="color: #FFFFFF;text-align: center">个人中心</h1>
        </div>
    </div>
</div>
<div class="weui-cells">
   <form id="form" method="post">
       <a class="weui-cell weui-cell_access" href="javascript:;">
           <div class="weui-cell__bd">
               <p>头像</p>
           </div>
           <div class="weui-cell__ft">
               <input id="uploaderInput" name="uploaderInput" class="weui-uploader__input" type="file" accept="image/*"  multiple="">
               <img id="icon" class="circleImg" src="/img/logo.png"  />
           </div>
       </a>
       <a class="weui-cell weui-cell_access" href="javascript:;">
           <div class="weui-cell__bd">
               <p>名字</p>
           </div>
           <div class="weui-cell__ft" id="name">小明的爸爸</div>
       </a>

       <%--<a class="weui-cell weui-cell_access" href="javascript:;">--%>
       <%--<div class="weui-cell__bd">--%>
       <%--<p>老师</p>--%>
       <%--</div>--%>
       <%--<div class="weui-cell__ft">小名的老师</div>--%>
       <%--</a>--%>

       <a class="weui-cell weui-cell_access" href="javascript:;">
           <div class="weui-cell__bd">
               <p>身份</p>
           </div>
           <div class="weui-cell__ft" id="type">家长</div>
       </a>
       <a class="weui-cell weui-cell_access password" href="javascript:;">
           <div class="weui-cell__bd">
                <p>修改密码</p>
           </div>
       </a>

       <input  id="oldpass" name="oldpass" type="hidden">
       <input  id="newpass1" name="newpass1" type="hidden">
       <input  id="newpass2" name="newpass2" type="hidden">
       <div class="js_dialog" id="passDialog" hidden>
           <div class="weui-mask"></div>
           <div class="weui-dialog">
               <div class="weui-dialog__bd" id="passContent">
                   <h3 class="page__title" style="text-align: center">修改密码</h3>
                   <div class="weui-cell">

                       <div class="weui-cell__bd"><label class="weui-label">旧密码：</label></div>
                       <div class="weui-cell__ft">
                           <input  id="old"  class="weui-input" type="password" placeholder="请输入旧密码">
                       </div>
                   </div>
                   <div class="weui-cell">

                       <div class="weui-cell__bd"><label class="weui-label">新密码：</label></div>
                       <div class="weui-cell__ft">
                           <input id="new1"  class="weui-input" type="password" placeholder="请输入新密码">
                       </div>
                   </div>
                   <div class="weui-cell">

                       <div class="weui-cell__bd"><label class="weui-label">新密码：</label></div>
                       <div class="weui-cell__ft">
                           <input  id="new2" class="weui-input" type="password" placeholder="请再次输入新密码">
                       </div>
                   </div>
               </div>
               <div class="weui-dialog__ft">
                   <button id="confirm" type="button" class="weui-dialog__btn weui-dialog__btn_primary">确定</button>
               </div>
           </div>
       </div>
   </form>
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
<a href="/user/logout" class="weui-btn weui-btn_plain-primary">退出登录</a>
<script type="application/javascript">
    $(function () {
        var $icon = $("#icon"),
            $name = $("#name"),
            $type = $("#type"),
            $upload = $("#uploaderInput"),
            $dialog = $("#dialog"),
            $dialogContent = $("#dialogContent"),
            $password = $("#confirm"),
            $passDialog = $("#passDialog"),
            $passLabel = $(".password"),


            $userUrl = "/user/info",
            $loginUrl = "/login.do",
            $thisUrl = "/user.do",
            $updateUrl = "/user/update"
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
                window.location.href = $loginUrl
            }

        });
        $upload.on('change', update(1));
        $password.on('click', function () {
            $passDialog.fadeOut(100);
            $("#newpass1").val($("#new1").val());
            $("#newpass2").val($("#new2").val());
            $("#oldpass").val($("#old").val());
            update(2);
        });
        $passLabel.on('click', function () {
            $passDialog.fadeIn(200);
        });
        function update(key) {
            var form = new FormData(document.getElementById('form'));
            $.ajax({
                url:$updateUrl + "?key=" +key,
                type:"post",
                data:form,
                processData:false,
                contentType:false,
                success:function(data){
                    if (data.code != 0){
                        alert(data.msg);
                    } else {
                        window.location.href($thisUrl)
                    }

                },
                error:function(e){
                    alert("未知错误");
                }
            });
        }
        function alert(msg) {
            $dialogContent.text(msg);
            $dialog.fadeIn(100);
        }
        $dialog.on('click', '.weui-dialog__btn', function(){
            $(this).parents('.js_dialog').fadeOut(200);
        });

    })
</script>
</body>
</html>
