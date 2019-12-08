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
}
