<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2019/4/2
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--输出,条件,迭代标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt" %>
<!--数据格式化标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="sql" %>
<!--数据库相关标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fn" %>
<!--常用函数标签库-->
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>TheWebGL</title>
    <% String path = request.getContextPath(); %>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Best Store"/>
    <link href="<%=path%>/resource/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="<%=path%>/resource/layui/css/layui.css" rel="stylesheet" type="text/css">

    <script src="<%=path%>/resource/layui/layui.js"></script>
    <script src="<%=path%>/resource/js/jquery3.3.1.js"></script>
</head>
<body>
<br>
<form class="layui-form" id="form_test">

    <div class="layui-form-item">
        <label class="layui-form-label">模型名称：</label>
        <div class="layui-input-block">
            <input type="text" name="modelName" required lay-verify="required"
                   placeholder="请输入模型名称" autocomplete="off" class="layui-input" style="width: 400px">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">模型类型：</label>
        <div class="layui-input-block" style="width: 400px">
            <select name="modelType" lay-verify="required">
                <option value=""></option>
                <option value="obj">obj</option>
                <option value="fbx">fbx</option>
                <option value="3ds">3ds</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">模型文件：</label>
        <input type="hidden" id="file" name="modelFile" value=""/>
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

    layui.use(['element', 'layer'], function () {
        var element = layui.element;
        var layer = layui.layer;
    });

    var filepath;
    var index = parent.layer.getFrameIndex(window.name);
    layui.use('form', function () {
        var form = layui.form;
        //监听 商品模型form 上传到后天数据库
        form.on('submit(formDemo)', function (data) {
            layer.msg(JSON.stringify(data.field));
            debugger;
            $.ajax({
                url: "<%=path%>/Goods/GoodsModelFileAdd",
                dataType: 'json',
                data: data.field,
                success: function (data) {
                    if (data.code == 100) {
                        debugger;
                        layer.msg("商品模型文件添加成功!");
                        parent.layer.close(index);
                    } else {
                        layer.msg("商品模型文件添加失败，请重新操作！");
                    }
                },
                error: function () {
                    layer.msg("返回数据错误");
                }
            });
            // 取消自动提交；
            return false;
        });
    });

    layui.use('upload', function () {
        var upload = layui.upload;
        upload.render({
            elem: '#test10',
            url: '<%=path%>/file/fileUpLoad',
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
                    filepath = res.extend.filePath;
                    $("#file").val(filepath);
                    $("#test10").empty();
                    $("#test10").append("<div></div>").addClass("layui-form-mid layui-word-aux")
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
