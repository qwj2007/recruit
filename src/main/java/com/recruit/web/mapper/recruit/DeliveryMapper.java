package com.recruit.web.mapper.recruit;

import com.recruit.web.pojo.Delivery;

public interface DeliveryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Delivery record);

    int insertSelective(Delivery record);

    Delivery selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Delivery record);

    int updateByPrimaryKey(Delivery record);

    Delivery selectDeliveryByResumesId(Integer resumeid);
}