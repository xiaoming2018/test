<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2019/2/23
  Time: 20:19
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
<!--支持EL表达式，不设的话，EL表达式不会解析-->
<html>
<head>
    <title>GoodsCarts</title>
    <% String path = request.getContextPath(); %>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Best Store"/>

    <link href='<%=path %>/resource/css/font.css' rel='stylesheet' type='text/css'>
    <link href='<%=path %>/resource/css/font1.css' rel='stylesheet' type='text/css'>
    <link href="<%=path %>/resource/css/jquery.countdown.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/animate.min.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="<%=path %>/resource/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="${pageContext.request.contextPath}/resource/layui/css/layui.css" rel="stylesheet" type="text/css">

    <script src="<%=path %>/resource/js/jquery.min.js"></script>
    <script src="<%=path %>/resource/js/bootstrap-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/layui/layui.js"></script>

    <script type="text/javascript">
        layui.use(['element', 'layer'], function () {
            var element = layui.element;
            var layer = layui.layer;
        });
        $(function () {
            //页面加载完毕
            //标题栏的设置
            var flag = "${loginFlag}";
            if (flag.toString() == "success") {
                $("#first").hide();
                $("#second").show();
            } else {
                $("#first").show();
                $("#second").hide();
            }
            // 页面加载完毕，实现结算中心的数据解析
            check();
        })
    </script>
</head>
<body>
<div class="header">
    <div class="container">
        <%-- 账户信息 --%>
        <div class="header-grid">
            <div id="first" class="header-grid-left animated wow slideInLeft">
                <ul>
                    <li>
                        <i class="glyphicon glyphicon-envelope" aria-hidden="true"></i>
                        <a href="<%=path %>/mail.html">邮箱</a>
                    </li>
                    <li>
                        <i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>+1234 567 890
                    </li>
                    <li>
                        <i class="glyphicon glyphicon-log-in" aria-hidden="true"></i>
                        <a href="<%=path %>/login.jsp">登陆</a>
                    </li>
                    <li><i class="glyphicon glyphicon-book" aria-hidden="true"></i>
                        <a href="<%=path %>/register.html">注册</a>
                    </li>
                </ul>
            </div>
            <div id="second" class="header-grid-left animated wow slideInLeft">
                <ul class="layui-nav">
                    <li class="layui-nav-item" lay-unselect="">
                        <a href="javascript:">
                            <img src="${pageContext.request.contextPath}/${User.userPicture}"
                                 class="layui-nav-img">${User.userName}
                        </a>
                    </li>
                    <li class="layui-nav-item"><a href="<%=path%>/servlet/editPage?userId=${User.userId}">基本资料</a></li>
                    <li class="layui-nav-item"><a href="<%=path%>/page/toCart?userId=${User.userId}">购物车</a></li>
                    <li class="layui-nav-item"><a href="<%=path%>/order/orderCenter?userId=${User.userId}">订单中心</a></li>
                    <li class="layui-nav-item"><a href="<%=path%>/servlet/Logout">退出登录</a></li>
                </ul>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="logo-nav">
            <div class="logo-nav-left animated wow zoomIn">
                <h1><a href="${pageContext.request.contextPath}/page/toIndex">Best Store <span>Shop anywhere</span></a>
                </h1>
            </div>
            <%--标题栏 按钮--%>
            <div class="logo-nav-left1">
                <nav class="navbar navbar-default">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header nav_2">
                        <button type="button" class="navbar-toggle collapsed navbar-toggle1" data-toggle="collapse"
                                data-target="#bs-megadropdown-tabs">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
                        <%-- 菜单栏 --%>
                        <h3>
                            <ul class="nav navbar-nav">
                                <li class="active"><a href="${pageContext.request.contextPath}/page/toIndex" class="act">主页</a></li>
                                <li class="active"><a href="${pageContext.request.contextPath}/page/Good">商品</a></li>
                                <li class="active"><a href="${pageContext.request.contextPath}/page/Furniture">家具</a></li>
                                <li class="active"><a href="${pageContext.request.contextPath}/page/Mail">联系我们</a></li>
                            </ul>
                        </h3>
                    </div>
                </nav>
            </div>
            <%--搜索按钮--%>
            <div class="logo-nav-right">
                <div class="search-box">
                    <div id="sb-search" class="sb-search">
                        <%--搜索按钮 完善--%>
                        <form>
                            <input class="sb-search-input" placeholder="输入关键词..." type="search" id="search">
                            <input class="sb-search-submit" type="submit" value="">
                            <span class="sb-icon-search"> </span>
                        </form>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!-- breadcrumbs -->
<div class="breadcrumbs">
    <div class="container">
        <ol class="breadcrumb breadcrumb1 animated wow slideInLeft" data-wow-delay=".5s">
            <li><a href="<%=path %>/page/toIndex"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>首页</a></li>
            <li class="active">购物车</li>
        </ol>
    </div>
</div>

<div class="checkout">
    <div class="container">
        <h3 class="animated wow slideInLeft" data-wow-delay=".5s">购物车中包含: <span
                id="goodsTotalAmount"> ${goodsList.size()} </span> 件商品</h3>
        <div class="checkout-right animated wow slideInUp" data-wow-delay=".5s">
            <table class="timetable_sub">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>商品</th>
                    <th>数量</th>
                    <th>商品名称</th>
                    <th>快递服务费</th>
                    <th>单价</th>
                    <th>移除</th>
                </tr>
                </thead>
                <% int i = 0;%>
                <c:forEach items="${goodsList}" var="goods">
                    <tr class="${goods.goodsId}">
                        <td class="invert"><%=i %>
                        </td>
                        <% i++;%>
                        <td class="invert-image">
                            <a href="<%=path%>/page/toGoods?id=${goods.goodsId}">
                                <img src="<%=path %>/${goods.goodsPicture}" class="img-responsive"/>
                            </a>
                        </td>
                        <td class="invert">
                            <div class="quantity">
                                <div class="quantity-select">
                                    <div class="entry value-minus">&nbsp;</div>
                                    <div class="entry value"><span id="goodsAmount">${goods.goodsAmount}</span></div>
                                    <div class="entry value-plus active">&nbsp;</div>
                                    <div class="entry goodsIdValue" style="visibility:hidden"><span id="goodsId">${goods.goodsId}</span></div>
                                </div>
                            </div>
                        </td>
                        <td class="invert">${goods.goodsName}</td>
                        <td class="invert">￥5.00</td>
                        <td class="invert">${goods.goodsPrice}</td>
                        <td class="invert">
                            <div class="rem">
                                <div class="close<%=i%>"></div>
                            </div>
                            <script>
                                $(document).ready(function (c) {
                                    $('.close<%=i%>').on('click', function (c) {
                                        // 后台进行数据操作
                                        $.ajax({
                                            url: "<%=path %>/Goods/Remove",
                                            data: "userId=" + '${User.userId}' + "&goodsId=" + '${goods.goodsId}',
                                            async: false,
                                            success: function (result) {
                                                debugger;
                                                if (result.code == 100) {
                                                    // 删除成功 结算中心重建
                                                    layer.msg("商品移除成功！");
                                                    // 表格显示删除
                                                    $('.${goods.goodsId}').fadeOut('slow', function (c) {
                                                        $('.${goods.goodsId}').remove();
                                                    });
                                                    check();
                                                } else {
                                                    // 删除失败，信息提示。
                                                    layer.msg("商品未移除，请重新操作！");
                                                }
                                            }
                                        });

                                    });
                                });

                                function check() {
                                    // 对结算中心进行更新 重新请求
                                    debugger;
                                    $.ajax({
                                        url: "<%=path %>/Goods/updateCart",
                                        data: "userId=" + "${User.userId}",
                                        async: false,
                                        success: function (result) {
                                            debugger;
                                            console.log(result);
                                            if (result.code == 100) {
                                                //结算中心返回数据解析
                                                resolveResult(result);
                                            } else {
                                                var message = "购物车为空";
                                                location.href = "<%=path%>/page/warn?message=" + message;
                                                //layer.msg("更新结算失败，请重新操作。");
                                            }
                                        }
                                    })
                                }

                                function resolveResult(result) {
                                    debugger;
                                    // 首先数据清空
                                    $("#checkBox").html("");
                                    var goodslist = result.extend.goodsList;
                                    var totalPrice = result.extend.totalPrice;
                                    var totalGoodsAmount = result.extend.totalGoodsAmount;
                                    // 更新商品总件数
                                    debugger;
                                    var total_Amount = $("#goodsTotalAmount").text();
                                    console.log(total_Amount);
                                    $("#goodsTotalAmount").text(totalGoodsAmount);

                                    $.each(goodslist, function (index, item) {
                                        var li = $("<li></li>").append(item.goodsName)
                                            .append($("<i></i>").append(' -- '))
                                            .append(item.goodsAmount)
                                            .append("件")
                                            .append($("<span></span>").append("￥" + item.goodsAmount * item.goodsPrice));
                                        $("#checkBox").append(li);
                                    });
                                    var FreeLi = $("<li></li>").append("总服务费:").append($("<i></i>"))
                                        .append($("<span></span>").append("￥" + 5 * goodslist.length));
                                    var TotalLi = $("<li></li>").append("共消费：").append($("<i></i>"))
                                        .append($("<span></span>").append("￥" + totalPrice));
                                    $("#checkBox").append(FreeLi).append(TotalLi);
                                }
                            </script>
                        </td>
                    </tr>
                </c:forEach>
                <!--quantity-->
                <script>
                    $('.value-plus').on('click', function () {
                        var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10) + 1;
                        var goodsIdtext = $(this).parent().find('.goodsIdValue');
                        var goodsId = parseInt(goodsIdtext.text(), 10);
                        updateAmount(${User.userId}, goodsId, newVal);
                        divUpd.text(newVal);
                    });

                    $('.value-minus').on('click', function () {
                        var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10) - 1;

                        var goodsIdtext = $(this).parent().find('.goodsIdValue');
                        var goodsId = parseInt(goodsIdtext.text(), 10);
                        if (newVal >= 1) {
                            // 数据更新
                            updateAmount(${User.userId}, goodsId, newVal);
                            divUpd.text(newVal);
                        }
                    });

                    function updateAmount(userId, goodsId, newVal) {
                        debugger;
                        $.ajax({
                            url: "<%=path %>/Goods/amountChange",
                            data: "userId=" + userId + "&goodsId=" + goodsId + "&amount=" + newVal,
                            async: false,
                            success: function (result) {
                                debugger;
                                if (result.code == 100) {
                                    layer.msg("数量改变成功");
                                } else {
                                    layer.msg("数量改变失败，请重新操作");
                                }
                            }
                        });
                        check();
                    }
                </script>
                <!--quantity-->
            </table>
        </div>
        <div class="checkout-left">
            <div class="checkout-left-basket animated wow slideInLeft" data-wow-delay=".5s">
                <h4>商品结算中心</h4>
                <ul id="checkBox"></ul>
            </div>
            <div class="checkout-right-basket animated wow slideInRight" data-wow-delay=".5s">
                <a href="<%=path %>/page/toIndex"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>继续购物</a>
            </div>
            <div class="checkout-right-basket animated wow slideInRight" data-wow-delay=".5s">
                <a href="<%=path %>/order/ListAdd?userId=${User.userId}">
                    <span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>确认订单</a>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!-- footer -->
<div class="footer">
    <div class="container">
        <div class="footer-grids"></div>
        <div class="footer-logo animated wow slideInUp" data-wow-delay=".5s">
            <h2><a href="<%=path %>/page/toIndex">Best Store <span>shop anywhere</span></a></h2>
        </div>
        <div class="copy-right animated wow slideInUp" data-wow-delay=".5s">
            <p>Copyright &copy; 2018. Zhejiang Sci-Tech University</p>
        </div>
    </div>
</div>
</body>
</html>
