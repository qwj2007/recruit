package com.recruit.web.mapper.recruit;

import com.recruit.web.pojo.Emailset;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface EmailsetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Emailset record);

    int insertSelective(Emailset record);

    Emailset selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Emailset record);

    int updateByPrimaryKey(Emailset record);

    Emailset selectOneEmail();

}