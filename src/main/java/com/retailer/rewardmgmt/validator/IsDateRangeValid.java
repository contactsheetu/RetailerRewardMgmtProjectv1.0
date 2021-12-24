package com.retailer.rewardmgmt.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.springframework.beans.factory.annotation.Value;

@Target({  ElementType.ANNOTATION_TYPE, ElementType.PARAMETER, ElementType.TYPE , ElementType.FIELD, ElementType.METHOD, ElementType.TYPE_PARAMETER, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateRangeValidator.class)
@Documented
public @interface IsDateRangeValid {

	@Value("{startdate.lessthan.enddate.erromessage}")
	String message() default "Invalid Input!! Startdate is less than Enddate";

	String endDate();
	
	String startDate();
	
	  Class<String>[] groups() default {};

	  Class<? extends Payload>[] payload() default {};
	

}
