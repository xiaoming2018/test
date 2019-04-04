<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2019/4/4
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>theWebGL-ProModelFileEdit</title>
    <% String path = request.getContextPath(); %>
    <link href="<%=path%>/resource/layui/css/layui.css" rel="stylesheet" type="text/css">
    <script src="<%=path%>/resource/js/jquery3.3.1.js"></script>
    <script src="<%=path%>/resource/layui/layui.js"></script>
</head>
<body>
<form class="layui-form" id="form_test">
    <div class="layui-form-item">
        <label class="layui-form-label">模型名称：</label>
        <div class="layui-input-block">
            <input type="hidden" name="modelId" value="${GoodsModelFile.modelId}">
            <input type="text" name="modelName" required lay-verify="required" value="${GoodsModelFile.modelName}"
                   autocomplete="off" class="layui-input" style="width: 280px">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">商品类型：</label>
        <div class="layui-input-block" style="width: 400px">
            <select id="selectModelFile" name="modelType" lay-verify="required">
                <%-- 商品类型数据解析 value and name --%>
                <option value=""></option>
                <option value="obj">obj</option>
                <option value="fbx">fbx</option>
                <option value="3ds">3ds</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">模型文件：</label>
        <input type="hidden" id="file" name="modelFile" value="${GoodsModelFile.modelFile}"/>
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
    var index = parent.layer.getFrameIndex(window.name);
    layui.use('form', function () {
        var form = layui.form;

        var temp = "${GoodsModelFile.modelType}";
        $("#selectModelFile").val(temp);
        form.render();
        //监听提交
        form.on('submit(formDemo)', function (data) {
            layer.msg(JSON.stringify(data.field));
            $.ajax({
                url: "<%=path%>/Goods/GoodsModelUpdate",
                dataType: 'json',
                data: data.field,
                success: function (data) {
                    if (data.code == 100) {
                        layer.msg("商品类型更新成功！");
                        parent.layer.close(index);
                    } else {
                        layer.msg("添加失败，请重新操作！");
                    }
                },
                error: function () {
                    layer.msg("更新商品类型时，返回数据错误");
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
