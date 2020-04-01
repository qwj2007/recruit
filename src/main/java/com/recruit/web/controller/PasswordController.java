package com.recruit.web.controller;

import com.recruit.web.common.LoginUtil;
import com.recruit.web.pojo.Emailset;
import com.recruit.web.pojo.Userinfo;
import com.recruit.web.service.IEmailsetService;
import com.recruit.web.service.IUserinfoService;
import com.recruit.web.util.EncodeBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

/**
 * 作者：qiwj
 * 时间：2019/12/11
 */
@Controller
@RequestMapping("password")
public class PasswordController {
    @Autowired
    private IUserinfoService userinfoService;
    @Autowired
    private IEmailsetService emailsetService;

    @RequestMapping("/sendemail")
    @ResponseBody
    public String SendEmail(HttpServletRequest request) throws Exception {
        String email = request.getParameter("email");
        Userinfo userinfo = userinfoService.getUserInfo(null, email);
        Emailset emailset = emailsetService.selectOneEmail();
        String gotoEmail = gotoEmail(email);//跳转到邮箱登录页面
        if (userinfo != null) {
            String Url = "http://" + request.getServerName() + ":" + request.getServerPort() + "/password/modifypassword?email=" + EncodeBase64.encodeBase64(email);

            String strHref = EncodeBase64.encodeBase64(Url);
            StringBuilder sb = new StringBuilder();
            sb.append("亲爱的用户 " + email + "：您好！<br/><br/>");
            sb.append("  请点击下面链接进行密码重置：<br /><br /><table><tr><td>");
            sb.append("<a href=" + Url + ">重置密码" + strHref + "</a>");
            sb.append("</td></tr></table>");
            sb.append("<hr>");
            Properties props = new Properties();
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", emailset.getSendservice());// smtp服务器地址
            Session session = Session.getInstance(props);
            session.setDebug(true);
            Message msg = new MimeMessage(session);
            msg.setSubject("重置密码");
            //设置html格式
            Multipart mp = new MimeMultipart("related");
            BodyPart bodyPart = new MimeBodyPart();
            bodyPart.setDataHandler(new DataHandler(sb.toString(), "text/html;charset=UTF-8"));

            mp.addBodyPart(bodyPart);
            msg.setContent(mp);// 设置邮件内容对象

            //msg.setText(sb.toString());
            //msg.setContent("rer",sb.toString());
            msg.setFrom(new InternetAddress(emailset.getEmailaddress()));//发件人邮箱(我的163邮箱)
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(userinfo.getEmail())); //收件人邮箱(我的QQ邮箱)
            msg.saveChanges();

            Transport transport = session.getTransport();
            transport.connect(emailset.getEmailaddress(), emailset.getEmailpwd());//发件人邮箱,授权码(可以在邮箱设置中获取到授权码的信息)

            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } else {
            return "0";
        }
        return "http://" + gotoEmail;
    }

    private String gotoEmail(String mail) {
        String strmail = mail.split("@")[1];
        strmail = strmail.toLowerCase();
        if (strmail.equals("163.com")) {
            return "mail.163.com";
        } else if (strmail.equals("vip.163.com")) {
            return "vip.163.com";
        } else if (strmail.equals("126.com")) {
            return "mail.126.com";
        } else if (strmail == "qq.com" || strmail == "vip.qq.com" || strmail == "foxmail.com") {
            return "mail.qq.com";
        } else if (strmail.equals("gmail.com")) {
            return "mail.google.com";
        } else if (strmail.equals("sohu.com")) {
            return "mail.sohu.com";
        } else if (strmail.equals("tom.com")) {
            return "mail.tom.com";
        } else if (strmail.equals("vip.sina.com")) {
            return "vip.sina.com";
        } else if (strmail.equals("sina.com.cn") || strmail.equals("sina.com")) {
            return "mail.sina.com.cn";
        } else if (strmail == "tom.com") {
            return "mail.tom.com";
        } else if (strmail == "yahoo.com.cn" || strmail == "yahoo.cn") {
            return "mail.cn.yahoo.com";
        } else if (strmail == "tom.com") {
            return "mail.tom.com";
        } else if (strmail == "yeah.net") {
            return "www.yeah.net";
        } else if (strmail == "21cn.com") {
            return "mail.21cn.com";
        } else if (strmail == "hotmail.com") {
            return "www.hotmail.com";
        } else if (strmail == "sogou.com") {
            return "mail.sogou.com";
        } else if (strmail == "188.com") {
            return "www.188.com";
        } else if (strmail == "139.com") {
            return "mail.10086.cn";
        } else if (strmail == "189.cn") {
            return "webmail15.189.cn/webmail";
        } else if (strmail == "wo.com.cn") {
            return "mail.wo.com.cn/smsmail";
        } else if (strmail == "139.com") {
            return "mail.10086.cn";
        } else {
            return "";
        }
    }

    @RequestMapping("/retrievePassword")
    public String RetrievePassword(Model model,HttpServletRequest request) {
        LoginUtil.isLogin(request, model);
        return "retrievepassword";
    }

    @RequestMapping("/modifypassword")
    public String modifypassword(HttpServletRequest request, Model model) throws Exception {
        LoginUtil.isLogin(request, model);
        String email = request.getParameter("email");
        String de_email = EncodeBase64.decodeBase64(email);
        model.addAttribute("email", de_email);
        return "modifypassword";
    }

    @RequestMapping("/editPwd")
    @ResponseBody
    public String editPwd(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");
        Userinfo userinfo = userinfoService.getUserInfo(null, email);
        if (userinfo == null) {
            return "0";
        }
        userinfo.setPwd(pwd);
        Integer result = userinfoService.updateByPrimaryKey(userinfo);
        if (result > 0) {
            return "1";
        } else {
            return "2";
        }

    }
}
