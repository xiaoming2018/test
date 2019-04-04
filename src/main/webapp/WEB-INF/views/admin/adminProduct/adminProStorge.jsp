<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2019/4/4
  Time: 21:36
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
    <title>theWenGL StrogeUpload</title>
    <% String path = request.getContextPath(); %>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Best Store"/>
    <link href="<%=path%>/resource/layui/css/layui.css" rel="stylesheet" type="text/css">

    <script src="<%=path%>/resource/layui/layui.js"></script>
    <script src="<%=path%>/resource/js/jquery3.3.1.js"></script>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">The WebGL 商品管理后台</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="<%=path%>/page/adminIndex">控制台</a></li>
            <li class="layui-nav-item"><a href="<%=path%>/page/adminProduct">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户管理</a></li>
            <li class="layui-nav-item"><a href="">订单管理</a></li>
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
                <li class="layui-nav-item"><a href="<%=path%>/page/adminProduct">基础信息管理</a></li>
                <li class="layui-nav-item"><a href="<%=path%>/page/adminProType">商品类型管理</a></li>
                <li class="layui-nav-item"><a href="<%=path%>/page/adminProModelFile">商品模型管理</a></li>
                <li class="layui-nav-item"><a href="<%=path%>/page/adminProStorge">商品库存管理</a></li>
            </ul>
        </div>
    </div>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <h2>this is the storge jsp</h2>
        <%-- 文件模板上传 预览 提交 入库 完毕 --%>

    </div>
    <div class="layui-footer">
        <!-- 底部固定区域 -->
        Copyright &copy; 2018. Sunxm Zhejiang Sci-Tech University
    </div>
</div>
</body>
</html>
