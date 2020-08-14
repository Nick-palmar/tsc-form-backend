package com.palmar.covid19.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.palmar.covid19.data.CovidForm;
import com.palmar.covid19.data.UserForm;
import com.palmar.covid19.Dao.FormDaoImpl;

@Controller
public class UserView {
	
	private FormDaoImpl formDaoImpl = new FormDaoImpl();
	
	// use @RequestBody to automatically deserialize the JSON and convert it into the userINfo object
	@RequestMapping("/form")
	public String form(@RequestBody UserForm userInfo) { 
	    
		
		// insert object into forms table
		try {
			System.out.println(userInfo.getName());
			formDaoImpl.insertIntoForm(userInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "{\"formDate\": " + "\"" + userInfo.getDate() + "\"" + ", \"name\": " + "\"" + userInfo.getName() + "\"" + ", \"email\": " + "\"" + userInfo.getEmail() + "\"" + ", \"role\": " + "\"" + userInfo.getRole() + "\"" + ", \"age\": " + "\"" + userInfo.getAge() + "\"" + "}";
		
	}

}
