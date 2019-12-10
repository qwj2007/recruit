function userlogin() {
    var userName = document.getElementById("username").value; //账号
    var pwd = document.getElementById("userpwd").value; //密码
    var parm = { username: userName, pwd: pwd };

    $.ajax({
        url: "/index/login",
        type: "post",
        data: parm,
        async: false,
        dataType: "text",
        success: function (msg) {

            if (msg == "1") {
                //$('#login').hide();
                //$('#show').show();
                //$('#liuser').html("您好！" + userName);
                //location.href = 'Center/personindex.aspx';
                //location.href="";
                location.reload()
            }
            else if (msg == "0") {
                location.href = 'Manage/Index.aspx';
            }
            else {
                alert("登录失败！");
            }
        }
    });

}
function logout() {
    $("#username").val("");
    $("#userpwd").val("");
    //document.getElementById("username").value = ''; //账号
    //document.getElementById("userpwd").value = ''; //密码

    $.ajax({
        url: "/index/loginOut",
        type: "post",
        //data: parm,
        async: false,
        dataType: "text",
        success: function (msg) {
            if (msg == "1") {
               // $('#login').show();
               // $('#show').hide();

                //$('#login').hide();
                //$('#show').show();
                //$('#liuser').html("您好！" + userName);
                //location.href = 'Center/personindex.aspx';
                //location.href="";
                window.location.reload();
            }
            else if (msg == "0") {
                location.href = 'Manage/Index.aspx';
            }

        }
    });

    //location.href = '/Manage/Logout.aspx';
}