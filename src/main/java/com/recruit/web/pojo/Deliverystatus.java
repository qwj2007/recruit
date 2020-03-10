package com.recruit.web.pojo;

import java.util.Date;

public class Deliverystatus {
    private Integer id;

    private Integer resumeid;

    private Integer status;

    private Integer recruitinfoid;

    private Date createtime;

    private Integer delveryid;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRecruitinfoid() {
        return recruitinfoid;
    }

    public void setRecruitinfoid(Integer recruitinfoid) {
        this.recruitinfoid = recruitinfoid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getDelveryid() {
        return delveryid;
    }

    public void setDelveryid(Integer delveryid) {
        this.delveryid = delveryid;
    }
}