package com.recruit.web.pojo;

import java.util.Date;

public class Baseinfo {
    private Integer id=0;

    private String logourl;

    private String systemtitle;

    private String footinfo="";

    private Boolean isactive;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogourl() {
        return logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl == null ? null : logourl.trim();
    }

    public String getSystemtitle() {
        return systemtitle;
    }

    public void setSystemtitle(String systemtitle) {
        this.systemtitle = systemtitle == null ? null : systemtitle.trim();
    }

    public String getFootinfo() {
        return footinfo;
    }

    public void setFootinfo(String footinfo) {
        this.footinfo = footinfo == null ? null : footinfo.trim();
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}