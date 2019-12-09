package com.recruit.web.pojo;

import java.util.Date;

public class Educationexperience {
    private Integer id;

    private Integer resumesid;

    private String begintime;

    private String endtime;

    private String schoolname;

    private String majorname;

    private String education;

    private String academicdegree;

    private String learningform;

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

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname == null ? null : schoolname.trim();
    }

    public String getMajorname() {
        return majorname;
    }

    public void setMajorname(String majorname) {
        this.majorname = majorname == null ? null : majorname.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getAcademicdegree() {
        return academicdegree;
    }

    public void setAcademicdegree(String academicdegree) {
        this.academicdegree = academicdegree == null ? null : academicdegree.trim();
    }

    public String getLearningform() {
        return learningform;
    }

    public void setLearningform(String learningform) {
        this.learningform = learningform == null ? null : learningform.trim();
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