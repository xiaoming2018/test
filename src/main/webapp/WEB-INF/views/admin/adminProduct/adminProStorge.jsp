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
        <br>
        <%-- 文件模板上传 预览 提交 入库 完毕 --%>
        &nbsp;&nbsp;<button class="layui-btn layui-btn-normal" id="fileDownload">模板下载</button>
        <button type="button" class="layui-btn" id="fileUpload"><i class="layui-icon"></i>文件上传</button>
        <%--<a href="<%=path%>/file/Download"> 文件下载测试 </a>--%>
        <%-- 文件数据解析 --%>
        <div style="padding: 15px;" id="body">
            <table id="demo" class="layui-table">
            </table>
        </div>
        <%-- 设置提交按钮，进行库存的入库操作 --%>
        <div id="button">
            &nbsp;&nbsp;
        </div>
        <%-- 自动点击 下载文件 --%>
        <div id="aherf"></div>
    </div>
    <div class="layui-footer">
        <!-- 底部固定区域 -->
        Copyright &copy; 2018. Sunxm Zhejiang Sci-Tech University
    </div>
</div>
<script type="text/javascript">
    layui.use(['upload', 'table'], function () {
        var upload = layui.upload;
        var table = layui.table;
        upload.render({
            elem: "#fileUpload",
            url: "<%=path%>/file/ExcelFileUpload",
            accept: 'file',
            exts: 'xls|xlsx',
            done: function (result) {
                if (result.code == 100) {
                    debugger;
                    layer.msg("excel文件上传成功！");
                    // 解析数据表格
                    bulid_goods_table(result.extend.listValue);
                } else {
                    layer.msg("excel文件上传失败, " + result.extend.message);
                }
            },
            error: function () {
                layer.msg("文件上上传接口出错！");
            }
        })
    })

    $(function () {
        // button 点击事件下载
        $("#fileDownload").click(function () {
            // 创建a标签，设置属性，并出发点击下载
            var $a = $("<a></a>");
            //$a.attr("href", "<%=path%>/file/DownloadFile?fileName=muban.xlsx");
            $a.attr("href","<%=path%>/file/Download");
            $("#aherf").append($a);
            $a[0].click();
            $a.remove();
        })
    });

    layui.use('layer', function () {
        var layer = layui.layer;
    });

    // 解析数据表格
    function bulid_goods_table(listValue) {
        $("#demo").empty();
        // 表头设计
        var thead = $("<thead></thead>");
        var tr = $("<tr></tr>");
        var th = $("<th></th>").append("ID");
        var th1 = $("<th></th>").append("商品名称");
        var th2 = $("<th></th>").append("价格");
        var th3 = $("<th></th>").append("库存");
        tr.append(th).append(th1).append(th2).append(th3);
        thead.append(tr);
        $("#demo").append(thead);
        var tbody = $("<tbody></tbody>");
        $("#demo").append(tbody);
        $("#demo tbody").empty();

        $.each(listValue, function (index, item) {
            var goodsId = $("<td></td>").append(item.goodsId);
            var goodsName = $("<td></td>").append(item.goodsName);
            var goodsPrice = $("<td></td>").append(item.goodsPrice);
            var goodsAmount = $("<td></td>").append(item.goodsAmount);

            $("<tr></tr>").append(goodsId).append(goodsName)
                .append(goodsPrice).append(goodsAmount)
                .appendTo("#demo tbody");
        })

        var btn = $("<button></button>").addClass("layui-btn layui-btn-normal").append("入库操作");
        $("#button").append(btn);
        btn.click(function () {
            debugger;
            console.log(listValue);//对象数组
            console.log(JSON.stringify(listValue));
            $.ajax({
                contentType: "application/json",
                url: "<%=path%>/Goods/AddGoodsList",
                data: JSON.stringify(listValue),
                dataType: "json",
                method: 'post',
                success: function (result) {
                    debugger;
                    if (result.code == 100) {
                        layer.msg(result.extend.message);
                        // 入库成功后，进行表格的隐藏或删除。 以及入库按钮隐藏
                        $("#demo").empty();
                        $("#button").empty();
                    } else {
                        layer.msg(result.extend.message);
                    }
                },
                error: function () {
                    layer.msg("入库请求失败！");
                }
            })
        })
    }
</script>
</body>
</html>
