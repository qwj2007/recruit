package com.recruit.web.service.impl;

import com.recruit.web.mapper.recruit.NavigationMapper;
import com.recruit.web.pojo.Navigation;
import com.recruit.web.service.INavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者：qiwj
 * 时间：2020/3/12
 */
@Service
public class NavigationServiceImpl implements INavigationService {
    @Autowired
    private NavigationMapper navigationMapper;
    @Override
    public List<Navigation> selectNavigation() {
        return navigationMapper.selectNavigation();
    }

    @Override
    public int insertSelective(Navigation record) {
        return navigationMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Navigation record) {
        return navigationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Navigation record) {
        return 0;
    }

    @Override
    public Navigation selectByPrimaryKey(Integer id) {
        return navigationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Navigation record) {
        return 0;
    }
}
