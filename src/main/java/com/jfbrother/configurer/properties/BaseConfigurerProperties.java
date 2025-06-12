package com.jfbrother.configurer.properties;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;

import java.sql.SQLException;
import java.util.Properties;

@Data
public class BaseConfigurerProperties {
    //是否启用重试机制
    private Boolean breakAfterAcquireFailure;
    //重试间隔时间
    private Integer timeBetweenConnectErrorMillis;
    //重试错误次数
    private Integer connectionErrorRetryAttempts;

    private String type;
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    private Integer initialSize;
    private Integer minIdle;
    private Integer maxActive;
    private Long maxWait;
    private Long timeBetweenEvictionRunsMillis;
    private Long minEvictableIdleTimeMillis;
    private String validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean poolPreparedStatements;
    private Integer maxPoolPreparedStatementPerConnectionSize;
    private String filters;
    private String connectionProperties;
    private boolean useGlobalDataSourceStat;

    /**
     * 将属性赋值给DruidDataSource
     *
     * @param dataSource     数据源
     * @param needSetAccount 是否需要设置账号信息
     * @throws SQLException
     */
    public void setDruidDataSource(DruidDataSource dataSource, Boolean needSetAccount) throws SQLException {
        setDruidDataSource(dataSource, needSetAccount, null);
    }

    public void setDruidDataSource(DruidDataSource dataSource, Boolean needSetAccount, String connectionProperties) throws SQLException {
        if (needSetAccount) {
            dataSource.setUrl(getUrl());
            dataSource.setUsername(getUsername());
            dataSource.setPassword(getPassword());
            //dataSource.setDriverClassName(getDriverClassName());
        }
        if (getInitialSize() != null) {
            dataSource.setInitialSize(getInitialSize());
        }
        if (getMinIdle() != null) {
            dataSource.setMinIdle(getMinIdle());
        }
        if (getMaxActive() != null) {
            dataSource.setMaxActive(getMaxActive());
        }
        if (getMaxWait() != null) {
            dataSource.setMaxWait(getMaxWait());
        }
        if (getTimeBetweenEvictionRunsMillis() != null) {
            dataSource.setTimeBetweenEvictionRunsMillis(getTimeBetweenEvictionRunsMillis());
        }
        if (getMinEvictableIdleTimeMillis() != null) {
            dataSource.setMinEvictableIdleTimeMillis(getMinEvictableIdleTimeMillis());
        }
        if (getTimeBetweenConnectErrorMillis() != null) {
            dataSource.setTimeBetweenConnectErrorMillis(getTimeBetweenConnectErrorMillis());
        }
        if (getBreakAfterAcquireFailure() != null) {
            dataSource.setBreakAfterAcquireFailure(getBreakAfterAcquireFailure());
        }
        if(getConnectionErrorRetryAttempts()!=null){
            dataSource.setConnectionErrorRetryAttempts(getConnectionErrorRetryAttempts());
        }
        String validationQuery = getValidationQuery();
        if (validationQuery != null && !"".equals(validationQuery)) {
            dataSource.setValidationQuery(validationQuery);
        }
        dataSource.setTestWhileIdle(isTestWhileIdle());
        dataSource.setTestOnBorrow(isTestOnBorrow());
        dataSource.setTestOnReturn(isTestOnReturn());
        if (isPoolPreparedStatements()) {
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(getMaxPoolPreparedStatementPerConnectionSize());
        }
        dataSource.setFilters(getFilters());//这是最关键的,否则SQL监控无法生效

        String connectionPropertiesStr = getConnectionProperties();
        if (connectionProperties != null) {
            //如果有自定义的配置传进来，则使用自定义配置
            connectionPropertiesStr = connectionProperties;
        }
        if (connectionPropertiesStr != null && !"".equals(connectionPropertiesStr)) {
            Properties connectProperties = new Properties();
            String[] propertiesList = connectionPropertiesStr.split(";");
            for (String propertiesTmp : propertiesList) {
                //第一个等号出现的索引
                int denghaoIndex = propertiesTmp.indexOf("=");
                String key = propertiesTmp.substring(0, denghaoIndex);
                String value = propertiesTmp.substring(denghaoIndex + 1);
                connectProperties.put(key, value);
            }
            dataSource.setConnectProperties(connectProperties);
        }
        dataSource.setUseGlobalDataSourceStat(isUseGlobalDataSourceStat());
    }
}
