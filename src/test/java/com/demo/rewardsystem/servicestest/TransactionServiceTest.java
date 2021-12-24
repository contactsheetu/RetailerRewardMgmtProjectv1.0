package com.demo.rewardsystem.servicestest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.retailer.rewardmgmt.RewardPointApplication;
import com.retailer.rewardmgmt.model.RewardPointsModel;
import com.retailer.rewardmgmt.model.TransactionModel;
import com.retailer.rewardmgmt.rules.RewardPointsRules;
import com.retailer.rewardmgmt.services.TransactionService;

/**
 * 
 * @author Sheetu.Gupta
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = RewardPointApplication.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = { MockMvcAutoConfiguration.class })
@DisplayName("Testing TransactionService class")
public class TransactionServiceTest {

	@Autowired
	TransactionService transactionService;

	@Mock
	private TransactionService transactionServiceMock = Mockito.mock(TransactionService.class);

	/**
	 * 
	 */
	@Test
	@DisplayName("Should Add Transaction and Validate the calculated Reward Points")
	public void addTransaction_and_validateRewardPoints() {

		TransactionModel tm = new TransactionModel(new BigDecimal(444.53), "Mock Test Transaction", 1);
		TransactionModel actualResponse = transactionService.addTransaction(tm);

		TransactionModel mockTx = new TransactionModel(new BigDecimal(444.53), "Mock Test Transaction", 1);
		mockTx.setTransactionDate(LocalDate.now());
		mockTx.setUser(actualResponse.getUser());
		mockTx.setUserID(actualResponse.getUserID());
		RewardPointsModel rpm = new RewardPointsModel();
		rpm.setRewardPoints(RewardPointsRules.calculateAwardPoints(tm));
		mockTx.setRewardPoint(rpm);

		Mockito.when(transactionServiceMock.addTransaction(mockTx)).thenReturn(mockTx);

		assertThat(actualResponse.getRewardPoint().getRewardPoints())
				.isEqualTo(mockTx.getRewardPoint().getRewardPoints());
		
		assertThat(actualResponse.getRewardPoint().getRewardPoints())
		.isEqualTo(new BigDecimal(739.06).setScale(2, RoundingMode.HALF_EVEN));
		
		assertNotNull(actualResponse.getTransactionID(),
				"Snap!! Transaction ID is null, it is not saved successfully.");

	}

	/**
	 * Get all the transaction of the given userID for a period of Oct, 2021
	 * Validate that all transactions have userID as 1 Validate that startdate and
	 * enddate for all transaction lies between 10/1 to 10/31
	 */
	@Test
	public void getTransactions_for_given_period() {
		LocalDate startDate = LocalDate.of(2021, 10, 1);
		LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());

		List<TransactionModel> allTransactions = transactionService.getUserTransactionsBetween(1, startDate, endDate);

		assertTrue(allTransactions.stream().map(TransactionModel::getUserID).collect(Collectors.toSet()).contains(1l));

		assertThat(allTransactions).hasSize(11).allMatch(element -> (element.getUserID() == 1));
		assertThat(allTransactions).allSatisfy(element -> {
			assertThat(element.getTransactionDate()).isAfterOrEqualTo(startDate);
			assertThat(element.getTransactionDate()).isBeforeOrEqualTo(endDate);
		});
	}

}
