package com.recruit.web.service.impl;

import com.recruit.web.mapper.recruit.BaseinfoMapper;
import com.recruit.web.pojo.Baseinfo;
import com.recruit.web.service.IBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 作者：qiwj
 * 时间：2020/3/13
 */
@Service
public class BaseInfoServiceImpl implements IBaseInfoService {
    @Autowired
    private BaseinfoMapper baseinfoMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Baseinfo record) {
        return 0;
    }

    @Override
    public int insertSelective(Baseinfo record) {
        return 0;
    }

    @Override
    public Baseinfo selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Baseinfo record) {
        return baseinfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Baseinfo record) {
        return 0;
    }

    @Override
    public Baseinfo selectBaseInfo() {
        return baseinfoMapper.selectBaseInfo();
    }
}
