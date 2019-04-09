<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2019/4/9
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>adminManagerAdd</title>
    <% String path = request.getContextPath(); %>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Best Store"/>
    <link href="<%=path%>/resource/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="<%=path%>/resource/layui/css/layui.css" rel="stylesheet" type="text/css">

    <script src="<%=path%>/resource/layui/layui.js"></script>
    <script src="<%=path%>/resource/js/jquery3.3.1.js"></script>
</head>
<body>
<br>
<form class="layui-form" id="form_test">
    <div class="layui-form-item">
        <label class="layui-form-label"> 名称：</label>
        <div class="layui-input-block">
            <input type="text" name="managerName" required lay-verify="required"
                   placeholder="请输入管理员名称" autocomplete="off" class="layui-input" style="width: 400px">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label"> 账户：</label>
        <div class="layui-input-block">
            <input type="text" name="managerAccount" required lay-verify="required"
                   placeholder="请输入管理员账户" autocomplete="off" class="layui-input" style="width: 400px">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">登陆密码：</label>
        <div class="layui-input-block">
            <input type="text" name="managerPassword" required lay-verify="pass"
                   placeholder="请输入登录密码" autocomplete="off" class="layui-input" style="width: 400px">
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">邮箱：</label>
        <div class="layui-input-block">
            <input type="text" name="managerEmail" required lay-verify="email"
                   placeholder="请输入邮箱" autocomplete="off" class="layui-input" style="width: 400px">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">联系方式：</label>
        <div class="layui-input-block">
            <input type="text" name="managerPhone" required lay-verify="phoneNumber"
                   placeholder="请输入手机号码" autocomplete="off" class="layui-input" style="width: 400px">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">头像：</label>
        <div class="layui-input-block">
            <input type="hidden" id="pic" name="managerPicture" value="" lay-verify="required"/>
            <div class="layui-input-list">
                <img class="layui-upload-img" id="demo1" style="width: 100px;height: 100px">
                <p id="demoText"></p>
                <br>
                <button type="button" class="layui-btn" id="test1"><i class="layui-icon">&#xe67c;</i>头像上传</button>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo" id="submit">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script>
    //Demo
    var filepath;
    var index = parent.layer.getFrameIndex(window.name);
    layui.use('form', function () {
        var form = layui.form;
        //监听提交
        form.on('submit(formDemo)', function (data) {
            //layer.msg(JSON.stringify(data.field));
            debugger;
            console.log(data.field);
            $.ajax({
                url: "<%=path%>/User/ManagerAdd",
                dataType: 'json',
                data: data.field,
                success: function (data) {
                    if (data.code == 100) {
                        layer.msg("user添加成功");
                        parent.layer.close(index);
                    } else {
                        layer.msg(data.extend.message);
                    }
                },
                error: function () {
                    layer.msg("网络返回数据错误");
                }
            });
            // 取消自动提交；
            return false;
        });
        form.verify({
            pass: [
                /^[\S]{6,12}$/,
                '密码必须6到12位，且不能出现空格'
            ],
            phoneNumber: [
                /^1[3|4|5|7|8][0-9]{9}$/,
                '手机号码格式不正确，请更正'
            ]
        })

    });

    layui.use('upload', function () {
        var upload = layui.upload;
        var uploadInst = upload.render({
            elem: '#test1',
            url: '<%=path%>/file/imageUpLoad',
            acceptMime: 'image/*',
            //auto:false,
            //bindAction:'#submit',
            field: 'goodspic',  // 图片字段名 与 后台接受参数名对应一致
            before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            },
            done: function (res) {
                //如果上传失败
                if (res.code == 200) {
                    return layer.msg('上传失败');
                } else {
                    debugger;
                    filepath = res.extend.filePath;
                    $("#pic").val(filepath); // 设置filepath
                    // 根据name值 为value赋值
                    var span = $("<span style='color: #FF5722;'></span>").append("头像上传成功!");
                    $("#demoText").empty().append(span);
                    return layer.msg('头像上传成功');
                }
            },
            error: function () {
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }
        });
    })
</script>
</body>
</html>
