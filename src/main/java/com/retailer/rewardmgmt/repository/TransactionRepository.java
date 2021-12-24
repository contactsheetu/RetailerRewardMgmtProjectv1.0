package com.retailer.rewardmgmt.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.retailer.rewardmgmt.model.TransactionModel;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {

	 
	@Transactional(readOnly = true)
	List<TransactionModel> findByUserID(long userID);

	@Transactional(readOnly = true)
	List<TransactionModel> findAllByUserIDAndTransactionDateBetween(long userID, LocalDate startDate, LocalDate endDate, Sort sortBy);

}
