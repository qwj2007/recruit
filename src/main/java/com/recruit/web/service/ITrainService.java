package com.recruit.web.service;

import com.recruit.web.pojo.Trainingexperience;

import java.util.List;

/**
 * 作者：qiwj
 * 时间：2019/12/8
 */
public interface ITrainService {
    List<Trainingexperience> selecTrainByResumeId(Integer id);
    int deleteByPrimaryKey(Integer id);

    int insert(Trainingexperience record);

    int insertSelective(Trainingexperience record);

    Trainingexperience selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Trainingexperience record);

    int updateByPrimaryKey(Trainingexperience record);
}
