package com.palmar.covid19.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CovidForm {
	
	// define attributes that may be used in admin view or in user form
	// variables applicable to both
	private String name;
	private Date formDate;
	private String ageGroup;
	
	// admin view attributes
	private boolean flagged;
	
	// user form attributes
	private String email;
	private String role;
	private boolean question1;
	private boolean question2;
	private boolean question3;
	private boolean question4;
	private boolean question5;
	private boolean question6;
	private boolean question7;
	private boolean question8;
	private boolean question9;
	private boolean question10;
	
	// constructor; initizalize attributes that overlap
	public CovidForm (String name, String formDate, String ageGroup) {
		this.name = name;
		this.ageGroup = ageGroup;
		
		// change date from string to date
		String pattern = "yyyy/MM/dd";
		SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
		Date formatDateUtil = null;
		try {
			formatDateUtil = dateFormatter.parse(formDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.formDate = formatDateUtil;
	}
	
	// provide setters and getters for private attributes in constructor
	public void setName(String newName) {
		this.name = newName;
	}
	public String getName() {
		return this.name;
	}
	public void setDate(Date newDate) {
		this.formDate = newDate;
	}
	public Date getDate() {
		return this.formDate;
	}
	public void setAge(String newAge) {
		this.ageGroup = newAge;
	}
	public String getAge() {
		return this.ageGroup;
	}
	
	// provide setter for admin view flagged attribute
	public void setFlagStatus(boolean flagStatus) {
		this.flagged = flagStatus;
	}
	// provide getter for admin with flagged attribute
	public boolean getFlagStatus() {
		return this.flagged;
	}
	
	// provide setters for other user form attributes
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}
	public void setRole(String newRole) {
		this.role = newRole;
	}
	public void setQ1(boolean newAns) {
		this.question1 = newAns;
	}
	public void setQ2(boolean newAns) {
		this.question2 = newAns;
	}
	public void setQ3(boolean newAns) {
		this.question3 = newAns;
	}
	public void setQ4(boolean newAns) {
		this.question4 = newAns;
	}
	public void setQ5(boolean newAns) {
		this.question5 = newAns;
	}
	public void setQ6(boolean newAns) {
		this.question6 = newAns;
	}
	public void setQ7(boolean newAns) {
		this.question7 = newAns;
	}
	public void setQ8(boolean newAns) {
		this.question8 = newAns;
	}
	public void setQ9(boolean newAns) {
		this.question9 = newAns;
	}
	public void setQ10(boolean newAns) {
		this.question10 = newAns;
	}
	
	// provide getters for other user form attributes
	public String getEmail() {
		return this.email;
	}
	public String getRole() {
		return this.role;
	}
	public boolean getQ1() {
		return this.question1;
	}
	public boolean getQ2() {
		return this.question2;
	}
	public boolean getQ3() {
		return this.question3;
	}
	public boolean getQ4() {
		return this.question4;
	}
	public boolean getQ5() {
		return this.question5;
	}
	public boolean getQ6() {
		return this.question6;
	}
	public boolean getQ7() {
		return this.question7;
	}
	public boolean getQ8() {
		return this.question8;
	}
	public boolean getQ9() {
		return this.question9;
	}
	public boolean getQ10() {
		return this.question10;
	}
}
