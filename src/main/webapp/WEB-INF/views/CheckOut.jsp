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

</body>
</html>
