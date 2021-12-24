package com.retailer.rewardmgmt.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.validation.annotation.Validated;

import com.retailer.rewardmgmt.model.TransactionModel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Validated
public class RewardPointsDTO {
	
	public RewardPointsDTO(){
		super();
		amount = new BigDecimal(0.0);
		rewardPoints = new BigDecimal(0.0);
	}
	

	@DateTimeFormat(iso = ISO.DATE)
	@Getter
	@Setter
	LocalDate startDate;

	@Getter
	@Setter
	@DateTimeFormat(iso = ISO.DATE)
	LocalDate endDate;

	@Setter
	@Getter
	BigDecimal amount;

	@Getter
	@Setter
	BigDecimal rewardPoints;

	@Getter
	@Setter
	long userID;

	@Getter
	@Setter
	List<TransactionModel> transactions;

	public void addAmount(BigDecimal amount) {
		this.amount = this.amount.add(amount).setScale(2, BigDecimal.ROUND_HALF_EVEN);;
	}

	
	public void addRewardPoints(BigDecimal rewardPoints) {
		this.rewardPoints = this.rewardPoints.add(rewardPoints).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

}
