<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2019/4/2
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TheWebGL-adminProModelFile</title>
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
                <li class="layui-nav-item"><a href="<%=path%>/page/adminProduct">基础信息管理</a></li>
                <li class="layui-nav-item"><a href="<%=path%>/page/adminProType">商品类型管理</a></li>
                <li class="layui-nav-item"><a href="<%=path%>/page/adminProModelFile">商品模型管理</a></li>
                <li class="layui-nav-item"><a href="<%=path%>/page/adminProStorge">商品库存管理</a></li>
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
            url: '<%=path%>/Goods/GoodsModelFile',
            page: true,
            toolbar: 'default',
            loading:'true',
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
                {field: 'modelId', title: 'ID', width: 100, sort: true, fixed: 'left'},
                {field: 'modelName', title: '模型名称', width: 200},
                {field: 'modelFile', title: '模型地址', width: 600},
                {field: 'modelType', title: '模型类型', width: 150},
                {field: 'modelCreateTime', title: '创建时间', width: 150, sort: true},
                {field: 'modelUpdateTime', title: '更新时间', width: 150, sort: true},
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
                        area: ['600px', '400px'],
                        title: '商品模型添加',
                        content: '<%=path%>/page/getProductModelFileAdd',
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
                        var del_goodsModelIds = "";
                        var del_goodsModelName = "";
                        for (var i = 0; i < data.length; i++) {
                            del_goodsModelName += data[i].modelName + ',';
                            del_goodsModelIds += data[i].modelId + '-';
                        }
                        del_goodsModelName = del_goodsModelName.substring(0, del_goodsModelName.length - 1);
                        del_goodsModelIds = del_goodsModelIds.substring(0, del_goodsModelIds.length - 1);
                        layer.confirm('确认删除 ' + del_goodsModelName + ' 等模型吗？', {
                            btn: ['确认', '取消'],
                            yes: function (index) {
                                $.ajax({
                                    url: "<%=path%>/Goods/GoodsModelDelete",
                                    data: "del_goodsModelIds=" + del_goodsModelIds,
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
                    debugger;
                    var temp = data[0].modelId;
                    if (data.length > 1) {
                        layer.msg("请只选择一条数据进行编辑！");
                    } else if (data.length == 1) {
                        layer.open({
                            type: 2,
                            area: ['600px', '400px'],
                            title: '商品模型编辑',
                            content: '<%=path%>/page/getProductModelFileEdit?&goodsModelFileId=' + data[0].modelId,
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
                layer.msg('ID：' + data.modelName + ' 的查看操作');
                layer.open({
                    type: 2,
                    area: ['600px', '400px'],
                    title: '商品编辑',
                    content: '<%=path%>/page/getProductDetail?&goodsId=' + data.goodsId,
                    maxmin: 'true',
                    end: function () {
                        location.reload();
                    }
                });
            } else if (obj.event === 'del') {
                layer.confirm('真的删除 ' + data.modelName + ' 么?', {
                    btn: ['确认', '取消'],
                    yes: function (index) {
                        $.ajax({
                            url: "<%=path%>/Goods/GoodsModelDelete",
                            data: "del_goodsModelIds=" + data.modelId,
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
                    area: ['600px', '400px'],
                    title: '商品模型编辑',
                    content: '<%=path%>/page/getProductModelFileEdit?&goodsModelFileId=' + data.modelId,
                    maxmin: 'true',
                    end: function () {
                        location.reload();
                    }
                });
            }
        });
    })

</script>
<script type="text/html" id="barDemo">
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
