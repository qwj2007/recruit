package com.recruit.web.pojo;

public class Emailset {
    private Integer id;

    private String emailaddress;

    private String emailpwd;

    private String sendservice;

    private String contents;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress == null ? null : emailaddress.trim();
    }

    public String getEmailpwd() {
        return emailpwd;
    }

    public void setEmailpwd(String emailpwd) {
        this.emailpwd = emailpwd == null ? null : emailpwd.trim();
    }

    public String getSendservice() {
        return sendservice;
    }

    public void setSendservice(String sendservice) {
        this.sendservice = sendservice == null ? null : sendservice.trim();
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents == null ? null : contents.trim();
    }
}