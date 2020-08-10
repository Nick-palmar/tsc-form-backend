/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Map;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@SpringBootApplication
public class Main {

  @Value("${spring.datasource.url}")
  private String dbUrl;

  @Autowired
  private DataSource dataSource;

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
  }

  @RequestMapping("/")
  String index() {
    return "index";
  }
 

@RequestMapping("/form")
@ResponseBody
public String form(@RequestParam(required=true) String date, 
					@RequestParam(required=true) String name, 
					@RequestParam(required=true) String email,
					@RequestParam(required=true) String role, 
					@RequestParam (required=true) String age, 
					@RequestParam(defaultValue="false") boolean question1,
					@RequestParam(defaultValue="false") boolean question2,
					@RequestParam(defaultValue="false") boolean question3,
					@RequestParam(defaultValue="false") boolean question4,
					@RequestParam(defaultValue="false") boolean question5,
					@RequestParam(defaultValue="false") boolean question6,
					@RequestParam(defaultValue="false") boolean question7,
					@RequestParam(defaultValue="false") boolean question8,
					@RequestParam(defaultValue="false") boolean question9,
					@RequestParam(defaultValue="false") boolean question10) { 
    
	// send values to form
	try {
		this.insertIntoForm(date, name, email, role, age, question1, question2, question3, question4, question5, question6, question7, question8, question9, question10);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	
	
	
	return "{\"formDate\": " + "\"" + date + "\"" + ", \"name\": " + "\"" + name + "\"" + ", \"email\": " + "\"" + email + "\"" + ", \"role\": " + "\"" + role + "\"" + ", \"age\": " + "\"" + age + "\"" + "}";
}

private void insertIntoForm(String date, String name, String email, String role, String age, boolean question1, boolean question2, boolean question3, boolean question4, boolean question5, boolean question6, boolean question7, boolean question8, boolean question9, boolean question10) throws SQLException {
	try (Connection connection = dataSource.getConnection();
		// sql insert into statement with missing values
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO form(form_date,name,email,role,age_group,question_1,question_2,question_3,question_4,question_5,question_6,question_7,question_8,question_9,question_10) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)")) {
        
		// change date from string to date
		String pattern = "yyyy/MM/dd";
		SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
		Date formatDateUtil = null;
		try {
			formatDateUtil = dateFormatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// add values to statement passed from method
		preparedStatement.setDate(1, new java.sql.Date(formatDateUtil.getTime()));
		preparedStatement.setString(2, name);
		preparedStatement.setString(3, email);
		preparedStatement.setString(4, role);
		preparedStatement.setString(5, age);
		preparedStatement.setBoolean(6, question1);
		preparedStatement.setBoolean(7, question2);
		preparedStatement.setBoolean(8, question3);
		preparedStatement.setBoolean(9, question4);
		preparedStatement.setBoolean(10, question5);
		preparedStatement.setBoolean(11, question6);
		preparedStatement.setBoolean(12, question7);
		preparedStatement.setBoolean(13, question8);
		preparedStatement.setBoolean(14, question9);
		preparedStatement.setBoolean(15, question10);
		
		
		preparedStatement.executeUpdate();

    } catch (SQLException e) {
		e.getMessage();
    }
}


  @Bean
  public DataSource dataSource() throws SQLException {
    if (dbUrl == null || dbUrl.isEmpty()) {
      return new HikariDataSource();
    } else {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(dbUrl);
      return new HikariDataSource(config);
    }
  }

}
