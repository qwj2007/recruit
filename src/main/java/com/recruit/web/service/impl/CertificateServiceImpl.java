package com.recruit.web.service.impl;

import com.recruit.web.mapper.recruit.CertificateMapper;
import com.recruit.web.pojo.Certificate;
import com.recruit.web.service.ICertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者：qiwj
 * 时间：2019/12/8
 */
@Service
public class CertificateServiceImpl implements ICertificateService {
    @Autowired
    private CertificateMapper certificateMapper;
    @Override
    public List<Certificate> selectCertificateByResumeId(Integer id) {
        return certificateMapper.selectCertificateByResumeId(id);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return certificateMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Certificate record) {
        return certificateMapper.insert(record);
    }

    @Override
    public int insertSelective(Certificate record) {
        return certificateMapper.insertSelective(record);
    }

    @Override
    public Certificate selectByPrimaryKey(Integer id) {
        return certificateMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Certificate record) {
        return certificateMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Certificate record) {
        return certificateMapper.updateByPrimaryKey(record);
    }
}
