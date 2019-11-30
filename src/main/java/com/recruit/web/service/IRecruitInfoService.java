package com.recruit.web.service;



import com.recruit.web.pojo.Recruitinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 作者：qiwj
 * 时间：2019/11/26
 */

public interface IRecruitInfoService {
     List<Recruitinfo> selectRecruitInfos();
     Recruitinfo  selectById(@Param("id") Integer id);
}
