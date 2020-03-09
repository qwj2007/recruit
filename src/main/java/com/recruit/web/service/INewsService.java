package com.recruit.web.service;

import com.recruit.web.pojo.News;
import java.util.List;

/**
 * 作者：qiwj
 * 时间：2019/11/27
 */
public interface INewsService {
    List<News> selectNews();
    News selectByPrimaryKey(Integer id);
    List<News>  selectNewInfos();
    int insertSelective(News record);
    int updateByPrimaryKeySelective(News record);
    int updateByPrimaryKey(News record);
    Integer updateBatch( List<Integer> id);
}
