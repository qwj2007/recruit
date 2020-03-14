package com.recruit.web.service;

import com.recruit.web.pojo.Hrnotice;

import java.util.List;

/**
 * 作者：qiwj
 * 时间：2019/11/1
 */
public interface IHrnoticeService {
     List<Hrnotice> GetHrnoticeByUserId(String userid);
     Hrnotice selectByPrimaryKey(Integer id);
}
