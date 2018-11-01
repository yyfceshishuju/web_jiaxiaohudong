<%--
  Created by IntelliJ IDEA.
  User: rizhiyi123
  Date: 2018/10/30
  Time: 上午12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>
<form id="upload" enctype="multipart/form-data" method="post">
    <input type="file" name="file" id="pic"/>
    <input type="button" value="提交" onclick="uploadPic();"/>
    <span class="showUrl"></span>
    <img src="" class="showPic" alt="">
</form>
<script type="text/javascript">
    function uploadPic() {
        var form = document.getElementById('upload'),
            formData = new FormData(form);
        $.ajax({
            url:"/image/uploadImage",
            type:"post",
            data:formData,
            processData:false,
            contentType:false,
            success:function(res){
                if(res){
                    alert("上传成功！");
                }
                console.log(res);
                $("#pic").val("");
                $(".showUrl").html(res);
                $(".showPic").attr("src",res);
            },
            error:function(err){
                alert("网络连接失败,稍后重试",err);
            }

        })

    }
</script>
</body>
</html>
