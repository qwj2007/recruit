package com.recruit.web.mapper.recruit;

import com.recruit.web.pojo.Trainingexperience;

import java.util.List;

public interface TrainingexperienceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Trainingexperience record);

    int insertSelective(Trainingexperience record);

    Trainingexperience selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Trainingexperience record);

    int updateByPrimaryKey(Trainingexperience record);
    List<Trainingexperience> selecTrainByResumeId(Integer id);
}