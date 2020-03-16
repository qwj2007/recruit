/**
 * 图片上传数量及其大小等控制
 * 点击开始上传按钮，执行上传
 *
 */
var success = 0;
var fail = 0;
var imgurls = "";
var imgsName = "";
var urls = getContextPath();
var layer;
layui.use(['upload', 'layer'], function () {
    var upload = layui.upload;
     layer = layui.layer;
    //多图片上传
    upload.render({
        elem: '#btn_select'
        , url: urls+'/manage/uploadimage' //改成您自己的上传接口
        , multiple: true
        //, auto: false
        //上传的单个图片大小
        , size: 10240
        //最多上传的数量
        , number: 20
        //MultipartFile file 对应，layui默认就是file,要改动则相应改动
        //, field: 'file'
        //, bindAction: '#btn_up'
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#div_proview').append('<img src="' + result + '" style="width: 100px;height: 100px" alt="' + file.name + '" class="layui-upload-img">')
            });
        }
        , done: function (res) {
            //上传完毕
            //每个图片上传结束的回调，成功的话，就把新图片的名字保存起来，作为数据提交
            if (res.code == "1") {
                fail++;
            } else {
                success++;
                imgurls = imgurls + "" + res.data.src + ",";
                $('#imgUrls').val(imgurls);
            }
        }
        , allDone: function (obj) {
            layer.msg("总共要上传图片总数为：" + (fail + success) + "\n"
                + "其中上传成功图片数为：" + success + "\n"
                + "其中上传失败图片数为：" + fail
            ) //*/
        }
    });
});

/**
 * 清空预览的图片及其对应的成功失败数
 * 原因：如果已经存在预览的图片的话，再次点击上选择图片时，预览图片会不断累加
 * 表面上做上传成功的个数清0，实际后台已经上传成功保存了的，只是没有使用，以最终商品添加的提交的为准
  */
function cleanImgsPreview() {
    $("#cleanImgs").click(function () {
        success = 0;
        fail = 0;
        $('#div_proview').html("");
        $('#imgUrls').val("");
    });
}

/**
 * 保存
 */
function saveBanner() {
    var ius = $("#imgUrls").val();
    $.ajax({
        type: "POST",
        url: urls+"/manage/saveBanner",
        data: {
            imageurls: ius,
        },
        dataType:"json",
        success: function (result) {
            debugger
            var alertIndex;
            if (result.resultCode == "1"||result.resultCode == "0") {
                alertIndex = layer.alert(result.msg, {icon: 1}, function () {
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                    top.layer.close(alertIndex);
                });
            } else {
                layer.msg(result.msg,{icon:5});
            }
        }
    });
}





