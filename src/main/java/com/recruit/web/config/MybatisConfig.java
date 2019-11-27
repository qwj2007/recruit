package com.recruit.web.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 作者：qiwj
 * 时间：2019/11/27
 */
//@Configuration
//@MapperScan("com.recruit.web.mapper")
public class MybatisConfig {
    //@Bean
    //@ConfigurationProperties(prefix = "spring.datasource")
   // public DataSource dataSource()
    //{
     //   return  new DruidDataSource();
    //}
}
