package com.recruit.web.service;

import com.recruit.web.pojo.Certificate;

import java.util.List;

/**
 * 作者：qiwj
 * 时间：2019/12/8
 */
public interface ICertificateService {
    List<Certificate> selectCertificateByResumeId(Integer id);
    int deleteByPrimaryKey(Integer id);

    int insert(Certificate record);

    int insertSelective(Certificate record);

    Certificate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Certificate record);

    int updateByPrimaryKey(Certificate record);
}
