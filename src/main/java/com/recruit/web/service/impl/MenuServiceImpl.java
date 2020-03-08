package com.recruit.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.recruit.web.mapper.recruit.MenuMapper;
import com.recruit.web.pojo.Menu;
import com.recruit.web.pojo.MenuNavView;
import com.recruit.web.pojo.MenuViews;
import com.recruit.web.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 作者：qiwj
 * 时间：2020/3/7
 */
@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenus() {
        List<Menu> menus = menuMapper.getMenus();
        return menus;
    }

    @Override
    public String getMenuJson() {
        List<Menu> menus = menuMapper.getMenus();
        List<MenuNavView> listmenu = new ArrayList<>();
        if (menus != null) {
            menus.forEach(menu -> {
                MenuNavView menuNavView = new MenuNavView();
                menuNavView.setId(menu.getId());
                menuNavView.setDisplayName(menu.getName());
                menuNavView.setLinkUrl(menu.getUrl());
                menuNavView.setName(menu.getName());
                menuNavView.setIconUrl(menu.getIconurl()==null?"":menu.getIconurl());
                menuNavView.setPid(menu.getPid());
                menuNavView.setSpread(false);
                menuNavView.setTarget("");
                listmenu.add(menuNavView);
            });
        }
        List<MenuViews> views=getMenuViews(0,listmenu);
        return JSON.toJSONString(views);
    }

    @Override
    public List<MenuViews> getMenuViews(Integer id, List<MenuNavView> menuNavViews) {
        List<MenuViews> menuViewsList=new ArrayList<>();
        List<MenuNavView> mainList = menuNavViews.stream()
                .filter(mm -> mm.getPid() == id).collect(Collectors.toList());

        mainList.forEach(x->{
            MenuViews menuV=new MenuViews();
            menuV.setItem(x);
            menuV.setChildren(getMenuViews(x.getId(),menuNavViews));
            menuViewsList.add(menuV);
        });
       return  menuViewsList;
    }


}
