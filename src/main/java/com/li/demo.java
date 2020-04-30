package com.li;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

@EnableAutoConfiguration
@Configuration
@MapperScan("com.li.dao")
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.li.dao"})
public class demo {

@Autowired
private Environment env;

//创建数据源
@Bean
public DataSource getDataSource() {
	DruidDataSource dataSource = new DruidDataSource();
    dataSource.setUrl(env.getProperty("spring.datasource.url"));
    dataSource.setUsername(env.getProperty("spring.datasource.username"));
    dataSource.setPassword(env.getProperty("spring.datasource.password"));
    return dataSource;
} 

//创建SqlSessionFactory  bean
@Bean
public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(getDataSource());
  PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:/mybatis/*.xml"));
    return sqlSessionFactoryBean.getObject();
}



//
@Bean
public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
    WebMvcConfigurerAdapter adapter=new WebMvcConfigurerAdapter(){
        @Override
        public void addViewControllers(ViewControllerRegistry registry){
            //默认是登录页面
            registry.addViewController("/").setViewName("login");
            //成功页面
            registry.addViewController("/index.html").setViewName("login");

            System.out.println("=====");
        }
    };
    return adapter;
}




//添加事物Bean
@Bean
public PlatformTransactionManager transactionManager() {
    return new DataSourceTransactionManager(getDataSource());
}




}
