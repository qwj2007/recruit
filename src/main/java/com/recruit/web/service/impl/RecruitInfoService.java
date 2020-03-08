package com.recruit.web.service.impl;



import com.recruit.web.mapper.recruit.RecruitinfoMapper;
import com.recruit.web.pojo.Recruitinfo;
import com.recruit.web.service.IRecruitInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者：qiwj
 * 时间：2019/11/26
 */
@Service
public class RecruitInfoService implements IRecruitInfoService {
    @Autowired
    private RecruitinfoMapper recruitinfoMapper;


@Override
    public List<Recruitinfo> selectRecruitInfos() {
        List<Recruitinfo> list=recruitinfoMapper.selectRecruitInfos();
        return list;
    }

    @Override
    public Recruitinfo selectById(Integer id) {
        return recruitinfoMapper.selectById(id);
    }

    @Override
    public List<Recruitinfo> selectAllRecruitInfos() {
        List<Recruitinfo> list=recruitinfoMapper.selectAllRecruitInfos();
        return list;
    }

    @Override
    public Integer updateByPrimaryKeySelective(Recruitinfo record) {
      return   recruitinfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer insertSelective(Recruitinfo recruitinfo) {
        return recruitinfoMapper.insertSelective(recruitinfo);
    }

    @Override
    public Integer updateBatch(List<Integer> ids) {
        return recruitinfoMapper.updateBatch(ids);
    }


}
