package com.retailer.rewardmgmt.rules;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.deliveredtechnologies.rulebook.RuleState;
import com.deliveredtechnologies.rulebook.annotation.Given;
import com.deliveredtechnologies.rulebook.annotation.Result;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;
import com.retailer.rewardmgmt.model.RewardRuleModel;
import com.retailer.rewardmgmt.model.TransactionModel;

@Rule(order = 1)
public class RuleForPurchasedAbove100 {

	private static BigDecimal PURCHASELIMIT_100 = new BigDecimal(100);

	@Given
	TransactionModel tx;

	@Result
	private BigDecimal rewardPoints;

	@When
	public boolean when() {
		return tx.getAmount().compareTo(PURCHASELIMIT_100) > 0;
	}

	@Then
	public RuleState then() {
		List<RewardRuleModel> rules = tx.getUser().getUserProgram().getRewardProgram().getRewardProgramRules();
		Optional<RewardRuleModel> rule = rules.stream()
				.filter(r -> r.getApplicableLimit().compareTo(PURCHASELIMIT_100) == 0).findFirst();

		if (rule.isPresent()) {
			rewardPoints = rule.get().getBonusPoints()
					.add(rule.get().getMultiplier().multiply((tx.getAmount().subtract(PURCHASELIMIT_100))));
			return RuleState.BREAK;
		}
		return RuleState.NEXT;
	}
}
