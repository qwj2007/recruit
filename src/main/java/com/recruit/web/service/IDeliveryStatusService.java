package com.recruit.web.service;

import com.recruit.web.pojo.Deliverystatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 作者：qiwj
 * 时间：2020/3/10
 */
public interface IDeliveryStatusService {
    int deleteByPrimaryKey(Integer id);

    int insert(Deliverystatus record);

    int insertSelective(Deliverystatus record);

    Deliverystatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Deliverystatus record);

    int updateByPrimaryKey(Deliverystatus record);

    String passornopass(String infos,String contents, Integer status,HttpServletRequest request);
}
