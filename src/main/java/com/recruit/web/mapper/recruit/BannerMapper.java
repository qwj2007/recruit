package com.recruit.web.mapper.recruit;

import com.recruit.web.pojo.Banner;

import java.util.List;
import java.util.Map;

public interface BannerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Banner record);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);

    List<Banner> selectBanner(Map<String,Object> map);
    List<Banner> selectBannerQT();

}