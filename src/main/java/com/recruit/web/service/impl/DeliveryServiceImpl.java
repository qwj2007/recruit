package com.recruit.web.service.impl;

import com.recruit.web.mapper.recruit.DeliveryMapper;
import com.recruit.web.pojo.Delivery;
import com.recruit.web.service.IDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 作者：qiwj
 * 时间：2019/12/1
 */
@Service
public class DeliveryServiceImpl implements IDeliveryService {
    @Autowired
    private DeliveryMapper deliveryMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Delivery record) {
        return 0;
    }

    @Override
    public int insertSelective(Delivery record) {
        Integer count = deliveryMapper.insertSelective(record);
        return count;
    }

    @Override
    public Delivery selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Delivery record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Delivery record) {
        return 0;
    }

    @Override
    public Delivery selectDeliveryByResumesId(Integer resumeid) {
        return deliveryMapper.selectDeliveryByResumesId(resumeid);
    }
}
