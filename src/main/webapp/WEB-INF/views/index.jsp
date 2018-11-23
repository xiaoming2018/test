<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2018/11/20
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        $(function () {
            alert(111);
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
<!-- header -->
<div class="header">
    <div class="container">
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
                <ul>
                    <li><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i><a
                            href="${pageContext.request.contextPath}/mail.html">邮箱</a></li>
                    <li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>+1234 567 890</li>
                    <li><i class="glyphicon glyphicon-log-in" aria-hidden="true"></i><a
                            href="${pageContext.request.contextPath}/login.jsp">${User.userName}</a></li>
                    <li class="layui-nav-item"><a href="javascript:;"> <img src="${pageContext.request.contextPath}/${User.userPicture}" class="layui-nav-img"> 管理员</a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="">基本资料</a>
                            </dd>
                            <dd>
                                <a href="">安全设置</a>
                            </dd>
                        </dl>
                    </li>
                </ul>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="logo-nav">
            <div class="logo-nav-left animated wow zoomIn">
                <h1><a href="${pageContext.request.contextPath}/index.html">Best Store <span>Shop anywhere</span></a>
                </h1>
            </div>
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
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="${pageContext.request.contextPath}/index.html"
                                                  class="act">主页</a></li>
                            <!-- Mega Menu -->
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">商品<b class="caret"></b></a>
                                <ul class="dropdown-menu multi-column columns-3">
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <ul class="multi-column-dropdown">
                                                <h6>男士</h6>
                                                <li><a href="products.html">服装</a></li>
                                                <li><a href="products.html">钱包</a></li>
                                                <li><a href="products.html">鞋子</a></li>
                                                <li><a href="products.html">腕表</a></li>
                                                <li><a href="products.html">饰品</a></li>
                                            </ul>
                                        </div>
                                        <div class="col-sm-4">
                                            <ul class="multi-column-dropdown">
                                                <h6>女士</h6>
                                                <li><a href="products.html">服装</a></li>
                                                <li><a href="products.html">钱包</a></li>
                                                <li><a href="products.html">鞋子</a></li>
                                                <li><a href="products.html">腕表</a></li>
                                                <li><a href="products.html">饰品</a></li>
                                                <li><a href="products.html">珠宝</a></li>
                                                <li><a href="products.html">美容、护理</a></li>
                                            </ul>
                                        </div>
                                        <div class="col-sm-4">
                                            <ul class="multi-column-dropdown">
                                                <h6>儿童</h6>
                                                <li><a href="products.html">家庭风</a></li>
                                                <li><a href="products.html">男生服饰</a></li>
                                                <li><a href="products.html">女生服饰</a></li>
                                                <li><a href="products.html">鞋子</a></li>
                                                <li><a href="products.html">名牌店</a></li>
                                            </ul>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">家具<b class="caret"></b></a>
                                <ul class="dropdown-menu multi-column columns-3">
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <ul class="multi-column-dropdown">
                                                <h6>家装家具</h6>
                                                <li><a href="furniture.html">炊具</a></li>
                                                <li><a href="furniture.html">沙发</a></li>
                                                <li><a href="furniture.html">餐桌</a></li>
                                                <li><a href="furniture.html">鞋柜</a></li>
                                                <li><a href="furniture.html">家居服饰</a></li>
                                            </ul>
                                        </div>
                                        <div class="col-sm-4">
                                            <ul class="multi-column-dropdown">
                                                <h6>办公室家具</h6>
                                                <li><a href="furniture.html">地毯</a></li>
                                                <li><a href="furniture.html">办公桌</a></li>
                                                <li><a href="furniture.html">沙发</a></li>
                                                <li><a href="furniture.html">鞋柜</a></li>
                                                <li><a href="furniture.html">插座</a></li>
                                                <li><a href="furniture.html">电动装备</a></li>
                                            </ul>
                                        </div>
                                        <div class="col-sm-4">
                                            <ul class="multi-column-dropdown">
                                                <h6>装饰</h6>
                                                <li><a href="furniture.html">玩具</a></li>
                                                <li><a href="furniture.html">壁钟</a></li>
                                                <li><a href="furniture.html">灯具</a></li>
                                                <li><a href="furniture.html">品牌商店</a></li>
                                            </ul>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                </ul>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/mail.html">联系我们</a></li>
                        </ul>
                    </div>
                </nav>
            </div>
            <div class="logo-nav-right">
                <div class="search-box">
                    <div id="sb-search" class="sb-search">
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
        <div class="new-collections-grids">
            <div class="col-md-3 new-collections-grid">
                <div class="new-collections-grid1 animated wow slideInUp">
                    <div class="new-collections-grid1-image">
                        <a href="single.html" class="product-image"><img
                                src="${pageContext.request.contextPath}/resource/images/7.jpg" alt=" "
                                class="img-responsive"/></a>
                        <div class="new-collections-grid1-image-pos">
                            <a href="${pageContext.request.contextPath}/single.html">预览详情</a>
                        </div>
                        <div class="new-collections-grid1-right">
                            <div class="rating">
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/2.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/2.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/2.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/1.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/1.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </div>
                    <h4><a href="${pageContext.request.contextPath}/single.html">女士夹克</a></h4>
                    <p>灰色系女士春冬款</p>
                    <div class="new-collections-grid1-left simpleCart_shelfItem">
                        <p><i>￥325</i> <span class="item_price">￥249</span><a class="item_add" href="#">
                            加入购物车</a></p>
                    </div>
                </div>
                <div class="new-collections-grid1 animated wow slideInUp">
                    <div class="new-collections-grid1-image">
                        <a href="${pageContext.request.contextPath}/single.html" class="product-image"><img
                                src="${pageContext.request.contextPath}/resource/images/8.jpg" alt=" "
                                class="img-responsive"/></a>
                        <div class="new-collections-grid1-image-pos">
                            <a href="${pageContext.request.contextPath}/single.html">预览详情</a>
                        </div>
                        <div class="new-collections-grid1-right">
                            <div class="rating">
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/2.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/2.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/1.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/1.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/1.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </div>
                    <h4><a href="${pageContext.request.contextPath}/single.html">运动鞋</a></h4>
                    <p>春夏男子轻便跑步鞋减震休闲男鞋.</p>
                    <div class="new-collections-grid1-left simpleCart_shelfItem">
                        <p><i>￥280</i> <span class="item_price">￥150</span><a class="item_add" href="#">加入购物车 </a></p>
                    </div>
                </div>
            </div>
            <div class="col-md-6 new-collections-grid">
                <div class="new-collections-grid1 new-collections-grid1-image-width animated wow slideInUp"
                >
                    <div class="new-collections-grid1-image">
                        <a href="${pageContext.request.contextPath}/single1.html" class="product-image"><img
                                src="${pageContext.request.contextPath}/resource/images/canzhuo1.jpg" alt=" "
                                class="img-responsive"/></a>
                        <div class="new-collections-grid1-image-pos new-collections-grid1-image-pos1">
                            <a href="${pageContext.request.contextPath}/single1.html">预览详情</a>
                        </div>
                        <div class="new-collections-grid1-right new-collections-grid1-right-rate">
                            <div class="rating">
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/2.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/2.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/2.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/2.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/1.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <div class="new-one">
                            <p>New</p>
                        </div>
                    </div>
                    <h4><a href="${pageContext.request.contextPath}/single1.html">餐桌</a></h4>
                    <p>简约、时尚餐桌，大理石制作.</p>
                    <div class="new-collections-grid1-left simpleCart_shelfItem">
                        <p><i>￥580</i> <span class="item_price">￥550</span><a class="item_add" href="#">加入购物车 </a></p>
                    </div>
                </div>
                <div class="new-collections-grid-sub-grids">
                    <div class="new-collections-grid1-sub">
                        <div class="new-collections-grid1 animated wow slideInUp">
                            <div class="new-collections-grid1-image">
                                <a href="${pageContext.request.contextPath}/single.html" class="product-image"><img
                                        src="${pageContext.request.contextPath}/resource/images/6.jpg" alt=" "
                                        class="img-responsive"/></a>
                                <div class="new-collections-grid1-image-pos">
                                    <a href="${pageContext.request.contextPath}/single2.html">预览详情</a>
                                </div>
                                <div class="new-collections-grid1-right">
                                    <div class="rating">
                                        <div class="rating-left">
                                            <img src="${pageContext.request.contextPath}/resource/images/2.png" alt=" "
                                                 class="img-responsive"/>
                                        </div>
                                        <div class="rating-left">
                                            <img src="${pageContext.request.contextPath}/resource/images/2.png" alt=" "
                                                 class="img-responsive"/>
                                        </div>
                                        <div class="rating-left">
                                            <img src="${pageContext.request.contextPath}/resource/images/2.png" alt=" "
                                                 class="img-responsive"/>
                                        </div>
                                        <div class="rating-left">
                                            <img src="${pageContext.request.contextPath}/resource/images/2.png" alt=" "
                                                 class="img-responsive"/>
                                        </div>
                                        <div class="rating-left">
                                            <img src="${pageContext.request.contextPath}/resource/images/2.png" alt=" "
                                                 class="img-responsive"/>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                </div>
                            </div>
                            <h4><a href="${pageContext.request.contextPath}/single.html">壁灯</a></h4>
                            <p>最新时尚壁灯，最新欧美风.</p>
                            <div class="new-collections-grid1-left simpleCart_shelfItem">
                                <p><i>￥480</i> <span class="item_price">￥400</span><a class="item_add"
                                                                                      href="#">加入购物车</a></p>
                            </div>
                        </div>
                    </div>
                    <div class="new-collections-grid1-sub">
                        <div class="new-collections-grid1 animated wow slideInUp">
                            <div class="new-collections-grid1-image">
                                <a href="${pageContext.request.contextPath}/single.html" class="product-image">
                                    <img src="${pageContext.request.contextPath}/resource/images/9.jpg" alt=" "
                                         class="img-responsive"/>
                                </a>
                                <div class="new-collections-grid1-image-pos">
                                    <a href="${pageContext.request.contextPath}/single.html">预览详情</a>
                                </div>
                                <div class="new-collections-grid1-right">
                                    <div class="rating">
                                        <div class="rating-left">
                                            <img src="${pageContext.request.contextPath}/resource/images/2.png" alt=" "
                                                 class="img-responsive"/>
                                        </div>
                                        <div class="rating-left">
                                            <img src="${pageContext.request.contextPath}/resource/images/1.png" alt=" "
                                                 class="img-responsive"/>
                                        </div>
                                        <div class="rating-left">
                                            <img src="${pageContext.request.contextPath}/resource/images/1.png" alt=" "
                                                 class="img-responsive"/>
                                        </div>
                                        <div class="rating-left">
                                            <img src="${pageContext.request.contextPath}/resource/images/1.png" alt=" "
                                                 class="img-responsive"/>
                                        </div>
                                        <div class="rating-left">
                                            <img src="${pageContext.request.contextPath}/resource/images/1.png" alt=" "
                                                 class="img-responsive"/>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                </div>
                            </div>
                            <h4><a href="${pageContext.request.contextPath}/single.html">壁灯</a></h4>
                            <p>最新时尚壁灯，最新欧美风.</p>
                            <div class="new-collections-grid1-left simpleCart_shelfItem">
                                <p><i>￥280</i> <span class="item_price">￥150</span><a class="item_add"
                                                                                      href="#">加入购物车</a></p>
                            </div>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
            <div class="col-md-3 new-collections-grid">
                <div class="new-collections-grid1 animated wow slideInUp">
                    <div class="new-collections-grid1-image">
                        <a href="${pageContext.request.contextPath}/single2.html" class="product-image"><img
                                src="${pageContext.request.contextPath}/resource/images/shaonv1.jpg" alt=" "
                                class="img-responsive"/></a>
                        <div class="new-collections-grid1-image-pos">
                            <a href="${pageContext.request.contextPath}/single2.html">预览详情</a>
                        </div>
                        <div class="new-collections-grid1-right">
                            <div class="rating">
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/2.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/2.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/2.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/1.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/1.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </div>
                    <h4><a href="${pageContext.request.contextPath}/single2.html">女士蓝色流苏衫</a></h4>
                    <p>清新亮丽流苏衫</p>
                    <div class="new-collections-grid1-left simpleCart_shelfItem">
                        <p><i>￥180</i> <span class="item_price">￥120</span>
                            <a class="item_add" href="#">添加到购物车 </a>
                        </p>
                    </div>
                </div>
                <div class="new-collections-grid1 animated wow slideInUp">
                    <div class="new-collections-grid1-image">
                        <a href="${pageContext.request.contextPath}/single.html" class="product-image"><img
                                src="${pageContext.request.contextPath}/resource/images/11.jpg" alt=" "
                                class="img-responsive"/></a>
                        <div class="new-collections-grid1-image-pos">
                            <a href="${pageContext.request.contextPath}/single.html">预览详情</a>
                        </div>
                        <div class="new-collections-grid1-right">
                            <div class="rating">
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/2.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/2.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/2.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/2.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="rating-left">
                                    <img src="${pageContext.request.contextPath}/resource/images/1.png" alt=" "
                                         class="img-responsive"/>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </div>
                    <h4><a href="${pageContext.request.contextPath}/single.html">石质手镯</a></h4>
                    <p>欧美异域风情，舒适手感.</p>
                    <div class="new-collections-grid1-left simpleCart_shelfItem">
                        <p><i>￥340</i> <span class="item_price">￥257</span><a class="item_add" href="#">加入购物车</a></p>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<div class="footer">
    <div class="container">
        <div class="footer-grids">
        </div>
        <div class="footer-logo animated wow slideInUp">
            <h2><a href="${pageContext.request.contextPath}/index.html">Best Store <span>shop anywhere</span></a></h2>
        </div>
        <div class="copy-right animated wow slideInUp">
            <p>Copyright &copy; 2018. Sunxm Zhejiang Sci-Tech University</p>
        </div>
    </div>
</div>
</body>
</html>