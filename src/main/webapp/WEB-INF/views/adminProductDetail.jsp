<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2019/4/1
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
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

    <div class="layui-form-item" style="display: none">
        <div class="layui-input-block" style="display: none">
            <input type="text" name="goodsId" class="layui-input" type="hidden" value="${Goods.goodsId}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">商品名称：</label>
        <div class="layui-input-block">
            <input type="text" name="goodsName" readonly
                   placeholder="请输入商品名称" autocomplete="off" class="layui-input" style="width: 400px"
                   value="${Goods.goodsName}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">商品类型：</label>
        <div class="layui-input-block" style="width: 400px">
            <input type="text" name="goodsName" readonly
                   autocomplete="off" class="layui-input" style="width: 400px"
                   value="${goodsType.goodstypeName}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">商品价格：</label>
        <div class="layui-input-block">
            <input type="text" name="goodsPrice" readonly class="layui-input" style="width: 400px"
                   value="${Goods.goodsPrice}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">价格折扣：</label>
        <div class="layui-input-inline">
            <input type="text" name="goodsDiscount" readonly class="layui-input" style="width: 180px"
                   value="${Goods.goodsDiscount}">
        </div>
        <div class="layui-form-mid layui-input-inline" style="color: #e51c23">%</div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">商品数量：</label>
        <div class="layui-input-block">
            <input type="text" name="goodsAmount" class="layui-input" style="width: 400px"
                   value="${Goods.goodsAmount}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">是否新品：</label>
        <div class="layui-input-block">
            <input type="checkbox" id="Isnew" name="goodsIsnew" lay-text="是|否"  lay-skin="switch">
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">产品描述：</label>
        <div class="layui-input-block">
            <textarea name="goodsDesc" readonly class="layui-textarea" style="height: 100px;width: 400px" >${Goods.goodsDesc}</textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">商品图片：</label>
        <div class="layui-input-block">
            <input type="hidden" id="pic" name="goodsPicture" value="${Goods.goodsPicture}" lay-verify="required"/>
            <div class="layui-input-list">
                <img class="layui-upload-img" id="demo1" style="width: 100px;height: 100px" src="<%=path%>/${Goods.goodsPicture}">
                <p id="demoText"></p>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript">
    var form;
    $(function () {
        layui.use(['element', 'laytpl', 'form'], function () {
            var element = layui.element;
            var laytpl = layui.laytpl;
            form = layui.form;
        });
        if('${Goods.goodsIsnew}' == 'true'){
            $("#Isnew").attr('checked','true');
        }
    })

</script>
</body>
</html>
