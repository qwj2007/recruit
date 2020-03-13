layui.use(['form', 'layer', 'table', 'laytpl', 'upload', 'layedit'], function () {
    var urls = getContextPath();
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table,
        layedit = layui.layedit;

    var index = layedit.build('contents', {height: 180, border: 1}); //建立编辑器

    form.on("submit(addManager)", function (data) {
        //获取防伪标记
        $.ajax({
            type: 'POST',
            url: urls + '/manager/editemail',
            data: {
                id: $("#id").val(),
                emailaddress: $("#emailaddress").val(),//主键
                emailpwd: $("#emailpwd").val(),
                sendservice: $("#sendservice").val(),
                contents:layedit.getContent(index)            },
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






