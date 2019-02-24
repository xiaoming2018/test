<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2019/2/23
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--输出,条件,迭代标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%> <!--数据格式化标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="sql"%> <!--数据库相关标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fn"%> <!--常用函数标签库-->
<%@ page isELIgnored="false"%> <!--支持EL表达式，不设的话，EL表达式不会解析-->
<html>
<head>
    <title>GoodsCarts</title>
    <% String path = request.getContextPath(); %>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Best Store"/>

    <link href='<%=path %>/resource/css/font.css' rel='stylesheet' type='text/css'>
    <link href='<%=path %>/resource/css/font1.css' rel='stylesheet' type='text/css'>
    <link href="<%=path %>/resource/css/jquery.countdown.css" rel="stylesheet" >
    <link href="<%=path %>/resource/css/animate.min.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="<%=path %>/resource/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="${pageContext.request.contextPath}/resource/layui/css/layui.css" rel="stylesheet" type="text/css">

    <script src="<%=path %>/resource/js/jquery.min.js"></script>
    <script src="<%=path %>/resource/js/bootstrap-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/layui/layui.js"></script>

    <!-- 引入模型js -->
    <script type="text/javascript" src="<%=path %>/webgl_resource/webgl_test.js"></script>
    <script src="<%=path %>/webgl_resource/build/three.js"></script>
    <script src="<%=path %>/webgl_resource/js/libs/stats.min.js"></script>
    <script src="<%=path %>/webgl_resource/js/libs/dat.gui.min.js"></script>
    <script src="<%=path %>/webgl_resource/js/loaders/OBJLoader.js"></script>
    <script src="<%=path %>/webgl_resource/js/controls/OrbitControls.js"></script>
    <script type="text/javascript">
        layui.use(['element','layer'],function(){
            var element = layui.element;
            var layer = layui.layer;
        })
        $(function () {
            //标题栏的设置
            var flag = "${message}";
            if (flag.toString().length > 0) {
                $("#first").hide();
                $("#second").show();
            } else {
                $("#first").show();
                $("#second").hide();
            }
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
                        <i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>+1234 567 890</li>
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
                        <a href="javascript:;">
                            <img src="${pageContext.request.contextPath}/${User.userPicture}" class="layui-nav-img">${User.userName}</a>
                        <dl class="layui-nav-child">
                            <dd><a href="${pageContext.request.contextPath}/servlet/editPage?userId=${User.userId}">基本资料</a></dd>
                            <dd><a href="${pageContext.request.contextPath}/page/toCart?userId=${User.userId}">购物车</a></dd>
                            <dd><a href="${pageContext.request.contextPath}/servlet/Logout?userId=${User.userId}">退出登录</a></dd>
                        </dl>
                    </li>
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
                                <li class="active"><a href="${pageContext.request.contextPath}/page/Good" class="act">商品</a></li>
                                <li class="active"><a href="${pageContext.request.contextPath}/page/Furniture" class="act">家具</a></li>
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
        <h3 class="animated wow slideInLeft" data-wow-delay=".5s">购物车中包含: <span> ${goodsCartList.size()} 件商品</span>
        </h3>
        <div class="checkout-right animated wow slideInUp" data-wow-delay=".5s">
            <table class="timetable_sub">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>商品</th>
                    <th>数量</th>
                    <th>商品名称</th>
                    <th>快递服务费</th>
                    <th>价格</th>
                    <th>移除</th>
                </tr>
                </thead>




                <tr class="rem1">
                    <td class="invert">1</td>
                    <td class="invert-image"><a href="single.html"><img src="resource/images/22.jpg" class="img-responsive"/></a></td>
                    <td class="invert">
                        <div class="quantity">
                            <div class="quantity-select">
                                <div class="entry value-minus">&nbsp;</div>
                                <div class="entry value"><span>1</span></div>
                                <div class="entry value-plus active">&nbsp;</div>
                            </div>
                        </div>
                    </td>
                    <td class="invert">黑色皮靴</td>
                    <td class="invert">￥5.00</td>
                    <td class="invert">￥290.00</td>
                    <td class="invert">
                        <div class="rem">
                            <div class="close1"></div>
                        </div>
                        <script>$(document).ready(function (c) {
                            $('.close1').on('click', function (c) {
                                $('.rem1').fadeOut('slow', function (c) {
                                    $('.rem1').remove();
                                });
                            });
                        });
                        </script>
                    </td>
                </tr>
                <tr class="rem2">
                    <td class="invert">2</td>
                    <td class="invert-image"><a href="single.html"><img src="resource/images/30.jpg" alt=" "
                                                                        class="img-responsive"/></a></td>
                    <td class="invert">
                        <div class="quantity">
                            <div class="quantity-select">
                                <div class="entry value-minus">&nbsp;</div>
                                <div class="entry value"><span>1</span></div>
                                <div class="entry value-plus active">&nbsp;</div>
                            </div>
                        </div>
                    </td>
                    <td class="invert">木制小方桌</td>
                    <td class="invert">￥5.00</td>
                    <td class="invert">￥250.00</td>
                    <td class="invert">
                        <div class="rem">
                            <div class="close2"></div>
                        </div>
                        <script>$(document).ready(function (c) {
                            $('.close2').on('click', function (c) {
                                $('.rem2').fadeOut('slow', function (c) {
                                    $('.rem2').remove();
                                });
                            });
                        });
                        </script>
                    </td>
                </tr>
                <tr class="rem3">
                    <td class="invert">3</td>
                    <td class="invert-image"><a href="single.html"><img src="resource/images/11.jpg" alt=" "
                                                                        class="img-responsive"/></a></td>
                    <td class="invert">
                        <div class="quantity">
                            <div class="quantity-select">
                                <div class="entry value-minus">&nbsp;</div>
                                <div class="entry value"><span>1</span></div>
                                <div class="entry value-plus active">&nbsp;</div>
                            </div>
                        </div>
                    </td>
                    <td class="invert">石质手镯</td>
                    <td class="invert">￥5.00</td>
                    <td class="invert">￥299.00</td>
                    <td class="invert">
                        <div class="rem">
                            <div class="close3"></div>
                        </div>
                        <script>$(document).ready(function (c) {
                            $('.close3').on('click', function (c) {
                                $('.rem3').fadeOut('slow', function (c) {
                                    $('.rem3').remove();
                                });
                            });
                        });
                        </script>
                    </td>
                </tr>
                <!--quantity-->
                <script>
                    $('.value-plus').on('click', function () {
                        var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10) + 1;
                        divUpd.text(newVal);
                    });

                    $('.value-minus').on('click', function () {
                        var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10) - 1;
                        if (newVal >= 1) divUpd.text(newVal);
                    });
                </script>
                <!--quantity-->
            </table>
        </div>
        <div class="checkout-left">
            <div class="checkout-left-basket animated wow slideInLeft" data-wow-delay=".5s">
                <h4>商品结算中心</h4>
                <ul>
                    <li>黑色皮靴 <i>-</i> <span>￥250.00 </span></li>
                    <li>木制小方桌 <i>-</i> <span>￥290.00 </span></li>
                    <li>石质手镯 <i>-</i> <span>￥299.00 </span></li>
                    <li>总服务费 <i>-</i> <span>￥15.00</span></li>
                    <li>总计 <i>-</i> <span>￥854.00</span></li>
                </ul>
            </div>
            <div class="checkout-right-basket animated wow slideInRight" data-wow-delay=".5s">
                <a href="index.html"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>继续购物</a>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!-- //checkout -->
<!-- footer -->
<div class="footer">
    <div class="container">
        <div class="footer-grids"></div>
        <div class="footer-logo animated wow slideInUp" data-wow-delay=".5s">
            <h2><a href="<%=path %>/page/toIndex">Best Store <span>shop anywhere</span></a></h2>
        </div>
        <div class="copy-right animated wow slideInUp" data-wow-delay=".5s">
            <p>Copyright &copy; 2018. Sunxm Zhejiang Sci-Tech University</p>
        </div>
    </div>
</div>

</body>
</html>
