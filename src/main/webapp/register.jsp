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

    <%--页面js代码--%>
    <script type="text/javascript">

    </script>
</head>

<body>
<div class="breadcrumbs">
    <div class="container">
        <ol class="breadcrumb breadcrumb1 animated wow slideInLeft">
            <li><a href="index.html"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>主页</a></li>
            <li class="active">注册</li>
        </ol>
    </div>
</div>
<!-- register -->
<div class="register">
    <div class="container">
        <h3 class="animated wow zoomIn">注册</h3>
        <p class="est animated wow zoomIn">请填写真实信息！</p>
        <div class="login-form-grids">
            <h5 class="animated wow slideInUp">基础信息</h5>
            <form class="animated wow slideInUp" method="post" action="servlet/register">
                <input type="text" name="userName" placeholder="姓名：" required=" ">
                <input type="text" name="userPhoneNumber" placeholder="手机号码：" required=" ">
                <input type="text" name="userAddress" placeholder="地址：" required=" ">

                <h6 class="animated wow slideInUp">个性信息</h6>
                <input type="text" name="userNickname" placeholder="昵称：" required=" ">

                <h6 class="animated wow slideInUp">登陆信息</h6>
                <input type="email" name="userEmail" placeholder="邮箱:" required=" ">
                <input type="password" name="userPassword" placeholder="密码:" required=" ">
                <input type="password" name="userPassword2" placeholder="重复密码:" required=" ">
                <div class="register-check-box">
                    <div class="check">
                        <label class="checkbox"><input type="checkbox" name="checkbox"><i> </i>我接受网站协议条款</label>
                    </div>
                </div>
                <input type="submit" value="注册">
            </form>
        </div>
    </div>
</div>
<!-- footer -->
<div class="footer">
    <div class="container">
        <div class="footer-grids">
            <div class="footer-logo animated wow slideInUp">
                <h2><a href="index.html">Best Store <span>shop anywhere</span></a></h2>
            </div>
            <div class="copy-right animated wow slideInUp">
                <p>Copyright &copy; 2018. Sunxm Zhejiang Sci-Tech University</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
