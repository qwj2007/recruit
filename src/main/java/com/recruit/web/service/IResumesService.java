package com.recruit.web.service;

import com.recruit.web.pojo.Resumes;
import org.apache.ibatis.annotations.Param;

/**
 * 作者：qiwj
 * 时间：2019/11/30
 */
public interface IResumesService {
    int deleteByPrimaryKey(Integer id);

    int insert(Resumes record);

    int insertSelective(Resumes record);

    Resumes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resumes record);

    int updateByPrimaryKey(Resumes record);

    Resumes selctResumeByUserId( String userid);
}
