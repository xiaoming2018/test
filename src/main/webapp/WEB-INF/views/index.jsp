<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2018/11/20
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--输出,条件,迭代标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%> <!--数据格式化标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="sql"%> <!--数据库相关标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fn"%> <!--常用函数标签库-->
<%@ page isELIgnored="false"%> <!--支持EL表达式，不设的话，EL表达式不会解析-->
<!DOCTYPE html>
<html>
<head>
    <title>TheWebGL</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Best Store"/>
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="${pageContext.request.contextPath}/resource/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href='${pageContext.request.contextPath}/resource/css/font.css' rel='stylesheet' type='text/css'>
    <link href='${pageContext.request.contextPath}/resource/css/font1.css' rel='stylesheet' type='text/css'>
    <link href="${pageContext.request.contextPath}/resource/layui/css/layui.css" rel="stylesheet" type="text/css">

    <script src="${pageContext.request.contextPath}/resource/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/layui/layui.js"></script>

    <script src="${pageContext.request.contextPath}/webgl_resource/webgl_test.js"></script>
    <script src="${pageContext.request.contextPath}/webgl_resource/build/three.js"></script>
    <script src="${pageContext.request.contextPath}/webgl_resource/js/libs/stats.min.js"></script>
    <script src="${pageContext.request.contextPath}/webgl_resource/js/libs/dat.gui.min.js"></script>
    <script src="${pageContext.request.contextPath}/webgl_resource/js/loaders/OBJLoader.js"></script>
    <script src="${pageContext.request.contextPath}/webgl_resource/js/controls/OrbitControls.js"></script>

    <script type="text/javascript">
        layui.use('element',function(){
            var element = layui.element;
        })
        $(function () {
            var flag = "${message}";
            debugger;
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
<!-- header -->
<div class="header">
    <div class="container">
        <%-- 账户信息 --%>
        <div class="header-grid">
            <div id="first" class="header-grid-left animated wow slideInLeft">
                <ul>
                    <li><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i><a
                            href="${pageContext.request.contextPath}/mail.html">邮箱</a></li>
                    <li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>+1234 567 890</li>
                    <li><i class="glyphicon glyphicon-log-in" aria-hidden="true"></i><a
                            href="${pageContext.request.contextPath}/login.jsp">登陆</a></li>
                    <li><i class="glyphicon glyphicon-book" aria-hidden="true"></i><a
                            href="${pageContext.request.contextPath}/register.html">注册</a></li>
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
<div class="new-collections">
    <div class="container">
        <h3 class="animated wow zoomIn">2018 新品上架</h3>
        <p class="est animated wow zoomIn">没有最时尚，只有更时尚</p>
        <%-- c标签 循环 --%>
        <div class="new-collections-grids">
            <c:forEach var="i" begin="0" step="2" end = "${PageInfo.list.size()-2}">
            <%-- 对于商品进行重复性布局 代码重用--%>
                <div class="col-md-4 new-collections-grid">
                    <div class="new-collections-grid1 animated wow slideInUp">
                        <div class="new-collections-grid1-image">
                            <a href="${pageContext.request.contextPath}/page/toGoods?id=${PageInfo.list[i].goodsId}" class="product-image">
                                <img src="${pageContext.request.contextPath}/${PageInfo.list[i].goodsPicture}" alt=" " class="img-responsive"/>
                            </a>
                            <div class="new-collections-grid1-image-pos">
                                <a href="${pageContext.request.contextPath}/page/toGoods?id=${PageInfo.list[i].goodsId}">预览详情</a>
                            </div>
                        </div>
                        <h4>
                            <a href="${pageContext.request.contextPath}/page/toGoods?id=${PageInfo.list[i].goodsId}">${PageInfo.list[i].goodsName}</a>
                        </h4>
                        <p>${PageInfo.list[i].goodsDesc}</p>
                        <div class="new-collections-grid1-left simpleCart_shelfItem">
                            <p>
                                <span class="item_price">${PageInfo.list[i].goodsPrice}</span>
                                <a class="item_add" href="${pageContext.request.contextPath}/addcart?canshu">加入购物车</a>
                            </p>
                        </div>
                    </div>
                    <div class="new-collections-grid1 animated wow slideInUp">
                        <div class="new-collections-grid1-image">
                            <a href="${pageContext.request.contextPath}/page/toGoods?id=${PageInfo.list[i+1].goodsId}" class="product-image">
                                <img src="${pageContext.request.contextPath}/${PageInfo.list[i+1].goodsPicture}" alt=" " class="img-responsive"/>
                            </a>
                            <div class="new-collections-grid1-image-pos">
                                <a href="${pageContext.request.contextPath}/page/toGoods?id=${PageInfo.list[i+1].goodsId}">预览详情</a>
                            </div>
                        </div>
                        <h4>
                            <a href="${pageContext.request.contextPath}/page/toGoods?id=${PageInfo.list[i+1].goodsId}">${PageInfo.list[i+1].goodsName}</a>
                        </h4>
                        <p>${PageInfo.list[i+1].goodsDesc}</p>
                        <div class="new-collections-grid1-left simpleCart_shelfItem">
                            <p>
                                <i>￥280</i>
                                <span class="item_price">${PageInfo.list[i+1].goodsPrice}</span>
                                <a class="item_add" href="#">加入购物车</a>
                            </p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<%--底部布局--%>
<div class="footer">
    <div class="container">
        <div class="footer-grids">
        </div>
        <div class="footer-logo animated wow slideInUp">
            <h2><a href="${pageContext.request.contextPath}/page/toIndex">Best Store <span>shop anywhere</span></a></h2>
        </div>
        <div class="copy-right animated wow slideInUp">
            <p>Copyright &copy; 2018. Sunxm Zhejiang Sci-Tech University</p>
        </div>
    </div>
</div>
</body>
</html>