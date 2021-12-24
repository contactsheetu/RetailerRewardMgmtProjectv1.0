package com.retailer.rewardmgmt.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Table(name = "REWARD_PROGRAM")
@Entity
@Getter
@Setter
public class RewardProgramModel {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "program_id_seq")
	@SequenceGenerator(name = "program_id_seq", sequenceName = "program_id_seq", initialValue = 1, allocationSize = 1)
	@Column(name = "PROGRAMID", precision = 10, nullable = false, unique = false)
	@Id
	private Long programID;

	@Column(name = "DESCRIPTION", length = 100, nullable = false, unique = false)
	private String description;
	
	@OneToMany(targetEntity = RewardRuleModel.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "programID")
	private List<RewardRuleModel> rewardProgramRules;

	@Column(name = "STARTDATE", nullable = true, unique = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;

	@Column(name = "EXPIRYDATE", nullable = true, unique = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expiryDate;
	
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
