package com.retailer.rewardmgmt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.retailer.rewardmgmt.services.UserService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@Validated
@Component
public class UserController {

	@Value("${welcome.message:default}")
	private String welcomeMessage = "";

	/*
	 * 
	 */
	@RequestMapping("/")
	public String goToHome(Model messageModel) {
		messageModel.addAttribute("welcomeMessage", this.welcomeMessage);
		return "index";
	}

	@Autowired
	UserService userService;
	
	/*
	 * 
	 */
	@GetMapping("getUserList")
	public ResponseEntity<List<?>> getUserList() {
			log.info("[ getUserLists ]");
			return ResponseEntity.ok(userService.getAllUsers());
	}

	/*
	 * 
	 */
	@GetMapping(value = "getUserDetails/userID/{userID}")
	public ResponseEntity<Optional<?>> getUser(@PathVariable(value = "userID", required = true) long userID) {
		log.info("[ getUserLists ]");
		return ResponseEntity.ok(userService.getUser(userID));
	}

}
