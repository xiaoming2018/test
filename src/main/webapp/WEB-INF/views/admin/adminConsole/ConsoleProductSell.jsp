<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2019/3/12
  Time: 15:57
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
    <title>ConsoleProductSell</title>
    <% String path = request.getContextPath(); %>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Best Store"/>
    <link href="<%=path%>/resource/css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="<%=path%>/resource/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="<%=path%>/resource/layui/css/layui.css" rel="stylesheet" type="text/css">

    <script src="<%=path%>/resource/js/jquery.min.js"></script>
    <script src="<%=path%>/resource/js/bootstrap-3.1.1.min.js"></script>
    <script src="<%=path%>/resource/layui/layui.js"></script>

    <%-- 引入表格插件--%>
    <script src="<%=path%>/static/plugin/echarts/echarts.js"></script>

</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">The WebGL 商品管理后台</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="<%=path%>/page/adminIndex">控制台</a></li>
            <li class="layui-nav-item"><a href="<%=path%>/page/adminProduct">商品管理</a></li>
            <li class="layui-nav-item"><a href="<%=path%>/page/adminUser">用户管理</a></li>
            <li class="layui-nav-item"><a href="<%=path%>/page/adminOrder">订单管理</a></li>
            <li class="layui-nav-item">
                <a href="javascript:">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="<%=path%>/page/adminWarn">邮件管理</a></dd>
                    <dd><a href="<%=path%>/page/adminWarn">消息管理</a></dd>
                    <dd><a href="<%=path%>/page/adminWarn">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:">
                    <img src="<%=path%>/${admin.managerPicture}" class="layui-nav-img">
                    ${admin.managerName}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="<%=path%>/servlet/adminLogout">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item">
                    <a class="" href="javascript:">订单统计</a>
                    <dl class="layui-nav-child">
                        <dd><a href="<%=path%>/page/getConsoleDayOrder">每日订单</a></dd>
                        <dd><a href="<%=path%>/page/getConsoleMonthOrder">每月订单</a></dd>
                        <dd><a href="<%=path%>/page/getConsoleOrderTotal">销量统计</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="<%=path%>/page/getConsoleProductSell">热销产品</a></li>
            </ul>
        </div>
    </div>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 50px;">
            <div id="main" style="margin: auto; width: 1200px;height:800px;"></div>
        </div>
    </div>
    <div class="layui-footer">
        <!-- 底部固定区域 -->
        Copyright &copy; 2018.  Zhejiang Sci-Tech University
    </div>
</div>
<script type="text/javascript">
    layui.use('element', function () {
        var element = layui.element;
    });
    var myChart = echarts.init(document.getElementById("main"));
    var option = {
        title: {
            text: '热销商品统计'
        },
        tooltip: {},
        legend: {
            data: ['商品数量']
        },
        xAxis: {
            data: []
        },
        yAxis: {},
        series: [{
            name: '商品数量',
            type: 'bar',
            data: []
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.showLoading();// 数据加载完成之前显示一段简单的loading动画

    var date = []; // 时间数组，（实际用来盛放x轴的坐标值）
    var numbers = []; // 订单数量数组，（实际用来盛放y轴的坐标值）
    $.ajax({
        type: "post",
        async: true,
        url: "<%=path%>/Console/getProductSell",
        dataType: "json",
        success: function (result) {
            if (result.code == 100) {
                // 处理返回数据 填入表格
                debugger;
                console.log(result);
                for (var i = 0; i < result.extend.goodsAmount.length; i++) {
                    date.push(result.extend.goodsNames[i]);
                    numbers.push(result.extend.goodsAmount[i]);
                }
                myChart.hideLoading();
                myChart.setOption({
                    xAxis: {
                        data: date
                    },
                    series: [{
                        name: '订单数量',
                        type: 'bar',
                        data: numbers
                    }]

                })
            } else {
                layer.msg("数据加载失败，请刷新页面！");
            }
        },
        error: function () {
            layer.msg("表格数据网络请求出错！");
            myChart.hideLoading();
        }
    });
    myChart.setOption(option);
</script>
</body>
</html>
