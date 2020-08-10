package com.palmar.covid19.Dao;

import java.sql.SQLException;

import com.palmar.covid19.data.CovidForm;

public interface FormDao {
	public void insertIntoForm(CovidForm formData) throws SQLException;
}
