package com.recruit.web.service;

import com.recruit.web.pojo.Banner;

import java.util.List;
import java.util.Map;

/**
 * 作者：qiwj
 * 时间：2020/3/16
 */
public interface IBannerService {
    List<Banner> selectBanner(Map<String,Object> map);
    List<Banner> selectBannerQT();
    int insertSelective(Banner record);
    int updateByPrimaryKeySelective(int id, String isShow);
}
