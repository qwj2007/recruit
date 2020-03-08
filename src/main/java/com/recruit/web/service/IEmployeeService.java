package com.recruit.web.service;

import com.recruit.web.pojo.Employee;
import org.springframework.stereotype.Service;

/**
 * 作者：qiwj
 * 时间：2020/3/7
 */
@Service
public interface IEmployeeService {
    Employee getEmployeeByUserIdPwd(String userid,String pwd);
}
