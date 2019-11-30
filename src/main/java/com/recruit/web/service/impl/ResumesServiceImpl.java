package com.recruit.web.service.impl;

import com.recruit.web.mapper.recruit.ResumesMapper;
import com.recruit.web.pojo.Resumes;
import com.recruit.web.service.IResumesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 作者：qiwj
 * 时间：2019/11/30
 */
@Service
public class ResumesServiceImpl implements IResumesService {
    @Autowired
    private ResumesMapper resumesMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Resumes record) {
      Integer result = resumesMapper.insert(record);
        return result;
    }

    @Override
    public int insertSelective(Resumes record) {
       Integer result= resumesMapper.insertSelective(record);
        return result;
    }

    @Override
    public Resumes selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Resumes record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Resumes record) {
        return 0;
    }
}
