package com.palmar.covid19.Dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.palmar.covid19.Application;
import com.palmar.covid19.data.AdminForm;
import com.palmar.covid19.data.CovidForm;
import com.palmar.covid19.data.UserForm;


public class FormDaoImpl {	

//	@Autowired
//	private DataSource dataSource;
	
	Connection connection = null;
	
	public void insertIntoForm(UserForm formData) throws SQLException, URISyntaxException{
		System.out.println("Entering insertInto");
//		Application app = new Application();
		try {
			connection = Application.getConnection();
			// sql insert into statement with missing values
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO form(form_date,name,email,role,age_group,question_1,question_2,question_3,question_4,question_5,question_6,question_7,question_8,question_9,question_10) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
		        
				
			// add values to statement passed from method
			preparedStatement.setDate(1, formData.getDate());
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
	
	public ArrayList<UserForm> getRequestedForms(AdminForm adminRequest) throws SQLException, URISyntaxException {
		final String myQuery;
		System.out.println("Entering getRequestedForm");
		// initialize array list to be returned
		ArrayList<UserForm> selectedRecords = new ArrayList<UserForm>();
		UserForm currentRecord;
		
		String FlaggedQueryCheck = "question_1 = 't' OR question_2 = 't' OR question_3 = 't' OR question_4 = 't' OR"
				+ " question_5 = 't' OR question_6 = 't' OR question_7 = 't' OR question_8 = 't' OR question_9 = 't' OR question_10 = 't'";
		
		// there are 3 parameters to be checked, so 8 possible different queries the admin can request. Check which query the admin has requested
		if (adminRequest.getFlagStatus() == true && !adminRequest.getAge().equals("any") && !adminRequest.getName().isEmpty()) {
			myQuery = "SELECT * FROM form "
					+ "WHERE form_date='" + adminRequest.getDate() + "' AND age_group=" + adminRequest.getAge() + "AND name='" + adminRequest.getName() + "' AND (" + FlaggedQueryCheck + ");";
		} else if (adminRequest.getFlagStatus() == false && !adminRequest.getAge().equals("any") && !adminRequest.getName().isEmpty()) {
			myQuery = "SELECT * FROM form "
					+ "WHERE form_date='" + adminRequest.getDate() + "' AND age_group='" + adminRequest.getAge() + "' AND name='" + adminRequest.getName() + "';";
		} else if (adminRequest.getFlagStatus() == false && adminRequest.getAge().equals("any") && !adminRequest.getName().isEmpty()) {
			myQuery = "SELECT * FROM form "
					+ "WHERE form_date='" + adminRequest.getDate() + "' AND name='" + adminRequest.getName() + "';";
		} else if (adminRequest.getFlagStatus() == true && adminRequest.getAge().equals("any") && adminRequest.getName().isEmpty()) {
			myQuery = "SELECT * FROM form "
					+ "WHERE form_date='" + adminRequest.getDate() + "' AND (" + FlaggedQueryCheck + ");";
		} else if (adminRequest.getFlagStatus() == true && !adminRequest.getAge().equals("any") && adminRequest.getName().isEmpty()) {
			myQuery = "SELECT * FROM form "
					+ "WHERE form_date='" + adminRequest.getDate() + "' AND age_group='" + adminRequest.getAge() + "' AND (" + FlaggedQueryCheck + ");";
		} else if (adminRequest.getFlagStatus() == true && adminRequest.getAge().equals("any") && !adminRequest.getName().isEmpty()) {
			myQuery = "SELECT * FROM form "
					+ "WHERE form_date='" + adminRequest.getDate() + "' AND name='" + adminRequest.getName() + "' AND (" + FlaggedQueryCheck + ";";
		} else if (adminRequest.getFlagStatus() == false && !adminRequest.getAge().equals("any") && adminRequest.getName().isEmpty()) {
			myQuery = "SELECT * FROM form "
					+ "WHERE form_date='" + adminRequest.getDate() + "' AND age_group='" + adminRequest.getAge() + "';";
		} else {
			myQuery = "SELECT * FROM form "
					+ "WHERE form_date='" + adminRequest.getDate() + "';";
		}
		System.out.println(myQuery);

		try {
			connection = Application.getConnection();
			// prepare sql select query
			PreparedStatement preparedStatement = connection.prepareStatement(myQuery);
	        // get records from select
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// loop through records to create new covid form objects for each record
			while (resultSet.next()) {
				// get columns
				Date recordDate = resultSet.getDate(2);
				String strRecordDate = recordDate.toString();
				String recordName = resultSet.getString(3);
				String recordEmail = resultSet.getString(4);
				String recordRole = resultSet.getString(5);
				String recordAgeGroup = resultSet.getString(6);
				boolean recordQ1 = resultSet.getBoolean(7);
				boolean recordQ2 = resultSet.getBoolean(8);
				boolean recordQ3 = resultSet.getBoolean(9);
				boolean recordQ4 = resultSet.getBoolean(10);
				boolean recordQ5 = resultSet.getBoolean(11);
				boolean recordQ6 = resultSet.getBoolean(12);
				boolean recordQ7 = resultSet.getBoolean(13);
				boolean recordQ8 = resultSet.getBoolean(14);
				boolean recordQ9 = resultSet.getBoolean(15);
				boolean recordQ10 = resultSet.getBoolean(16);
				
				// create new covid form object for record
				currentRecord = new UserForm(recordName, strRecordDate, recordAgeGroup, recordEmail, recordRole, 
						recordQ1, recordQ2, recordQ3, recordQ4, recordQ5, recordQ6, recordQ7, recordQ8, recordQ9, recordQ10);
				
				// check if the forms are flagged forms, will be used for ease of output in angular
				if (recordQ1 == true || recordQ2 == true || recordQ3 == true || recordQ4 == true || recordQ5 == true || recordQ6 == true ||
						recordQ7 == true || recordQ8 == true || recordQ9 == true || recordQ10 == true) {
					currentRecord.setFlagStatus(true);
				} else {
					currentRecord.setFlagStatus(false);
				}
				
				// covid form object is now fully created. Append it to the empty arraylist which will be returned 
				selectedRecords.add(currentRecord);
			}

	    } catch (SQLException e) {
			e.getMessage();
	    }
	
		// all records have been captured, return them now
		return selectedRecords;
		
	
	}
}
