package com.recruit.web.service;

import com.recruit.web.pojo.Resumes;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 作者：qiwj
 * 时间：2019/11/30
 */
public interface IResumesService {
    int deleteByPrimaryKey(Integer id);

    int insert(Resumes record);

    int insertSelective(Resumes record);

    Resumes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resumes record);

    int updateByPrimaryKey(Resumes record);

    Resumes selctResumeByUserId( String userid);

    String PersoncenterCheck(HttpServletRequest request, Model model);

    List<Resumes> selectResumeAll();
}
