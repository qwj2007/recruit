package com.recruit.web.service;

import com.recruit.web.pojo.Delivery;

/**
 * 作者：qiwj
 * 时间：2019/12/1
 */
public interface IDeliveryService {
    int deleteByPrimaryKey(Integer id);

    int insert(Delivery record);

    int insertSelective(Delivery record);

    Delivery selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Delivery record);

    int updateByPrimaryKey(Delivery record);
}
