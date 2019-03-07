<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2019/2/25
  Time: 9:33
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
    <title>TheWebGL</title>
    <% String path = request.getContextPath(); %>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Best Store"/>
    <link href="<%=path%>/resource/css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="<%=path%>/resource/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href='<%=path%>/resource/css/font.css' rel='stylesheet' type='text/css'>
    <link href='<%=path%>/resource/css/font1.css' rel='stylesheet' type='text/css'>
    <link href="<%=path%>/resource/layui/css/layui.css" rel="stylesheet" type="text/css">

    <script src="<%=path%>/resource/js/jquery.min.js"></script>
    <script src="<%=path%>/resource/js/bootstrap-3.1.1.min.js"></script>
    <script src="<%=path%>/resource/layui/layui.js"></script>

    <script type="text/javascript">
        layui.use(['element', 'layer'], function () {
            var element = layui.element;
            var layer = layui.layer;
        })
        $(function () {
            var flag = "${loginFlag}";
            debugger;
            if (flag.toString() == "success") {
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
                    <li><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i><a
                            href="<%=path%>/mail.html">邮箱</a></li>
                    <li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>+1234 567 890</li>
                    <li><i class="glyphicon glyphicon-log-in" aria-hidden="true"></i><a
                            href="<%=path%>/login.jsp">登陆</a></li>
                    <li><i class="glyphicon glyphicon-book" aria-hidden="true"></i><a
                            href="<%=path%>/register.html">注册</a></li>
                </ul>
            </div>
            <div id="second" class="header-grid-left animated wow slideInLeft">
                <ul class="layui-nav">
                    <li class="layui-nav-item" lay-unselect="">
                        <a href="javascript:;">
                            <img src="<%=path%>/${User.userPicture}" class="layui-nav-img">${User.userName}
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
                <h1><a href="<%=path%>/page/toIndex">Best Store <span>Shop anywhere</span></a>
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
                                <li class="active"><a href="<%=path%>/page/toIndex" class="act">主页</a></li>
                                <li class="active"><a href="<%=path%>/page/Good" class="act">商品</a></li>
                                <li class="active"><a href="<%=path%>/page/Furniture" class="act">家具</a></li>
                                <li class="active"><a href="<%=path%>/page/Mail">联系我们</a></li>
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

<div class="breadcrumbs">
    <div class="container">
        <ol class="breadcrumb breadcrumb1 animated wow slideInLeft" data-wow-delay=".5s">
            <li><a href="<%=path %>/page/toIndex"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>首页</a></li>
            <li class="active">信息提示页</li>
        </ol>
    </div>
</div>

<div class="new-collections">
    <div class="container">
        <h4 class="success">${message}   三秒后跳转主页。。。</h4>
        <% response.setHeader("refresh", "3;toIndex"); %>
    </div>
</div>

<div class="footer">
    <div class="container">
        <div class="footer-grids"></div>
        <div class="footer-logo animated wow slideInUp">
            <h2><a href="<%=path%>/page/toIndex">Best Store <span>shop anywhere</span></a></h2>
        </div>
        <div class="copy-right animated wow slideInUp">
            <p>Copyright &copy; 2018. Sunxm Zhejiang Sci-Tech University</p>
        </div>
    </div>
</div>

</body>
</html>
