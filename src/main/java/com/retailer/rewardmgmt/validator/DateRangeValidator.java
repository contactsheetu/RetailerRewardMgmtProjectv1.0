package com.retailer.rewardmgmt.validator;

import java.lang.reflect.Field;
import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.retailer.rewardmgmt.dto.RewardPointsDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class DateRangeValidator implements ConstraintValidator<IsDateRangeValid, RewardPointsDTO> {

	String endDateFieldName;
	
	String startDateFieldName;

	@Override
	public void initialize(IsDateRangeValid constraintAnnotation) {
		startDateFieldName = constraintAnnotation.startDate();
		endDateFieldName = constraintAnnotation.endDate();
	}

	@Override
	public boolean isValid(RewardPointsDTO value, ConstraintValidatorContext constraintValidatorContext) {
		
		try {
        final Field beforeDateField = value.getClass().getDeclaredField(startDateFieldName);
        beforeDateField.setAccessible(true);

        final Field afterDateField = value.getClass().getDeclaredField(endDateFieldName);
        afterDateField.setAccessible(true);

        final LocalDate startDate = (LocalDate) beforeDateField.get(value);
        final LocalDate endDate = (LocalDate) afterDateField.get(value);

		return startDate.isBefore(endDate);
        
		}catch (Exception e) {
			log.error("End date is lesser than start date" + e.getMessage());
		}
		return false;
	}

}
