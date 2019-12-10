package com.recruit.web.service.impl;

import com.recruit.web.mapper.recruit.OtherinfosMapper;
import com.recruit.web.pojo.Otherinfos;
import com.recruit.web.service.IOtherInfosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者：qiwj
 * 时间：2019/12/8
 */
@Service
public class OtherInfosServiceImpl implements IOtherInfosService {
    @Autowired
    private OtherinfosMapper otherinfosMapper;
    @Override
    public List<Otherinfos> selectOtherinfosById(Integer id) {
        return otherinfosMapper.selectOtherinfosById(id);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return otherinfosMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Otherinfos record) {
        return otherinfosMapper.insert(record);
    }

    @Override
    public int insertSelective(Otherinfos record) {
        return otherinfosMapper.insertSelective(record);
    }

    @Override
    public Otherinfos selectByPrimaryKey(Integer id) {
        return otherinfosMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Otherinfos record) {
        return otherinfosMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Otherinfos record) {
        return otherinfosMapper.updateByPrimaryKey(record);
    }
}
