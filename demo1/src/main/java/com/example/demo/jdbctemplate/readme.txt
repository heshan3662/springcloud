此为双数据源配置，注意
XXapplication.java 启动类
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)

调用 ：
    @Autowired
    DBTools DBTools;

    public List query( String name) {
        return  DBTools.testquerysqldb1( )  ;
    }


事物配置：
    @Transactional(rollbackFor=Exception.class)
    可以在方法、类上加事物，在一次连接内有效！

测试：
http://localhost:8762/test/query