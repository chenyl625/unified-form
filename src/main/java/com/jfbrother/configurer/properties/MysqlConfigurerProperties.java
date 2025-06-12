package com.jfbrother.configurer.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class MysqlConfigurerProperties extends BaseConfigurerProperties {

}
