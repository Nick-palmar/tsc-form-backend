package com.palmar.covid19.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.palmar.covid19.data.CovidForm;

@Controller
public class AdminView {
	
	private CovidForm adminRequest;
	
	
	@GetMapping("/admin")
	@ResponseBody
	public String admin(@RequestParam(defaultValue="false") boolean isFlagged,
			@RequestParam (defaultValue="any") String age, 
			@RequestParam(required=true) String formDate, 
			@RequestParam(required=true) String searchName) {
		
		// create new adminRequest object to pass to dao
		adminRequest = new CovidForm(searchName, formDate, age);
		adminRequest.setFlagStatus(isFlagged);
		
		return adminRequest.getName() + adminRequest.getAge() + adminRequest.getDate() + adminRequest.getFlagStatus();
	}
	
	
}
