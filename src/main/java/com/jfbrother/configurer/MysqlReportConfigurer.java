package com.jfbrother.configurer;

import com.alibaba.druid.pool.DruidDataSource;
import com.jfbrother.configurer.properties.MysqlReportConfigurerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@Slf4j
@EnableConfigurationProperties(MysqlReportConfigurerProperties.class)
public class MysqlReportConfigurer {
    @Autowired
    private MysqlReportConfigurerProperties druidSettings;

    /**
     * mssql数据源
     *
     * @return
     */
    @Bean("mysqlReportDruidDataSource")
    public DruidDataSource dataSource() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        druidSettings.setDruidDataSource(dataSource, true);
        return dataSource;
    }

    @Bean(name = "mysqlReportJdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(
            @Qualifier("mysqlReportDruidDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
