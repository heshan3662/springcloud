package com.example.demo.mybatise.dao.impl;

  import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 import org.springframework.stereotype.Service;

 import javax.annotation.Resource;
import java.util.Map;


@Service
public class MysqlMapper  {

    @Autowired
    @Resource(name = "mysqlTemplate")
    private SqlSessionTemplate sqlSessionTemplate;


     public int getUser(Long id) {
         sqlSessionTemplate.selectOne("updateUser",id);
        return  1;
     }
}
