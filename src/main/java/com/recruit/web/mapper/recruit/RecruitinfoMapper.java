package com.recruit.web.mapper.recruit;

import com.recruit.web.pojo.Recruitinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RecruitinfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Recruitinfo record);

    int insertSelective(Recruitinfo record);

    Recruitinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Recruitinfo record);

    int updateByPrimaryKey(Recruitinfo record);

    List<Recruitinfo> selectRecruitInfos();

    Recruitinfo selectById(@Param("id") Integer id);

    List<Recruitinfo> selectAllRecruitInfos(Map<String,Object> map);

    Integer updateBatch(@Param("id") List<Integer> id);

}