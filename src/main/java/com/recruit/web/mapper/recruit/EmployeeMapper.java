package com.recruit.web.mapper.recruit;

import com.recruit.web.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    Employee getEmployeeByNamePwd(@Param("userid") String userid,@Param("password") String password);
    List<Employee>getEmployeeAll();

    int updateBatch(@Param("id") List<Integer> id);
    int updatePwd(@Param("id") List<Integer> id);


}