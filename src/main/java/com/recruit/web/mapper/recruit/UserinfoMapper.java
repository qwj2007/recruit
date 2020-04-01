package com.recruit.web.mapper.recruit;

import com.recruit.web.pojo.Userinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);

    Userinfo getUserInfoByNamePwd(@Param("username") String username, @Param("pwd") String pwd);

    Userinfo  getUserInfo(@Param("username") String username, @Param("email") String email);
    List<Userinfo> getUserAll(Map<String,Object> map);
    int deleteUser(@Param("id") List<Integer> id);
    int updatePwd(@Param("id") List<Integer> id);
}