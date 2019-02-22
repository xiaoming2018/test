<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2019/2/21
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TheWebGL</title>
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

    <!-- div样式设置 用来设置3D模型展示 -->
    <style type="text/css">
        div#canvas-frame {
            border: none;
            cursor: pointer;
            width: 600px;
            height: 500px;
        }
    </style>

    <script type="text/javascript">
        layui.use('element',function(){
            var element = layui.element;
        })
        $(function () {
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
                                <dd><a href="">退出</a></dd>
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
    <!-- 模态框的加载 展示模型-->
    <div id="myModal" class="modal fade man_shirt" tabindex="-1" role="dialog">
        <div class="modal-dialog" style="height:500px ; width:600px" role="document">
            <div class="modal-content" style="height:500px ; width:600px">
                <div id="canvas-frame"></div> <!-- 隐藏div -->
            </div>
        </div>
    </div>

    <!-- breadcrumbs -->
    <div class="breadcrumbs">
        <div class="container">
            <ol class="breadcrumb breadcrumb1 animated wow slideInLeft" data-wow-delay=".5s">
                <li><a href="index.html"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>首页</a></li>
                <li class="active">详情</li>
            </ol>
        </div>
    </div>

    <div class="single">
        <div class="container">
            <div class="col-md-4 products-left">
                <div class="filter-price animated wow slideInUp" data-wow-delay=".5s">
                    <h3>通过价格筛选</h3>
                    <ul class="dropdown-menu1">
                        <li><a href="javavoid(0);">
                            <div id="slider-range"></div>
                            <input type="text" id="amount" style="border: 0"/>
                        </a></li>
                    </ul>
                    <script type='text/javascript'>
                    $(window).load(function () {
                        $("#slider-range").slider({
                            range: true,
                            min: 0,
                            max: 10000,
                            values: [100, 2000],
                            slide: function (event, ui) {
                                $("#amount").val("$" + ui.values[0] + " - $" + ui.values[1]);
                            }
                        });
                        $("#amount").val("$" + $("#slider-range").slider("values", 0) + " - $" + $("#slider-range").slider("values", 1));


                    });
                    </script>
                    <script type="text/javascript" src="<%=path %>/resource/js/jquery-ui.min.js"></script>
                </div>
                <div class="categories animated wow slideInUp" data-wow-delay=".5s">
                    <h3>类别</h3>
                    <ul class="cate">
                        <li><a href="products.html">热卖</a> <span>(15)</span></li>
                        <li><a href="products.html">收藏</a> <span>(16)</span></li>
                        <ul>
                            <li><a href="products.html">炊具</a> <span>(2)</span></li>
                            <li><a href="products.html">新品</a> <span>(0)</span></li>
                            <li><a href="products.html">家装</a> <span>(1)</span></li>
                        </ul>
                        <li><a href="products.html">饰品</a> <span>(15)</span></li>
                        <ul>
                            <li><a href="products.html">壁钟</a> <span>(2)</span></li>
                            <li><a href="products.html">新品</a> <span>(0)</span></li>
                            <li><a href="products.html">灯具</a> <span>(1)</span></li>
                            <li><a href="products.html">名品店</a> <span>(0)</span></li>
                        </ul>
                    </ul>
                </div>
                <div class="men-position animated wow slideInUp" data-wow-delay=".5s">
                    <a href="single.html"><img src="<%=path %>/resource/images/29.jpg" alt=" " class="img-responsive"/></a>
                    <div class="men-position-pos">
                        <h4>夏季新品</h4>
                        <h5><span>55%</span>超强折扣</h5>
                    </div>
                </div>
            </div>
            <div class="col-md-8 single-right">
                <div class="col-md-5 single-right-left animated wow slideInUp" data-wow-delay=".5s">
                    <div class="flexslider">
                        <ul class="slides">
                            <li data-thumb="<%=path %>/${Goods.goodsPicture}">
                                <div class="thumb-image">
                                    <img src="<%=path %>/${Goods.goodsPicture}" data-imagezoom="true" class="img-responsive">
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-7 single-right-left simpleCart_shelfItem animated wow slideInRight" data-wow-delay=".5s">
                    <h3>${Goods.goodsName}</h3>
                    <button id="man_shirt" class="btn btn-success"> 3D商品展示</button>
                    <h4>${Goods.goodsPrice}</h4>
                    <div class="rating1">
						<span class="starRating">
							<input id="rating5" type="radio" name="rating" value="5">
							<label for="rating5">5</label>
							<input id="rating4" type="radio" name="rating" value="4">
							<label for="rating4">4</label>
							<input id="rating3" type="radio" name="rating" value="3" checked>
							<label for="rating3">3</label>
							<input id="rating2" type="radio" name="rating" value="2">
							<label for="rating2">2</label>
							<input id="rating1" type="radio" name="rating" value="1">
							<label for="rating1">1</label>
						</span>
                    </div>
                    <div class="description">
                        <h5><i>${Goods.goodsDesc}</i></h5>
                        <p> 让您尽享时尚、休闲夹克。
                            施有特殊染色加工，有助于呈现出更富有韵味的色系。
                            本季的编织工艺更为考究，具有恰到好处的厚实质感，适合搭配叠穿。
                            纽扣领的款型，商务休闲两相宜。
                            下摆两侧、领部内侧的赤耳滚边布带，洋溢着复古气息。
                        </p>
                    </div>
                    <div class="color-quality">
                        <div class="color-quality-left">
                            <h5>颜色 : </h5>
                            <ul>
                                <li><a href="#"><span></span>红色</a></li>
                                <li><a href="#" class="brown"><span></span>蓝色</a></li>
                                <li><a href="#" class="purple"><span></span>紫色</a></li>
                                <li><a href="#" class="gray"><span></span>粉红色</a></li>
                            </ul>
                        </div>
                        <div class="color-quality-right">
                            <h5>品质 :</h5>
                            <select id="country1" onchange="change_country(this.value)" class="frm-field required sect">
                                <option value="null">5 分</option>
                                <option value="null">6 分</option>
                                <option value="null">7 分</option>
                                <option value="null">10 分</option>
                            </select>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div class="occasional"></div>
                    <div class="occasion-cart">
                        <button class="btn btn-success" id="item_add" >加入购物车</button>
                        <button class="btn btn-danger" id="item_buy">立即购买</button>
                    </div>
                </div>
                <script type="text/javascript">
                    $("#man_shirt").click(function () {
                        $("#canvas-frame").empty(); // 数据清空(模态框每次加载时，需要清空数据)
                        $('#myModal').modal({
                            keyboard: false
                        });
                        start();  //调用渲染函数
                    })
                    $("#item_add").click(function(){
                        alert("点击加入购物车");
                        debugger;
                        var message = "${sessionScope.get("message")}";
                        if(message.length == 0){
                            layer.msg("请先登录网站");
                        }else{
                            var userId = ${sessionScope.get("User")}.userId;
                            var goodsId = ${Goods.goodsId};
                            var goodsamount = 1;
                            debugger;
                            $.ajax({
                                url:"<%=path %>/Goods/add",
                                data:"userId="+userId+"goodsId="+goodsId+"goodsamount="+goodsamount,
                                success:function (result) {
                                    if(result.code == 100){
                                        alert("succes")
                                    }else{
                                        alert("failed")
                                    }
                                }
                            });

                        }
                    })
                </script>

                <div class="clearfix"></div>
                <div class="bootstrap-tab animated wow slideInUp" data-wow-delay=".5s">
                    <div class="bs-example bs-example-tabs" role="tabpanel" data-example-id="togglable-tabs">
                        <ul id="myTab" class="nav nav-tabs" role="tablist">
                            <li role="presentation" class="active">
                                <a href="#home" id="home-tab" role="tab" data-toggle="tab" aria-controls="home"
                                   aria-expanded="true">描述</a>
                            </li>
                            <li role="presentation">
                                <a href="#profile" role="tab" id="profile-tab" data-toggle="tab"
                                   aria-controls="profile">评论（2）</a>
                            </li>
                            <li role="presentation" class="dropdown">
                                <a href="#" id="myTabDrop1" class="dropdown-toggle" data-toggle="dropdown"
                                   aria-controls="myTabDrop1-contents">相关信息 <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1" id="myTabDrop1-contents">
                                    <li><a href="#dropdown1" tabindex="-1" role="tab" id="dropdown1-tab" data-toggle="tab"
                                           aria-controls="dropdown1">面料</a>
                                    </li>
                                    <li><a href="#dropdown2" tabindex="-1" role="tab" id="dropdown2-tab" data-toggle="tab"
                                           aria-controls="dropdown2">设计</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <div role="tabpanel" class="tab-pane fade in active bootstrap-tab-text" id="home"
                                 aria-labelledby="home-tab">
                                <h5>商品主要描述</h5>
                                <p>让您尽享时尚、休闲夹克。
                                    施有特殊染色加工，有助于呈现出更富有韵味的色系。
                                    本季的编织工艺更为考究，具有恰到好处的厚实质感，适合搭配叠穿。
                                    商务休闲两相宜。
                                    下摆两侧、领部内侧的赤耳滚边布带，洋溢着复古气息。
                                </p>
                            </div>
                            <div role="tabpanel" class="tab-pane fade bootstrap-tab-text" id="profile"
                                 aria-labelledby="profile-tab">
                                <div class="bootstrap-tab-text-grids">
                                    <div class="bootstrap-tab-text-grid">
                                        <div class="bootstrap-tab-text-grid-left">
                                            <img src="<%=path %>/resource/images/4.png" alt=" " class="img-responsive"/>
                                        </div>
                                        <div class="bootstrap-tab-text-grid-right">
                                            <ul>
                                                <li><a href="#">一颗红烧肉</a></li>
                                                <li><a href="#">
                                                    <span class="glyphicon glyphicon-share" aria-hidden="true"></span>回复</a>
                                                </li>
                                            </ul>
                                            <p>挺好的价格非常美丽 比平时划算 物美价廉 质量一如既往的好</p>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="bootstrap-tab-text-grid">
                                        <div class="bootstrap-tab-text-grid-left">
                                            <img src="<%=path %>/resource/images/5.png" alt=" " class="img-responsive"/>
                                        </div>
                                        <div class="bootstrap-tab-text-grid-right">
                                            <ul>
                                                <li><a href="#">伤心天涯人</a></li>
                                                <li><a href="#"><span class="glyphicon glyphicon-share"
                                                                      aria-hidden="true"></span>回复</a></li>
                                            </ul>
                                            <p>女朋友对衣服很满意，大小刚好，穿起来舒适又有型.</p>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="add-review">
                                        <h4>评论</h4>
                                        <%-- 邮箱信息回复 --%>
                                        <form action="#">
                                            <input type="text" value="名称" onfocus="this.value = '';"
                                                   onblur="if (this.value == '') {this.value = 'name';}" required="">
                                            <input type="email" value="邮箱" onfocus="this.value = '';"
                                                   onblur="if (this.value == '') {this.value = '';}" required="">
                                            <input type="text" value="主题" onfocus="this.value = '';"
                                                   onblur="if (this.value == '') {this.value = 'Subject';}" required="">
                                            <textarea type="text" onfocus="this.value = '';"
                                                      onblur="if (this.value == '') {this.value = '';}"
                                                      required="">内容...</textarea>
                                            <input type="submit" value="发送">
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane fade bootstrap-tab-text" id="dropdown1"
                                 aria-labelledby="dropdown1-tab">
                                <p>亚麻布是经亚麻捻成线织成的，表面不像化纤和棉布那样平滑，具有生动的凹凸纹理，画家常常利用亚麻布这一微妙有趣的材质美感。同时除合成纤维外，亚麻布是纺织品中最结实的一种。</p>
                            </div>
                            <div role="tabpanel" class="tab-pane fade bootstrap-tab-text" id="dropdown2"
                                 aria-labelledby="dropdown2-tab">
                                <p>原创设计作品，产品图案已经登记版权，翻版必究。</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    <!-- footer -->
    <div class="footer">
        <div class="container">
            <div class="footer-grids"></div>
            <div class="footer-logo animated wow slideInUp" data-wow-delay=".5s">
                <h2><a href="index.html">Best Store <span>shop anywhere</span></a></h2>
            </div>
            <div class="copy-right animated wow slideInUp" data-wow-delay=".5s">
                <p>Copyright &copy; 2018. Sunxm Zhejiang Sci-Tech University</p>
            </div>
        </div>
    </div>
</body>
</html>
