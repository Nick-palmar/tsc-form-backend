package com.palmar.covid19.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.palmar.covid19.data.AdminForm;
import com.palmar.covid19.data.UserForm;

public interface FormDao {
	public void insertIntoForm(UserForm formData) throws SQLException;
	
	public ArrayList<UserForm> getRequestedForms(AdminForm adminRequest) throws SQLException;
}
