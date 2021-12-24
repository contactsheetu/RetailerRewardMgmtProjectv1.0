package com.demo.rewardsystem.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.retailer.rewardmgmt.RewardPointApplication;
import com.retailer.rewardmgmt.exceptionhandler.Custom400Exception;

/**
 * 
 * @author Sheetu.Gupta
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = RewardPointApplication.class)
@AutoConfigureMockMvc
@DisplayName("Running TransactionControllerTest class")
public class TransactionControllerTest {

	/**
	 * mock mvc
	 */
	@Autowired
	private MockMvc mvc;
	
	@Value("${entity.notfound.message}")
	String errMessage;

	   
		@Test
		@DisplayName("Get all transactions")
		public void getAllTransactions() throws Exception {
			this.mvc.perform(get("/getAllTransactions").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().is2xxSuccessful())
					.andDo(MockMvcResultHandlers.print())
					.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(514));
		}
	
	
	@Test
	@DisplayName("Get all transactions for a given id")
	public void givenUseID_getAllTransactions() throws Exception {
		int userID = 5;
		this.mvc.perform(get("/getUserTransactions/userID/" + userID).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(100));
	}

	
	@Test
	@DisplayName("Add a new transaction")
	public void addTransaction() throws Exception {
		String tmJson = "{\"amount\":67.30,\"userID\":1,\"description\":\"Mock Test Transaction\"}";
		this.mvc.perform(
				(MockHttpServletRequestBuilder) MockMvcRequestBuilders.post("/addTransaction")
				.content(tmJson)
				.contentType(MediaType.APPLICATION_JSON)
	      		.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.transactionID").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.rewardPoint.rewardPoints", is(17.30)));
	}
	
	/**
	 * Find transaction by invalid given userid
	 * 
	 * @throws Exception
	 */
	@Test
	@DisplayName("Find transaction by invalid given userid")
	public void givenUserID_whenInvalidUserID_thenThrowUserNotFoundException() throws Exception {
		int userID = 11;
		this.mvc.perform(get("/getUserTransactions/userID/" + userID).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError())
				.andExpect(result -> assertThrows(Custom400Exception.class, () -> {
					throw result.getResolvedException();
				})).andExpect(result -> assertThat(result.getResolvedException().getMessage(),
						containsString(errMessage + " [User ID: " + userID + "]")));

	}

}
