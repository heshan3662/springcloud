package com.example.demo.jdbctemplate.test;

 import com.example.demo.jdbctemplate.DBTools;
 import org.springframework.beans.factory.annotation.Autowired;

 import org.springframework.transaction.annotation.Transactional;
 import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

 import java.util.ArrayList;
 import java.util.List;


@RestController
@RequestMapping("/testJDBC")

public class TestJDBC {

    @Autowired
    DBTools DBTools;


    /**
     * 测试数据库连接
     * @param name
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public List query( String name) {
        List  list1 = DBTools.testquerysqldb1( )  ;
        List  list2 =  DBTools.testquerysqldb2( )  ;
        list1.addAll(list2);
        return list1;
    }

    /***
     * 测试update
     * @param name
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public int  update( String name) {
        String sql = "update gd_user set department_id =? where  id = ?  " ;
        List  _parames = new ArrayList<Object>();
        _parames.add("1");
        _parames.add("1");
        int i  = DBTools.update(sql,_parames.toArray() )  ;
        return i;
    }

    /****
     *insert返回自增长主键测试
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public Long  insert(  ) {
        String _sql = "";
        List<Object> _parames = new ArrayList<Object>();

        _sql = "insert into gd_user(" +
                 "department_id," +
                "username," +
                "userpass," +
                "truename," +
                "mobile," +
                "token," +
                "token_expiration," +
                "last_login_time," +
                "last_login_ip" +
                ") " +
                "values( ?,?,?,?,?,?,?,?,?);";
         _parames.add("1");
        _parames.add("heshan");
        _parames.add("123");
        _parames.add("何山");
        _parames.add("186");
        _parames.add("000000000");
        _parames.add(1566365262);
        _parames.add("12312312");
        _parames.add(1566365262);
        return   DBTools.insertForPRIMARY_KEY(_sql, _parames.toArray());
    }

    /****
     * 事物测试，程序强制出现异常
     * insert插入返回主键id，事物无法控制 ，猜测原因是jdbc重新打开了一个链接，事物无法管控（）
     * update数据库会回滚
     * @return
     */
    @RequestMapping(value = "/transactionalTest", method = RequestMethod.GET)
    @Transactional(rollbackFor=Exception.class)
    public Long  TransactionalTest(  ) {
        String _sql = "";
        List<Object> _parames = new ArrayList<Object>();
        //测试点1：insert返回主键id
        _sql = "insert into gd_user(" +
                "department_id," +
                "username," +
                "userpass," +
                "truename," +
                "mobile," +
                "token," +
                "token_expiration," +
                "last_login_time," +
                "last_login_ip" +
                ") " +
                "values( ?,?,?,?,?,?,?,?,?);";
        _parames.add("1");
        _parames.add("heshan");
        _parames.add("123");
        _parames.add("何山");
        _parames.add("186");
        _parames.add("000000000");
        _parames.add(1566365262);
        _parames.add("12312312");
        _parames.add(1566365262);
         Long res=    DBTools.insertForPRIMARY_KEY(_sql, _parames.toArray());
        //测试点2：update表
        String sql = "update gd_user set department_id =? where  id = ?  " ;
       _parames = new ArrayList<Object>();
        _parames.add("2");
        _parames.add(res);
        DBTools.update(sql,_parames.toArray() )  ;
         int i = 5/0 ;//让程序出exception，测试事物是否有用
        return res;
    }

    public String hiError(@RequestParam String  name) {
        return "hi," + name + ",sorry,error!";
    }
}