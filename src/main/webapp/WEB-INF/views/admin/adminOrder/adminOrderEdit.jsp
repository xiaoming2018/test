<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2019/4/10
  Time: 11:15
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
    <title>adminOrderEdit jsp</title>
    <% String path = request.getContextPath(); %>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Best Store"/>
    <link href="<%=path%>/resource/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="<%=path%>/resource/layui/css/layui.css" rel="stylesheet" type="text/css">

    <script src="<%=path%>/resource/layui/layui.js"></script>
    <script src="<%=path%>/resource/js/jquery3.3.1.js"></script>
    <script type="text/javascript">
        $(function () {
            layui.use(['laydate', 'util'], function () {
                var laydate = layui.laydate;
                var util = layui.util;
                var orderTime = util.toDateString("${Order.orderTime}");
                orderTime = orderTime.substring(0, orderTime.length / 2 + 1);
                laydate.render({
                    elem: '#orderTime',
                    value: orderTime,
                    //format: 'yyyy-MM-dd',
                    isInitValue: true,
                    trigger: 'click'
                });
            })
        })
    </script>
</head>
<body>
<br>
<form class="layui-form" id="form_test">
    <input type="hidden" name="orderId" value="${Order.orderId}">
    <div class="layui-form-item">
        <label class="layui-form-label">用户：</label>
        <div class="layui-input-block" style="width: 400px">
            <select id="user" name="userId" lay-verify="required" >
                <%-- 基本用户数据解析 value and name --%>
                <option value=""></option>
                <c:forEach var="user" items="${Users}">
                    <option value="${user.userId}">${user.userName}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">商品：</label>
        <div class="layui-input-block" style="width: 400px">
            <select id="goods" name="goodsId" lay-verify="required" >
                <%-- 商品类型数据解析 value and name --%>
                <option value=""></option>
                <c:forEach var="good" items="${Goods}">
                    <option value="${good.goodsId}">${good.goodsName}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">商品数量：</label>
        <div class="layui-input-block">
            <input type="text" name="goodsAmount" required lay-verify="number" value="${Order.goodsAmount}"
                   placeholder="请输入商品数量" autocomplete="off" class="layui-input" style="width: 400px">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">订单时间：</label>
        <div class="layui-input-block">
            <input type="text" name="orderTime" id="orderTime" class="layui-input" style="width: 280px">
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
    var index = parent.layer.getFrameIndex(window.name);
    layui.use('form', function () {
        var form = layui.form;
        var userId = "${Order.userId}";
        $("#user").val(userId);
        var goodsId = "${Order.goodsId}";
        $("#goods").val(goodsId);
        form.render();

        //监听提交
        form.on('submit(formDemo)', function (data) {
            debugger;
            layer.msg(JSON.stringify(data.field));
            $.ajax({
                url: "<%=path%>/order/OrderEdit",
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

</script>
</body>
</html>
