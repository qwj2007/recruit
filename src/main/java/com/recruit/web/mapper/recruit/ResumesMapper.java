package com.recruit.web.mapper.recruit;

import com.recruit.web.pojo.Resumes;

public interface ResumesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Resumes record);

    int insertSelective(Resumes record);

    Resumes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resumes record);

    int updateByPrimaryKey(Resumes record);
}