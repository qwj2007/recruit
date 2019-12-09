package com.recruit.web.service;

import com.recruit.web.pojo.Educationexperience;

import java.util.List;

/**
 * 作者：qiwj
 * 时间：2019/12/8
 */
public interface IEdutionExpericenceService {
    List<Educationexperience> selectEducationByResumeid(Integer id);
    int deleteByPrimaryKey(Integer id);

    int insert(Educationexperience record);

    int insertSelective(Educationexperience record);

    Educationexperience selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Educationexperience record);

    int updateByPrimaryKey(Educationexperience record);


}
