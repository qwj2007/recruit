package com.recruit.web.mapper.recruit;

import com.recruit.web.pojo.Deliverystatus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DeliverystatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Deliverystatus record);

    int insertSelective(Deliverystatus record);

    Deliverystatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Deliverystatus record);

    int updateByPrimaryKey(Deliverystatus record);
}