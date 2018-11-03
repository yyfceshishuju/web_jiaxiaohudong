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
    <title>学生评价</title>
    <%@include file="../common/header.jsp"%>
</head>
<body>
<div class="page__hd"  style="background:#778899;">
    <div class="weui-flex">
        <div class="weui-flex__item">
            <h1 class="page__title" style="color: #FFFFFF;text-align: center">学生评价</h1>
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
<div id="students" class="weui-cells">

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
    <div id="list"></div>
</div>
<div id="upload" class="weui-cells" hidden>
    <ul>
        <li>

            <div class="weui-cells__title">
                <button type="button" id="return" class="weui-btn weui-btn_mini weui-btn_default"> 重新选择学生</button>
            </div>

        </li>
        <li>
            <form id="questionForm" method="post">
                <div class="weui-cells__title">学生评价</div>
                <div class="weui-cells weui-cells_form">
                    <div class="weui-cell weui-cell_select weui-cell_select-after">
                        <div class="weui-cell__hd"><label class="weui-label">分类</label></div>
                        <div class="weui-cell__bd">
                            <select class="weui-select" name="category" id="category">
                            </select>
                        </div>
                    </div>
                    <div class="weui-cell">
                        <div class="weui-cell__hd"><label class="weui-label">评价</label></div>
                        <div class="weui-cell__bd">
                            <textarea class="weui-textarea" placeholder="" rows="3" id="assess" name="assess" ></textarea>
                            <div class="weui-textarea-counter"><span>0</span>/200</div>
                        </div>
                    </div>
                    <input type="hidden" id="student" name="student" hidden>
                </div>
            </form>

        </li>
    </ul>
    <br>
    <button type="button" id="confirm" class="weui-btn weui-btn_plain-primary">确认上传</button>

</div>
</div>


<script type="text/javascript">
    $(function(){
        var $searchBar = $('#searchBar'),
            $searchText = $('#searchText'),
            $searchInput = $('#searchInput'),
            $searchClear = $('#searchClear'),
            $searchCancel = $('#searchCancel'),
            $upload = $("#upload"),
            $students = $("#students"),
            $category = $("#category"),
            $confirm = $("#confirm"),
            $student = $("#student"),
            $return = $("#return"),
            $toast = $("#toast"),
            $content = $(".weui-toast__content"),
            $dialog = $("#dialog"),
            $dialogContent = $("#dialogContent"),
            $list = $("#list"),

            questionUrl = "/teacher/addScore",
            categoryUrl = "/teacher/scoreCategory",
            studentUrl = "/student/list",
            searchUrl = "/student/search"
        ;

        reloadStudents(studentUrl);
        $.ajax({
            url: categoryUrl,
            method: "get",
            success: function (data) {
                data = data.data;
                console.log(data);
                for (var i=0;i<data.name.length;i+=2){
                    if(data.name[i+1] == data.name[i]){
                        $category.append('<option value="'+ data.name[i]+'">' + data.name[i] + '</option>');
                    }
                    else
                        $category.append('<option value="'+ data.name[i] + data.name[i+1] +'">' + data.name[i] + data.name[i+1] + '</option>');
                }
            }
        });


        $list.on("click", ".weui-cell_access", function () {

            $student.val($(this).find(".id").val());
            $students.hide();
            $upload.show();
        });
        $return.on("click", function(){
            $student.val('');
            $students.show();
            $upload.hide();
        });
        $confirm.on("click", function () {
            if(!$("textarea[name='assess']").val()){
                alert("请填写评价");
                return;
            }

            var form = new FormData(document.getElementById('questionForm'));
            $.ajax({
                url:questionUrl,
                type:"post",
                data:form,
                processData:false,
                contentType:false,
                success:function(data){
                    if (data.code != 0){
                        alert(data.msg);
                    } else {
                        hint(data.msg);
                        $student.val('');
                        $students.show();
                        $upload.hide();
                    }
                },
                error:function(e){
                    alert(e);
                }
            })
        });
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
                    //展示搜索结果
                    var url = searchUrl + '?name='+this.value;
                    reloadStudents(url)
                } else {
                    //展示默认结果
                    reloadStudents(studentUrl)
                }
            })
        ;
        $searchClear.on('click', function(){
            //展示默认结果
            reloadStudents(studentUrl);
            $searchInput.val('');
            $searchInput.focus();
        });
        $searchCancel.on('click', function(){
            cancelSearch();
            $searchInput.blur();
        });
        function reloadStudents(url) {
            $.ajax({
                url: url,
                method: "get",
                success: function (data) {
                    data = data.data;
                    $list.empty();
                    for (var i=0; i<data.length; i++){
                        var status = data[i].pid==null || data[i].pid==0?"<p style='color: #FF0000'>未绑定家长</p>":"<p style='color: #00CD00'>已绑定家长</p>";
                        $list.append('<a class="weui-cell weui-cell_access" href="javascript:;">' +
                            '        <div class="weui-cell__hd">' +
                            '            <img class="circleImg" src="'+ data[i].icon +'"  />' +
                            '        </div>' +
                            '        <div class="weui-cell__bd">' +
                            '            <p>'+data[i].name+'</p>' +
                            '            <p style="color: #999999">'+data[i].studentid+'</p>' +
                            '        </div>' +
                            '        <input class="id" hidden value="' +data[i].id+ '">' +
                            '        <div class="weui-cell__ft">' +
                                        status +
                            '        </div>' +
                            '    </a>');
                    }

                }
            });

        }
        function cancelSearch(){
            //展示默认结果
            reloadStudents(studentUrl);
            $searchBar.removeClass('weui-search-bar_focusing');
            $searchText.show();
        }

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
