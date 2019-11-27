package com.recruit.web.mapper.teacher;

import com.recruit.web.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 作者：qiwj
 * 时间：2019/11/27
 */
@Repository
@Mapper
public interface TeacherMapper {
    Teacher selectById();
}
