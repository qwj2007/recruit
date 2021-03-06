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
        url: urls + '/manage/loadBanner/',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 10,
        id: "managerListTable",
        cols: [[
            //{type: "checkbox", fixed: "left", width: 10},
            {field: "id", title: 'id', width: 50, align: "center"},
            {
                field: 'imageurl', title: '图片地址', minWidth: 50,minHeight:100, align: "center", templet: function (d) {
                var imgurl = '<img src="' + d.imageurl + '" alt="" >'
                return imgurl;
            }
            },
            {field: "isdisplay", title: '是否显示', width: 100, align: "center",templet:function (d) {
              return  d.isdisplay?"是":"否";
            }},
            {title: '操作', minWidth: 50, templet: '#managerListBar', fixed: "right", align: "center"}
        ]]
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click", function () {
        if ($(".searchVal").val() !== '') {
            table.reload("managerListTable", {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: $(".searchVal").val()  //搜索的关键字
                }
            });
        } else {
            layer.msg("请输入搜索的内容");
        }
    });

    //添加用户
    function addManager(edit) {
        var urls = getContextPath() + "/manage/uploadbanner";
        var tit = "上传图片";
        var index = layui.layer.open({
            title: tit,
            type: 2,
            anim: 1,
            area: ['800px', '90%'],
            content: urls,
            success: function (layero, index) {
            }
        });
    }

    $(".addManager_btn").click(function () {
        addManager();
    });

    //批量删除
    $("#delAll_btn").click(function () {
        var checkStatus = table.checkStatus('managerListTable'),
            data = checkStatus.data,
            ids = [];
        if (data.length > 0) {
            for (var i in data) {
                ids.push(data[i].id);
            }
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
                //获取防伪标记
                del(ids.join(','));
            });
        } else {
            layer.msg("请选择需要删除的用户");
        }
    });
    //批量删除
    $("#btn_resert").click(function () {
        var checkStatus = table.checkStatus('managerListTable'),
            data = checkStatus.data,
            ids = [];
        if (data.length > 0) {
            for (var i in data) {
                ids.push(data[i].id);
            }
            layer.confirm('确定重置用户的密码？', {icon: 3, title: '提示信息'}, function (index) {
                //获取防伪标记
                uppwd(ids.join(','));
            });
        } else {
            layer.msg("请选择用户");
        }
    });

    function updateBanner(id,flag) {
        var urls = getContextPath();
        $.ajax({
            type: 'POST',
            url: urls + '/manage/updateBanner/',
            data: {id: id,flag:flag},
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

    //列表操作
    table.on('tool(managerList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'edit0') { //不显示
            update(data.id,"1");//显示操作
        }
        else if(layEvent==="edit1"){
            update(data.id,"0");//不显示操作
        }
        else if (layEvent === 'del') { //删除
            layer.confirm('确定删除？', {icon: 3, title: '提示信息'}, function (index) {
                update(data.id,'');
            });
        }
    });


    function update(id,flag) {
        var urls = getContextPath();
        $.ajax({
            type: 'POST',
            url: urls + '/manage/updateBanner/',
            data: {id: id,flag:flag},
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

    /**13位时间戳转换成 年月日 上午 时间  2018-05-23 10:41:08 */
    function createTime(v) {
        return new Date(parseInt(v)).toLocaleString()
    }

    /**重写toLocaleString方法*/
    Date.prototype.toLocaleString = function () {
        var y = this.getFullYear();
        var m = this.getMonth() + 1;
        m = m < 10 ? '0' + m : m;
        var d = this.getDate();
        d = d < 10 ? ("0" + d) : d;
        var h = this.getHours();
        h = h < 10 ? ("0" + h) : h;
        var M = this.getMinutes();
        M = M < 10 ? ("0" + M) : M;
        var S = this.getSeconds();
        S = S < 10 ? ("0" + S) : S;
        //  return y+"-"+m+"-"+d+" "+h+":"+M+":"+S;
        return y + "-" + m + "-" + d;
    };


});
