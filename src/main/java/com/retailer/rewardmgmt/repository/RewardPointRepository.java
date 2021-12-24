package com.retailer.rewardmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.retailer.rewardmgmt.model.RewardPointsModel;

@Repository
public interface RewardPointRepository extends JpaRepository<RewardPointsModel, Long> {

	

	
}
