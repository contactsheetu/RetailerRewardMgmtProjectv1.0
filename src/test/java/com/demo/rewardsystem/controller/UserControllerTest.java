package com.demo.rewardsystem.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.retailer.rewardmgmt.RewardPointApplication;
import com.retailer.rewardmgmt.exceptionhandler.Custom400Exception;

/**
 * 
 * @author Sheetu.Gupta
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = RewardPointApplication.class)
@AutoConfigureMockMvc
@DisplayName("Running UserControllerTest class")
public class UserControllerTest {

	/**
	 * mock mvc
	 */
	@Autowired
	private MockMvc mvc;
	
	@Value("${entity.notfound.message}")
	String errMessage;

	@Test
	@DisplayName("Get user of a valid given ID")
	void givenUserID_getValidUser() throws Exception {
		this.mvc.perform(get("/getUserDetails/userID/2").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andExpect(jsonPath("userID", is(2)))
				.andExpect(jsonPath("firstName", is("Leonard"))).andExpect(jsonPath("lastName", is("Hofstader")));
	}

	/**
	 * Find all customers
	 * 
	 * @throws Exception
	 */
	@Test
	@DisplayName("Get all users list")
	public void givenNoCriteria_getAllUsersList() throws Exception {
		this.mvc.perform(get("/getUserList").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$[6].userID", is(7)))
				.andExpect(jsonPath("$[6].firstName", is("Rajesh")))
				.andExpect(jsonPath("$[6].lastName", is("Koothrpali")));
	}

	/**
	 * Find user by given invalid id
	 * 
	 * @throws Exception
	 */
	@Test
	@DisplayName("Find user by invalid given id")
	public void givenUserID_whenInvalidUserID_thenThrowUserNotFoundException() throws Exception {
		int userID = 11;
		this.mvc.perform(get("/getUserDetails/userID/" + userID).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError())
				.andExpect(result -> assertThrows(Custom400Exception.class, () -> {
					throw result.getResolvedException();
				})).andExpect(result -> assertThat(result.getResolvedException().getMessage(),
						containsString(errMessage + " [User ID: " + userID + "]")));

	}
}
