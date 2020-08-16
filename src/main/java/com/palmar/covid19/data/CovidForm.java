package com.palmar.covid19.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CovidForm {
	
	// define attributes that may be used in admin view or in user form
	// variables applicable to both
	private String name;
	private java.sql.Date formDate;
	private String ageGroup;


	
	// constructor; initizalize attributes that overlap
	public CovidForm (String name, String formDate, String ageGroup) {
		this.name = name;
		this.ageGroup = ageGroup;
		
		Pattern goodPattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
		
		// check if the pattern is already good
		if (goodPattern.matcher(formDate).matches()) { 
			this.formDate = java.sql.Date.valueOf(formDate);
		} else {
		
			// change date from string to date
			String pattern = "yyyy/MM/dd";
			SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
			Date formatDateUtil = null;
			try {
				formatDateUtil = dateFormatter.parse(formDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			this.formDate = new java.sql.Date(formatDateUtil.getTime());
			System.out.println(this.formDate);
		}
	}
	
	// provide setters and getters for private attributes in constructor
	public void setName(String newName) {
		this.name = newName;
	}
	public String getName() {
		return this.name;
	}
	public void setDate(java.sql.Date newDate) {
		this.formDate = newDate;
	}
	public java.sql.Date getDate() {
		return this.formDate;
	}
	public void setAge(String newAge) {
		this.ageGroup = newAge;
	}
	public String getAge() {
		return this.ageGroup;
	}
	
}
