/**
 * 
 */
package com.retailer.rewardmgmt.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.retailer.rewardmgmt.exceptionhandler.Custom400Exception;
import com.retailer.rewardmgmt.model.RewardPointsModel;
import com.retailer.rewardmgmt.model.TransactionModel;
import com.retailer.rewardmgmt.repository.RewardPointRepository;
import com.retailer.rewardmgmt.repository.TransactionRepository;
import com.retailer.rewardmgmt.rules.RewardPointsRules;

/**
 * @author Sheetu.Gupta
 *
 */
@Service
@Validated
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	RewardPointRepository rewardPointRepository;

	@Autowired
	UserService userService;

	public TransactionModel save(TransactionModel transaction) {
		return transactionRepository.save(transaction);
	}

	public TransactionModel addTransaction(TransactionModel transaction) {

		transaction.setUser(userService.getUser(transaction.getUserID()).get());
		RewardPointsModel rpm = new RewardPointsModel();
		rpm.setRewardPoints(RewardPointsRules.calculateAwardPoints(transaction));

		if (transaction.getTransactionDate() == null) {
			transaction.setTransactionDate(LocalDate.now());
		}
		transaction.setRewardPoint(rpm);

		TransactionModel persistTx = save(transaction);
		rpm.setTransactionID(persistTx.getTransactionID());
		rewardPointRepository.save(rpm);

		return persistTx;

	}

	public List<TransactionModel> getUserTransactionsBetween(long userID, LocalDate startDate, LocalDate endDate) {

		// return allTransactions.stream().filter(t ->
		// (t.getUser().getID().equals(userID))).filter(t ->
		// (t.getTransactionDate().isAfter(startDate) &&
		// t.getTransactionDate().isBefore(endDate))).collect(Collectors.toList());
		return transactionRepository.findAllByUserIDAndTransactionDateBetween(userID, startDate, endDate,
				Sort.by(Sort.Direction.DESC, "TransactionDate"));
	}

	public List<TransactionModel> getAllTransactions() {
		Optional<List<TransactionModel>> allTranxs = Optional.of(transactionRepository.findAll());
		if (!allTranxs.isPresent()) {
			throw new Custom400Exception(null);
		}
		return allTranxs.get();
	}

	public List<TransactionModel> getUserTransactions(long userID) {
		userService.getUser(userID);
		return transactionRepository.findByUserID(userID);
	}

}
