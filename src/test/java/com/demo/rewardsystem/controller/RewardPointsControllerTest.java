package com.demo.rewardsystem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.retailer.rewardmgmt.RewardPointApplication;
import com.retailer.rewardmgmt.controller.RewardPointsController;
import com.retailer.rewardmgmt.dto.RewardPointsDTO;
import com.retailer.rewardmgmt.services.RewardPointsService;

/**
 * 
 * @author Sheetu.Gupta
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = RewardPointApplication.class)
@AutoConfigureMockMvc
@DisplayName("Running RewardPointsControllerTest class")
public class RewardPointsControllerTest {
	
	/**
	 * mock mvc
	 */
	@Autowired
	private MockMvc mvc;

	@InjectMocks
	RewardPointsController rewardPointControllerMock;

	@Mock
	RewardPointsService rewardPointsService;

	private static long userID = 5;
	private static long quarter = 4;
	private static int month = 3;
	private static String startDate = "2021-07-01";
	private static String endDate = "2021-09-30";

	@Test
	@DisplayName("Get all transactions for a given quarter and user id")
	public void givenUseID_getQuarterlyPoints() throws Exception {
		ResponseEntity<RewardPointsDTO> responseEntity = rewardPointControllerMock.getQuarterlyPoints(userID, quarter,
				Optional.ofNullable(null));
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	@DisplayName("Get all transactions for a given month and user id")
	public void givenUseID_getMonthlyPoints() throws Exception {
		ResponseEntity<RewardPointsDTO> responseEntity = rewardPointControllerMock.getMonthlyPoints(userID, month,
				Optional.ofNullable(null));
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	@DisplayName("Get all transactions for a given id for 3 months")
	public void givenUseID_getThreeMonthsRewardPoints() throws Exception {
		ResponseEntity<RewardPointsDTO> responseEntity = rewardPointControllerMock.getThreeMonthsPoints(userID);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	@DisplayName("Get all transactions for a given id for given duration")
	public void givenUseID_getGivenDurationRewardPoints() throws Exception {
		ResponseEntity<RewardPointsDTO> responseEntity = rewardPointControllerMock.getPointsForInterval(userID,
				startDate, endDate);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	@DisplayName("Error when StartDate Less Than EndDate")
	public void givenUseID_whenStartDateLessThanEndDate() throws Exception {
		this.mvc.perform(get("/getPointsForInterval/userID/" + userID + "/startDate/" + endDate + "/endDate/" + startDate).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError())
		.andExpect(result -> assertThrows(ConstraintViolationException.class, () -> {
			throw result.getResolvedException();
		})).andExpect(result -> assertEquals(result.getResolvedException().getMessage(),
				"getRewardPointsForInterval.<return value>: Invalid Input!! Startdate is less than Enddate"));
	}

}
