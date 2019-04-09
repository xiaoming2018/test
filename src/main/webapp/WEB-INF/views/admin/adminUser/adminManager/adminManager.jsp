<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2019/4/8
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>adminManagerUser</title>
    <% String path = request.getContextPath(); %>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Best Store"/>
    <link href="<%=path%>/resource/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="<%=path%>/resource/layui/css/layui.css" rel="stylesheet" type="text/css">

    <script src="<%=path%>/resource/layui/layui.js"></script>
    <script src="<%=path%>/resource/js/jquery3.3.1.js"></script>
    <style type="text/css">
        .layui-table-cell {
            height: 58px !important;
            white-space: normal;
        }

        .layui-table img {
            max-height: 48px;
            max-width: 48px;
        }
    </style>

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
            <li class="layui-nav-item"><a href="<%=path%>/servlet/adminLogout">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item"><a href="<%=path%>/page/adminBaseUser">基本用户管理</a></li>
                <li class="layui-nav-item"><a href="<%=path%>/page/adminManagerUser">管理员用户管理</a></li>
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
            url: '<%=path%>/User/ManagerData',
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
                {field: 'managerId', title: 'ID', width: 80, sort: true, fixed: 'left'},
                {field: 'managerName', title: '管理员名称', width: 180},
                {field: 'managerAccount', title: '管理员账户', width: 180},
                {field: 'managerPassword', title: '密码', width: 200},
                {field: 'managerEmail', title: '邮箱', width: 200},
                {field: 'managerPicture', title: '头像', width: 80, templet: '#status'},
                {field: 'managerPhone', title: '联系方式', width: 200},
                {field: 'createTime', title: '创建时间', width: 200, sort: true},
                {field: 'updateTime', title: '更新时间', width: 200, sort: true},
                {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 240}
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
                        title: '管理员添加',
                        content: '<%=path%>/page/getManagerAdd',
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
                        var del_managerIds = "";
                        var del_managerName = "";
                        for (var i = 0; i < data.length; i++) {
                            del_managerName += data[i].managerName + ',';
                            del_managerIds += data[i].managerId + '-';
                        }
                        del_managerName = del_managerName.substring(0, del_managerName.length - 1);
                        del_managerIds = del_managerIds.substring(0, del_managerIds.length - 1);
                        layer.confirm('确认删除 ' + del_managerName + ' 等商品吗？', {
                            btn: ['确认', '取消'],
                            yes: function (index) {
                                $.ajax({
                                    url: "<%=path%>/User/ManagerDelete",
                                    data: "del_managerIds=" + del_managerIds,
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
                            title: '管理员信息编辑',
                            content: '<%=path%>/page/getManagerEdit?&managerId=' + data[0].managerId,
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
            if (obj.event === 'del') {
                layer.confirm('真的删除 ' + data.managerName + ' 么?', {
                    btn: ['确认', '取消'],
                    yes: function (index) {
                        $.ajax({
                            url: "<%=path%>/User/ManagerDelete",
                            data: "del_managerIds=" + data.managerId,
                            async: false,
                            success: function (result) {
                                debugger;
                                if (result.code == 100) {
                                    layer.msg("删除成功");
                                    parent.layer.close(index);
                                    location.reload();
                                } else {
                                    layer.msg(result.extend.message);
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
                    title: '管理员编辑',
                    content: '<%=path%>/page/getManagerEdit?&managerId=' + data.managerId,
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
    <img src="<%=path%>/{{d.managerPicture}}">
</script>
</body>
</html>
