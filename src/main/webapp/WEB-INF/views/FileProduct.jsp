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
                   autocomplete="off" class="layui-input" style="width: 400px"
                   value="${Goods.goodsName}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">模型名称：</label>
        <div class="layui-input-block">
            <input type="text" name="goodsName" required lay-verify="required"
                   placeholder="请输入模型名称" autocomplete="off" class="layui-input" style="width: 400px">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">模型类型：</label>
        <div class="layui-input-block" style="width: 400px">
            <select name="modelType" lay-verify="required">
                <option value="obj">obj</option>
                <option value="obj">fbx</option>
                <option value="obj">3ds</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">模型文件：</label>
        <input type="hidden" id="pic" name="modelFile" value=""/>
        <div class="layui-upload-drag" id="test10">
            <i class="layui-icon"></i>
            <p>点击上传，或将文件拖拽到此处</p>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo" id="submit">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>

</form>
<script type="text/javascript">

    var filepath;
    var index = parent.layer.getFrameIndex(window.name);
    layui.use('form', function () {
        var form = layui.form;
        //监听提交
        form.on('submit(formDemo)', function (data) {
            //layer.msg(JSON.stringify(data.field));
            $.ajax({
                url: "<%=path%>/Goods/GoodsAdd",
                dataType: 'json',
                data: data.field,
                success: function (data) {
                    if (data.code == 100) {
                        layer.msg("添加成功");
                        parent.layer.close(index);
                    } else {
                        layer.msg("添加失败，请重新操作！");
                    }
                },
                error: function () {
                    layer.msg("返回数据错误");
                }
            });
            // 取消自动提交；
            return false;
        });
        form.verify({
            pass: [
                /^[\S]{6,12}$/,
                '密码必须6到12位，且不能出现空格'
            ]
        })
    });

    layui.use('upload', function () {
        var upload = layui.upload;
        upload.render({
            elem: '#test10',
            url: '<%=path%>/file/fileUpLoad?goodsId=' + ${Goods.goodsId},
            method: 'post',
            accept: 'file',
            exts: 'fbx|obj|3ds',
            //auto: false,
            //bindAction: '#submit',
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
