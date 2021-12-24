package com.retailer.rewardmgmt.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER")
@EqualsAndHashCode(of= {"userID"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserModel {

	@Id
	@SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq", initialValue = 6, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "USERID" , updatable = false, nullable = false)
	@Getter	@Setter
	long userID;

	@Getter	@Setter
	@Column(name = "FIRSTNAME")
	String firstName;	
		
	@Getter	@Setter
	@Column(name = "LASTNAME")
	String lastName;
	
	
    @OneToMany(targetEntity = TransactionModel.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    private List<TransactionModel> userTransactions;
    
	@Getter
	@Setter
    @OneToOne(targetEntity = UserProgramModel.class)
	@JoinColumn(name = "userID" , referencedColumnName = "userID")
	UserProgramModel userProgram;

}
