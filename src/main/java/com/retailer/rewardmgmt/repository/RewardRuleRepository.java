package com.retailer.rewardmgmt.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.retailer.rewardmgmt.model.RewardRuleModel;

@Repository
public interface RewardRuleRepository extends JpaRepository<RewardRuleModel, Long> {

	
	@Transactional(readOnly = true)
	List<RewardRuleModel> findAll();

	
	
	@Transactional(readOnly = true)
	RewardRuleModel findByApplicableLimit(BigDecimal applicableLimit);
	
	
	
	@Transactional(readOnly = true)
	RewardRuleModel findByProgramIDAndApplicableLimit(Long programID, BigDecimal applicableLimit);
	
}
