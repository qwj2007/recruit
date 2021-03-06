package com.recruit.web.mapper.recruit;

import com.recruit.web.pojo.Resumes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResumesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Resumes record);

    int insertSelective(Resumes record);

    Resumes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resumes record);

    int updateByPrimaryKey(Resumes record);

    Resumes selctResumeByUserId(@Param("userid") String userid);

    List<Resumes> selectResumeAll(Map<String,Object> map);
}