package com.retailer.rewardmgmt.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Table(name = "Reward_Program_Rules")
@Entity
@Getter
@Setter
public class RewardRuleModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reward_point_rule_seq")
	@SequenceGenerator(name = "reward_point_rule_seq", sequenceName = "reward_point_rule_seq", initialValue = 3, allocationSize = 1)
	@Column(name = "RULEID", precision = 10, nullable = false, unique = true)
	private Long ruleID;

	@Column(name = "PROGRAMID")
	private Long programID;

	@Column(name = "APPLICABLELIMIT", precision = 10, scale = 2, nullable = false, unique = false)
	private BigDecimal applicableLimit;

	@Column(name = "MULTIPLIER", precision = 10, scale = 2, nullable = false, unique = false)
	private BigDecimal multiplier;

	@Column(name = "BONUSPOINTS", precision = 10, scale = 2, nullable = false, unique = false)
	private BigDecimal bonusPoints;

	@Column(name = "DESCRIPTION", length = 100, nullable = false, unique = false)
	private String description;

	@CreationTimestamp
	@Column(name = "CREATEDATE", nullable = true, unique = false)
	private LocalDateTime createDate;

	@Column(name = "CREATEUSER", length = 50, nullable = true, unique = false, columnDefinition = "varchar(50) default \'Admin\'")
	private String createUser;

	@Column(name = "LASTUPDATETIMESTAMP", nullable = true, unique = false)
	private LocalDateTime updateDate;

	@Column(name = "UPDATEDBY", length = 50, nullable = true, unique = false, columnDefinition = "varchar(50) default \'Admin\'")
	private String updateUser;

}
