package com.recruit.web.service;

import com.recruit.web.pojo.Baseinfo;

/**
 * 作者：qiwj
 * 时间：2020/3/13
 */
public interface IBaseInfoService {
    int deleteByPrimaryKey(Integer id);

    int insert(Baseinfo record);

    int insertSelective(Baseinfo record);

    Baseinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Baseinfo record);

    int updateByPrimaryKey(Baseinfo record);
    Baseinfo selectBaseInfo();
}
