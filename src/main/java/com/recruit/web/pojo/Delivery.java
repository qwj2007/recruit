package com.recruit.web.pojo;

import java.util.Date;

public class Delivery {
    private Integer id;

    private Integer resumeid;

    private Date createtime;

    private Integer recruitinfoid;

    private Boolean isactive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResumeid() {
        return resumeid;
    }

    public void setResumeid(Integer resumeid) {
        this.resumeid = resumeid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getRecruitinfoid() {
        return recruitinfoid;
    }

    public void setRecruitinfoid(Integer recruitinfoid) {
        this.recruitinfoid = recruitinfoid;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }
}