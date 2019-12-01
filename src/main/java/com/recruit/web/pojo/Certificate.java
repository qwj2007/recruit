package com.recruit.web.pojo;

import java.util.Date;

public class Certificate {
    private Integer id;

    private Integer resumesid;

    private Date certificatedate;

    private String certificatename;

    private String remark;

    private Integer orders;

    private Integer createuserid;

    private Date cratetime;

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

    public Date getCertificatedate() {
        return certificatedate;
    }

    public void setCertificatedate(Date certificatedate) {
        this.certificatedate = certificatedate;
    }

    public String getCertificatename() {
        return certificatename;
    }

    public void setCertificatename(String certificatename) {
        this.certificatename = certificatename == null ? null : certificatename.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public Integer getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(Integer createuserid) {
        this.createuserid = createuserid;
    }

    public Date getCratetime() {
        return cratetime;
    }

    public void setCratetime(Date cratetime) {
        this.cratetime = cratetime;
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