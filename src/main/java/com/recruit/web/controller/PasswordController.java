package com.recruit.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 作者：qiwj
 * 时间：2019/12/11
 */
@Controller
@RequestMapping("password")
public class PasswordController {
    @RequestMapping("/sendemail")
    public String SendEmail() throws Exception {
        
        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.163.com");// smtp服务器地址
        Session session = Session.getInstance(props);
        session.setDebug(true);
        Message msg = new MimeMessage(session);
        msg.setSubject("这是一个测试程序....");
        msg.setText("你好!这是我的第一个javamail程序---WQ");
        msg.setFrom(new InternetAddress("XXXXXXXXXXX@163.com"));//发件人邮箱(我的163邮箱)
        msg.setRecipient(Message.RecipientType.TO,new InternetAddress("XXXXXXXXXXX@qq.com")); //收件人邮箱(我的QQ邮箱)
        msg.saveChanges();

        Transport transport = session.getTransport();
        transport.connect("XXXXXXXXXX@163.com", "XXXXXXXXX");//发件人邮箱,授权码(可以在邮箱设置中获取到授权码的信息)

        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();

        return "1";
    }

    private String gotoEmail(String mail) {
        String strmail = mail.split("@")[1];
        strmail = strmail.toLowerCase();
        if (strmail == "163.com") {
            return "mail.163.com";
        } else if (strmail == "vip.163.com") {
            return "vip.163.com";
        } else if (strmail == "126.com") {
            return "mail.126.com";
        } else if (strmail == "qq.com" || strmail == "vip.qq.com" || strmail == "foxmail.com") {
            return "mail.qq.com";
        } else if (strmail == "gmail.com") {
            return "mail.google.com";
        } else if (strmail == "sohu.com") {
            return "mail.sohu.com";
        } else if (strmail == "tom.com") {
            return "mail.tom.com";
        } else if (strmail == "vip.sina.com") {
            return "vip.sina.com";
        } else if (strmail == "sina.com.cn" || strmail == "sina.com") {
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

}
