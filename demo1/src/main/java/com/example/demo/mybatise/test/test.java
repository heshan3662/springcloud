package com.example.demo.mybatise.test;

  import com.example.demo.mybatise.dao.impl.MysqlMapper;
  import com.example.demo.mybatise.entity.Test;
import com.example.demo.mybatise.entity.Users;
import com.example.demo.mybatise.sqlserverdao.impl.SqlServerMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/testMybatise")
class TestMybatise {
    @Autowired
    MysqlMapper mysqlMapper;

    @Autowired
    SqlServerMapper sqlServerMapper;

    @Autowired
    @Resource(name = "sqlserverTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    @RequestMapping(value = "/getUser")
    public int getTowDataResource() {
//        Map o = sqlSessionTemplate.selectOne("com.example.demo.mybatise.dao.MysqlMapper.getUser",9L);
//        return o;
        int i  = mysqlMapper.getUser(9L);
        return i;
    }

    @RequestMapping(value = "/getTest")
    public Test  getSqlServerData() {
        return  sqlSessionTemplate.selectOne("com.example.demo.mybatise.sqlserverdao.SqlMapper.getTest",11);
//        return sqlServerMapper.getTest(11);
    }
}