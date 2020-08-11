package com.palmar.covid19.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		        
				
				// add values to statement passed from method
				preparedStatement.setDate(1, new java.sql.Date(formData.getDate().getTime()));
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
	
	public ArrayList<CovidForm> getRequestedForms(CovidForm adminRequest) throws SQLException {
		final String myQuery;
		// initialize array list to be returned
		ArrayList<CovidForm> selectedRecords = new ArrayList<CovidForm>();
		CovidForm currentRecord;
		
		String notFlaggedQueryCheck = "question_1 = 'f' AND question_2 = 'f' AND question_3 = 'f' AND question_4 = 'f' AND"
				+ " question_5 = 'f' AND question_6 = 'f' AND question_7 = 'f' AND question_8 = 'f' AND question_9 = 'f' AND question_10 = 'f';";
		String FlaggedQueryCheck = "question_1 = 't' OR question_2 = 't' OR question_3 = 't' OR question_4 = 't' OR"
				+ " question_5 = 't' OR question_6 = 't' OR question_7 = 't' OR question_8 = 't' OR question_9 = 't' OR question_10 = 't'";
		
		// there are 3 parameters to be checked, so 8 possible different queries the admin can request. Check which query the admin has requested
		if (adminRequest.getFlagStatus() == true && !adminRequest.getAge().equals("any") && !adminRequest.getName().isEmpty()) {
			myQuery = "SELECT * FROM form "
					+ "WHERE form_date=" + adminRequest.getDate() + "AND age_group=" + adminRequest.getAge() + "AND name=" + adminRequest.getName() + "AND (" + FlaggedQueryCheck + ");";
		} else if (adminRequest.getFlagStatus() == false && !adminRequest.getAge().equals("any") && !adminRequest.getName().isEmpty()) {
			myQuery = "SELECT * FROM form "
					+ "WHERE form_date=" + adminRequest.getDate() + "AND age_group=" + adminRequest.getAge() + "AND name=" + adminRequest.getName() + ";";
		} else if (adminRequest.getFlagStatus() == false && adminRequest.getAge().equals("any") && !adminRequest.getName().isEmpty()) {
			myQuery = "SELECT * FROM form "
					+ "WHERE form_date=" + adminRequest.getDate() + "AND name=" + adminRequest.getName() + ";";
		} else if (adminRequest.getFlagStatus() == true && adminRequest.getAge().equals("any") && adminRequest.getName().isEmpty()) {
			myQuery = "SELECT * FROM form "
					+ "WHERE form_date=" + adminRequest.getDate() + "AND (" + FlaggedQueryCheck + ");";
		} else if (adminRequest.getFlagStatus() == true && !adminRequest.getAge().equals("any") && adminRequest.getName().isEmpty()) {
			myQuery = "SELECT * FROM form "
					+ "WHERE form_date=" + adminRequest.getDate() + "AND age_group=" + adminRequest.getAge() + "AND (" + FlaggedQueryCheck + ");";
		} else if (adminRequest.getFlagStatus() == true && adminRequest.getAge().equals("any") && !adminRequest.getName().isEmpty()) {
			myQuery = "SELECT * FROM form "
					+ "WHERE form_date=" + adminRequest.getDate() + "AND name=" + adminRequest.getName() + "AND (" + FlaggedQueryCheck + ";";
		} else if (adminRequest.getFlagStatus() == false && !adminRequest.getAge().equals("any") && adminRequest.getName().isEmpty()) {
			myQuery = "SELECT * FROM form "
					+ "WHERE form_date=" + adminRequest.getDate() + "AND age_group=" + adminRequest.getAge() + ";";
		} else {
			myQuery = "SELECT * FROM form "
					+ "WHERE form_date=" + adminRequest.getDate() + ";";
		}
		try (Connection connection = dataSource.getConnection();
				// prepare sql select query
				PreparedStatement preparedStatement = connection.prepareStatement(myQuery)) {
			        // get records from select
					ResultSet resultSet = preparedStatement.executeQuery();
					
					// loop through records to create new covid form objects (similar to formInfo objs) for each record
					while (resultSet.next()) {
						currentRecord = new CovidForm();
					}

			    } catch (SQLException e) {
					e.getMessage();
			    }
		
	
	}
}
