package com.palmar.covid19;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@SpringBootApplication
public class Application {
	
	public DataSource appDataSource;
	
	@Value("${spring.datasource.url}")
	private String dbUrl;
	
	@Autowired
	private DataSource dataSource;
	
	public Application() {
		this.appDataSource = dataSource;
		System.out.println(this.appDataSource);
	}

	public static void main(String[] args) {
		System.out.println("Entering main");
		SpringApplication.run(Application.class, args);

	}
	
	@Bean
	public DataSource dataSource() throws SQLException {
		System.out.println("Entering dataSource");
		if (dbUrl == null || dbUrl.isEmpty()) {
			System.out.println("url is empty");
			return new HikariDataSource();
		} else {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(dbUrl);
			System.out.println("dataSource url: " + dbUrl);
			System.out.println("config: " + config);
			HikariDataSource hds = new HikariDataSource(config);
			System.out.println("hikari: " + hds);
			return hds;
		}
	}

}
