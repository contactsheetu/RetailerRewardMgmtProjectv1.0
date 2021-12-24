package com.retailer.rewardmgmt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER_PROGRAM")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProgramModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	long id;

	@Column(name = "USERID")
	long userID;

	@Column(name = "PROGRAMID")
	long programID;

	@OneToOne(targetEntity = RewardProgramModel.class)
	@JoinColumn(name = "PROGRAMID", referencedColumnName = "PROGRAMID", updatable = false, insertable = false)
	RewardProgramModel rewardProgram;

}
