package com.retailer.rewardmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.retailer.rewardmgmt.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

	List<UserModel> findByUserID(String userID);

	
}
