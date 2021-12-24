package com.demo.rewardsystem.servicestest;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.retailer.rewardmgmt.RewardPointApplication;
import com.retailer.rewardmgmt.model.TransactionModel;
import com.retailer.rewardmgmt.rules.RewardPointsRules;
import com.retailer.rewardmgmt.services.UserService;

/**
 * 
 * @author Sheetu.Gupta
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = RewardPointApplication.class)
@AutoConfigureMockMvc
@DisplayName("Testing RewardPointsRulesTest class")
public class RewardPointsRulesTest {
	

	@Autowired
	UserService userService;

	/**
	 * 
	 */
	@Test
	@DisplayName("To validate below 50 Transaction reward points")
	public void validate_below50_Transaction() {

		TransactionModel tm = new TransactionModel(new BigDecimal(44.53), "Mock Test Transaction", 1);
		tm.setUser(userService.getUser(1).get());
		assertThat(RewardPointsRules.calculateAwardPoints(tm)).isEqualTo(new BigDecimal(0.00).setScale(2));
	}

	/**
	 * 
	 */
	@Test
	@DisplayName("To validate Above 50 Transaction reward points")
	public void validate_Above50_Transaction() {

		TransactionModel tm = new TransactionModel(new BigDecimal(74.1250273), "Mock Test Transaction", 1);
		tm.setUser(userService.getUser(1).get());
		assertThat(RewardPointsRules.calculateAwardPoints(tm)).isEqualTo(new BigDecimal(24.13).setScale(2, RoundingMode.HALF_EVEN));
	}

	/**
	 * 
	 */
	@Test
	@DisplayName("To validate Above 100 Transaction reward points")
	public void validate_Above100_Transaction() {

		TransactionModel tm = new TransactionModel(new BigDecimal(365.22766554), "Mock Test Transaction", 1);
		tm.setUser(userService.getUser(1).get());
		assertThat(RewardPointsRules.calculateAwardPoints(tm)).isEqualTo(new BigDecimal(580.46).setScale(2, RoundingMode.HALF_EVEN));
	}

}
