package com.recruit.web.service.impl;

import com.recruit.web.mapper.recruit.EmailsetMapper;
import com.recruit.web.pojo.Emailset;
import com.recruit.web.service.IEmailsetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 作者：qiwj
 * 时间：2019/12/11
 */
@Service
public class EmailsetServiceImpl implements IEmailsetService {
    @Autowired
    private EmailsetMapper emailsetMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Emailset record) {
        return 0;
    }

    @Override
    public int insertSelective(Emailset record) {
        return 0;
    }

    @Override
    public Emailset selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Emailset record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Emailset record) {
        return 0;
    }

    @Override
    public Emailset selectOneEmail() {
        return emailsetMapper.selectOneEmail();
    }
}
