package com.recruit.web.mapper.recruit;

import com.recruit.web.pojo.Baseinfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BaseinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Baseinfo record);

    int insertSelective(Baseinfo record);

    Baseinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Baseinfo record);

    int updateByPrimaryKey(Baseinfo record);
    Baseinfo selectBaseInfo();

}