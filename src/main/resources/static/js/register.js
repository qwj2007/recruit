$(function () {
    registerSteps();

})

//身份证相关--begin
var Wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1];    // 加权因子   
var ValideCode = [1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2];            // 身份证验证位值.10代表X   
function IdCardValidate(idCard) {
    idCard = trim(idCard.replace(/ /g, ""));               //去掉字符串头尾空格                     
    if (idCard.length == 15) {
        return isValidityBrithBy15IdCard(idCard);       //进行15位身份证的验证    
    } else if (idCard.length == 18) {
        var a_idCard = idCard.split("");                // 得到身份证数组   
        if (isValidityBrithBy18IdCard(idCard) && isTrueValidateCodeBy18IdCard(a_idCard)) {   //进行18位身份证的基本验证和第18位的验证
            return true;
        } else {
            return false;
        }
    } else {
        return false;
    }
}
/**  
 * 判断身份证号码为18位时最后的验证位是否正确  
 * @param a_idCard 身份证号码数组  
 * @return  
 */
function isTrueValidateCodeBy18IdCard(a_idCard) {
    var sum = 0;                             // 声明加权求和变量   
    if (a_idCard[17].toLowerCase() == 'x') {
        a_idCard[17] = 10;                    // 将最后位为x的验证码替换为10方便后续操作   
    }
    for (var i = 0; i < 17; i++) {
        sum += Wi[i] * a_idCard[i];            // 加权求和   
    }
    valCodePosition = sum % 11;                // 得到验证码所位置   
    if (a_idCard[17] == ValideCode[valCodePosition]) {
        return true;
    } else {
        return false;
    }
}
/**  
  * 验证18位数身份证号码中的生日是否是有效生日  
  * @param idCard 18位书身份证字符串  
  * @return  
  */
function isValidityBrithBy18IdCard(idCard18) {
    var year = idCard18.substring(6, 10);
    var month = idCard18.substring(10, 12);
    var day = idCard18.substring(12, 14);
    var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
    // 这里用getFullYear()获取年份，避免千年虫问题   
    if (temp_date.getFullYear() != parseFloat(year)
          || temp_date.getMonth() != parseFloat(month) - 1
    || temp_date.getDate() != parseFloat(day)) {
        return false;
    } else {
        return true;
    }
}
/**  
 * 验证15位数身份证号码中的生日是否是有效生日  
 * @param idCard15 15位书身份证字符串  
 * @return  
 */
function isValidityBrithBy15IdCard(idCard15) {
    var year = idCard15.substring(6, 8);
    var month = idCard15.substring(8, 10);
    var day = idCard15.substring(10, 12);
    var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
    // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法   
    if (temp_date.getYear() != parseFloat(year)
            || temp_date.getMonth() != parseFloat(month) - 1
            || temp_date.getDate() != parseFloat(day)) {
        return false;
    } else {
        return true;
    }
}
//去掉字符串头尾空格   
function trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

//身份证相关--end
function registerSteps() {
    var email;
    var moblie;
    var username;
    var password;

    var XM;
    var XB;
    var MZID;
    var SFZH;
    var CSRQ;
    var NL;
    var JG;
    var ZZMMID;
    var HYZKID;
    var RSGXSZD;
    var HKSZD;
    var LXDH;
    var XJZD;
    var BYXS;
    var SXZY;
    var XLID;
    var XWID;
    var SFYJS;
    var GZSJ;
    var DWMC;
    var ZW;
    $(".form-btn button").click(function () {
        //var email;
        //var moblie;
        //var username;
        //var password;
        var arrayObj = new Array();
        username = $.trim($("#username1").val());
        arrayObj.push('username1');
        reg_checkAjax("username1");
        password = $.trim($("#password1").val());
        reg_checkAjax("password1");
        arrayObj.push('password1');
        if (exitsid("passconfirm1")) {
            reg_checkAjax("passconfirm1");
            arrayObj.push('passconfirm1');
        }
        if (exitsid("email1")) {
            reg_checkAjax("email1");
            email = $.trim($('#email1').val());
            arrayObj.push('email1');
        }
        //if (exitsid("moblie")) {
        //    reg_checkAjax("moblie");
        //    email = $.trim($('#moblie').val());
        //    //arrayObj.push('moblie');
        //}

        for (i = 0; i < arrayObj.length; i++) {
            if (!exitsdate(arrayObj[i])) {
                return false;
            }
        }
        if(username=="")
        {
            alert("用户名不能为空");
            return false;
        }
        if(password=="")
        {
            alert('密码不能为空！');
            return false;
        }
        if(email=="")
        {
            alert('邮箱不能为空！');
            return false;
        }
        var flag = false;
        $.ajax({
            url: "/center/checklogin?username=" + username + "&pwd=" + password + "&email=" + email + "",
            type: "post",
            async: false,
            dataType: "text",
            success: function (msg) {
                if (msg == "ok") {
                    flag = true;
                }
                else {
                    //alert("注册失败！");
                    if (msg == "1") {
                        msg = '用户名已存在！';
                        //update_html(id, "0", msg);
                        alert(msg);
                        return false;
                    }
                    else if (msg == "2") {
                        msg = '邮箱已注册！';
                        alert(msg);
                        return false;
                    }
                }
            }
        });

        if (flag == true) {
            $('#stepOne').removeClass("stepsOnec");
            $('#stepTwo').removeClass("stepsTwo");
            $('#stepOne').addClass("stepsOne");
            $('#stepTwo').addClass("stepsTwou");
            $(".registerOne").css("display", "none");
            $(".registerOne").siblings(".registerTwo").css("display", "block");
            return false;
        }
    });

    $("#stepTwoX").click(function () {
        debugger
        XM = $('#username').val();
        XB = $('input:radio[name="XB"]:checked').val();
        MZID=  $('#nationality option:selected').text()
       // MZID = $('#nationality').text();
        SFZH = $('#idcard').val();
        NL = $('#NL').val();
        CSRQ = $('#birthday').val();
        JG = $('#JG').val();
        ZZMMID = $('#ZZMMID option:selected').text();
        HYZKID = $('#HYZKID option:selected').text();
        RSGXSZD = $('#RSGXSZD').val();
        HKSZD = $('#HKSZD').val();
        LXDH = $('#LXDH').val();
        XJZD = $('#XJZD').val();
        BYXS = $('#BYXS').val();
        SXZY = $('#SXZY').val();
        XLID = $('#XLID option:selected').text();
        XWID = $('#XWID option:selected').text();
        SFYJS = $('input:radio[name="SFYJS"]:checked').val();
        GZSJ = $('#GZSJ').val();
        DWMC = $('#DWMC').val();
        ZW = $('#ZW').val();
        if (XM == '') {
            alert("姓名不能为空");
            return false;
        }
        if (MZID == '') {
            alert("民族不能为空");
            return false;
        }
        if (SFZH == '') {
            alert("身份证号不能为空");
            return false;
        }
        else {
            var isOk = IdCardValidate(SFZH);
            if (isOk == false) {
                alert("请输入正确的身份证号码");
                return false;
            }
        }
        if (CSRQ == '') {
            alert("出生日期不能为空");
            return false;
        }
        if (JG == '') {
            alert("籍贯不能为空");
            return false;
        }
        if (ZZMMID == '') {
            alert("政治面貌不能为空");
            return false;
        }
        if (HYZKID == '') {
            alert("婚姻状况不能为空");
            return false;
        }
        if (RSGXSZD == '') {
            alert("人事关系所在地不能为空");
            return false;
        }
        if (HKSZD == '') {
            alert("户口所在地不能为空");
            return false;
        }
        if (LXDH == '') {
            alert("联系电话不能为空");
            return false;
        }
        if (XJZD == '') {
            alert("现居住地不能为空");
            return false;
        }
        if (BYXS == '') {
            alert("毕业学校不能为空");
            return false;
        }
        if (SXZY == '') {
            alert("所学专业不能为空");
            return false;
        }
        if (XLID == '') {
            alert("学历不能为空");
            return false;
        }
        if (XWID == '') {
            alert("学位不能为空");
            return false;
        }
        if (GZSJ == '') {
            alert("参加工作时间不能为空");
            return false;
        }
        if (DWMC == '') {
            alert("单位名称不能为空");
            return false;
        }
        if (ZW == '') {
            alert("职务不能为空");
            return false;
        }

        $('#stepThree').removeClass("stepsThree");
        $('#stepTwo').removeClass("stepsTwou");
        $('#stepThree').addClass("stepsThreeu");
        $('#stepTwo').addClass("stepsTwo");
        $(".registerTwo").css("display", "none");
        $(".registerTwo").siblings(".registerThree").css("display", "block");
        return false;
    });

    $("#stepTwoS").click(function () {
        $('#stepOne').removeClass("stepsOne");
        $('#stepTwo').removeClass("stepsTwou");
        $('#stepOne').addClass("stepsOnec");
        $('#stepTwo').addClass("stepsTwo");
        $(".registerTwo").css("display", "none");
        $(".registerTwo").siblings(".registerOne").css("display", "block");
        return false;
    });

    $("#stepThreeX").click(function () {
        if ($("#agree").is(':checked')) {
            $.ajax({
                type: "POST",
                url: "/Register/RegisterUserInfo",
                processData: true,
                async: false,
                cache: false,
                data: {
                    username: username,
                    pwd: password,
                    email: email,
                    truename: XM,
                    sex: XB,
                    birthday: CSRQ,
                    age: NL,
                    nation: MZID,
                    nativeplace: JG,
                    maritalstatus: HYZKID,
                    registeredresidence: HKSZD,
                    politicaloutlook: ZZMMID,
                    workdate: GZSJ,
                    locationpersonnelrelationship: RSGXSZD,
                    card: SFZH,
                    education: XLID,
                    graduateschool: BYXS,
                    major: SXZY,
                    academicdegree: XWID,
                    phone: LXDH,
                    companyname: DWMC,
                    postname: ZW,
                    nowaddress: XJZD,
                    isfreshstudent: SFYJS },
                success: function (data) {
                    // alert(data);
                    if (data == "ok") {
                        alert("注册成功！");
                        window.location.href = "/center/personindex";
                        // document.location.href = "/Center/personindex.aspx";
                        // window.event.returnValue = false;
                    }
                    else {
                        alert(data);
                    }

                }
            });
        }
        else {
            alert('请先阅读承诺书，并同意承诺书内容！')
            return false;
        }
    });

    $("#stepThreeS").click(function () {
        $('#stepThree').removeClass("stepsThreeu");
        $('#stepTwo').removeClass("stepsTwo");
        $('#stepThree').addClass("stepsThree");
        $('#stepTwo').addClass("stepsTwou");
        $(".registerThree").css("display", "none");
        $(".registerThree").siblings(".registerTwo").css("display", "block");
        return false;
    });
}
