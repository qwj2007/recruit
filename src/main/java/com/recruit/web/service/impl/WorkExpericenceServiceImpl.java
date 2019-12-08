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
}
