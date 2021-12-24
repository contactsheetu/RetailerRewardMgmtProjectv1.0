package com.retailer.rewardmgmt.controller;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.retailer.rewardmgmt.dto.RewardPointsDTO;
import com.retailer.rewardmgmt.services.RewardPointsService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@Validated
public class RewardPointsController {

	@Autowired
	RewardPointsService rewardPointsService;

	@RequestMapping(value = { "/getQuarterlyPoints/userID/{userID}/quarter/{quarter}/year/{year}",
			"/getQuarterlyPoints/userID/{userID}/quarter/{quarter}/",
			"/getQuarterlyPoints/userID/{userID}/quarter/{quarter}" }, method = RequestMethod.GET)
	public ResponseEntity<RewardPointsDTO> getQuarterlyPoints(
			@PathVariable(value = "userID", required = true) long userID,
			@Positive @Range(min = 1, max = 4) @PathVariable(value = "quarter", required = true) long quarter,
			@Positive @Min(value = 2020) @PathVariable Optional<String> year) {
		log.info("[ Quarterly Award Points ]");
		if (!year.isPresent())
			year = Optional.of(Year.now().toString());
		return ResponseEntity.ok(rewardPointsService.getQuarterlyPoints(userID, quarter, year.get()));

	}

	@RequestMapping(value = { "/getMonthlyPoints/userID/{userID}/month/{month}/year/{year}",
			"/getMonthlyPoints/userID/{userID}/month/{month}/",
			"/getMonthlyPoints/userID/{userID}/month/{month}" }, method = RequestMethod.GET)
	public ResponseEntity<RewardPointsDTO> getMonthlyPoints(
			@PathVariable(value = "userID", required = true) long userID,
			@Positive @Range(min = 1, max = 12) @PathVariable(value = "month", required = true) int month,
			@Positive @Min(value = 2020) @PathVariable Optional<String> year) {

		log.info("[ Monthly Award Points ]");
		if (!year.isPresent())
			year = Optional.of(Year.now().toString());
		return ResponseEntity.ok(rewardPointsService.getMonthlyPoints(userID, month, year.get()));

	}

	// Required date format - 2007-12-03
	@RequestMapping(value = "/getPointsForInterval/userID/{userID}/startDate/{startDate}/endDate/{endDate}", method = RequestMethod.GET)
	public ResponseEntity<RewardPointsDTO> getPointsForInterval(
			@PathVariable(value = "userID", required = true) long userID,
			@PathVariable(value = "startDate", required = true) @DateTimeFormat(iso = ISO.DATE) String startDate,
			@PathVariable(value = "endDate", required = true) @DateTimeFormat(iso = ISO.DATE) String endDate) {
		log.info("[ getPointsForInterval Award Points ]");
		return ResponseEntity.ok(rewardPointsService.getRewardPointsForInterval(userID,
				LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE),
				LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE)));
	}

	@RequestMapping(value = "/getThreeMonthsPoints/userID/{userID}", method = RequestMethod.GET)
	public ResponseEntity<RewardPointsDTO> getThreeMonthsPoints(
			@PathVariable(value = "userID", required = true) long userID) {
		log.info("[ getPointsForThreeMonths Award Points ]");
		return ResponseEntity.ok(rewardPointsService.getRewardPointsForInterval(userID,
				LocalDate.now().minusMonths(3).plusDays(1), LocalDate.now()));
	}

}
