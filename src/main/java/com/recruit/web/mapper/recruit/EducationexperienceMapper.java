package com.recruit.web.mapper.recruit;

import com.recruit.web.pojo.Educationexperience;

public interface EducationexperienceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Educationexperience record);

    int insertSelective(Educationexperience record);

    Educationexperience selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Educationexperience record);

    int updateByPrimaryKey(Educationexperience record);
}