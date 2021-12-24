/**
 * 
 */
package com.retailer.rewardmgmt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.retailer.rewardmgmt.exceptionhandler.Custom400Exception;
import com.retailer.rewardmgmt.model.UserModel;
import com.retailer.rewardmgmt.repository.UserRepository;

/**
 * @author Sheetu.Gupta
 *
 */
@Service
@Validated
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Value("${entity.notfound.message}")
	String errMessage;

	public Optional<UserModel> getUser(long userID) {
		return Optional.of(userRepository.findById(userID)
				.orElseThrow(() -> new Custom400Exception(errMessage + " [User ID: " + userID + "]")));
	}

	public List<UserModel> getAllUsers() {
		return userRepository.findAll();
	}

}
