package com.recruit.web.service.impl;

import com.recruit.web.mapper.recruit.FamilyMapper;
import com.recruit.web.pojo.Family;
import com.recruit.web.service.IFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者：qiwj
 * 时间：2019/12/8
 */
@Service
public class FamilyServiceImpl implements IFamilyService {
    @Autowired
    private FamilyMapper familyMapper;
    @Override
    public List<Family> selectFamilyByResumeId(int id) {
        return familyMapper.selectFamilyByResumeId(id);
    }
}
