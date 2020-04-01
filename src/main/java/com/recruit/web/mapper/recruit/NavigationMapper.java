package com.recruit.web.mapper.recruit;

import com.recruit.web.pojo.Navigation;

import java.util.List;
import java.util.Map;

public interface NavigationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Navigation record);

    int insertSelective(Navigation record);

    Navigation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Navigation record);

    int updateByPrimaryKey(Navigation record);
    List<Navigation> selectNavigation();
    List<Navigation> selectNavigationAll(Map<String,Object> map);

}