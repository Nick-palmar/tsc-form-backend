package com.palmar.covid19.data;

public class AdminForm extends CovidForm{

	
	// admin view attributes
	private boolean flagged;
	
	public AdminForm(String name, String formDate, String ageGroup, boolean flagged) {
		super(name, formDate, ageGroup);
		this.flagged = flagged;
	}
	
	// provide setter for admin view flagged attribute
	public void setFlagStatus(boolean flagStatus) {
		this.flagged = flagStatus;
	}
	// provide getter for admin with flagged attribute
	public boolean getFlagStatus() {
		return this.flagged;
	}

}
