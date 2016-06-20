package com.ashishpinninti;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.spring.SpringRule;

@SpringRule
public class OverWeightRule {

	private Metric metric;
	private static final int BASE_WEIGHT = 160;

	public OverWeightRule() {
	}

	public OverWeightRule(Metric metric) {
		super();
		this.metric = metric;
	}

	@Condition
	public boolean evaluate() {
		int upperLimit = (int) (BASE_WEIGHT + BASE_WEIGHT * (0.1));
		return metric.getValue() >= upperLimit;
	}

	@Action
	public void execute() {
		Alert alert = new Alert(this.metric, "Over Weight", BASE_WEIGHT,
				String.valueOf(System.currentTimeMillis()));
		MicroserviceDemoApplication.getDatastore().save(alert);
	}
}
