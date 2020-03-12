package com.recruit.web.service;

import com.recruit.web.pojo.Navigation;

import java.util.List;

/**
 * 作者：qiwj
 * 时间：2020/3/12
 */
public interface INavigationService {
    List<Navigation> selectNavigation();

    int insertSelective(Navigation record);

    int updateByPrimaryKeySelective(Navigation record);

    int deleteByPrimaryKey(Integer id);

    int insert(Navigation record);



    Navigation selectByPrimaryKey(Integer id);



    int updateByPrimaryKey(Navigation record);

}
