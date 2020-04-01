package com.recruit.web.service.impl;

import com.recruit.web.mapper.recruit.NewsMapper;
import com.recruit.web.pojo.News;
import com.recruit.web.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 作者：qiwj
 * 时间：2019/11/27
 */
@Service
public class NewsServiceImpl implements INewsService {
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<News> selectNews() {
        return newsMapper.selectNews();
    }

    @Override
    public News selectByPrimaryKey(Integer id) {
        return newsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<News> selectNewInfos(Map<String,Object> map) {
        return newsMapper.selectNewInfos(map);
    }

    @Override
    public int insertSelective(News record) {
        return newsMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(News record) {
        return newsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(News record) {
        return newsMapper.updateByPrimaryKey(record);
    }

    @Override
    public Integer updateBatch(List<Integer> id) {
        return newsMapper.updateBatch(id);
    }

}
