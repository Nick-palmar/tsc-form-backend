package com.palmar.covid19.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.palmar.covid19.data.CovidForm;

public class FormDaoImpl {
	
	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Autowired
	private DataSource dataSource;
	
	
	public void insertIntoForm(CovidForm formData) throws SQLException{
		try (Connection connection = dataSource.getConnection();
				// sql insert into statement with missing values
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO form(form_date,name,email,role,age_group,question_1,question_2,question_3,question_4,question_5,question_6,question_7,question_8,question_9,question_10) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);")) {
		        
				// change date from string to date
				String pattern = "yyyy/MM/dd";
				SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
				Date formatDateUtil = null;
				try {
					formatDateUtil = dateFormatter.parse(formData.getDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				// add values to statement passed from method
				preparedStatement.setDate(1, new java.sql.Date(formatDateUtil.getTime()));
				preparedStatement.setString(2, formData.getName());
				preparedStatement.setString(3, formData.getEmail());
				preparedStatement.setString(4, formData.getRole());
				preparedStatement.setString(5, formData.getAge());
				preparedStatement.setBoolean(6, formData.getQ1());
				preparedStatement.setBoolean(7, formData.getQ2());
				preparedStatement.setBoolean(8, formData.getQ3());
				preparedStatement.setBoolean(9, formData.getQ4());
				preparedStatement.setBoolean(10, formData.getQ5());
				preparedStatement.setBoolean(11, formData.getQ6());
				preparedStatement.setBoolean(12, formData.getQ7());
				preparedStatement.setBoolean(13, formData.getQ8());
				preparedStatement.setBoolean(14, formData.getQ9());
				preparedStatement.setBoolean(15, formData.getQ10());
				
				
				preparedStatement.executeUpdate();

		    } catch (SQLException e) {
				e.getMessage();
		    }
	}
}
