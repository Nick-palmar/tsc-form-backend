package com.palmar.covid19.data;

public class UserForm extends CovidForm{
	
	
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
	
	// admin view attributes, for return form
	private boolean flagged;
	
	// provide setter for admin view flagged attribute, for return form
	public void setFlagStatus(boolean flagStatus) {
		this.flagged = flagStatus;
	}
	// provide getter for admin with flagged attribute
	public boolean getFlagStatus() {
		return this.flagged;
	}
		
	public UserForm(String name, String formDate, String ageGroup, String email, String role, 
			boolean question1, boolean question2, boolean question3, boolean question4, boolean question5, 
			boolean question6, boolean question7, boolean question8, boolean question9, boolean question10) {
		super(name, formDate, ageGroup);
		this.email = email;
		this.role = role;
		this.question1 = question1;
		this.question2 = question2;
		this.question3 = question3;
		this.question4 = question4;
		this.question5 = question5;
		this.question6 = question6;
		this.question7 = question7;
		this.question8 = question8;
		this.question9 = question9;
		this.question10 = question10;
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
