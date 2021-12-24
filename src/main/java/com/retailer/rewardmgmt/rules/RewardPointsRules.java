package com.retailer.rewardmgmt.rules;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;

import com.deliveredtechnologies.rulebook.Fact;
import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.model.RuleBook;
import com.deliveredtechnologies.rulebook.model.rulechain.cor.CoRRuleBook;
import com.deliveredtechnologies.rulebook.model.runner.RuleBookRunner;
import com.retailer.rewardmgmt.model.TransactionModel;
import com.retailer.rewardmgmt.services.RewardPointsService;

public class RewardPointsRules extends CoRRuleBook<Long> {
	
	@SuppressWarnings("unchecked")
	private static RuleBook<BigDecimal> ruleBook = new RuleBookRunner("com.retailer.rewardmgmt.rules");
	
	@Autowired
	RewardPointsService rewardPointsService;

	public static BigDecimal calculateAwardPoints(TransactionModel tx) {
		ruleBook.setDefaultResult(new BigDecimal(0).setScale(2));

		NameValueReferableMap<TransactionModel> facts = new FactMap<TransactionModel>();
		facts.put("", new Fact<TransactionModel>(tx));
		ruleBook.run(facts);

		return ruleBook.getResult().get().getValue().setScale(2, RoundingMode.HALF_EVEN);
	}


}
