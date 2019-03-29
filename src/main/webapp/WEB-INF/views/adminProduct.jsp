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
    <title>TheWebGL</title>
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
            <li class="layui-nav-item"><a href="<%=path%>/page/AdminIndex">控制台</a></li>
            <li class="layui-nav-item"><a href="<%=path%>/page/AdminProduct">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户管理</a></li>
            <li class="layui-nav-item"><a href="">订单管理</a></li>
            <li class="layui-nav-item">
                <a href="javascript:">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="<%=path%>/page/AdminWarn">邮件管理</a></dd>
                    <dd><a href="<%=path%>/page/AdminWarn">消息管理</a></dd>
                    <dd><a href="<%=path%>/page/AdminWarn">授权管理</a></dd>
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
            <li class="layui-nav-item"><a href="<%=path%>/servlet/AdminLogout">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item"><a href="javascript:">基础信息管理</a></li>
                <li class="layui-nav-item"><a href="javascript:">商品类型管理</a></li>
                <li class="layui-nav-item"><a href="javascript:">商品模型管理</a></li>
                <li class="layui-nav-item"><a href="">商品库存管理</a></li>
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
        Copyright &copy; 2018. Sunxm Zhejiang Sci-Tech University
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
            url: '<%=path%>/Goods/GoodsData',
            page: true,
            toolbar: 'default',
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
                {field: 'goodsId', title: 'ID', width: 100, sort: true, fixed: 'left'},
                {field: 'goodsName', title: '商品名称', width: 200},
                {field: 'goodsPrice', title: '价格', width: 150, sort: true},
                {field: 'goodsDiscount', title: '折扣', width: 150, sort: true},
                {field: 'goodsIsnew', title: '是否新品', width: 150, templet: '#IsNew'},
                {field: 'goodsStatus', title: '状态', width: 150, templet: '#status'},
                {field: 'goodsAmount', title: '库存', width: 140, sort: true},
                {field: 'goodsSellAmount', title: '已售', width: 140, sort: true},
                {field: 'goodsCreateTime', title: '创建时间', width: 180, sort: true},
                {field: 'goodsUpdateTime', title: '更新时间', width: 180, sort: true},
                {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 216}
            ]]
        });

        // toolbar 添加 删除 编辑处理函数
        table.on('toolbar(test)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'add':
                    layer.open({
                        type: 2,
                        area: ['700px', '800px'],
                        title: '商品添加',
                        content: '<%=path%>/page/getProductAdd',
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
                        var del_goodsIds = "";
                        var del_goodsName = "";
                        for (var i = 0; i < data.length; i++) {
                            del_goodsName += data[i].goodsName + ',';
                            del_goodsIds += data[i].goodsId + '-';
                        }
                        del_goodsName = del_goodsName.substring(0, del_goodsName.length - 1);
                        del_goodsIds = del_goodsIds.substring(0, del_goodsIds.length - 1);
                        layer.confirm('确认删除 ' + del_goodsName + ' 等商品吗？', {
                            btn: ['确认', '取消'],
                            yes: function (index) {
                                $.ajax({
                                    url: "<%=path%>/Goods/GoodsDelete",
                                    data: "del_goodsIds=" + del_goodsIds,
                                    async: false,
                                    success: function (result) {
                                        debugger;
                                        if (result.code == 100) {
                                            layer.msg("删除成功");
                                            parent.layer.close(index);
                                            location.reload();
                                        } else {
                                            layer.msg("删除失败");
                                        }
                                    },
                                    error: function () {
                                        layer.msg("删除请求失败！");
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
                            area: ['700px', '800px'],
                            title: '商品编辑',
                            content: '<%=path%>/page/getProductEdit?&goodsId=' + data[0].goodsId,
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
            if (obj.event === 'detail') {
                // 商品细节
                layer.msg('ID：' + data.id + ' 的查看操作');
            } else if (obj.event === 'del') {
                layer.confirm('真的删除 ' + data.goodsName + ' 么?', {
                    btn: ['确认', '取消'],
                    yes: function (index) {
                        $.ajax({
                            url: "<%=path%>/Goods/GoodsDelete",
                            data: "del_goodsIds=" + data.goodsId,
                            async: false,
                            success: function (result) {
                                debugger;
                                if (result.code == 100) {
                                    layer.msg("删除成功");
                                    parent.layer.close(index);
                                    location.reload();
                                } else {
                                    layer.msg("删除失败");
                                }
                            },
                            error: function () {
                                layer.msg("删除请求失败！");
                            }
                        })
                    },
                    btn2: function (index) {
                        parent.layer.close(index);
                    }
                })
            } else if (obj.event === 'edit') {
                layer.open({
                    type: 2,
                    area: ['700px', '800px'],
                    title: '商品编辑',
                    content: '<%=path%>/page/getProductEdit?&goodsId=' + data.goodsId,
                    maxmin: 'true',
                    end: function () {
                        location.reload();
                    }
                });
            } else if (obj.event === 'modelfile') {
                layer.open({
                    type:2,
                    area:['800px','800px'],
                    title:'3D模型展示',
                    content:'<%=path%>/page/getProductFile?&goodsId=' + data.goodsId,
                    maxmin : 'true',
                })


            }
        });

    })

</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="modelfile">模型</a>
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="detail">详情</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/html" id="status">
    {{#  if(d.goodsStatus == 0){ }}
    上市
    {{#  } else { }}
    下架
    {{#  } }}
</script>
<script type="text/html" id="IsNew">
    {{#  if(d.goodsIsnew == true){}}
    是
    {{#  } else { }}
    否
    {{#  } }}
</script>
</body>
</html>
