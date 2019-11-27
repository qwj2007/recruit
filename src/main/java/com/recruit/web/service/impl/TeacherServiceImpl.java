package com.recruit.web.service.impl;

import com.recruit.web.mapper.teacher.TeacherMapper;
import com.recruit.web.pojo.Teacher;
import com.recruit.web.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 作者：qiwj
 * 时间：2019/11/27
 */
@Service
public class TeacherServiceImpl implements ITeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public Teacher selectById() {
        return teacherMapper.selectById();
    }
}
