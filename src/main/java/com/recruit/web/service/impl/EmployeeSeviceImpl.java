package com.recruit.web.service.impl;

import com.recruit.web.mapper.recruit.EmployeeMapper;
import com.recruit.web.pojo.Employee;
import com.recruit.web.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
