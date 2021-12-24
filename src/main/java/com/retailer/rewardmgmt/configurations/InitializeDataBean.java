package com.retailer.rewardmgmt.configurations;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.retailer.rewardmgmt.model.UserModel;
import com.retailer.rewardmgmt.repository.UserRepository;

@Component
public class InitializeDataBean {

	@Bean
	public CommandLineRunner demo(UserRepository iUserRepository) {
		return (args) -> {
			UserModel user1 = new UserModel();
			user1.setFirstName("Howard");
			user1.setLastName("Woliwotz");
			UserModel user2 = new UserModel();
			user2.setFirstName("Rajesh");
			user2.setLastName("Koothrpali");
			iUserRepository.save(user1);
			iUserRepository.save(user2);

		};
	}


}
