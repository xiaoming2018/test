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

    <script type="text/javascript" src="<%=path%>/resource/js/jquery-1.8.3.min.js"></script>
    <script src="<%=path%>/resource/js/preloader.js"></script>
    <link rel="stylesheet" href="<%=path%>/resource/css/style_error.css">
    <link href='http://fonts.googleapis.com/css?family=Finger+Paint' rel='stylesheet' type='text/css'>
    <script type="text/javascript" src="<%=path%>/resource/js/css_browser_selector.js"></script>
    <script type="text/javascript" src="<%=path%>/resource/js/plax.js"></script>
    <script type="text/javascript" src="<%=path%>/resource/js/jquery.spritely-0.6.1.js"></script>
    <script type="text/javascript" src="<%=path%>/resource/js/jquery-animate-css-rotate-scale.js"></script>
    <script type="text/javascript" src="<%=path%>/resource/js/script_error.js"></script>

</head>
<body>
<div id="indicator"></div>
<div class="wrapper">
    <div class="sky init">
        <div id="clouds" class="clouds init"></div>
    </div>

    <div class="convas init">
        <div id="mountain" class="mountain"></div>
        <div class="ground"></div>
        <div class="holder">
            <div class="rocks"></div>
            <div class="work-sign"></div>
            <div class="text-sign">
                <div class="text font">
                    We're Working Hard...return now
                </div>
            </div>
            <div class="init hole">
                <div class="sweat"></div>
                <div class="worker swing "></div>
                <div class="ground-bottom">
                    <div class="cleaner"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<% response.setHeader("refresh", "5;/ChatRobot/page/adminIndex"); %>
</body>
</html>
