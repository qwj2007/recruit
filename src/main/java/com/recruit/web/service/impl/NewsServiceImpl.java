package com.recruit.web.service.impl;

import com.recruit.web.mapper.recruit.NewsMapper;
import com.recruit.web.pojo.News;
import com.recruit.web.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
