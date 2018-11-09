package com.fxzhj.common.conf;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;


@Configuration
public class DruidDataSourceConfig {

    @Bean
    public DataSource dataSource(){
    	/*DataSource dataSource = new DataSource();
        dataSource.setUrl("jdbc:mysql://139.129.98.216:3306/test?useUnicode=true&characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");*/
        DruidDataSource dataSource = new DruidDataSource();

    	dataSource.setUrl("jdbc:mysql://localhost:3306/charging_pile?useUnicode=true&characterEncoding=UTF-8");
    	dataSource.setUsername("root");
    	dataSource.setPassword("1234");
    	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    	/*DruidDataSource dataSource = new DruidDataSource();

    	dataSource.setUrl(this.dbUrl);
    	dataSource.setUsername(username);
    	dataSource.setPassword(password);
    	dataSource.setDriverClassName(driverClassName);

        //configuration
    	dataSource.setInitialSize(initialSize);
    	dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
        	dataSource.setFilters(filters);
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        dataSource.setConnectionProperties(connectionProperties);  */
        return dataSource;
    }
}
