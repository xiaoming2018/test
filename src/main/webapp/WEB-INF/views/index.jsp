<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2018/11/20
  Time: 14:49
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
<!DOCTYPE html>
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
        });
        $(function () {
            var flag = "${loginFlag}";
            console.log("${PageInfo.hasNextPage}");
            debugger;
            if (flag.toString() == "success") {
                $("#first").hide();
                $("#second").show();
            } else {
                $("#first").show();
                $("#second").hide();
            }
            // 页码数据回传解析
            to_page(${PageInfo.pageNum});
        });

        function to_page(pn) {
            $.ajax({
                url: "<%=path%>/page/pageInfo",
                data: "pn=" + pn,
                async: false,
                success: function (result) {
                    debugger;
                    if (result.code == 100) {
                        // 解析 pageinfo
                        // 解析显示分页信息
                        build_page_info(result);
                        // 解析显示分页条数据
                        build_page_nav(result);
                        //重新转发pn, 到主页显示更多商品
                        debugger;
                        var num = ${PageInfo.pageNum};
                        if ( num != pn) {
                            // 解析非当前页,,重新刷新页面
                            debugger;
                            location.href = "<%=path%>/page/toIndex?pn=" + pn;
                        }
                    }
                }
            });
        }
        //解析显示分页信息
        function build_page_info(result) {
            $("#page_info_area").empty();
            $("#page_info_area").append("当前 " + result.extend.pageInfo.pageNum + "页, 总" +
                result.extend.pageInfo.pages + " 页, 总" +
                result.extend.pageInfo.total + " 条记录  ");
        }

        //解析显示分页条 点击分页条进行请求
        function build_page_nav(result) {
            //page_nav_area
            $("#page_nav_area").empty();
            var ul = $("<ul></ul>").addClass("pagination");
            //构建元素
            var fistPageli = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
            var prePageli = $("<li></li>").append($("<a></a>").append("&laquo;"));
            //判断是否存在前页
            if (result.extend.pageInfo.hasPreviousPage == false) {
                fistPageli.addClass("disabled ");
                prePageli.addClass("disabled ");
            }
            //为元素添加点击翻页事件
            fistPageli.click(function () {
                to_page(1);
            });
            prePageli.click(function () {
                to_page(result.extend.pageInfo.pageNum - 1);
            });
            //构建元素
            var nextPageli = $("<li></li>").append($("<a></a>").append("&raquo;"));
            var lastPageli = $("<li></li>").append($("<a></a>").append("尾页").attr("href", "#"));
            //判断是否存在后一页
            if (result.extend.pageInfo.hasNextPage == false) {
                nextPageli.addClass("disabled ");
                lastPageli.addClass("disabled ");
            }
            //为元素添加点击
            nextPageli.click(function () {
                to_page(result.extend.pageInfo.pageNum + 1);
            });
            lastPageli.click(function () {
                to_page(result.extend.pageInfo.pages);
            });
            //ul添加首页和前一页
            ul.append(fistPageli).append(prePageli);
            //遍历页码号
            $.each(result.extend.pageInfo.navigatepageNums, function (index, item) {
                var numli = $("<li></li>").append($("<a></a>").append(item));
                if (result.extend.pageInfo.pageNum == item) {
                    //当前页添加活动标识。
                    numli.addClass("active");
                }
                numli.click(function () {
                    to_page(item);
                });
                ul.append(numli);
            });
            //添加下一页和尾页
            ul.append(nextPageli).append(lastPageli);
            //将ul 添加到 nav 元素中
            var navEle = $("<nav></nav>").append(ul);
            navEle.appendTo("#page_nav_area");
        }
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
                            href="<%=path%>/mail.html">邮箱</a></li>
                    <li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>+1234 567 890</li>
                    <li><i class="glyphicon glyphicon-log-in" aria-hidden="true"></i><a
                            href="<%=path%>/login.jsp">登陆</a></li>
                    <li><i class="glyphicon glyphicon-book" aria-hidden="true"></i><a
                            href="<%=path%>/register.jsp">注册</a></li>
                </ul>
            </div>
            <div id="second" class="header-grid-left animated wow slideInLeft">
                <ul class="layui-nav">
                    <li class="layui-nav-item" lay-unselect="">
                        <a href="javascript:">
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
<div class="new-collections">
    <div class="container">
        <h3 class="animated wow zoomIn">2018 新品上架</h3>
        <p class="est animated wow zoomIn">没有最时尚，只有更时尚</p>
        <%-- c标签 循环 --%>
        <div class="new-collections-grids" id="div1">
            <c:choose>
                <c:when test="${PageInfo.pageNum*6 <= PageInfo.total}">
                    <%--如果 当前页数*6 < 总记录数--%>
                    <c:forEach var="i" begin="0" step="2" end="4">
                        <%-- 对于商品进行重复性布局 代码重用--%>
                        <div id="content-goods" class="col-md-4 new-collections-grid">
                            <div class="new-collections-grid1 animated wow slideInUp">
                                <div class="new-collections-grid1-image">
                                    <a href="<%=path%>/page/toGoods?id=${PageInfo.list[i].goodsId}"
                                       class="product-image">
                                        <img src="<%=path%>/${PageInfo.list[i].goodsPicture}"
                                             style="width:300px;height:400px;"
                                             class="img-responsive"/>
                                    </a>
                                    <div class="new-collections-grid1-image-pos">
                                        <a href="<%=path%>/page/toGoods?id=${PageInfo.list[i].goodsId}">预览详情</a>
                                    </div>
                                </div>
                                <h4>
                                    <a href="<%=path%>/page/toGoods?id=${PageInfo.list[i].goodsId}">${PageInfo.list[i].goodsName}</a>
                                </h4>
                                <p>${PageInfo.list[i].goodsDesc}</p>
                                <div class="new-collections-grid1-left simpleCart_shelfItem">
                                    <p>
                                        <span class="item_price">${PageInfo.list[i].goodsPrice}</span>
                                        <button class="btn btn-success" id="item_add_${i}">加入购物车</button>
                                        <button class="btn btn-danger" id="item_buy_${i}">立即购买</button>
                                    </p>
                                </div>
                            </div>
                            <div class="new-collections-grid1 animated wow slideInUp">
                                <div class="new-collections-grid1-image">
                                    <a href="<%=path%>/page/toGoods?id=${PageInfo.list[i+1].goodsId}"
                                       class="product-image">
                                        <img src="<%=path%>/${PageInfo.list[i+1].goodsPicture}"
                                             style="width:300px;height:400px;"
                                             class="img-responsive"/>
                                    </a>
                                    <div class="new-collections-grid1-image-pos">
                                        <a href="<%=path%>/page/toGoods?id=${PageInfo.list[i+1].goodsId}">预览详情</a>
                                    </div>
                                </div>
                                <h4>
                                    <a href="<%=path%>/page/toGoods?id=${PageInfo.list[i+1].goodsId}">${PageInfo.list[i+1].goodsName}</a>
                                </h4>
                                <p>${PageInfo.list[i+1].goodsDesc}</p>
                                <div class="new-collections-grid1-left simpleCart_shelfItem">
                                    <p>
                                        <span class="item_price">${PageInfo.list[i+1].goodsPrice}</span>
                                        <button class="btn btn-success" id="item_add_${i+1}">加入购物车</button>
                                        <button class="btn btn-danger" id="item_buy_${i+1}">立即购买</button>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <script>
                            $(document).ready(function (c) {
                                $("#item_add_${i}").on('click', function (c) {
                                    debugger;
                                    var goodsId = ${PageInfo.list[i].goodsId};
                                    console.log(goodsId);
                                    $.ajax({
                                        url: "<%=path %>/servlet/CheckUserOnline",
                                        type: "POST",
                                        success: function (result) {
                                            debugger;
                                            if (result.code == 100) {
                                                //layer.msg("已经登陆！");
                                                addToshopCart(result.extend.user.userId, goodsId);
                                            } else {
                                                layer.msg("未登陆！请先登录");
                                            }
                                        }
                                    })
                                });

                                $("#item_buy_${i}").on('click', function (c) {
                                    // 立即购买按钮 点击事件的绑定
                                    debugger;
                                    var goodsId = ${PageInfo.list[i].goodsId};
                                    $.ajax({
                                        url: "<%=path %>/servlet/CheckUserOnline",
                                        type: "POST",
                                        success: function (result) {
                                            debugger;
                                            if (result.code == 100) {
                                                debugger;
                                                location.href = "<%=path%>/page/toCheckOut?userId=" + result.extend.user.userId +
                                                    "&goodsId=" + goodsId;
                                            } else {
                                                layer.msg("未登录，请先登录");
                                            }
                                        }
                                    })

                                });

                                $("#item_buy_${i+1}").on('click', function (c) {
                                    // 立即购买按钮 点击事件的绑定
                                    debugger;
                                    var goodsId = ${PageInfo.list[i+1].goodsId};
                                    $.ajax({
                                        url: "<%=path %>/servlet/CheckUserOnline",
                                        type: "POST",
                                        success: function (result) {
                                            debugger;
                                            if (result.code == 100) {
                                                debugger;
                                                location.href = "<%=path%>/page/toCheckOut?userId=" + result.extend.user.userId +
                                                    "&goodsId=" + goodsId;
                                            } else {
                                                layer.msg("未登录，请先登录");
                                            }
                                        }
                                    })

                                });

                                $("#item_add_${i+1}").on('click', function (c) {
                                    debugger;
                                    var goodsId = ${PageInfo.list[i+1].goodsId};
                                    $.ajax({
                                        url: "<%=path %>/servlet/CheckUserOnline",
                                        type: "POST",
                                        success: function (result) {
                                            debugger;
                                            if (result.code == 100) {
                                                //layer.msg("已经登陆！");
                                                addToshopCart(result.extend.user.userId, goodsId);
                                            } else {
                                                layer.msg("未登陆！请先登录");
                                            }
                                        }
                                    })
                                })
                            });

                            function addToshopCart(userId, goodsId) {
                                var goodsamount = 1;
                                debugger;
                                $.ajax({
                                    url: "<%=path %>/Goods/add",
                                    data: "userId=" + userId + "&goodsId=" + goodsId + "&goodsAmount=" + goodsamount,
                                    success: function (result) {
                                        debugger;
                                        if (result.code == 100) {
                                            layer.msg("成功加入购物车");
                                        } else {
                                            layer.msg("加入购物车失败");
                                        }
                                    }
                                });
                            }
                        </script>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <%--最后一页设置--%>
                    <c:forEach var="j" begin="0" step="2" end="${PageInfo.list.size()-2}">
                        <%-- 对于商品进行重复性布局 代码重用--%>
                        <div id="content-goods" class="col-md-4 new-collections-grid">
                            <div class="new-collections-grid1 animated wow slideInUp">
                                <div class="new-collections-grid1-image">
                                    <a href="<%=path%>/page/toGoods?id=${PageInfo.list[j].goodsId}"
                                       class="product-image">
                                        <img src="<%=path%>/${PageInfo.list[j].goodsPicture}"
                                             style="width:300px;height:400px;"
                                             class="img-responsive"/>
                                    </a>
                                    <div class="new-collections-grid1-image-pos">
                                        <a href="<%=path%>/page/toGoods?id=${PageInfo.list[j].goodsId}">预览详情</a>
                                    </div>
                                </div>
                                <h4>
                                    <a href="<%=path%>/page/toGoods?id=${PageInfo.list[j].goodsId}">${PageInfo.list[j].goodsName}</a>
                                </h4>
                                <p>${PageInfo.list[j].goodsDesc}</p>
                                <div class="new-collections-grid1-left simpleCart_shelfItem">
                                    <p>
                                        <span class="item_price">${PageInfo.list[j].goodsPrice}</span>
                                        <button class="btn btn-success" id="item_add_${j}">加入购物车</button>
                                        <button class="btn btn-danger" id="item_buy_${j}">立即购买</button>
                                    </p>
                                </div>
                            </div>
                            <div class="new-collections-grid1 animated wow slideInUp">
                                <div class="new-collections-grid1-image">
                                    <a href="<%=path%>/page/toGoods?id=${PageInfo.list[j+1].goodsId}"
                                       class="product-image">
                                        <img src="<%=path%>/${PageInfo.list[j+1].goodsPicture}"
                                             style="width:300px;height:400px;"
                                             class="img-responsive"/>
                                    </a>
                                    <div class="new-collections-grid1-image-pos">
                                        <a href="<%=path%>/page/toGoods?id=${PageInfo.list[j+1].goodsId}">预览详情</a>
                                    </div>
                                </div>
                                <h4>
                                    <a href="<%=path%>/page/toGoods?id=${PageInfo.list[j+1].goodsId}">${PageInfo.list[j+1].goodsName}</a>
                                </h4>
                                <p>${PageInfo.list[j+1].goodsDesc}</p>
                                <div class="new-collections-grid1-left simpleCart_shelfItem">
                                    <p>
                                        <span class="item_price">${PageInfo.list[j+1].goodsPrice}</span>
                                        <button class="btn btn-success" id="item_add_${j+1}">加入购物车</button>
                                        <button class="btn btn-danger" id="item_buy_${j+1}">立即购买</button>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <script>
                            $(document).ready(function (c) {
                                $("#item_add_${j}").on('click', function (c) {
                                    debugger;
                                    var goodsId = ${PageInfo.list[j].goodsId};
                                    console.log(goodsId);
                                    $.ajax({
                                        url: "<%=path %>/servlet/CheckUserOnline",
                                        type: "POST",
                                        success: function (result) {
                                            debugger;
                                            if (result.code == 100) {
                                                //layer.msg("已经登陆！");
                                                addToshopCart(result.extend.user.userId, goodsId);
                                            } else {
                                                layer.msg("未登陆！请先登录");
                                            }
                                        }
                                    })
                                });

                                $("#item_buy_${j}").on('click', function (c) {
                                    // 立即购买按钮 点击事件的绑定
                                    var goodsId = ${PageInfo.list[j].goodsId};
                                    $.ajax({
                                        url: "<%=path %>/servlet/CheckUserOnline",
                                        type: "POST",
                                        success: function (result) {
                                            if (result.code == 100) {
                                                location.href = "<%=path%>/page/toCheckOut?userId=" + result.extend.user.userId +
                                                    "&goodsId=" + goodsId;
                                            } else {
                                                layer.msg("未登录，请先登录");
                                            }
                                        }
                                    })
                                });
                                $("#item_buy_${j+1}").on('click', function (c) {
                                    // 立即购买按钮 点击事件的绑定
                                    var goodsId = ${PageInfo.list[j+1].goodsId};
                                    $.ajax({
                                        url: "<%=path %>/servlet/CheckUserOnline",
                                        type: "POST",
                                        success: function (result) {
                                            if (result.code == 100) {
                                                location.href = "<%=path%>/page/toCheckOut?userId=" + result.extend.user.userId +
                                                    "&goodsId=" + goodsId;
                                            } else {
                                                layer.msg("未登录，请先登录");
                                            }
                                        }
                                    })
                                });
                                $("#item_add_${j+1}").on('click', function (c) {
                                    $.ajax({
                                        url: "<%=path %>/servlet/CheckUserOnline",
                                        type: "POST",
                                        success: function (result) {
                                            if (result.code == 100) {
                                                //layer.msg("已经登陆！");
                                                addToshopCart(result.extend.user.userId, goodsId);
                                            } else {
                                                layer.msg("未登陆！请先登录");
                                            }
                                        }
                                    })
                                })
                            });

                            function addToshopCart(userId, goodsId) {
                                var goodsamount = 1;
                                $.ajax({
                                    url: "<%=path %>/Goods/add",
                                    data: "userId=" + userId + "&goodsId=" + goodsId + "&goodsAmount=" + goodsamount,
                                    success: function (result) {
                                        if (result.code == 100) {
                                            layer.msg("成功加入购物车");
                                        } else {
                                            layer.msg("加入购物车失败");
                                        }
                                    }
                                });
                            }
                        </script>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <!--bootstarp 构建分页信息 -->
    <div class="row">
        <br>
        <div class="col-md-4" ></div>
        <!-- 分页信息 -->
        <div class="col-md-4" id="page_info_area"></div>
        <!-- 分页条信息 -->
        <div class="col-md-4" id="page_nav_area"></div>
    </div>
</div>

<%--底部布局--%>
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