layui.use(['form', 'layer', 'table', 'laytpl', 'upload', 'layedit'], function () {
    var urls = getContextPath();
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table,
        layedit = layui.layedit,
        upload = layui.upload;
    var index = layedit.build('footinfo', {height: 180, border: 1}); //建立编辑器
    var uploadInst = upload.render({
        elem: '#logourl'
        , url: urls + '/manage/uploadimage' //改成您自己的上传接口
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#logourl').attr('src', result); //图片链接（base64）
            });
        }
        , done: function (res) {
            debugger
            //如果上传失败
            if (res.code > 0) {
                return layer.msg('上传失败');
            }
            //上传成功
            //打印后台传回的地址: 把地址放入一个隐藏的input中, 和表单一起提交到后台, 此处略..
            /*   console.log(res.data.src);*/
            $("#hid_logourl").val(res.data.src);
            // window.parent.uploadHeadImage(res.data.src);
            // var demoText = $('#demoText');
            //demoText.html('<span style="color: #8f8f8f;">上传成功!!!</span>');
            //上传成功
        }
        , error: function () {
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    });


    form.on("submit(btn_addbaseinfo)", function (data) {
        debugger
        //获取防伪标记
        $.ajax({
            type: 'POST',
            url: urls + '/manage/editbaseinfo',
            data: {
                id: $("#id").val(),
                logourl: $("#hid_logourl").val(),//主键
                systemtitle: $("#systemtitle").val(),
                footinfo: layedit.getContent(index)
            },
            dataType: "json",
            headers: {},
            success: function (res) {//res为相应体,function为回调函数
                var alertIndex;
                if (res.resultCode == "1" || res.resultCode == "0") {
                    alertIndex = layer.alert(res.msg, {icon: 1}, function () {
                        layer.closeAll("iframe");
                        //刷新父页面
                        // parent.location.reload();
                        top.layer.close(alertIndex);
                    });
                }
                else {
                    layer.alert("操作异常", {icon: 5});
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                debugger
                layer.alert('操作失败！！！' + XMLHttpRequest.status + "|" + XMLHttpRequest.readyState + "|" + textStatus, {icon: 5});
            }
        });
        return false;
    });
});






