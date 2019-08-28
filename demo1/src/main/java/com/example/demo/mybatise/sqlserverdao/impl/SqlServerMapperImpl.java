package com.example.demo.mybatise.sqlserverdao.impl;

 import com.example.demo.mybatise.entity.Test;
  import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

@Repository
public class SqlServerMapperImpl implements SqlServerMapper {

    @Autowired
    @Resource(name = "sqlserverTemplate")
    SqlSessionTemplate sqlSessionTemplate;


    @Override
    public Test  getTest(Integer id) {
        Test  o = sqlSessionTemplate.selectOne("com.example.demo.mybatise.sqlserverdao.SqlMapper.getTest",id);
        return o;
    }
}
