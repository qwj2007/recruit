package com.recruit.web.pojo;

import java.util.Date;

public class Banner {
    private Integer id = 0;

    private String imageurl;
    private String linkurl;

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }

    private Boolean isactive = true;
    private Boolean isdisplay = true;

    public Boolean getIsdisplay() {
        return isdisplay;
    }

    public void setIsdisplay(Boolean isdisplay) {
        this.isdisplay = isdisplay;
    }

    private Date createtime = new Date();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl == null ? null : imageurl.trim();
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