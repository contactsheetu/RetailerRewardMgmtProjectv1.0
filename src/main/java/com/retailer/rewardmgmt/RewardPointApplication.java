package com.retailer.rewardmgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.retailer")
public class RewardPointApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardPointApplication.class);

	}

}
