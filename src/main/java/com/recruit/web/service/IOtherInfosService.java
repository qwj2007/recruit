package com.recruit.web.service;

import com.recruit.web.pojo.Otherinfos;

import java.util.List;

/**
 * 作者：qiwj
 * 时间：2019/12/8
 */
public interface IOtherInfosService {
 List<Otherinfos> selectOtherinfosById(Integer id);
}
