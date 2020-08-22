package com.palmar.covid19.controller;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.palmar.covid19.Dao.FormDaoImpl;
import com.palmar.covid19.data.AdminForm;
import com.palmar.covid19.data.UserForm;

@Controller
//@SpringBootApplication
public class AdminView {
	
	private AdminForm adminRequest;
	private static FormDaoImpl formDaoImpl = new FormDaoImpl();
	
	private ArrayList<UserForm> selectedForms;
	
//	public static void main(String[] args) throws Exception {
//	    SpringApplication.run(AdminView.class, args);
//	  }
	
	@GetMapping("/admin/form")
	@ResponseBody
	public ArrayList<UserForm> admin(@RequestParam(defaultValue="false") boolean isFlagged,
			@RequestParam (defaultValue="any") String age, 
			@RequestParam(required=true) String formDate, 
			@RequestParam(required=true) String searchName) throws URISyntaxException {
		
		// create new adminRequest object to pass to dao
		adminRequest = new AdminForm(searchName, formDate, age, isFlagged);
		
		// call method in dao to search for the admin's request
		try {
			selectedForms = formDaoImpl.getRequestedForms(adminRequest);
		} catch (SQLException e) {
			e.printStackTrace();
			// if there is an error, create an empty array list
			selectedForms = new ArrayList<UserForm>();
		}
		
		// return the list of forms to the http get request in angular
		return selectedForms;
		
//		String testReturn = "";
//		
//		for (UserForm form: selectedForms) {
//			testReturn += "\n name: " + form.getName() + " email: " + form.getEmail() + " date: " + form.getDate() + " role: " + form.getRole() + " team: " + form.getAge() + " flagged: " + form.getFlagStatus();
//		}
//		if (testReturn.equals("")) {
//			return "No records";
//		} else {
//			return testReturn;
//		}
	}
	
	
}
