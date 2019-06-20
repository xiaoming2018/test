<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2018/11/23
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>TheWebGL</title>
    <% String path = request.getContextPath(); %>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Best Store"/>
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.css" rel="stylesheet" type="text/css"
          media="all"/>
    <link href="${pageContext.request.contextPath}/resource/css/style.css" rel="stylesheet" type="text/css"
          media="all"/>
    <script src="${pageContext.request.contextPath}/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/bootstrap-3.1.1.min.js"></script>
    <link href='${pageContext.request.contextPath}/resource/css/font.css' rel='stylesheet' type='text/css'>
    <link href='${pageContext.request.contextPath}/resource/css/font1.css' rel='stylesheet' type='text/css'>

    <script src="${pageContext.request.contextPath}/resource/layui/layui.js"></script>
    <link href="${pageContext.request.contextPath}/resource/layui/css/layui.css" rel="stylesheet" type="text/css">

    <script type="text/javascript">
        layui.use('element', function () {
            var element = layui.element;
        });
        layui.use('upload', function () {
            var $ = layui.jquery,
                upload = layui.upload;
        })
    </script>
</head>
<body>
<div class="breadcrumbs">
    <div class="container">
        <ol class="breadcrumb breadcrumb1 animated wow slideInLeft">
            <li><a href="<%=path%>/page/toIndex"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>主页</a></li>
            <li class="active">编辑个人信息</li>
        </ol>
    </div>
</div>
<!-- register -->
<div class="register">
    <div class="container">
        <h3 class="animated wow zoomIn">编辑个人信息</h3>
        <p class="est animated wow zoomIn">请填写真实信息！</p>
        <div class="login-form-grids">
            <h5 class="animated wow slideInUp">基础信息</h5>
            <form class="animated wow slideInUp" method="post" action="register">
                <div id="message" style="color: #ff0000">${message}</div>
                <input type="text" name="userName" value="${User.userName}" placeholder="姓名：" required=" ">
                <input type="text" name="userPhoneNumber" value="${User.userPhoneNumber}" placeholder="手机号码："
                       required=" ">
                <input type="text" name="userAddress" value="${User.userAddress}" placeholder="地址：" required=" ">

                <h6 class="animated wow slideInUp">个性信息</h6>
                <input type="text" name="userNickname" value="${User.userNickname}" placeholder="昵称：" required=" ">

                <h6 class="animated wow slideInUp">登陆信息</h6>
                <input type="email" name="userEmail" value="${User.userEmail}" placeholder="邮箱:" required=" ">
                <input type="password" name="userPassword" value="${User.userPassword}" placeholder="密码:" required=" ">

                <div class="register-check-box">
                    <div class="check">
                        <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>我接受网站协议条款</label>
                    </div>
                </div>
                <input type="submit" value="提交">
            </form>
        </div>
    </div>
</div>
<!-- footer -->
<div class="footer">
    <div class="container">
        <div class="footer-grids">
            <div class="footer-logo animated wow slideInUp">
                <h2><a href="<%=path%>/page/toIndex">Best Store <span>shop anywhere</span></a></h2>
            </div>
            <div class="copy-right animated wow slideInUp">
                <p>Copyright &copy; 2018.Zhejiang Sci-Tech University</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
