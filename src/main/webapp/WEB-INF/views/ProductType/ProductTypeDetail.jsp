<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2019/4/1
  Time: 21:49
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
        <label class="layui-form-label">类型ID：</label>
        <div class="layui-input-block">
            <input type="text" name="goodstypeId" readonly value="${goodsType.goodstypeId}"
                   placeholder="请输入商品类型名称" autocomplete="off" class="layui-input" style="width: 280px">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">类型名称：</label>
        <div class="layui-input-block">
            <input type="text" name="goodstypeName" readonly value="${goodsType.goodstypeName}"
                   placeholder="请输入商品类型名称" autocomplete="off" class="layui-input" style="width: 280px">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">创建时间：</label>
        <div class="layui-input-block">
            <input type="text" name="goodstypeName" readonly value="${goodsType.goodstypeCreatetime}"
                   class="layui-input" style="width: 280px">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">更新时间：</label>
        <div class="layui-input-block">
            <input type="text" name="goodstypeName" readonly value="${goodsType.goodstypeUpdatatime}"
                   class="layui-input" style="width: 280px">
        </div>
    </div>

</form>
</body>
</html>
