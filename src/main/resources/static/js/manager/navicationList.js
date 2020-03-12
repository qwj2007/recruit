layui.use(['form', 'layer', 'table', 'laytpl'], function () {
    var urls = getContextPath();
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;
    //招聘信息
    var tableIns = table.render({
        elem: '#managerList',
        url: urls + '/manager/loadNavication/',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 10,
        id: "managerListTable",
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: "id", title: 'id', width: 50, align: "center"},
            {field: 'name', title: '名称', minWidth: 50, align: "center"},
            {field: 'url', title: '连接地址', minWidth: 50, align: "center"},
            {field: 'orders', title: '顺序', minWidth: 50, align: "center"},
            {
                field: 'isdisplay', title: '是否显示', minWidth: 50, align: "center", templet: function (d) {
                if (d.isdisplay) {
                    return '<span style="color: #0d5f9b">是</span>';
                }
                else {
                    return '<span style="color: #0d5f9b">否</span>';
                }
            }
            },

            {title: '操作', minWidth: 80, templet: '#managerListBar', fixed: "right", align: "center"}
        ]]
    });

    //添加
    function addManager(edit) {
        var urls = getContextPath() + "/manager/navicationEdit";
        var tit = "添加招聘信息";
        if (edit) {
            tit = "编辑招聘信息";
            urls += "?id=" + edit.id;
        }
        else {
            urls += "?id=0";
        }
        var index = layui.layer.open({
            title: tit,
            type: 2,
            anim: 1,
            area: ['500px', '90%'],
            content: urls,
            success: function (layero, index) {
            }
        });
    }

    $(".addManager_btn").click(function () {
        addManager();
    });

    //批量删除
    $(".delAll_btn").click(function () {
        var checkStatus = table.checkStatus('managerListTable'),
            data = checkStatus.data,
            ids = [];
        if (data.length > 0) {
            for (var i in data) {
                ids.push(data[i].id);
            }
            layer.confirm('确定删除选中的信息？', {icon: 3, title: '提示信息'}, function (index) {
                //获取防伪标记
                del(ids.join(','));
            });
        } else {
            layer.msg("请选择需要删除的用户");
        }
    });

    //列表操作
    table.on('tool(managerList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'edit') { //编辑
            addManager(data);
        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此招聘信息？', {icon: 3, title: '提示信息'}, function (index) {
                del(data.id);
            });
        }
    });

    form.on('switch(IsLock)', function (data) {
        var tipText = '确定锁定当前用户吗？';
        if (!data.elem.checked) {
            tipText = '确定启用当前用户吗？';
        }
        layer.confirm(tipText, {
            icon: 3,
            title: '系统提示',
            cancel: function (index) {
                data.elem.checked = !data.elem.checked;
                form.render();
                layer.close(index);
            }
        }, function (index) {
            changeLockStatus(data.value, data.elem.checked);
            layer.close(index);
        }, function (index) {
            data.elem.checked = !data.elem.checked;
            form.render();
            layer.close(index);
        });
    });

    function del(id) {
        var urls = getContextPath();
        $.ajax({
            type: 'POST',
            url: urls + '/manager/deleteNavication/',
            data: {id: id},
            dataType: "json",
            headers: {
                //"X-CSRF-TOKEN-yilezhu": $("input[name='AntiforgeryKey_yilezhu']").val()
            },
            success: function (data) {//res为相应体,function为回调函数
                layer.msg(data.msg, {
                    time: 1000 //1s后自动关闭
                }, function () {
                    tableIns.reload();
                    layer.close(index);
                });
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                layer.alert('操作失败！！！' + XMLHttpRequest.status + "|" + XMLHttpRequest.readyState + "|" + textStatus, {icon: 5});
            }
        });
    }

    function changeLockStatus(managerId, status) {
        $.ajax({
            type: 'POST',
            url: '/Manager/ChangeLockStatus/',
            data: {Id: managerId, Status: status},
            dataType: "json",
            headers: {
                "X-CSRF-TOKEN-yilezhu": $("input[name='AntiforgeryKey_yilezhu']").val()
            },
            success: function (data) {//res为相应体,function为回调函数
                layer.msg(data.ResultMsg, {
                    time: 2000 //2s后自动关闭
                }, function () {
                    tableIns.reload();
                    layer.close(index);
                });
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                layer.alert('操作失败！！！' + XMLHttpRequest.status + "|" + XMLHttpRequest.readyState + "|" + textStatus, {icon: 5});
            }
        });
    }

});
