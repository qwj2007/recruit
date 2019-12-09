package com.recruit.web.service.impl;

import com.recruit.web.mapper.recruit.EducationexperienceMapper;
import com.recruit.web.pojo.Educationexperience;
import com.recruit.web.service.IEdutionExpericenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者：qiwj
 * 时间：2019/12/8
 */
@Service
public class EdutionExpericenceServiceImpl implements IEdutionExpericenceService {

    @Autowired
    private EducationexperienceMapper educationexperienceMapper;
    @Override
    public List<Educationexperience> selectEducationByResumeid(Integer id) {
        return educationexperienceMapper.selectEducationByResumeid(id);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return educationexperienceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Educationexperience record) {
        return 0;
    }

    @Override
    public int insertSelective(Educationexperience record) {
        return educationexperienceMapper.insertSelective(record);
    }

    @Override
    public Educationexperience selectByPrimaryKey(Integer id) {
        return educationexperienceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Educationexperience record) {
        return educationexperienceMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Educationexperience record) {
        return educationexperienceMapper.updateByPrimaryKey(record);
    }
}
