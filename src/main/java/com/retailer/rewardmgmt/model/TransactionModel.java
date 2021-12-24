package com.retailer.rewardmgmt.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "TRANSACTION")
@EqualsAndHashCode(of = { "transactionID", "transactionDate" })
public class TransactionModel {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRANSACTIONID", updatable = false, nullable = false)
	long transactionID;

	@Getter
	@Setter
	@CreationTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "TRANSACTIONDATE", updatable = false)
	@NonNull
	LocalDate transactionDate;

	@Getter
	@Column(name = "AMOUNT", precision = 10, scale = 2)
	@NonNull
	BigDecimal amount;

	@Getter
	@Setter
	@Column(name = "USERID")
	long userID;
	

	@Getter
	@Setter
    @OneToOne(targetEntity = RewardPointsModel.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "TRANSACTIONID", referencedColumnName = "TRANSACTIONID", insertable = true)
    RewardPointsModel rewardPoint;

    
    @OneToOne(targetEntity = UserModel.class)
	@JoinColumn(name = "userID",  updatable = false, insertable = false)
    @Getter
	@Setter
	@JsonIgnore
	UserModel user;
    
    @Getter
    @Setter
	@Column(name = "DESCRIPTION")
	private String description;

	public TransactionModel(BigDecimal amount, LocalDate transactionDate, String description,
			long userID) {
		super();
		this.amount = amount.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		this.description = description;
		this.userID = userID;
		this.transactionDate = transactionDate;
	}

	public TransactionModel(BigDecimal amount, String description, long userID) {
		super();
		this.amount = amount.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		this.description = description;
		this.userID = userID;
	}

	public void setAmount(BigDecimal amount) {
		amount = amount == null ? new BigDecimal(0.0) : amount;
		BigDecimal bd = amount.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		this.amount = bd;
	}

}
