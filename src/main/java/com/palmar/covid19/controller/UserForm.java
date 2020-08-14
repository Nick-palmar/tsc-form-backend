package com.palmar.covid19.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.palmar.covid19.data.CovidForm;
import com.palmar.covid19.Dao.FormDaoImpl;

@Controller
public class UserForm {
	
	private FormDaoImpl formDaoImpl;
	
	private CovidForm userInfo;
	
	@RequestMapping("/form")
	public String form(@RequestParam(required=true) String formDate, 
						@RequestParam(required=true) String name, 
						@RequestParam(required=true) String email,
						@RequestParam(required=true) String role, 
						@RequestParam (required=true) String age, 
						@RequestParam(defaultValue="false") boolean question1,
						@RequestParam(defaultValue="false") boolean question2,
						@RequestParam(defaultValue="false") boolean question3,
						@RequestParam(defaultValue="false") boolean question4,
						@RequestParam(defaultValue="false") boolean question5,
						@RequestParam(defaultValue="false") boolean question6,
						@RequestParam(defaultValue="false") boolean question7,
						@RequestParam(defaultValue="false") boolean question8,
						@RequestParam(defaultValue="false") boolean question9,
						@RequestParam(defaultValue="false") boolean question10) { 
	    
		// create new userInfo object with variables from @RequestParam
		userInfo = new CovidForm(name, formDate, age);
		userInfo.setEmail(email);
		userInfo.setRole(role);
		userInfo.setQ1(question1);
		userInfo.setQ2(question2);
		userInfo.setQ3(question3);
		userInfo.setQ4(question4);
		userInfo.setQ5(question5);
		userInfo.setQ6(question6);
		userInfo.setQ7(question7);
		userInfo.setQ8(question8);
		userInfo.setQ9(question9);
		userInfo.setQ10(question10);
		
		// insert object into forms table
		try {
			formDaoImpl.insertIntoForm(userInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "{\"formDate\": " + "\"" + userInfo.getDate() + "\"" + ", \"name\": " + "\"" + userInfo.getName() + "\"" + ", \"email\": " + "\"" + userInfo.getEmail() + "\"" + ", \"role\": " + "\"" + userInfo.getRole() + "\"" + ", \"age\": " + "\"" + userInfo.getAge() + "\"" + "}";
		
	}

}
