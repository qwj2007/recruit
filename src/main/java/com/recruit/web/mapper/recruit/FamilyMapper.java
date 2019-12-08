package com.recruit.web.mapper.recruit;

import com.recruit.web.pojo.Family;

import java.util.List;

public interface FamilyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Family record);

    int insertSelective(Family record);

    Family selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Family record);

    int updateByPrimaryKey(Family record);

    List<Family> selectFamilyByResumeId(int id);
}