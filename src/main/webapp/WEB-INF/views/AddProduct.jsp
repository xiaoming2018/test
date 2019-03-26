<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2019/3/19
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Product Add jsp</title>
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
                   placeholder="请输入商品名称" autocomplete="off" class="layui-input" style="width: 400px">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">商品类型：</label>
        <div class="layui-input-block" style="width: 400px">
            <select name="goodsTypeId" lay-verify="required">
                <%-- 商品类型数据解析 value and name --%>
                <option value=""></option>
                <c:forEach var="type" items="${goodsTypeList}">
                    <option value="${type.goodstypeId}">${type.goodstypeName}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">商品价格：</label>
        <div class="layui-input-block">
            <input type="text" name="goodsPrice" required lay-verify="number"
                   placeholder="请输入商品价格" autocomplete="off" class="layui-input" style="width: 400px">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">价格折扣：</label>
        <div class="layui-input-inline">
            <input type="text" name="goodsDiscount" required lay-verify="number"
                   placeholder="请输入价格折扣" autocomplete="off" class="layui-input" style="width: 180px">
        </div>
        <div class="layui-form-mid layui-input-inline" style="color: #e51c23">%</div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">商品数量：</label>
        <div class="layui-input-block">
            <input type="text" name="goodsAmount" required lay-verify="number"
                   placeholder="请输入商品数量" autocomplete="off" class="layui-input" style="width: 400px">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">是否新品：</label>
        <div class="layui-input-block">
            <input type="checkbox" name="goodsIsnew" lay-text="是|否" value="1" lay-skin="switch">
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">产品描述：</label>
        <div class="layui-input-block">
            <textarea name="goodsDesc" placeholder="请输入产品描述" class="layui-textarea"
                      style="height: 100px;width: 400px"></textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">商品图片：</label>
        <div class="layui-input-block">
            <input type="hidden" id="pic" name="goodsPicture" value="" lay-verify="required"/>
            <div class="layui-input-list">
                <img class="layui-upload-img" id="demo1" style="width: 100px;height: 100px">
                <p id="demoText"></p>
                <br>
                <button type="button" class="layui-btn" id="test1"><i class="layui-icon">&#xe67c;</i>图片上传</button>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo" id="submit">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script>
    //Demo
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
        var uploadInst = upload.render({
            elem: '#test1',
            url: '<%=path%>/file/imageUpLoad',
            acceptMime: 'image/*',
            //auto:false,
            //bindAction:'#submit',
            field: 'goodspic',  // 图片字段名 与 后台接受参数名对应一致
            before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    debugger;
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            },
            done: function (res) {
                //如果上传失败
                if (res.code == 200) {
                    return layer.msg('上传失败');
                } else {
                    debugger;
                    filepath = res.extend.filePath;
                    $("#pic").val(filepath); // 设置filepath
                    // 根据name值 为value赋值
                    var span = $("<span style='color: #FF5722;'></span>").append("商品图片上传成功!");
                    $("#demoText").empty().append(span);
                    return layer.msg('图片上传成功');
                }
            },
            error: function () {
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }
        });
    })
</script>
</body>
</html>
