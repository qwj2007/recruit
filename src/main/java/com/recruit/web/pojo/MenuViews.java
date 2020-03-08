package com.recruit.web.pojo;

import java.util.List;

/**
 * 作者：qiwj
 * 时间：2020/3/7
 */
public class MenuViews {
    private MenuNavView item;
    private List<MenuViews> children;

    public MenuNavView getItem() {
        return item;
    }

    public void setItem(MenuNavView item) {
        this.item = item;
    }

    public List<MenuViews> getChildren() {
        return children;
    }

    public void setChildren(List<MenuViews> children) {
        this.children = children;
    }
}
