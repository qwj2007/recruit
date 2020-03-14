layui.use(['form', 'layer', 'laydate'], function () {
    var laydate = layui.laydate;
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    var urls = getContextPath();
    //自定义格式
    //laydate.render({
    //  elem: '#begintime'
    //   ,format: 'yyyy-MM-dd'
    // });
    //  laydate.render({
    //     elem: '#endtime'
    //     ,format: 'yyyy-MM-dd'
    //  });
    form.on("submit(addManager)", function (data) {

        //获取防伪标记
        $.ajax({
            type: 'POST',
            url: urls + '/manage/editnews',
            data: {
                id: $("#id").val(),//主键
                title: $("#title").val(),
                contents: $("#contents").val()
            },
            dataType: "json",
            headers: {
                // "X-CSRF-TOKEN": $("input[name='AntiforgeryKey']").val()
            },
            success: function (res) {//res为相应体,function为回调函数
                var alertIndex;
                if (res.resultCode == "1" || res.resultCode == "0") {
                    alertIndex = layer.alert(res.msg, {icon: 1}, function () {
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
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
    form.verify({
        username: function (value, item) { //value：表单的值、item：表单的DOM对象
            if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                return '用户名不能有特殊字符';
            }
            if (/(^\_)|(\__)|(\_+$)/.test(value)) {
                return '用户名首尾不能出现下划线\'_\'';
            }
            if (/^\d+\d+\d$/.test(value)) {
                return '用户名不能全为数字';
            }
        }

        //我们既支持上述函数式的方式，也支持下述数组的形式
        //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
        , pass: [
            /^[\S]{6,12}$/
            , '密码必须6到12位，且不能出现空格'
        ]
    });
});