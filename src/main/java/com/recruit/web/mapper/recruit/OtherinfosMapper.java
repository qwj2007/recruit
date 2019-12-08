package com.recruit.web.mapper.recruit;

import com.recruit.web.pojo.Otherinfos;

import java.util.List;

public interface OtherinfosMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Otherinfos record);

    int insertSelective(Otherinfos record);

    Otherinfos selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Otherinfos record);

    int updateByPrimaryKey(Otherinfos record);
    List<Otherinfos> selectOtherinfosById(Integer id);
}