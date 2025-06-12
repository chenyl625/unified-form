package com.jfbrother;


import net.unicon.cas.client.configuration.EnableCasClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class,
		RabbitAutoConfiguration.class
})
@EnableJpaAuditing
@EnableCasClient
@EnableScheduling
@EnableCaching
public class BaseServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseServerApplication.class, args);
	}

//	/**
//	 * Querydsl for SQL 构造工厂，需要时开启
//	 * @param dataSource
//	 * @return
//	 */
//	@Bean
//	public SQLQueryFactory sqlQueryFactory(@Qualifier("primaryDataSource") DataSource dataSource){
//		SQLTemplates templates = new MySQLTemplates();
//		return new SQLQueryFactory(new com.querydsl.sql.Configuration(templates), dataSource);
//	}
}
