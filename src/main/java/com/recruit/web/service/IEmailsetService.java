package com.recruit.web.service;

import com.recruit.web.pojo.Emailset;

/**
 * 作者：qiwj
 * 时间：2019/12/11
 */
public interface IEmailsetService {
    int deleteByPrimaryKey(Integer id);

    int insert(Emailset record);

    int insertSelective(Emailset record);

    Emailset selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Emailset record);

    int updateByPrimaryKey(Emailset record);

    Emailset selectOneEmail();
}
