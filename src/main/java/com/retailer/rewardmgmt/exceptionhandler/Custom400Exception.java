package com.retailer.rewardmgmt.exceptionhandler;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
public class Custom400Exception extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
	@Value("${entity.notfound.message}")
	private String message;

	


	
}
