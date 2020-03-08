package com.recruit.web.pojo;

/**
 * 作者：qiwj
 * 时间：2020/3/7
 */
public class loginParam
{
    private  Integer loginCount;
    private String loginTime;
    private String loginIp;

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
}
