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
<%-- 选项卡 设置 --%>
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
    <ul class="layui-tab-title">
        <li class="layui-this">选择模型</li>
        <li>上传模型</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <from class="layui-form" id="select_modelfile">
                <div class="layui-form-item">
                    <label class="layui-form-label">商品名称：</label>
                    <%-- 设置GoodsId值 --%>
                    <input type="hidden" name="goodsId" value="${Goods.goodsId}" >
                    <div class="layui-input-block">
                        <input type="text" name="goodsName" readonly
                               autocomplete="off" class="layui-input" style="width: 400px"
                               value="${Goods.goodsName}">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">模型文件：</label>
                    <div class="layui-input-block" style="width: 400px">
                        <select name="goodsModelId" lay-verify="required">
                            <option value=""></option>
                            <c:forEach var="model" items="${GoodsModelList}">
                                <option value="${model.modelId}">${model.modelName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="formSub" id="submit_file">立即提交</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>

            </from>
        </div>
        <div class="layui-tab-item">
            <form class="layui-form" id="form_test">
                <div class="layui-form-item">
                    <label class="layui-form-label">商品名称：</label>
                    <input type="hidden" name="goodsId" value="${Goods.goodsId}" >
                    <div class="layui-input-block">
                        <input type="text" name="goodsName" readonly
                               autocomplete="off" class="layui-input" style="width: 400px"
                               value="${Goods.goodsName}">
                    </div>
                </div>

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
        </div>
    </div>
</div>

<script type="text/javascript">

    layui.use(['element', 'layer'], function () {
        var element = layui.element;
        var layer = layui.layer;
    });

    var filepath;
    var index = parent.layer.getFrameIndex(window.name);
    layui.use('form', function () {
        var form = layui.form;
        //监听第一个表格提交
        form.on('submit(formSub)',function(data){
            layer.msg(JSON.stringify(data.field));
            debugger;
            $.ajax({
                url: "<%=path%>/Goods/GoodsUpdate",
                dataType: 'json',
                data: data.field,
                success: function (data) {
                    if (data.code == 100) {
                        debugger;
                        layer.msg("商品模型文件添加成功！");
                        parent.layer.close(index);
                    } else {
                        layer.msg("商品模型文件添加失败，请重新操作！");
                    }
                },
                error: function () {
                    layer.msg("商品模型返回数据错误");
                }
            });
            // 取消自动提交
            return false;
        });

        //监听第二个表格提交
        form.on('submit(formDemo)', function (data) {
            layer.msg(JSON.stringify(data.field));
            debugger;
            $.ajax({
                url: "<%=path%>/Goods/GoodsModelFileUpdate",
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
