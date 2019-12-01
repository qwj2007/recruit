package com.recruit.web.mapper.recruit;

import com.recruit.web.pojo.Hrnotice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface HrnoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hrnotice record);

    int insertSelective(Hrnotice record);

    Hrnotice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hrnotice record);

    int updateByPrimaryKey(Hrnotice record);

    List<Hrnotice> selectNoticeCountByUserId(@Param("userid") String userid);
}