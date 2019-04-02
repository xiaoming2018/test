<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2019/4/1
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>theWebGL</title>
    <% String path = request.getContextPath(); %>
    <link href="<%=path%>/resource/layui/css/layui.css" rel="stylesheet" type="text/css">
    <script src="<%=path%>/resource/js/jquery3.3.1.js"></script>
    <script src="<%=path%>/resource/layui/layui.js"></script>
</head>
<body>
<br>
<form class="layui-form" id="form_test">
    <div class="layui-form-item">
        <label class="layui-form-label">类型名称：</label>
        <div class="layui-input-block">
            <input type="hidden" name="goodstypeId" value="${goodsType.goodstypeId}">
            <input type="text" name="goodstypeName" required lay-verify="required" value="${goodsType.goodstypeName}"
                   placeholder="请输入商品类型名称" autocomplete="off" class="layui-input" style="width: 280px">
        </div>
    </div>
    <br>
    <br>
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
        //监听提交
        form.on('submit(formDemo)', function (data) {
            layer.msg(JSON.stringify(data.field));
            $.ajax({
                url: "<%=path%>/Goods/GoodsTypeUpdate",
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
</script>
</body>
</html>
