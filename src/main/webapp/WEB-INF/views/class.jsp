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
    <title>错题上传</title>
    <%@include file="common/header.jsp"%>
</head>
<body>
<div class="page__hd"  style="background:#778899;">
    <div class="weui-flex">
        <div class="weui-flex__item">
            <h1 class="page__title" style="color: #FFFFFF;text-align: center">错题上传</h1>
        </div>
    </div>
</div>
<div class="weui-cells" hidden>

    <div class="weui-search-bar" id="searchBar">
        <form class="weui-search-bar__form">
            <div class="weui-search-bar__box">
                <i class="weui-icon-search"></i>
                <input type="search" class="weui-search-bar__input" id="searchInput" placeholder="搜索" required/>
                <a href="javascript:" class="weui-icon-clear" id="searchClear"></a>
            </div>
            <label class="weui-search-bar__label" id="searchText">
                <i class="weui-icon-search"></i>
                <span>搜索</span>
            </label>
        </form>
        <a href="javascript:" class="weui-search-bar__cancel-btn" id="searchCancel">取消</a>
    </div>
    <br>
    <div class="weui-cells__title">请先选择一个同学</div>
    <a class="weui-cell weui-cell_access" href="javascript:;">
        <div class="weui-cell__bd">
            <p>小明同学</p>
        </div>

        <div class="weui-cell__ft">
            <img class="circleImg" src="./img/logo.png"  />
        </div>
    </a>
    <a class="weui-cell weui-cell_access" href="javascript:;">
        <div class="weui-cell__bd">
            <p>小华同学</p>
        </div>

        <div class="weui-cell__ft">
            <img class="circleImg" src="./img/logo.png"  />
        </div>
    </a>
    <a class="weui-cell weui-cell_access" href="javascript:;">
        <div class="weui-cell__bd">
            <p>小爱同学</p>
        </div>

        <div class="weui-cell__ft">
            <img class="circleImg" src="/img/logo.png"  />
        </div>
    </a>
    <a class="weui-cell weui-cell_access" href="javascript:;">
        <div class="weui-cell__bd">
            <p>小明同学</p>
        </div>

        <div class="weui-cell__ft">
            <img class="circleImg" src="/img/logo.png"  />
        </div>
    </a>

</div>
<div class="weui-cells">
    <ul>
        <li>
            <div class="weui-cells__title">上传题目</div>
            <div class="weui-uploader">
                <div class="weui-uploader__bd">
                    <form id="upload" enctype="multipart/form-data" method="post">
                    <ul class="weui-uploader__files" id="uploaderFiles">
                        <li class="weui-uploader__file"></li></ul>
                    <div class="weui-uploader__input-box">

                            <input id="uploaderInput" name="file" class="weui-uploader__input" type="file" accept="image/*" multiple="">


                    </div>
                        <input type="button" value="提交" onclick="uploadPic();"/>
                    </form>
                </div>
            </div>

        </li>
        <li>
            <div class="weui-cells__title">题目相关信息</div>
            <div class="weui-cells weui-cells_form">
                <div class="weui-cell">
                    <div class="weui-cell__hd"><label class="weui-label">标题</label></div>
                    <div class="weui-cell__bd">
                        <input class="weui-input" type="text" placeholder="请输入错题题目"/>
                    </div>
                </div>
                <div class="weui-cell weui-cell_select weui-cell_select-after">
                    <div class="weui-cell__hd"><label class="weui-label">分类</label></div>
                    <div class="weui-cell__bd">
                        <select class="weui-select" name="select2">
                            <option value="1">中国</option>
                            <option value="2">美国</option>
                            <option value="3">英国</option>
                        </select>
                    </div>
                </div>
                <div class="weui-cell">
                    <div class="weui-cell__hd"><label class="weui-label">题目</label></div>
                    <div class="weui-cells weui-cells_form">
                        <div class="weui-cell">
                            <div class="weui-cell__bd">
                                <textarea class="weui-textarea" placeholder="" rows="3"></textarea>
                                <div class="weui-textarea-counter"><span class="showUrl"></span><span>0</span>/200</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </li>
    </ul>
    <br>
    <a href="javascript:;" class="weui-btn weui-btn_plain-primary">确认上传</a>

</div>
</div>


<script type="text/javascript">
    $(function(){
        var $searchBar = $('#searchBar'),
            $searchResult = $('#searchResult'),
            $searchText = $('#searchText'),
            $searchInput = $('#searchInput'),
            $searchClear = $('#searchClear'),
            $searchCancel = $('#searchCancel');

        function hideSearchResult(){
            $searchResult.hide();
            $searchInput.val('');
        }
        function cancelSearch(){
            hideSearchResult();
            $searchBar.removeClass('weui-search-bar_focusing');
            $searchText.show();
        }

        $searchText.on('click', function(){
            $searchBar.addClass('weui-search-bar_focusing');
            $searchInput.focus();
        });
        $searchInput
            .on('blur', function () {
                if(!this.value.length) cancelSearch();
            })
            .on('input', function(){
                if(this.value.length) {
                    $searchResult.show();
                } else {
                    $searchResult.hide();
                }
            })
        ;
        $searchClear.on('click', function(){
            hideSearchResult();
            $searchInput.focus();
        });
        $searchCancel.on('click', function(){
            cancelSearch();
            $searchInput.blur();
        });
    });
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
