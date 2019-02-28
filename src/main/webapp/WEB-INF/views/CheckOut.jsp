<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2019/2/26
  Time: 13:52
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
    <title>TheWebGL</title>
    <% String path = request.getContextPath(); %>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Best Store"/>
    <link href="<%=path %>/resource/css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="<%=path %>/resource/layui/css/layui.css" rel="stylesheet" type="text/css">

    <script src="<%=path %>/resource/js/jquery.min.js"></script>
    <script src="<%=path %>/resource/js/bootstrap-3.1.1.min.js"></script>
    <script src="<%=path %>/resource/layui/layui.js"></script>

    <script type="text/javascript">
        layui.use(['element','layer'],function(){
            var element = layui.element;
            var layer = layui.layer;
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
        });
    </script>
</head>
<body>
<%-- 用户信息显示 --%>
<div class="breadcrumbs">
    <div class="container">
        <ol class="breadcrumb breadcrumb1 animated wow slideInLeft" data-wow-delay=".5s">
            <li><a href="<%=path %>/page/toIndex"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>首页</a></li>
            <li class="active">商品结算页</li>
        </ol>
    </div>
</div>

<h6>收货人信息：</h6>
<dl class="dl-horizontal">
    <dt>姓名：</dt>
    <dd>${User.userName}</dd>
    <dt>邮箱：</dt>
    <dd>${User.userEmail}</dd>
    <dt>地址: </dt>
    <dd>${User.userAddress}</dd>
</dl>


<%-- 商品信息表格 --%>
<div class="checkout-right animated wow slideInUp" data-wow-delay=".5s">
    <table class="timetable_sub">
        <thead>
        <tr>
            <th>编号</th>
            <th>商品</th>
            <th>数量</th>
            <th>商品名称</th>
            <th>快递服务费</th>
            <th>总价</th>
            <th>移除</th>
        </tr>
        </thead>
        <% int i=0;%>
        <c:forEach items="${goodsList}" var="goods">
            <tr class="${goods.goodsId}">
                <td class="invert"><%=i %></td>
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
                                    url:"<%=path %>/Goods/Remove",
                                    data:"userId="+ '${User.userId}'+"&goodsId="+'${goods.goodsId}',
                                    async:false,
                                    success:function (result) {
                                        debugger;
                                        if(result.code == 100){
                                            // 删除成功 结算中心重建
                                            layer.msg("商品移除成功！");
                                            // 表格显示删除
                                            $('.${goods.goodsId}').fadeOut('slow', function (c) {
                                                $('.${goods.goodsId}').remove();
                                            });
                                            check();
                                        }else{
                                            // 删除失败，信息提示。
                                            layer.msg("商品未移除，请重新操作！");
                                        }
                                    }
                                });

                            });
                        });
                        function check(){
                            // 对结算中心进行更新 重新请求
                            debugger;
                            $.ajax({
                                url:"<%=path %>/Goods/updateCart",
                                data:"userId="+"${User.userId}",
                                async:false,
                                success:function (result) {
                                    debugger;
                                    console.log(result);
                                    if(result.code == 100){
                                        //结算中心返回数据解析
                                        resolveResult(result);
                                    }else{
                                        layer.msg("更新结算失败，请重新操作。");
                                    }
                                }
                            })

                        }
                        function resolveResult(result){
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

                            $.each(goodslist,function(index,item){
                                var li = $("<li></li>").append(item.goodsName)
                                    .append($("<i></i>").append(' -- '))
                                    .append(item.goodsAmount)
                                    .append("件")
                                    .append( $("<span></span>").append("￥" + item.goodsAmount * item.goodsPrice));
                                $("#checkBox").append(li);
                            })
                            var FreeLi = $("<li></li>").append("总服务费:").append($("<i></i>"))
                                .append( $("<span></span>").append("￥" + 5*goodslist.length));
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
                var goodsId = parseInt(goodsIdtext.text(),10);
                updateAmount(${User.userId},goodsId,newVal);
                divUpd.text(newVal);
            });

            $('.value-minus').on('click', function () {
                var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10) - 1;

                var goodsIdtext = $(this).parent().find('.goodsIdValue');
                var goodsId = parseInt(goodsIdtext.text(),10);
                if (newVal >= 1){
                    // 数据更新
                    updateAmount(${User.userId},goodsId,newVal);
                    divUpd.text(newVal);
                }
            });
            function updateAmount(userId,goodsId,newVal){
                debugger;
                $.ajax({
                    url:"<%=path %>/Goods/amountChange",
                    data:"userId="+ userId +"&goodsId=" + goodsId +"&amount=" + newVal,
                    async:false,
                    success:function(result){
                        debugger;
                        if(result.code == 100){
                            layer.msg("数量改变成功");
                        }else{
                            layer.msg("数量改变失败，请重新操作");
                        }
                    }
                })
                check();
            }
        </script>
        <!--quantity-->
    </table>
</div>
</body>
</html>
