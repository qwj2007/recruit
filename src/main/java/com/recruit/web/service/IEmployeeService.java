package com.recruit.web.service;

import com.recruit.web.pojo.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者：qiwj
 * 时间：2020/3/7
 */
@Service
public interface IEmployeeService {
    Employee getEmployeeByUserIdPwd(String userid, String pwd);

    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<Employee> getEmployeeAll();

    int updateBatch(List<Integer> id);
    int updatePwd( List<Integer> id);
}
