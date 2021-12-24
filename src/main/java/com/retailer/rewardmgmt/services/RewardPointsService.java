/**
 * 
 */
package com.retailer.rewardmgmt.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.retailer.rewardmgmt.dto.RewardPointsDTO;
import com.retailer.rewardmgmt.model.RewardPointsModel;
import com.retailer.rewardmgmt.model.RewardRuleModel;
import com.retailer.rewardmgmt.model.TransactionModel;
import com.retailer.rewardmgmt.repository.RewardPointRepository;
import com.retailer.rewardmgmt.repository.RewardRuleRepository;
import com.retailer.rewardmgmt.rules.RewardPointsRules;
import com.retailer.rewardmgmt.validator.IsDateRangeValid;

/**
 * @author Sheetu.Gupta
 *
 */
@Service
@Validated
public class RewardPointsService {

	@Autowired
	TransactionService transactionService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RewardPointRepository rewardPointRepository;

	@Autowired
	RewardRuleRepository rewardRuleRepository;

	public List<RewardRuleModel> findAll() {
		return rewardRuleRepository.findAll();
	}

	public RewardRuleModel findByApplicableLimit(BigDecimal applicableLimit) {
		return rewardRuleRepository.findByApplicableLimit(applicableLimit);
	}

	public RewardRuleModel findByProgramIDAndApplicableLimit(Long programID, BigDecimal applicableLimit) {
		return rewardRuleRepository.findByProgramIDAndApplicableLimit(programID, applicableLimit);
	}

	public RewardPointsDTO getMonthlyPoints(long userID, int month, String year) {
		Month inputMonth = Month.of(month);
		LocalDate firstDayOfMonth = LocalDate.of(Integer.valueOf(year), inputMonth, 1);
		LocalDate lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth());
		return getRewardPointsForInterval(userID, firstDayOfMonth, lastDayOfMonth);
	}

	public RewardPointsDTO getQuarterlyPoints(long userID, long quarter, String year) {
		LocalDate firstDayOfQuarter = null;
		LocalDate lastDayOfQuarter = null;
		Month quarterStartMonth = Month.JANUARY;

		if (2 == quarter) {
			quarterStartMonth = Month.APRIL;
		}
		if (3 == quarter) {
			quarterStartMonth = Month.JULY;
		}
		if (4 == quarter) {
			quarterStartMonth = Month.OCTOBER;
		}
		try {
		
			firstDayOfQuarter = LocalDate.of(Integer.valueOf(year), quarterStartMonth, 1);
			lastDayOfQuarter = firstDayOfQuarter.plusMonths(2).with(TemporalAdjusters.lastDayOfMonth());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getRewardPointsForInterval(userID, firstDayOfQuarter, lastDayOfQuarter);
	}


	@IsDateRangeValid(startDate = "startDate", endDate = "endDate")
	public RewardPointsDTO getRewardPointsForInterval(long userID, LocalDate startDate, LocalDate endDate) {
		List<TransactionModel> userTranxtions = transactionService.getUserTransactionsBetween(userID, startDate, endDate);

		RewardPointsDTO rewardPoints = new RewardPointsDTO();
		rewardPoints.setEndDate(endDate);
		rewardPoints.setStartDate(startDate);
		rewardPoints.setUserID(userID);
		rewardPoints.setTransactions(userTranxtions);

		userTranxtions.stream().forEach(tm -> {
			if (!Optional.ofNullable(tm.getRewardPoint()).isPresent()) {
				RewardPointsModel rpm = new RewardPointsModel();
				rpm.setRewardPoints(RewardPointsRules.calculateAwardPoints(tm));
				rpm.setTransactionID(tm.getTransactionID());
				tm.setRewardPoint(rpm);
				transactionService.save(tm);
			}
			
			rewardPoints.addAmount(tm.getAmount());
			rewardPoints.addRewardPoints(tm.getRewardPoint().getRewardPoints());
		});

		return rewardPoints;
	}


}
