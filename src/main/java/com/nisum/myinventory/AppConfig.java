package com.nisum.myinventory;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan("com.nisum.myinventory.persistence.mapper")
public class AppConfig {
	@Bean
	@ConfigurationProperties(prefix="dataSource")
	public DataSource getDataSource() {
		PGSimpleDataSource source = new PGSimpleDataSource();
		source.setServerName("localhost");
		source.setDatabaseName("inventory_db");
		source.setUser("admin");
		source.setPassword("admin");
		
		return source;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(getDataSource());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		return sessionFactory.getObject();
	}
}