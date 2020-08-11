package com.palmar.covid19.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.palmar.covid19.data.CovidForm;

public interface FormDao {
	public void insertIntoForm(CovidForm formData) throws SQLException;
	
	public ArrayList<CovidForm> getRequestedForms(CovidForm adminRequest) throws SQLException;
}
