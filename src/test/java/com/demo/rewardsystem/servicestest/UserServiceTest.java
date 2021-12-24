package com.demo.rewardsystem.servicestest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.retailer.rewardmgmt.RewardPointApplication;
import com.retailer.rewardmgmt.exceptionhandler.Custom400Exception;
import com.retailer.rewardmgmt.services.UserService;

/**
 * 
 * @author Sheetu.Gupta
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = RewardPointApplication.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = { MockMvcAutoConfiguration.class })
@DisplayName("Testing UserService class")
public class UserServiceTest {

	@Autowired
	UserService userService;

	@Value("${entity.notfound.message}")
	String errMessage;
	
	/**
	 * 
	 */
	@Test
	@DisplayName("Should Throw Exception when User with given ID does not exist")
	public void shouldFailWhenUserDoesNotExist() {
		int userID = 65;
		assertThatThrownBy(() -> {
			userService.getUser(userID);
		}).isInstanceOf(Custom400Exception.class).hasMessage(errMessage + " [User ID: " + userID + "]");
	}

}
