package com.recruit.web.pojo;

import java.util.Date;

public class Otherinfos {
    private Integer id;

    private Integer resumesid;

    private String contents;

    private Integer createuserid;

    private Date createtime;

    private Integer updateuserid;

    private Date updatetime;

    private Boolean isactive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResumesid() {
        return resumesid;
    }

    public void setResumesid(Integer resumesid) {
        this.resumesid = resumesid;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents == null ? null : contents.trim();
    }

    public Integer getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(Integer createuserid) {
        this.createuserid = createuserid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getUpdateuserid() {
        return updateuserid;
    }

    public void setUpdateuserid(Integer updateuserid) {
        this.updateuserid = updateuserid;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }
}