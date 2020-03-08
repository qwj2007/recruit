package com.recruit.web.service;

import com.recruit.web.pojo.Menu;
import com.recruit.web.pojo.MenuNavView;
import com.recruit.web.pojo.MenuViews;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者：qiwj
 * 时间：2020/3/7
 */
@Service
public interface IMenuService {
    List<Menu> getMenus();
    String getMenuJson();
    List<MenuViews> getMenuViews(Integer id, List<MenuNavView> menuNavViews);
}
