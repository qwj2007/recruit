package com.recruit.web.service.impl;

import com.recruit.web.mapper.recruit.UserinfoMapper;
import com.recruit.web.pojo.Userinfo;
import com.recruit.web.service.IUserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 作者：qiwj
 * 时间：2019/11/28
 */
@Service
public class UserinfoServiceImpl implements IUserinfoService{
    @Autowired
    private UserinfoMapper userinfoMapper;
    @Override
    public Userinfo getUserInfoByNamePwd(String username, String pwd) {
        Userinfo userinfo= userinfoMapper.getUserInfoByNamePwd(username,pwd);
        return  userinfo;
    }

    @Override
    public Userinfo getUserInfo(String username, String email) {
        Userinfo userinfo= userinfoMapper.getUserInfo(username,email);
        return  userinfo;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Userinfo record) {
        return 0;
    }

    @Override
    public int insertSelective(Userinfo record) {
     return   userinfoMapper.insertSelective(record);
    }

    @Override
    public Userinfo selectByPrimaryKey(Integer id) {
        Userinfo userinfo= userinfoMapper.selectByPrimaryKey(id);
        return  userinfo;
    }

    @Override
    public int updateByPrimaryKeySelective(Userinfo record) {
        return userinfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Userinfo record) {
        return userinfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Userinfo> getUserAll(Map<String,Object> map) {
        return userinfoMapper.getUserAll(map);
    }

    @Override
    public int deleteUser(List<Integer> id) {
        return userinfoMapper.deleteUser(id);
    }

    @Override
    public int updatePwd(List<Integer> id) {
        return userinfoMapper.updatePwd(id);
    }
}
