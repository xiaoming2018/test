<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2018/11/20
  Time: 21:37
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
    <script src="${pageContext.request.contextPath}/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/bootstrap-3.1.1.min.js"></script>
    <link href='${pageContext.request.contextPath}/resource/css/font.css' rel='stylesheet' type='text/css'>
    <link href='${pageContext.request.contextPath}/resource/css/font1.css' rel='stylesheet' type='text/css'>
</head>

<script type="text/javascript">
    $(function(){
        $("#login1").click(function () {
            var email = $("#email").val();
            var password = $("#password").val();
            $.ajax({
                type: "post",
                url: "servlet/index",
                data: {"email": email, "password": password},
                success: function (result) {
                    alert(12345);
                    debugger;
                    console.log(result);
                    if(result.code == 200){
                        alert(result.extend.message);
                        $("#message").text(result.extend.message);
                    }else if(result.code == 100){
                        alert("登陆成功!");
                        //进行页面跳转 到home
                        window.location.href='index.html';
                    }
                },
                dataType: "json",
                async: true
            });
            return false; // 取消默认请求发送
        });
    })
</script>
<body>
<div class="breadcrumbs">
    <div class="container">
        <ol class="breadcrumb breadcrumb1 animated wow slideInLeft">
            <li><a href="${pageContext.request.contextPath}/index.html"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>主页</a></li>
            <li class="active">登陆</li>
        </ol>
    </div>
</div>
<!-- login -->
<div class="login">
    <div class="container">
        <h3 class="animated wow zoomIn">登陆</h3>
        <p class="est animated wow zoomIn" >请使用邮箱及密码登录！</p>
        <div class="login-form-grids animated wow slideInUp">
            <div id="message" style="color: #ff0000">${message}</div>
            <form action="servlet/index" method="post">
                <input type="email" id="email" name="email" placeholder="邮箱" required=" ">
                <input type="password" id="password" name="password" placeholder="密码" required=" ">
                <div class="forgot">
                    <a href="register.html">Forgot Password?</a>
                </div>
                <input type="submit" id="login" value="登陆">
            </form>
            <div id="content"></div>
        </div>
        <h4 class="animated wow slideInUp" >未注册账号</h4>
        <p class="animated wow slideInUp" ><a href="register.jsp">前往注册</a> 或者 返回
            <a href="${pageContext.request.contextPath}/index.html">主页<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></a></p>
    </div>
</div>

<!-- footer -->
<div class="footer">
    <div class="container">
        <div class="footer-grids">
            <div class="footer-logo animated wow slideInUp" >
                <h2><a href="${pageContext.request.contextPath}/index.html">Best Store <span>shop anywhere</span></a></h2>
            </div>
            <div class="copy-right animated wow slideInUp" >
                <p>Copyright &copy; 2018. Zhejiang Sci-Tech University</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>

