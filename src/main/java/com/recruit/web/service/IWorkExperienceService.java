package com.recruit.web.service;

import com.recruit.web.pojo.Workexperience;

import java.util.List;

/**
 * 作者：qiwj
 * 时间：2019/12/8
 */
public interface IWorkExperienceService {
   List<Workexperience>  selectWorkExperienceById(Integer resumeid);
   Workexperience selectByPrimaryKey(Integer id);

   int deleteByPrimaryKey(Integer id);

   int insert(Workexperience record);

   int insertSelective(Workexperience record);

   int updateByPrimaryKeySelective(Workexperience record);

   int updateByPrimaryKey(Workexperience record);


}
