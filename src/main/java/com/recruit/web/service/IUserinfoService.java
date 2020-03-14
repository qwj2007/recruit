package com.recruit.web.service;

import com.recruit.web.pojo.Userinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 作者：qiwj
 * 时间：2019/11/28
 */
public interface IUserinfoService {
    Userinfo getUserInfoByNamePwd(String username, String pwd);
    Userinfo  getUserInfo(String username,String email);

    int deleteByPrimaryKey(Integer id);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);
    List<Userinfo> getUserAll();

    int deleteUser( List<Integer> id);
    int updatePwd(List<Integer> id);
}
