package com.example.demo.mybatise;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.demo.mybatise.sqlserverdao.impl", sqlSessionFactoryRef = "sqlserverSessionFactory")
public class MybatisSqlServerConfig {


    @Bean(name = "sqlserverMybatise")
    @ConfigurationProperties(prefix = "spring.sqlserver-mybatise.datasource")
    public DataSource mysqlDataSource() {

        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlserverTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("sqlserverMybatise") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlserverSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("sqlserverMybatise") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);
        bean.setDataSource(dataSource);
        ResourcePatternResolver rsourcePatternResolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(rsourcePatternResolver.getResources("classpath*:com/example/demo/mybatise/sqlserverdao/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "sqlserverTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlserverSessionFactory") SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }
}
