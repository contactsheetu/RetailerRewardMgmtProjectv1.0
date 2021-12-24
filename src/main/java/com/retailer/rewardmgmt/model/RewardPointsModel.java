package com.retailer.rewardmgmt.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERREWARDPOINTS")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RewardPointsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	@Getter
	@Setter
	long id;

	@Getter
	@Setter
	BigDecimal rewardPoints;

	@Getter
	@Setter
	long transactionID;

}
