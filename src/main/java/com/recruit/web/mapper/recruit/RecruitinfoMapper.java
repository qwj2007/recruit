package com.recruit.web.mapper.recruit;



import com.recruit.web.pojo.Recruitinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RecruitinfoMapper {
    List<Recruitinfo> selectRecruitInfos();
    Recruitinfo selectById();
}