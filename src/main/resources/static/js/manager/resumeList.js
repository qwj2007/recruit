layui.use(['form', 'layer', 'table', 'laytpl'], function () {
    var urls = getContextPath();
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;
    //招聘信息
    var tableIns = table.render({
        elem: '#resumeList',
        url: urls + '/manage/loadresumeList/',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 10,
        method:'post',
        id: "managerListTable",
        cols: [
            [
                {type: "checkbox", fixed: "left", width: 50},
                {field: "id", title: 'id', width: 50, align: "center"},
                {
                    field: 'truename', title: '姓名', minWidth: 50, align: "center", templet: function (d) {
                    var previewurl = urls + "/resume/preview?type=0&resumesid=" + d.id;
                    return '<a href="' + previewurl + '" target="_blank" style="color: #0d5f9b">' + d.truename + '</a>';
                }
                },
                {field: 'applyworkname', title: '应聘岗位', minWidth: 20, align: "center"},
                {field: 'nation', title: '民族', minWidth: 50, align: "center"},
                {field: 'sex', title: '性别', minWidth: 100, align: "center"},
                {field: 'age', title: '年龄', minWidth: 20, align: "center"},
                {field: 'education', title: '学历', minWidth: 50, align: "center"},
                {field: 'academicdegree', title: '学位', minWidth: 50, align: "center"},
                {field: 'locationpersonnelrelationship', title: '人事关系所在地', minWidth: 100, align: "center"},
                {field: 'registeredresidence', title: '户口所在地', minWidth: 100, align: "center"},
                {field: "recruitinfoid", title: 'recruitinfoid', width: 50, align: "center"},
                {field: "delveryid", title: 'delveryid', width: 50, align: "center"},
                {field: "userid", title: 'userid', width: 50, align: "center"}
            ]
        ],
        done: function () {
            $("[data-field='id']").css('display', 'none');
            $("[data-field='recruitinfoid']").css('display', 'none');
            $("[data-field='delveryid']").css('display', 'none');
            $("[data-field='userid']").css('display', 'none');
        }
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
        var urls = getContextPath() + "/manage/eidtnewspage";
        var tit = "添加公告";
        if (edit) {
            tit = "编辑公告";
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


    //批量不通过
    $("#nopass").click(function () {
        passornopass(0);//不通过
    });
    //批量通过
    $("#pass").click(function () {

        passornopass(1);//通过
    });

    function passornopass(status) {

        var checkStatus = table.checkStatus('managerListTable'),
            data = checkStatus.data,
            infos = [];
        if (data.length > 0) {
            for (var i in data) {
                infos.push(data[i].id + "-" + data[i].recruitinfoid + "-" + data[i].deliveryid + "-" + data[i].userid);
            }
            layer.confirm('确定操作么？', {icon: 3, title: '提示信息'}, function (index) {
                //获取防伪标记
                update(infos.join(','), status);
            });
        } else {
            layer.msg("请选择操作的信息");
        }
    }

    //列表操作
    table.on('tool(managerList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'edit') { //编辑
            addManager(data);
        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此公告？', {icon: 3, title: '提示信息'}, function (index) {
                del(data.id);
            });
        }
    });


    function update(id, status) {
        var urls = getContextPath();
        $.ajax({
            type: 'POST',
            url: urls + '/manage/passornopass/',
            data: {infos: id, status: status},
            dataType: "json",
            headers: {
                //"X-CSRF-TOKEN-yilezhu": $("input[name='AntiforgeryKey_yilezhu']").val()
            },
            success: function (data) {//res为相应体,function为回调函数
                layer.msg(data.msg, {
                    time: 1000 //1s后自动关闭
                }, function () {
                    tableIns.reload();
                   // layer.close(index);
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
