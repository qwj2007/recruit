package com.recruit.web.mapper.recruit;

import com.recruit.web.pojo.Workexperience;

import java.util.List;

public interface WorkexperienceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Workexperience record);

    int insertSelective(Workexperience record);

    Workexperience selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Workexperience record);

    int updateByPrimaryKey(Workexperience record);

    List<Workexperience> selectWorkExperienceById(Integer resumeid);
}