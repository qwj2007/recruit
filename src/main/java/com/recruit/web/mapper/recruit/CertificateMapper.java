package com.recruit.web.mapper.recruit;

import com.recruit.web.pojo.Certificate;

public interface CertificateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Certificate record);

    int insertSelective(Certificate record);

    Certificate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Certificate record);

    int updateByPrimaryKey(Certificate record);
}