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
}
