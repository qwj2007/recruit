package com.recruit.web.pojo;

import java.util.Date;

public class Family {
    private Integer id;

    private Integer resumesid;

    private String appellation;

    private String username;

    private Date birthday;

    private String politicaloutlook;

    private String companyname;

    private String job;

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

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation == null ? null : appellation.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPoliticaloutlook() {
        return politicaloutlook;
    }

    public void setPoliticaloutlook(String politicaloutlook) {
        this.politicaloutlook = politicaloutlook == null ? null : politicaloutlook.trim();
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname == null ? null : companyname.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
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