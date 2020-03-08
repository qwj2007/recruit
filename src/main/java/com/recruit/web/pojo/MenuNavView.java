package com.recruit.web.pojo;

/**
 * 作者：qiwj
 * 时间：2020/3/7
 */
public class MenuNavView {

    private int id;
    private int pid ;
    private String Name ;
    /// <summary>
    /// 标题
    /// </summary>
    private String DisplayName;
    /// <summary>
    /// 图标

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getIconUrl() {
        return IconUrl;
    }

    public void setIconUrl(String iconUrl) {
        IconUrl = iconUrl;
    }

    public String getLinkUrl() {
        return LinkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        LinkUrl = linkUrl;
    }

    public Boolean getSpread() {
        return Spread;
    }

    public void setSpread(Boolean spread) {
        Spread = spread;
    }

    public String getTarget() {
        return Target;
    }

    public void setTarget(String target) {
        Target = target;
    }

    /// </summary>
    private String IconUrl ;
    /// <summary>
    /// 链接
    /// </summary>
    private String LinkUrl ;
    /// <summary>
    /// 是否展开
    /// </summary>
    private Boolean Spread=false;
    /// <summary>
    /// 是否新开窗口
    /// </summary>
    private String Target ;
}
