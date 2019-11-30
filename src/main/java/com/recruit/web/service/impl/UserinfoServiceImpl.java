package com.recruit.web.service.impl;

import com.recruit.web.mapper.recruit.UserinfoMapper;
import com.recruit.web.pojo.Userinfo;
import com.recruit.web.service.IUserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Userinfo userinfo= userinfoMapper.getUserInfoByNamePwd(username,email);
        return  userinfo;
    }
}
