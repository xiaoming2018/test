<%--
  Created by IntelliJ IDEA.
  User: sun xiaoming
  Date: 2018/11/22
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Avatar upload</title>
    <script src="${pageContext.request.contextPath}/resource/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery.Jcrop.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery.color.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery.form.js"></script>
    <link   href="${pageContext.request.contextPath}/resource/css/jquery.Jcrop.css" rel="stylesheet" type="text/css">

    <style type="text/css">
        /* 控制预览区域大小*/
        #preview-pane .preview-container {
            width: 110px;
            height: 110px;
            overflow: hidden;
        }

        #targetDiv {
            width: 400px;
            height: 400px;
            background-color: #f7fdff;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $("#file_upload").change(function () {
                debugger;
                $("#msg").text('');
                var oFile = $(this)[0].files[0];
                //判断上传文件大小
                if (oFile.size > 1 * 1024 * 1024) {
                    $("#msg").text('你选择了太大的文件，请选择一个1M以下的图像文件').css('color', 'red');
                    $(this).val("");
                    return;
                }
                //判断类型
                var filepath = $(this).val();
                var extStart = filepath.lastIndexOf(".");
                var ext = filepath.substring(extStart, filepath.length).toUpperCase();
                if (ext != ".JPEG" && ext != ".PNG" && ext != ".JPG") {
                    $("#msg").text('请选择一个有效的图像文件(jpg,png是允许的)').css('color', 'red');
                    $(this).val("");
                    return;
                }
                $("#fileUp").ajaxSubmit({
                    type: "POST",
                    url: "file/img/upload",
                    dataType: "json",
                    contentType: "application/json",
                    success: function (parameter) {
                        $("#target2").attr('src', 'upload/' + parameter.fileName);
                        $("#filePathInput").val('upload/' + parameter.fileName);
                        if ($("#format").text() == "重新上传") {
                            jcrop_api.destroy()
                        }
                        $("#format").text("重新上传");
                        //启动jcrop支持
                        openJcrop('upload/' + parameter.fileName);
                    },
                    error: function (data) {
                        alert("ajax传输发生错误！！！");
                    }
                });
            });

            //启动jcrop
            function openJcrop(imgPath) {
                //启动jcrop支持
                var boundx, boundy,
                    xsize = $('#preview-pane .preview-container').width(),
                    ysize = $('#preview-pane .preview-container').height();

                $('#target').Jcrop({
                    minSize: [110, 110],
                    onChange: updatePreview,
                    onSelect: updatePreview,
                    aspectRatio: xsize / ysize
                }, function () {
                    // Use the API to get the real image size
                    var bounds = this.getBounds();
                    boundx = bounds[0];
                    boundy = bounds[1];
                    jcrop_api = this;
                });
                jcrop_api.setImage(imgPath);

                function updatePreview(c) {
                    if (parseInt(c.w) > 0) {
                        var rx = xsize / c.w;
                        var ry = ysize / c.h;
                        $('#preview-pane .preview-container img').css({
                            width: Math.round(rx * boundx) + 'px',
                            height: Math.round(ry * boundy) + 'px',
                            marginLeft: '-' + Math.round(rx * c.x) + 'px',
                            marginTop: '-' + Math.round(ry * c.y) + 'px'
                        });
                        $("#w").val(c.w);
                        $("#h").val(c.h);
                        $("#x").val(c.x);
                        $("#y").val(c.y);
                    }
                }
            }
        });

        function photoSummit() {
            //alert($("#w").val()+","+$("#h").val()+","+$("#x").val()+","+$("#y").val());
            //$("#fileUp").attr("action", "/file/img/upload").submit();
            if ($("#w").val() > 0 && $("#h").val() > 0) {
                $("#fileUp").ajaxSubmit({
                    type: "POST",
                    url: "file/img/cutandscale",
                    dataType: "json",
                    contentType: "application/json",
                    success: function (data) {
                        $("#msg").text('上传头像成功！！！').css('color', 'red');
                        //alert($("#filePathInput").val());
                        window.parent.back($("#filePathInput").val());
                    },
                    error: function (data) {
                        alert("ajax传输发生错误！！！");
                    }
                });
            } else {
                $("#msg").text('请用鼠标截取图片').css('color', 'red');
            }
        }
    </script>
</head>
<body>
<dl class="dialogBox D_uploadLogo">
    <dt class="dialogHeader">
        <span class="title">头像上传</span>
    </dt>
    <dd class="dialogBody">
        <dl class="bisinessLogo">
            <dt class="title">预览</dt>
            <dd class="img">
                <div id="preview-pane">
                    <div class="preview-container">
                        <img src="" id="target2" class="jcrop-preview" alt="未选择图片"/>
                    </div>
                </div>
            </dd>
            <dd class="tc">尺寸：110*110px</dd>
        </dl>
        <dl class="bisinessInfo">
            <dt class="btnBox02">
                <form id="fileUp" action="/file/img/upload" method="post" enctype="multipart/form-data" target="ifm">
                    <a class="btnGray" href="javascript:">
                        <span class="text" id="format">选择图片</span>
                        <b class="bgR"></b>
                        <input type="file" id="file_upload" class="inputFile" name="userphoto"/>
                        <input type="hidden" id="w" name="w"/>
                        <input type="hidden" id="h" name="h"/>
                        <input type="hidden" id="x" name="x"/>
                        <input type="hidden" id="y" name="y"/>
                    </a>
                </form>
            </dt>
            <dd class="info">
                请从本地选择一张照片，支持jpg,png格式  <span id="msg"></span>
                <div id="targetDiv">
                    <img src="" id="target" width="400" height="400" alt="未选择图片"/>
                </div>
            </dd>
        </dl>
    </dd>
    <input type="hidden" id="filePathInput" value=""/>

    <dd class="dialogBottom">
        <a class="btnBlue btn_confirm" href="javascript:" onclick="photoSummit();"><span class="text">确定</span><b
                class="bgR"></b></a>
        <a class="btnGray btn_cancel" href="javascript:" onclick="hideDialog();"><span class="text">取消</span><b
                class="bgR"></b></a>
    </dd>
</dl>
</body>
</html>
