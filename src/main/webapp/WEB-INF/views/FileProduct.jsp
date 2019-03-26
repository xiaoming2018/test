<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2019/3/26
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Product 3D file</title>
    <% String path = request.getContextPath(); %>
    <link href="<%=path%>/resource/layui/css/layui.css" rel="stylesheet" type="text/css">
    <script src="<%=path%>/resource/js/jquery3.3.1.js"></script>
    <script src="<%=path%>/resource/layui/layui.js"></script>
</head>
<body>
<br>
<form class="layui-form" id="form_test">
    <div class="layui-form-item">
        <label class="layui-form-label">商品名称：</label>
        <div class="layui-input-block">
            <input type="text" name="goodsName" required lay-verify="required"
                   placeholder="请输入商品名称" autocomplete="off" class="layui-input" style="width: 400px"
                   value="${Goods.goodsName}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">模型文件：</label>
        <div class="layui-upload-drag" id="test10">
            <i class="layui-icon"></i>
            <p>点击上传，或将文件拖拽到此处</p>
        </div>
    </div>
</form>
<script type="text/javascript">
    layui.use('upload', function () {
        var upload = layui.upload;
        upload.render({
            elem: '#test10',
            url: '<%=path%>/file/fileUpLoad',
            data:'',
            method: 'post',
            accept: 'file',
            exts: 'fbx|obj|3ds',
            auto: false,
            bindAction: '#submit',
            done: function (res) {
                debugger;
                console.log(res);
                if (res.code == 100) {
                    layer.msg("模型文件 上传成功");
                    $("#test10").empty();
                    $("#test10").append("<div></div>").addClass("layui-form-mid layui-word-aux")
                        .attr("name", "goodsModelId").attr("value", res.extend.goodsfileid)
                        .append("文件上传成功");
                } else {
                    layer.msg("模型文件，上传失败");
                }
            }
        });
    })
</script>

</body>
</html>
