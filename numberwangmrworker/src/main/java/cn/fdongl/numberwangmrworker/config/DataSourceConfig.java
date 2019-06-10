package cn.fdongl.numberwangmrworker.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {


    @Bean(name = "h2DataSource")
    @Qualifier("h2DataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.h2")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

//    @Bean(name = "hiveDataSource")
//    @Qualifier("hiveDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.hive")
//    public DataSource secondaryDataSource() {
//        System.setProperty("hadoop.home.dir","E:\\winutils-master\\hadoop-3.0.0");
//        System.setProperty("HADOOP_USER_NAME","root");
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "hiveJdbcTemplate")
//    public JdbcTemplate secondaryJdbcTemplate(
//            @Qualifier("hiveDataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }


}
