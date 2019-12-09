package com.recruit.web.service.impl;

import com.recruit.web.mapper.recruit.WorkexperienceMapper;
import com.recruit.web.pojo.Workexperience;
import com.recruit.web.service.IWorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者：qiwj
 * 时间：2019/12/8
 */
@Service
public class WorkExpericenceServiceImpl implements IWorkExperienceService {
    @Autowired
    private WorkexperienceMapper workExperienceService;
    @Override
    public List<Workexperience> selectWorkExperienceById(Integer resumeid) {
        return workExperienceService.selectWorkExperienceById(resumeid);
    }

    @Override
    public Workexperience selectByPrimaryKey(Integer id) {
        return workExperienceService.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return workExperienceService.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Workexperience record) {
        return workExperienceService.insert(record);
    }

    @Override
    public int insertSelective(Workexperience record) {
        return workExperienceService.insert(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Workexperience record) {
        return workExperienceService.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Workexperience record) {
        return workExperienceService.updateByPrimaryKey(record);
    }
}
