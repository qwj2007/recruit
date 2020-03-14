package com.recruit.web.service.impl;

import com.recruit.web.mapper.recruit.EmployeeMapper;
import com.recruit.web.pojo.Employee;
import com.recruit.web.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者：qiwj
 * 时间：2020/3/7
 */
@Service
public class EmployeeSeviceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public Employee getEmployeeByUserIdPwd(String userid, String pwd) {
        Employee employee= employeeMapper.getEmployeeByNamePwd(userid,pwd);
        return  employee;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Employee record) {
        return 0;
    }

    @Override
    public int insertSelective(Employee record) {
        return employeeMapper.insertSelective(record);
    }

    @Override
    public Employee selectByPrimaryKey(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Employee record) {
        return employeeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Employee record) {
        return 0;
    }

    @Override
    public List<Employee> getEmployeeAll() {
        return employeeMapper.getEmployeeAll();
    }

    @Override
    public int updateBatch(List<Integer> id) {
        return employeeMapper.updateBatch(id);
    }

    @Override
    public int updatePwd(List<Integer> id) {
        return employeeMapper.updatePwd(id);
    }
}
