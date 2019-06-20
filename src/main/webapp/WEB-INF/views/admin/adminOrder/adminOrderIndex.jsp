<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2019/4/9
  Time: 21:46
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
    <title>adminOrderIndexJsp</title>
    <% String path = request.getContextPath(); %>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Best Store"/>
    <link href="<%=path%>/resource/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="<%=path%>/resource/layui/css/layui.css" rel="stylesheet" type="text/css">

    <script src="<%=path%>/resource/layui/layui.js"></script>
    <script src="<%=path%>/resource/js/jquery3.3.1.js"></script>
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
                <li class="layui-nav-item"><a href="">未完成订单管理</a></li>
                <li class="layui-nav-item"><a href="">已完成订单管理</a></li>
            </ul>
        </div>
    </div>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;" id="body">
            <table class="layui-hide" id="demo" lay-filter="test"></table>
        </div>
    </div>
    <div class="layui-footer">
        <!-- 底部固定区域 -->
        Copyright &copy; 2018.  Zhejiang Sci-Tech University
    </div>
</div>
<script type="text/javascript">
    layui.use(['element', 'laytpl', 'form'], function () {
        var element = layui.element;
        var laytpl = layui.laytpl;
        var form = layui.form;
    });
    layui.use('table', function () {
        var table = layui.table;
        //table 渲染
        table.render({
            elem: '#demo',
            height: 'full-160',
            cellMinWidth: '80',
            url: '<%=path%>/order/orderData',
            page: true,
            toolbar: 'default',
            loading: 'true',
            limit: 30,
            response: {
                statusCode: 100 //重新规定成功的状态码为 200，table 组件默认为 0
            },
            parseData: function (res) {
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.extend.PageInfo.total, //解析数据长度
                    "data": res.extend.PageInfo.list //解析数据列表
                }
            },
            cols: [[
                {type: 'checkbox', fixed: 'left', style: 'height:28px;'},
                {field: 'orderId', title: 'ID', width: 100, sort: true, fixed: 'left'},
                {
                    field: 'userId', title: '用户名称', width: 200, templet: function (d) {
                        var name = "";
                        $.ajax({
                            url: "<%=path%>/User/GetUser",
                            data: "userId=" + d.userId,
                            type: "post",
                            async: false,
                            success: function (result) {
                                if (result.code == 100) {
                                    name = result.extend.User.userName;
                                }
                            }
                        });
                        return name;
                    }
                },
                {
                    field: 'goodsId', title: '商品名称', width: 150, templet: function (d) {
                        var goodsName = "";
                        $.ajax({
                            url: "<%=path%>/Goods/GetGoods",
                            data: "goodsId=" + d.goodsId,
                            type: "post",
                            async: false,
                            success: function (result) {
                                if (result.code == 100) {
                                    goodsName = result.extend.Goods.goodsName;
                                }
                            }
                        });
                        return goodsName;
                    }
                },
                {field: 'goodsAmount', title: '订单数量', width: 150},
                {field: 'orderStatus', title: '订单状态', width: 150},
                {field: 'orderTime', title: '下单时间', width: 140},
                {field: 'orderCreateTime', title: '创建时间', width: 140, sort: true},
                {field: 'orderUpdateTime', title: '更新时间', width: 180, sort: true},
                {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 250}
            ]]
        });

        // toolbar 添加 删除 编辑处理函数
        table.on('toolbar(test)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'add':
                    layer.open({
                        type: 2,
                        area: ['600px', '400px'],
                        title: '订单添加',
                        content: '<%=path%>/page/getOrderAdd',
                        maxmin: 'true',
                        end: function () {
                            location.reload();
                        }
                    });
                    break;
                case 'delete':
                    // 删除操作
                    data = checkStatus.data;
                    if (data.length >= 1) {
                        var del_orderIds = "";
                        for (var i = 0; i < data.length; i++) {
                            del_orderIds += data[i].orderId + '-';
                        }
                        del_orderIds = del_orderIds.substring(0, del_orderIds.length - 1);
                        layer.confirm('确认删除ID: ' + del_orderIds + ' 等订单吗？', {
                            btn: ['确认', '取消'],
                            yes: function (index) {
                                $.ajax({
                                    url: "<%=path%>/order/OrderDelete",
                                    data: "del_orderIds=" + del_orderIds,
                                    async: false,
                                    success: function (result) {
                                        if (result.code == 100) {
                                            layer.msg("删除成功");
                                            parent.layer.close(index);
                                            location.reload(); // 重新加载页面
                                        } else {
                                            layer.msg(result.extend.message);
                                        }
                                    },
                                    error: function () {
                                        layer.msg("删除请求网络错误！");
                                    }
                                })
                            },
                            btn2: function (index) {
                                parent.layer.close(index);
                            }
                        })
                    } else {
                        layer.msg("请至少选择一条删除的数据");
                    }
                    break;
                case 'update':
                    data = checkStatus.data;
                    if (data.length > 1) {
                        layer.msg("请只选择一条数据进行编辑！");
                    } else if (data.length == 1) {
                        layer.open({
                            type: 2,
                            area: ['600px', '400px'],
                            title: '订单编辑',
                            content: '<%=path%>/page/getOrderEdit?&orderId=' + data[0].orderId,
                            maxmin: 'true',
                            end: function () {
                                location.reload();
                            }
                        });
                    } else {
                        layer.msg("请选择一条数据进行编辑！");
                    }
                    break;
            }
        });

        // 表格操作 详情 删除 编辑处理函数
        table.on('tool(test)', function (obj) {
            var data = obj.data;
            if (obj.event === 'confirm') {
                // 商品细节
                layer.confirm('确认ID: ' + data.orderId + ' 订单么?', {
                    btn: ['确认', '取消'],
                    yes: function (index) {
                        $.ajax({
                            url: "<%=path%>/order/orderConfirm",
                            data: "orderId=" + data.orderId,
                            async: false,
                            success: function (result) {
                                if (result.code == 100) {
                                    layer.msg(result.extend.message);
                                    parent.layer.close(index);
                                    location.reload();
                                } else {
                                    layer.msg(result.extend.message);
                                }
                            },
                            error: function () {
                                layer.msg("确认订单请求失败！");
                            }
                        })
                    },
                    btn2: function (index) {
                        parent.layer.close(index);
                    }
                })
            } else if (obj.event === 'unconfirm') {
                layer.confirm('确认 取消 ' + data.orderId + ' 订单么?', {
                    btn: ['确认', '取消'],
                    yes: function (index) {
                        $.ajax({
                            url: "<%=path%>/order/orderUnconfrim",
                            data: "orderId=" + data.orderId,
                            async: false,
                            success: function (result) {
                                debugger;
                                if (result.code == 100) {
                                    parent.layer.close(index);
                                    location.reload();
                                } else {
                                    layer.msg("订单取消失败");
                                }
                            },
                            error: function () {
                                layer.msg("订单取消请求失败！");
                            }
                        })
                    },
                    btn2: function (index) {
                        parent.layer.close(index);
                    }
                })
            } else if (obj.event === 'del') {
                layer.confirm('确认删除 ' + data.orderId + ' 订单么?', {
                    btn: ['确认', '取消'],
                    yes: function (index) {
                        $.ajax({
                            url: "<%=path%>/order/OrderDelete",
                            data: "del_orderIds=" + data.orderId,
                            async: false,
                            success: function (result) {
                                debugger;
                                if (result.code == 100) {
                                    parent.layer.close(index);
                                    location.reload();
                                } else {
                                    layer.msg("订单删除失败");
                                }
                            },
                            error: function () {
                                layer.msg("订单删除请求失败！");
                            }
                        })
                    },
                    btn2: function (index) {
                        parent.layer.close(index);
                    }
                })
            }
        });
    })

</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="confirm">确认订单</a>
    <a class="layui-btn layui-btn-xs" lay-event="unconfirm">取消订单</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除订单</a>
</script>
</body>
</html>
